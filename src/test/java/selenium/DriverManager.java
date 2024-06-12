package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//Singleton design pattern.
public class DriverManager {
	public static WebDriver chromeDriver, edgeDriver;

	// Returns driver based on specified browser, each type of driver is instantiated only once.
	public static WebDriver initDriver(String browser) {
		if (browser.equalsIgnoreCase("edge")) {
			if (edgeDriver == null) {
				WebDriverManager.edgedriver().setup();
				edgeDriver = new EdgeDriver();
			}
			return edgeDriver;
		} else {
			if (chromeDriver == null) {
				WebDriverManager.chromedriver().setup();
				chromeDriver = new ChromeDriver();
			}
			return chromeDriver;
		}
	}
}