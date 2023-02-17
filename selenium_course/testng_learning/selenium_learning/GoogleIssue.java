package selenium_learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleIssue {

	public static void main(String[] args) {
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.google.com/gmail/about/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[@data-action='sign in']/following-sibling::a")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.titleContains("Create your Google Account"));

		driver.findElement(By.cssSelector("#firstName")).sendKeys("TEstRohit");
		driver.findElement(By.cssSelector("#lastName")).sendKeys("TEstRohit");
		driver.findElement(By.cssSelector("#username")).sendKeys("TEst12Rohit");
		driver.findElement(By.cssSelector("#passwd *.whsOnd.zHQkBf")).sendKeys("test@12#12");
		driver.findElement(By.cssSelector("#confirm-passwd *.whsOnd.zHQkBf")).sendKeys("test@12#12");
		
		driver.findElement(By.cssSelector(".VfPpkd-muHVFf-bMcfAe")).click();
		
		/*
		 * WebElement checkbox1 =
		 * driver.findElement(By.cssSelector(".VfPpkd-muHVFf-bMcfAe"));
		 * checkbox1.click();
		 */
		//WebElement checkBox = driver.findElement(By.cssSelector(".enBDyd "));
		/*
		 * if(!checkbox1.isSelected()) checkbox1.click();
		 */
		
		driver.findElement(By.cssSelector("#accountDetailsNext")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.textToBe(By.cssSelector(".ahT6S >span"), "Verifying your phone number"));
		driver.findElement(By.cssSelector("#phoneNumberId")).sendKeys("1234567890");
		driver.close();
	}

}
