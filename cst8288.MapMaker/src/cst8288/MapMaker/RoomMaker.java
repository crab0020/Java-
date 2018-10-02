package cst8288.MapMaker;

import java.awt.event.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;

public class RoomMaker extends Polygon {
	
	double startingX; 
	double startingY;
	
	public Polygon roomShape (String roomName, int points, double x, double y, MouseEvent e) {
		Polygon roomPolygon = new Polygon();
		int pointIncrements = 360/points; 
		startingX = e.getX();
		startingY = e.getY();
		x = e.getX();
		y = e.getY();
		double radius = startingX + startingY - e.getX() - e.getY();
		int remainingCircumference = 90; 
		Double[] polygonCoordinates = new Double[points * 2];

		for(int i = 0; i < points; i = i + 2) {
			int a = remainingCircumference - pointIncrements;
			remainingCircumference = a;
			
			polygonCoordinates[i] = radius * (Math.cos(a));
			polygonCoordinates[i + 1] = (radius * (Math.sin(a)) * (-1));
		}
		roomPolygon.getPoints().setAll(polygonCoordinates);
		roomPolygon.setStroke(Color.BLACK);
		roomPolygon.setStrokeWidth(4);
		roomPolygon.setStrokeLineCap(StrokeLineCap.ROUND);
		roomPolygon.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		return roomPolygon;
	}
}
