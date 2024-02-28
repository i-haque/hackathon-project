package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.Screenshot;

public class TestCase04 extends BaseClass {

	@Test(priority=1)
	public void navigateToGymPage() throws InterruptedException, IOException {
		HomePage page = new HomePage(driver);
		page.populateLocationField();
		Thread.sleep(2000);
		page.clickLocationOption();
		page.clickGymButton();
		
		Thread.sleep(2000);
		assertEquals(true, driver.getTitle().equals("Top Gyms in Chennai - Best Fitness Center - Justdial"));
		
		Screenshot.takeScreenshot(driver, "gymPage");
		logger.info("Navigated to gym page successfully");
	}
}
