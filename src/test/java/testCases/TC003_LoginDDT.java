package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataPrividers;

public class TC003_LoginDDT extends BaseClass
{
	@Test(dataProvider = "LoginData",dataProviderClass=DataPrividers.class,groups = "Datadriven")//dataProviderClass=DataPrividers.class bcse not its present is same class
																		//so classname.class								
	public void verify_loginDDT(String email,String password,String exp) 
	{
		
		logger.info("**** Strting TC003_LoginDDT ****");
		try
		{
			HomePage hp = new  HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//loginpage
			LoginPage lp = new LoginPage(driver);
			lp.setemail(email);
			lp.setpassword(password);
	
			lp.clickbtn();
	
			//validation
			MyAccountPage maccp= new MyAccountPage(driver);
			boolean targetpage	=maccp.isMyAccountPageExist();
			
			
			// data is valid - login success - pass
		    //				 - login failed  - fail
			// data is invalid - login success - fail
		    //				 - login failed  - pass
			
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true)
				{
					Assert.assertTrue(true);
					maccp.clickLogOut();
				}
				else
				{
					Assert.assertTrue(false);
				}	
			}	
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetpage==true)
				{
					maccp.clickLogOut();
					Assert.assertTrue(false);	
				}
				else
				{
					
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****  TC003_LoginDDT ****");
	}
}
