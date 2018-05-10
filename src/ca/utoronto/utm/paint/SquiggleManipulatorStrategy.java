package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * SquiggleStrategy is a MouseListener and MouseMotionListener that performs 
 * the necessary actions when the mouse is moved in order to draw a squiggle.
 * SquiggleStrategy has a squiggle, an associated model, and an associated 
 * view. SquiggleStrategy can perform actions in response to mouse events.
 */
public class SquiggleManipulatorStrategy implements ShapeManipulatorStrategy {
	private Squiggle squiggle; //The squiggle to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a SquiggleStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public SquiggleManipulatorStrategy(PaintModel paintModel, View view) {
		this.view = view;
		this.model=paintModel;
	}

	@Override
	/**
	 * Perform an action when the mouse is dragged. Draws where the mouse is dragged.
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
	 * Perform an action when the mouse is pressed. Instantiate a squiggle and set values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Color color = this.view.getColorChooserPanel().getColor();
		// Gets the current value for shape thickness and fill.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		squiggle = new Squiggle(color, currentThickness, false);
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
