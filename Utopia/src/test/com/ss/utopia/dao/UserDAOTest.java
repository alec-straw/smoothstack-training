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

import main.com.ss.utopia.dao.UserDAO;
import main.com.ss.utopia.entity.User;
import main.com.ss.utopia.service.ConnectionUtil;

/**
 * @author alecs
 *
 */
class UserDAOTest {

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
		UserDAO udao = new UserDAO(conn);
		assertNotNull(udao.readUsers());
		User usr = new User();
		usr.setUserid(1 + udao.getMaxUserId());
		usr.setEmail("a@b.com");
		usr.setFamilyName("B");
		usr.setGivenName("A");
		usr.setPassword("passwrd");
		usr.setPhone("1234567");
		usr.setRoleid(1);
		usr.setUsername("usr");
		udao.addUser(usr);
		usr.setEmail("abcde@email.com");
		udao.updateUser(usr);
		assertEquals(usr.getEmail(),
		udao.getUserById(usr.getUserid()).getEmail());
		udao.deleteUser(usr);

	}
}
