package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	// The components that make this up.
	private PaintModel model; //Holds all of the shapes that are draw.
	private PaintPanel paintPanel; //The panel on which everything is drawn.
	private ShapeChooserPanel shapeChooserPanel; //Used to choose a shape.
	private FillButton fillButton; //Used to choose fill setting.
	private ColorChooserPanel colorChooserPanel; //Used to choose color.
	private CopyManipulatorStrategy cml; //Used when something is copied. Keeps track of what was copied.
	private TransparencySlider TransparencySlider; //Used to change transparency.
	private ThicknessSlider ThicknessSlider; //Used to change stroke thickness.
	
	/**
	 * Creates the view, adds all the panels to see the final product
	 * @param model The model that the view talks to
	 */
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		
		Container c=this.getContentPane();
		c.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		this.shapeChooserPanel = new ShapeChooserPanel(this);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		c.add(this.shapeChooserPanel, gbc);
	
		this.colorChooserPanel = new ColorChooserPanel(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		c.add(this.colorChooserPanel, gbc);
			
		JLabel ThickLabel = new JLabel("Thickness");
		gbc.gridx = 0;
		gbc.gridy = 2;
		c.add(ThickLabel, gbc);
		
		this.ThicknessSlider = new ThicknessSlider(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		c.add(this.ThicknessSlider, gbc);	
		
		JLabel TransLabel = new JLabel("Transparency");
		gbc.gridx = 0;
		gbc.gridy = 4;
		c.add(TransLabel, gbc);
	
		this.TransparencySlider = new TransparencySlider(this);
		gbc.gridx = 0;
		gbc.gridy = 5;
		c.add(this.TransparencySlider, gbc);	
		
		this.fillButton = new FillButton(this);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.NORTH;
		c.add(this.fillButton, gbc);

		this.model=model;
		
		this.paintPanel = new PaintPanel(model, this);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 7;
		gbc.gridx = 1;
		gbc.gridy = 0;
		c.add(this.paintPanel, gbc);
	
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Returns the PaintPanel
	 * @return The PaintPanel
	 */
	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	/**
	 * Returns the ShapeChooserPanel
	 * @return The ShapeChooserPanel
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	/**
	 * Returns the ColorChooserPanel
	 * @return The ColorChooserPanel
	 */
	public ColorChooserPanel getColorChooserPanel() {
		return colorChooserPanel;
	}
	
	public FillButton getFillButton(){
		return fillButton;
	}
	
	/**
	 * Return the PaintModel.
	 * @return The PaintModel.
	 */
	public PaintModel getPaintModel() {
		return this.model;
	}
	
	/**
	 * Return the TransparencyPanel.
	 * @return The TransparencyPanel.
	 */
	public TransparencySlider getTransparencySlider(){
		return this.TransparencySlider;
	}

	/**
	 * Return the ThicknessPanel.
	 * @return The ThicknessPanel.
	 */
	public ThicknessSlider getThicknessSlider(){
		return this.ThicknessSlider;
	}
	
	
	/**
	 * Creates the menu bar and adds actionListeners to the items
	 * @return The menu bar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		menuItem = new JMenuItem("Change Background");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		

		menu.addSeparator();// -------------

		
		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("New")) {
			this.model.clear();
			this.TransparencySlider.reset();
			this.fillButton.reset();
			this.ThicknessSlider.reset();
			this.colorChooserPanel.reset();
			this.shapeChooserPanel.reset();
		}
		else if (e.getActionCommand().equals("Undo")) {
			this.model.undo();
		}
		
		else if (e.getActionCommand().equals("Redo")) {
			this.model.redo();
		}
		else if (e.getActionCommand() == "Exit"){
			System.exit(0);
		}
		else if (e.getActionCommand().equals("Change Background")) {
			Color bg = JColorChooser.showDialog(null, "Choose background color", Color.white);
			this.getPaintPanel().setBackground(bg);
			this.model.changeEraserColor(bg);
		}
		else {
			this.getPaintPanel().removeListeners();
			this.getShapeChooserPanel().deselectAll();
			StrategyFactory shapeMaker = new StrategyFactory();
			if (e.getActionCommand() != "Paste"){
			// Make ManipulatorStrategy using ShapeFactory
				ShapeManipulatorStrategy ShapeStrategy = 
						shapeMaker.makeOtherStrategy(e.getActionCommand(), this, null, null);
				cml = (CopyManipulatorStrategy) ShapeStrategy;
				// Sets the Strategy for shape drawing. See setStrategy in PaintPanel. 
				this.paintPanel.setStrategy(ShapeStrategy);
			}
			else{
				if (cml == null)
					return;
				ShapeManipulatorStrategy ShapeStrategy = 
						shapeMaker.makeOtherStrategy(e.getActionCommand(), this, cml.getCopyList(),cml.getCopyOrigin());
				this.paintPanel.setStrategy(ShapeStrategy);
			}
		}
		
	}
	
}
