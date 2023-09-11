package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
//define repository
	@FindBy(name = "txtUsername")
	WebElement objUser;
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement objPass;
	@FindBy(id="btnLogin")
	WebElement objLogin;
	
	//Write a method
	public void adminLogin(String username,String password)
	{
		objUser.clear();
		objUser.sendKeys(username);
		objPass.clear();
		objPass.sendKeys(password);
		objLogin.click();
	}
}
