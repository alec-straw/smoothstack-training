/**
 * 
 */
package main.com.ss.utopia.entity;

import java.time.LocalDateTime;

/**
 * @author alecs
 *
 */
public class FlightStatus {

	private Integer id;
	private Integer routeID;
	private Integer planeID;
	private LocalDateTime departure_time;
	private Integer reservedSeats;
	private Float seatPrice;
	private Integer maxCapacity;
	private Integer passengerCount;
	private Integer availableSeats;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRouteID() {
		return routeID;
	}

	public void setRouteID(Integer routeID) {
		this.routeID = routeID;
	}

	public Integer getPlaneID() {
		return planeID;
	}

	public void setPlaneID(Integer planeID) {
		this.planeID = planeID;
	}

	public LocalDateTime getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(LocalDateTime departure_time) {
		this.departure_time = departure_time;
	}

	public Integer getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(Integer reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public Float getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Float seatPrice) {
		this.seatPrice = seatPrice;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

}
