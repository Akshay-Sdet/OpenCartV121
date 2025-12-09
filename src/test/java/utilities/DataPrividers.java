package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataPrividers 
{
	//Dataprovider1
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_Logindata.xlsx"; // xl file from test data
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object of ExcelUtility 
		  
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] =new String[totalrows][totalcols];
		
		for(int i=1 ; i<totalrows; i++)
		{
			for (int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getcelldata("Sheet1", i, j);
			}
		}
		return logindata;
	}
	
	//Dataprovider2
	
	
	//Dataprovider3
	
	
	//Dataprovider4
	
	
}
