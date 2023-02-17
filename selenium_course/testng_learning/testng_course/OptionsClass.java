package testng_course;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExcelUtil;

public class OptionsClass {

	// https://www.guru99.com/chrome-options-desiredcapabilities.html
	// https://www.selenium.dev/documentation/webdriver/drivers/options/

	// https://www.guru99.com/firefox-profile-selenium-webdriver.html
	// https://www.selenium.dev/documentation/webdriver/browsers/firefox/

	// https://chromedriver.chromium.org/capabilities
	// https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions
	ChromeOptions options;
	WebDriver driver;

	@Test(dataProvider = "getTestData")
	public void fillRegistrationPage(Map<String, String> obj) throws MalformedURLException {
		String browserName = "chrome";
		if (browserName.equalsIgnoreCase("chrome")) {
			useChromeOptions();
			driver = WebDriverManager.chromedriver().capabilities(options).create();
		} else if (browserName.equalsIgnoreCase("firefox"))
			driver = WebDriverManager.firefoxdriver().create();
		driver.get("https://www.amazon.com");
		// driver.manage().window().maximize();
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
		listOfArgumentsInChromeOptions();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		return ExcelUtil.readExcelData("").iterator();
	}

	private void listOfArgumentsInChromeOptions() {

		options = new ChromeOptions();
		// start-maximized: Opens Chrome in maximize mode
		options.addArguments("start-maximized");
		// incognito: Opens Chrome in incognito mode
		options.addArguments("incognito");
		// headless: Opens Chrome in headless mode
		options.addArguments("headless");
		// disable-extensions: Disables existing extensions on Chrome browser
		options.addArguments("disable-extensions");
		// disable-popup-blocking: Disables pop-ups displayed on Chrome browser
		options.addArguments("disable-popup-blocking");
		// make-default-browser: Makes Chrome default browser
		options.addArguments("make-default-browser");
		// version: Prints chrome browser version
		options.addArguments("version");
		// disable-infobars: Prevents Chrome from displaying the notification ‘Chrome is
		// being controlled by automated software
		options.addArguments("disable-infobars");
	}

	private void useChromeOptions() {
		options = new ChromeOptions();
		options.setCapability("browserName", "chrome");
		// To add new extensions
		// options.addExtensions(new File("/path/extension.crx"));

		// To add binary path
		// options.setBinary(new File("/path/to/chrome"));

		// to accept untrust certificates
		options.setAcceptInsecureCerts(true);

		options.addArguments("start-maximized");

		// disable infoBars
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		// options.addArguments("disable-infobars");

		// options.addArguments("--headless");
		// options.addArguments("--disable-gpu");
	}
}
