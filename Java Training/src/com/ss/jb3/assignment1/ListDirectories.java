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
	
	ListDirectories(String dir){
		directory = dir;
	}
	
	void listDirectories() {
		File file;
		try {
			file = new File(directory);
			if (!file.isDirectory()) {
				System.out.println("");
			}
		}
		catch(NullPointerException e) {
			
		}
	}
	
	/**
	 * @param args
	 * list directory from command argument
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListDirectories list = new ListDirectories(args[0]);
		list.listDirectories();
	}

}
