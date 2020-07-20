package ww.assessment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	BufferedWriter writer;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		LOGGER.log(Level.INFO, "driver path set", driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.weightwatchers.com/us/");
		LOGGER.log(Level.INFO, "Navigated to WW site", "https://www.weightwatchers.com/us/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String fileName = "testResult.txt";
		try {
			writer = new BufferedWriter(new FileWriter(fileName, false));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 0)
	public void testHomePageTitle() {
		homepage = new HomePage(driver);
		String homePageTitle = homepage.getTitle();
		String expectedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help | WW USA";
		// Fails if below Title is used
		// String expectedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help";
		try {
			writer.append("homepage Title : " + homePageTitle);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(homePageTitle, expectedTitle);
	}

	@Test(priority = 1)
	public void clickOnWorkShop() {
		homepage.clickOnWorkShop();
		worshopPage = new WorkshopPage(driver);
		try {
			Thread.sleep(10000);
			String PageTitle = worshopPage.getWorkshopPageTitle();
			String titleExpected = "Find WW Studios & Meetings Near You | WW USA";
			writer.append("\nWorkshop page title : " + PageTitle);
			Assert.assertEquals(PageTitle, titleExpected);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test(priority = 2)
	public void searchlocation() {
		worshopPage.setLocation();
		worshopPage.clickButton();
		locName = worshopPage.getLocatioName();
		LOGGER.log(Level.INFO, "Location Name : {0} ", locName);
		LOGGER.log(Level.INFO, "distance : {0}", worshopPage.getDistance());
		try {
			writer.append("\nDistance : " + worshopPage.getDistance());
			writer.append("\nLocation Name : " + locName);
			writer.append("\nPerson Schedule on given day : ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void clickVerifyLocationName() {
		worshopPage.clickOnLocation();
		location = new WorkshopLocation(driver);
		Assert.assertEquals(location.getTextLocation(), locName);
	}

	@Test(priority = 4)
	@Parameters("day")
	public void printMeeting(String day) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		int dayNo = 0;
		switch (day.toUpperCase()) {
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
		String xpathDay = "//*[@class='scheduleContainerMobile-1RfmF']/div[" + dayNo + "]/div";
		List<WebElement> list = driver.findElements(By.xpath(xpathDay));
		for (WebElement element : list) {
			WebElement name = element.findElement(By.xpath(".//span[@class='meetingTime-1g52A']/following-sibling::span[1]"));
			names.add(name.getText());
		}
		for (String s : names) {
			Integer count = countMap.get(s);
			if (count == null)
				count = 0;
			countMap.put(s, count + 1);
		}

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
			try {
				writer.append("\n" + entry.getKey() + ": " + entry.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getDay() {
		String day = null;
		System.out.println("Enter the day(SUN,MON,TUE,WED,THU,FRI,SAT)");
		Scanner in = new Scanner(System.in);
		day = in.nextLine();
		return day.toUpperCase();
	}

	@AfterTest
	public void cleanup() {
		try {
			System.out.println("Result Writtin to testResult.txt");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
