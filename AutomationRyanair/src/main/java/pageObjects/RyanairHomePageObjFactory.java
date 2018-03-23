package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RyanairHomePageObjFactory {
	
	WebDriver driver;

	@FindBy(xpath = "//span[text()='One way']")
	WebElement oneWayRadioButton;

	@FindBy(xpath = "//span[text()='Return']")
	WebElement returnRadioButton;

	@FindBy(xpath = "//input[@type='text'][@placeholder='Departure airport']")
	WebElement departureAirportInput;

	@FindBy(xpath = "//input[@type='text'][@placeholder='Destination airport']")
	WebElement destinationAirportInput;

	@FindBy(xpath = "//ul[@class='days']//li[not(contains(@class, 'unavailable'))][position()=1]")
	List<WebElement> availableDates;

	@FindBy(xpath = "//div[@class='disabled-wrap date-input'][position()=1]")
	WebElement toDateInput;

	@FindBy(xpath = "//div[@class='dropdown-handle']")
	WebElement passengersDropdown;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Adults')]//div[@class='details-controls']//button[@ng-click='$ctrl.increment()']")
	WebElement adultsIncrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Adults')]//div[@class='details-controls']//button[@ng-click='$ctrl.decrement()']")
	WebElement adultsDecrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Teens')]//div[@class='details-controls']//button[@ng-click='$ctrl.increment()']")
	WebElement teensIncrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Teens')]//div[@class='details-controls']//button[@ng-click='$ctrl.decrement()']")
	WebElement teensDecrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Children')]//div[@class='details-controls']//button[@ng-click='$ctrl.increment()']")
	WebElement childrenIncrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Children')]//div[@class='details-controls']//button[@ng-click='$ctrl.decrement()']")
	WebElement childrenDecrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Infants')]//div[@class='details-controls']//button[@ng-click='$ctrl.increment()']")
	WebElement infantsIncrement;

	@FindBy(xpath = "//div[@class='details' and contains(.,'Infants')]//div[@class='details-controls']//button[@ng-click='$ctrl.decrement()']")
	WebElement infantsDecrement;

	@FindBy(xpath = "//text()[.='OK']/ancestor::button[1]")
	WebElement infantPopUp;

	@FindBy(xpath = "//span[@class='terms-conditions-checkbox-span']")
	WebElement termsConditionChkBox;

	@FindBy(xpath = "//button[@class='core-btn-primary core-btn-block core-btn-big']")
	WebElement goButton;

	String travelTipOkBtn_XPATH = "//span[@translate='foh.home.flight_search_errors.confirm'][text()='OK']";

	public RyanairHomePageObjFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void oneWayRadioButtonClick() {
		oneWayRadioButton.click();
	}

	public void fromToAirportDetails(String fromAirport, String toAirport) throws Exception {
		departureAirportInput.clear();
		departureAirportInput.sendKeys(fromAirport);
		departureAirportInput.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		destinationAirportInput.clear();
		destinationAirportInput.sendKeys(toAirport);
		destinationAirportInput.sendKeys(Keys.TAB);
	}

	public void setFlyDate() throws Exception {
		Thread.sleep(1000);
		availableDates.get(0).click();
	}

	public void addPassengers(int adults, int teens, int children, int infants) {
		passengersDropdown.click();

		for (int i = 1; i < adults; i++) {
			adultsIncrement.click();

		}
		for (int i = 0; i < teens; i++) {
			teensIncrement.click();

		}
		for (int i = 0; i < children; i++) {
			childrenIncrement.click();

		}

		for (int i = 0; i < infants; i++) {
			infantsIncrement.click();
			if (infantPopUp.isDisplayed()) {
				infantPopUp.click();
			}

		}

	}

	public void proceedFromHome() throws Exception {
		Thread.sleep(2000);
		passengersDropdown.click();
		termsConditionChkBox.click();
		goButton.click();
        
		List<WebElement> travelTipOkBtn = driver.findElements(By.xpath(travelTipOkBtn_XPATH));
		
		if (travelTipOkBtn.size() > 0) {
			travelTipOkBtn.get(0).click();
		}
	}

}
