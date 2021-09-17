/**
 * unit test for Line
 */
package com.ss.jb4.assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class LineTest {

	@Test
	void test() {
		Line test1 = new Line(0, 0, 1, 1 );
		Line test2 = new Line(0, 0, 1, 1 );
		Line test3 = new Line(0, 0, 0, 0 );
		Line test4 = new Line(0, 0, -1, 1);
		//same line is parallel
		assertTrue(test1.parallelTo(test2));
		//divide by zero for empty line
		assertThrows(ArithmeticException.class, ()->test3.getSlope());
		//length of zero for empty line
		assertEquals(0, test3.getDistance(), 0.0001);
		//these lines are not parallel
		assertFalse(test1.parallelTo(test4));
	}

}
