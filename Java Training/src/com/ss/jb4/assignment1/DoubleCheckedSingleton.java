/**
 * 
 */
package com.ss.jb4.assignment1;

/**
 * @author alecs
 *
 */
public class DoubleCheckedSingleton {

	private static volatile DoubleCheckedSingleton instance = null;

	private DoubleCheckedSingleton() {

	}

	// get your instance
	public DoubleCheckedSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedSingleton.class) {
				if (instance == null)
					instance = new DoubleCheckedSingleton();
			}
		}
		return instance;
	}

}
