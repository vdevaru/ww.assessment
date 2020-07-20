package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkshopPage {
	WebDriver driver;

	@FindBy(tagName = "title")
	WebElement titleWorkshop;

	@FindBy(id = "location-search")
	WebElement locationInput;

	@FindBy(id = "location-search-cta")
	WebElement button;

	@FindBy(className = "linkUnderline-1_h4g")
	WebElement locationName;

	@FindBy(className = "distance-OhP63")
	WebElement locationDistance;

	public WorkshopPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void setLocation() {
		locationInput.sendKeys("10011");
		;
	}

	public void clickButton() {
		button.click();
	}

	public String getWorkshopPageTitle() {
		return driver.getTitle();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getLocatioName() {
		return locationName.getText();
	}

	public String getDistance() {
		return locationDistance.getText();
	}

	public void clickOnLocation() {
		locationName.click();
	}

}
