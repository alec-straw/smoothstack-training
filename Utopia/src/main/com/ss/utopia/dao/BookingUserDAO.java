/**
 * data access for booking_user
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.BookingUser;

/**
 * @author alecs
 *
 */
public class BookingUserDAO extends BaseDAO<BookingUser> {

	public BookingUserDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub

	}

	public void addUser(BookingUser user) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_user (booking_id, user_id) VALUES (?, ?)",
				new Object[] { user.getBookingID(), user.getUserID() });
	}

	public void updateBookingUser(BookingUser user) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_user set user_id = ? where booking_id = ?",
				new Object[] { user.getUserID(), user.getBookingID() });
	}

	public void deleteUser(BookingUser user) throws ClassNotFoundException, SQLException {
		save("delete from booking_user where booking_id = ?", new Object[] { user.getBookingID() });
	}

	public List<BookingUser> readUser() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_user", null);
	}

	@Override
	protected List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingUser> users = new ArrayList<>();
		while (rs.next()) {
			BookingUser user = new BookingUser();
			user.setBookingID(rs.getInt("booking_id"));
			user.setUserID(rs.getInt("user_id"));
			users.add(user);
		}
		return users;
	}

}
