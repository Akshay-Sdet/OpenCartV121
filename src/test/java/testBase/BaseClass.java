package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	public Properties p;
	public Logger logger;//log4j
	
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"}) //this is bcs of in testng.xml file have give parameters those we are passing here.
	public void setUp(String os, String br) throws IOException
	{
		//this is for config.properties help to access data 
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p= new Properties();
		p.load(file);
		
		//this is log generate (facing issue in pckg import so not working)
		logger=LogManager.getLogger(this.getClass());
		
		//this will work as per parmeter data passes in testng.xml file
		switch(br.toLowerCase())
		{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver();
			default : System.out.println("Invalid browser name..."); return;
		}
	//	driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));	// reading URL from property file
	}

	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatedNumber =RandomStringUtils.randomNumeric(10);
		return generatedNumber ;
	}
	public String randomAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String generatedNumber =RandomStringUtils.randomNumeric(3);
		return generatedstring+generatedNumber ;
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeSCreenShot = (TakesScreenshot)driver;
		 File sourceFile=takeSCreenShot.getScreenshotAs(OutputType.FILE);
		 
		 String targetFilePath=System.getProperty("user.dir", "\\screenshots\\")+ tname + " " + timeStamp + ".png";
		 
		 File targetFile= new File(targetFilePath);
		 
		 
		 sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
}

