package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Shape has a color, stroke. A shape can set these attributes and can be executed (drawn).
 *
 */
public abstract class Shape implements DrawingCommand{
	private Color color = Color.BLACK; //Default color
	private int stroke; //The stroke thickness. Always 0<=stroke<=20.
	
	/**
	 * A constructor subclasses of Shape would potentially use.
	 * @param color The color of the shape.
	 * @param stroke The stroke of the shape.
	 */
	public Shape(Color color, int stroke){
		this.color = color;
		this.stroke = stroke;
	}
	
	/**
	 * Set the color of the shape.
	 * @param color The color of the shape.
	 */
	public void setColor(Color color) {
		this.color=color;
	}
	
	/**
	 * Get the color of the shape.
	 * @return The color of the shape.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Return the stroke thickness of the shape.
	 * @return The stroke thickness of the shape.
	 */
	public int getStroke() {
		return this.stroke;
	}
	
	/**
	 * Set the stroke thickness.
	 * @param n The stroke thickness.
	 */
	public void setStroke(int n) {
		this.stroke = n;
	}
	
	/**
	 * Draw the shape.
	 * @param g2d The graphics.
	 */
	public abstract void execute(Graphics2D g2d);
	
}
