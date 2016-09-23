package aholg.model;

/**
 * Holds information about a command.
 * 
 * @author Anton
 *
 */
public class Command {
	private Type type;
	private Shape shape;
	private Node color;
	private Canvas canvas;

	public enum Type {
		CANVAS, RECTANGLE, LINE, BUCKET, CLEAR, HELP, QUIT
	}

	

	/**
	 * Creates a new command.
	 * 
	 * @param type
	 *            - Holds the type of command:
	 *            'Canvas','Line','Rectangle','Bucket','Quit','Help'.
	 * @param color
	 *            - If type is 'Bucket' then a node object is stored here,
	 *            otherwise null.
	 */
	public Command(Type type, Node color) {
		if (type == null||color == null) 
            throw new IllegalArgumentException("Type and color can't be null");
		this.type = type;
		this.color = color;

	}

	/**
	 * Creates a new command.
	 * 
	 * @param type
	 *            - Holds the type of command:
	 *            'Canvas','Line','Rectangle','Bucket','Quit','Help'.
	 * @param canvas
	 *            - If type is 'Canvas' then a canvas object is stored,
	 *            otherwise null.
	 */
	public Command(Type type, Canvas canvas) {
		if (type == null||canvas == null) 
            throw new IllegalArgumentException("Type and canvas can't be null");
		this.type = type;
		this.canvas = canvas;
	}

	/**
	 * Creates a new command.
	 * 
	 * @param type
	 *            - Holds the type of command:
	 *            'Canvas','Line','Rectangle','Bucket','Quit','Help'.
	 * @param shape
	 *            - If type is either a 'Line' or 'Rectangle' then the shape of
	 *            that type is stored in this param, otherwise it is null.
	 */
	public Command(Type type, Shape shape) {
		if (type == null||shape == null) 
            throw new IllegalArgumentException("Type and shape can't be null");
		this.type = type;
		this.shape = shape;

	}

	/**
	 * Creates a new command.
	 * 
	 * @param type
	 *            - Holds the type of command:
	 *            'Canvas','Line','Rectangle','Bucket','Quit','Help'.
	 */
	public Command(Type type) {
		if (type == null) 
            throw new IllegalArgumentException("Type can't be null");
		this.type = type;

	}

	public Type getType() {
		return type;
	}

	public Shape getShape() {
		return shape;
	}

	public Node getColor() {
		return color;
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
