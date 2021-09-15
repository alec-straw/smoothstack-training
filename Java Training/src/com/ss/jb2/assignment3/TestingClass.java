/**
 * 
 */
package com.ss.jb2.assignment3;

/**
 * @author alecs
 *
 */
public class TestingClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shape[] shapes = new Shape[6];
		shapes[0] = new Circle(2.0);
		shapes[1] = new Circle(1.0);
		shapes[2] = new Triangle(2.0);
		try {
			shapes[3] = new Triangle(2.0, 2.0, 4.0);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			shapes[3] = new Triangle(2.0, 2.0, 3.0);
		}
		shapes[4] = new Rectangle(2.0);
		shapes[5] = new Rectangle(2.0, 3.0);
		for(Shape myShape : shapes) {
			myShape.display();
			System.out.println("Area: " + myShape.calculateArea());
			System.out.println();
		}

	}

}
