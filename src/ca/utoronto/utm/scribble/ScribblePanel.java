package ca.utoronto.utm.scribble;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ScribblePanel extends JPanel implements MouseMotionListener {
	int i=0;
	ArrayList<Pair> drawingPoints;
	public ScribblePanel(){
		// Method of JComponent
		setBackground(Color.blue);
		this.addMouseMotionListener(this);
		drawingPoints=new ArrayList<Pair>();
	}

	// Use g to draw on the JPanel, lookup java.awt.Graphics in
	// the javadoc to see more of what this can do for you!!
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(Color.white);
        g2d.drawString ("i="+i, 50, 75);
		i=i+1;

		// Draw bubbles
		for(Pair p:this.drawingPoints){
			g2d.drawOval(p.getX(), p.getY(), 4, 4);
		}

		// issue 53: change to line drawing
		for(int i=0;i<this.drawingPoints.size()-1; i++){
			Pair p1=this.drawingPoints.get(i);
			Pair p2=this.drawingPoints.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		g2d.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("Dragged " +arg0.getX()+" "+arg0.getY());
		this.drawingPoints.add(new Pair(arg0.getX(), arg0.getY()));
		this.repaint(); // schedule a call to paint (and so paintComponent)
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		System.out.println("Moved " +arg0.getX()+" "+arg0.getY());
	}
}
