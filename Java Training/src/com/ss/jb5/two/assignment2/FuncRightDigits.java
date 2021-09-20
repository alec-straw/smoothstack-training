/**
 * 
 */
package com.ss.jb5.two.assignment2;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author alecs
 *
 */
public class FuncRightDigits {

	public LinkedList<Integer> getRights(LinkedList<Integer> ints) {
		return ints.stream().map(num -> num % 10).collect(Collectors.toCollection(LinkedList::new));
	}

}
