package testng_course;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class First_Day_testng {

	/**
	 * beforeSuite 
	 * beforeTest
	 * beforeclass
	 * Beforemethod
	 * test 
	 * AfterMethod
	 * AfterClass
	 * Aftertest
	 */
	
	/*
	 * @BeforeSuite --> Setting up the environment
	 * 
	 * @BeforeTest --> Launching.... browser
	 * 
	 * @BeforeClass --> Enter URL
	 * 
	 * @BeforeMethod --> Logged in
	 * 
	 * @Test --> Test Completed
	 * 
	 * @AfterMethod --> Logged out.
	 * 
	 * @AfterClass --> Closing browser
	 * 
	 * @AfterTest --> delete cookies.... PASSED: testMethod
	 */

	// 1
	@BeforeSuite
	public void setUp() {
		System.out.println("@BeforeSuite --> Setting up the environment");
	}

	// 2
	@BeforeTest
	public void launchBrowser() {
		System.out.println("@BeforeTest --> Launching.... browser");
	}

	// 3
	@BeforeClass
	public void enterURL() {
		System.out.println("@BeforeClass --> Enter URL");
	}

	// 4, 10, 13
	@BeforeMethod
	public void EnterSiteUrl() {
		System.out.println("@BeforeMethod --> Logged in");
	}

	// 5
	@Test
	public void testMethod() {
		System.out.println("@Test --> Test Completed");
	}

	// 11
	@Test
	public void verifyTitle() {
		System.out.println("@Test --> Title of the page...");
	}
	
	//14
	@Test
	public void verifyTitle_test() {
		System.out.println("@Test --> Title of the page...");
	}
	

	// 6, 12, 15
	@AfterMethod
	public void logOut() {
		System.out.println("@AfterMethod --> Logged out.");
	}

	// 8
	@AfterTest
	public void deleteCookies() {
		System.out.println("@AfterTest --> delete cookies....");
	}

	// 7
	@AfterClass
	public void closeBrowser() {
		System.out.println("@AfterClass --> Closing browser");
	}

	// 9
	@AfterSuite
	public void generateReport() {
		System.out.println("@AfterSuite --> Reports Generated");
	}

}
