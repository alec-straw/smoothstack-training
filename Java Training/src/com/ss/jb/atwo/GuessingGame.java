/**
 * A number guessing game
 */
package com.ss.jb.atwo;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * @author alecs
 * Game of 5 tries to guess a randomized number from 1 to 100
 */
public class GuessingGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		Integer num = rand.nextInt(100) + 1;
		Scanner input = new Scanner(System.in);
		Integer guess = 0;
		Integer numGuesses = 0;
		while(++numGuesses <= 5) {
			System.out.println("Guess a number from 1-100");
			try {
				guess = input.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input");
				break;
			}
			if (Math.abs(guess - num) <= 10) {
				System.out.println("Congratulations your number was " + num);
				break;
			}
		}
		if (numGuesses > 5) System.out.println("Sorry, your number was " + num);
		input.close();
	}

}
