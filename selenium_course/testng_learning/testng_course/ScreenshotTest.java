package testng_course;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ScreenshotTest extends BaseClass{
	
	@Test
	public void openGoogle() {
		driver.get("http://www.google.com");
		BaseClass.createTest("OpenGoogle");
		test.info("Verifying title");
		Assert.assertEquals(driver.getTitle(), "Amazon","Verifying Title");	
	}
	
	@Test
	public void openAmazon()
	{
		driver.get("http://www.amazon.com");
		BaseClass.createTest("OpenAmazon");
		test.info("Verifying title");
		Assert.assertEquals(driver.getTitle(), "Amaaaazon");
	}

}
