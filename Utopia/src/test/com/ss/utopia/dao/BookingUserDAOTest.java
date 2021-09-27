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

import main.com.ss.utopia.dao.BookingUserDAO;
import main.com.ss.utopia.entity.BookingUser;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class BookingUserDAOTest {

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
		BookingUserDAO budao = new BookingUserDAO(conn);
		BookingUser bookingUser = new BookingUser();
		bookingUser.setBookingID(1);
		bookingUser.setUserID(1);
		budao.addUser(bookingUser);
		assertTrue(budao.readUser().stream().anyMatch(bu -> (bu.getUserID() == bookingUser.getUserID())));
		budao.deleteUser(bookingUser);
	}

}
