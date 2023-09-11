package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver = driver;
	}
	//define repository
	@FindBy(xpath = "//b[normalize-space()='PIM']")
	WebElement clickPim;
	@FindBy(xpath = "//input[@id='btnAdd']")
	WebElement clickAdd;
	@FindBy(name = "firstName")
	WebElement fName;
	@FindBy(name = "middleName")
	WebElement mname;
	@FindBy(name = "lastName")
	WebElement lname;
	@FindBy(name = "employeeId")
	WebElement BeforeEID;
	@FindBy(id = "btnSave")
	WebElement clickSave;
	@FindBy(name = "personal[txtEmployeeId]")
	WebElement AfterEID;
	//method for adding employee
	public boolean addEmp(String firstName,String middleName,String lastName)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.clickPim).click().perform();
		ac.moveToElement(this.clickAdd).click().perform();
		this.fName.sendKeys(firstName);
		this.mname.sendKeys(middleName);
		this.lname.sendKeys(lastName);
		String expectedEID = this.BeforeEID.getAttribute("value");
		ac.moveToElement(this.clickSave).click().perform();
		String actualEID = this.AfterEID.getAttribute("value");
		if(expectedEID.equals(actualEID))
		{
			Reporter.log("Emp login success"+expectedEID+"   "+actualEID,true);
			return true;
		}
		else
		{
			Reporter.log("Emp login failed"+expectedEID+"   "+actualEID,true);
			return false;
		}	
	}
}
