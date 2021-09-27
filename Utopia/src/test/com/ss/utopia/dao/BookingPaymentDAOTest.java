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
import main.com.ss.utopia.dao.BookingPaymentDAO;
import main.com.ss.utopia.entity.Booking;
import main.com.ss.utopia.entity.BookingPayment;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class BookingPaymentDAOTest {

	static Connection conn = null;
	static Booking booking = null;

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
		BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
		BookingPayment bp = new BookingPayment();
		bp.setBookingID(booking.getBookingID());
		bp.setStripID(booking.getConfirmationCode());
		bp.setRefunded(0);
		bpdao.addPayment(bp);
		bp.setRefunded(1);
		bpdao.updateRefunded(bp);
		assertNotNull(bpdao.readPayment(booking.getBookingID()));
		bpdao.deletePayment(bp.getBookingID());
	}

}
