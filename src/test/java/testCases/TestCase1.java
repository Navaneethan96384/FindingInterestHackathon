package testCases;

import seleniumUtils.DriverManager;
import pageObjects.*;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Utils.TimePeriod;

public class TestCase1 {
	
	@Test
	public void run()
	{
		WebDriver driver = DriverManager.initDriver("chrome");
		driver.get("https://emicalculator.net");
		EmiCalculatorPage emiCalculatorPage = new EmiCalculatorPage(driver);
		emiCalculatorPage.setHomeLoanAmount(1000000);
		emiCalculatorPage.setInterestRate(10.5f);
		emiCalculatorPage.setLoanTenure(2, TimePeriod.YEAR);	
		String[][] data = emiCalculatorPage.getMonthlyPaymentDetailsForYear(1);
		System.out.println(Arrays.toString(data));
	}
	
	//@Test
	public void run2()
	{
		WebDriver driver = DriverManager.initDriver("chrome");
		driver.get("https://emicalculator.net");
		EmiCalculatorPage emiCalculatorPage = new EmiCalculatorPage(driver);
		WebElement element1 = driver.findElement(By.xpath("//div[@id='emipaymenttable']/table/tbody/tr[contains(@class,'yearlypaymentdetails')][1]"));
		System.out.println(element1.getAttribute("class"));
		WebElement element2 = element1.findElement(By.xpath("//td[1]"));
		System.out.println(element2.getAttribute("id"));
	}
}
