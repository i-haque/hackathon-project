package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CarWashPage;
import pageObjects.FreeListing;
import pageObjects.GymPage;
import pageObjects.HomePage;
import utilities.ExcelUtils;
import utilities.Screenshot;

public class CarWashServices {

	public static WebDriver driver;
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	public List<String> storeNames;
	public List<Float> storeRatings;
	public List<Integer> storeReviews;
	public List<String> storePhoneNumbers;
	public List<String> storeAddresses;
	public List<String> subMenus;
	
	@Given("User is on the JustDial page")
	public void userIsOnJustDialPage() throws InterruptedException, IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-blink-features=AutomationControlled");
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.justdial.com/");
		assertEquals(true, driver.getTitle().equals("Find Businesses Near You on Local Search Engine - Justdial"));
		Screenshot.takeScreenshot(driver, "justdialPage");
		logger.info("Cucumber: User on JustDial page");
		Thread.sleep(10000);
	}
	
	@When("Close popup")
	public void closePopup() {
		HomePage page = new HomePage(driver);
		page.handlePopup();
	}
	
	@And("Select location and search for car wash services")
	public void selectLocationAndSearch() throws InterruptedException {
		HomePage page = new HomePage(driver);
		page.populateLocationField();
		Thread.sleep(2000);
		page.clickLocationOption();
		page.populateServiceField();
		page.search();
	}
	
	@Then("User collects car wash service details")
	public void collectCarWashData() throws InterruptedException, IOException {
		CarWashPage page = new CarWashPage(driver);
		page.clickSort();
		page.clickRatingOption();
		
		Screenshot.takeScreenshot(driver, "carwashPage");
		
		//get names
		storeNames = page.getNames();
		assertEquals(true, storeNames.size() >= 5);
		
		// get ratings
		storeRatings = page.getRatings();
		assertEquals(true, storeRatings.size() >= 5);
		
		// get reviews
		storeReviews = page.getReview();
		assertEquals(true, storeReviews.size() >= 5);
		
		//get phone numbers
		page.revealPhoneNumbers();
		Thread.sleep(1000);
		storePhoneNumbers = page.getPhoneNumbers();
		assertEquals(true, storePhoneNumbers.size() >= 5);
		
		// get addresses
		storeAddresses = page.getAddresses();
		assertEquals(true, storeAddresses.size() >= 5);
		
		// saving data to excel
		List<List<String>> data = new ArrayList<List<String>>();
		for (int i=0; i<10; i++) {
			List<String> temp = new ArrayList<String>();
			temp.add(storeNames.get(i));
			temp.add(storeRatings.get(i).toString());
			temp.add(storeReviews.get(i).toString());
			temp.add(storePhoneNumbers.get(i));
			temp.add(storeAddresses.get(i));
			
			data.add(temp);
		}
		ExcelUtils.saveCarWashDataToExcel(data);
		logger.info("Cucumber: car wash data collected and saved to excel");
	}
	
	@Given("User is on the free listing page")
	public void onFreeListingPage() {
		CarWashPage page = new CarWashPage(driver);
		page.clickOnListing();
		assertEquals(true, driver.getTitle().equals("Free Listing - Just Dial - List In Your Business For Free"));
		logger.info("Cucumber: user on free listing page");
	}
	
	@When("User enters a wrong phone number")
	public void getErrorMsg() {
		FreeListing page = new FreeListing(driver);
		page.populateMobileNumberInput();
		page.clickStartNowButton();
	}
	
	@Then("User gets an error message")
	public void validateErrorMsg() throws IOException {
		FreeListing page = new FreeListing(driver);
		String errorMsg = page.getErrorMsg();
		assertEquals(true, errorMsg.equals("Please Enter a Valid Mobile Number"));
		logger.info("Cucumber: user gets an error message after entering wrong phone number");
		Screenshot.takeScreenshot(driver, "freeListing");
	}
	
	@Given("User is on the home page")
	public void navigateToHomePage() throws InterruptedException {
		FreeListing page = new FreeListing(driver);
		page.goToHomePage();
		Thread.sleep(1000);
		assertEquals(true, driver.getTitle().equals("Find Businesses Near You on Local Search Engine - Justdial"));
		logger.info("Cucumber: user on home page");
	}
	
	@When("User selects the location and clicks on the gym icon")
	public void navigateToGymPage() throws InterruptedException {
		HomePage page = new HomePage(driver);
		page.populateLocationField();
		Thread.sleep(2000);
		page.clickLocationOption();
		page.clickGymButton();
		
		Thread.sleep(2000);
		assertEquals(true, driver.getTitle().equals("Top Gyms in Chennai - Best Fitness Center - Justdial"));
		logger.info("Cucumber: user on gym page");
	}
	
	@Then("User needs to collect all the sub menus")
	public void collectAndPrintSubMenus() throws IOException {
		GymPage page = new GymPage(driver);
		subMenus = page.getSubMenus();
		assertEquals(true, subMenus.size() > 0);
		
		// saving sub menus to excel
		ExcelUtils.saveGymSubMenuDataToExcel(subMenus);
		logger.info("Cucumber: gym sbu menus collected and saved to excel");
		Screenshot.takeScreenshot(driver, "gymPage");
	}
	
	@And("User closes the browser")
	public void closeBrowser() {
		driver.quit();
	}
}
