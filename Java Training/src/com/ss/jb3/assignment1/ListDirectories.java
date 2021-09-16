/**
 * list folder and all files and sub-directories
 */
package com.ss.jb3.assignment1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author alecs
 *
 */
public class ListDirectories {


	// method to list directories recursively
	public void listDirectories(String directory) {
		Path path;
		try {
			path = Path.of(directory);
			if (!Files.exists(path)) {
				System.out.println("Path does not exist");
				System.exit(0);
			}
			if (Files.isDirectory(path)) {
				System.out.println("Directory: " + path.getFileName());
				// list sub-directories
				for (File subPath : path.toFile().listFiles()) {
					ListDirectories list = new ListDirectories();
					list.listDirectories(subPath.getPath());
				}
			} else
				System.out.println("File: " + path.getFileName());
		} catch (NullPointerException e) {
			System.out.println("null arguement provied to listDirectories");
		}
	}

	/**
	 * @param args list directory from command argument
	 */
	public static void main(String[] args) {
		
		System.out.println("Enter path: ");
		try (Scanner input = new Scanner(System.in)) {
			ListDirectories list = new ListDirectories();
			list.listDirectories(input.next());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
