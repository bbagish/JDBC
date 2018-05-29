package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

	private static WebDriver driver;

	public static WebDriver getDriver() {

		if (driver == null) {
			switch (Config.getProperty("browser")) {
			case "chrome":
				System.setProperty(Config.getProperty("drivertype"), Config.getProperty("driverpath"));
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "/Users/cybertek/Documents/Libraries/drivers/geckodriver");
				driver = new FirefoxDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", "/the/location/of/ie/driver");
				driver = new InternetExplorerDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			}
		}
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

}