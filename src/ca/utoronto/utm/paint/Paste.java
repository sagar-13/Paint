package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A paste is a shape that has a transparent point and an count of number of shapes in a paste (numShapes). 
 * A paste can get the number of shapes in a paste. 
 * A paste is used to keep track of how many new shapes are added to the DrawingCommand stack in the model
 * it is also a placeholder in the case the user wants to undo a paste, the model will know how many times to undo
 * to undo all of the pasted shapes.
 * It is a shape so it can be placed on the DrawingCommand stack to keep the order of the actions.
 */
public class Paste extends Shape{
	
	private Point p = new Point(0, 0);
	private int numShapes;
	
	/**
	 * Creates a paste object with a transparent point
	 * @param numShapes The number of shapes in a paste
	 */
	public Paste(int numShapes) {
		super(new Color(0,0,0,0), 1);
		this.numShapes = numShapes;
	}
	
	 /**
	  * Returns the number of shapes in a paste
	  * @return The number of shapes in a paste
	  */
	public int getNumShapes() {
		return numShapes;
	}

	@Override
	/**
	 * Draws the paste object
	 */
	public void execute(Graphics2D g2d) {
		g2d.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
	}
	
}
