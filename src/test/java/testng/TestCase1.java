//package testng;
//
//import org.openqa.selenium.WebDriver;
//
//import pageObjects.EmiCalculatorPage;
//import pageObjects.HomeLoanEmiCalculatorPage;
//import pageObjects.LoanCalculatorPage;
//import seleniumUtils.DriverFactory;
//import utils.ExcelUtil;
//import utils.TimePeriod;
//
//public class TestCase1 {
//	
////	@Test(priority = 1)
//	public void run()
//	{
//		WebDriver driver = DriverFactory.createDriver("chrome");
//		driver.get("https://emicalculator.net");
//		EmiCalculatorPage emiCalculatorPage = new EmiCalculatorPage(driver);
//		emiCalculatorPage.clickCarLoanTab();
//		emiCalculatorPage.setCarLoanAmount(500);
//		emiCalculatorPage.setInterestRate(10.5f);
//		emiCalculatorPage.setLoanTenure(2, TimePeriod.MONTH);	
//		String[] data = emiCalculatorPage.getEmiDetails();
//		emiCalculatorPage.clickHomeLoanEmiCalculatorMenuItem();
//		driver.quit();
//	}
//	
////	@Test(priority = 2)
//	public void run2()
//	{
//		WebDriver driver = DriverFactory.createDriver("chrome");
//		HomeLoanEmiCalculatorPage homeLoanEmiCalculatorPage = new HomeLoanEmiCalculatorPage(driver);
//		homeLoanEmiCalculatorPage.setHomeValue(30000000);
//		homeLoanEmiCalculatorPage.setDownPaymentPercentage(0);
//		homeLoanEmiCalculatorPage.setInterestRate(8.5f);
//		homeLoanEmiCalculatorPage.setLoanTenure(20, TimePeriod.YEAR);
//		String[][] dataStrings = homeLoanEmiCalculatorPage.getYearOnYearTableData();
//		ExcelUtil excelUtil = new ExcelUtil();
//		excelUtil.create("src/test/resources/yearOnYearPaymentDetails.xlsx", "yearOnYearPayment", dataStrings);
//		homeLoanEmiCalculatorPage.clickLoanCalculatorMenuItem();
//		driver.quit();	
//	}
//	
////	@Test(priority = 3)
//	public void run3()
//	{
//		WebDriver driver = DriverFactory.createDriver("chrome");
//		LoanCalculatorPage loanCalculatorPage = new LoanCalculatorPage(driver);
//		loanCalculatorPage.clickEmiCalculatorTab();
//		loanCalculatorPage.setLoanAmountOnInput(6500000);
//		loanCalculatorPage.setLoanInterestOnInput(11.95f);
//		loanCalculatorPage.setLoanTenureOnInput(5, TimePeriod.YEAR);
//		loanCalculatorPage.setLoanFeesOnInput(0);
//		loanCalculatorPage.getLoanAmountFromSlider();
//		loanCalculatorPage.getLoanInterestFromSlider();
//		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.YEAR);
//		loanCalculatorPage.getLoanFeesFromSlider();
//		
//		loanCalculatorPage.clickLoanAmountCalculatorTab();
//		loanCalculatorPage.setEmiOnInput(25000);
//		loanCalculatorPage.setLoanInterestOnInput(11.95f);
//		loanCalculatorPage.setLoanTenureOnInput(5, TimePeriod.YEAR);
//		loanCalculatorPage.setLoanFeesOnInput(0);
//		loanCalculatorPage.getEmiFromSlider();
//		loanCalculatorPage.getLoanInterestFromSlider();
//		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.YEAR);
//		loanCalculatorPage.getLoanFeesFromSlider();
//		
//		loanCalculatorPage.clickLoanTenureCalculatorTab();
//		loanCalculatorPage.setLoanAmountOnInput(6500000);
//		loanCalculatorPage.setEmiOnInput(50000);
//		loanCalculatorPage.setLoanInterestOnInput(11.95f);
//		loanCalculatorPage.setLoanFeesOnInput(0);
//		loanCalculatorPage.getLoanAmountFromSlider();
//		loanCalculatorPage.getEmiFromSlider();
//		loanCalculatorPage.getLoanInterestFromSlider();
//		loanCalculatorPage.getLoanFeesFromSlider();
//		driver.quit();
//	}
//}
