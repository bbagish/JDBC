package JDBCconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionDemo {
	private static String URL = "jdbc:mysql://localhost:3306/hr";
	public static String DbUserName = "root";
	private static String DbPassword = "root";

	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection(URL, DbUserName, DbPassword);
			System.out.println("MySQL database connection successfull");
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("MySql database connection attempt failed");
			e.printStackTrace();
		}
	}
}