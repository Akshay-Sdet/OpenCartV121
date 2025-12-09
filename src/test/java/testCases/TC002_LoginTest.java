package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups = {"Regression","Master"})
	public void verify_login()
	{
		
		logger.info("*****Starting TC002_loginTest*****");
		
		try 
		{
			//homepage
				HomePage hp = new  HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				Thread.sleep(2000);
				
				//loginpage
				LoginPage lp = new LoginPage(driver);
				
				Thread.sleep(2000);
				
				lp.setemail(p.getProperty("email"));// fatching from Config.xml
				
				Thread.sleep(2000);
				
				lp.setpassword(p.getProperty("password"));// fatching from Config.xml
				
				Thread.sleep(2000);
				
				lp.clickbtn();
				
				Thread.sleep(2000);
				
				//validation
				MyAccountPage maccp= new MyAccountPage(driver);
				boolean targetpage	=maccp.isMyAccountPageExist();
				//Assert.assertEquals(targetpage, true , "Login failed");
				Assert.assertTrue(targetpage);
		}
		catch (Exception e) 
		{
			Assert.fail();
		}
			
	  logger.info("*****completed TC002_loginTest*****");
			
	}
}
