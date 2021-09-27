/**
 * 
 */
package com.ss.jb5.two.assignment4;

import java.util.List;

/**
 * @author alecs
 *
 */
public class StringxRemover {

	public List<String> removeXs(List<String> strings) {
		return strings.stream().map(str -> str.replace("x", "")).toList();
	}

}
