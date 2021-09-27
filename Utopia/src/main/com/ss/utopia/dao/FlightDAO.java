/**
 * DAO for flights
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.Flight;

/**
 * @author alecs
 *
 */
public class FlightDAO extends BaseDAO<Flight> {

	final String selectFlightsql = "SELECT flight.*, route.origin_id, route.destination_id, airplane.type_id, max_capacity "
			+ "FROM flight LEFT JOIN route on route_id=route.id LEfT JOIN airplane on airplane_id=airplane.id "
			+ "LEFT JOIN airplane_type on airplane.type_id=airplane_type.id";

	public FlightDAO(Connection conn) {
		super(conn);
	}

	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("INSERT INTO flight (id, route_id, airplane_id, departure_time, reserved_seats, seat_price) VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] { flight.getFlightID(), flight.getRouteID(), flight.getPlaneID(),
						flight.getDeparture_time(), flight.getReservedSeats(), flight.getSeatPrice() });
	}

	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("UPDATE flight SET departure_time = ? , reserved_seats = ? , seat_price = ? where id = ?", new Object[] {
				flight.getDeparture_time(), flight.getReservedSeats(), flight.getSeatPrice(), flight.getFlightID() });
	}

	public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("DELETE from flight where id = ?", new Object[] { flight.getFlightID() });
	}

	public List<Flight> readFlights() throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight", null);
	}

	// select flights along route ordered by time
	public List<Flight> readFlightByRoute(Integer routeId) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight WHERE route_id = ?", new Object[] { routeId });
	}

	// get max flight id
	public Integer getMaxFlightId() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight WHERE id = (select max(id) from flight)", null).get(0).getFlightID();
	}

	// select flight by flight id
	public Flight readFlightByID(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight WHERE id = ?", new Object[] { id }).get(0);
	}

	// returns list of flight description
	public List<String> description() throws SQLException {
		List<String> flights = new ArrayList<>();
		PreparedStatement pstmt = conn.prepareStatement("select * from flight_status;");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			StringBuilder strb = new StringBuilder();
			strb.append("Flight " + rs.getInt("id"));
			strb.append(", Plane: " + rs.getInt("airplane_id"));
			strb.append(" Route: " + rs.getInt("route_id"));
			strb.append("\n\tDeparture Time: " + rs.getObject("departure_time", LocalDateTime.class)
					.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")));
			strb.append("\n\tSeat Price: " + rs.getFloat("seat_price"));
			strb.append("\n\tAvailable Seats: " + rs.getInt("available_seats"));
			flights.add(strb.toString());
		}
		return flights;
	}

	@Override
	protected List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<>();
		while (rs.next()) {
			Flight flight = new Flight();
			flight.setFlightID(rs.getInt("id"));
			flight.setRouteID(rs.getInt("route_id"));
			flight.setPlaneID(rs.getInt("airplane_id"));
			flight.setDeparture_time(rs.getObject("departure_time", LocalDateTime.class));
			flight.setReservedSeats(rs.getInt("reserved_seats"));
			flight.setSeatPrice(rs.getFloat("seat_price"));
			flights.add(flight);
		}
		rs.close();
		return flights;
	}

}
