package seleniumUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class DocumentReadyStateExpectedCondition implements ExpectedCondition<Boolean> {
	// Logic to wait till a webpage has completely loaded.
	public Boolean apply(WebDriver driver) {
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		String readyState = jsExec.executeScript("return document.readyState").toString();
		if (readyState.equals("complete") || readyState.equals("interactive")) {
			// Returns true when the page has been completely loaded.
			return true;
		} else
			return false;
	}
}
