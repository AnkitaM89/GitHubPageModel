package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
	@FindBy(xpath = "//a[@id='welcome']")
	WebElement clickWelcome;
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement clickLogout;
	public void logoutApp()
	{
		clickWelcome.click();
		clickLogout.click();
	}
}