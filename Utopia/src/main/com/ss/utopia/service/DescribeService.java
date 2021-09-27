/**
 * converts objects to descriptive strings
 */
package main.com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.dao.AirportDAO;
import main.com.ss.utopia.entity.Route;
import main.com.ss.utopia.entity.User;

/**
 * @author alecs
 *
 */
public class DescribeService {

	public List<String> describeUsers(List<User> users) {

		List<String> descriptions = new ArrayList<>();
		users.forEach((usr) -> {
			StringBuilder strb = new StringBuilder();
			strb.append("Name: ");
			strb.append(usr.getGivenName() + " " + usr.getFamilyName());
			strb.append("\n\tusername: " + usr.getUsername());
			strb.append(" Email: " + usr.getEmail());
			descriptions.add(strb.toString());
		});
		return descriptions;
	}

	public List<String> describeRoutes(List<Route> rts) {
		ArrayList<String> descriptions = null;
		try (Connection conn = new ConnectionUtil().getConnection()) {
			AirportDAO adao = new AirportDAO(conn);
			descriptions = new ArrayList<String>(rts.stream().map((rt) -> {
				try {
					return adao.readByCode(rt.getDestinationID());
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("Read error on airports");
				}
				return rt.getDestinationID();
			}).toList());
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Failure to read routes");
		}
		return descriptions;
	}

}
