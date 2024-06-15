package cucumber.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;

import cucumber.runners.TestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.EmiCalculatorPage;
import seleniumUtils.DriverFactory;
import utils.PropertiesReader;
import utils.TimePeriod;

public class CalculateCarLoanEmiSteps {
	WebDriver driver;
	EmiCalculatorPage emiCalculatorPage;

	@Given("the user is on the emi_calculator page using chrome")
	public void the_user_is_on_the_emi_calculator_page_using_chrome() throws Exception {

		driver = DriverFactory.createDriver("chrome");
		driver.get(PropertiesReader.readProperty("emicalculator.url"));
		emiCalculatorPage = new EmiCalculatorPage(driver);
	}

	@Given("the user is on the emi_calculator page using edge")
	public void the_user_is_on_the_emi_calculator_page_using_edge() throws Exception {

		driver = DriverFactory.createDriver("edge");
		driver.get(PropertiesReader.readProperty("emicalculator.url"));
		emiCalculatorPage = new EmiCalculatorPage(driver);
	}

	@When("the user clicks on the car_loan tab")
	public void the_user_clicks_on_the_car_loan_tab() {
			assertTrue(emiCalculatorPage.clickCarLoanTab());
	}

	@When("the user enters the car loan amount {int}")
	public void the_user_enters_the_car_loan_amount(Integer amount) {
		assertTrue(emiCalculatorPage.setCarLoanAmount(amount));
	}

	@When("the user enters the car loan interest {float}")
	public void the_user_enters_the_loan_interest(Float rate) {
		assertTrue(emiCalculatorPage.setInterestRate(rate));
	}

	@When("the user enters the car loan tenure {int} year")
	public void the_user_enters_the_loan_tenure_year(Integer duration) {
		assertTrue(emiCalculatorPage.setLoanTenure(duration, TimePeriod.YEAR));
	}

	@When("the user enters the car loan tenure {int} month")
	public void the_user_enters_the_loan_tenure_month(Integer duration) {
		assertTrue(emiCalculatorPage.setLoanTenure(duration, TimePeriod.MONTH));
	}

	@Then("I verify and display the car emi details")
	public void I_verify_and_display_the_emi_details() {
		emiCalculatorPage.getEmiDetails();
	}
	
	@And("the user navigates to home_loan_calculator page")
	public void the_user_navigates_to_home_loan_calculator_page()
	{
		assertTrue(emiCalculatorPage.clickHomeLoanEmiCalculatorMenuItem());
	}

	@After
	public void afterScenario() {
		if(driver != null)
		driver.close();
	}
}
