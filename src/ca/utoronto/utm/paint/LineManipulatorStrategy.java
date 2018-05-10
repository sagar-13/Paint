package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * DrawLine is a MouseListener and MouseMotionListener that performs the necessary actions when the mouse is moved
 * in order to draw a line. DrawLine has a squiggle, an associated model, and an associated view. drawLine 
 * can perform actions in response to mouse events.
 */
public class LineManipulatorStrategy implements ShapeManipulatorStrategy {
	private Squiggle squiggle; //The squiggle to be drawn (line is squiggle where points are specified)
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a drawLine MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public LineManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view = view;
	}
	
	@Override
	/**
	 * Action that occurs when the mouse is pressed. Instantiates a new squiggle (line is made up of squiggles).
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Color color = this.view.getColorChooserPanel().getColor();
		
		// Gets the current value for shape thickness.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		
		squiggle = new Squiggle(color, currentThickness, false);
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.addCommand(squiggle);
		}

	@Override
	/**
	 * Performs an action when the mouse is dragged. Provides feedback on line drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		squiggle.removePoint();
		this.model.getCommands().remove(squiggle);
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.addCommand(squiggle);
	}
	
	@Override
	/**
	 * Performs an action when the mouse is released. Sets the drawn line back to null.
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
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
}
