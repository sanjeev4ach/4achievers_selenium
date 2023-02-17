package testng_course;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindBrokenLinks {

	static WebDriver driver;

	public static void main(String[] args) {
		driver = WebDriverManager.chromedriver().create();
		driver.get("http://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));

		List<WebElement> activeLinks = new ArrayList<>();
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i).getAttribute("href"));
			if (links.get(i).getAttribute("href") != null
					&& (!links.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(links.get(i));
			}
		}

		System.out.println("===========================================================");
		System.out.println("Size of Active links - " + activeLinks.size());
		System.out.println("===========================================================");

		for (int j = 0; j < activeLinks.size(); j++) {
			try {
				HttpURLConnection obj = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href"))
						.openConnection();
				obj.setRequestMethod("HEAD");
				obj.connect();
				int respCode = obj.getResponseCode();
				if(respCode>=400) {
					System.out.println("URL is -"+activeLinks.get(j).getAttribute("href"));
				}
				String response = obj.getResponseMessage();
				System.out.println(activeLinks.get(j).getAttribute("href") + " ---> " + response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
