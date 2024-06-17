package cucumber.hooks;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import io.cucumber.core.cli.Main;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import utils.ConsoleViewControl;

public class RetestHook {

	public static HashMap<String, Integer> retryLimit = new HashMap<>();
	public static Stack<String> retestScenariosStack = new Stack<>();
	public static final int MAX_RETRY_LIMIT = 3;

	@After
	public void retryIfScenarioFails(Scenario scenario) {
		if (scenario.isFailed()) {
			String scenarioTagName = scenario.getSourceTagNames().toArray(new String[0])[0];
			if (!retestScenariosStack.contains(scenarioTagName))
				retestScenariosStack.add(scenarioTagName);
		}
	}

	@AfterAll
	public static void run() {
		while (!retestScenariosStack.isEmpty()) {
			String failedScenarioTagName = retestScenariosStack.pop();

			if (retryLimit.containsKey(failedScenarioTagName)) {
				Integer retriesLeft = retryLimit.get(failedScenarioTagName);
				if (retriesLeft == 0) {
					ConsoleViewControl.enablePrintingInConsole();
					System.out.println("  Retest limit reached, Skipping scenario.");
					ConsoleViewControl.disablePrintingInConsole();
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

	public static void retestScenario(String failedScenarioTagName) {
		ConsoleViewControl.enablePrintingInConsole();
		System.out.println("  Scenario with tag " + failedScenarioTagName + " failed, Attempting retest #"
				+ (3 - retryLimit.get(failedScenarioTagName)));

		String[] argv = { "--glue", "cucumber", "--tags", failedScenarioTagName, // Tag to rerun
				"--plugin",
				"html:target/cucumber-retest-reports/cucumber-retest-report-"
						+ failedScenarioTagName.replaceAll("@", "") + "-attempt~"
						+ (RetestHook.MAX_RETRY_LIMIT - retryLimit.get(failedScenarioTagName)) + ".html",
				"--plugin",
				"json:target/cucumber-retest-reports/CucumberRetestReport-" + failedScenarioTagName.replaceAll("@", "")
						+ "-attempt~" + (RetestHook.MAX_RETRY_LIMIT - retryLimit.get(failedScenarioTagName)) + ".json",
				"src/test/resources/features" };

		ConsoleViewControl.disablePrintingInConsole();

		byte exitstatus = Main.run(argv, Thread.currentThread().getContextClassLoader());

		ConsoleViewControl.enablePrintingInConsole();
		if (exitstatus == 0)
			System.out.println("  Retest succeeded for tag " + failedScenarioTagName);
	}
}
