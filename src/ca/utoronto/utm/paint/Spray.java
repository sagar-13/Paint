package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A Spray is a shape that has an ArrayList of points. A Spray can add new 
 * points to this ArrayList and can be drawn.
 */
public class Spray extends Shape {
	private ArrayList<Point> points; //The points that make up the Spray.
	
	/**
	 * Construct a new spray.
	 * @param color The color of the spray.
	 * @param stroke The stroke thickness.
	 */
	public Spray(Color color, int stroke) {
		super(color, stroke);
		points = new ArrayList<Point>();
	}
	
	/**
	 * Add a point to the Spray.
	 * @param p The point to be added.
	 */
	public void addPoint(Point p) {
		points.add(p);
	}
	
	/**
	 * Removes the last point from the Spray.
	 */
	public void removePoint() {
		points.remove(points.size()-1);
	}
	
	/**
	 * Return the points of the spray.
	 * @return The list of points that make up the spray.
	 */
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	@Override
	/**
	 * Draw the Spray.
	 */
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		for(int i=0;i<points.size()-1; i++){
			g2d.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i).getX(), points.get(i).getY());
		}		
	}
}
