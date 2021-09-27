/**
 * a booking user entity
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class BookingUser {

	private Integer bookingID;
	private Integer userID;

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}
