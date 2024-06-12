package selenium;

import org.openqa.selenium.WebDriver;

public class EmiCalculatorPage {
	public void perform() {
		WebDriver driver = DriverManager.initDriver("chrome");
		driver.get("https://emicalculator.net/");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
