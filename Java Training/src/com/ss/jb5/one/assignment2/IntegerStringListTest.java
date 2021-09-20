/**
 * test of Integer list to odd/even string
 */
package com.ss.jb5.one.assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class IntegerStringListTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.one.assignment2.IntegerStringList#makeString(java.util.LinkedList)}.
	 */
	@Test
	void testMakeString() {
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		numbers.add(3);
		numbers.add(8);
		IntegerStringList list = new IntegerStringList();
		assertEquals("o3,e8", list.makeString(numbers));
	}

}
