/**
 * data access for booking guest
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.BookingGuest;

/**
 * @author alecs
 *
 */
public class BookingGuestDAO extends BaseDAO<BookingGuest> {

	public BookingGuestDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub

	}

	public void addAgent(BookingGuest guest) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_guest (booking_id, contact_email, contact_phone) VALUES (?, ?, ?)",
				new Object[] { guest.getBookingID(), guest.getEmail(), guest.getPhone() });
	}

	public void updateEmail(BookingGuest guest) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_guest set contact_email = ? where booking_id = ?",
				new Object[] { guest.getEmail(), guest.getBookingID() });
	}

	public void deleteGuest(BookingGuest guest) throws ClassNotFoundException, SQLException {
		save("delete from booking_guest where booking_id = ?", new Object[] { guest.getBookingID() });
	}

	public List<BookingGuest> readGuest() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_guest", null);
	}

	@Override
	protected List<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingGuest> guests = new ArrayList<>();
		while (rs.next()) {
			BookingGuest guest = new BookingGuest();
			guest.setBookingID(rs.getInt("booking_id"));
			guest.setEmail(rs.getString("contact_email"));
			guest.setPhone(rs.getString("contact_phone"));
		}
		return guests;
	}

}
