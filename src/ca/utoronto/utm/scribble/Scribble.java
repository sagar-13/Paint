package ca.utoronto.utm.scribble;
import javax.swing.*;
class Scribble {
	public static void createAndShowGUI() {
		JFrame jf=new JFrame("Graphics Panel");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(new ScribblePanel());
		jf.setSize(200,200);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
