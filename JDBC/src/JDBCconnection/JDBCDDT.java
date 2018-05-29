package JDBCconnection;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.Driver;
import utils.DBUtility;
import utils.DBUtility.DBType;

public class JDBCDDT extends DBUtility {
	WebDriver driver;

	@BeforeTest
	public void gotoApplication() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get("https://editor.datatables.net/examples/simple/simple.html");
	}

	@Test
	public void test() throws SQLException {
		establishConnection(DBType.MYSQL);
		System.out.println("Connection is created");
		List<String[]> queryResultList = executeQuery(
				"SELECT first_name, last_name, job_id, city,phone_number, hire_date, salary "
						+ "FROM employees e JOIN departments d " + "ON e.department_id=d.department_id "
						+ "JOIN locations l " + "ON d.location_id=l.location_id");

		closeConnections();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		for (int rowNum = 0; rowNum < 5; rowNum++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".buttons-create")));
			driver.findElement(By.cssSelector(".buttons-create")).click();
			// get data from SQL return list
			String[] rowData = queryResultList.get(rowNum);
			String firstName = rowData[0];
			String lastName = rowData[1];
			String position = rowData[2];
			String office = rowData[3];
			String extension = rowData[4].substring(8);
			String startDate = rowData[5];
			String salary = rowData[6];
			// enter data into front end
			driver.findElement(By.id("DTE_Field_first_name")).sendKeys(firstName);
			driver.findElement(By.id("DTE_Field_last_name")).sendKeys(lastName);
			driver.findElement(By.id("DTE_Field_position")).sendKeys(position);
			driver.findElement(By.id("DTE_Field_office")).sendKeys(office);
			driver.findElement(By.id("DTE_Field_extn")).sendKeys(extension);
			driver.findElement(By.id("DTE_Field_start_date")).sendKeys(startDate);
			driver.findElement(By.id("DTE_Field_salary")).sendKeys(salary);
			WebElement create = driver.findElement(By.xpath("//button[.='Create']"));
			create.click();
			wait.until(ExpectedConditions.invisibilityOf(create));

		}

	}
}
