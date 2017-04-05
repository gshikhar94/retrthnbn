package com.mindtree.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcConnection {

	public static Connection getConnection() {
		try {
			String URL = "";
			ResourceBundle resourceBundle = ResourceBundle.getBundle("JdbcProperties");

			Class.forName(resourceBundle.getString("sqlDriver"));

			URL = resourceBundle.getString("url");

			String USER = resourceBundle.getString("userName");
			String PASS = resourceBundle.getString("password");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			if (conn != null) {
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error While connecting");
		}
		return null;

	}

	public static void closeConnection(Connection con) throws SQLException {

		if (con != null) {
			con.close();

		}

	}
}
