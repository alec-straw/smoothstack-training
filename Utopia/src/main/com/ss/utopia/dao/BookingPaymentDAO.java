/**
 * 
 */
package main.com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.ss.utopia.entity.BookingPayment;

/**
 * @author alecs
 *
 */
public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

	public BookingPaymentDAO(Connection conn) {
		super(conn);

	}

	public void addPayment(BookingPayment payment) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_payment (booking_id, stripe_id) VALUES (?, ?)",
				new Object[] { payment.getBookingID(), payment.getStripID() });
	}

	public void updateRefunded(BookingPayment payment) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_payment set refunded = ? where booking_id = ?",
				new Object[] { payment.getRefunded(), payment.getBookingID() });
	}

	public void deletePayment(Integer bookingID) throws ClassNotFoundException, SQLException {
		save("delete from booking_payment where booking_id = ?", new Object[] { bookingID });
	}

	public List<BookingPayment> readPayment(Integer bookingID) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_payment where booking_id = ?", new Object[] { bookingID });
	}

	@Override
	protected List<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingPayment> payments = new ArrayList<>();
		while (rs.next()) {
			BookingPayment payment = new BookingPayment();
			payment.setBookingID(rs.getInt("booking_id"));
			payment.setStripID(rs.getString("stripe_id"));
			payment.setRefunded(rs.getInt("refunded"));
		}
		return payments;
	}

}
