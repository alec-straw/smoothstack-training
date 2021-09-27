/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.Booking;

/**
 * @author alecs
 *
 */
public class BookingDAO extends BaseDAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public void addBooking(String confirmationCode) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking (confirmation_code) VALUES (?)", new Object[] { confirmationCode });
	}

	public void deactivateBooking(Integer id) throws ClassNotFoundException, SQLException {
		save("UPDATE booking set is_active = 0 where id = ?",
				new Object[] { id });
	}

	public void deleteBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("delete from booking where id = ?", new Object[] { booking.getBookingID() });
	}

	public Booking confirmBooking(String code) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking WHERE confirmation_code = ?", new Object[] { code }).get(0);
	}

	public Integer getMaxBookingId() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking WHERE id = (select max(id) from booking)", null).get(0).getBookingID();
	}

	public List<Booking> readBooking() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking", null);
	}

	@Override
	protected List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> books = new ArrayList<>();
		while (rs.next()) {
			Booking booking = new Booking();
			booking.setBookingID(rs.getInt("id"));
			booking.setConfirmationCode(rs.getString("confirmation_code"));
			booking.setIsActive(rs.getInt("is_active"));
			books.add(booking);
		}
		return books;
	}

}
