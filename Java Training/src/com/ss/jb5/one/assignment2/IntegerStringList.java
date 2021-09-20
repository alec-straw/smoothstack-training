/**
 * 
 */
package com.ss.jb5.one.assignment2;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author alecs
 *
 */
public class IntegerStringList {

	public String makeString(LinkedList<Integer> numbers) {
		return numbers.stream()
				.map((Integer num) -> ((num % 2 == 0) ? "e" : "o") + num)
				.collect(Collectors.joining(","));
	}

}
