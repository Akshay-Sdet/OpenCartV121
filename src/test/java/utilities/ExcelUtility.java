package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public static 	FileInputStream fi;
	public static 	FileOutputStream fo;
	public static	XSSFWorkbook workbook;
	public static	XSSFSheet sheet;
	public static	XSSFRow row;
	public static	XSSFCell cell;
	public static	CellStyle style;
	  String path;
	
	public ExcelUtility(String path)
	{
		this.path = path ;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public  int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	
	
	
	public  String getcelldata(String sheetName,int rownum,int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);
		cell=row.getCell(column);
		String data;
		try 
		{
			data=cell.toString();
		} 
		catch (Exception e) 
		{
			data= "";
		}
	
		workbook.close();
		fi.close();
		
		return data;
	}
	
	//we are doung both reading as well ass writing
	
	public  String setcelldata(String sheetName,int rownum,int column,String data) throws IOException
		{
			File xlfile= new File(path);
			if (!xlfile.exists()) //if file is not exist create new file
			{
				workbook= new XSSFWorkbook(fi);
				fo= new FileOutputStream(xlfile);
				workbook.write(fo);
			}
			fi = new FileInputStream(path);
			workbook= new XSSFWorkbook(fi);
			
			if(workbook.getSheetIndex(sheetName)==-1) // sheet is not exist create sheet
			{
				workbook.createSheet(sheetName);
				sheet=workbook.getSheet(sheetName);
			}
			
			if (sheet.getRow(rownum)==null)	// row is not exist create sheet
			{
				sheet.createRow(rownum);
				row=sheet.getRow(rownum);
			}
			
			cell=row.createCell(column);//creating column
			
			cell.setCellValue(data); //passing data
			
			//writing data
			fo= new FileOutputStream(path);
			
			workbook.write(fo);
			
			workbook.close();
			fi.close();
			
			return data;
		}
	
	public  void setgreencolor(String sheetName,int rownum,int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);//get row
		cell=row.getCell(column);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		//writing data
		fo= new FileOutputStream(path);
		
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		
	}
	public  void setredcolor(String sheetName,int rownum,int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);//get row
		cell=row.getCell(column);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		//writing data
		fo= new FileOutputStream(path);
		
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		
		
	}
}
