package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import pageObjects.CarWashPage;
import testBase.BaseClass;
import utilities.Screenshot;

public class TestCase02 extends BaseClass {
	
	public static List<String> storeNames;
	public static List<Float> storeRatings;
	public static List<Integer> storeReviews;
	public static List<String> storePhoneNumbers;
	public static List<String> storeAddresses;
	
	@Test(priority=1)
	public void stores() throws IOException {
		CarWashPage page = new CarWashPage(driver);
		page.clickSort();
		page.clickRatingOption();
		storeNames = page.getNames();
		
		assertEquals(true, storeNames.size() >= 5);
		Screenshot.takeScreenshot(driver, "carwash");
		
		logger.info("Stores retrieved successfully");
	}
	
	@Test(priority=2)
	public void ratings() {
		CarWashPage page = new CarWashPage(driver);
		storeRatings = page.getRatings();
		
		assertEquals(true, storeRatings.size() >= 5);
		
		logger.info("Ratings retrieved successfully");
	}
	
	@Test(priority=3)
	public void reviews() {
		CarWashPage page = new CarWashPage(driver);
		storeReviews = page.getReview();
		
		assertEquals(true, storeReviews.size() >= 5);
		
		logger.info("Reviews retrieved successfully");
	}
	
	@Test(priority=4)
	public void phones() throws InterruptedException {
		CarWashPage page = new CarWashPage(driver);
		page.revealPhoneNumbers();
		Thread.sleep(1000);
		storePhoneNumbers = page.getPhoneNumbers();
		
		assertEquals(true, storePhoneNumbers.size() >= 5);
		
		logger.info("Phone numbers retrieved successfully");
	}
	
	@Test(priority=5)
	public void addresses() {
		CarWashPage page = new CarWashPage(driver);
		storeAddresses = page.getAddresses();
		
		assertEquals(true, storeAddresses.size() >= 5);
		
		logger.info("Addresses retrieved successfully");
	}
	
	@Test(priority=6)
	public void goToListingLink() throws IOException {
		CarWashPage page = new CarWashPage(driver);
		page.clickOnListing();
		Screenshot.takeScreenshot(driver, "listingPage");
		assertEquals(true, driver.getTitle().equals("Free Listing - Just Dial - List In Your Business For Free"));
		
		logger.info("Navigated to free listing page successfully");
	}
	
	
}
