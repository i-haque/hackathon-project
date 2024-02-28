package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		
		File srcFile = ss.getScreenshotAs(OutputType.FILE);
		String ssName = fileName + ".png";
		
		File destFile = new File(System.getProperty("user.dir") + "\\screenshots\\" + ssName);
		
		FileUtils.copyFile(srcFile, destFile);
	}
}
