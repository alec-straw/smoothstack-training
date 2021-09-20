/**
 * converts linked list to doubled link list
 */
package com.ss.jb5.two.assignment3;

import java.util.List;

/**
 * @author alecs
 *
 */
public class FuncDoubleInt {

	public List<Integer> doubleInts(List<Integer> ints) {
		return ints.stream().map(num -> num * 2).toList();
	}

}
