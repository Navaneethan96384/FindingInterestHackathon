package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumUtils.ElementUtil;
import seleniumUtils.TableReader;
import utils.TimePeriod;

public class HomeLoanEmiCalculatorPage {
	@FindBy(xpath = "//input[@id='homeprice']")
	private WebElement homeValueInputElement;

	@FindBy(xpath = "//input[@id='downpayment']")
	private WebElement downPaymentInputElement;

	@FindBy(xpath = "//input[@id='homeloaninterest']")
	private WebElement interestRateInputElement;

	@FindBy(xpath = "//input[@id='homeloanterm']")
	private WebElement loanTenureInputElement;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Yr ']")
	private WebElement loanTenureYearToggleElement;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Mo ']")
	private WebElement loanTenureMonthToggleElement;

	@FindAll(@FindBy(xpath = "//tr[contains(@class,'yearlypaymentdetails')] | //table[@class='noextras']/tbody/tr[1]"))
	private List<WebElement> yearOnYearTableRowElements;

	@FindBy(xpath = "//button[@class='navbar-toggler']")
	private WebElement navBarTogglerElement;

	@FindBy(xpath = "//a[@title='Calculators']")
	private WebElement calculatorMenuElement;

	@FindBy(xpath = "//a[@title='Calculators']/following-sibling::ul//*[contains(text(),'Loan Calculator')]")
	private WebElement loanCalculatorMenuItemElement;

	@FindBy(tagName = "html")
	private WebElement scrollableElement;

	private WebDriver driver;
	private ElementUtil elementUtil;

	public HomeLoanEmiCalculatorPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://emicalculator.net/home-loan-emi-calculator");
		elementUtil = new ElementUtil(driver);
		elementUtil.waitUntillLoadedPage();
		loadElements();
	}

	private void loadElements() {
		PageFactory.initElements(driver, this);
	}

	public Boolean setHomeValue(int amount) {
		if (!elementUtil.scrollToAndVerifyElement(homeValueInputElement, scrollableElement))
			return false;

		elementUtil.highlightElement(homeValueInputElement);
		homeValueInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(amount), Keys.ENTER);
		ElementUtil.takeScreenshot(driver, "setHomeValue");
		elementUtil.undoHighlightElement(homeValueInputElement);
		return true;
	}

	public Boolean setDownPaymentPercentage(float percent) {
		if (!elementUtil.scrollToAndVerifyElement(downPaymentInputElement, scrollableElement))
			return false;

		elementUtil.highlightElement(downPaymentInputElement);
		downPaymentInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(percent), Keys.ENTER);
		ElementUtil.takeScreenshot(driver, "setDownPaymentPercentage");
		elementUtil.undoHighlightElement(downPaymentInputElement);
		return true;
	}

	public Boolean setInterestRate(float rate) {
		if (!elementUtil.scrollToAndVerifyElement(interestRateInputElement, scrollableElement))
			return false;

		elementUtil.highlightElement(interestRateInputElement);
		interestRateInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(rate), Keys.ENTER);
		ElementUtil.takeScreenshot(driver, "setInterestRate");
		elementUtil.undoHighlightElement(interestRateInputElement);
		return true;
	}

	public Boolean setLoanTenure(int duration, TimePeriod timePeriod) {
		if (!elementUtil.scrollToAndVerifyElement(loanTenureInputElement, scrollableElement))
			return false;
		if (!elementUtil.verifyElement(loanTenureMonthToggleElement))
			return false;
		if (!elementUtil.verifyElement(loanTenureYearToggleElement))
			return false;

		if (timePeriod == TimePeriod.MONTH) {
			elementUtil.highlightElement(loanTenureMonthToggleElement);
			ElementUtil.takeScreenshot(driver, "setLoanTenurePeriod");
			loanTenureMonthToggleElement.click();
			elementUtil.undoHighlightElement(loanTenureMonthToggleElement);
		} else {
			elementUtil.highlightElement(loanTenureYearToggleElement);
			ElementUtil.takeScreenshot(driver, "setLoanTenurePeriod");
			loanTenureYearToggleElement.click();
			elementUtil.undoHighlightElement(loanTenureYearToggleElement);
		}

		elementUtil.highlightElement(loanTenureInputElement);
		loanTenureInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(duration), Keys.ENTER);
		ElementUtil.takeScreenshot(driver, "setLoanTenure");
		elementUtil.undoHighlightElement(loanTenureInputElement);
		return true;
	}

	public String[][] getYearOnYearTableData() {
		WebElement tableElement = yearOnYearTableRowElements.get(0)
				.findElement(By.xpath("./ancestor::div[@id='paymentschedule']"));
		if (!elementUtil.scrollToAndVerifyElement(tableElement, scrollableElement))
			return null;

		elementUtil.highlightElement(tableElement);
		String tableData[][] = TableReader.readData(yearOnYearTableRowElements, driver);
		ElementUtil.takeScreenshot(driver, "getYearOnYearTableData");
		elementUtil.undoHighlightElement(tableElement);
		return tableData;
	}

	public Boolean clickLoanCalculatorMenuItem() {
		if (!elementUtil.scrollToAndVerifyElement(calculatorMenuElement, scrollableElement)) {
			if (!elementUtil.scrollToAndVerifyElement(navBarTogglerElement, scrollableElement))
				return false;
			else {
				navBarTogglerElement.click();
				if (!elementUtil.verifyElement(calculatorMenuElement))
					return false;
			}
		}

		elementUtil.highlightElement(calculatorMenuElement);
		ElementUtil.takeScreenshot(driver, "clickCalculatorMenuElement");
		boolean clicked = elementUtil.clickUntilPresenceOfElement(calculatorMenuElement, loanCalculatorMenuItemElement);
		elementUtil.undoHighlightElement(calculatorMenuElement);
		if (!clicked)
			return false;
		elementUtil.highlightElement(loanCalculatorMenuItemElement);
		ElementUtil.takeScreenshot(driver, "clickHomeLoanEmiCalculator");
		loanCalculatorMenuItemElement.click();
		elementUtil.undoHighlightElement(loanCalculatorMenuItemElement);
		return true;
	}
}
