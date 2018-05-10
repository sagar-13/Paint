package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A Triangle is a shape that has a corner and corners list.
 * A Triangle can set these attributes and can be drawn.
 */
public class Triangle extends FillableShape {
	private ArrayList<Point> corners = new ArrayList<Point>(); //The corners that the Triangle is to be drawn from.
	
	/**
	 * Construct a new Triangle.
	 * @param corner The corner the Triangle is to be drawn from.
	 * @param color The color of the Triangle.
	 * @param fill True if the Triangle is to be filled, false otherwise.
	 * @param stroke The stroke of the Triangle.
	 */
	public Triangle(Point corner, Color color, boolean fill, int stroke){
		super(color, fill, stroke);
		for (int i = 0; i < 3; i++) {
			this.corners.add(corner);
		}
	}
	
	/**
	 * Adds the corner to the given index of the corners list.
	 * @param index
	 * @param corner
	 */
	public void setCorner(int index, Point corner) {
		this.corners.set(index, corner);
	}
	
	/**
	 * Return the list of corners.
	 * @return The list of corners.
	 */
	public ArrayList<Point> getCorners(){
		return this.corners;
	}
	
	@Override
	/**
	 * Draw the Triangle.
	 */
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		if (this.isFilled() && this.corners.get(1).getX() != this.corners.get(2).getX() && this.corners.get(1).getY() != this.corners.get(2).getY()){
			g2d.fillPolygon(new int[] {this.corners.get(0).getX(), this.corners.get(1).getX(), this.corners.get(2).getX()},
							new int[] {this.corners.get(0).getY(), this.corners.get(1).getY(), this.corners.get(2).getY()},
							3);
		} else {
			g2d.drawPolygon(new int[] {this.corners.get(0).getX(), this.corners.get(1).getX(), this.corners.get(2).getX()},
							new int[] {this.corners.get(0).getY(), this.corners.get(1).getY(), this.corners.get(2).getY()},
							3);
			}
	}
}
