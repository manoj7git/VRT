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

public class sensorCofigUtility extends BaseClass{
	
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/TestData/" + "SensorConfigTestdata.xlsx";

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
	//Sensor Configuration module related Test Data reference
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "TcSC004")
	public static Object[][] SC004() {
		String sheetName = "TcSC004";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC005")
	public static Object[][] SC005() {
		String sheetName = "TcSC005";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC006")
	public static Object[][] SC006() {
		String sheetName = "TcSC006";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC007")
	public static Object[][] SC007() {
		String sheetName = "TcSC007";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC012")
	public static Object[][] SC0012() {
		String sheetName = "TcSC012";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC013")
	public static Object[][] SC0013() {
		String sheetName = "TcSC013";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC014")
	public static Object[][] SC0014() {
		String sheetName = "TcSC014";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC015")
	public static Object[][] SC0015() {
		String sheetName = "TcSC015";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC020")
	public static Object[][] SC0020() {
		String sheetName = "TcSC020";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC021")
	public static Object[][] SC021() {
		String sheetName = "TcSC021";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC022")
	public static Object[][] SC022() {
		String sheetName = "TcSC022";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC023")
	public static Object[][] SC023() {
		String sheetName = "TcSC023";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC041")
	public static Object[][] SC041() {
		String sheetName = "TcSC041";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC042")
	public static Object[][] SC042() {
		String sheetName = "TcSC042";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC043")
	public static Object[][] SC043() {
		String sheetName = "TcSC043";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "TcSC046")
	public static Object[][] SC046() {
		String sheetName = "TcSC046";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC046a")
	public static Object[][] SC046a() {
		String sheetName = "TcSC046a";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC048")
	public static Object[][] SC048() {
		String sheetName = "TcSC048";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC049")
	public static Object[][] SC049() {
		String sheetName = "TcSC049";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC050")
	public static Object[][] SC050() {
		String sheetName = "TcSC050";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC052")
	public static Object[][] SC052() {
		String sheetName = "TcSC052";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC053")
	public static Object[][] SC053() {
		String sheetName = "TcSC053";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC056")
	public static Object[][] SC056() {
		String sheetName = "TcSC056";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC058")
	public static Object[][] SC058() {
		String sheetName = "TcSC058";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC059")
	public static Object[][] SC059() {
		String sheetName = "TcSC059";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC062")
	public static Object[][] SC062() {
		String sheetName = "TcSC062";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	@DataProvider(name = "TcSC063")
	public static Object[][] SC063() {
		String sheetName = "TcSC063";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "TcSC064")
	public static Object[][] SC064() {
		String sheetName = "TcSC064";
		Object[][] data = getTestData(sheetName);
		return data;
	}
}
