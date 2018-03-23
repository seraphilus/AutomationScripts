package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightChoiceObjFactory {
	
	WebDriver driver;
	
    private String availableFlight_XPATH = "//div[@class='wrapper']/div[@class='scroller']/div[contains(@class,'slide')][%s]";
	
	private String availableFlights_XPATH = "//div[@class='flights-table__rows']//div[@class='flight-header__min-price hide-mobile']";
	
	@FindBy(xpath = "//div[@class='wrapper']/div[@class='scroller']/div[@class='slide'][1]")
	WebElement availableFlight;

	@FindBy(xpath = "//div[@class='flight-header__min-price hide-mobile']")
	WebElement flightPriceButton;

	@FindBy(xpath = "//div[@class='flights-table-fares']//div[@class='flights-table-fares__fare fare-select standard']")
	WebElement standardFare;

	@FindBy(xpath = "//div[@class='flights-table-fares']//div[@class='flights-table-fares__fare fare-select leisure-plus middle']")
	WebElement plusFare;

	@FindBy(xpath = "//div[@class='flights-table-fares']//div[@class='flights-table-fares__fare fare-select business-plus last recommended']")
	WebElement flexiPlusFare;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement selectFlightContinue;

	@FindBy(xpath = "//div[@class='trips-basket trips-cnt']//button")
	WebElement checkOut;
	
	@FindBy(xpath = "//div[@class='popup-msg__button-wrapper']//button[.='No thanks']")
	WebElement addSeatsNoTy;
	
	
	
	public FlightChoiceObjFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectFirstAvailableFlight() throws Exception {
		Thread.sleep(500);

		boolean flightFound = false;
		int position = 2;
		while (!flightFound) {
			String xpathAvailableFlight = String.format(availableFlight_XPATH, position++);
			WebElement availableFlightDate = driver.findElement(By.xpath(xpathAvailableFlight));
		    availableFlightDate.click();
			Thread.sleep(1000);
			List<WebElement> availableFlights = driver.findElements(By
					.xpath(availableFlights_XPATH));
			for (int i = 0; i < availableFlights.size(); i++) {
				if (ExpectedConditions.elementToBeClickable(availableFlights.get(i)) != null) {
					availableFlights.get(i).click();
					Thread.sleep(500);
					flightFound = true;
					break;
				}
			}
		}
	}

	public void chooseFare(FareType fareType) throws Exception {
		if (fareType == FareType.StandardFare) {
			standardFare.click();
		} else if (fareType == FareType.Plus) {
			plusFare.click();
		}
		if (fareType == FareType.FlexiPlus) {
			flexiPlusFare.click();
		}
		Thread.sleep(1000);
		selectFlightContinue.click();
		
	}
	
	public void checkOut() throws Exception {
		Thread.sleep(1000);
		checkOut.click();
		Thread.sleep(1000);
		if(driver.findElements(By.xpath("//div[@class='popup-msg__button-wrapper']//button[.='No thanks']")).size() != 0) {
			addSeatsNoTy.click();
		}
	}
	


}
