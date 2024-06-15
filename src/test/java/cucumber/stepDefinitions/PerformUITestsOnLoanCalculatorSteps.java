package cucumber.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.EmiCalculatorPage;
import pageObjects.LoanCalculatorPage;
import seleniumUtils.DriverFactory;
import utils.PropertiesReader;
import utils.TimePeriod;

public class PerformUITestsOnLoanCalculatorSteps {
	WebDriver driver;
	LoanCalculatorPage loanCalculatorPage;

	@Given("the user is on the loan_calculator page using chrome")
	public void the_user_is_on_the_loan_calculator_page_using_chrome() throws Exception {

		driver = DriverFactory.createDriver("chrome");
		driver.get(PropertiesReader.readProperty("loancalculator.url"));
		loanCalculatorPage = new LoanCalculatorPage(driver);
	}

	@Given("the user is on the loan_calculator page using edge")
	public void the_user_is_on_the_loan_calculator_page_using_edge() throws Exception {

		driver = DriverFactory.createDriver("edge");
		driver.get(PropertiesReader.readProperty("loancalculator.url"));
		loanCalculatorPage = new LoanCalculatorPage(driver);
	}

	@And("the user clicks on the emi_calculator tab")
	public void the_user_clicks_on_the_emi_calculator_tab() {
		assertTrue(loanCalculatorPage.clickEmiCalculatorTab());
	}

	@When("the user on emi_calculator tab enters the loan amount {int}")
	public void the_user_on_emi_calculator_tab_enters_the_loan_amount(Integer amount) {
		assertTrue(loanCalculatorPage.setLoanAmountOnInput(amount));
	}

	@When("the user on emi_calculator tab enters the loan interest {float}")
	public void the_user_on_emi_calculator_tab_enters_the_loan_interest(Float rate) {
		assertTrue(loanCalculatorPage.setLoanInterestOnInput(rate));
	}

	@When("the user on emi_calculator tab enters the loan tenure {int} year")
	public void the_user_on_emi_calculator_tab_enters_the_loan_tenure_year(Integer duration) {
		assertTrue(loanCalculatorPage.setLoanTenureOnInput(duration, TimePeriod.YEAR));
	}

	@When("the user on emi_calculator tab enters the loan tenure {int} month")
	public void the_user_on_emi_calculator_tab_enters_the_loan_tenure_month(Integer duration) {
		assertTrue(loanCalculatorPage.setLoanTenureOnInput(duration, TimePeriod.MONTH));
	}
	
	@When("the user on emi_calculator tab enters the loan fees {int}")
	public void the_user_on_emi_calculator_tab_enters_the_loan_fees(Integer amount) {
		assertTrue(loanCalculatorPage.setLoanFeesOnInput(amount));
	}

	@Then("I verify the loan amount ui on emi_calculator tab")
	public void I_verify_the_loan_amount_ui_on_emi_calculator_tab() {
		loanCalculatorPage.getLoanAmountFromSlider();
	}
	
	@Then("I verify the loan interest ui on emi_calculator tab")
	public void I_verify_the_loan_interest_ui_on_emi_calculator_tab() {
		loanCalculatorPage.getLoanInterestFromSlider();
	}
	
	@Then("I verify the year loan tenure ui on emi_calculator tab")
	public void I_verify_the_year_loan_tenure_ui_on_emi_calculator_tab() {
		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.YEAR);
	}
	
	@Then("I verify the month loan tenure ui on emi_calculator tab")
	public void I_verify_the_month_loan_tenure_ui_on_emi_calculator_tab() {
		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.MONTH);
	}
	
	@Then("I verify the loan fees ui on emi_calculator tab")
	public void I_verify_the_loan_fees_ui_on_emi_calculator_tab() {
		loanCalculatorPage.getLoanFeesFromSlider();
	}
	
	
	
	@Given("the user clicks on the loan_amount_calculator tab")
	public void the_user_clicks_on_the_loan_amount_calculator_tab() {
		assertTrue(loanCalculatorPage.clickLoanAmountCalculatorTab());
	}

	@When("the user on loan_amount_calculator tab enters the emi amount {int}")
	public void the_user_on_loan_amount_calculator_tab_enters_the_emi_amount(Integer amount) {
		assertTrue(loanCalculatorPage.setEmiOnInput(amount));
	}

	@When("the user on loan_amount_calculator tab enters the loan interest {float}")
	public void the_user_on_loan_amount_calculator_tab_enters_the_loan_interest(Float rate) {
		assertTrue(loanCalculatorPage.setLoanInterestOnInput(rate));
	}

	@When("the user on loan_amount_calculator tab enters the loan tenure {int} year")
	public void the_user_on_loan_amount_calculator_tab_enters_the_loan_tenure_year(Integer duration) {
		assertTrue(loanCalculatorPage.setLoanTenureOnInput(duration, TimePeriod.YEAR));
	}

	@When("the user on loan_amount_calculator tab enters the loan tenure {int} month")
	public void the_user_on_loan_amount_calculator_tab_enters_the_loan_tenure_month(Integer duration) {
		assertTrue(loanCalculatorPage.setLoanTenureOnInput(duration, TimePeriod.MONTH));
	}
	
	@When("the user on loan_amount_calculator tab enters the loan fees {int}")
	public void the_user_on_loan_amount_calculator_tab_enters_the_loan_fees(Integer amount) {
		assertTrue(loanCalculatorPage.setLoanFeesOnInput(amount));
	}

	@Then("I verify the loan emi ui on loan_amount_calculator tab")
	public void I_verify_the_loan_emi_ui_on_loan_amount_calculator_tab() {
		loanCalculatorPage.getEmiFromSlider();
	}
	
	@Then("I verify the loan interest ui on loan_amount_calculator tab")
	public void I_verify_the_loan_interest_ui_on_loan_amount_calculator_tab() {
		loanCalculatorPage.getLoanInterestFromSlider();
	}
	
	@Then("I verify the year loan tenure ui on loan_amount_calculator tab")
	public void I_verify_the_year_loan_tenure_ui_on_loan_amount_calculator_tab() {
		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.YEAR);
	}
	
	@Then("I verify the month loan tenure ui on loan_amount_calculator tab")
	public void I_verify_the_month_loan_tenure_ui_on_loan_amount_calculator_tab() {
		loanCalculatorPage.getLoanTenureFromSlider(TimePeriod.MONTH);
	}
	
	@Then("I verify the loan fees ui on loan_amount_calculator tab")
	public void I_verify_the_loan_fees_ui_on_loan_amount_calculator_tab() {
		loanCalculatorPage.getLoanFeesFromSlider();
	}
	
	
	
	@Given("the user clicks on the loan_tenure_calculator tab")
	public void the_user_clicks_on_the_loan_tenure_calculator_tab() {
		assertTrue(loanCalculatorPage.clickLoanTenureCalculatorTab());
	}

	@When("the user on loan_tenure_calculator tab enters the loan amount {int}")
	public void the_user_on_loan_tenure_calculator_tab_enters_the_loan_amount(Integer amount) {
		assertTrue(loanCalculatorPage.setLoanAmountOnInput(amount));
	}
	
	@When("the user on loan_tenure_calculator tab enters the emi amount {int}")
	public void the_user_on_loan_tenure_calculator_tab_enters_the_emi_amount(Integer amount) {
		assertTrue(loanCalculatorPage.setEmiOnInput(amount));
	}

	@When("the user on loan_tenure_calculator tab enters the loan interest {float}")
	public void the_user_on_loan_tenure_calculator_tab_enters_the_loan_interest(Float rate) {
		assertTrue(loanCalculatorPage.setLoanInterestOnInput(rate));
	}
	
	@When("the user on loan_tenure_calculator tab enters the loan fees {int}")
	public void the_user_on_loan_tenure_calculator_tab_enters_the_loan_fees(Integer amount) {
		assertTrue(loanCalculatorPage.setLoanFeesOnInput(amount));
	}
	
	@Then("I verify the loan amount ui on loan_tenure_calculator tab")
	public void I_verify_the_loan_amount_ui_on_loan_tenure_calculator_tab() {
		loanCalculatorPage.getLoanAmountFromSlider();
	}

	@Then("I verify the loan emi ui on loan_tenure_calculator tab")
	public void I_verify_the_loan_emi_ui_on_loan_tenure_calculator_tab() {
		loanCalculatorPage.getEmiFromSlider();
	}
	
	@Then("I verify the loan interest ui on loan_tenure_calculator tab")
	public void I_verify_the_loan_interest_ui_on_loan_tenure_calculator_tab() {
		loanCalculatorPage.getLoanInterestFromSlider();
	}
	
	@Then("I verify the loan fees ui on loan_tenure_calculator tab")
	public void I_verify_the_loan_fees_ui_on_loan_tenure_calculator_tab() {
		loanCalculatorPage.getLoanFeesFromSlider();
	}

	@After
	public void afterScenario() {
		if (driver != null)
			driver.close();
	}
}
