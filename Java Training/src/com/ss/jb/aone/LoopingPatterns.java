/**
 * Prints patterns using loops
 */
package com.ss.jb.aone;

/**
 * @author alecs
 *
 */
public class LoopingPatterns {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String stars = "";
		String spaces = "";
		String periods = ".........";
		for(Integer count = 1; count <= 4; count++) {
			System.out.println(count + ")");
			if (count == 3) {
				spaces = "     ";
				stars = "*";
			}
			if(count == 2 || count == 4)
				System.out.println(periods);
			for(Integer level = 1; level <= 4; level++) {
				switch (count) {
				case 1:
					stars = stars + '*';
					System.out.println(spaces + stars);
					break;
				case 2:
					System.out.println(spaces + stars);
					stars = stars.substring(1);
					break;
				case 3:
					System.out.println(spaces + stars);
					spaces = spaces.substring(1);
					stars = stars + "**";
					break;
				case 4:
					spaces = spaces + ' ';
					stars = stars.substring(2);
					System.out.println(spaces + stars);
					break;
				}
			}
			if(count == 1 || count == 3)
				System.out.println(periods);
			periods = periods + '.';
		}
		
	}

}
