package cucumber.stepDefinitions;



import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import pageObjects.EmiCalculatorPage;
import seleniumUtils.DriverFactory;
import utils.PropertiesReader;
import utils.TimePeriod;

public class CalculateCarLoanEmiSteps{
    WebDriver driver;
    EmiCalculatorPage emiCalculatorPage;

    @Given("the user is on the emicalculator page using chrome")
    public void the_user_is_on_the_emicalculator_page_using_chrome() {
    	
        driver = DriverFactory.createDriver("chrome");
        driver.get(PropertiesReader.readProperty("emicalculator.url"));
        emiCalculatorPage = new EmiCalculatorPage(driver);
    }
    
    @Given("the user is on the emicalculator page using edge")
    public void the_user_is_on_the_emicalculator_page_using_edge() {
    	
        driver = DriverFactory.createDriver("edge");
        driver.get(PropertiesReader.readProperty("emicalculator.url"));
        emiCalculatorPage = new EmiCalculatorPage(driver);
    }

    @When("the user clicks on the car loan tab")
    public void the_user_clicks_on_the_car_loan_tab() {
    	assertTrue(emiCalculatorPage.clickCarLoanTab());
    }

	@When("the user enters the car loan {int}")
    public void the_user_enters_the_car_loan(Integer amount) {
    	assertTrue(emiCalculatorPage.setCarLoanAmount(amount));
    }

    @When("the user enters the loan interest {float}")
    public void the_user_enters_the_loan_interest(Float rate) {
    	assertTrue(emiCalculatorPage.setInterestRate(rate));
    }
    
    @When("the user enters the loan tenure {int} year")
    public void the_user_enters_the_loan_tenure_year(Integer duration) {
        assertTrue(emiCalculatorPage.setLoanTenure(duration, TimePeriod.YEAR));
    }
    
    @When("the user enters the loan tenure {int} month")
    public void the_user_enters_the_loan_tenure_month(Integer duration) {
        assertTrue(emiCalculatorPage.setLoanTenure(duration, TimePeriod.MONTH));
    }

    @Then("I verify and display the emi details")
    public void I_verify_and_display_the_emi_details() {
    	emiCalculatorPage.getEmiDetails();
    }
}
