/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.Route;

/**
 * @author alecs
 *
 */
public class RouteDAO extends BaseDAO<Route> {

	public RouteDAO(Connection conn) {
		super(conn);
	}

	public void addRoute(Route route) throws ClassNotFoundException, SQLException {
		save("INSERT INTO route (origin_id, destination_id) VALUES (?, ?)",
				new Object[] { route.getOriginID(), route.getDestinationID() });
	}

	public void updateRoute(Route route) throws ClassNotFoundException, SQLException {
		save("UPDATE route set origin_id = ? , destination_id = ? where id = ?",
				new Object[] { route.getOriginID(), route.getDestinationID(), route.getRouteId() });
	}

	public void deleteRoute(Route route) throws ClassNotFoundException, SQLException {
		save("delete from route where id = ?", new Object[] { route.getRouteId() });
	}

	public List<Route> readRoutes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route", null);
	}

	// lookup routes by orign
	public List<Route> readRoutesOrig(String orig) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route WHERE origin_id = ?", new Object[] { orig });
	}

	public List<Route> readRoutesDest(String dest) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route WHERE destination_id = ?", new Object[] { dest });
	}

	public List<Route> readRoutesByID(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route WHERE id = ?", new Object[] { id });
	}

	@Override
	protected List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<>();
		while (rs.next()) {
			Route route = new Route();
			route.setRouteId(rs.getInt("id"));
			route.setOriginID(rs.getString("origin_id"));
			route.setDestinationID(rs.getString("destination_id"));
			routes.add(route);
		}
		rs.close();
		return routes;
	}
}