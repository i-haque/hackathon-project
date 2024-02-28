package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.Screenshot;

public class TestCase01 extends BaseClass {
		
	@Test(priority=1)
	public void testTile() throws InterruptedException, IOException {
		HomePage page = new HomePage(driver);
		String title = page.getTitle(driver);
		
		assertEquals(true, title.equals("Find Businesses Near You on Local Search Engine - Justdial"));
		
		Screenshot.takeScreenshot(driver, "homepage");
		logger.info("Title of the Justdial page verified");
		
		Thread.sleep(10000);
		page.handlePopup();
	}
	
	@Test(priority=2)
	public void navigateToCarWashPage() throws InterruptedException, IOException {
		HomePage page = new HomePage(driver);
		page.populateLocationField();
		Thread.sleep(2000);
		page.clickLocationOption();
		page.populateServiceField();
		page.search();
		
		Thread.sleep(2000);
		String title = page.getTitle(driver);
		assertEquals(true, title.equals("Top Car Washing Services near Chennai - Best Car Washing Centre - Justdial"));
		
		Screenshot.takeScreenshot(driver, "search");
		logger.info("Navigated successfully to car wash page");
	}
	
}
