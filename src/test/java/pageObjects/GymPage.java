package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GymPage extends BasePage {

	public GymPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='jsx-6ab5af3a8693e5db font15 fw500 mr-6']")
	List<WebElement> items;
	
	public List<String> getSubMenus() {
		List<String> subMenus = new ArrayList<String>();
		for (WebElement item: items) {
			subMenus.add(item.getText());
		}
		return subMenus;
	}
}
