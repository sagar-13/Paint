package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * RectangleStrategy is a MouseListener and MouseMotionListener that performs 
 * the necessary actions when the mouse is moved in order to draw a rectangle.
 * RectangleStrategy has a rectangle, an associated model, and an associated 
 * view. RectangleStrategy can perform actions in response to mouse events.
 */
public class RectangleManipulatorStrategy implements ShapeManipulatorStrategy {

	private Rectangle rectangle; //The rectangle to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a RectangleStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public RectangleManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view=view;
	}

	// MouseMotionListener below
	
	@Override
	/**
	 * Performs an action when mouse is dragged. Provides feedback on rectangle drawing.
	 */
	public void mouseDragged(MouseEvent e) {	
		if (!(SwingUtilities.isLeftMouseButton(e)))
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
		
	@Override
	/**
	 * Performs an action when the mouse is pressed. Instantiates rectangle and sets values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//Initialize new rectangle
		Point corner = new Point(e.getX(), e.getY());
		Color color = this.view.getColorChooserPanel().getColor();
		
		// Gets the current value for shape thickness and fill.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		Boolean fill = this.view.getFillButton().getFill();
		
		this.rectangle = new Rectangle(corner, 0, 0, corner, color, fill, currentThickness);
		
	}

	@Override
	/**
	 * Performs an action when the mouse is released. Sets the drawn rectangle back to null.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//Resets this class' rectangle for future drawings.
		if(this.rectangle!=null){
			this.rectangle=null;}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
