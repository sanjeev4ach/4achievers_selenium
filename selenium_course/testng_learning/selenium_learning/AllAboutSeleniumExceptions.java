package selenium_learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllAboutSeleniumExceptions {

	// Some element is not visible on the page
	// Page got refreshed
	// one unique id/dynamic id is always created with every webelement for the first time
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement tempElement = driver.findElement(By.xpath("//span[contains(text(),'t Change')]/preceding-sibling::input"));
		tempElement.click();
		List<WebElement> links = driver.findElements(By.cssSelector("#nav-xshop>a"));
		links.get(2).click();
		WebElement temptext = driver.findElement(By.xpath("//*[text()='Shop the perfect gift card']"));
		
		new WebDriverWait(driver, Duration.ofSeconds(20)).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.visibilityOf(temptext));
		
		List<WebElement> links1 = driver.findElements(By.cssSelector("#nav-xshop>a"));
		links1.get(1).click();

	}

}
