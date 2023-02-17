package testng_course;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners(listeners.ListenersDemo.class)
public class HandleWebTable {

	// task
	
	// verify if Selenium is present on the table
	// click on the checkbox of Selenium
	// click on know more link if Selenium presents
	// create a 2D array and store data

	WebDriver driver;

	Map<String, String> tableMap;
	List<Map<String, String>> tableData = new ArrayList<>();

	@Test(testName="ListenersTest")
	public void handleWebTable(@Optional("test")String testParam) {
		driver = WebDriverManager.chromedriver().create();
		driver.get("https://seleniumpractise.blogspot.com/2021/08");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		// columns
		List<WebElement> tableHeaders = driver.findElements(By.xpath("//table[@id='customers']//th"));
		Assert.assertEquals(tableHeaders.size(), 5, "Table headers count");
		for (WebElement headers : tableHeaders) {
			System.out.println(headers.getText());
		}

		// rows
		List<WebElement> noOFRows = driver.findElements(By.xpath("//table[@id='customers']//tr"));
		Assert.assertEquals(noOFRows.size(), 7, "Table rows count");

		// Data
		List<WebElement> allData = driver.findElements(By.xpath("//table[@id='customers']//tr//td"));
		boolean dataStatus = false;
		for (WebElement tempData : allData) {
			String value = tempData.getText();
			System.out.println(value);
			if (value.contains("Ola")) {
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Company Found");

		// first way
		for (int i = 1; i < noOFRows.size(); i++) {
			tableMap = new HashMap<>();
			for (int j = 2; j <= tableHeaders.size(); j++) {
				String columnName = driver.findElement(By.xpath("//table[@id='customers']//tr[1]//th[" + j + "]"))
						.getText();
				String value = driver
						.findElement(By.xpath("//table[@id='customers']//tr[" + (i + 1) + "]//td[" + j + "]"))
						.getText();
				tableMap.put(columnName, value);
			}
			tableData.add(tableMap);
		}
		System.out.println("Total Rows - " + tableData.size());
		System.out.println("Data captured " + tableData);
		tableData.clear();

		// Second Way
		for (int i = 1; i < noOFRows.size(); i++) {
			tableMap = new HashMap<>();
			for (int j = 2; j <= tableHeaders.size(); j++) {
				String columnName = driver.findElement(By.xpath("//table[@id='customers']//tr[1]//th[" + j + "]"))
						.getText();
				String value = noOFRows.get(i).findElement(By.xpath("td[" + j + "]")).getText();
				tableMap.put(columnName, value);
			}
			tableData.add(tableMap);
		}
		System.out.println("Total Rows - " + tableData.size());
		System.out.println("Data captured " + tableData);

	}

}
