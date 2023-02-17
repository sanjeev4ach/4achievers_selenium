package testng_course;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAnnotations {

	@Test(priority = 2, invocationTimeOut = 50000, invocationCount = 5, testName = "", description = "")
	public void invocationTimeOut() {
		System.out.println("InvocationCount:");
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("http://google.co.in");
	}

	@Test(invocationCount = 5, threadPoolSize = 3)
	public void invocationCount() {
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("http://google.co.in");
		long id = Thread.currentThread().getId();
		System.out.println("ThreadPoolSize: Thread id is: " + id);
	}

}
