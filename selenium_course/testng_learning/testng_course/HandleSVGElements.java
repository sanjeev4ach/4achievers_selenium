package testng_course;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleSVGElements {

	static WebDriver driver;

	public void secondWMethod() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		driver = WebDriverManager.chromedriver().capabilities(options).create();
		driver.get("https://emicalculator.net/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		String verticalXpath = "//*[local-name()='svg']//*[name()='g' and @class='highcharts-series-group']//*[name()='rect']";
		List<WebElement> list = driver.findElements(By.xpath(verticalXpath));
		
		String textPath = "//*[local-name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']//*[name()='text']";
		
		Actions action = new Actions(driver);
		for(WebElement temp:list) {
			action.moveToElement(temp).perform();
			Thread.sleep(500);
			System.out.println(driver.findElement(By.xpath(textPath)).getText());
		}
		
	}
	
	public void firstMethod() {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		driver = WebDriverManager.chromedriver().capabilities(options).create();
		driver.get(
				"https://www.google.com/search?q=covid+cases+in+india&rlz=1C1GCEU_enIN1041IN1041&oq=covid+cases+in+&aqs=chrome.0.0i433i512j69i57j0i433i512l2j0i512j0i10i433j0i512l3j0i433i512.3065j0j15&sourceid=chrome&ie=UTF-8");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		List<WebElement> list = driver
				.findElements(By.xpath("//*[local-name()='svg' and @class='uch-psvg']//*[name()='rect']"));
		Actions action = new Actions(driver);
		for (WebElement temp : list) {
			action.moveToElement(temp).perform();
			System.out.println(driver.findElement(By.xpath("//div[@class='ExnoTd']")).getText());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		HandleSVGElements ob = new HandleSVGElements();
		ob.firstMethod();
		driver.quit();
		ob.secondWMethod();
		
		driver.quit();
	}
}
