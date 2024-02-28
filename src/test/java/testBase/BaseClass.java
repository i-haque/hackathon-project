package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import testCases.TestCase02;
import testCases.TestCase05;
import utilities.ExcelUtils;

public class BaseClass {
	public static Logger logger;
	public static WebDriver driver;
	ChromeOptions chromeOption = new ChromeOptions();
	EdgeOptions edgeOption = new EdgeOptions();
	public Properties p = new Properties();
	
	@Parameters({"os", "browser"})
	@BeforeTest
	public void setup(String os, String browser) throws InterruptedException, IOException {
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader("C:\\Users\\2304033\\eclipse-workspace\\Hackathon-CarWash-Project\\src\\test\\resources\\config.properties");
		p.load(file);
		if (p.getProperty("exec_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("no matching os!");
				return;
			}
			
			// browser
			if (browser.equalsIgnoreCase("edge")) {
				edgeOption.addArguments("--disable-blink-features=AutomationControlled");
				edgeOption.addArguments("--disable-notifications");
				capabilities.setBrowserName("MicrosoftEdge");
				capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOption);
			} else if (browser.equalsIgnoreCase("chrome")) {
				chromeOption.addArguments("--disable-blink-features=AutomationControlled");
				chromeOption.addArguments("--disable-notifications");
				capabilities.setBrowserName("chrome");
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOption);
			} else {
				System.out.println("no matching browser!");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") , capabilities);
		} else {
			if (browser.equalsIgnoreCase("edge")) {
				edgeOption.addArguments("--disable-blink-features=AutomationControlled");
				edgeOption.addArguments("--disable-notifications");
				driver = new EdgeDriver(edgeOption);
			} else {
				chromeOption.addArguments("--disable-blink-features=AutomationControlled");
				chromeOption.addArguments("--disable-notifications");
				driver = new ChromeDriver(chromeOption);
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.justdial.com/");
		
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";			File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	 
	}
	
	@AfterTest
	public void cleanup() throws IOException {
		List<List<String>> data = new ArrayList<List<String>>();
		
		List<String> names = TestCase02.storeNames;
		List<Float> ratings = TestCase02.storeRatings;
		List<Integer> reviews = TestCase02.storeReviews;
		List<String> phoneNumbers = TestCase02.storePhoneNumbers;
		List<String> addresses = TestCase02.storeAddresses;
		
		for (int i=0; i<10; i++) {
			List<String> temp = new ArrayList<String>();
			temp.add(names.get(i));
			temp.add(ratings.get(i).toString());
			temp.add(reviews.get(i).toString());
			temp.add(phoneNumbers.get(i));
			temp.add(addresses.get(i));
			
			data.add(temp);
		}
		ExcelUtils.saveCarWashDataToExcel(data);
		
		List<String> menus = TestCase05.subMenus;
		ExcelUtils.saveGymSubMenuDataToExcel(menus);
		
		driver.quit();
	}
	
}
