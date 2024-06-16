package cucumber.hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utils.PropertiesReader;

public class ScreenshotHook {
	public static Path mostRecentScreenshotTakenPath;

	@BeforeAll
	public static void cleanUp() {
		File directory = new File(PropertiesReader.readProperty("screenshots.path"));
		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
	}

	@BeforeStep
	public void beforeStep() {
		mostRecentScreenshotTakenPath = null;
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (mostRecentScreenshotTakenPath == null)
			return;

		try {
			byte[] fileBytes = Files.readAllBytes(mostRecentScreenshotTakenPath);
			scenario.attach(fileBytes, "image/png", mostRecentScreenshotTakenPath.getFileName().toString());
		} catch (IOException ignored) {
		}
	}
}
