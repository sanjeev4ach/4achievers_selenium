package testng_course;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenerateExtentFiveForAllTests {
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	WebDriver driver;
	private static final String DIR_LOCATION = System.getProperty("user.dir") + "/screenshots/";

	@BeforeSuite
	public void setUp() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter("index.html");
			spark.loadXMLConfig(new File("extent-config.xml"));
			extent.attachReporter(spark);
		}
	}

	@AfterSuite
	public void teardown() throws IOException {
		if(Objects.nonNull(extent))
			extent.flush();
		Desktop.getDesktop().browse(new File("index.html").toURI());
	}

	@Test
	public void attachScreenShot() {
		test = extent.createTest("Test starts1");
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.google.com");

		test.pass("Site Open Successfully",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("ExtentTest.png")).build());
	}

	@Test
	public void attachScreenShotBase64() throws IOException {
		test = extent.createTest("Test starts2");
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.google.com");

		test.pass("Site Open Successfully", MediaEntityBuilder
				.createScreenCaptureFromBase64String(takeScreenshotInBase64("ExtentTest.png")).build());
	}

	@Test
	public void attachScreenShot64() throws IOException {
		test = extent.createTest("Test starts3");
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.google.com");

		test.pass("Site Open Successfully",
				MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
	}

	public String takeScreenshot(String fileName) {
		String path = "";
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			path = DIR_LOCATION + fileName;
			FileUtils.copyFile(file, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public String takeScreenshotInBase64(String fileName) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = DIR_LOCATION + fileName;
		FileUtils.copyFile(file, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	public String getBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
