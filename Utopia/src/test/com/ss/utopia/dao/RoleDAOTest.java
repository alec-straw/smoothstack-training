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

import main.com.ss.utopia.dao.RoleDAO;
import main.com.ss.utopia.entity.Role;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class RoleDAOTest {

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
		RoleDAO rdao = new RoleDAO(conn);
		Role rl = new Role();
		rl.setName("testrole");
		rl.setRoleID(4);
		rdao.addRole(rl);
		rl.setName("test");
		rl.setRoleID(rdao.getRoleByName("test").getRoleID());
		rdao.updateRole(rl);
		assertEquals(rl.getName(), rdao.getRoleByID(rl.getRoleID()).getName() );
		rdao.deleteRole(rl);
	}

}
