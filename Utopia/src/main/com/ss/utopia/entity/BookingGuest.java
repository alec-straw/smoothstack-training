/**
 * booking guest entity
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class BookingGuest {

	private Integer bookingID;
	private String email;
	private String phone;

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
