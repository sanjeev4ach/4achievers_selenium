package testng_course;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExcelUtil;


public class DataProviderTest {

	WebDriver driver;
	ChromeOptions options;

	@DataProvider
	public Object[][] getDataInSingleObjectArray() {
		//return new Object[][] {{"chrome"},{"firefox"}};
		Object[][] ob = { { "chrome"}, { "firefox" } };
		return ob;
	}

	@Test(dataProvider = "getDataInSingleObjectArray", priority = 0, description = "Testing data provider annotation with one D object array")
	public void testDataProvider(String browserName) {
		if (browserName.equalsIgnoreCase("chrome"))
			driver = WebDriverManager.chromedriver().create();
		else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	
	@DataProvider(indices = { 0, 3 })
	public Object[][] getDataInTwoDArray() {
		Object[][] data = new Object[4][3];
		data[0][0] = "Rohit";
		data[0][1] = "Sharma";
		data[0][2] = "Rohit.sharma@gmail.com";

		data[1][0] = "Virat";
		data[1][1] = "Kohli";
		data[1][2] = "Virat.Kohli@gmail.com";

		data[2][0] = "Sanjeev";
		data[2][1] = "Agarwal";
		data[2][2] = "Sanjeev.Agarwal@gmail.com";

		data[3][0] = "Test";
		data[3][1] = "test";
		data[3][2] = "Test.test@gmail.com";
		return data;
	}

	@DataProvider
	public Object[][] getDataInTwoDArray2() {
		Object[][] data = { { "Rohit", "Sharma", "Rohit.sharma@gmail.com" },
				{ "Virat", "Kohli", "Virat.Kohli@gmail.com" }, { "Sanjeev", "Agarwal", "Sanjeev.Agarwal@gmail.com" } };
		return data;
	}

	@Test(dataProvider = "getDataInTwoDArray", priority = 1, description = "Testing data provider annotation with Two D object array")
	public void testDataProviderOf2dArray(String firstname, String phoneNumber, String password) {
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
		driver.findElement(By.cssSelector("#continue")).click();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		return ExcelUtil.readExcelData("").iterator();
	}

	@Test(dataProvider = "getTestData", priority = 2, description = "Testing data provider annotation with one D object array")
	public void fillRegistrationPage(Map<String, String> obj) {
		String browserName = "chrome";
		if (browserName.equalsIgnoreCase("chrome")) {
			// useChromeOptions();
			// driver = WebDriverManager.chromedriver().capabilities(options).create();
			driver = WebDriverManager.chromedriver().create();
		} else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement signInLink = driver.findElement(By.cssSelector("#nav-tools * .nav-line-1-container"));
		Actions act = new Actions(driver);
		act.moveToElement(signInLink).perform();
		WebElement registerationLink = driver.findElement(By.cssSelector("#nav-flyout-ya-newCust>a"));
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(registerationLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.cssSelector("#ap_customer_name")).sendKeys(obj.get("firstname"));
		driver.findElement(By.cssSelector("#ap_email")).sendKeys(obj.get("phoneNumber"));
		driver.findElement(By.cssSelector("#ap_password")).sendKeys(obj.get("password"));
		driver.findElement(By.cssSelector("#ap_password_check")).sendKeys(obj.get("password"));
		driver.findElement(By.cssSelector("#continue")).click();
	}

	@DataProvider
	public Object[][] getTestData2ndWay() {
		return ExcelUtil.readExcelData();

	}

	@Test(dataProvider = "getTestData2ndWay", priority = 3, description = "Testing data provider annotation with one D object array")
	public void fillRegistrationPage2ndWay(Map<String, String> obj) {
		String browserName = "chrome";
		if (browserName.equalsIgnoreCase("chrome")) {
			// useChromeOptions();
			// driver = WebDriverManager.chromedriver().capabilities(options).create();
			driver = WebDriverManager.chromedriver().create();
		} else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement signInLink = driver.findElement(By.cssSelector("#nav-tools * .nav-line-1-container"));
		Actions act = new Actions(driver);
		act.moveToElement(signInLink).perform();
		WebElement registerationLink = driver.findElement(By.cssSelector("#nav-flyout-ya-newCust>a"));
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(registerationLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.cssSelector("#ap_customer_name")).sendKeys(obj.get("firstname"));
		driver.findElement(By.cssSelector("#ap_email")).sendKeys(obj.get("phoneNumber"));
		driver.findElement(By.cssSelector("#ap_password")).sendKeys(obj.get("password"));
		driver.findElement(By.cssSelector("#ap_password_check")).sendKeys(obj.get("password"));
		driver.findElement(By.cssSelector("#continue")).click();
	}
	
	@DataProvider(parallel = true)
	public Object[][] getTestDataWith2DArray() {
		return ExcelUtil.readExcelData1();

	}
	
	@Test(dataProvider = "getTestDataWith2DArray", priority = 3, description = "Testing data provider annotation with one D object array")
	public void fillRegistrationPageWith2DArray(String firstName,String phoneNumber,String password) {
		String browserName = "chrome";
		if (browserName.equalsIgnoreCase("chrome")) {
			// useChromeOptions();
			// driver = WebDriverManager.chromedriver().capabilities(options).create();
			driver = WebDriverManager.chromedriver().create();
		} else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement signInLink = driver.findElement(By.cssSelector("#nav-tools * .nav-line-1-container"));
		Actions act = new Actions(driver);
		act.moveToElement(signInLink).perform();
		WebElement registerationLink = driver.findElement(By.cssSelector("#nav-flyout-ya-newCust>a"));
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(registerationLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.cssSelector("#ap_customer_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#ap_email")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("#ap_password")).sendKeys(password);
		driver.findElement(By.cssSelector("#ap_password_check")).sendKeys(password);
		driver.findElement(By.cssSelector("#continue")).click();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	private void useChromeOptions() {
		options = new ChromeOptions();

		// To add new extensions
		options.addExtensions(new File("/path/extension.crx"));

		// To add binary path
		options.setBinary(new File("/path/to/chrome"));

		// to accept untrust certificates
		options.setAcceptInsecureCerts(true);

		// disable infoBars
		options.addArguments("disable-infobars");
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
	}

}
