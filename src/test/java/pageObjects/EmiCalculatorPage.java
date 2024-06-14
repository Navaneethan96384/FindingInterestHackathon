package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.TimePeriod;
import seleniumUtils.ElementUtil;
import seleniumUtils.TableReader;

public class EmiCalculatorPage {

	@FindBy(xpath = "//a[text()='Car Loan']")
	private WebElement carLoanTabElement;

	@FindBy(xpath = "//input[@id='loanamount']")
	private WebElement loanAmountInputElement;

	@FindBy(xpath = "//input[@id='loaninterest']")
	private WebElement loanInterestInputElement;

	@FindBy(xpath = "//input[@id='loanterm']")
	private WebElement loanTenureInputElement;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Yr ']")
	private WebElement loanTenureYearToggleElement;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Mo ']")
	private WebElement loanTenureMonthToggleElement;

	@FindAll(@FindBy(xpath = "//div[@id='emipaymenttable']/table/tbody/tr[contains(@class,'yearlypaymentdetails')]"))
	private List<WebElement> yearlyPaymentTableRowElements;

	@FindBy(xpath = "//button[@class='navbar-toggler']")
	private WebElement navBarTogglerElement;

	@FindBy(xpath = "//a[@title='Calculators']")
	private WebElement calculatorMenuElement;

	@FindBy(xpath = "//a[@title='Calculators']/following-sibling::ul//*[contains(text(),'Home Loan EMI Calculator')]")
	private WebElement homeLoanEmiCalculatorMenuItemElement;

	@FindBy(tagName = "html")
	private WebElement scrollableElement;

	private WebDriver driver;
	private ElementUtil elementUtil;

	public EmiCalculatorPage(WebDriver driver) {
		this.driver = driver;
		loadElements();
		elementUtil = new ElementUtil(driver);
	}

	private void loadElements() {
		PageFactory.initElements(driver, this);
	}

	public Boolean setHomeLoanAmount(int amount) {
		if (!elementUtil.scrollToAndVerifyElement(loanAmountInputElement, scrollableElement))
			return false;

		loanAmountInputElement.sendKeys(String.valueOf(amount));
		return true;
	}

	public Boolean setInterestRate(float rate) {
		if (!elementUtil.scrollToAndVerifyElement(loanInterestInputElement, scrollableElement))
			return false;

		loanAmountInputElement.sendKeys(String.valueOf(rate));
		return true;
	}

	public Boolean setLoanTenure(int duration, TimePeriod timePeriod) {
		if (!elementUtil.scrollToAndVerifyElement(loanTenureInputElement, scrollableElement))
			return false;
		if (!elementUtil.verifyElement(loanTenureMonthToggleElement))
			return false;
		if (!elementUtil.verifyElement(loanTenureYearToggleElement))
			return false;

		if (timePeriod == TimePeriod.MONTH)
			loanTenureMonthToggleElement.click();
		else
			loanTenureYearToggleElement.click();
		loanAmountInputElement.sendKeys(String.valueOf(duration));
		return true;
	}

	public String[][] getMonthlyPaymentDetailsForYear(int year) {
		WebElement yearlyPaymentRowElement = yearlyPaymentTableRowElements.get(year - 1);
		System.out.println(yearlyPaymentRowElement);
		WebElement paymentYearToggleElement = elementUtil.findAndVerifyElement(yearlyPaymentRowElement,
				By.xpath("//td[contains(@class,'toggle')]"));
		if (paymentYearToggleElement == null) {
			System.out.println("Returning null");
			return null;
		}
		paymentYearToggleElement.click();
		List<WebElement> monthlyPaymentRowElements = elementUtil.findAndVerifyElements(yearlyPaymentRowElement,
				By.xpath("//following-sibling::tr[1]//tbody/tr"));
		return TableReader.readData(monthlyPaymentRowElements, driver);
	}
}
