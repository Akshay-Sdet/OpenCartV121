package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkRepoter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repoName;
	
		public void onStart(ITestContext testcontext)
		  {
			/*
			    SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			    Date dt= new Date();
			    String currenydatetimestamp=df.format(dt);
			*/
			    String  timeStampe=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			    repoName = "Test-Repost-"+ timeStampe +".html";
			    sparkRepoter = new ExtentSparkReporter(".\\reports\\"+repoName) ;
			    
			    sparkRepoter.config().setDocumentTitle("Opencart Automation Report");
			    sparkRepoter.config().setReportName("Opencart Function Testing");
			    sparkRepoter.config().setTheme(Theme.DARK);
			    
			    extent= new ExtentReports();
			    extent.attachReporter(sparkRepoter);
			    extent.setSystemInfo("Aplication", " Opencart");
			    extent.setSystemInfo("Module", " Admin");
			    extent.setSystemInfo("Sub Module", " Customer");
			    extent.setSystemInfo("User Name", System.getProperty("user.name"));
			    extent.setSystemInfo("Environment", " QA");
			    
			    
			    String os=testcontext.getCurrentXmlTest().getParameter("os");
			    extent.setSystemInfo("Operating System", os);
			    
			    String browser=testcontext.getCurrentXmlTest().getParameter("browser");
			    extent.setSystemInfo("Browser", browser);
			    
			    List<String> includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
			    if(!includedGroups.isEmpty())
			    {
			    	 extent.setSystemInfo("Groups", includedGroups.toString());
			    }
	    
		  }

		 
		public void onTestSuccess(ITestResult result)
		  {
		    test=extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.PASS,result.getName()+" Got sucussfully exucated");
		  }

		
		public void onTestFailure(ITestResult result)
		  {
		    test=extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    
		    test.log(Status.FAIL, result.getName()+" Got Failed ");
		    test.log(Status.INFO, result.getThrowable().getMessage());
		    
		    try
		    {
			    String imagPath=new BaseClass().captureScreen(result.getName());
			    test.addScreenCaptureFromPath(imagPath);
		    }
		    catch(IOException e1)
		    {
		    	e1.printStackTrace();
		    }
		    
		    
		  }

		
		public void onTestSkipped(ITestResult result)
		  {
			 test=extent.createTest(result.getClass().getName());
			 test.assignCategory(result.getMethod().getGroups());
			 
			  test.log(Status.SKIP, result.getName()+" Got SKipped ");
			    test.log(Status.INFO, result.getThrowable().getMessage());
		  }


		public void onFinish(ITestContext context)
		  {
			extent.flush();
			
			String pathOfExtentReport=System.getProperty("user.dir"+"\\reports\\"+repoName);
			File extentReport = new File(pathOfExtentReport);
			
			try 
			{
				Desktop.getDesktop().browse(extentReport.toURI());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
				
			
			
			
		  }
}
