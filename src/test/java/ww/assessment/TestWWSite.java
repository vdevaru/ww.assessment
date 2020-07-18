package ww.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.WorkshopLocation;
import pages.WorkshopPage;

public class TestWWSite {

	String driverPath = "test/resources/chromedriver.exe";
	WebDriver driver;
	HomePage homepage;
	WorkshopPage worshopPage;
	WorkshopLocation location;
	String locName;
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
        LOGGER.log(Level.INFO, "drive path set",driverPath); 
		driver = new ChromeDriver();
		driver.get("https://www.weightwatchers.com/us/");
        LOGGER.log(Level.INFO, "Navigated to WW site","https://www.weightwatchers.com/us/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void testHomePageTitle() {
		homepage = new HomePage(driver);
		String homePageTitle = homepage.getTitle();
		String expectedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help | WW USA";
		Assert.assertEquals(homePageTitle, expectedTitle);
	}

	@Test(priority = 1)
	public void clickOnWorkShop() {
		homepage.clickOnWorkShop();
		worshopPage = new WorkshopPage(driver);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String PageTitle = worshopPage.getWorkshopPageTitle();
		String titleExpected = "Find WW Studios & Meetings Near You | WW USA";
		Assert.assertEquals(PageTitle, titleExpected);
	}

	public static void CallWait(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test(priority = 2)
	public void searchlocation() {
		worshopPage.setLocation();
		worshopPage.clickButton();
		locName = worshopPage.getLocatioName();
		LOGGER.log(Level.INFO,"Location Name : {0} ",locName);
		LOGGER.log(Level.INFO,"distance : {0}",worshopPage.getDistance());
	}

	@Test(priority = 3)
	public void clickVerifyLocationName() {
		worshopPage.clickOnLocation();
		location = new WorkshopLocation(driver);
		Assert.assertEquals(location.getTextLocation(), locName);
	}
	
	@Test(priority = 4)
	@Parameters("day")
	public void Meeting(String day) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		int dayNo = 0;
		String day1 = "THU";
        switch(day1) 
        { 
            case "SUN": 
                dayNo = 1;
                break; 
            case "MON": 
                dayNo = 2; 
                break; 
            case "TUE": 
            	dayNo = 3;
                break; 
            case "WED": 
            	dayNo = 4;
                break; 
            case "THU": 
            	dayNo = 5;
                break; 
            case "FRI": 
            	dayNo = 6;
                break; 
            case "SAT": 
            	dayNo = 7;
                break; 
        } 
        
        List<String> names = new ArrayList<String>();
		String xpathDay = "//*[@class='scheduleContainerMobile-1RfmF']/div["+dayNo+"]/div";
		List<WebElement> list = driver.findElements(By.xpath(xpathDay));
		for(WebElement element:list) {
			WebElement name = element.findElement(By.xpath(".//span[@class='meetingTime-1g52A']/following-sibling::span[1]"));
			names.add(name.getText());
		}
		for (String s: names) {
			Integer count = countMap.get(s);
			if (count == null)
				count = 0;

			countMap.put(s, count + 1);
		}

       for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
    	   
			System.out.println(entry.getKey() + ": " + entry.getValue());

		}
	}

	public String getDay() {
		String day = null;
		System.out.println("Enter the day(SUN,MON,TUE,WED,THU,FRI,SAT)");
	     Scanner in = new Scanner(System.in);

	     day = in.nextLine();
		return day;
	}

	@AfterTest
	public void cleanup() {
		driver.quit();
	}

}
