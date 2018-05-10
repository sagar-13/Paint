package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;


/**
 * A ThicknessSlider is a JSlider and ChangeListener that updates
 * its transparency attribute. The shapes drawn will have their transparency dependent on this value.
 */
public class ThicknessSlider extends JSlider implements ChangeListener {
	private View view; // So we can talk to our parent or other components of the view
	private int thickness; //current value of the slider 
	final static int MIN_THICKNESS = 0;
	final static int MAX_THICKNESS = 20;
	final static int initial_THICKNESS = 5;    

	
	/**
	 * Construct a new ThicknessPanel.
	 * @param view The view the ThicknessPanel is connected to.
	 */
	public ThicknessSlider(View view) {	
		super(JSlider.HORIZONTAL,
		          MIN_THICKNESS, MAX_THICKNESS, initial_THICKNESS);

		//Turn on labels at major tick marks.
		this.setMajorTickSpacing(5);
		this.setMinorTickSpacing(1);
		this.setPaintTicks(true);
		this.setPaintLabels(true);
		this.addChangeListener(this);
		thickness = initial_THICKNESS;
		
		setPreferredSize(new Dimension(112, 50));
		
	}
	/**
	 * Returns the current value of the JSlider for thickness.
	 * @return this.stroke
	 */
	public int getThickness(){
		return this.thickness;
		
	}
	
	@Override
	/**
	 * Change the thickness of the shapes to be drawn on the PaintPanel.
	 */
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		// Assign ColorChooserPanel's current color a transparency.
        if (!source.getValueIsAdjusting()) {
            this.thickness = (int)source.getValue();
            
        }    
    }
	
	public void reset(){
		setValue(initial_THICKNESS);
		
	}
}
		

