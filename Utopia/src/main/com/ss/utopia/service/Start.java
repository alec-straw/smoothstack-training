/**
 * main function to start from
 */
package main.com.ss.utopia.service;

import java.util.Arrays;

import java.util.Scanner;

import main.com.ss.utopia.service.admin.AdminService;

/**
 * @author alecs
 *
 */
public class Start {

	public static Scanner input = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Menu menu = new Menu();
		Integer choice = -1;
		while (choice != 3) {
			choice = menu.toIntMenu(Arrays.asList("Employee/Agent", "Administrator", "Traveler", "Quit"));
			switch (choice) {
			case 0:
				new EmployeeService();
				break;
			case 1:
				new AdminService();
				break;
			case 2:
				new TravelerService();
				break;
			}
		}
		input.close();

	}
}
