package cucumber.hooks;

import java.io.File;

import io.cucumber.java.BeforeAll;
import utils.LogUtil;
import utils.PropertiesReader;

public class CleanUpHook {
	@BeforeAll
	public static void cleanUp() {

		File screenshotsDirectory = new File(PropertiesReader.readProperty("screenshots.path"));
		if (screenshotsDirectory.isDirectory()) {
			for (File file : screenshotsDirectory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}

		File cucumberReportsDirectory = new File(PropertiesReader.readProperty("cucumber.reports.path"));
		if (cucumberReportsDirectory.isDirectory()) {
			for (File file : cucumberReportsDirectory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}

		File cucumberRetestReportsDirectory = new File(PropertiesReader.readProperty("cucumber.retest-reports.path"));
		if (cucumberRetestReportsDirectory.isDirectory()) {
			for (File file : cucumberRetestReportsDirectory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
	}
}
