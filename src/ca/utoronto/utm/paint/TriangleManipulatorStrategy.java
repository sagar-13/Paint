package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * TriangleStrategy is a MouseListener and MouseMotionListener that performs 
 * the necessary actions when the mouse is moved in order to draw a Triangle.
 * TriangleStrategy has a Triangle, an associated model, and an associated 
 * view. TriangleStrategy can perform actions in response to mouse events.
 */
public class TriangleManipulatorStrategy implements ShapeManipulatorStrategy {

	private Triangle Triangle; //The Triangle to be drawn.
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	private int numOfSetPoints = 0; // The number of confirmed triangle points.
	
	/**
	 * Create a TriangleStrategy MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public TriangleManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view=view;
	}
	
	/**
	 * Sets the new corner based on the x and y values of the mouse.
	 * @param mouseX
	 * @param mouseY
	 */
	private void setTriangleCorners(int mouseX, int mouseY) {
		if (numOfSetPoints == 1) {
			this.Triangle.setCorner(1, new Point(mouseX, mouseY));
			this.Triangle.setCorner(2, new Point(mouseX, mouseY));
		} else if (numOfSetPoints == 2) {
			this.Triangle.setCorner(2, new Point(mouseX, mouseY));
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
		
	@Override
	/**
	 * Performs an action when the mouse is pressed. Instantiates Triangle and sets values.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (numOfSetPoints == 0) {
			Point firstCorner = new Point(mouseX, mouseY);
			Color color = this.view.getColorChooserPanel().getColor();
			// Gets the current value of the JSlider for shape thickness	
			int currentThickness = this.view.getThicknessSlider().getThickness();
			Boolean fill = this.view.getFillButton().getFill();
			this.Triangle = new Triangle(firstCorner, color, fill, currentThickness);
			// Sets whether the shape is to be filled or not
		} else {
			this.model.getCommands().remove(this.Triangle);
			setTriangleCorners(mouseX, mouseY);
		}
		this.model.addCommand(this.Triangle);
		numOfSetPoints++;
		if(this.Triangle!=null && numOfSetPoints == 3){
			numOfSetPoints = 0;
			this.Triangle=null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
	/**
	 * Performs an action when the mouse is moved. Responsible for triangle drawing feedback.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (numOfSetPoints != 0) {
			this.model.getCommands().remove(this.Triangle);
			int mouseX = e.getX();
			int mouseY = e.getY();
			setTriangleCorners(mouseX, mouseY);
			this.model.addCommand(this.Triangle);
		}
	}
}
