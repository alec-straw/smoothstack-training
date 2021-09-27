package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.FlightStatus;

public class FlightStatusDAO extends BaseDAO<FlightStatus> {
	public FlightStatusDAO(Connection conn) {
		super(conn);
	}

	// select flight by flight id
	public FlightStatus readFlightByID(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight_status WHERE id = ?", new Object[] { id }).get(0);
	}

	@Override
	protected List<FlightStatus> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightStatus> flights = new ArrayList<>();
		while (rs.next()) {
			FlightStatus flight = new FlightStatus();
			flight.setId(rs.getInt("id"));
			flight.setRouteID(rs.getInt("route_id"));
			flight.setPlaneID(rs.getInt("airplane_id"));
			flight.setDeparture_time(rs.getObject("departure_time", LocalDateTime.class));
			flight.setReservedSeats(rs.getInt("reserved_seats"));
			flight.setSeatPrice(rs.getFloat("seat_price"));
			flight.setPassengerCount(rs.getInt("passenger_count"));
			flight.setAvailableSeats(rs.getInt("available_seats"));
			flights.add(flight);
		}
		rs.close();
		return flights;
	}
}
