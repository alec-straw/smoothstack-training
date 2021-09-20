/**
 * 
 */
package com.ss.jb5.two.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class SampleSingletonTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.two.assignment6.SampleSingleton#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		SampleSingleton s1 = SampleSingleton.getInstance();
		SampleSingleton s2 = SampleSingleton.getInstance();
		assertEquals(s1, s2);
	}

	/**
	 * Test method for
	 * {@link com.ss.jb5.two.assignment6.SampleSingleton#getConnection()}.
	 */
	@Test
	void testGetConnection() {
		SampleSingleton s1 = SampleSingleton.getInstance();
		assertEquals(null, s1.getConnection());
	}

}
