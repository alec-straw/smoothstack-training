/**
 * 
 */
package com.ss.jb5.one.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class ThreeLetterAStringTest {

	/**
	 * Test method for {@link com.ss.jb5.one.assignment3.ThreeLetterAString#getStrings(java.util.LinkedList)}.
	 */
	@Test
	void testGetStrings() {
		LinkedList<String> strs = new LinkedList<String>();
		ThreeLetterAString lister = new ThreeLetterAString();
		//test empty
		assertEquals ("", lister.getStrings(strs).stream().collect(Collectors.joining(",")));
		//test sorted
		strs.add("ape");
		strs.add("apple");
		strs.add("at");
		strs.add("eat");
		strs.add("add");
		assertEquals("ape,add", lister.getStrings(strs).stream().collect(Collectors.joining(",")));
	}

}
