package ca.utoronto.utm.paint;


import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * SprayStrategy is a MouseListener and MouseMotionListener that performs 
 * the necessary actions when the mouse is moved in order to draw a Spray.
 * SprayStrategy has a Spray, an associated model, and an associated 
 * view. SprayStrategy can perform actions in response to mouse events.
 */
public class SprayManipulatorStrategy implements ShapeManipulatorStrategy {
	private Spray spray; //The Spray to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a SprayStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public SprayManipulatorStrategy(PaintModel paintModel, View view) {
		this.view = view;
		this.model=paintModel;
	}
	
	/**
	 * Creates the random spray of points to be added to the spray object.
	 * @param e the MouseEvent in progress which gives coordinates for point locations
	 */
	public void generateSpray(MouseEvent e) {
		// Gets the current value for thickness
		int currentThickness = this.view.getThicknessSlider().getThickness();
		// uses two times the thickness for the amount of points plus 1 for when thickness is set to 0.
		int numOfPoints = currentThickness * 2 + 1;
		for (int pointCount = 0; pointCount < numOfPoints; pointCount++){
			// creates a randomDistance within the currentThickness for point position.
			int randomDistance = (int)(Math.random() * currentThickness);
	        // create random angle in a circle to be used for point position.
	        double randomAngle = Math.random() * 2 * Math.PI;
	        // creates the x and y coordinates of the point.
	        int pointX = (int)(e.getX() + randomDistance * Math.cos(randomAngle));
	        int pointY = (int)(e.getY() + randomDistance * Math.sin(randomAngle));
	        spray.addPoint(new Point(pointX, pointY));
		   }
	}

	@Override
	/**
	 * Perform an action when the mouse is dragged. Sprays where the mouse is dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
	   this.model.getCommands().remove(spray);
	   this.model.removeCommand(spray);
	   generateSpray(e);
	   this.model.addCommand(spray);
	}
	
	@Override
	/**
	 * Perform an action when the mouse is pressed. Instantiate a Spray and set values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		Color color = this.view.getColorChooserPanel().getColor();
		spray = new Spray(color, 1);
		generateSpray(e);
	    this.model.addCommand(spray);
	}

	@Override
	/**
	 * Perform an action when the mouse is released. Sets the drawn spray to null.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		if(this.spray!=null)
			this.spray=null;
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
