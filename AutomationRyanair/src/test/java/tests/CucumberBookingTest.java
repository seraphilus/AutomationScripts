package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature",
		glue = {"src/test/java/stepdefinition"},
		plugin = {"pretty", "html:site/cucumber-html-report"},
		monochrome=true
		)


public class CucumberBookingTest {



}
