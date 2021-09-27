/**
 * jtest for flightBooking
 */
package test.com.ss.utopia.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.com.ss.utopia.dao.BookingDAO;
import main.com.ss.utopia.dao.FlightBookingDAO;
import main.com.ss.utopia.entity.Booking;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class FlightBookingDAOTest {

	static Connection conn = null;
	static Booking booking;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ConnectionUtil connUtil = new ConnectionUtil();
		conn = connUtil.getConnection();
		BookingDAO bdao = new BookingDAO(conn);
		bdao.addBooking("xxx");
		booking = bdao.confirmBooking("xxx");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		new BookingDAO(conn).deleteBooking(booking);
		conn.close();
	}

	@Test
	void test() throws ClassNotFoundException, SQLException {
		FlightBookingDAO fbdao = new FlightBookingDAO(conn);
		fbdao.addBooking(1, booking.getBookingID());
		assertTrue(fbdao.readByFlight(1).stream().anyMatch(f -> (f.getBookingID() == booking.getBookingID())));
		fbdao.deleteBooking(1, booking.getBookingID());

	}

}
