package selenium_learning;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FifthDay_Waits {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("http://www.4achievers.com");
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// WebDriver Wait
		WebElement element = driver.findElement(By.xpath(""));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys("Testing Waits");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// 7, 14, 21, 28, 35
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		clickOn(driver, element,20);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// practical example of implicit Wait
		// Pass all the attributes from a single Element, preference will be given
		// according to the sequence
		
		driver.findElement(new ByAll(By.name("name12"), By.id("name12"), By.xpath("//form[@id='common_form']/input[1]"))).sendKeys("test123");
		driver.findElement(new ByIdOrName("name")).sendKeys("");
		driver.findElement(new ByChained(By.className("enquire-form-popup"), By.className("div-block-434"), By.className("form-block-5 w-form"), By.xpath(""))).sendKeys("");

		// Fluent Wait

		// Declare and initialise a fluent wait
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver);
		// Specify the timout of the wait
		fwait.withTimeout(Duration.ofMillis(50000));
		// Sepcify polling time
		fwait.pollingEvery(Duration.ofMillis(2500));
		// Specify what exceptions to ignore
		fwait.ignoring(NoSuchElementException.class);

		// This is how we specify the condition to wait on.
		// This is what we will explore more in this chapter
		fwait.until(ExpectedConditions.alertIsPresent());

	}

	public static void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

}
