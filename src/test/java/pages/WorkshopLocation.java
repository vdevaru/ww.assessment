package pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkshopLocation {
	WebDriver driver;
	String day;

	@FindBy(className = "locationName-1jro_")
	WebElement locationPageName;
	

	public WorkshopLocation(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getTextLocation() {
		return locationPageName.getText();
	}
	
	public Map<String,Integer> getMeeting(Integer day){
		
		return null;
		
	}

}
