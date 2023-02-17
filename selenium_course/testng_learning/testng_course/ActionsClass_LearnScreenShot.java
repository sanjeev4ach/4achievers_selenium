package testng_course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass_LearnScreenShot {

	// https://www.toolsqa.com/selenium-webdriver/actions-class-in-selenium/
	// https://www.lambdatest.com/blog/what-is-actions-class-in-selenium/

	/**
	 * here is a method in util class to read CSV file -> task create a csv file and
	 * use that in dataprovider 2. Actions, Screenshot incorporate 3. Only use CSS
	 * Selector From Me to Everyone 10:01 PM click on inspect and open developer
	 * tools using actions class 4. USe Drag and drop method of Actions class in
	 * dragAndDrop()
	 * 
	 */
	WebDriver driver;
	private static final String DIR_LOCATION = System.getProperty("user.dir") + "/screenshots/";

	@DataProvider
	public Object[][] getDataInTwoDArray2() {
		Object[][] data = { { "Rohit", "Sharma", "Rohit.sharma@gmail.com" },
				{ "Virat", "Kohli", "Virat.Kohli@gmail.com" }, { "Sanjeev", "Agarwal", "Sanjeev.Agarwal@gmail.com" },
				{ "Rohit", "Sharma", "Rohit.sharma@gmail.com" } };
		return data;
	}

	// If you need to do mousehover, drag and drop, double click, right click

	@Test(dataProvider = "getDataInTwoDArray2", priority = 1, description = "Testing data provider annotation with Two D object array")
	public void testDataProviderOf2dArray(String firstname, String phoneNumber, String password) {
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// 1. mouse hover => moveToElement
		WebElement signInLink = driver.findElement(By.cssSelector("#nav-tools * .nav-line-1-container"));
		Actions act = new Actions(driver);
		act.moveToElement(signInLink).perform();
		WebElement registerationLink = driver.findElement(By.cssSelector("#nav-flyout-ya-newCust>a"));
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(registerationLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.cssSelector("#ap_customer_name")).sendKeys(firstname);
		driver.findElement(By.cssSelector("#ap_email")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("#ap_password")).sendKeys(password);
		driver.findElement(By.cssSelector("#ap_password_check")).sendKeys(password);
		takeScreenshot("Register_Page" + "_" + new Date().getSeconds() + ".png");
		driver.findElement(By.cssSelector("#continue")).click();
	}

	@Test(priority = 2)
	public void dragAndDrop() {
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.switchTo().frame(0);

		// 2. drag and drop
		WebElement source = driver.findElement(By.cssSelector("#draggable"));
		takeScreenShotofAnElement(source, "source.png");
		WebElement target = driver.findElement(By.cssSelector("#droppable"));
		Actions act = new Actions(driver);
		act.clickAndHold(source).moveToElement(target).build().perform();
	}

	@Test
	public void performRightClickOnGoogle() throws InterruptedException {
		driver = WebDriverManager.chromedriver().create();
		driver.get("http://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		WebElement sellLink = driver
				.findElement(By.xpath("//div[@id='nav-xshop']/a[text()='Sell']"));
		Actions act = new Actions(driver);
		act.contextClick(sellLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).
		sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		Thread.sleep(15000);
		System.out.println("Clicked");
	}

	@Test
	public void automateGoogleSearch() {
		driver = WebDriverManager.chromedriver().create();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		WebElement searchbox = driver.findElement(By.cssSelector(".YacQv+.gLFyf"));
		searchbox.sendKeys("Selenium");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='mkHrUc']//li")));

		List<WebElement> searchTextLists = driver.findElements(By.xpath("//div[@class='mkHrUc']//li"));
		for (WebElement temp : searchTextLists) {
			System.out.println(temp.getText());
			if (temp.getText().equalsIgnoreCase("selenium tutorial")) {
				temp.click();
				break;
			}
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public void takeScreenshot(String fileName) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(DIR_LOCATION + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void takeScreenShotofAnElement(WebElement element, String fileName) {
		File src = element.getScreenshotAs(OutputType.FILE);

		if (!new File(DIR_LOCATION).exists())
			new File(DIR_LOCATION).mkdir();
		File file = new File(DIR_LOCATION + fileName);
		if (!file.exists())
			try {
				file.createNewFile();
				FileHandler.copy(src, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
