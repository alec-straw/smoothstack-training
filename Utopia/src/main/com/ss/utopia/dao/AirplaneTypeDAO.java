package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}

	public void addPlane(AirplaneType planeType) throws ClassNotFoundException, SQLException {
		save("INSERT INTO airplane_type (id, max_capacity) VALUES (?, ?)",
				new Object[] { planeType.getTypeID(), planeType.getMaxCapacity() });
	}

	public void updateType(AirplaneType planeType) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane_type set max_capacity = ? where id = ?",
				new Object[] { planeType.getMaxCapacity(), planeType.getTypeID() });
	}

	public void deleteType(AirplaneType planeType) throws ClassNotFoundException, SQLException {
		save("delete from airplane where id = ?", new Object[] { planeType.getTypeID() });
	}

	public List<AirplaneType> readPlanes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane_type", null);
	}

	@Override
	protected List<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<AirplaneType> planes = new ArrayList<>();
		while (rs.next()) {
			AirplaneType planeType = new AirplaneType();
			planeType.setTypeID(rs.getInt("id"));
			planeType.setMaxCapacity(rs.getInt("max_capacity"));
			planes.add(planeType);
		}
		return planes;
	}
}