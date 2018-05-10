package ca.utoronto.utm.paint;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * SquareStrategy is a MouseListener and MouseMotionListener that performs the
 * necessary actions when the mouse is moved in order to draw a Square.
 * SquareStrategy has a square, an associated model, and an associated view.
 * SquareStrategy can perform actions in response to mouse events.
 */
public class SquareManipulatorStrategy implements ShapeManipulatorStrategy {
	private Rectangle rectangle; //The rectangle to be drawn (a square is a rectangle).
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a SquareStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public SquareManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view=view;
	}

	@Override
	/**
	 * Performs an action when the mouse is dragged. Provides feedback on square drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//If the mouse is not on the diagonal of the square, the mouse is forced to move on the diagonal.
		if (!(Math.abs(e.getX()-this.rectangle.getOrigin().getX()) == Math.abs(e.getY()-this.rectangle.getOrigin().getY()))){
			try {
				int xCheck = (int)(e.getX() - this.rectangle.getOrigin().getX());
				int yCheck = (int)(e.getY() - this.rectangle.getOrigin().getY());
				Robot robot = new Robot();
				//Check if mouse is moving upper left or lower right.
				if (((xCheck>0) && (yCheck>0)) || ((xCheck<0) && (yCheck<0)))
					robot.mouseMove((int)this.view.getPaintPanel().getLocationOnScreen().getX()+e.getX(),
						(int)(this.view.getPaintPanel().getLocationOnScreen().getY()+e.getX()-
							this.rectangle.getOrigin().getX()+this.rectangle.getOrigin().getY()));
				//Check if mouse is moving upper right or lower left.
				else 
					robot.mouseMove((int)this.view.getPaintPanel().getLocationOnScreen().getX()+e.getX(),
						(int)(this.view.getPaintPanel().getLocationOnScreen().getY()-e.getX()+
							this.rectangle.getOrigin().getX()+this.rectangle.getOrigin().getY()));
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
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
		this.model.addCommand((DrawingCommand) this.rectangle);
	}
	

	@Override
	/**
	 * Perform an action when the mouse is pressed. Instantiate the square (rectangle) and set values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Point corner = new Point(e.getX(), e.getY());
		Color color = this.view.getColorChooserPanel().getColor();
		// Gets the current value for shape thickness and fill.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		Boolean fill = this.view.getFillButton().getFill();
		this.rectangle = new Rectangle(corner, 0, 0, corner, color, fill, currentThickness);
		// Sets fill
	}
	@Override
	/**
	 * Perform an action when the mouse is released.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		if(this.rectangle!=null)
			this.rectangle=null;
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
}
