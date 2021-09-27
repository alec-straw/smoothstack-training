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

import main.com.ss.utopia.dao.AirportDAO;

import main.com.ss.utopia.entity.Airport;

import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class AirportDAOTest {
	
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
		AirportDAO adao = new AirportDAO(conn);
		assertNotNull(adao.readAirports());
		Airport ap = new Airport();
		ap.setAirportCode("zzz");
		ap.setCityName("z");
		adao.addAirport(ap);
		ap.setCityName("yyyy");
		adao.updateAirport(ap);
		assertEquals("zzz, y", adao.readByCode("zzz"));
		adao.deleteAirport(ap);

	}

}
