/**
 * Prints patterns using loops
 */
package com.ss.jb1.a1;

/**
 * @author alecs
 *
 */
public class LoopingPatterns {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder stars = new StringBuilder("");
		StringBuilder spaces = new StringBuilder("");
		StringBuilder periods = new StringBuilder(".........");
		for(Integer count = 1; count <= 4; count++) {
			System.out.println(count + ")");
			if (count == 3) {
				spaces.append("     ");
				stars.append("*");
			}
			if(count == 2 || count == 4)
				System.out.println(periods);
			for(Integer level = 1; level <= 4; level++) {
				switch (count) {
				case 1:
					stars.append('*');
					System.out.println(spaces.toString() + stars);
					break;
				case 2:
					System.out.println(spaces.toString() + stars);
					stars.delete(0, 1);
					break;
				case 3:
					System.out.println(spaces.toString() + stars);
					spaces.delete(0, 1);
					stars.append("**");
					break;
				case 4:
					spaces.append(' ');
					stars.delete(0, 2);
					System.out.println(spaces.toString() + stars);
					break;
				}
			}
			if(count == 1 || count == 3)
				System.out.println(periods);
			periods.append('.');
		}
		
	}

}
