/**
 * 
 */
package com.ss.jb5.two.assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class StringxRemoverTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.two.assignment4.StringxRemover#removeXs(java.util.List)}.
	 */
	@Test
	void testRemoveXs() {
		String[] strs = { "abcd", "cxdxs", "yxy", "", "m" };
		String[] expectedResult = { "abcd", "cds", "yy", "", "m" };
		StringxRemover strxrm = new StringxRemover();
		assertEquals(strxrm.removeXs(Arrays.asList(strs)), Arrays.asList(expectedResult));
	}

}
