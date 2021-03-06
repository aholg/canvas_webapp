package aholg.model;


/**
 * Abstract class that takes two coordinates and can be extended for a specific shape.
 * @author Anton
 *
 */
public abstract class Shape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Shape(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	int getX1() {
		return x1;
	}

	int getY1() {
		return y1;
	}

	int getX2() {
		return x2;
	}

	int getY2() {
		return y2;
	}
}
