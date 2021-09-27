/**
 * test for route DAO
 */
package test.com.ss.utopia.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.com.ss.utopia.dao.AirportDAO;
import main.com.ss.utopia.dao.RouteDAO;
import main.com.ss.utopia.entity.Airport;
import main.com.ss.utopia.entity.Route;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class RouteDAOTest {

	static Connection conn = null;
	static Airport ap = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ConnectionUtil connUtil = new ConnectionUtil();
		conn = connUtil.getConnection();
		ap = new Airport();
		ap.setAirportCode("XXX");
		ap.setCityName("Z");
		AirportDAO adao = new AirportDAO(conn);
		adao.addAirport(ap);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		new AirportDAO(conn).deleteAirport(ap);
		conn.close();
	}

	@Test
	void test() throws ClassNotFoundException, SQLException {
		RouteDAO rdao = new RouteDAO(conn);
		assertNotNull(rdao.readRoutes());
		Route rt = new Route();
		rt.setDestinationID("XXX");
		rt.setOriginID("LAX");
		rdao.addRoute(rt);
		rt.setRouteId(rdao.readRoutesDest(rt.getDestinationID()).get(0).getRouteId());
		rt.setDestinationID("LAX");
		rt.setOriginID("XXX");
		rdao.updateRoute(rt);
		Route rt2 = rdao.readRoutesByID(rt.getRouteId()).get(0);
		assertEquals(rt.getDestinationID(), rt2.getDestinationID());
		assertEquals(rt.getOriginID(), rt2.getOriginID());
	}

}
