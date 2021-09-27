/**
 * 
 */
package test.com.ss.utopia.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.com.ss.utopia.dao.BookingDAO;
import main.com.ss.utopia.entity.Booking;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class BookingDAOTest {

	static Connection conn = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ConnectionUtil connUtil = new ConnectionUtil();
		conn = connUtil.getConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		conn.close();
	}

	@Test
	void test() throws ClassNotFoundException, SQLException {
		Booking booking;
		BookingDAO bdao = new BookingDAO(conn);
		bdao.addBooking("xxx");
		booking = bdao.confirmBooking("xxx");
		assertNotNull(booking);
		bdao.deactivateBooking(booking.getBookingID());
		assertEquals(0, bdao.confirmBooking("xxx").getIsActive());
		bdao.deleteBooking(booking);
	}

}
