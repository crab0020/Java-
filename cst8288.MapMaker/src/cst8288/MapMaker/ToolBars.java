package cst8288.MapMaker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

public class ToolBars {
	
	static String selectedTool;
	
	public static ToolBar getStatusBar() {
		while(selectedTool == null) {
			if(selectedTool != null) {
			ToolBar statusBar = new ToolBar(new Label("Tool: " /*+ getSelectedTool()*/),
					new Separator());
					return statusBar;}
			else {ToolBar statusBar = new ToolBar(new Label("Tool: "),
					new Separator());
					return statusBar;}
		}
		return getStatusBar();
	} 
	
	/*public static String getSelectedTool() {
		return selectedTool;
	}
	
	public static String setSelectedTool(String toolChoice) {
		selectedTool = toolChoice;
		return selectedTool;
	}*/
	
	static MenuButton createMenuButton(String name, EventHandler<ActionEvent> handler) {
		Label icon = new Label();
		icon.setId(name+"-icon");
		MenuButton menuButton = new MenuButton(name, icon);
		menuButton.setOnAction(handler);
		menuButton.setId(name);
		return menuButton;
	}
		
	public static ToolBar getToolsBar() throws FileNotFoundException {
		FileInputStream input = new FileInputStream("resources/icons/40xp/plans.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        
		ToolBar toolsBar = new ToolBar(createMenuButton("Select", (e) -> {}),
											createMenuButton("Move", (e) -> {}),
											new MenuButton("Room", imageView, Menus.createMenuItem("Line", (e) -> {}),
																		Menus.createMenuItem("Triangle", (e) -> {}),
																			Menus.createMenuItem("Rectangle", (e) -> {}),
																				Menus.createMenuItem("Pentagon", (e) -> {}), 
																					Menus.createMenuItem("Hexagon", (e) -> {})),
										createMenuButton("Path", (e) -> {/*setSelectedTool("Path");*/}),
										createMenuButton("Erase", (e) -> {/*setSelectedTool("Erase");*/}),
										createMenuButton("Door", (e) -> {/*setSelectedTool("Room: Door");*/}));
		toolsBar.setOrientation(Orientation.VERTICAL);
		return toolsBar;
	}
	
	// Polygon manipulation code. Remember to cite jewelsea github. 
	
	/*static Polygon drawRoomTriangle() {
		Polygon roomTriangle = new Polygon();
		
		roomTriangle.getPoints().setAll(
				);
		
		roomTriangle.setStroke(Color.BLACK);
		roomTriangle.setStrokeWidth(4);
		roomTriangle.setStrokeLineCap(StrokeLineCap.ROUND);
		roomTriangle.setFill(Color.GREEN.deriveColor(0, 1.2, 1, 0.6));
		return roomTriangle;
	}
	
	static Polygon drawRoomRectangle() {
		Polygon roomRectangle = new Polygon();
		
		roomRectangle.getPoints().setAll(
				);
		
		roomRectangle.setStroke(Color.BLACK);
		roomRectangle.setStrokeWidth(4);
		roomRectangle.setStrokeLineCap(StrokeLineCap.ROUND);
		roomRectangle.setFill(Color.YELLOW.deriveColor(0, 1.2, 1, 0.6));
		return roomRectangle;
	}
	
	static Polygon drawRoomPentagon() {
		Polygon roomPentagon = new Polygon();
		
		roomPentagon.getPoints().setAll(
				);
		
		roomPentagon.setStroke(Color.BLACK);
		roomPentagon.setStrokeWidth(4);
		roomPentagon.setStrokeLineCap(StrokeLineCap.ROUND);
		roomPentagon.setFill(Color.RED.deriveColor(0, 1.2, 1, 0.6));
		return roomPentagon;
	}
	
	static Polygon drawRoomHexagon() {
		Polygon roomHexagon = new Polygon();
		
		roomHexagon.getPoints().setAll(
				);
		
		roomHexagon.setStroke(Color.BLACK);
		roomHexagon.setStrokeWidth(4);
		roomHexagon.setStrokeLineCap(StrokeLineCap.ROUND);
		roomHexagon.setFill(Color.AZURE.deriveColor(0, 1.2, 1, 0.6));
		return roomHexagon;
	}*/
	
	private ObservableList<Anchor> createControlAnchorsFor(final ObservableList<Double> points){
		ObservableList<Anchor> anchors = FXCollections.observableArrayList();
		
		for (int i = 0; i < points.size(); i += 2) {
			final int idx = i;
			
			DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
			DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));
			
			xProperty.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
					points.set(idx, (double) x); 
				}
			});
			
			yProperty.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
					points.set(idx + 1, (double) y); 
				}
			});
			
			anchors.add(new Anchor(Color.YELLOW, xProperty, yProperty));			
		}
		
		return anchors;
	}
	
	class Anchor extends Circle {
		private final DoubleProperty x, y;
		
	Anchor(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 10);
		setFill(color.deriveColor(1, 1, 1, 0.5));
		setStroke(color);
		setStrokeWidth(2);
		setStrokeType(StrokeType.OUTSIDE);
		
		this.x = x;
		this.y = y;
		
		x.bind(centerXProperty());
		y.bind(centerYProperty());
		shapesDrag();
	}
	
	private void shapesDrag() {
		final Delta dragDelta = new Delta();
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				dragDelta.x = getCenterX() - mouseEvent.getX();
				dragDelta.y = getCenterY() - mouseEvent.getY();
				getScene().setCursor(Cursor.MOVE);
			}
		});
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
			}
		});
		
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				double newX = mouseEvent.getX() + dragDelta.x;
				if (newX > 0 && newX <getScene().getWidth()) {
					setCenterX(newX);
				}
				double newY = mouseEvent.getY() + dragDelta.y;
				if (newY > 0 && newY <getScene().getHeight()) {
					setCenterY(newY);
				
			}
		}
		});	
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.HAND);
				}
			}
		});	
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					getScene().setCursor(Cursor.DEFAULT);
				}
			}
		});	
	}
	
	private class Delta {
		double x, y; 
	}
	
	}
}
