/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.Role;

/**
 * @author alecs
 *
 */
public class RoleDAO extends BaseDAO<Role> {

	public RoleDAO(Connection conn) {
		super(conn);
	}

	public void addRole(Role role) throws ClassNotFoundException, SQLException {
		save("INSERT INTO  user_role (name) VALUES (?)", new Object[] { role.getName() });
	}

	public void updateRole(Role role) throws ClassNotFoundException, SQLException {
		save("UPDATE user_role set name = ? where id = ?", new Object[] { role.getName(), role.getRoleID() });
	}

	public void deleteRole(Role role) throws ClassNotFoundException, SQLException {
		save("delete from user_role where id = ?", new Object[] { role.getRoleID() });
	}

	public List<Role> readRoles() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user_role", null);
	}

	public Role getRoleByID(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * from user_role WHERE id = ?", new Object[] { id }).get(0);
	}

	public Role getRoleByName(String name) throws ClassNotFoundException, SQLException {
		return read("SELECT * from user_role WHERE LOWER(name) like ?", new Object[] { "%" + name + "%" }).get(0);
	}

	@Override
	protected List<Role> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Role> roles = new ArrayList<>();
		while (rs.next()) {
			Role role = new Role();
			role.setRoleID(rs.getInt("id"));
			role.setName(rs.getString("name"));
			roles.add(role);
		}
		return roles;
	}

}
