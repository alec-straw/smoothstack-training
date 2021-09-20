/**
 * returns sublist of strings starting with a and exactly 3 letters
 */
package com.ss.jb5.one.assignment3;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author alecs
 *
 */
public class ThreeLetterAString {

	public LinkedList<String> getStrings(LinkedList<String> strs) {
		return strs.stream().filter(str -> str.startsWith("a") && str.length() == 3)
				.collect(Collectors.toCollection(LinkedList::new));
	}

}
