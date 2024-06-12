package selenium;

import org.testng.annotations.Test;

public class EmiCalculatorTest {
	@Test
	public void test()
	{
		new EmiCalculatorPage().perform();
	}
}
