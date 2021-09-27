/**
 * 
 */
package main.com.ss.utopia.service.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import main.com.ss.utopia.dao.AirportDAO;
import main.com.ss.utopia.dao.BookingDAO;
import main.com.ss.utopia.dao.FlightBookingDAO;
import main.com.ss.utopia.dao.FlightDAO;
import main.com.ss.utopia.dao.FlightStatusDAO;
import main.com.ss.utopia.dao.PassengerDAO;
import main.com.ss.utopia.dao.RoleDAO;
import main.com.ss.utopia.dao.RouteDAO;
import main.com.ss.utopia.dao.UserDAO;
import main.com.ss.utopia.entity.Airplane;
import main.com.ss.utopia.entity.Airport;
import main.com.ss.utopia.entity.Flight;
import main.com.ss.utopia.entity.FlightStatus;
import main.com.ss.utopia.entity.Passenger;
import main.com.ss.utopia.entity.Route;
import main.com.ss.utopia.entity.User;
import main.com.ss.utopia.service.ConnectionUtil;
import main.com.ss.utopia.service.DescribeService;
import main.com.ss.utopia.service.Menu;
import main.com.ss.utopia.service.Start;

/**
 * @author alecs
 *
 */
public class AdminService {

	ConnectionUtil connUtil = new ConnectionUtil();

	public AdminService() {
		Menu m = new Menu();
		int choice = m.toIntMenu(Arrays.asList("Add/Update/Delete/Read Flights", "Add/Update/Delete/Read Seats",
				"Add/Update/Delete/Read Tickets and Passengers", "Add/Update/Delete/Read Airports",
				"Add/Update/Delete/Read Travelers", "Add/Update/Delete/Read Employees",
				"Over-ride Trip Cancellation for a ticket."));
		try {
			switch (choice) {
			case 0:
				System.out.println("Manage Flights");
				menuFlights();
				break;
			case 1:
				System.out.println("Manage Seats");
				menuSeats();
				break;
			case 2:
				System.out.println("Manage Bookings/Passengers");
				menuBookings();
				break;
			case 3:
				System.out.println("Manage Airports");
				menuAirports();
				break;
			case 4: // traveler accounts
				System.out.println("Manage traveler accounts");
				manageAccounts("traveler");
				break;
			case 5: // employees account
				System.out.println("Manage employee accounts");
				manageAccounts("agent");
				break;
			case 6:
				removePassenger();

			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database access error");
		}

	}

	// select route by origin then destination
	public Route selectRoute() {
		Menu menu = new Menu();
		int choice = -1;
		List<Route> routes = null;
		ArrayList<String> descriptions = null;
		do {
			try (Connection conn = connUtil.getConnection()) {
				Airport origin = menu.selectAirport();
				System.out.println("Select destination airport");
				RouteDAO rdao = new RouteDAO(conn);
				routes = rdao.readRoutesOrig(origin.getAirportCode());
				DescribeService describe = new DescribeService();
				descriptions = new ArrayList<String>(describe.describeRoutes(routes));
				descriptions.add("Create new");
				choice = menu.toIntMenu(descriptions);
				if (choice == descriptions.size()) {
					updateDestinations(origin);
				} else
					break;
			} catch (Exception e) {
				System.out.println("Failure to read route");
				e.printStackTrace();
			}
		} while (true);
		return routes.get(choice);
	}

	// menu to update airports and routes
	private void menuAirports() throws ClassNotFoundException, SQLException {
		Menu menu = new Menu();
		int choice = menu.toIntMenu(Arrays.asList("Add", "Update", "Delete", "Read"));
		Connection conn = connUtil.getConnection();

		switch (choice) {
		case 0:
			Airport ap = new Airport();
			System.out.print("Enter IATA ID: ");
			ap.setAirportCode(menu.getString());
			System.out.print("Enter name of city: ");
			ap.setCityName(menu.getString());
			AirportDAO adao = new AirportDAO(conn);
			adao.addAirport(ap);
			break;
		case 1:
			System.out.println(updateDestinations(menu.selectAirport()));
			break;
		case 2:
			System.out.println(removeAirport());
			break;
		case 3:
			readAirports();

		}
	}

	// ask for an airport to list destination for
	private void readAirports() {
		Menu menu = new Menu();
		try (Connection conn = connUtil.getConnection()) {
			AirportDAO adao = new AirportDAO(conn);
			System.out.println("Select airport to view destinations");
			Airport selection = menu.selectAirport();
			RouteDAO rdao = new RouteDAO(conn);
			rdao.readRoutesOrig(selection.getAirportCode()).forEach((rt) -> {
				try {
					System.out.println(adao.readByCode(rt.getDestinationID()));
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("Read error");
				}
			});
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("failure to read airports");
		}
	}

	// ask for an airport to remove
	private String removeAirport() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			Airport ap = menu.selectAirport();
			adao.deleteAirport(ap);
			return "Airport deleted";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			return "Failure to delete airport";
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// update destinations, route table, for selected airport
	private String updateDestinations(Airport ap) throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			AirportDAO adao = new AirportDAO(conn);
			System.out.println("Select airport to add to destinations");
			ArrayList<Airport> airports = new ArrayList<Airport>(adao.readAirports());
			airports.removeIf(a -> a.getAirportCode() == ap.getAirportCode());
			Airport ap2 = menu.selectAirport(airports);
			Route route = new Route();
			RouteDAO rdao = new RouteDAO(conn);
			route.setDestinationID(ap2.getAirportCode());
			route.setOriginID(ap.getAirportCode());
			rdao.addRoute(route);
			conn.commit();
			return "Successfully added to destinations";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			e.printStackTrace();
			return "Failure to update destinations";
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	// menu to update bookings of passengers on flights
	private void menuBookings() throws SQLException {
		Menu menu = new Menu();
		int choice = menu.toIntMenu(Arrays.asList("Add", "Update", "Delete", "Read"));
		switch (choice) {
		case 0:
			menu.createBooking();
			break;
		case 1:
			System.out.println(updatePassenger());
			break;
		case 2:
			System.out.println(removePassenger());
			break;
		case 3:
			listFlightPassengers();
			break;
		}
	}

	// user selects flight then view passengers on flight
	private void listFlightPassengers() {
		Menu menu = new Menu();
		System.out.println("Read passengers from where?");
		try (Connection conn = connUtil.getConnection()) {
			Flight flight = menu.selectFlight(menu.selectRoute());
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.getPassengersByFlight(flight.getFlightID()).forEach(System.out::println);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Failure to read passengers");
		}
	}

	// remove a passenger from a flight
	private String removePassenger() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			System.out.println("Remove passenger for: ");
			Flight flight = menu.selectFlight(menu.selectRoute());
			List<Passenger> passengers = pdao.getPassengersByFlight(flight.getFlightID());
			Passenger passenger = passengers.get(menu.toIntMenu(passengers.stream().map(p -> p.toString()).toList()));

			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			BookingDAO bdao = new BookingDAO(conn);
			fbdao.deleteBooking(flight.getFlightID(), passenger.getBookingID());
			pdao.deletePassenger(passenger);
			bdao.deactivateBooking(passenger.getBookingID());
			return "Passenger removed successfully";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			return "Failure to remove passenger";
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// select passenger to update
	private String updatePassenger() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			System.out.println("Update bookings for: ");
			Flight flight = menu.selectFlight(menu.selectRoute());
			List<Passenger> passengers = pdao.getPassengersByFlight(flight.getFlightID());
			Passenger passenger = passengers.get(menu.toIntMenu(passengers.stream().map(p -> p.toString()).toList()));
			System.out.println("Update which attribute?");
			int choice = menu.toIntMenu(Arrays.asList("First name", "Last name", "Address"));
			switch (choice) {
			case 0:
				passenger.setGivenName(menu.getString());
				break;
			case 1:
				passenger.setFamilyName(menu.getString());
				break;
			case 2:
				passenger.setAddress(menu.getString());
				break;
			}
			pdao.updatePassengerAddr(passenger);
			return "Passenger updated successfully";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			return "Failure to update passenger";
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// presents operations to perform on user or employee accounts
	private void manageAccounts(String role) throws SQLException, ClassNotFoundException {
		Menu menu = new Menu();
		int choice = menu.toIntMenu(Arrays.asList("Add", "Update", "Delete", "Read"));
		Connection conn = connUtil.getConnection();
		UserDAO udao = new UserDAO(conn);
		RoleDAO roledao = new RoleDAO(conn);
		Integer roleid = roledao.getRoleByName(role).getRoleID();
		switch (choice) {
		case 0:
			udao.addUser(createUser(roleid));
			break;
		case 1:
			// select user
			User selection = selectUser(udao.readByRole(roleid));
			updateUser(selection);
		case 2:
			// delete user
			User selected = selectUser(udao.readByRole(roleid));
			System.out.println(removeUser(selected));
			break;
		case 3:
			udao.readByRole(roleid).stream().forEach(System.out::println);
			break;
		}
		conn.close();
	}

	// removed user
	String removeUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.deleteUser(user);
			conn.commit();
			return "User removed";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			return "Failure to remove user";
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// update user attributes
	private void updateUser(User user) throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			// update account
			System.out.println("Selecte attribute to update");
			Integer choice2 = menu.toIntMenu(Arrays.asList("Email", "password", "phone"));
			if (choice2 == 0) {
				System.out.println("Set new email");
				user.setEmail(menu.getString());
			} else if (choice2 == 1) {
				System.out.println("Set new password");
				user.setPassword(menu.getString());
			} else {
				System.out.println("Set new phone number");
				user.setPhone(menu.getString());
			}
			udao.updateUser(user);
			conn.commit();
			System.out.println("User updated");
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				conn.rollback();
				System.out.println("Failure to update user");
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// create user of role
	private User createUser(Integer roleid) {
		Menu menu = new Menu();
		User usr = new User();
		usr.setRoleid(roleid);
		System.out.println("Enter username");
		usr.setUsername(menu.getString());
		System.out.println("Enter password");
		usr.setPassword(menu.getString());
		System.out.println("Enter first/given name");
		usr.setGivenName(menu.getString());
		System.out.println("Enter last/family name");
		usr.setFamilyName(menu.getString());
		System.out.println("Enter email");
		usr.setEmail(menu.getString());
		System.out.println("Enter phone");
		usr.setPhone(menu.getString());
		return usr;
	}

	// select menu from list of users
	private User selectUser(List<User> users) {
		Menu menu = new Menu();
		int choice = menu.toIntMenu(users.stream().map(User::toString).toList());
		return users.get(choice);
	}

	// menu for seats
	private void menuSeats() throws ClassNotFoundException, SQLException {
		Menu menu = new Menu();
		int choice = menu.toIntMenu(Arrays.asList("Add", "Update", "Delete", "Read"));
		switch (choice) {
		case 0:
			addSeats();
			// add reserved seats
			break;
		case 1:
			updateSeatPrice();
			// update seat price
			break;
		case 2:
			removeSeats();
			// remove reserved seats
			break;
		case 3:
			FlightDAO fdao = new FlightDAO(connUtil.getConnection());
			fdao.description().forEach(System.out::println);
			break;
		}
	}

	// add seats to flight
	private void addSeats() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight flight = menu.selectFlight(menu.selectRoute());
			FlightStatusDAO fsdao = new FlightStatusDAO(conn);
			FlightStatus fs = fsdao.readFlightByID(flight.getFlightID());
			System.out.println("How many seats to reserve of " + fs.getAvailableSeats() + " available seats?");
			flight.setReservedSeats(flight.getReservedSeats() + menu.selectInt(fs.getAvailableSeats()));
			System.out.println("Seats added successfully");
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				conn.rollback();
				System.out.println("Failure to add seats");
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// update seat price
	private void updateSeatPrice() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight flight = menu.selectFlight(menu.selectRoute());
			System.out.print("Current price: " + flight.getSeatPrice() + "\nEnter new price: ");
			Float price = Start.input.nextFloat();
			flight.setSeatPrice(price);
			conn.commit();
			System.out.println("Seats price updated");
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				conn.rollback();
				System.out.println("Failure to change price");
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// remove seats from flight
	private void removeSeats() throws SQLException {
		Menu menu = new Menu();
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight flight = menu.selectFlight(menu.selectRoute());
			System.out.println("How many seats of " + flight.getReservedSeats() + " reserved seats to remove?");
			flight.setReservedSeats(flight.getReservedSeats() - menu.selectInt(flight.getReservedSeats()));
			conn.commit();
			System.out.println("Seats removed succefully");
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				conn.rollback();
				System.out.println("Failure to remove seats");
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// menu for flights
	private void menuFlights() throws ClassNotFoundException, SQLException {
		Menu m = new Menu();
		int choice = m.toIntMenu(Arrays.asList("Add", "Update", "Delete", "Read"));
		switch (choice) {
		case 0:
			createFlight();
			break;
		case 1:
			updateFlightMenu();
			break;
		case 2:
			deleteFlightMenu();
			break;
		case 3:
			FlightDAO fdao = new FlightDAO(connUtil.getConnection());
			fdao.description().forEach(System.out::println);
			break;
		}
	}

	// delete flight
	public void deleteFlightMenu() {
		Menu menu = new Menu();
		try {
			Route route = menu.selectRoute();
			Flight flight = menu.selectFlight(route);
			System.out.println(removeFlight(flight));
		} catch (SQLException e) {
			System.out.println("Database read error");
		}

	}

	// update flight
	public void updateFlightMenu() throws ClassNotFoundException {
		Menu menu = new Menu();
		try {
			Route route = menu.selectRoute();
			Flight flight = menu.selectFlight(route);
			System.out.println("Selecte attribute to update");
			Integer choice = menu.toIntMenu(Arrays.asList("Plane ID", "Depature Time"));
			switch (choice) {
			case 0:
				Airplane plane = menu.selectPlane();
				flight.setPlaneID(plane.getPlaneID());
				break;
			case 1:
				LocalDateTime datetime = LocalDateTime.now();
				System.out.println("Select month");
				int month = 1 + menu.toIntMenu(Arrays.asList(DateFormatSymbols.getInstance().getMonths()));
				System.out.println("Select day");
				int day = menu.selectInt(YearMonth.of(datetime.getYear(), month).lengthOfMonth());
				System.out.println("Select hour");
				int hour = menu.selectInt(24);
				System.out.println("Select minute");
				int minute = 15 * menu.toIntMenu(IntStream.range(0, 5)
						.mapToObj(i -> hour + ":" + ((i > 0) ? String.valueOf(i * 15) : "00")).toList());
				datetime = LocalDateTime.of(datetime.getYear(), month, day, hour, minute);
				flight.setDeparture_time(datetime);
				break;
			}

			System.out.println(updateFlight(flight));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// create totally new flight
	public void createFlight() {
		Menu menu = new Menu();
		try {
			Route route = menu.selectRoute();
			Airplane plane = menu.selectPlane();
			LocalDateTime datetime = LocalDateTime.now();
			System.out.println("Select month");
			int month = 1 + menu.toIntMenu(Arrays.asList(DateFormatSymbols.getInstance().getMonths()));
			System.out.println("Select day");
			int day = menu.selectInt(YearMonth.of(datetime.getYear(), month).lengthOfMonth());
			System.out.println("Select hour");
			int hour = menu.selectInt(24);
			System.out.println("Select minute");
			int minute = 15 * menu.toIntMenu(IntStream.range(0, 5)
					.mapToObj(i -> hour + ":" + ((i > 0) ? String.valueOf(i * 15) : "00")).toList());
			datetime = LocalDateTime.of(datetime.getYear(), month, day, hour, minute);
			int reservedSeats = 0;
			Float price = 100.0f;
			Flight flight = new Flight();
			flight.setRouteID(route.getRouteId());
			flight.setPlaneID(plane.getPlaneID());
			flight.setDeparture_time(datetime);
			flight.setReservedSeats(reservedSeats);
			flight.setSeatPrice(price);
			System.out.println(addFlight(flight));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String addFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			flight.setFlightID(fdao.getMaxFlightId());
			fdao.addFlight(flight);
			conn.commit();
			return "Flight added sucessfully";
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			return "Flight addition failed";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public String updateFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			conn.commit();
			return "Flight updated sucessfully";
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			return "Flight update failed";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public String removeFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.deleteFlight(flight);
			conn.commit();
			return "Flight deleted sucessfully";
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			return "Flight delete failed";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}