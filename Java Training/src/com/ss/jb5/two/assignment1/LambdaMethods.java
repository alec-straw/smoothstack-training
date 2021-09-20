/**
 * class that returns lambdas
 */
package com.ss.jb5.two.assignment1;

import java.util.function.Function;

/**
 * @author alecs
 *
 */
public class LambdaMethods {
	public Function<Integer, String> getLambda(Integer index) {
		switch (index) {
		case 1:
			return isOdd();
		case 2:
			return isPrime();
		case 3:
			return isPalindrome();
		default:
			return null;
		}
	}

	public Function<Integer, String> isOdd() {
		return (i) -> (i % 2 == 1) ? "ODD" : "EVEN";
	}

	public Function<Integer, String> isPrime() {
		return (i) -> {
			for (int c = 2; c < i / 2; c++)
				if (i % c == 0)
					return "COMPOSITE";
			return "PRIME";
		};
	}

	public Function<Integer, String> isPalindrome() {
		return (i) -> (String.valueOf(i).equals(new StringBuilder(String.valueOf(i)).reverse().toString()))
				? "PALINDROME"
				: "NOT PALINDROME";
	}

}
