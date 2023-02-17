package testng_course;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnJavaScriptExecutor {

	// why we need it => when you have any external script and you want to inject
	// the same you can use javascript
	// it's an interface which provides mechanism to execute JS through selenium
	// driver

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(getTitle());

		WebElement logo = driver.findElement(By.cssSelector("#nav-logo-sprites"));
		//performClickByID("nav-logo-sprites");
		//js.executeScript("document.getElementById('nav-logo-sprites').click()");
		refresh();
		Thread.sleep(10000);

		logo = driver.findElement(By.cssSelector("#nav-logo-sprites"));
		js.executeScript("arguments[0].click()", logo);
		Thread.sleep(5000);
		
		js.executeScript("prompt('Please Enter your Name')");
		Thread.sleep(5000);
		Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
		alert.sendKeys("Test");
		
		scrollIntoView(driver.findElement(By.cssSelector("#navBackToTop")));

		generateAlert("Welcome to Selenium");
		Thread.sleep(5000);
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).accept();

		
		
		
		js.executeScript("confirm('Are you sure?')");
		Thread.sleep(5000);
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).dismiss();

		WebElement signInLink = driver.findElement(By.cssSelector("#nav-tools * .nav-line-1-container"));
		Actions act = new Actions(driver);
		Action action = act.moveToElement(signInLink).build();
		action.perform();

		WebElement registerationLink = driver.findElement(By.cssSelector("#nav-flyout-ya-newCust>a"));
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(registerationLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//executeAsync();
		
		WebElement userNameTxt = driver.findElement(By.cssSelector("#ap_customer_name"));
		highlightElement(userNameTxt);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// set the text
		jsExecutor.executeScript("arguments[0].value='testuser'", userNameTxt);
		
		WebElement emailOrPhone = driver.findElement(By.cssSelector("#ap_email"));
		drawBorder(emailOrPhone);
		
		// get the text
		String text = (String) jsExecutor.executeScript("return arguments[0].value", userNameTxt);
		System.out.println(text);
		jsExecutor.executeScript("arguments[0].value=arguments[1]", emailOrPhone, "123245453");

		driver.findElement(By.cssSelector("#ap_password"));
		driver.findElement(By.cssSelector("#ap_password_check"));
		highlightElement(driver.findElement(By.cssSelector("#continue")));
		driver.findElement(By.cssSelector("#continue")).click();
		long endTime = System.currentTimeMillis();
		long totaltime = endTime-startTime;
		System.out.println(totaltime);
	}

	public static void highlightElement(WebElement element) throws InterruptedException {
		String backgroundColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 3; i++) {
			changeColor("rgb(0,110,0)", element);
			changeColor(backgroundColor, element);
		}
	}

	public static void changeColor(String color, WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor ='" + color + "'", element);
		Thread.sleep(2000);
	}

	public static void drawBorder(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border ='3px solid red'", element);
		Thread.sleep(2000);
	}

	public static String getTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public static void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public static void refresh() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}
	
	public static void scrollDownPage() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
	}
	
	public static void scrollIntoView(WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public static void executeAsync() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(function(){alert('Hello World');},4000);alert('JavaScript Learning');");
	}
	
	public static void performClickByID(String elementID) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('"+elementID+"').click()");
	}

}
