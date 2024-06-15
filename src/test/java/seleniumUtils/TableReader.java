package seleniumUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableReader {

	public static String[][] readData(List<WebElement> tableRows, WebDriver driver) {
		ElementUtil elementUtil = new ElementUtil(driver);
		WebElement firstRowElement = tableRows.get(0);
		List<WebElement> firstRowCellElements = elementUtil.findAndVerifyElements(firstRowElement,
				By.xpath("./td | ./th"));

		String[][] tableData = new String[tableRows.size()][firstRowCellElements.size()];

		for (int i = 0; i < tableRows.size(); i++) {
			WebElement tableRowElement = tableRows.get(i);
			List<WebElement> rowCellElements = elementUtil.findAndVerifyElements(tableRowElement,
					By.xpath("./td | ./th"));
			for (int j = 0; j < rowCellElements.size(); j++) {
				tableData[i][j] = rowCellElements.get(j).getText();
			}
		}
		return tableData;
	}
}
