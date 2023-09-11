package applicationLayer;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelFileUtil;

public class DriverScript {
	String inputpath ="./FileInput/Employee.xlsx";
	String outputpath ="./FileOutput/PomResults.xlsx";
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//access Login page class
		LoginPage login =PageFactory.initElements(driver, LoginPage.class);
		//call login method
		login.adminLogin("Admin", "Qedge123!@#");
	}
	@Test
	public void startTest() throws Throwable
	{
		report= new ExtentReports("./Reports/FunctionalityTest.html");
		logger=report.startTest("Adding Employess");
		logger.assignAuthor("Ankita");
		logger.assignCategory("OrangeHRM Functional Testing");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("EmpData");
		Reporter.log("No of rows::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			String fname =xl.getCellData("EmpData", i, 0);
			String mname = xl.getCellData("EmpData", i, 1);
			String lname = xl.getCellData("EmpData", i, 2);
			AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
			boolean res =emp.addEmp(fname, mname, lname);
			if(res)
			{
				xl.setCellData("EmpData", i, 3, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Employee"+i+" added successfully");
			}
			else
			{
				xl.setCellData("EmpData", i, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Failed to add employee");
			}
		}
		report.endTest(logger);
		report.flush();
	}
	@AfterTest
	public void tearDown()
	{
		LogoutPage logout =PageFactory.initElements(driver, LogoutPage.class);
		logout.logoutApp();
		driver.quit();
	}

}
