/**
 * implementation of triangle shape
 */
package com.ss.jb2.assignment3;

/**
 * @author alecs
 * a triangle defined by three side lengths
 */
public class Triangle implements Shape {

	
	
	private Double side1, side2, side3;
	
	//constructor given three side lengths
	Triangle(Double s1, Double s2, Double s3){
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	
	//constructs equilateral triangle 
	Triangle(Double equalSides){
		side1 = side2 = side3 = equalSides;
	}
	
	@Override
	//calculates area with Herons formula
	public Double calculateArea() {
		Double semiPerimeter = (side1 + side2 + side3) / 2.0;
		return Math.sqrt(semiPerimeter*(semiPerimeter - side1)*(semiPerimeter - side2)*(semiPerimeter - side3));
	}

	@Override
	//displays side lengths
	public void display() {
		System.out.println("Triangle:");
		System.out.println("Side 1: " + side1);
		System.out.println("Side 2: " + side2);
		System.out.println("Side 3: " + side3);
	}

}
