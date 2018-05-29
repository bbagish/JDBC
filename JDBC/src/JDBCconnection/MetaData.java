package JDBCconnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetaData {
	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String user = "root";
	private static String pwd = "root";

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	static ResultSetMetaData rsMetaData = null;
	static String sql = "SELECT * FROM departments";

	public static void main(String[] args) throws SQLException {
		connection = DriverManager.getConnection(URL, user, pwd);
		DatabaseMetaData dbMetaData = connection.getMetaData();
		System.out.println("Database metadata demo : " + dbMetaData.getDatabaseProductName() + ", "
				+ dbMetaData.getDatabaseProductVersion() + ", " + dbMetaData.getUserName());

		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		rsMetaData = resultSet.getMetaData();

		for (int colIndex = 1; colIndex <= rsMetaData.getColumnCount(); colIndex++) {
			System.out.println(rsMetaData.getColumnName(colIndex));
		}
		resultSet.last();
		resultSet.getRow();
		resultSet.beforeFirst();

		resultSet.close();
		statement.close();
		connection.close();
	}
}
