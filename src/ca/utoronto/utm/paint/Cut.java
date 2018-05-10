package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A cut is a shape that has a transparent point and 2 ArrayLists of DrawingCommands. A cut can get
 * the ArrayList. A cut is used to keep track of what the DrawingCommand stack in the model
 * was like before the cut was made, it is a placeholder in the case the user wants to undo a cut.
 * If the user wishes to redo an undone cut, it has access to what shapes must be cut.
 * It is a shape so it can be placed on the DrawingCommand stack to keep the order of the actions.
 */
public class Cut extends Shape{
	
	private Point p = new Point(0, 0);
	private ArrayList<DrawingCommand> commands;
	private ArrayList<DrawingCommand> toCopy;
	
	/**
	  * Creates a cut object with a transparent point
	  * @param commands The DrawingCommand stack
	  */
	public Cut(ArrayList<DrawingCommand> commands, ArrayList<DrawingCommand> toCopy) {
		super(new Color(0,0,0,0), 1);
		this.commands = commands;
		this.toCopy = toCopy;
	}
	
	 /**
	  * Returns the DrawingCommand stack
	  * @return The DrawingCommand stack
	  */
	public ArrayList<DrawingCommand> getCommands() {
		return commands;
	}
	
	 /**
	  * Returns the DrawingCommand stack of shapes to be cut
	  * @return The DrawingCommand stack of shapes to be cut
	  */
	public ArrayList<DrawingCommand> getToCopy() {
		return toCopy;
	}

	@Override
	 /**
	  * Draws the cut object
	  */
	public void execute(Graphics2D g2d) {
		g2d.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
	}
	
}
