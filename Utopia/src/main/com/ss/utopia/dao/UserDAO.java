/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.User;

/**
 * @author alecs
 *
 */
public class UserDAO extends BaseDAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	public void addUser(User user) throws ClassNotFoundException, SQLException {
		save("INSERT INTO user (role_id, given_name, family_name, username, email, password, phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] { user.getRoleid(), user.getGivenName(), user.getFamilyName(), user.getUserid(),
						user.getEmail(), user.getPassword(), user.getPhone() });
	}

	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		save("UPDATE user set email = ?, password = ?, phone = ? where id = ?",
				new Object[] { user.getEmail(), user.getPassword(), user.getPhone(), user.getUserid() });
	}

	public void deleteUser(User User) throws ClassNotFoundException, SQLException {
		save("DELETE from user where id = ?", new Object[] { User.getUserid() });
	}

	public List<User> readUsers() throws ClassNotFoundException, SQLException {
		return read("SELECT * from user", null);
	}

	public User getUserById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user WHERE id = ?", new Object[] { id }).get(0);
	}

	//returns list of user of a certain user_role
	public List<User> readByRole(Integer role) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user WHERE role_id = ?", new Object[] { role });
	}

	// get max user id for next user
	public Integer getMaxUserId() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user WHERE id = (select max(id) from user)", null).get(0).getUserid();
	}

	@Override
	protected List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User user = new User();
			user.setUserid(rs.getInt("id"));
			user.setRoleid(rs.getInt("role_id"));
			user.setGivenName(rs.getString("given_name"));
			user.setFamilyName(rs.getString("family_name"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			users.add(user);
		}
		return users;
	}

}
