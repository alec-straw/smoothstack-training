/**
 * terminal menu for utopia
 */
package main.com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.com.ss.utopia.dao.AirplaneDAO;
import main.com.ss.utopia.dao.AirportDAO;
import main.com.ss.utopia.dao.BookingDAO;
import main.com.ss.utopia.dao.FlightBookingDAO;
import main.com.ss.utopia.dao.FlightDAO;
import main.com.ss.utopia.dao.PassengerDAO;
import main.com.ss.utopia.dao.RouteDAO;
import main.com.ss.utopia.entity.Airplane;
import main.com.ss.utopia.entity.Airport;
import main.com.ss.utopia.entity.Booking;
import main.com.ss.utopia.entity.Flight;
import main.com.ss.utopia.entity.Passenger;
import main.com.ss.utopia.entity.Route;

/**
 * @author alecs
 *
 */
public class Menu {

	ConnectionUtil connUtil = new ConnectionUtil();

	// present flights by route and select by departure time
	public Flight selectFlight(Route route) {

		try (Connection conn = connUtil.getConnection()) {
			FlightDAO fdao = new FlightDAO(conn);
			List<Flight> flights = fdao.readFlightByRoute(route.getRouteId());
			System.out.println("Select depature time");
			return flights.get(toIntMenu(flights.stream().map(flt -> flt.getDeparture_time().toString()).toList()));
		} catch (Exception e) {
			System.out.println("failure to read flight data for route " + route.getRouteId());
			return null;
		}
	}

	// ask for plane selection of plane listed by description
	public Airplane selectPlane() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			System.out.println("Select Plane");
			AirplaneDAO pdao = new AirplaneDAO(conn);
			List<Airplane> planes = pdao.readPlanes();
			return planes.get(toIntMenu(pdao.description()));
		} catch (Exception e) {
			System.out.println("Failure to read plane");
			return null;
		}
	}

	// select route by origin then destination
	public Route selectRoute() throws SQLException {
		Airport origin = selectAirport();
		try (Connection conn = connUtil.getConnection()) {
			System.out.println("Select destination airport");
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> routes = rdao.readRoutesOrig(origin.getAirportCode());
			DescribeService describe = new DescribeService();
			ArrayList<String> descriptions = new ArrayList<String>(describe.describeRoutes(routes));
			descriptions.add("Create new");
			int choice = toIntMenu(descriptions);
			if (choice == descriptions.size())
				return null;
			else
				return routes.get(choice);
		} catch (Exception e) {
			System.out.println("Failure to read route");
			return null;
		}
	}

	// presents menu of given airports returns selected airport
	public Airport selectAirport(List<Airport> airports) {
		System.out.println("Select airport");
		int choice = toIntMenu(airports.stream().map(ap -> ap.toString()).toList());
		return airports.get(choice);
	}

	// presents menu of all airports returns selected airport
	public Airport selectAirport() throws SQLException {

		try (Connection conn = connUtil.getConnection()) {
			AirportDAO adao = new AirportDAO(conn);
			return selectAirport(adao.readAirports());
		} catch (ClassNotFoundException e) {
			System.out.println("Could not read airport");
			return null;
		}
	}

	// create passenger and book to a flight
	public String createBooking() throws SQLException {
		Flight flight = selectFlight(selectRoute());
		Passenger pass = new Passenger();
		System.out.println("Enter passenger given name");
		pass.setGivenName(getString());
		System.out.println("Enter passenger family name");
		pass.setFamilyName(getString());
		System.out.println("Enter birth year: ");
		Integer year = selectInt(1900, 2200);
		System.out.println("Enter birth month: ");
		Integer month = selectInt(12);
		System.out.println("Enter birth day: ");
		Integer day = selectInt(YearMonth.of(year, month).lengthOfMonth());
		pass.setDob(LocalDate.of(year, month, day));
		System.out.println("Enter gender");
		pass.setGender(getString());
		System.out.println("Enter address");
		pass.setAddress(getString());
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			StringBuilder confirmatiionCode = new StringBuilder();
			Random rand = new Random();
			confirmatiionCode.append(bdao.getMaxBookingId() + 1);
			confirmatiionCode.append((rand.nextInt() % 89999999) + 10000000);
			bdao.addBooking(confirmatiionCode.toString());
			Booking book = bdao.confirmBooking(confirmatiionCode.toString());
			pass.setBookingID(book.getBookingID());
			PassengerDAO pdao = new PassengerDAO(conn);
			pass.setPassengerID(pdao.getMaxPassId() + 1);
			pdao.addPassenger(pass);
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			fbdao.addBooking(flight.getFlightID(), book.getBookingID());
			conn.commit();
			return "Added Successfully";
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null)
				conn.rollback();
			return "Failure to add booking";
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	// presents list of strings returns index of choice
	public Integer toIntMenu(List<String> choices) {
		Integer choice = 0;
		for (int ct = 0; ct < choices.size(); ct++)
			System.out.println((ct + 1) + ") " + choices.get(ct));
		choice = selectInt(choices.size());
		return choice - 1;
	}

	// request integer selection
	public Integer selectInt(Integer begin, Integer total) {
		Integer choice = 0;
		while (choice < begin || choice > total) {
			System.out.print("Enter choice " + begin + " to " + total + ": ");
			try {
				choice = Integer.parseInt(Start.input.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
		return choice;
	}

	// select int with default param
	public Integer selectInt(Integer total) {
		return selectInt(1, total);
	}

	// request integer selection
	public String getString() {
		StringBuilder strb;
		while (true) {
			System.out.print("Enter: ");
			try {
				strb = new StringBuilder();
				strb.append(Start.input.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
		return strb.toString();
	}

}
