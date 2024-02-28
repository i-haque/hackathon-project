package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarWashPage extends BasePage {

	public CarWashPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[text()='Sort By']")
	WebElement sortBy;
	
	@FindBy(xpath = "//*[text()='Rating'][1]")
	WebElement ratingOption;
	
	@FindBy(xpath = "//div[contains(@class, 'resultbox_title_anchor')]")
	List<WebElement> nameLocator;
	
	@FindBy(xpath = "//div[contains(@class, 'resultbox_totalrate')]")
	List<WebElement> ratingLocator;
	
	@FindBy(xpath = "//div[contains(@class, 'resultbox_countrate')]")
	List<WebElement> reviewLocator;
	
	@FindBy(xpath = "//div[@class='jsx-3349e7cd87e12d75 button_flare']")
	List<WebElement> phoneRevealer;
	
	@FindBy(xpath = "//span[@class='jsx-3349e7cd87e12d75 callcontent callNowAnchor' or @class='jsx-3349e7cd87e12d75 callcontent']")
	List<WebElement> phoneLocator;
	
	@FindBy(xpath = "//div[contains(@class, 'resultbox_address')]/div/div[2]")
	List<WebElement> addressLocator;
	
	@FindBy(xpath = "//li[@id='header_freelisting']/a")
	WebElement listingLink;
	
	public void clickSort() {
		sortBy.click();
	}
	
	public void clickRatingOption() {
		ratingOption.click();
	}
	
	public List<String> getNames() {
		List<String> storeNames = new ArrayList<String>();
		for (WebElement name: nameLocator) {
			storeNames.add(name.getText());
		}
		return storeNames;
	}
	
	public List<Float> getRatings() {
		List<Float> storeRatings = new ArrayList<Float>();
		for (WebElement rating: ratingLocator) {
			storeRatings.add(Float.parseFloat(rating.getText()));
		}
		return storeRatings;
	}
	
	public List<Integer> getReview() {
		List<Integer> storeReviews = new ArrayList<Integer>();
		for (WebElement review: reviewLocator) {
			storeReviews.add(Integer.parseInt(review.getText().split(" ")[0].replace(",", "")));
		}
		return storeReviews;
	}
	
	public void revealPhoneNumbers() {
		for (WebElement item: phoneRevealer) {
			item.click();
		}
	}
	
	public List<String> getPhoneNumbers() {
		List<String> storePhoneNumbers = new ArrayList<String>();
		for (WebElement phone: phoneLocator) {
			storePhoneNumbers.add(phone.getText());
		}
		return storePhoneNumbers;
	}
	
	public List<String> getAddresses() {
		List<String> storeAddresses = new ArrayList<String>();
		for (WebElement address: addressLocator) {
			storeAddresses.add(address.getText());
		}
		return storeAddresses;
	}
	
	public void clickOnListing() {
		listingLink.click();
	}

	
}
