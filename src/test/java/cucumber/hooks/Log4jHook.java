package cucumber.hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.LogUtil;

public class Log4jHook {
	@BeforeAll
	public static void setUp()
	{
		LogUtil.initializeLogger();
	}
	
	@Before
	public static void logScenario(Scenario scenario)
	{
		LogUtil.info("Executing Scenario: " + scenario.getName());
	}
	
	@AfterStep
	public static void logScenarioStep(Scenario scenario)
	{
		if(scenario.isFailed())
			LogUtil.error("Error: " + scenario.getName() + ", Step line at " + scenario.getLine());
		else
			LogUtil.info("Step Success: " + scenario.getName() + ", Step line at " + scenario.getLine());
	}
}
