package stepdefinition;


import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.CreditCardDetails;
import pageObjects.ErrorMessageVerifyObjFactory;
import pageObjects.FareType;
import pageObjects.FlightChoiceObjFactory;
import pageObjects.LoginDetailsObjFactory;
import pageObjects.PassengerAndPaymentDetailsObjFactory;
import pageObjects.RyanairHomePageObjFactory;

public class CucumberTestingSteps {
	String baseUrl;
	WebDriver driver;

	LoginDetailsObjFactory loginObj;
	RyanairHomePageObjFactory homePgObj;
	FlightChoiceObjFactory flightChoiceObj;
	PassengerAndPaymentDetailsObjFactory passengersPaymentObj;
	ErrorMessageVerifyObjFactory errorVerifyObj;

	// Change driver path in string with your own file path
	String driverPath = "C:\\automation files\\chrome\\chromedriver.exe";

	@Given("^I make a booking of a ticket for adults and child$")
	public void i_make_a_booking_of_a_ticket_for_adults_and_child() throws Exception {

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		baseUrl = "https://www.ryanair.com/ie/en/";

		loginObj = new LoginDetailsObjFactory(driver);
		homePgObj = new RyanairHomePageObjFactory(driver);
		flightChoiceObj = new FlightChoiceObjFactory(driver);
		passengersPaymentObj = new PassengerAndPaymentDetailsObjFactory(driver);
		errorVerifyObj = new ErrorMessageVerifyObjFactory(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);

		// Log in into site with valid credentials
		loginObj.login("automation.slo@gmail.com", "Test54321");

		// Selecting one-way option
		homePgObj.oneWayRadioButtonClick();
		Thread.sleep(1000);

		// Selecting details of a flight search and proceeding to next page
		homePgObj.fromToAirportDetails("Dublin", "London Gatwick");
		homePgObj.setFlyDate();
		homePgObj.addPassengers(2, 1, 0, 0);
		homePgObj.proceedFromHome();

		// Selecting first available flight and proceeding to the checkout
		flightChoiceObj.selectFirstAvailableFlight();
		flightChoiceObj.chooseFare(FareType.StandardFare);
		flightChoiceObj.checkOut();

	}

	@When("^I pay for booking with card$")
	public void i_pay_for_booking_with_card() throws Exception {

		System.setProperty("webdriver.chrome.driver", driverPath);

		// Fills the page with passenger and billing details
		passengersPaymentObj.fillPassengerDetails();
		passengersPaymentObj.fillContactDetails();
		passengersPaymentObj.fillBillingAddress();

		CreditCardDetails creditCard = new CreditCardDetails("5555555555555557", "MC", 10, 2018, 265, "SoonToEnd");

		passengersPaymentObj.fillPaymentDetails(creditCard);

	}

	@Then("^I should get payment declined message$")
	public void i_should_get_payment_declined_message() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\automation files\\chrome\\chromedriver.exe");

		passengersPaymentObj.submitPayment();

		// Verifies given error message to be equal to expected error message
		Assert.assertEquals(errorVerifyObj.errorValidationMessage, errorVerifyObj.errorPaymentMessage.getText());
		
		driver.quit();
	}

}
