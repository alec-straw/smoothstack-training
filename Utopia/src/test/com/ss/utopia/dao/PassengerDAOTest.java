/**
 * test for passenger dao
 */
package test.com.ss.utopia.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.com.ss.utopia.dao.PassengerDAO;
import main.com.ss.utopia.entity.Passenger;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class PassengerDAOTest {

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
	void test() throws Exception {
		PassengerDAO pdao = new PassengerDAO(conn);
		Passenger pass = new Passenger();
		assertNotNull(pdao.readPassengers());
		assertNotNull(pdao.getPassengersByFlight(1));
		pass.setGivenName("A");
		pass.setFamilyName("Z");
		pass.setGender("male");
		pass.setDob(LocalDate.now());
		pass.setBookingID(1);
		pass.setAddress("123");
		pass.setPassengerID(pdao.getMaxPassId() + 1);
		//add passenger
		pdao.addPassenger(pass);
		pass.setAddress("000");
		pdao.updatePassengerAddr(pass);
		assertEquals(pass.toString(), pdao.getPassengersByID(pass.getPassengerID()).toString());
		pdao.deletePassenger(pass);
	}

}
