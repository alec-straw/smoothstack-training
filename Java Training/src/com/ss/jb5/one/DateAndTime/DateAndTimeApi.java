/**
 * date and time api practice
 */
package com.ss.jb5.one.DateAndTime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author alecs
 *
 */
public class DateAndTimeApi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//	    1.  Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?
		ZonedDateTime bday = ZonedDateTime.of(LocalDateTime.of(1993, 4, 28, 0, 0), ZoneId.of("America/Chicago"));
//	    2.  Given a random date, how would you find the date of the previous Thursday?
		ZonedDateTime today = ZonedDateTime.now();
		ZonedDateTime now = today.minusDays(1);
		int total = 1;
		for (; now.getDayOfWeek() != DayOfWeek.THURSDAY; total++)
			now = now.minusDays(1);
		System.out.println(total + " days since last thurdsay from " + today);
//	    3.  What is the difference between a ZoneId and a ZoneOffset?
		System.out.println("ZoneID: " + today.getZone());
		System.out.println("ZoneOffset: " + today.getOffset());
//	    4.  How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
		Instant in = bday.toInstant();
		ZonedDateTime fromInstant = ZonedDateTime.ofInstant(in, ZoneId.of("America/Chicago"));
		System.out.println("From ZoneDateTime to Instant back to ZonedDateTime " + fromInstant);
//	    5.  Write an example that, for a given year, reports the length of each month within that year.
		ZonedDateTime givenYear = bday.minusDays(bday.getDayOfYear() - 1);
		while (givenYear.getMonth() != Month.DECEMBER) {
			System.out.println(Duration.between(givenYear.plusMonths(1), givenYear).toDays() * -1 + " days in "
					+ givenYear.getMonth() + ", " + givenYear.getYear());
			givenYear = givenYear.plusMonths(1);
		}
//	    6.  Write an example that, for a given month of the current year, lists all of the Mondays in that month.
		Month given = Month.FEBRUARY;
		now = today.minusDays(today.getDayOfMonth() - 1);
		while (now.getMonth() != given)
			now = now.minusMonths(1);
		while (now.getMonth() == given) {
			if (now.getDayOfWeek() == DayOfWeek.MONDAY)
				System.out.println(now.getDayOfMonth() + " of " + given + ", " + now.getYear() + " is a Monday");
			now = now.plusDays(1);
		}
//	    7.  Write an example that tests whether a given date occurs on Friday the 13th.
		ZonedDateTime givenDate = ZonedDateTime.of(LocalDateTime.of(2021, 8, 13, 0, 0), ZoneId.of("America/Chicago"));
		if (givenDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) && givenDate.getDayOfMonth() == 13)
			System.out.println(givenDate + " is on Friday the 13th");
		else
			System.out.println(givenDate + " is not on Friday the 13th");

	}

}
