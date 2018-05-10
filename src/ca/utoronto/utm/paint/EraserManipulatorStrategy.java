package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * EraserStrategy is a MouseListener and MouseMotionListener that performs 
 * the necessary actions when the mouse is moved in order to draw a eraser.
 * EraserStrategy has a eraser, an associated model, and an associated 
 * view. EraserStrategy can perform actions in response to mouse events.
 */
public class EraserManipulatorStrategy implements ShapeManipulatorStrategy {
	private Squiggle squiggle; //The squiggle to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a SquiggleStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public EraserManipulatorStrategy(PaintModel paintModel, View view) {
		this.view = view;
		this.model=paintModel;
	}

	@Override
	/**
	 * Perform an action when the mouse is dragged. Erases where the mouse is dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		this.model.getCommands().remove(squiggle);
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.addCommand(squiggle);
	}
	

	@Override
	/**
	 * Perform an action when the mouse is pressed. Instantiate a squiggle (eraser is a type of squiggle).
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Color color = this.view.getPaintPanel().getBackground();

		// Gets the current value for shape thickness
		int currentThickness = this.view.getThicknessSlider().getThickness();
		
		squiggle = new Squiggle(color, currentThickness, true);
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.addCommand(squiggle);
	}

	@Override
	/**
	 * Perform an action when the mouse is released.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		if(this.squiggle!=null)
			this.squiggle=null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}


}
