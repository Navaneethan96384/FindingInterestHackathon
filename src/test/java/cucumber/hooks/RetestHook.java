package cucumber.hooks;

import java.util.HashMap;

import cucumber.stepDefinitions.CalculateCarLoanEmiSteps;
import io.cucumber.core.cli.Main;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class RetestHook {

	public static HashMap<String, Integer> retryLimit = new HashMap<>();
	public static final int MAX_RETRY_LIMIT = 3;

	@After
	public void retryIfScenarioFails(Scenario scenario) {
		if (scenario.isFailed()) {
			String failedScenarioTagName = scenario.getSourceTagNames().toArray(new String[0])[0];

			if (retryLimit.containsKey(failedScenarioTagName)) {
				Integer retriesLeft = retryLimit.get(failedScenarioTagName);
				if (retriesLeft == 0) {
					if (failedScenarioTagName.toLowerCase().contains("smoke")) {
						CalculateCarLoanEmiSteps.smokeTestStatus = false;
						System.out.println("  Smoke test failed, aborting further tests.");
					} else
						System.out.println("  Retest limit reached, Skipping scenario.");
					return;
				} else {
					retryLimit.put(failedScenarioTagName, retriesLeft - 1);
					retestScenario(failedScenarioTagName);
				}
			} else {
				retryLimit.put(failedScenarioTagName, MAX_RETRY_LIMIT - 1);
				retestScenario(failedScenarioTagName);
			}
		}
	}

	public void retestScenario(String failedScenarioTagName) {
		System.out.println("  Scenario with tag " + failedScenarioTagName + " failed, Attempting retest #"
				+ (3 - retryLimit.get(failedScenarioTagName)));

		String[] argv = { "--glue", "cucumber", "--tags", failedScenarioTagName, // Tag to rerun
				"--plugin",
				"html:target/cucumber-retest-reports/cucumber-retest-report-"
						+ failedScenarioTagName.replaceAll("@", "")
						+ (RetestHook.MAX_RETRY_LIMIT - retryLimit.get(failedScenarioTagName)) + ".html",
				"--plugin",
				"json:target/cucumber-retest-reports/CucumberRetestReport-" + failedScenarioTagName.replaceAll("@", "")
						+ (RetestHook.MAX_RETRY_LIMIT - retryLimit.get(failedScenarioTagName)) + ".json",
				"src/test/resources/features" };

		byte exitstatus = Main.run(argv, Thread.currentThread().getContextClassLoader());
		if (exitstatus == 0)
			System.out.println("  Retest succeeded for tag " + failedScenarioTagName);
	}
}
