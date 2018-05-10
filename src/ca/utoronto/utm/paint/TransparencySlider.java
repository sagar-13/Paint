package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.Hashtable;

/**
 * A TransparencySlider is a JSlider and ChangeListener that updates
 * ColourChooserPanel's color attribute's transparency using a 
 * JSlider
 */
public class TransparencySlider extends JSlider implements ChangeListener {
	private View view; // So we can talk to our parent or other components of the view
	private int transparency; //current value of the slider 
	final static int MAX_TRANSPARENCY = 0; //The max transparency.
	final static int MIN_TRANSPARENCY = 255; //The minimum transparency.
	final static int INITIAL_TRANSPARENCY = 255; //The initial transparency.

	
	/**
	 * Construct a new TransparencyPanel.
	 * @param view The view the TransparencyPanel is connected to.
	 */
	public TransparencySlider(View view) {	
		super(JSlider.HORIZONTAL,
		          MAX_TRANSPARENCY, MIN_TRANSPARENCY, INITIAL_TRANSPARENCY);
		this.view = view;

		//Turn on labels at major tick marks.
		setMajorTickSpacing(50);
		setMinorTickSpacing(10);
		setPaintTicks(true);
		setPaintLabels(true);
		addChangeListener(this);
		
		// Add Labels, max=0, min=255 (transparency values are backwards)
		Hashtable labels = new Hashtable();
		labels.put(new Integer(0), new JLabel("Max"));
		labels.put( new Integer(255), new JLabel("Min"));
		setLabelTable(labels);
		
		setPreferredSize(new Dimension(112, 50));
	}

	@Override
	/**
	 * Change the transparency of the shapes to be drawn on the PaintPanel.
	 */
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		// Assign ColorChooserPanel's current color a transparency.
        if (!source.getValueIsAdjusting()) {
            this.transparency = (int)source.getValue();
            Color current = this.view.getColorChooserPanel().getColor();
            this.view.getColorChooserPanel().setColor(new 
            		Color(current.getRed(), current.getGreen(), 
            				current.getBlue(), transparency));
        }    
    }
	
	public void reset(){
		setValue(255);
	}
}
		

