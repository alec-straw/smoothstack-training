/**
 * 
 */
package com.ss.jb5.two.assignment6;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author alecs
 *
 */
public class SampleSingleton {

	private SampleSingleton() {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://3.16.210.106:5432/inventory", "user", "pass");
		} catch (SQLException e) {
			System.out.println("No connection");
		}
	}

	volatile private static Connection conn = null;

	volatile private static SampleSingleton instance = null;

	public static SampleSingleton getInstance() {
		if (instance == null) {
			synchronized (SampleSingleton.class) {
				if (instance == null)
					instance = new SampleSingleton();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

}
