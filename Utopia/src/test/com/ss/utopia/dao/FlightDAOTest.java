/**
 * 
 */
package test.com.ss.utopia.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.com.ss.utopia.dao.FlightDAO;
import main.com.ss.utopia.entity.Flight;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class FlightDAOTest {
	
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
		FlightDAO fdao = new FlightDAO(conn);
		assertNotNull(fdao.readFlightByRoute(1));
		assertNotNull(fdao.readFlights());
		assertNotNull(fdao.description());
		Flight f = new Flight();
		f.setPlaneID(1);
		f.setDeparture_time(LocalDateTime.now());
		f.setFlightID(fdao.getMaxFlightId() + 1);
		f.setReservedSeats(1);
		f.setRouteID(1);
		f.setSeatPrice(100.0f);
		fdao.addFlight(f);
		assertEquals(f.getFlightID(), fdao.readFlightByID(f.getFlightID()).getFlightID());
		f.setSeatPrice(2.0f);
		fdao.updateFlight(f);
		assertEquals(f.getSeatPrice(), fdao.readFlightByID(f.getFlightID()).getSeatPrice());
		fdao.deleteFlight(f);
	}

}
