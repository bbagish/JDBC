package JDBCconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtility;
import utils.DBUtility.DBType;

public class JDBCSQLStatement {

	public static void main(String[] args) throws SQLException {
		Connection connection =DBUtility.getConnection(DBType.MYSQL);
		Statement statement = null;
		ResultSet resultset = null;
		try {
			System.out.println("Connected to MySql database..");
			statement = connection.createStatement();
			resultset = statement.executeQuery("Select * from countries");
			while (resultset.next()) {
				System.out
						.println(resultset.getString("country_name") + "'s ID is " + resultset.getString("country_id"));
			}
			System.out.println(
					"####################################Query 2 on the way #################################");
			resultset.previous();
			resultset.previous();
			resultset.close();
			resultset = statement.executeQuery("SELECT last_name, department_name"
					+ " FROM employees e join departments d" + " ON e.department_id=d.department_id");
			while (resultset.next()) {
				System.out.println(
						resultset.getString("last_name") + " works in " + resultset.getString("department_name"));
			}

		} catch (SQLException e) {
			System.out.println("Something went wrong");

		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();

			}

		}
	}
}
