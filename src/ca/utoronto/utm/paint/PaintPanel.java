package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * A PaintPanel is a JPanel and Observer that has shapes drawn on to it. A PaintPanel has an associated model and view,
 * and a fill and color state for the shapes to be drawn.
 */
class PaintPanel extends JPanel implements Observer  {

	private PaintModel model; // Shapes are stored in here
	private View view; // Access to parent or other components of the view.

	/**
	 * Construct a new PaintPanel.
	 * @param model The associated model.
	 * @param view The associated view.
	 */
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(750,300));
		this.model = model;
		this.model.addObserver(this);
		this.view=view;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
        this.model.execute(g2d);
		g2d.dispose();
	}

	@Override
	/**
	 * Update the PaintPanel.
	 */
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 * Set the strategy for shape drawing given by the ShapeChooserPanel
	 * Changes active mouse listeners to draw user selected shape.
	 */
	public void setStrategy(ShapeManipulatorStrategy strategy){
		this.addMouseListener(strategy);
		this.addMouseMotionListener(strategy);
	}
	
	
	/**
	 * Remove all of the listeners from the PaintPanel.
	 */
	public void removeListeners(){
		for (MouseListener m: this.getMouseListeners()){
			this.removeMouseListener(m);
		}
		for (MouseMotionListener m: this.getMouseMotionListeners()){
			this.removeMouseMotionListener(m);
		}
	}

}
	
