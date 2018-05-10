package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A ColorChooserPanel is a JPanel and ActionListener that has a view and a color. A ColorChooserPanel can respond to
 * actions and in doing so changes the color of the shapes to be drawn.
 */
public class ColorChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private Color color = Color.BLACK; //The selected color. Default Black.
	private JButton colorButton; //The button to access more colours.
	private JButton oldButton; // Keeps track of current selection.
	private JButton initialButton; //The initial black button. Aids in implementation of "new".
	
	/**
	 * Construct a new ColorChooserPanel.
	 * @param view The view the ColorChooserPanel is connected to.
	 */
	public ColorChooserPanel(View view) {	
		this.view=view;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		
		initialButton = new JButton();
		initialButton.setPreferredSize(new Dimension(28, 28));
		initialButton.setBackground(Color.BLACK);
		initialButton.addActionListener(this);
		initialButton.setActionCommand("#"+Integer.toHexString(Color.BLACK.getRGB()).substring(2));
		this.add(initialButton,c);
		
		c.gridx++;
		
		Color[] colors = {Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, 
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};;
		for (Color col : colors) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(28, 28));
			button.setBackground(col);
			
			//Converting from RGB to HEX to easily decode later
			//referenced from http://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal
			button.setActionCommand("#"+Integer.toHexString(col.getRGB()).substring(2));
			this.add(button, c);
			
			c.gridx ++;
			if (c.gridx == 4) {
				c.gridy ++;
				c.gridx = 0;
			}
			button.addActionListener(this);
		}
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		colorButton = new JButton("More Colours");
		colorButton.setPreferredSize(new Dimension(112, 28));
		this.add(colorButton, c);
		colorButton.addActionListener(this);
	}
	
	/**
	 * Return the selected color.
	 * @return The selected color.
	 */
	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Set the selected color.
	 * @param color The new color to be selected.
	 */
	public void setColor(Color color) {
		this.color = color;
		
	}
	
	@Override
	/**
	 * Change the color of the shapes to be drawn on the PaintPanel.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == colorButton) {
			this.color = JColorChooser.showDialog(null, "Change Color", this.color);
			if (oldButton.getBorder() != null){
				oldButton.setBorder(null);
			}
			
			if (color == null) {
				this.color = Color.BLACK;
				}	
		}
		else {
			this.color = Color.decode(e.getActionCommand());
			((JButton) e.getSource()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
					BorderFactory.createLoweredBevelBorder()));
			if (oldButton != null)
				oldButton.setBorder(null);
			oldButton = (JButton) e.getSource();
			}
		
		
	}
	
	public void reset(){
		actionPerformed(new ActionEvent(initialButton,ActionEvent.ACTION_PERFORMED,
				"#"+Integer.toHexString(Color.BLACK.getRGB()).substring(2)));
		
	}


	
}
