package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.TimePeriod;

public class HomeLoanEmiCalculatorPage {
	@FindBy(xpath = "//input[@id='homeprice']")
	private WebElement homeValueInputElement;
	
	@FindBy(xpath = "//input[@id='downpayment']")
	private WebElement downPaymentInputElement;

	@FindBy(xpath = "//input[@id='homeloaninterest']")
	private WebElement interestRateInputElement;
	
	@FindBy(xpath = "//input[@id='homeloanterm']")
	private WebElement loanTenureInputElement;
	
	@FindAll(@FindBy(xpath = "//tr[contains(@class,'yearlypaymentdetails')] | //table[@class='noextras']/tbody/tr[1]"))
	private List<WebElement> yearOnYearTableRowElements;
	
	
	private WebDriver driver;
	
	public HomeLoanEmiCalculatorPage(WebDriver driver) {
		this.driver = driver;
		loadElements();
	}
	
	private void loadElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	public Boolean setHomeValue()
	{
		return null;
	}
	
	public Boolean setDownPaymentPercentage(float percent)
	{
		return null;
	}
	
	public Boolean setInterestRate(float rate)
	{
		return null;
	}
	
	public Boolean setLoanTenure(int duration, TimePeriod timePeriod)
	{
		return null;
	}
	
	public String[][] getYearOnYearTableData()
	{
		return null;
	}
}
