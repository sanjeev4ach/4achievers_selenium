package testng_course;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;



public class PopUps {
	static WebDriver driver;
	
	public static void handleAuthPopUp() {
		driver = WebDriverManager.chromedriver().create();
		//auth pop ups
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		//http://username:password@test.com
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	}
	
	public static void enterTextInAlert() throws InterruptedException{
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("prompt('Please Enter your Name')");
		Thread.sleep(5000);
		Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
		alert.sendKeys("Test");
		System.out.println(alert.getText());
	}
	
	public static void main(String[] args) throws InterruptedException {
		handleAuthPopUp();
		enterTextInAlert();
	}

}
