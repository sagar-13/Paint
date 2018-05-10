package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * An ellipse is a shape that has an edge, width height and origin.
 * An ellipse can set these attributes and can be drawn.
 */
public class Ellipse extends FillableShape {
	private Point edge; //The edge of the ellipse that is dragged.
	private int width; //The width of the ellipse.
	private int height; //The height of the ellipse.
	private Point origin; //The points where the ellipse originated from.
	
	/**
	 * Construct a new ellipse.
	 * @param edge The edge of the ellipse that is dragged.
	 * @param width The width of the ellipse.
	 * @param height The height of the ellipse.
	 * @param origin The points where the ellipse originated from.
	 * @param color The color of the ellipse.
	 * @param fill True if the shape is to be filled, false otherwise.
	 * @param stroke The stroke thickness.
	 */
	public Ellipse(Point edge, int width, int height, Point origin, Color color, boolean fill, int stroke) {
		super(color, fill, stroke);
		this.edge = edge;
		this.width = width;
		this.height = height;
		this.origin = origin;
	}
	
	/**
	 * Return the edge of the ellipse.
	 * @return The edge.
	 */
	public Point getEdge(){
		return this.edge;
	}
	
	/**
	 * Return the height of the ellipse.
	 * @return The height of the ellipse.
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Return the width of the ellipse.
	 * @return The width of the ellipse.
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Set the edge of the ellipse.
	 * @param edge The ellipse's new edge.
	 */
	public void setEdge(Point edge){
		this.edge = edge;
	}
	
	/**
	 * Set the height of the ellipse.
	 * @param height The ellipse's new height.
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * Set the width of the ellipse.
	 * @param width The ellipse's new width.
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * Return the origin of the ellipse.
	 * @return The origin of the ellipse.
	 */
	public Point getOrigin(){
		return this.origin;
	}

	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		if (this.isFilled()){
			g2d.fillOval(edge.getX(), edge.getY(), width, height);
		}
		else { 
			g2d.drawOval(edge.getX(), edge.getY(), width, height);
			
		}
		
	}

}
