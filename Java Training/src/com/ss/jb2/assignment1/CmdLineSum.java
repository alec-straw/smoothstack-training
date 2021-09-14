/**
 * sums args from cmd line
 */
package com.ss.jb2.assignment1;

/**
 * @author alecs
 * 
 */
public class CmdLineSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double sum = 0;
		for(String str : args) {
			try {
				sum += Double.parseDouble(str);
			}
			catch(NumberFormatException e) {
				System.out.println("Skipping invalid input");
			}
		}
		System.out.println("Sum: " + sum);
	}

}
