/**
 * passenger entity
 */
package main.com.ss.utopia.entity;

import java.time.LocalDate;

/**
 * @author alecs
 *
 */
public class Passenger {

	private Integer passengerID;
	private Integer bookingID;
	private String givenName;
	private String familyName;
	private LocalDate dob;
	private String gender;
	private String address;

	public String toString() {
		StringBuilder sb = new StringBuilder("ID: ");
		sb.append(passengerID);
		sb.append(" Booking: " + bookingID);
		sb.append(" Name: "+givenName);
		sb.append(" " + familyName);
		
		return sb.toString();
	}

	public Integer getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(Integer passengerID) {
		this.passengerID = passengerID;
	}

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
