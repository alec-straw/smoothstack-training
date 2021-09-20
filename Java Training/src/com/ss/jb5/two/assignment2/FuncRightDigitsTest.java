/**
 * 
 */
package com.ss.jb5.two.assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class FuncRightDigitsTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.two.assignment2.FuncRightDigits#getRights(java.util.LinkedList)}.
	 */
	@Test
	void testGetRights() {
		FuncRightDigits func = new FuncRightDigits();
		Integer[] numbers = {1, 10, 23, 456, 789};
		Integer[] rights = { 1, 0, 3, 6, 9 };
		assertEquals(Arrays.asList(rights), func.getRights(new LinkedList<Integer>(Arrays.asList(numbers))));
	}

}
