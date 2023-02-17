package selenium_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Locators_Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://way2automation.com/way2auto_jquery/index.php");
		// Name 
		
		driver.findElement(By.name("name")).sendKeys("Dinesh Jha");
		driver.findElement(By.name("phone")).sendKeys("1234556789");
		
		Select temp = new Select(driver.findElement(By.xpath("//select[@name='country']")));
		temp.selectByIndex(1);
		
		// className
		//driver.findElement(By.className("button")).click();
		
		driver.findElement(By.xpath("//div[@id='load_box']/form[@id='load_form']/descendant::input[8]")).click();
		
		driver.findElement(By.cssSelector(""));
		
		
	}

}
