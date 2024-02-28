package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"C:\\Users\\2304033\\eclipse-workspace\\Hackathon-CarWash-Project\\featurefiles\\carWash.feature",
							"C:\\Users\\2304033\\eclipse-workspace\\Hackathon-CarWash-Project\\featurefiles\\freeListing.feature",
							"C:\\Users\\2304033\\eclipse-workspace\\Hackathon-CarWash-Project\\featurefiles\\gymSubMenu.feature"},
							glue = "stepDefinitions", 
							plugin = {"pretty", "html:reports//cucumberReport.html"})

public class TestRun {

}
