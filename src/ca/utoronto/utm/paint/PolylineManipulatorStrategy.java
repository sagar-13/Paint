package ca.utoronto.utm.paint;


import java.awt.Color;

import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * drawPolyline is a ShapeManipulatorStrategy that performs the necessary actions when the mouse is moved
 * in order to draw a polyline. PolylineStrategy has a squiggle, an associated model, and an associated view. drawPolyline 
 * can perform actions in response to mouse events.
 */
public class PolylineManipulatorStrategy implements ShapeManipulatorStrategy {

	private Squiggle squiggle; //The squiggle to be drawn (polyline is squiggle where points are specified)
	private PaintModel model; //The associated model.
	private View view; //The associated view.

	/**
	 * Create a PolylineStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public PolylineManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view = view;
	}

	@Override
	/**
	 * Performs an action when mouse is pressed. Instantiates the polyline (squiggle) and sets values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Color color = this.view.getColorChooserPanel().getColor();
		
		// Gets the current value for shape thickness.	
				int currentThickness = this.view.getThicknessSlider().getThickness();
		if (squiggle == null){
			squiggle = new Squiggle(color, currentThickness, false);
			squiggle.addPoint(new Point(e.getX(), e.getY()));
		}
		squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.removeCommand(squiggle);
		this.model.addCommand(squiggle);
		}

	@Override
	/**
	 * Performs an action when the mouse is released. Distinguishes a vertex of the polyline.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		//Double click ends the polyline.
		if (e.getClickCount() == 2){
			this.model.getCommands().remove(squiggle);
			this.model.addCommand(squiggle);
			squiggle = null;
		}
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
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	
	
}
	
	
