package selenium_learning;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDriverMethods {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C://New folder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		/*
		 * driver.get("http://www.google.com"); driver.navigate().back();
		 */
		
		
		driver.navigate().to("http://www.google.com");
		driver.navigate().to("http://www.flipkart.com");
		driver.navigate().to("http://www.amazon.com");
		System.out.println("Moving back..... ");
		
		Set<Cookie> cookiesSet = driver.manage().getCookies();
		cookiesSet.size();
		driver.manage().deleteAllCookies();
		cookiesSet = driver.manage().getCookies();
		cookiesSet.size();
		driver.navigate().back();
		System.out.println("On Flipkart Page...... ");
		driver.navigate().back();
		System.out.println("On Google Page...... ");
		driver.navigate().forward();
		System.out.println("On Flipkart Page...... ");
		
		String actualtitle = driver.getTitle();
		System.out.println(actualtitle);
		String expectedTitle = "online Shopping";
		
		// validation point 
		if(actualtitle.toLowerCase().contains(expectedTitle))
			System.out.println("Test Passed....");
		
		if(actualtitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Test Passed....");
		}
		else
			System.out.println("Test Not Passed....");
		
		driver.quit();
	}

}
