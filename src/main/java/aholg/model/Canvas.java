package aholg.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class holds the canvas matrix and adds new shapes and colors to it.
 * 
 * @author Anton
 *
 */
public class Canvas {
	private int width;
	private int height;
	private String[][] canvasBoard;
	private ArrayList<Observer> observers;

	/**
	 * Constructor for a new canvas.
	 * 
	 * @param width
	 *            - Canvas width.
	 * @param height
	 *            - Canvas height.
	 * @throws Exception
	 *             - Exception if height or width are below 1.
	 */
	public Canvas(int width, int height) throws Exception {
		checkCanvas(width, height);
		observers = new ArrayList();
		this.width = width;
		this.height = height;
		canvasBoard = new String[height + 2][width + 2];

	}

	/**
	 * Creates a new line and puts it in the canvasboard matrix.
	 * 
	 * @param line
	 *            - object containing coordinates.
	 * @throws IndexOutOfBoundsException
	 *             - Throws out of bounds error if rectangle coordinates are
	 *             outside the canvas borders.
	 * @throws Exception
	 *             - Throws exception for unsupported diagonal lines.
	 */
	public void newLine(Line line) throws IndexOutOfBoundsException, Exception {
		int posx, posy;
		int x1 = line.getX1();
		int x2 = line.getX2();
		int y1 = line.getY1();
		int y2 = line.getY2();

		checkIndexes(x1, y1, x2, y2);
		if (y1 == y2) {
			posx = x1;
			while (posx <= x2) {
				canvasBoard[y1][posx] = "x";
				posx++;
			}
		} else if (y1 > y2 && x1 == x2) {
			posx = x1;
			posy = y1;
			while (posx <= x2 && posy >= y2) {
				canvasBoard[posy][posx] = "x";

				posy--;
			}
		} else if (y1 < y2 && x1 == x2) {
			posx = x1;
			posy = y1;
			while (posx <= x2 && posy <= y2) {
				canvasBoard[posy][posx] = "x";

				posy++;
			}
		} else {
			throw new Exception("Diagonal lines are not supported. Please enter a horizontal or vertical line.");
		}

	}

	/**
	 * Creates a new rectangle and puts it on the canvas matrix.
	 * 
	 * @param rectangle
	 *            - object containing coordinates for the rectangle.
	 * @throws IndexOutOfBoundsException
	 *             - Throws out of bounds error if rectangle coordinates are
	 *             outside the canvas borders.
	 */
	public void newRectangle(Rectangle rectangle) throws IndexOutOfBoundsException, Exception {
		int x1 = rectangle.getX1();
		int x2 = rectangle.getX2();
		int y1 = rectangle.getY1();
		int y2 = rectangle.getY2();
		newLine(new Line(x1, y1, x2, y1));
		newLine(new Line(x1, y1, x1, y2));
		newLine(new Line(x2, y1, x2, y2));
		newLine(new Line(x1, y2, x2, y2));

	}

	/**
	 * Flood fill algorithm for coloring the canvas.
	 * 
	 * @param Node
	 *            - contains x and y coordinate and color of the node.
	 * @param targetColor
	 *            - Target color to be changed.
	 * @param replacementColor
	 *            - Replacement color to be used.
	 */
	private void floodFill(Node node, String targetColor, String replacementColor) {
		if (targetColor.equals(replacementColor)) {
			return;
		}

		Queue<Node> q = new LinkedList<>();

		int[][] nodeMap = new int[height + 2][width + 2];
		q.add(node);

		while (q.isEmpty() == false) {
			node = q.remove();
			
			int x = node.getX();
			int y = node.getY();

			if (node.getColor().equals(targetColor)) {

				canvasBoard[y][x] = replacementColor;
				if (nodeMap[y][x - 1] == 0) {

					q.add(new Node(x - 1, y, canvasBoard[y][x - 1]));
					nodeMap[y][x - 1] = 1;
				}
				if (nodeMap[y][x + 1] == 0) {

					q.add(new Node(x + 1, y, canvasBoard[y][x + 1]));
					nodeMap[y][x + 1] = 1;
				}
				if (nodeMap[y + 1][x] == 0) {

					q.add(new Node(x, y + 1, canvasBoard[y + 1][x]));
					nodeMap[y + 1][x] = 1;
				}
				if (nodeMap[y - 1][x] == 0) {

					q.add(new Node(x, y - 1, canvasBoard[y - 1][x]));
					nodeMap[y - 1][x] = 1;
				}

			}

		}

	}

	/**
	 * Fills an area on the canvas with color starting from a given coordinate
	 * provided by a node object.
	 * 
	 * @param color
	 *            - Node object containing new color and coordinates.
	 */
	public void colorFill(Node color) {
		int x = color.getX();
		int y = color.getY();
		checkIndexes(x, y);
		String paint = color.getColor();
		Node node = new Node(x, y, canvasBoard[y][x]);
		floodFill(node, canvasBoard[y][x], paint);

	}

	/**
	 * Creates a new canvas matrix according to the width and height entered in
	 * the constructor.
	 */
	public void newCanvas() {

		for (int row = 0; row <= height + 1; row++) {
			for (int col = 0; col <= width + 1; col++) {
				if ((col == 0 || col == width + 1) && (row > 0 && row <= height)) {
					canvasBoard[row][col] = "|";
				} else if ((row == 0 || row == height + 1)) {
					canvasBoard[row][col] = "-";
				} else {
					canvasBoard[row][col] = " ";
				}

			}
		}

	}

	/**
	 * Prints the canvas matrix to a string and notifies the registered
	 * observers.
	 */
	public String printCanvas() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < canvasBoard.length; i++) {
			
			for (int j = 0; j < canvasBoard[i].length; j++) {
				result.append(canvasBoard[i][j]);

			}
			result.append("\n");
			result.setLength(result.length());
			
		}
		return result.toString();
	}

	/**
	 * Checks that coordinates are within the canvas area.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws IndexOutOfBoundsException
	 *             - Thrown if a value was outside the area.
	 */
	private void checkIndexes(int x1, int y1, int x2, int y2) throws IndexOutOfBoundsException {
		if (x1 < 1 || x1 > width) {
			throw new IndexOutOfBoundsException("Value x1 is outside of canvas: " + x1);
		} else if (y1 < 1 || y1 > height) {
			throw new IndexOutOfBoundsException("Value y1 is outside of canvas: " + y1);
		} else if (x2 < 1 || x2 > width) {
			throw new IndexOutOfBoundsException("Value x2 is outside of canvas: " + x2);
		} else if (y2 < 1 || y2 > height) {
			throw new IndexOutOfBoundsException("Value y2 is outside of canvas: " + y2);
		}
	}

	/**
	 * Checks that coordinates are within the canvas area.
	 * 
	 * @param x1
	 * @param y1
	 * @throws IndexOutOfBoundsException
	 *             - Thrown if a value was outside the area.
	 */
	private void checkIndexes(int x1, int y1) throws IndexOutOfBoundsException {
		if (x1 < 1 || x1 > width) {
			throw new IndexOutOfBoundsException("Value x1 is outside of canvas: " + x1);
		} else if (y1 < 1 || y1 > height) {
			throw new IndexOutOfBoundsException("Value y1 is outside of canvas: " + y1);
		}
	}

	/**
	 * Checks that given width and height are larger than 0 for a new canvas
	 * object.
	 * 
	 * @param width
	 * @param height
	 * @throws Exception
	 *             - Thrown if width or height was below 0.
	 */
	private void checkCanvas(int width, int height) throws Exception {
		if (width < 1) {
			throw new Exception("Invalid width size, please enter a width bigger than 0.");
		} else if (height < 1) {
			throw new Exception("Invalid height size, please enter a width bigger than 0.");
		}
	}

	public void clear(){
		newCanvas();
	}
	
	/**
	 * Notify all added observers(console) with a given ouput.
	 *
	 * @param output
	 *            - Output to notify observers with.
	 * 
	 */
	private void notify(String output) {

		for (Observer obsrv : observers) {
			obsrv.notify(output);
		}
	}

	/**
	 * Adds an observer to be notified.
	 *
	 * @param obs
	 *            Observer to add.
	 */
	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
