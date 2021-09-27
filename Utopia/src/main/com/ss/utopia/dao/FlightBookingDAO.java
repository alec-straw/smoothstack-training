/**
 * data acessor for flight bookings
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.FlightBooking;

/**
 * @author alecs
 *
 */
public class FlightBookingDAO extends BaseDAO<FlightBooking> {

	public FlightBookingDAO(Connection conn) {
		super(conn);
	}

	public void addBooking(Integer flightid, Integer bookid) throws ClassNotFoundException, SQLException {
		save("INSERT INTO flight_booking (flight_id, booking_id) VALUES (?, ?)", new Object[] { flightid, bookid });
	}

	public void deleteBooking(Integer flightid, Integer bookid) throws ClassNotFoundException, SQLException {
		save("delete from flight_booking where flight_id = ? AND booking_id = ?", new Object[] { flightid, bookid });
	}

	public List<FlightBooking> readByFlight(Integer flightid) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight_booking where flight_id = ?", new Object[] { flightid });
	}

	@Override
	protected List<FlightBooking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBooking> books = new ArrayList<>();
		while (rs.next()) {
			FlightBooking booking = new FlightBooking();
			booking.setBookingID(rs.getInt("id"));
			booking.setFlightID(rs.getInt("flight_id"));
			books.add(booking);
		}
		return books;
	}

}
