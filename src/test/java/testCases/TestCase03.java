package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.FreeListing;
import testBase.BaseClass;
import utilities.Screenshot;

public class TestCase03 extends BaseClass {

	@Test(priority=1)
	public void validateErrorMsg() throws IOException {
		FreeListing page = new FreeListing(driver);
		page.populateMobileNumberInput();
		page.clickStartNowButton();
		String errorMsg = page.getErrorMsg();
		
		Screenshot.takeScreenshot(driver, "listingPage");
		assertEquals(true, errorMsg.equals("Please Enter a Valid Mobile Number"));
		logger.info("On invalid input of phone number, error meesage recieved successfully ☑️");
	}
	
	@Test(priority=2)
	public void navigateToHomePage() throws InterruptedException, IOException {
		FreeListing page = new FreeListing(driver);
		page.goToHomePage();
		Thread.sleep(1000);
		
		Screenshot.takeScreenshot(driver, "backToHomePage");
		assertEquals(true, driver.getTitle().equals("Find Businesses Near You on Local Search Engine - Justdial"));
		logger.info("Navigated to Justdial homepage successfully ☑️");
	}
}
