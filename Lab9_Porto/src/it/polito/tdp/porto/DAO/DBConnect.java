package it.polito.tdp.porto.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnect {
	
	static private final String jdbcURL = "jdbc:mysql://localhost/porto?user=root";
	private static ComboPooledDataSource dataSource = null;

	public static Connection getConnection() {
		try {
			if (dataSource == null) {
				dataSource = new ComboPooledDataSource();
				dataSource.setJdbcUrl(jdbcURL);
			}
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot get connection " + jdbcURL, e);
		}
	}
}
