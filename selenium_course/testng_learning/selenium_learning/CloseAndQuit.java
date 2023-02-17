package selenium_learning;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloseAndQuit {
	
	WebDriver driver = null;
	
	public void handleIframe() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement iframe = driver.findElement(By.xpath("//span[@id='c_training_mode-error']/../div//iframe[@title='reCAPTCHA']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Assert.assertEquals(true, false);
	}
	
	public void invokeDriver() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.4achievers.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void enterFormDetails() {
		driver.findElement(By.cssSelector("#common_form>#name")).sendKeys("TestName");
		driver.findElement(By.cssSelector("#common_form>#phone")).sendKeys("125213512");
		driver.findElement(By.cssSelector("#common_form>#email")).sendKeys("test@test.com");
		Select option1 = new Select(driver.findElement(By.cssSelector("#common_form>#course")));
		option1.selectByIndex(1);
		Select option2 = new Select(driver.findElement(By.cssSelector("#common_form>div>#training_mode")));
		option2.selectByIndex(1);
		Select option3 = new Select(driver.findElement(By.cssSelector("#common_form>div>#location")));
		option3.selectByIndex(1);
		
	}
	
	public void handleCaptchaImage() {
		
	}
	
	public void doLogin(String username, String password) {
		
		
	}

	@Test
	public void testMethod() {
		CloseAndQuit obj = new CloseAndQuit();
		obj.invokeDriver();
		obj.enterFormDetails();
		obj.handleIframe();
		obj.doLogin("", "");
		obj.closeBrowser();
		
		/*
		 * // through key strokes String selectLinkOpeninNewTab =
		 * Keys.chord(Keys.CONTROL,Keys.ENTER);
		 * driver.findElement(By.xpath("//a[text()='Forgotten password?']")).sendKeys(
		 * selectLinkOpeninNewTab);
		 * 
		 * 
		 * // through JavaScript
		 * 
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("window.open()");
		 * 
		 * Set<String> windows = driver.getWindowHandles(); List<String> tempWindowsList
		 * = new ArrayList<>(windows);
		 * 
		 * String parentWindow = tempWindowsList.get(0); String childWindow1 =
		 * tempWindowsList.get(1); String childWindow2 = tempWindowsList.get(2);
		 * 
		 * driver.switchTo().window(childWindow1);
		 * System.out.println(driver.getTitle());
		 * 
		 * driver.switchTo().window(childWindow2);
		 * System.out.println(driver.getTitle());
		 * 
		 * driver.close();
		 * 
		 * driver.switchTo().window(parentWindow);
		 * System.out.println(driver.getTitle());
		 * 
		 * driver.quit();
		 * 
		 * // From Selenium 4 onwards
		 * 
		 * //driver.switchTo().newWindow(WindowType.TAB);
		 */
	}
	

}
