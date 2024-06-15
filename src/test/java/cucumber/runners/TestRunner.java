package cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "cucumber.stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/CucumberTestReport.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
