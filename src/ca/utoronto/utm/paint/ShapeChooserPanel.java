package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * A ShapeChooserPanel is a JPanel an ActionListener. A ShapeChooserPanel has an associated view. A ShapeChooserPanel
 * can perform actions based on button presses.
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	StrategyFactory shapeMaker; //Factory for all the strategies.
	ButtonGroup ShapeButtonGroup; //The group on buttons on the Panel.
	
	/**
	 * Construct a new ShapeChooserPanel.
	 * @param view The associated view.
	 */
	public ShapeChooserPanel(View view) {	
		this.view=view;
		shapeMaker = new StrategyFactory();
		String[] buttonActionCommands = {"Squiggle", "Spray", "Line", "Circle", "Square", "Eraser", "Triangle", "Polyline", "Ellipse", "Rectangle"};
		//Icons obtained from http://www.iconarchive.com/.
		ImageIcon[] buttonIcons = {new ImageIcon(this.getClass().getResource("/modeIcons/Squiggle.png")),
				   				   new ImageIcon(this.getClass().getResource("/modeIcons/Spray.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Line.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Circle.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Square.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Eraser.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Triangle.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Polyline.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Ellipse.png")),
								   new ImageIcon(this.getClass().getResource("/modeIcons/Rectangle.png"))};
		int buttonActionCommandsIndex = 0;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		ShapeButtonGroup = new ButtonGroup();
		for (ImageIcon icon : buttonIcons) {
			JToggleButton button = new JToggleButton(icon);
			button.setActionCommand(buttonActionCommands[buttonActionCommandsIndex]);
			buttonActionCommandsIndex++;
			button.setPreferredSize(new Dimension(56, 40));
			ShapeButtonGroup.add(button);
			this.add(button, c);	
			c.gridy ++;
			
			if (c.gridy == 5) {
				c.gridy = 0; 
				c.gridx = 1;
			}
				
			button.addActionListener(this);
		}

	}
	
	@Override
	/**
	 * Perform an action when a button is pressed. Sets the mouselistener to be used for the PaintPanel.
	 */
	public void actionPerformed(ActionEvent e) {
		// Makes sure previous listeners arn't active
		this.view.getPaintPanel().removeListeners();
		// Make ManipulatorStrategy using ShapeFactory

		ShapeManipulatorStrategy ShapeStrategy = 
				shapeMaker.makeShapeStrategy(e.getActionCommand(), this.view);
		
		// Sets the Strategy for shape drawing. See setStrategy in PaintPanel. 
		this.view.getPaintPanel().setStrategy(ShapeStrategy);
    
		System.out.println(e.getActionCommand());
	}
	
	public void deselectAll(){
		ShapeButtonGroup.clearSelection();
	}
	
	public void reset(){
		for (Component c: this.getComponents()){
			if (((JToggleButton) c).getActionCommand() == "Squiggle"){
				((JToggleButton) c).setSelected(true);
				actionPerformed(new ActionEvent(c, ActionEvent.ACTION_PERFORMED,"Squiggle"));
			}
		}
	}
	
}
