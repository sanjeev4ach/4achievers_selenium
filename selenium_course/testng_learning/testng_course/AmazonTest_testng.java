package testng_course;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest_testng {

	WebDriver driver;

	@BeforeMethod
	@Parameters({ "url","browser"})
	public void setUp(@Optional("http://www.amazon.com")String envUrl,@Optional("chrome") String browserName) {
		if (browserName.equalsIgnoreCase("chrome"))
			driver = WebDriverManager.chromedriver().create();
		else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get(envUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(priority = 2)
	public void doLogin() {
		System.out.println("Enter Username");
		System.out.println("Enter Password");
		System.out.println("Click on Login");

		System.out.println("Logged in");
		Assert.assertTrue(false);
	}

	@Test(priority = 1, description = "Verifying title",dependsOnMethods = "doLogin",alwaysRun = true)
	public void amazonTitleTest() {
		String title = driver.getTitle();
		System.out.println("Page title is - " + title);
		Assert.assertEquals(title, "Amazon.com. Spend less. Smile more.");
	}

	@Test(priority = 0)
	public void firstTest() {
		WebElement tempElement = driver
				.findElement(By.xpath("//span[contains(text(),'t Change')]/preceding-sibling::input"));
		tempElement.click();
		List<WebElement> links = driver.findElements(By.cssSelector("#nav-xshop>a"));
		links.get(2).click();
		WebElement temptext = driver.findElement(By.xpath("//*[text()='Shop the perfect gift card']"));

		new WebDriverWait(driver, Duration.ofSeconds(20)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(temptext));

		List<WebElement> links1 = driver.findElements(By.cssSelector("#nav-xshop>a"));
		links1.get(1).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
