/**
 * list folder and all files and subdirectories
 */
package com.ss.jb3.assignment1;

import java.io.File;

/**
 * @author alecs
 *
 */
public class ListDirectories {

	final private String directory;

	// constructor to save directory
	ListDirectories(String dir) {
		directory = dir;
	}

	// method to list directories recursively
	void listDirectories() {
		File file;
		try {
			file = new File(directory);
			if (!file.exists()) {
				System.out.println("Path does not exist");
				System.exit(0);
			}
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				for (File path : file.listFiles()) {
					ListDirectories list = new ListDirectories(path.getPath());
					list.listDirectories();
				}
			} else
				System.out.println("File: " + file.getName());
		} catch (NullPointerException e) {
			System.out.println("null arguement provied to listDirectories");
		}
	}

	/**
	 * @param args list directory from command argument
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Accepts first command line argument as directory");
		} else {
			ListDirectories list = new ListDirectories(args[0]);
			list.listDirectories();
		}
	}

}
