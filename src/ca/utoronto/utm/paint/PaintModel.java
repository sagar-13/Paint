package ca.utoronto.utm.paint;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

/**
 * A PaintModel is an Observable object that has an ArrayList of shapes. 
 * A PaintModel can add shapes to the list and can return this list of shapes.
 */
public class PaintModel extends Observable {
	//The list of shapes to be drawn.
	private ArrayList<DrawingCommand> commands =new ArrayList<DrawingCommand>(); 
	private ArrayList<DrawingCommand> undos = new ArrayList<DrawingCommand>();
	
	/**
	 * Add shapes to the ArrayList for repainting.
	 * @param s The shape to be added.
	 */
	public void addCommand(DrawingCommand d){
		this.commands.add(d);
		this.setChanged();
		this.notifyObservers();
		undos = new ArrayList<DrawingCommand>(); 
	}
	
	public void removeCommand(DrawingCommand d){
		this.commands.remove(d);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Return the list of shapes.
	 * @return The list of shapes.
	 */
	public ArrayList<DrawingCommand> getCommands(){
		return commands;
	}
	
	public void execute(Graphics2D g2d){
		for (DrawingCommand d: commands){
			d.execute(g2d);
		}
			
	}
	
	/**
	 * Clears all the current commands
	 */
	public void clear() {
		if (JOptionPane.showConfirmDialog(
	            null,
	            "Are you sure you want to clear the canvas? All your work and progress will be lost.",
	            "New",
	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			commands.clear();
			this.setChanged();
			this.notifyObservers();
		}
		else {
			return;
		}
	}
	
	/**
	 * Undo the last command
	 */
	public void undo() {
		if (commands.size() == 0) {
			return;
		}
		DrawingCommand command = commands.remove(commands.size() - 1);
		
		if (command instanceof Cut) {
			commands = ((Cut)command).getCommands();
			undos.add(command);
		}
		else if (command instanceof Paste) {
			for(int i = 0; i < ((Paste) command).getNumShapes(); i++)
				undo();
			undos.add(command);
		}
		else
			undos.add(command);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Redo the last undo
	 */
	public void redo() {
		if (undos.size() == 0) {
			return;
		}
	
		DrawingCommand command = undos.remove(undos.size() - 1);	
	
		if (command instanceof Cut) {
			//commands.add(command);
			for(DrawingCommand d: (((Cut) command).getToCopy()))
				commands.remove(d);
		}
		
		else if (command instanceof Paste) {
			for(int i = 0; i < ((Paste) command).getNumShapes(); i++)
				redo();
			commands.add(command);
		}
		else
			commands.add(command);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Changes the color of all eraser squiggles to the designated color
	 * @param color The designated color
	 */
	public void changeEraserColor(Color color) {
		for(DrawingCommand d: commands) {
			if (d instanceof Squiggle) {
				if (((Squiggle) d).getEraser()) {
					((Squiggle) d).setColor(color);
				}
			}
		}
	}

}

