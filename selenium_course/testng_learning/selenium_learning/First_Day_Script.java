package selenium_learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class First_Day_Script {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C://New folder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		String actualtitle = driver.getTitle();
		String expectedTitle = "google";
		
		// validation point 
		if(actualtitle.toLowerCase().equals(expectedTitle))
			System.out.println("Test Passed....");
		
		if(actualtitle.equalsIgnoreCase("google")) {
			System.out.println("Test Passed....");
		}
		else
			System.out.println("Test Not Passed....");
		
		
		System.out.println("Setting chrome Driver version 2..... ");
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();
		driver1.get("http://www.flipkart.com");
	}

}
