package Carwash;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	
	static ChromeOptions option = new ChromeOptions();
	static List<String> storeNames = new ArrayList<String>();
	static List<Float> storeRatings = new ArrayList<Float>();
	static List<Integer> storeReviews = new ArrayList<Integer>();
	static List<String> storeAddresses = new ArrayList<String>();
	static List<String> gymSubMenu = new ArrayList<String>();
	static List<String> storePhoneNumbers = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		
		option.addArguments("--disable-blink-features=AutomationControlled");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://www.justdial.com/");
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='jsx-ed08c56ad83f4a78 maybelater mt-50']/span")))).click();
		Thread.sleep(1000);
		
		String title = driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.id("city-auto-sug")).sendKeys("chennai");
		Thread.sleep(2000);
		driver.findElement(By.id("react-autowhatever-city-auto-suggest--item-1")).click();
		driver.findElement(By.id("main-auto")).sendKeys("car washing services");
		driver.findElement(By.xpath("//div[@class='search_button']")).click();
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@aria-label='Best deal Modal Close Icon']")))).click();
			Thread.sleep(1000);
		} catch(Exception e) {}
		
		driver.findElement(By.xpath("//*[text()='Sort By']")).click();
		driver.findElement(By.xpath("//*[text()='Rating'][1]")).click();
		
		List<WebElement> phones = driver.findElements(By.xpath("//div[@class='jsx-3349e7cd87e12d75 button_flare']"));
		for (WebElement phone: phones) {
			phone.click();
		}
		
		List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='jsx-3349e7cd87e12d75 callcontent callNowAnchor' or @class='jsx-3349e7cd87e12d75 callcontent']"));
		for (WebElement number: numbers) {
			System.out.println(number.getText());
		}
		
//		// store names
//		List<WebElement> names = driver.findElements(By.xpath("//div[contains(@class, 'resultbox_title_anchor')]"));
//		for (WebElement name: names) {
//			storeNames.add(name.getText());
//		}
//		System.out.println("store names - " + names.size());
//		for (String entry: storeNames) {
//			System.out.println(entry);
//		}
//		
//		// ratings
//		List<WebElement> ratings = driver.findElements(By.xpath("//div[contains(@class, 'resultbox_totalrate')]"));
//		for (WebElement rating: ratings) {
//			storeRatings.add(Float.parseFloat(rating.getText()));
//		}
//		System.out.println("rating - " + ratings.size());
//		for (Float entry: storeRatings) {
//			System.out.println(entry);
//		}
//		
//		// reviews
//		List<WebElement> reviews = driver.findElements(By.xpath("//div[contains(@class, 'resultbox_countrate')]"));
//		for (WebElement review: reviews) {
//			storeReviews.add(Integer.parseInt(review.getText().split(" ")[0].replace(",", "")));
//		}
//		System.out.println("reviews - " + reviews.size());
//		for (Integer entry: storeReviews) {
//			System.out.println(entry);
//		}
//		
//		// address
//		List<WebElement> addresses = driver.findElements(By.xpath("//div[contains(@class, 'resultbox_address')]/div/div[2]"));
//		for (WebElement address: addresses) {
//			storeAddresses.add(address.getText());
//		}
//		System.out.println("addresses - " + addresses.size());
//		for (String entry: storeAddresses) {
//			System.out.println(entry);
//		}
//		
//		// navigate to free listing
//		driver.findElement(By.xpath("//li[@id='header_freelisting']/a")).click();
//		driver.findElement(By.xpath("//input[@id='1']")).sendKeys("1234567890");
//		driver.findElement(By.xpath("//button[@aria-label='Start Now'][1]")).click();
//		String errorText = driver.findElement(By.xpath("//span[@class='undefined entermobilenumber_error__text__uPM09']")).getText();
//		System.out.println("error message: " + errorText);
//		
////		// back to main page
//		driver.findElement(By.xpath("//a[@class='header_logo__landing___K5b4']")).click();
//		driver.findElement(By.xpath("//a[@title='Gym in Chennai']")).click();
//		List<WebElement> subMenu = driver.findElements(By.xpath("//div[@class='jsx-6ab5af3a8693e5db font15 fw500 mr-6']"));
//		for (WebElement menu: subMenu) {
//			gymSubMenu.add(menu.getText());
//		}
//		for (String entry: gymSubMenu) {
//			System.out.println(entry);
//		}
//		driver.quit();
		
	}

}
