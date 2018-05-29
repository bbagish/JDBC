package JDBCconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import utils.DBUtility;
import utils.DBUtility.DBType;


public class DBUtilTest {
@Test
public void testUtilityConnection() throws SQLException {
	Connection connection = DBUtility.getConnection(DBType.MYSQL);
	Statement statement = connection.createStatement();
	ResultSet resultSet=statement.executeQuery("SELECT * FROM regions");
	List<String[]> sqlResultsList = new ArrayList();
	
	ResultSetMetaData rsMetaData=resultSet.getMetaData();
	int columnsCount =rsMetaData.getColumnCount();
	String[] colNames = new String[columnsCount];
	for(int colIndex=1; colIndex<=columnsCount; colIndex++) {
		colNames[colIndex-1]=rsMetaData.getColumnName(colIndex);
		
	}
	sqlResultsList.add(colNames);
	while(resultSet.next()) {
		String[] rowData=new String[columnsCount];
		for(int cellNum=1; cellNum<=columnsCount; cellNum++) {
			rowData[cellNum-1]=resultSet.getString(cellNum);
			
		}
		sqlResultsList.add(rowData);
	}
	for(String[] rowdata : sqlResultsList) {
		for(String cellData : rowdata) {
			System.out.println(cellData+" ");
		}
		System.out.println();
	}
	
	resultSet.close();
	
	connection.close();
	chooseDay(WeekDays2.Monday);
	chooseDay(WeekDays2.Tuesday);
}
public void chooseDay(WeekDays2 days) {
	switch (days) {
	case Monday:
		
	case Tuesday:
	case Wednesday:
		System.out.println("First Part of Week");
		break;
	case Thursday:
	case Friday:
		System.out.println("Almost Weekend");
		break;
	case Saturday:
	case Sunday:
	System.out.println("Weekend classes!!! Yeah!!");
	}
}
}
