/**
 * 
 */
package com.ss.jb2.assignment3;

/**
 * @author alecs
 *
 */
public class Rectangle implements Shape {

	private Double side1;
	private Double side2;

	// constructs a rectangle given two side lengths
	Rectangle(Double s1, Double s2) {
		side1 = s1;
		side2 = s2;
	}

	// constructs a square given a side length
	Rectangle(Double equalSides) {
		side1 = side2 = equalSides;
	}

	@Override
	// calculates and returns area
	public Double calculateArea() {
		return side1 * side2;
	}

	@Override
	// displays side lengths
	public void display() {
		System.out.println("Rectangle:");
		System.out.println("Side 1: " + side1);
		System.out.println("Side 2: " + side2);
	}

}
