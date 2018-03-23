package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorMessageVerifyObjFactory {

	WebDriver driver;

	public String errorValidationMessage = "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again";

	public String anotherReservationErrorValidationMessage = "Our systems have detected an identical reservation.\r\n"
			+ "If you have already made this reservation and have not yet received your confirmation email please check the following:\r\n"
			+ "Check your junk / spam folder for the reservation email\r\n"
			+ "Retrieve your booking under the “My Bookings” section of the website (you will require your flight details and payment card)\r\n"
			+ "If you wish to proceed with this new reservation please use an alternative form of payment or wait 24 hours. Alternatively please chat online with our Customer Service team.";

	@FindBy(xpath = "//prompt[@class='error prompt-text']//div[@class='info-text']")
	public WebElement errorPaymentMessage;

	public ErrorMessageVerifyObjFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
