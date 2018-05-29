package JDBCconnection;

import org.testng.annotations.Test;

import utils.DBUtility;
import utils.DBUtility.DBType;

import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;

public class SQLToExcel {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String sql = "SELECT country_id, country_name FROM countries";

	@BeforeTest

	public void setUp() throws SQLException {
		Connection connection = DBUtility.getConnection(DBType.MYSQL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		ExcelUtils.openExcelFile("./Countries.xlsx", "Sheet1");
	}

	@Test
	public void loadDataToExcel() throws SQLException {
		ExcelUtils.setCellData("COUNTRY_ID", 0, 0);
		ExcelUtils.setCellData("COUNTRY_NAME", 0, 1);

		int currentRow = 1;
		while (resultSet.next()) {
			String countryID = resultSet.getString("COUNTRY_ID");
			String countryName = resultSet.getString("COUNTRY_NAME");
			ExcelUtils.setCellData(countryID, currentRow, 0);
			ExcelUtils.setCellData(countryName, currentRow, 1);
			currentRow++;
		}
	}

	@AfterTest
	public void closeConnections() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}

	}

	@AfterTest
	public void afterTest() {
	}

}
