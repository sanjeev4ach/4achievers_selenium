package testng_course;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondTest extends BaseClass{
	
	
	@Test
	public void firstTest()
	{
		driver.get("http://www.amazon.com");
		BaseClass.createTest("firstTest");
		test.info("Verifying title");
		Assert.assertEquals(driver.getTitle(), "Amaaaazon");
	}

}
