/**
 * implementation of triangle shape
 */
package com.ss.jb2.assignment3;

/**
 * @author alecs a triangle defined by three side lengths
 */
public class Triangle implements Shape {

	private Double side1, side2, side3;

	// constructor given three side lengths
	Triangle(Double s1, Double s2, Double s3) {
		if (s1 + s2 > s3 && s2 + s3 > s1 && s1 + s3 > s2) {
			side1 = s1;
			side2 = s2;
			side3 = s3;
		} else {
			throw new IllegalArgumentException("Sides: " + s1 + ", " + s2 + ", " + s3 + " do not add up to triangle");
		}
	}

	// constructs equilateral triangle
	Triangle(Double equalSides) {
		side1 = side2 = side3 = equalSides;
	}

	@Override
	// calculates area with Herons formula
	public Double calculateArea() {
		Double semiPerim = (side1 + side2 + side3) / 2.0;
		return Math.sqrt(semiPerim * (semiPerim - side1) * (semiPerim - side2) * (semiPerim - side3));
	}

	@Override
	// displays side lengths
	public void display() {
		System.out.println("Triangle:");
		System.out.println("Side 1: " + side1);
		System.out.println("Side 2: " + side2);
		System.out.println("Side 3: " + side3);
	}

}
