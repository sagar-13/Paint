package ca.utoronto.utm.paint;

import java.awt.Graphics2D;

/**
 * A DrawingCommand can be executed in order to draw a shape.
 *
 */
public interface DrawingCommand {
	
	/**
	 * Draw the shape specified in the DrawingCommand
	 * @param g2d The graphics used to draw the shape.
	 */
	public void execute(Graphics2D g2d);

}
