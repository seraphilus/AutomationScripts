package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PassengerAndPaymentDetailsObjFactory {

	WebDriver driver;

	@FindBy(xpath = "//select[contains(@id,'title')]")
	List<WebElement> titleSelectors;

	@FindBy(xpath = "//input[contains(@id,'firstName')]")
	List<WebElement> firstNameSelectors;

	@FindBy(xpath = "//input[contains(@id,'lastName')]")
	List<WebElement> lastNameSelectors;

	@FindBy(xpath = "//passenger-to-companion")
	List<WebElement> savePassengerCheckboxes;

	@FindBy(id = "billingAddressAddressLine1")
	WebElement billingAddress1;

	@FindBy(id = "billingAddressCity")
	WebElement billingAddressCity;

	@FindBy(id = "billingAddressCountry")
	WebElement billingAddressCountry;

	@FindBy(xpath = "//input[contains(@id,'cardNumber')]")
	WebElement creditCardNumber;

	@FindBy(xpath = "//select[contains(@id,'cardType')]")
	WebElement cardType;

	@FindBy(xpath = "//select[contains(@id,'expiryMonth')]")
	WebElement expiryMonth;

	@FindBy(name = "expiryYear")
	WebElement expiryYear;

	@FindBy(name = "securityCode")
	WebElement cvv;

	@FindBy(name = "cardHolderName")
	WebElement cardHolderName;

	@FindBy(name = "acceptPolicy")
	WebElement termsAgreementCheckbox;

	@FindBy(xpath = "//div[@class='cta']//button")
	WebElement payNowButton;

	@FindBy(xpath = "//select[@name='phoneNumberCountry']")
	WebElement phoneCountry;

	@FindBy(xpath = "//input[@name='phoneNumber']")
	WebElement phoneNumber;

	public PassengerAndPaymentDetailsObjFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillPassengerDetails() throws Exception {
		String name = "It";
		for (int i = 0; i < titleSelectors.size(); i++) {

			Select titleSelector = new Select(titleSelectors.get(i));
			titleSelector.selectByValue("string:MR");

			firstNameSelectors.get(i).sendKeys(name);
			lastNameSelectors.get(i).sendKeys("Works");
			Thread.sleep(1000);
			savePassengerCheckboxes.get(i).click();

			name = name + "t";
		}
	}

	public void fillContactDetails() {
		Select selectPhoneCountry = new Select(phoneCountry);
		selectPhoneCountry.selectByVisibleText("Ireland");

		phoneNumber.sendKeys("12345678");

	}

	public void fillBillingAddress() throws Exception {
		billingAddress1.sendKeys("Test");
		billingAddressCity.sendKeys("Dublin");

	}

	public void fillPaymentDetails(CreditCardDetails creditCard) {
		creditCardNumber.sendKeys(creditCard.cardNumber);
		Select cardTypeSelector = new Select(cardType);
		cardTypeSelector.selectByValue(creditCard.cardType);
		Select expiryMonthSelector = new Select(expiryMonth);
		expiryMonthSelector.selectByVisibleText(Integer.toString(creditCard.expiryMonth));
		Select expiryYearSelector = new Select(expiryYear);
		expiryYearSelector.selectByVisibleText(Integer.toString(creditCard.expiryYear));

		cvv.sendKeys(Integer.toString(creditCard.cvv));
		cardHolderName.sendKeys(creditCard.cardholderName);

	}

	public void submitPayment() throws Exception {

		termsAgreementCheckbox.sendKeys(Keys.SPACE);
		;
		Thread.sleep(1000);
		payNowButton.click();

	}

}
