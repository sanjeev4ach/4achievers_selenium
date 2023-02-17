package testng_course;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	static ExtentReports extent;
	ExtentSparkReporter spark;
	public static ExtentTest test;

	public static WebDriver driver;

	static AbstractDriverOptions<?> options;

	@AfterMethod(enabled = true)
	public void testFunction(ITestResult result) {
		int status = result.getStatus();
		if (status == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + " Test case passed", ExtentColor.GREEN));
		} else if (status == ITestResult.FAILURE) {
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(result.getName())).build());
			test.fail(result.getThrowable());
		}else {
			test.skip(MarkupHelper.createLabel(result.getName() + " Test case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}

		driver.quit();
	}

	@BeforeMethod
	public void setUP(Method m) {
		initializeDriver("chrome");
	}

	@BeforeSuite(alwaysRun = true)
	public void setUpExtentReport() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter("index.html");
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("4achievers Automation Learning");
			spark.config().setReportName("Extent Report 5 Demo");
			spark.config().setTimelineEnabled(true);
			// spark.loadXMLConfig(new File("extent-config.xml"));

		}
	}

	@AfterSuite(alwaysRun = true)
	public void flushExtent() throws IOException {
		if (Objects.nonNull(extent))
			extent.flush();
		Desktop.getDesktop().browse(new File("index.html").toURI());
	}

	public static ExtentTest createTest(String methodName) {
		test = extent.createTest(methodName);
		return test;
	}

	public void initializeDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome"))
			driver = WebDriverManager.chromedriver().create();
		else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browserName", "firefox");
			options = new FirefoxOptions();
			options.merge(caps);
			driver = WebDriverManager.firefoxdriver().capabilities(options).create();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static String takeScreenShot(String fileName) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
