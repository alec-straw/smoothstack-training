/**
 * 
 */
package com.ss.jb3.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author alecs
 *
 */
public class CharReader {

	// request directory and checks for a character
	public void checkFile(Character c) {
		System.out.println("Enter path: ");
		try (Scanner input = new Scanner(System.in);
				FileReader fReader = new FileReader(input.nextLine());
				BufferedReader bReader = new BufferedReader(fReader)) {
			int readData;
			Integer count = 0;
			do {
				readData = bReader.read();
				if (c == (char) readData)
					count++;
			} while (readData != -1);
			System.out.println("Count " + count + " usages of " + c + " in file");

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0 && args[0].length() == 1) {
			CharReader sum = new CharReader();
			sum.checkFile(args[0].charAt(0));
		} else
			System.out.println("Usage: searches given file for character from command arguement");

	}

}
