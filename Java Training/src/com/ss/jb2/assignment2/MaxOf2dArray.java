/**
 * 
 */
package com.ss.jb2.assignment2;

import java.util.Random;

/**
 * @author alecs
 * Finds max of 2d array
 */
public class MaxOf2dArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize a 2d array
		final int ROWS = 4;
		final int COLUMNS = 6;
		Integer [][] array2d = new Integer [ROWS][COLUMNS];
		Random rand = new Random();
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				array2d[row][col] = rand.nextInt(100);
			}
		}
		//find max
		int max = -1, maxRow = -1, maxCol = -1;
		for (int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if (max < array2d[row][col]) {
					max = array2d[row][col];
					maxRow = row;
					maxCol = col;
				}
			}
		}
		System.out.println("Max of " + max + " at " + maxRow + ", " + maxCol);

	}

}
