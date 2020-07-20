package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(css = "a[href='https://www.weightwatchers.com/us/find-a-workshop/']")
	WebElement workShopLink;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickOnWorkShop() {
		workShopLink.click();
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
