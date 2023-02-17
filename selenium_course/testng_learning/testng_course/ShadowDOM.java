package testng_course;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDOM {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://books-pwakit.appspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement input =(WebElement)jse.executeScript("return document.querySelector('body > book-app').shadowRoot.querySelector('#input')");
		
		jse.executeScript("arguments[0].value='Selenium'", input);
	}
}
