package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * ShapeManipulatorStrategy is a MouseListener and MouseMotion listener that 
 * can respond to mouse events. Classes that implement this interface specify
 * shapes to be drawn. 
 */
public interface ShapeManipulatorStrategy extends MouseListener, MouseMotionListener {
	
	public void mouseClicked(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseReleased(MouseEvent e);
	
	public void mouseEntered(MouseEvent e);
		
	public void mouseExited(MouseEvent e);
	
	public void mouseDragged(MouseEvent e);
	
	public void mouseMoved(MouseEvent e);
	

}
