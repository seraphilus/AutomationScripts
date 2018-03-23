package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginDetailsObjFactory {
	
	WebDriver driver;
	
	@FindBy(xpath = "//ul[@class='e-menu menu-user']//a[@ui-sref='login']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@class='form-field']/input[@type='email']")
	WebElement loginEmail;

	@FindBy(xpath = "//div[@class='form-field password']//input[@name='password']")
	WebElement loginPass;

	@FindBy(xpath = "//div[@class='modal-form-container']//input[@type='checkbox']")
	WebElement keepMeLoggedChk;

	@FindBy(xpath = "//div[@class='modal-form-group']//button[@type='submit']")
	WebElement loginButtonFinal;

	public LoginDetailsObjFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String email, String password) throws Exception {
		loginButton.click();
		loginEmail.clear();
		loginEmail.sendKeys(email);

		loginPass.clear();
		loginPass.sendKeys(password);

		keepMeLoggedChk.click();
		loginButtonFinal.click();
		Thread.sleep(2000);
		
		

	}
	
	

}
