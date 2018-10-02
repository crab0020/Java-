package mapmaker.map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import mapmaker.map.shapes.PolyShapeSkeleton;
import mapmaker.map.ToolState.Tools;

public class MapAreaSkeleton extends Pane{

	/**
	 * instead of calling getChildren every time you can call directly the reference of it which is initialized in constructor
	 */
	private ObservableList< Node> children;
	/**
	 * active shape that is currently being manipulated
	 */
	private PolyShapeSkeleton activeShape;
	/**
	 * last location of the mouse
	 */
	private double startX, startY;
	/**
	 * Reference to ToolSate so you don't have to call ToolSate.getState() every time.
	 */
	private ToolState tool;

	public MapAreaSkeleton(){
		super();
		tool = ToolState.state();
		children = this.getChildren();
		registerMouseEvents();
	}

	private void registerMouseEvents(){
		addEventHandler( MouseEvent.MOUSE_PRESSED, this::pressClick);
		addEventHandler( MouseEvent.MOUSE_RELEASED, this::releaseClick);
		addEventHandler( MouseEvent.MOUSE_DRAGGED, this::dragClick);
	}

	private void pressClick( MouseEvent e){
		e.consume();
		startX = e.getX();
		startY = e.getY();
		switch( activeTool()){
			case Door:
			case Move:
			case Path:
			case Selection:
			case Erase:
			case Room:
				//create new shape with number of given 
				//add fill, stroke and strokeWidth as needed
				//finally add active shape to children of this class
				break;
			default:
				throw new UnsupportedOperationException( "Cursor for Tool \"" + activeTool().name() + "\" is not implemneted");
		}
	}

	private void dragClick( MouseEvent e){
		e.consume();
		switch( activeTool()){
			case Door:
			case Path:
			case Erase:
			case Selection:
			case Move:
				//start only needs to be updated for move , what we discussed in class we with out the need of PolyShape
				startX = e.getX();
				startY = e.getY();
				break;
			case Room:
				//redraw the active shape if it is not null
				break;
			default:
				throw new UnsupportedOperationException( "Drag for Tool \"" + activeTool().name() + "\" is not implemneted");
		}
	}

	private void releaseClick( MouseEvent e){
		e.consume();
		switch( activeTool()){
			case Door:
			case Move:
			case Path:
			case Selection:
			case Erase:
			case Room:
				break;
			default:
				throw new UnsupportedOperationException( "Release for Tool \"" + activeTool().name() + "\" is not implemneted");
		}
		activeShape = null;
	}
	
	private double distance ( double x1, double y1, double x2, double y2){
	    return Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
	}

	private Tools activeTool(){
		return tool.tool();
	}
}
