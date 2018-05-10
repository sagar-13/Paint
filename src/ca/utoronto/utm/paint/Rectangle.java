package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A rectangle is a shape that has a corner, width, height, and origin. 
 * A rectangle can set these attributes and can be drawn.
 */
public class Rectangle extends FillableShape {
	private Point corner; //The corner that the rectangle is to be drawn from.
	private int height; //The height of the rectangle.
	private int width; //The width of the rectangle.
	private Point origin; //The initial corner of the rectangle.
	
	/**
	 * Construct a new rectangle.
	 * @param corner The corner the rectangle is to be drawn from.
	 * @param height The height of the rectangle.
	 * @param width The width of the rectangle.
	 * @param origin The initial corner of the rectangle.
	 * @param color The color of the rectangle.
	 * @param fill True if the rectangle is to be filled, false otherwise.
	 * @param stroke The stroke of the rectangle.
	 */
	public Rectangle(Point corner, int height, int width, Point origin, Color color, boolean fill, int stroke){
		super(color, fill, stroke);
		this.corner = corner;
		this.height = height;
		this.width = width;
		this.origin = origin;
	}
	
	/**
	 * Return the corner of the rectangle.
	 * @return The corner.
	 */
	public Point getCorner(){
		return this.corner;
	}
	
	/**
	 * Return the height of the rectangle.
	 * @return The height of the rectangle.
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Return the width of the rectangle.
	 * @return The width of the rectangle.
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Set the corner of the rectangle.
	 * @param corner The rectangle's new corner.
	 */
	public void setCorner(Point corner){
		this.corner = corner;
	}
	
	/**
	 * Set the height of the rectangle.
	 * @param height The rectangle's new height.
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * Set the width of the rectangle.
	 * @param width The rectangle's new width.
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * Return the origin of the rectangle.
	 * @return The origin of the rectangle.
	 */
	public Point getOrigin(){
		return this.origin;
	}
	
	@Override
	/**
	 * Draw the rectangle.
	 */
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		if (this.isFilled()){
			g2d.fillRect(corner.getX(), corner.getY(), width, height);
		}
		else { 
			g2d.drawRect(corner.getX(), corner.getY(), width, height);
			
		}
		
	}

}
