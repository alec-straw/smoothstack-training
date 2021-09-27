/**
 * 
 */
package main.com.ss.utopia.entity;

/**
 * @author alecs
 *
 */
public class Airport {

	private String airportCode;
	private String cityName;

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return airportCode + ", " + cityName;
	}
}