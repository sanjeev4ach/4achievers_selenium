package testng_course;

import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DesiredCap {

	// it's a predefined class in Selenium webdriver
	// Use when we need to run cases on a remote machine then we use
	// desiredCapabilities

	WebDriver driver;

	@DataProvider
	public Object[][] getData() {
		Object[][] ob = new Object[2][1];
		ob[0][0] = "chrome";
		ob[1][0] = "edge";
		return ob;
	}

	@Test(dataProvider = "getData")
	public void setUp(String browserName) {

		DesiredCapabilities cap = new DesiredCapabilities();
		if (browserName.equalsIgnoreCase("chrome")) {
			//cap.setBrowserName("chrome");
			cap.setCapability("browserName", "chrome");
			cap.setPlatform(Platform.WINDOWS);
			//driver = new ChromeDriver(cap);
			driver = WebDriverManager.chromedriver().capabilities(cap).create();
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			cap.setCapability("browserName", "firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = WebDriverManager.firefoxdriver().capabilities(cap).create();
		} else if (browserName.equalsIgnoreCase("edge")) {
			cap.setCapability("browserName", "edge");
			cap.setPlatform(Platform.WINDOWS);
			driver = WebDriverManager.edgedriver().capabilities(cap).create();
		}
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	// it got deprecated in selenium 4, so we need to use BrowserOptions classes

}
