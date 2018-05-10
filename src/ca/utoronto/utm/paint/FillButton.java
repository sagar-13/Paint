package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * A FillPanel is a JPanel and ActionListener that has an associated view. A FillPanel can respond to actions and in
 * doing so changes if shapes to be drawn are filled or not filled.
 */
class FillButton extends JToggleButton implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view.
	private boolean fill; //True if the shapes are to be filled, false otherwise.
	
	/**
	 * Construct a new FillPanel.
	 * @param view The view the FillPanel is connected to.
	 */
	public FillButton(View view) {	
		super("Fill OFF");
		this.view=view;
		setPreferredSize(new Dimension(112, 30));
		addActionListener(this);
		}
	
	
	@Override
	/**
	 * Change if the shape to be drawn on the PaintPanel is to be filled or not.
	 */
	public void actionPerformed(ActionEvent e) {
		if (((JToggleButton) e.getSource()).isSelected()){
			((JToggleButton) e.getSource()).setText("Fill ON");
			this.fill = true;
		}
		else {
			((JToggleButton) e.getSource()).setText("Fill OFF");
			this.fill = false;
		}
	}
	
	/**
	 * Get whether or not the user wants to fill or not.
	 * @return boolean
	 */
	public boolean getFill(){
		return this.fill;
	}
	
	public void reset(){
		this.fill = false;
		setText("Fill OFF");
		setSelected(false);
	}
	
}
