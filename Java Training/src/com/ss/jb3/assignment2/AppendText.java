/**
 * appends text to file
 */
package com.ss.jb3.assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author alecs
 * appends text to file from command line, creates file if it does not exist
 */
public class AppendText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Enter path: ");

		try (Scanner input = new Scanner(System.in);
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(input.nextLine(), true))) {
			System.out.println("File opened. Enter line of text to append to file: ");
			bWriter.write("\n" + input.nextLine());
			bWriter.close();
			System.out.println("File appended");

		} catch (IOException e) {
			System.out.println("No writable file");
		} catch (RuntimeException e) {
			System.out.println("Input error");
			e.printStackTrace();
		}
	}

}
