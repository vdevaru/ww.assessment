package ww.assessment;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {

	public static void main(String[] args) {
	
		
		
		System.out.println("Hello World");
    	System.setProperty("webdriver.chrome.driver", "test/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.weightwatchers.com/us/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println(driver.getTitle() + " is the Home Page Title");
	//	WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='https://www.weightwatchers.com/us/find-a-workshop/']")));
		driver.findElement(By.cssSelector("a[href='https://www.weightwatchers.com/us/find-a-workshop/']")).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		System.out.println(driver.getTitle()+ " is the Workshop Page Title");
		driver.findElement(By.id("location-search")).sendKeys("10011");
		driver.findElement(By.id("location-search-cta")).click();
		//need to add wait here..check 
		WebElement location = driver.findElement(By.className("linkUnderline-1_h4g"));
		System.out.println(location.getText()+ " is the location title");
		System.out.println(driver.findElement(By.className("distance-OhP63")).getText()+ " is the location distance");
		location.click();
		System.out.println(driver.getTitle() + " is the location page Title");
		
		ww.assessment.DayOfWeek day = ww.assessment.DayOfWeek.SUN;
		
//		System.out.println(dayOfWeek.getValue() +" day of week");
		//$x("//*[@class='scheduleContainerMobile-1RfmF']/div[2]");
		List<String> names = new ArrayList<String>();
		String xpathDay = "//*[@class='scheduleContainerMobile-1RfmF']/div["+(day.getId())+"]/div";
//		System.out.println(xpathDay);
		List<WebElement> list = driver.findElements(By.xpath(xpathDay));
		
		for(WebElement element:list) {
			WebElement name = element.findElement(By.xpath(".//span[@class='meetingTime-1g52A']/following-sibling::span[1]"));
			names.add(name.getText());
		}
		Map<String, Integer> countMap = new HashMap();
		for (String s: names) {
			Integer count = countMap.get(s);
			if (count == null)
				count = 0;

			countMap.put(s, count + 1);
		}

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		driver.quit();
		return;
	}

}
