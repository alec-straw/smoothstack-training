/**
 * DAO for airplane
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.Airplane;

/**
 * @author alecs
 *
 */
public class AirplaneDAO extends BaseDAO<Airplane> {

	public AirplaneDAO(Connection conn) {
		super(conn);
	}

	public void addPlane(Airplane plane) throws ClassNotFoundException, SQLException {
		save("INSERT INTO airplane (id, type_id) VALUES (?, ?)",
				new Object[] { plane.getPlaneID(), plane.getTypeID() });
	}

	public void updatePlane(Airplane plane) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane set type_id = ? where id = ?", new Object[] { plane.getTypeID(), plane.getPlaneID() });
	}

	public void deletePlane(Airplane plane) throws ClassNotFoundException, SQLException {
		save("delete from airplane where id = ?", new Object[] { plane.getPlaneID() });
	}

	public List<Airplane> readPlanes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane ORDER BY id", null);
	}

	// returns list of plane descriptions ordered by id
	public List<String> description() throws SQLException {
		List<String> planes = new ArrayList<>();
		PreparedStatement pstmt = conn.prepareStatement("SELECT airplane.*, max_capacity "
				+ "FROM airplane LEFT JOIN airplane_type ON airplane.type_id = airplane_type.id ORDER BY airplane.id");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			planes.add("ID: " + rs.getInt("id") + " Type: " + rs.getInt("type_id") + " Capacity: "
					+ rs.getInt("max_capacity"));
		}
		return planes;
	}

	@Override
	protected List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> planes = new ArrayList<>();
		while (rs.next()) {
			Airplane plane = new Airplane();
			plane.setPlaneID(rs.getInt("id"));
			plane.setTypeID(rs.getInt("type_id"));
			planes.add(plane);
		}
		rs.close();
		return planes;
	}

}
