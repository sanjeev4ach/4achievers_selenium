package testng_course;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessTesting {

	//https://www.guru99.com/selenium-with-htmlunit-driver-phantomjs.html
	// Use PhantomJS and Firefox
	// find Broken link of Amazon Home Page 
	
	public static WebDriver driver;
	static Logger logger = Logger.getLogger(HeadlessTesting.class);
	

	@Test
	public void initializeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless","--window-size=1920,1200");
		//options.setHeadless(true);
		logger.info("Invoking Chrome in headless mode");
		driver = WebDriverManager.chromedriver().capabilities(options).create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		logger.info("Enetring Google URL");
		driver.get("https://google.com");
		logger.info("Google is Opened");
		logger.info("Page title is - " + driver.getTitle());
		logger.info("Page title is - " + driver.getCurrentUrl());
		logger.info("quiting driver");
		driver.quit();
	}

	@Test
	public void initializeWithHTML() {

		// HTML Unit driver is not available after Selenium 3.x versions
		// You need to download this seperately
		// also called ghost driver or headless browser

		WebDriver driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.com");

		logger.info("On Landing page - " + driver.getTitle());
		driver.quit();
		logger.info("driver quits");
	}

}
