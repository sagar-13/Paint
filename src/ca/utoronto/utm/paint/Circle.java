package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A circle is a shape that has a center and radius. A circle can set these 
 * attributes and can be drawn.
 */
public class Circle extends FillableShape {
	private Point centre; //The center of  the circle.
	private int radius; //The radius of the circle.
	
	/**
	 * /**
	 * Construct a new Circle.
	 * @param centre The center of the circle.
	 * @param radius The radius of the circle.
	 * @param fill True if the shape is to be filled, false otherwise.
	 * @param color The color of the circle.
	 * @param fill True if the shape is to be filled, false otherwise.
	 * @param stroke The stroke thickness.
	 */
	public Circle(Point centre, int radius,Color color, boolean fill, int stroke){
		super(color, fill, stroke);
		this.centre = centre;
		this.radius = radius;
		
	}
	
	/**
	 * Return the center of the circle.
	 * @return The center of the circle.
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Set the center of the circle.
	 * @param centre The circle's new center.
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/**
	 * Return the radius of the circle.
	 * @return The radius of the circle.
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Set the radius of the circle.
	 * @param radius The new radius of the circle.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	

	@Override
	/**
	 * Draw the circle.
	 */
	public void execute(Graphics2D g2d) {
		int x = this.centre.getX();
		int y = this.centre.getY();
		int radius = this.radius;
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		if (this.isFilled()){
			g2d.fillOval(x-radius, y-radius, radius*2, radius*2);
		}
		else { 
			g2d.drawOval(x-radius, y-radius, radius*2, radius*2);
		}
	}

}
