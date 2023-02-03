package se.liu.thela038.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleTest
{
    public static void main(String[] args) {
	final List<Circle> circles = new ArrayList<>();
	circles.add(new Circle(10, 10, 5, Color.cyan));
	circles.add(new Circle(12, 10, 5, Color.cyan));
	circles.add(new Circle(16, 10, 5, Color.cyan));
	for (Circle element : circles) {
	    System.out.println(element.getX() + " " + element.getY());
	}
    }
}
