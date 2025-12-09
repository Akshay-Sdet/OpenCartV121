package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{	
	@Test(groups = {"Sanity","Master"})
	public void verify_Account_Registration()
	{ 
			logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try
		{
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on my account Link");
			hp.clickRegister();
			logger.info("Clicked on my Register Link");
			
			AccountRegistrationPage regpag =new AccountRegistrationPage(driver);
			
			logger.info("providing customer details");
			regpag.setFirstName(randomeString().toUpperCase()); 
			regpag.setLastName(randomeString().toUpperCase());
			regpag.setEmail(randomeString()+"@gmail.com");
			regpag.setTelephone(randomNumber());
			
			String	password=randomAlphaNumeric();
			
			regpag.setPassword(password);
			regpag.setConfirmPassword(password); 
			regpag.setPrivacyPolicy();
			regpag.ClickContinue();
			
			logger.info("Validating Expected message..");
			String confmsg=regpag.getConfirmationMsg();
			
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed");
				logger.debug("Debug logs");
				Assert.assertTrue(false);
			}
			
			
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
		
	}

	
}
