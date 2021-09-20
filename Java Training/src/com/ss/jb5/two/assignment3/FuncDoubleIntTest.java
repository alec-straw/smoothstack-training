/**
 * test doubleInt
 */
package com.ss.jb5.two.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * @author alecs
 *
 */
class FuncDoubleIntTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.two.assignment3.FuncDoubleInt#doubleInts(java.util.LinkedList)}.
	 */
	@Test
	void testDoubleInts() {
		FuncDoubleInt func = new FuncDoubleInt();
		Integer[] numbers = { 6, 8, 6, 8, -1 };
		Integer[] expectedResult = { 12, 16, 12, 16, -2 };
		assertEquals(Arrays.asList(expectedResult), func.doubleInts(Arrays.asList(numbers)));
	}

}
