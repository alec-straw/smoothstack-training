/**
 * implementation of Circle shape
 */
package com.ss.jb2.assignment3;

/**
 * @author alecs a circle defined by a radius
 */
public class Circle implements Shape {

	private Double radius;

	// constructs a Circle given a radius
	Circle(Double r) {
		radius = r;
	}

	@Override
	// calculates and returns area
	public Double calculateArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public void display() {
		System.out.println("Circle:");
		System.out.println("Radius: " + radius);
		System.out.println("Diameter: " + 2.0 * radius);
		System.out.println("Circumference: " + 2.0 * radius * Math.PI);
	}

}
