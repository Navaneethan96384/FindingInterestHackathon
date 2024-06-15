package seleniumUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

	// Returns driver based on specified browser.
	public static WebDriver createDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "edge":
			return new EdgeDriver();

		case "chrome":
			return new ChromeDriver();
			
		default:
			return null;
		}
	}
}