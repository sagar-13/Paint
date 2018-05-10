package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * EllipseStrategy is a MouseListener and MouseMotionListener that performs the
 * necessary actions when the mouse is moved in order to draw a Ellipse. 
 * EllipseStrategy has a Ellipse, an associated model, and an associated view. 
 * EllipseStrategy can perform actions in response to mouse events.
 */
public class EllipseManipulatorStrategy implements ShapeManipulatorStrategy {
	private Ellipse ellipse; //The Ellipse to be drawn 
	private PaintModel model; //The associated model.
	private View view; //The associated view.
	
	/**
	 * Create a DrawEllipse MouseListener/MouseMotionListener.
	 * @param paintModel The associated model.
	 * @param view The associated view.
	 */
	public EllipseManipulatorStrategy(PaintModel paintModel, View view) {
		this.model=paintModel;
		this.view=view;
	}

	@Override
	/**
	 * Performs an action when the mouse is pressed. Instantiates a new Ellipse.
	 */
	public void mousePressed(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		// Initiates Ellipse
		Point edge = new Point(e.getX(), e.getY());
		Color color = this.view.getColorChooserPanel().getColor();
		
		// Gets the current value for shape thickness and fill.	
		int currentThickness = this.view.getThicknessSlider().getThickness();
		Boolean fill = this.view.getFillButton().getFill();
		this.ellipse = new Ellipse(edge,0,0,edge, color, fill, currentThickness);
		
	}

	@Override
	/**
	 * Performs an action when the mouse is released. Sets the drawn ellipse back to null.
	 */
	public void mouseReleased(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		if(this.ellipse!=null){
			this.ellipse=null;}	
	}

	@Override
	/**
	 * Performs an action when mouse is dragged. Provides feedback on ellipse drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!(SwingUtilities.isLeftMouseButton(e)))
			return;
		this.model.getCommands().remove(this.ellipse);
		int mouseX = e.getX();
		int mouseY = e.getY();
		int oppositeEdgeY = (int) Math.max(mouseY, this.ellipse.getOrigin().getY());
		int oppositeEdgeX = (int) Math.max(mouseX, this.ellipse.getOrigin().getX());
		//Min value of X and Y should be where the drawing is started from since drawing proceeds from
		//upper left (smaller values of X and Y) to bottom right.
		int startingDrawEdgeY = (int) Math.min(mouseY, this.ellipse.getOrigin().getY());
		int startingDrawEdgeX = (int) Math.min(mouseX, this.ellipse.getOrigin().getX());
		//Height and width will therefore be the larger x and y minus the smaller x and y.
		this.ellipse.setHeight(oppositeEdgeY-startingDrawEdgeY);
		this.ellipse.setWidth(oppositeEdgeX-startingDrawEdgeX);
		this.ellipse.setEdge(new Point(startingDrawEdgeX,startingDrawEdgeY));
		this.model.addCommand(this.ellipse);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
