package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;


/**
 * PasteListener is a MouseListener and MouseMotionListener that performs the
 * necessary actions to paste a shape. When paste is selected and a point is 
 * clicked, a previously copied shape is pasted at that point.
 * PasteListener has a shape to paste, an associated model, and copying point
 * of origin. 
 */
public class PasteManipulatorStrategy implements ShapeManipulatorStrategy {
	private ArrayList<DrawingCommand> toPaste; // List of shapes to paste
	private PaintModel model; // PaintModel, shapes stored here.
	private Point copyOrigin; // point of origin of copied shape
	
	/**
	 * Create an instance of PasteListener.
	 * @param model  The associated model. 
	 * @param toPaste The shapes to paste.
	 * @param copyOrigin  original point of copied shape
	 */
	public PasteManipulatorStrategy(PaintModel model, ArrayList<DrawingCommand> toPaste, Point copyOrigin){
		this.toPaste = toPaste;
		this.model = model;
		this.copyOrigin = copyOrigin;
	}

	/**
	 * Performs an action when the mouse is pressed. Pastes the current selection of copied shapes to the PaintPanel.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e))
			return;
		int x = 0;
		int y = 0;
		//Adds new shapes to the PaintModel list with the same attributes as those in the copy list but with
		//updated coordinates.
		for (DrawingCommand dc: toPaste){
			if (dc instanceof Circle){
				Circle dc1 = (Circle)dc;
				x = copyOrigin.getX() - dc1.getCentre().getX();
				y = copyOrigin.getY() - dc1.getCentre().getY();
				Point center = new Point(e.getX()-x,e.getY()-y);
				Shape circle = new Circle(center, dc1.getRadius(),dc1.getColor(),dc1.isFilled(),dc1.getStroke());
				this.model.addCommand(circle);
			}
			else if (dc instanceof Ellipse){
				Ellipse dc1 = (Ellipse)dc;
				x = copyOrigin.getX() - dc1.getEdge().getX();
				y = copyOrigin.getY() - dc1.getEdge().getY();
				Point edge = new Point(e.getX()-x,e.getY()-y);
				Ellipse ellipse = new Ellipse(edge, dc1.getWidth(), dc1.getHeight(), edge, dc1.getColor(), dc1.isFilled(), dc1.getStroke());
				this.model.addCommand(ellipse);
			}
			else if (dc instanceof Squiggle){
				Squiggle dc1 = (Squiggle)dc;
				Squiggle squiggle = new Squiggle(dc1.getColor(), dc1.getStroke(), dc1.getEraser());
				for (Point p: dc1.getPoints()){
					x = copyOrigin.getX() - p.getX();
					y = copyOrigin.getY() - p.getY();
					squiggle.addPoint(new Point(e.getX()-x, e.getY()-y));
				}
				this.model.addCommand(squiggle);
			}
			else if (dc instanceof Rectangle){
				Rectangle dc1 = (Rectangle)dc;
				x = copyOrigin.getX() - dc1.getCorner().getX();
				y = copyOrigin.getY() - dc1.getCorner().getY();
				Point corner = new Point(e.getX()-x, e.getY()-y);
				Rectangle rect = new Rectangle(corner,dc1.getHeight(),dc1.getWidth(),corner,dc1.getColor(),dc1.isFilled(),dc1.getStroke());
				this.model.addCommand(rect);
			}
			else if (dc instanceof Spray){
				Spray dc1 = (Spray)dc;
				Spray spray = new Spray(dc1.getColor(), dc1.getStroke());
				for (Point p: dc1.getPoints()){
					x = copyOrigin.getX() - p.getX();
					y = copyOrigin.getY() - p.getY();
					spray.addPoint(new Point(e.getX()-x, e.getY()-y));
				}
				this.model.addCommand(spray);
			}
			else if (dc instanceof Triangle){
				Triangle dc1 = (Triangle)dc;
				int i = 0;
				Triangle triangle = new Triangle(null,dc1.getColor(),dc1.isFilled(),dc1.getStroke());
				for (Point p: dc1.getCorners()){
					x = copyOrigin.getX() - p.getX();
					y = copyOrigin.getY() - p.getY();
					triangle.setCorner(i,new Point(e.getX()-x, e.getY()-y));
					i++;
				}
				this.model.addCommand(triangle);
			}
		}
		this.model.addCommand(new Paste(toPaste.size()));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
