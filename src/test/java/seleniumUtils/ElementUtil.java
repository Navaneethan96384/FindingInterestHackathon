package seleniumUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	JavascriptExecutor jExecutor;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jExecutor = (JavascriptExecutor) driver;
	}

	// To wait till a page has been completely loaded.
	public boolean waitUntillLoadedPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			// 'until' method executes the logic in apply() method from
			// DocumentReadyStateExpectedCondition till it returns true or timeout duration
			// is
			// exceeded.
			wait.until(new DocumentReadyStateExpectedCondition());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Wait & verify an element.
	public boolean verifyElement(WebElement element) {
		waitUntillLoadedPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyElementLocator(By locator) {
		waitUntillLoadedPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Scroll untill an element is found on the scrollable element.
	public boolean scrollToElement(WebElement element, WebElement scrollableElement) {
		waitUntillLoadedPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		try {
			wait.until(new ScrollUntillElementFound(element, scrollableElement));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean scrollToAndVerifyElement(WebElement element, WebElement scrollableElement) {
		scrollToElement(element, scrollableElement);
		return verifyElement(element);
	}

	// Click on an element till presence of some other element.
	public boolean clickUntilPresenceOfElement(WebElement clickableElement, WebElement element) {
		int timer = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500));
		while (timer++ != 10) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(clickableElement));
				clickableElement.click();
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			} catch (Exception ignored) {
			}
		}
		return false;
	}

	public boolean clickUntilPresenceOfElementLocator(WebElement clickableElement, By locator) {
		int timer = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500));
		while (timer++ != 10) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(clickableElement));
				clickableElement.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				return true;
			} catch (Exception ignored) {
			}
		}
		return false;
	}

	public WebElement findAndVerifyElement(WebElement element, By locator) {
		waitUntillLoadedPage();
		try {
			return element.findElement(locator);
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement findAndVerifyElement(WebDriver driver, By locator) {
		waitUntillLoadedPage();
		try {
			return driver.findElement(locator);
		} catch (Exception e) {
			return null;
		}
	}

	public List<WebElement> findAndVerifyElements(WebElement element, By locator) {
		waitUntillLoadedPage();
		try {
			return element.findElements(locator);
		} catch (Exception e) {
			return null;
		}
	}

	public List<WebElement> findAndVerifyElements(WebDriver driver, By locator) {
		waitUntillLoadedPage();
		try {
			return driver.findElements(locator);
		} catch (Exception e) {
			return null;
		}
	}

	public static void sleep(int durationInMillis) {
		try {
			Thread.sleep(durationInMillis);
		} catch (Exception ignored) {
		}
	}

	// Highlights an element in red.
	public void highlightElement(WebElement element) {
		jExecutor.executeScript("arguments[0].style.border='4px solid red'", element);
		sleep(100);
	}

	public void undoHighlightElement(WebElement element) {
		jExecutor.executeScript("arguments[0].style.border='0px solid red'", element);
	}

	// Takes screenshot and returns the path where it is saved.
	public static String takeScreenshot(WebDriver driver, String fileName) {

		Path screenshotSavePath = Paths.get(System.getProperty("user.home")
				+ "/eclipse-workspace/FindingInterestHackathon/src/test/resources/Screenshots/" + fileName + ".png");

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);

		try {
			Files.move(screenShot.toPath(), screenshotSavePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotSavePath.toString();
	}
}
