package aholg.model;

/**
 * Holds a color and a coordinate to be used for bucket fill.
 * @author Anton
 *
 */
public class Node  {
	private String color;
	private int x;
	private int y;
	private boolean processed;
	/**
	 * Creates a new color object.
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 * @param color - Color to be used.
	 */
	public Node(int x,int y, String color) {
		this.color=color;
		this.x=x;
		this.y=y;
		setProcessed(false);
	}
	String getColor() {
		return color;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	boolean isProcessed() {
		return processed;
	}
	void setProcessed(boolean processed) {
		this.processed = processed;
	}

}
