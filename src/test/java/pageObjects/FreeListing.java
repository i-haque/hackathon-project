package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreeListing extends BasePage {
	
	public FreeListing(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='1']")
	WebElement mobileNumberInput;
	
	@FindBy(xpath = "//button[@aria-label='Start Now'][1]")
	WebElement startNowButton;
	
	@FindBy(xpath = "//span[@class='undefined entermobilenumber_error__text__uPM09']")
	WebElement error;
	
	@FindBy(xpath = "//a[@class='header_logo__landing___K5b4']")
	WebElement homePageLink;
	
	public void populateMobileNumberInput() {
		mobileNumberInput.sendKeys("1234567890");
	}
	
	public void clickStartNowButton() {
		startNowButton.click();
	}
	
	public String getErrorMsg() {
		return error.getText();
	}
	
	public void goToHomePage() {
		homePageLink.click();
	}
	
}
