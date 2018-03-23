package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.CreditCardDetails;
import pageObjects.ErrorMessageVerifyObjFactory;
import pageObjects.FareType;
import pageObjects.FlightChoiceObjFactory;
import pageObjects.LoginDetailsObjFactory;
import pageObjects.PassengerAndPaymentDetailsObjFactory;
import pageObjects.RyanairHomePageObjFactory;

class JUnitBookingTest {

	String baseUrl;
	WebDriver driver;

	LoginDetailsObjFactory loginObj;
	RyanairHomePageObjFactory homePgObj;
	FlightChoiceObjFactory flightChoiceObj;
	PassengerAndPaymentDetailsObjFactory passengersPaymentObj;
	ErrorMessageVerifyObjFactory errorVerifyObj;

	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\automation files\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.ryanair.com/ie/en/";

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		loginObj = new LoginDetailsObjFactory(driver);
		homePgObj = new RyanairHomePageObjFactory(driver);
		flightChoiceObj = new FlightChoiceObjFactory(driver);
		passengersPaymentObj = new PassengerAndPaymentDetailsObjFactory(driver);
		errorVerifyObj = new ErrorMessageVerifyObjFactory(driver);
	}

	@AfterEach
	void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	void test() throws Exception {
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

		// Fills the page with passenger and billing details
		passengersPaymentObj.fillPassengerDetails();
		passengersPaymentObj.fillContactDetails();
		passengersPaymentObj.fillBillingAddress();

		CreditCardDetails creditCard = new CreditCardDetails("5555555555555557", "MC", 10, 2018, 265, "SoonToEnd");

		passengersPaymentObj.fillPaymentDetails(creditCard);
		passengersPaymentObj.submitPayment();

		// Verifies given error message to be equal to expected error message
		assertEquals(errorVerifyObj.errorValidationMessage, errorVerifyObj.errorPaymentMessage.getText());

	}

}
