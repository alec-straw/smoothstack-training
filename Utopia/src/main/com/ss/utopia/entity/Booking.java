/**
 * booking entity
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class Booking {

	private Integer bookingID;
	private Integer isActive;
	private String confirmationCode;

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

}
