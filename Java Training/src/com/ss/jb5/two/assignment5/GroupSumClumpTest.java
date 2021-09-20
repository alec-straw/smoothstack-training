/**
 * test for GroupSumClump
 */
package com.ss.jb5.two.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class GroupSumClumpTest {

	/**
	 * Test method for {@link com.ss.jb5.two.assignment5.GroupSumClump#groupSumClump(int, int[], int)}.
	 */
	@Test
	void testGroupSumClump() {
		GroupSumClump gsc = new GroupSumClump();
		int[] arr = {2, 4, 8};
		assertTrue(gsc.groupSumClump(0, arr, 10));
		int[] arr2 = {1, 2, 4, 8, 1};
		assertTrue(gsc.groupSumClump(0, arr2, 14));
		int[] arr3 = {2, 4, 4, 8};
		assertFalse(gsc.groupSumClump(0, arr3, 14));
	}

}
