package selenium_learning;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class HandleCaptcha {
	WebDriver driver = null;
	private static final String DIR_LOCATION = System.getProperty("user.dir") + "/screenshots/";

	public static void main(String[] args) {
		HandleCaptcha ob = new HandleCaptcha();
		ob.invokeDriverWithBonigracia();
		ob.closeForm();
		// ob.enterFormDetails();
		// ob.handleIframe();
		ob.clickOnContactUs();
		try {
			ob.fetchCaptchaText();
		} catch (IOException | TesseractException | InterruptedException e) {
			e.printStackTrace();
		}
		ob.closeBrowser();
	}

	public void closeForm() {
		WebElement closeImg = driver.findElement(By.cssSelector(".close-popup---brix"));
		clickOn(driver, closeImg, 15);

	}

	public void fetchCaptchaText() throws IOException, TesseractException, InterruptedException {
		WebElement captchaImgText = driver.findElement(By.xpath("//div[@class='captcha']//img"));
		File src = captchaImgText.getScreenshotAs(OutputType.FILE);
		String path = "captcha.png";
		if (!new File(DIR_LOCATION).exists())
			new File(DIR_LOCATION).mkdir();
		File file = new File(DIR_LOCATION + path);
		if (!file.exists())
			file.createNewFile();

		FileHandler.copy(src, file);
		Thread.sleep(3000);
		ITesseract image = new Tesseract();
		image.setDatapath("C://Selenium_Course/selenium_course/tessdata");
		image.setLanguage("eng");
		//Optical Character Recognition
		String imageText = image.doOCR(file);
		driver.findElement(By.xpath("//input[@id='captcha']")).sendKeys(imageText);
		// to refresh Captcha
		driver.findElement(By.xpath("//div[@class='captcha']/button"));
		driver.findElement(By.cssSelector("#captcha-error+input")).click();
	}

	public void handleIframe() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		WebElement iframe = driver
				.findElement(By.xpath("//span[@id='c_training_mode-error']/../div//iframe[@title='reCAPTCHA']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void invokeDriver() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.4achievers.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void invokeDriverWithBonigracia() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		WebDriverManager.chromedriver().browserVersion("").driverVersion("").setup();
		// driver = WebDriverManager.chromedriver().create();
		driver = new ChromeDriver(options);
		driver.get("https://www.4achievers.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void closeBrowser() {
		driver.quit();
	}

	public void enterFormDetails() {
		driver.findElement(By.cssSelector("#common_form>#name")).sendKeys("Test_" + generateRandomNumber(10, 100));
		driver.findElement(By.cssSelector("#common_form>#phone")).sendKeys("123456" + generateRandomNumber(1, 9999));
		driver.findElement(By.cssSelector("#common_form>#email"))
				.sendKeys("test_" + generateRandomNumber(1, 99) + "@test.com");
		Select option1 = new Select(driver.findElement(By.cssSelector("#common_form>#course")));
		option1.selectByIndex(1);
		Select option2 = new Select(driver.findElement(By.cssSelector("#common_form>div>#training_mode")));
		option2.selectByIndex(1);
		Select option3 = new Select(driver.findElement(By.cssSelector("#common_form>div>#location")));
		option3.selectByIndex(1);
	}

	private long generateRandomNumber(int min, int max) {
		Random random = new Random();
		long genratedNumber = random.nextInt(max - min) + min;
		return genratedNumber;
	}

	public void clickOnContactUs() {
		driver.findElement(By.xpath("//div[@class='menu-right']//*[text()='Contact Us']")).click();
		driver.findElement(By.cssSelector("#field")).sendKeys("test");
	}

	public static void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

}
