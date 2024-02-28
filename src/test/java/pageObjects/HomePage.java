package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
		
	@FindBy(id = "city-auto-sug")
	WebElement locationField;
	
	@FindBy(id = "react-autowhatever-city-auto-suggest--item-1")
	WebElement locationOption;
	
	@FindBy(id = "main-auto")
	WebElement serviceField;
	
	@FindBy(xpath = "//div[@class='search_button']")
	WebElement searchButton;
	
	@FindBy(xpath = "//div[@class='jsx-ed08c56ad83f4a78 maybelater mt-50']/span")
	WebElement popup;
	
	@FindBy(xpath = "//a[@title='Gym in Chennai']")
	WebElement gymButton;
	
	public String getTitle(WebDriver driver) throws InterruptedException {
		return driver.getTitle();
	}
	
	public void handlePopup() {
		popup.click();
	}
	
	public void populateLocationField() {
		locationField.sendKeys("chennai");
	}
	
	public void clickLocationOption() {
		locationOption.click();
	}
	
	public void populateServiceField() {
		serviceField.sendKeys("car washing services");
	}
	
	public void search() {
		searchButton.click();
	}
	
	public void clickGymButton() {
		gymButton.click();
	}
}
