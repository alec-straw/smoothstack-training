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
		Shape[] shapes = new Shape[3];
		shapes[0] = new Circle(2.0);
		shapes[1] = new Triangle(2.0);
		shapes[2] = new Rectangle(2.0);
		for(Shape myShape : shapes) {
			myShape.display();
			System.out.println("Area: " + myShape.calculateArea());
		}

	}

}
