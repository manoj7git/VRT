/**
 * @author manoj.ghadei
 *
 */

package com.vrt.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;


import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.vrt.base.BaseClass;

public class TestUtilities extends BaseClass{
	
	public String convert_StringDate_to_ActualDate_inCertainFormat(String dateString) throws ParseException {	   
	    
	    SimpleDateFormat formating = new SimpleDateFormat("dd MMM yyyy"); 
	    String dateinString = dateString;
	    //System.out.println(dateString);
	    Date date = formating.parse(dateinString);
	    
	    //System.out.println(date);
	    //System.out.println(formating.format(date));
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-d-yyyy"); 	    
	    String strDate = formatter.format(date);
	    //System.out.println("Date Format with MM-d-yyyy : "+strDate);
	    return strDate;    
	    
	}
	
	
	//Method to call the below method to capture screenshot when a Test Fails
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\manoj.ghadei\\git\\VRT\\VRT\\Screenshots" + "_" + timestamp + ".png"));
		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png"));
	}
	
	
	public static String getFailedTCScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public static String getPassTCScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/PassTCScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	//Read TestData from the Excel sheet
	public static String TestData_sheetPath = "C:\\Users\\manoj.ghadei\\git\\VRT\\VRT\\TestData\\AssetNameTestData.xlsx";	
	static Workbook book;
	static Sheet sheet;

	
	//Read TestData from the Excel sheet
	public static Object[][] getTestData(String sheetName){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(TestData_sheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" +
		sheet.getRow(0).getLastCellNum());
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				//System.out.println(data[i][j]);
			}
		}
		return data;
	}
	
	//Data Providers
	@DataProvider(name="getAstNameInvalidTestData")
	public static Object[][] getAstNameInvalidTestData() {
		
		//"AstName" sheet is the sheet name from which Data related to Asset Name field is read
		String sheetName = "InvalidAstName";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstNameValidTestData")
	public static Object[][] getAstNameValidTestData() {
				
		String sheetName = "ValidAstName";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getEqpIDinValidTestData")
	public static Object[][] getEqpIDinValidTestData() {		
		String sheetName = "InvalidEqpID";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getEqpIDValidTestData")
	public static Object[][] getEqpIDValidTestData() {				
		String sheetName = "ValidEqipID";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstTypeInValidTestData")
	public static Object[][] getAstTypeInValidTestData() {				
		String sheetName = "InvalidAstType";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstTypeValidTestData")
	public static Object[][] getAstTypeValidTestData() {				
		String sheetName = "ValidAstType";    
		Object[][] data = getTestData(sheetName);
		return data;
	}	
	
	@DataProvider(name="getAstMdlValidTestData")
	public static Object[][] getAstMdlValidTestData() {				
		String sheetName = "ValidAstMdl";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstSizeInValidTestData")
	public static Object[][] getAstSizeInValidTestData() {				
		String sheetName = "InvalidAstSize";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstSizeValidTestData")
	public static Object[][] getAstSizeValidTestData() {				
		String sheetName = "ValidAstSize";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstSizeUnitInValidTestData")
	public static Object[][] getAstSizeUnitInValidTestData() {				
		String sheetName = "InvalidAstSizeUnit";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstSizeUnitValidTestData")
	public static Object[][] getAstSizeUnitValidTestData() {				
		String sheetName = "ValidAstSizeUnit";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstMakerInValidTestData")
	public static Object[][] getAstMakerInValidTestData() {				
		String sheetName = "InvalidAstMaker";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstMakerValidTestData")
	public static Object[][] getAstMakerValidTestData() {				
		String sheetName = "ValidAstMaker";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstLocationInValidTestData")
	public static Object[][] getAstLocationInValidTestData() {				
		String sheetName = "InvalidAstLocation";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstLocationValidTestData")
	public static Object[][] getAstLocationValidTestData() {				
		String sheetName = "ValidAstLocation";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="getAstALLData")
	public static Object[][] getAstALLData() {				
		String sheetName = "AllAssetDetails";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="tc147")
	public static Object[][] tc147() {				
		String sheetName = "tc147";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="tc149")
	public static Object[][] tc149() {				
		String sheetName = "tc149";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="tcasst011")
	public static Object[][] tcasst011() {				
		String sheetName = "tcasst011";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name="tcasst014")
	public static Object[][] tcasst014() {				
		String sheetName = "tcasst014";    
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	

	

}
