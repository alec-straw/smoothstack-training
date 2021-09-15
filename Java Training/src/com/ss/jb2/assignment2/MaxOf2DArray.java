/**
 * 
 */
package com.ss.jb2.assignment2;

import java.util.Random;

/**
 * @author alecs
 * Finds max of 2d array
 */
public class MaxOf2DArray {
	
	
	//display max and location of 2d array
	void displayMax (final Integer [][] array2d) {
		int max = -1, maxRow = -1, maxCol = -1;
		for (int row = 0; row < array2d.length; row++) {
			for(int col = 0; col < array2d[row].length; col++) {
				if (max < array2d[row][col]) {
					max = array2d[row][col];
					maxRow = row+1;
					maxCol = col+1;
				}
			}
		}
		System.out.println("Max of " + max + " at " + maxRow + ", " + maxCol);

	}

	/**
	 * test display max with random integers 0-99
	 */
	public static void main(String[] args) {
		// initialize a 2d array
		final int ROWS = 4;
		final int COLUMNS = 6;
		Integer [][] arr2d = new Integer [ROWS][COLUMNS];
		Random rand = new Random();
		System.out.println("2D Array:");
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				arr2d[row][col] = rand.nextInt(100); 
				System.out.print(arr2d[row][col] + " ");
			}
			System.out.println();
		}
		//find max
		MaxOf2DArray max = new MaxOf2DArray();
		max.displayMax(arr2d);
		
	}

}
