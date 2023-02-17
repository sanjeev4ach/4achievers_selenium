package selenium_learning;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAuthThroughSelenium {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		//http://username:password@test.com
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		

	}

}
