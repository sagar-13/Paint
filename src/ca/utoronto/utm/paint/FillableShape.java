package ca.utoronto.utm.paint;

import java.awt.Color;

/**
 * A Shape that can be filled. Has a boolean fill. 
 *
 */
public abstract class FillableShape extends Shape {

	private boolean fill; //The fill status.
	
	/**
	 * Constructs a new FillableShape.
	 * @param color The color of the shape.
	 * @param fill True if the shape is to be filled, false otherwise.
	 * @param stroke The stroke the shape will be drawn in.
	 */
	public FillableShape(Color color, boolean fill, int stroke){
		super(color, stroke);
		this.fill = fill;
	
	}

	/**
	 * Return whether the shape is to be filled or not.
	 * @return True if the shape is filled, false otherwise.
	 */
	public boolean isFilled(){
		return this.fill;
	}
	
	/**
	 * Change the shape's fill status to true.
	 */
	public void setFill(){
		this.fill = true;
	}
	
	
}
