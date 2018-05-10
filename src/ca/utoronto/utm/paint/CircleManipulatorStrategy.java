package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * CircleStrategy is a ShapeManipulatorStrategy that performs the necessary actions when the mouse is moved
 * in order to draw a circle. CircleStrategy has a circle, an associated model, and an associated view. drawCircle can 
 * perform actions in response to mouse events.
 */
public class CircleManipulatorStrategy implements ShapeManipulatorStrategy {
	private Circle circle; //The circle to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a CircleStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public CircleManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view = view;
		;
	}
	
	@Override
	/**
	 * Performs an action when the mouse is pressed. Instantiates the circle 
	 * and sets values.
	 */
	public void mousePressed(MouseEvent e) {
		//Prevents errors than come with having multiple buttons pressed at the same time.
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		// Instantiates new circle
		Color color = this.view.getColorChooserPanel().getColor();
		
		// Gets the current value for shape thickness and fill.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		Boolean fill = this.view.getFillButton().getFill();
		Point centre = new Point(e.getX(), e.getY());
		this.circle=new Circle(centre, 0,color, fill,currentThickness);
		}	
		
	@Override
	/**
	 * Performs an action when mouse is released. 
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//Resets this class's rectangle for future drawings.
		if(this.circle!=null)
			this.circle=null;
	}
			
	@Override
	/**
	 * Performs an action when mouse is dragged. Provides feedback on circle drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//Remove object so only newest version of object is in shapes list.
		this.model.getCommands().remove(this.circle);
		int radius = (int) e.getPoint().distance(this.circle.getCentre().getX(),this.circle.getCentre().getY());
		this.circle.setRadius(radius);
		this.model.addCommand(this.circle);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
