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

import main.com.ss.utopia.dao.AirplaneTypeDAO;
import main.com.ss.utopia.entity.AirplaneType;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class AirplaneTypeDAOTest {

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
		AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
		AirplaneType apt = new AirplaneType();
		apt.setMaxCapacity(10);
		apt.setTypeID(9999);
		atdao.addPlane(apt);
		apt.setMaxCapacity(8888);
		atdao.updateType(apt);
		assertTrue(atdao.readPlanes().stream().anyMatch(a -> a.getMaxCapacity() == 8888));
		atdao.deleteType(apt);
	}

}
