/**
 * tests and demo of basic LambdaMethods class
 */
package com.ss.jb5.two.assignment1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * @author alecs
 *
 */
class LambdaMethodsTest {

	/**
	 * Test method for {@link com.ss.jb5.two.assignment1.LambdaMethods#getLambda(java.lang.Integer)}.
	 */
	@Test
	void testGetLambda() {
		LambdaMethods lam = new LambdaMethods();
		try (Stream<String> stream = Files.lines(Path.of("resources/testInput.txt"))) {
			stream.forEach((str) -> {
				String[] pair = str.split(" ");
				if (pair.length == 2)
					System.out.println(lam.getLambda(Integer.parseInt(pair[0])).apply(Integer.parseInt(pair[1])));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testIsOdd() {
		LambdaMethods lam = new LambdaMethods();
		assertEquals("ODD", lam.isOdd().apply(3));
		assertEquals("EVEN", lam.isOdd().apply(4));
	}

	@Test
	void testIsPrime() {
		LambdaMethods lam = new LambdaMethods();
		assertEquals("PRIME", lam.isPrime().apply(7));
		assertEquals("COMPOSITE", lam.isPrime().apply(8));
	}

	@Test
	void testIsPalindrome() {
		LambdaMethods lam = new LambdaMethods();
		assertEquals("PALINDROME", lam.isPalindrome().apply(101));
		assertEquals("NOT PALINDROME", lam.isPalindrome().apply(110));
	}

}
