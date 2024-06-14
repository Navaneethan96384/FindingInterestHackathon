package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.TimePeriod;

public class LoanCalculatorPage {
	@FindBy(xpath = "//li[@id='emi-calc']")
	private WebElement emiCalculatorTabElement; 
	
	@FindBy(xpath = "//li[@id='loan-amount-calc']")
	private WebElement loanAmountCalculatorTabElement;
	
	@FindBy(xpath = "//li[@id='loan-tenure-calc']")
	private WebElement loanTenureCalculatorTabElement;
	
	@FindBy(xpath = "//input[@id='loanamount']")
	private WebElement loanAmountInputElement;
	
	@FindBy(xpath = "//div[@id='loanamountslider']//span")
	private WebElement loanAmountSliderElement;
	
	@FindBy(xpath = "//input[@id='loaninterest']")
	private WebElement loanInterestInputElement;
	
	@FindBy(xpath = "//div[@id='loaninterestslider']//span")
	private WebElement loanInterestSliderElement;
	
	@FindBy(xpath = "//input[@id='loanterm']")
	private WebElement loanTenureInputElement;
	
	@FindBy(xpath = "//div[@id='loantermslider']//span")
	private WebElement loanTenureSliderElement;
	
	@FindBy(xpath = "//input[@id='loanfees']")
	private WebElement loanFeesInputElement;
	
	@FindBy(xpath = "//div[@id='loanfeesslider']//span")
	private WebElement loanFeesSliderElement;
	
	@FindBy(xpath = "//input[@id='loanemi']")
	private WebElement emiInputElement;
	
	@FindBy(xpath = "//div[@id='loanemislider']//span")
	private WebElement emiSliderElement;
	
	
	private WebDriver driver;
	
	public LoanCalculatorPage(WebDriver driver) {
		this.driver = driver;
		loadElements();
	}
	
	private void loadElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	public Boolean setLoanAmountOnInput(int amount)
	{
		return null;
	}
	
	public Integer getLoanAmountFromSlider()
	{
		return null;
	}
	
	public Boolean setLoanInterestOnInput(float rate)
	{
		return null;
	}
	
	public Float getLoanInterestFromSlider()
	{
		return null;
	}
	
	public Boolean setLoanTenureOnInput(int duration, TimePeriod timePeriod)
	{
		return null;
	}
	
	public Integer getLoanTenureFromSlider(TimePeriod timePeriod)
	{
		return null;
	}
	
	public Boolean setLoanFeesOnInput(int amount)
	{
		return null;
	}
	
	public Integer getLoanFeesFromSlider()
	{
		return null;
	}
	
	public Boolean setEmiOnInput(int amount)
	{
		return null;
	}
	
	public Integer getEmiFromSlider()
	{
		return null;
	}
}
