package pageObjects;

public class CreditCardDetails {
	
	String cardNumber;
	String cardType;
	int expiryMonth;
	int expiryYear;
	int cvv;
	String cardholderName;
	
	public CreditCardDetails(String cardNumber, String cardType, int expiryMonth, int expiryYear, int cvv,
			String cardholderName) {
		super();
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
		this.cardholderName = cardholderName;
	}
	
	

}
