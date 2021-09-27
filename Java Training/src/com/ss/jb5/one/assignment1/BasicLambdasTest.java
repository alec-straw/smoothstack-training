/**
 * test of various lambdas and a static helper method
 */
package com.ss.jb5.one.assignment1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class BasicLambdasTest {

	/**
	 * Test method for
	 * {@link com.ss.jb5.one.assignment1.BasicLambdas#containsE(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testContainsE() {
		String[] words = { "SmoothStack", "cat", "Bell", "eight" };
		// sort shortest to longest
		Arrays.sort(words, (str1, str2) -> str1.length() - str2.length());
		assertEquals("cat,Bell,eight,SmoothStack", Arrays.stream(words).collect(Collectors.joining(",")));
		// sort longest to shortest
		Arrays.sort(words, (str1, str2) -> str2.length() - str1.length());
		assertEquals("SmoothStack,eight,Bell,cat", Arrays.stream(words).collect(Collectors.joining(",")));
		// sort by first character
		Arrays.sort(words, (str1, str2) -> str1.toLowerCase().charAt(0) - str2.toLowerCase().charAt(0));
		assertEquals("Bell,cat,eight,SmoothStack", Arrays.stream(words).collect(Collectors.joining(",")));
		// put strings containing an e first
		//Arrays.sort(words, (str1, str2) -> (str1.toLowerCase().contains("e") && str2.toLowerCase().contains("e")) ? 0
		//		: str1.toLowerCase().contains("e") ? -1 : 1); //one line version
		Arrays.sort(words, (str1, str2) -> {
			if (str1.toLowerCase().contains("e") && str2.toLowerCase().contains("e"))
				return 0;
			else
				return str1.toLowerCase().contains("e") ? -1 : 1;
		});
		assertEquals("Bell,eight,cat,SmoothStack", Arrays.stream(words).collect(Collectors.joining(",")));
		// same with static helper method
		// first put back in alphabetical order
		Arrays.sort(words, (str1, str2) -> str1.toLowerCase().charAt(0) - str2.toLowerCase().charAt(0));
		Arrays.sort(words, (s1, s2) -> BasicLambdas.containsE(s1, s2));
		assertEquals("Bell,eight,cat,SmoothStack", Arrays.stream(words).collect(Collectors.joining(",")));
	}

}
