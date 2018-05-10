package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A squiggle is a shape that has an ArrayList of points. A squiggle can add 
 * new points to this ArrayList and can be drawn.
 */
public class Squiggle extends Shape {
	private ArrayList<Point> points; //The points that make up the squiggle.
	private boolean eraser; //True if the squiggle will be used as an eraser, false otherwise.
	
	/**
	 * Construct a new squiggle.
	 * @param color The color of the squiggle.
	 * @param stroke The stroke thickness.
	 * @param eraser True if the squiggle will be used as an eraser, false otherwise.
	 */
	public Squiggle(Color color, int stroke, boolean eraser) {
		super(color,stroke);
		points = new ArrayList<Point>();
		this.eraser = eraser;
	}
	
	/**
	 * Add a point to the squiggle.
	 * @param p The point to be added.
	 */
	public void addPoint(Point p) {
		points.add(p);
	}
	
	/**
	 * Removes the last point from the squiggle.
	 */
	public void removePoint() {
		points.remove(points.size()-1);
	}
	
	/**
	 * Return the points that make up the eraser.
	 * @return The ArrayList of points that make up the eraser.
	 */
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	/**
	 * Return if the squiggle is an eraser.
	 * @return True if the squiggle is an eraser, false otherwise.
	 */
	public boolean getEraser() {
		return eraser;
	}
	@Override
	/**
	 * Draw the squiggle.
	 */
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getStroke()));
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}		
	}
}
