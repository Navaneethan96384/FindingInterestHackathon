package cucumber.hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utils.LogUtil;
import utils.Utilities;

public class Log4jHook {
	
	private static String currentlyExecutingMethodString = null;
	
	@BeforeAll
	public static void setUp()
	{
		LogUtil.initializeLogger();
	}
	
	@Before
	public void logScenario(Scenario scenario)
	{
		LogUtil.info("Executing Scenario: " + scenario.getName());
	}
	
	@BeforeStep
	public static void clear()
	{
		currentlyExecutingMethodString = null;
	}
	
	public static void log()
	{
		currentlyExecutingMethodString = Utilities.getCurrentMethodName();
	}
	
	@AfterStep
	public static void logScenarioStep(Scenario scenario)
	{
		if(currentlyExecutingMethodString != null)
		if(scenario.isFailed())
			LogUtil.error("Error at step: " + currentlyExecutingMethodString + ", " + scenario.getStatus());
		else
			LogUtil.info("Step Success: " + currentlyExecutingMethodString + ", " + scenario.getStatus());
	}
}
