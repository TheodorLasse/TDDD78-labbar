package se.liu.thela038.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeTest
{
    public static void main(String[] args) {
	final List<Shape> shapes = new ArrayList<>();
	shapes.add(new Circle(10, 10, 5, Color.cyan));
	shapes.add(new Circle(12, 10, 5, Color.cyan));
	shapes.add(new Circle(16, 10, 5, Color.cyan));
	shapes.add(new Rectangle(10, 10, 10, 10, Color.blue));
	shapes.add(new Rectangle(12, 10, 10, 10, Color.blue));
	shapes.add(new Rectangle(16, 10, 10, 10, Color.blue));
	shapes.add(new Text(16, 10, 10, Color.blue, "tjolahola"));
	for (Shape shape : shapes) {
	    //shape.draw();
	}
    }
}
