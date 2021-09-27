/**
 * flight entity
 */
package main.com.ss.utopia.entity;

import java.time.LocalDateTime;

/**
 * @author alecs
 *
 */
public class Flight {

	private Integer flightID;
	private Integer routeID;
	private Integer planeID;
	private LocalDateTime departure_time;
	private Integer reservedSeats;
	private Float seatPrice;

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
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

}
