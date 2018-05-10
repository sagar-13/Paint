package ca.utoronto.utm.paint;

import java.util.ArrayList;

/**
 * Command Pattern: One method to make any ShapeManipulatorStrategy.
 */
public class StrategyFactory {
	
		/**
		 * Returns a ShapeManipulatorStrategy based on what the user specifies in the ShapeChooserPanel.
		 * @param type The type of ShapeManipulatorStrategy to be returned.
		 * @param view  used for strategy making (get Shape Modifiers)
		 * @return ShapeManipulatorStrategy  The desired Strategy.
		 */
		public ShapeManipulatorStrategy makeShapeStrategy(String type, View view) {
			switch (type){
				case "Circle": return new CircleManipulatorStrategy(view.getPaintModel(), view);
				case "Rectangle": return new RectangleManipulatorStrategy(view.getPaintModel(), view);
				case "Square": return new SquareManipulatorStrategy(view.getPaintModel(), view);
				case "Triangle": return new TriangleManipulatorStrategy(view.getPaintModel(), view);
				case "Squiggle":return new SquiggleManipulatorStrategy(view.getPaintModel(), view);
				case "Spray":return new SprayManipulatorStrategy(view.getPaintModel(), view);
				case "Polyline":return new PolylineManipulatorStrategy(view.getPaintModel(), view);
				case "Line":return new LineManipulatorStrategy(view.getPaintModel(), view);
				case "Ellipse":return new EllipseManipulatorStrategy(view.getPaintModel(), view);
				case "Eraser":return new EraserManipulatorStrategy(view.getPaintModel(), view);
			}
			return null;
		}
		
		/**
		 * Returns a ShapeManipulatorStrategy based on what the user specifies in the View.
		 * @param type The type of ShapeManipulatorStrategy to be returned.
		 * @param view The view associated with the strategies.
		 * @param toPaste The list of shapes that were copied.
		 * @param copyOrigin The origin of the rectangle where shapes were copied from.
		 * @return The desired strategy.
		 */
		public ShapeManipulatorStrategy makeOtherStrategy(String type, View view, ArrayList<DrawingCommand> toPaste, 
				Point copyOrigin){
			switch(type){
				case "Copy":return new CopyManipulatorStrategy(view.getPaintModel(), view, false);
				case "Cut":return new CopyManipulatorStrategy(view.getPaintModel(), view, true);
				case "Paste":return new PasteManipulatorStrategy(view.getPaintModel(), toPaste, copyOrigin);
			}
			return null;
		}
	}


