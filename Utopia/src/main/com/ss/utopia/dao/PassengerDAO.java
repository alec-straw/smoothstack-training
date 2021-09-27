/**
 * data access passenger
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import main.com.ss.utopia.entity.Passenger;

/**
 * @author alecs
 *
 */
public class PassengerDAO extends BaseDAO<Passenger> {

	public PassengerDAO(Connection conn) {
		super(conn);
	}

	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("INSERT INTO passenger (id, booking_id, given_name, family_name, dob, gender, address) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] { passenger.getPassengerID(), passenger.getBookingID(), passenger.getGivenName(),
						passenger.getFamilyName(), passenger.getDob(), passenger.getGender(), passenger.getAddress() });
	}

	public void updatePassengerAddr(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("UPDATE passenger set address = ? where id = ?",
				new Object[] { passenger.getAddress(), passenger.getPassengerID() });
	}

	public void deletePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("DELETE from passenger where id = ?", new Object[] { passenger.getPassengerID() });
	}

	public List<Passenger> readPassengers() throws ClassNotFoundException, SQLException {
		return read("SELECT * from passenger", null);
	}

	// get list of passengers on flight
	public List<Passenger> getPassengersByFlight(Integer flightid) throws ClassNotFoundException, SQLException {
		return read(
				"SELECT * FROM passenger WHERE booking_id in (SELECT booking_id FROM flight_bookings WHERE flight_id = ?)",
				new Object[] { flightid });
	}

	// get passenger by id
	public Passenger getPassengersByID(Integer id) throws Exception {
		return read("SELECT * FROM passenger WHERE id = ?", new Object[] { id }).get(0);
	}

	// get max passId for next pass
	public Integer getMaxPassId() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM passenger WHERE id = (select max(id) from passenger)", null).get(0).getPassengerID();
	}

	@Override
	protected List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while (rs.next()) {
			Passenger passenger = new Passenger();
			passenger.setPassengerID(rs.getInt("id"));
			passenger.setBookingID(rs.getInt("booking_id"));
			passenger.setGivenName(rs.getString("given_name"));
			passenger.setFamilyName(rs.getString("family_name"));
			passenger.setDob(rs.getObject("dob", LocalDate.class));
			passenger.setGender(rs.getString("gender"));
			passenger.setAddress(rs.getString("address"));
			passengers.add(passenger);
		}
		rs.close();
		return passengers;
	}

}
