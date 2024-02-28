package testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import pageObjects.GymPage;
import testBase.BaseClass;

public class TestCase05 extends BaseClass {
	
	public static List<String> subMenus;
	
	@Test(priority=1)
	public void printSubMenus() {
		GymPage page = new GymPage(driver);
		subMenus = page.getSubMenus();
		
		assertEquals(true, subMenus.size() > 0);
		
		logger.info("Retrieved sub menus successfully ☑️");
		
		for (String subMenu: subMenus) {
			System.out.println(subMenu);
		}
	}
}
