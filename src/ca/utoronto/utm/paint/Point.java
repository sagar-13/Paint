package ca.utoronto.utm.paint;

/**
 * A point has an x and y that will be used to plot itself when being drawn. *
 */
public class Point{
	int x, y; //The x and y coordinates.
	
	/**
	 * Creates a point
	 * @param x The x coordinate 
	 * @param y the y coordinate
	 */
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	/**
	 * Return the x coordinate
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate
	 * @param x The x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Return the y coordinate
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y coordinate
	 * @param y The y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
}
