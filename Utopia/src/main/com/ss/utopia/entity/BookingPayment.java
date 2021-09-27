/**
 * booking payment entity
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class BookingPayment {

	private Integer bookingID;
	private String stripID;
	private Integer refunded;

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public String getStripID() {
		return stripID;
	}

	public void setStripID(String stripID) {
		this.stripID = stripID;
	}

	public Integer getRefunded() {
		return refunded;
	}

	public void setRefunded(Integer refunded) {
		this.refunded = refunded;
	}

}
