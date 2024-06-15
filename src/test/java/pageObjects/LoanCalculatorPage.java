package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumUtils.ElementUtil;
import utils.TimePeriod;

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

	@FindAll(@FindBy(xpath = "//div[@id='loanamountsteps']/span/span[@class='marker']"))
	private List<WebElement> loanAmountStepElements;

	@FindBy(xpath = "//input[@id='loaninterest']")
	private WebElement loanInterestInputElement;

	@FindBy(xpath = "//div[@id='loaninterestslider']//span")
	private WebElement loanInterestSliderElement;

	@FindAll(@FindBy(xpath = "//div[@id='loanintereststeps']/span/span[@class='marker']"))
	private List<WebElement> loanInterestStepElements;

	@FindBy(xpath = "//input[@id='loanterm']")
	private WebElement loanTenureInputElement;

	@FindBy(xpath = "//div[@id='loantermslider']//span")
	private WebElement loanTenureSliderElement;

	@FindAll(@FindBy(xpath = "//div[@id='loantermsteps']/span/span[@class='marker']"))
	private List<WebElement> loanTenureStepElements;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Yr ']")
	private WebElement loanTenureYearToggleElement;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']//label[text()='Mo ']")
	private WebElement loanTenureMonthToggleElement;

	@FindBy(xpath = "//input[@id='loanfees']")
	private WebElement loanFeesInputElement;

	@FindBy(xpath = "//div[@id='loanfeesslider']//span")
	private WebElement loanFeesSliderElement;

	@FindAll(@FindBy(xpath = "//div[@id='loanfeessteps']/span/span[@class='marker']"))
	private List<WebElement> loanFeesStepElements;

	@FindBy(xpath = "//input[@id='loanemi']")
	private WebElement emiInputElement;

	@FindBy(xpath = "//div[@id='loanemislider']//span")
	private WebElement emiSliderElement;

	@FindAll(@FindBy(xpath = "//div[@id='loanemisteps']/span/span[@class='marker']"))
	private List<WebElement> emiStepElements;

	@FindBy(tagName = "html")
	private WebElement scrollableElement;

	private WebDriver driver;
	private ElementUtil elementUtil;

	public LoanCalculatorPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://emicalculator.net/loan-calculator");
		elementUtil = new ElementUtil(driver);
		elementUtil.waitUntillLoadedPage();
		loadElements();
	}

	private void loadElements() {
		PageFactory.initElements(driver, this);
	}

	public Boolean clickEmiCalculatorTab() {
		if (!elementUtil.scrollToAndVerifyElement(emiCalculatorTabElement, scrollableElement))
			return false;

		elementUtil.highlightElement(emiCalculatorTabElement);
		emiCalculatorTabElement.click();
		ElementUtil.takeScreenshot(driver, "clickEmiCalculatorTab");
		elementUtil.undoHighlightElement(emiCalculatorTabElement);
		return true;
	}

	public Boolean clickLoanAmountCalculatorTab() {
		if (!elementUtil.scrollToAndVerifyElement(loanAmountCalculatorTabElement, scrollableElement))
			return false;

		elementUtil.highlightElement(loanAmountCalculatorTabElement);
		loanAmountCalculatorTabElement.click();
		ElementUtil.takeScreenshot(driver, "clickLoanAmountCalculatorTab");
		elementUtil.undoHighlightElement(loanAmountCalculatorTabElement);
		return true;
	}

	public Boolean clickLoanTenureCalculatorTab() {
		if (!elementUtil.scrollToAndVerifyElement(loanTenureCalculatorTabElement, scrollableElement))
			return false;

		elementUtil.highlightElement(loanTenureCalculatorTabElement);
		loanTenureCalculatorTabElement.click();
		ElementUtil.takeScreenshot(driver, "clickLoanTenureCalculatorTab");
		elementUtil.undoHighlightElement(loanTenureCalculatorTabElement);
		return true;
	}

	public Boolean setLoanAmountOnInput(int amount) {
		return setValueOnInput(loanAmountInputElement, amount, "setLoanAmountOnInput");
	}

	public Float[] getLoanAmountFromSlider() {
		return getSliderDetails(loanAmountSliderElement, loanAmountStepElements, "getLoanAmountFromSlider");
	}

	public Boolean setLoanInterestOnInput(float rate) {
		return setValueOnInput(loanInterestInputElement, rate, "setLoanInterestOnInput");
	}

	public Float[] getLoanInterestFromSlider() {
		return getSliderDetails(loanInterestSliderElement, loanInterestStepElements, "getLoanInterestFromSlider");
	}

	public Boolean setLoanTenureOnInput(int duration, TimePeriod timePeriod) {
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

		return setValueOnInput(loanTenureInputElement, duration, "setLoanTenureOnInput");
	}

	public Float[] getLoanTenureFromSlider(TimePeriod timePeriod) {
		if (!elementUtil.scrollToAndVerifyElement(loanTenureInputElement, scrollableElement))
			return null;
		if (!elementUtil.verifyElement(loanTenureMonthToggleElement))
			return null;
		if (!elementUtil.verifyElement(loanTenureYearToggleElement))
			return null;

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

		return getSliderDetails(loanTenureSliderElement, loanTenureStepElements, "getLoanTenureFromSlider");
	}

	public Boolean setLoanFeesOnInput(int amount) {
		if (!elementUtil.scrollToAndVerifyElement(loanFeesInputElement, scrollableElement))
			return false;

		return setValueOnInput(loanFeesInputElement, amount, "setLoanFeesOnInput");
	}

	public Float[] getLoanFeesFromSlider() {
		return getSliderDetails(loanFeesSliderElement, loanFeesStepElements, "getLoanFeesFromSlider");
	}

	public Boolean setEmiOnInput(int amount) {
		if (!elementUtil.scrollToAndVerifyElement(emiInputElement, scrollableElement))
			return false;

		return setValueOnInput(emiInputElement, amount, "setEmiOnInput");
	}

	public Float[] getEmiFromSlider() {
		return getSliderDetails(emiSliderElement, emiStepElements, "getEmiFromSlider");
	}

	public Boolean setValueOnInput(WebElement inputElement, Number value, String methodName) {
		if (!elementUtil.scrollToAndVerifyElement(inputElement, scrollableElement))
			return false;

		elementUtil.highlightElement(inputElement);
		inputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(value), Keys.ENTER);
		ElementUtil.takeScreenshot(driver, methodName);
		elementUtil.undoHighlightElement(inputElement);
		return true;
	}

	public Float[] getSliderDetails(WebElement sliderElement, List<WebElement> stepElements, String methodName) {
		if (!elementUtil.scrollToAndVerifyElement(sliderElement, scrollableElement))
			return null;
		if (!elementUtil.verifyElement(stepElements.get(0)))
			return null;

		String sliderLeftBoundString, sliderRightBoundString;
		WebElement sliderLeftBoundElement = stepElements.get(0),
				sliderRightBoundElement = stepElements.get(stepElements.size() - 1);

		elementUtil.highlightElement(sliderLeftBoundElement);
		sliderLeftBoundString = sliderLeftBoundElement.getText().trim();
		elementUtil.undoHighlightElement(sliderLeftBoundElement);
		elementUtil.highlightElement(sliderRightBoundElement);
		sliderRightBoundString = sliderRightBoundElement.getText().trim();
		elementUtil.undoHighlightElement(sliderRightBoundElement);

		Float[] sliderBoundsFloats = { Float.parseFloat(sliderLeftBoundString.split("L")[0]),
				Float.parseFloat(sliderRightBoundString.split("L")[0]) };
		if (sliderRightBoundString.contains("L")) {
			sliderBoundsFloats[0] *= 100000;
			sliderBoundsFloats[1] *= 100000;
		}

		elementUtil.highlightElement(sliderElement);
		String slidePercentageString = sliderElement.getAttribute("style");
		ElementUtil.takeScreenshot(driver, methodName);
		elementUtil.undoHighlightElement(sliderElement);
		Float slidePercentage = Float.parseFloat(slidePercentageString.split("[: %]+")[1]);

		return new Float[] { sliderBoundsFloats[0], sliderBoundsFloats[1], slidePercentage };
	}
}
