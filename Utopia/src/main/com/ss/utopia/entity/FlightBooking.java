/**
 * flight bookings entity
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class FlightBooking {

	private Integer flightID;
	private Integer bookingID;

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
	}

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

}
