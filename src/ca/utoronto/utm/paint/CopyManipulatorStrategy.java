package ca.utoronto.utm.paint;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/**
 * CopyListener is a ShapeManipulatorStrategy that performs the
 * necessary actions to copy a shape. When a copy is selected, and the mouse is
 * pressed, a rectangle is created to enclose the shape the user wishes to copy.
 * CopyListener has an associated view, a list of shapes to copy, an associated
 * model, a Rectangle to enclose what's being copied and a boolean, cut.
 */
public class CopyManipulatorStrategy implements ShapeManipulatorStrategy {
	private View view; //associated view
	private ArrayList<DrawingCommand> toCopy; //shapes to copy
	private PaintModel model; //associated model
	private Rectangle rectangle; //rectangle for shape enclosure
	private Point copyOrigin; //point where a shape is copied from
	private boolean cut; // boolean: cut or not
	
	/**
	 * Creates an instance of CopyListener.
	 * @param model  associated model
	 * @param view	associated view
	 * @param cut  boolean for whether or not it is a cut.
	 */
	public CopyManipulatorStrategy(PaintModel model, View view, boolean cut){
		this.view = view;
		this.model = model;
		this.cut = cut;
	}

	
	@Override
	/**
	 * Performs an action when the mouse is pressed. 
	 * Instantiates copy space rectangle and sets values.
	 */
	public void mousePressed(MouseEvent e) {
		//Prevents errors than come with having multiple buttons pressed at the same time.
		if (SwingUtilities.isRightMouseButton(e))
			return;
		//Initialize new rectangle
		Point corner = new Point(e.getX(), e.getY());
		Color color = Color.BLACK;
		// Gets the current value for shape thickness	
		int currentThickness = 2;
		rectangle = new Rectangle(corner, 0, 0, corner, color, false, currentThickness);
		// Sets whether the shape is to be filled or not
		
	}
	
	/**
	 * Performs actions when the mouse is dragged. Changes the area of the space to be copied.
	 */
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e))
			return;
		this.model.getCommands().remove(this.rectangle);
		int mouseX = e.getX();
		int mouseY = e.getY();
		int oppositeCornerY = (int) Math.max(mouseY, this.rectangle.getOrigin().getY());
		int oppositeCornerX = (int) Math.max(mouseX, this.rectangle.getOrigin().getX());
		//Min value of X and Y should be where the drawing is started from since drawing proceeds from
		//upper left (smaller values of X and Y) to bottom right.
		int startingDrawCornerY = (int) Math.min(mouseY, this.rectangle.getOrigin().getY());
		int startingDrawCornerX = (int) Math.min(mouseX, this.rectangle.getOrigin().getX());
		//Height and width will therefore be the larger x and y minus the smaller x and y.
		this.rectangle.setHeight(oppositeCornerY-startingDrawCornerY);
		this.rectangle.setWidth(oppositeCornerX-startingDrawCornerX);
		this.rectangle.setCorner(new Point(startingDrawCornerX,startingDrawCornerY));
		this.model.addCommand(this.rectangle);
		}
		
	/**
	 * Performs actions when the mouse is released. Adds the copied shapes to the model.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e))
			return;
		toCopy = new ArrayList<>();
		boolean contains = false;
		//Give the values of the dragged rectangle to the new Rectangle2D object so that the contains method can be
		//used.
		Rectangle2D.Double copySpace = new Rectangle2D.Double(rectangle.getCorner().getX(),rectangle.getCorner().getY(), 
				rectangle.getWidth(), rectangle.getHeight());
		for (DrawingCommand d: model.getCommands()){
			if (d instanceof Circle){
				Circle dc1 = (Circle)d;
				contains = copySpace.contains(dc1.getCentre().getX()-dc1.getRadius(),dc1.getCentre().getY()-dc1.getRadius(),
						dc1.getRadius()*2,dc1.getRadius()*2);
				if (contains)
					toCopy.add(d);
			}
			if (d instanceof Ellipse){
				Ellipse dc1 = (Ellipse)d;
				contains = copySpace.contains(dc1.getEdge().getX(),dc1.getEdge().getY(),
						dc1.getWidth(),dc1.getHeight());
				if (contains)
					toCopy.add(d);
			}
			else if (d instanceof Squiggle){
				contains = true;
				Squiggle dc1 = (Squiggle)d;
				for (Point p: dc1.getPoints())
					if (!(copySpace.contains(p.getX(),p.getY()))){
						contains = false;
					}
				if (contains)
					toCopy.add(d);
			}
			else if (d instanceof Rectangle && d != rectangle){
				Rectangle dc1 = (Rectangle)d;
				contains = copySpace.contains(dc1.getCorner().getX(), dc1.getCorner().getY(), dc1.getWidth(), dc1.getHeight());
				if (contains)
					toCopy.add(d);
			}
			else if (d instanceof Spray){
				contains = true;
				Spray dc1 = (Spray) d;
				for (Point p: dc1.getPoints())
					if (!(copySpace.contains(p.getX(),p.getY()))){
						contains = false;
					}
				if (contains)
					toCopy.add(d);
			}
			else if (d instanceof Triangle){
				contains = true;
				Triangle dc1 = (Triangle) d;
				for (Point p: dc1.getCorners())
					if (!(copySpace.contains(p.getX(),p.getY()))){
						contains = false;
					}
				if (contains)
					toCopy.add(d);
			}
			
		}
		copyOrigin = new Point((int)copySpace.getCenterX(),(int)copySpace.getCenterY());
		if (cut){
			// creating a shallow copy of the current commands
			ArrayList<DrawingCommand> c = new ArrayList<DrawingCommand>(this.model.getCommands());
			c.remove(rectangle);
			this.model.addCommand(new Cut(c, toCopy));
			for(DrawingCommand d: toCopy){
				this.model.removeCommand(d);
			}

		}
		this.model.removeCommand(rectangle);
		
	}

	/**
	 * Returns the initial point where the shape is copied from.
	 * @return Point - initial copy point.
	 */
	public Point getCopyOrigin(){
		return this.copyOrigin;
	}
	
	/**
	 * Returns the list of shapes to copy
	 * @return ArrayList<DrawingCommand> Shapes to copy.
	 */
	public ArrayList<DrawingCommand> getCopyList(){
		return this.toCopy;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	

}
