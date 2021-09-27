/**
 * 
 */
package main.com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author alecs
 *
 */
public class TravelerService {

	static private ConnectionUtil connUtil = new ConnectionUtil();

	public TravelerService() {
		System.out.println("1) Book a Ticket");
		System.out.println("2) Cancel an Upcoming Trip ");
		System.out.println("3) Quit to previous");
		Menu menu = new Menu();
		int c = menu.selectInt(3);
		try (Connection conn = connUtil.getConnection()) {
			if (c == 1) {
				menu.selectFlight(menu.selectRoute());
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
