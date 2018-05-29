package JDBCconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JDBCNavigations {
	
	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String DbPassword = "12345";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String sql="SELECT department_id, count(*) FROM employees GROUP BY department_id";
	
	@BeforeTest
	public void connectToDatabase() throws SQLException{
		connection=DriverManager.getConnection(URL,DbUserName,DbPassword);
	}
	
	@Test
	public void getRowsCount() throws SQLException{
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		//GETROW method return current ROw number
		resultSet.last();
		int rowsCount=resultSet.getRow();
		
		System.out.println("Number of Rows returned --> " + rowsCount);
	}
	
	@Test(dependsOnMethods={"getRowsCount"},enabled=false)
	public void goReverse() throws SQLException{
		resultSet.afterLast();
		
		while(resultSet.previous()){
			System.out.println(resultSet.getString("last_name") +" earns "+ resultSet.getDouble("salary"));
		}
	}
	
	@Test(dependsOnMethods={"goReverse"},enabled=false)
	public void goToSpecificRow() throws SQLException{
		resultSet.absolute(2);
		System.out.println("2nd ROW : " + resultSet.getString("last_name") +" earns "+ resultSet.getDouble("salary"));
		resultSet.absolute(1);
		System.out.println("1st ROW : " + resultSet.getString("last_name") +" earns "+ resultSet.getDouble("salary"));
	}
	
	@Test()
	public void printAll() throws SQLException{
		resultSet.beforeFirst();
		
		while(resultSet.next()){
			int currentRowNum=resultSet.getRow();
			int currentDepID=resultSet.getInt("department_id");
			int currentCount=resultSet.getInt("count(*)");
			
			System.out.println(currentRowNum +"\t"+currentDepID+"-->"+currentCount);
			
		}
	}
	
	
	@AfterTest
	public void closeConnections() throws SQLException{
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
	
}
