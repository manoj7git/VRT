package com.vrt.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.vrt.base.BaseClass;

public class setupCreationUtility extends BaseClass{
	
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/TestData/" + "SetupTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	// Read TestData from the Excel sheet
	public static Object[][] getTestData(String sheetName) {
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
		System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	// Data Providers
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//SETUP Creation module related Test Data reference
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "SET003")
	public static Object[][] SET003() {
		String sheetName = "SET003";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET004")
	public static Object[][] SET004() {
		String sheetName = "SET004";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET005")
	public static Object[][] SET005() {
		String sheetName = "SET005";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET008")
	public static Object[][] SET008() {
		String sheetName = "SET008";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET009")
	public static Object[][] SET009() {
		String sheetName = "SET009";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET010")
	public static Object[][] SET010() {
		String sheetName = "SET010";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET015")
	public static Object[][] SET015() {
		String sheetName = "SET015";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET016")
	public static Object[][] SET016() {
		String sheetName = "SET016";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET017")
	public static Object[][] SET017() {
		String sheetName = "SET017";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET018")
	public static Object[][] SET018() {
		String sheetName = "SET018";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "SET019")
	public static Object[][] SET019() {
		String sheetName = "SET019";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "SET020")
	public static Object[][] SET020() {
		String sheetName = "SET020";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET022")
	public static Object[][] SET022() {
		String sheetName = "SET022";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
