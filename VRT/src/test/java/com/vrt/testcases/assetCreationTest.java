/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
//import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;


public class assetCreationTest extends BaseClass{
	
	//Refer TestUtilities Class for Data provider methods
	//Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p	
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	//Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	
	
	//Ensure the User has got rights to create Assets
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");

		/*// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");*/
		
		
		/*LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"), getPW("adminFull"), "FullAdmin",
				"12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");

		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();

		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		AppClose();
		Thread.sleep(1000);*/

	}
		
	//After All the tests are conducted
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

	//Before all tests are conducted 
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage= new LoginPage();
		MainHubPage=LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}
	
	// TearDown of the App
	@AfterMethod(alwaysRun=true)
	public void Teardown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getName()+" #"); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getThrowable()+" #"); //to add error/exception in extent report
			
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName()+" #");
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report
		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		
		driver.quit();
	}

	//~~~~~~~~~~
	//Test Cases
	//~~~~~~~~~~
	
	/*
	//ASST01-Verify if 25 max characters allowed in Asset name field
	@Test(groups = {"Sanity", "Regression"}, 
			description="ASST01-Verify if 25 max characters allowed in Asset name field")
	public void ASST01() throws InterruptedException {
		extentTest = extent.startTest("ASST01-Verify if 25 max characters allowed in Asset name field");
		SoftAssert sa1 = new SoftAssert();
		String expectedtxt = "12345678901234567890123456";  //26 Char input
		//System.out.println("count of Asset name text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetName();
		//System.out.println("count of Assent name text entered: "+actualtextentered.length());
		
		sa1.assertEquals(actualtextentered.length(), 25, "FAIL: Asset name accepts more than 25 characters");
		sa1.assertAll();
	}
	

	//ASST02-Verify the valid inputs accepted in Asset name field
	@Test(dataProvider="ASST02", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST02-Verify the valid inputs accepted in Asset name field")
	public void ASST02(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST02-Verify the valid inputs accepted in Asset name field");
		SoftAssert sa3 = new SoftAssert();
		
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4]; 
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa3.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"Fail: Asset Name not accepting the Valid characters");		
		sa3.assertAll();
	}
	
	
	//ASST03-Verify the invalid inputs in Asset name field
	@Test(groups = {"Sanity", "Regression"},dataProvider="ASST03", dataProviderClass=assetCreationUtility.class, 
			description="ASST03-Verify the invalid inputs in Asset name field")
	public void ASST03(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST03-Verify the invalid inputs in Asset name field");
		SoftAssert sa2 = new SoftAssert();
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4];
		String ExpAlrtMsg = (String) dataProvider[5]; 
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActAlertMsg = assetCreationPage.AlertMsg();
		
		sa2.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Asset Name field accepting invalid characters");		
		sa2.assertAll();
	}
	
	
	//ASST04-Verify if 15 max characters allowed in Asset ID field
	@Test(groups = {"Sanity", "Regression"}, 
			description="ASST04-Verify if 15 max characters allowed in Asset ID field")
	public void ASST04() throws InterruptedException {
		extentTest = extent.startTest("ASST04-Verify if 15 max characters allowed in Asset ID field");
		SoftAssert sa4 = new SoftAssert();
		String expectedtxt = "123456789012345a";  //16 Char input
		System.out.println("count of Equipment ID text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetID(expectedtxt);
		String actualtextentered = assetCreationPage.getEqpID();
		System.out.println("count of Equipment ID text entered: "+actualtextentered.length());
		
		sa4.assertEquals(actualtextentered.length(), 15, "FAIL: Asset ID accepts more than 15 characters");
		sa4.assertAll();
	}
	
	
	//ASST05-Verify the valid inputs accepted in Asset ID field
	@Test(dataProvider="ASST05", dataProviderClass=assetCreationUtility.class, groups = {"Sanity", "Regression"}, 
			description="ASST05-Verify the valid inputs accepted in Asset ID field")
	public void ASST05(String Name, String ID, String Type, String Manufacturer, 
			String Location, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST05-Verify the valid inputs accepted in Asset ID field");
		SoftAssert sa5 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa5.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"FAIL: Asset ID do not acceit valid characters");		
		sa5.assertAll();
	}
	
	
	//ASST06-Verify the invalid inputs in Asset ID field
	@Test(groups = {"Sanity", "Regression"}, dataProvider="ASST06", dataProviderClass=assetCreationUtility.class, 
			description="ASST06-Verify the invalid inputs in Asset ID field")
	public void ASST06(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST06-Verify the invalid inputs in Asset ID field");
		SoftAssert sa4 = new SoftAssert();
		
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4]; 
		String ExpAlrtMsg = (String) dataProvider[5]; 
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActAlertMsg = assetCreationPage.AlertMsg();
		
		sa4.assertEquals(ActAlertMsg, ExpAlrtMsg, "Fail: Asset ID field accepts Invalid characters");		
		sa4.assertAll();
	}
		
	
	//ASST07-Verify the drop down list values for Asset Type field
	@Test(groups = {"Sanity", "Regression"}, 
			description="ASST07-Verify the drop down list values for Asset Type field")
	public void ASST07() {
		extentTest = extent.startTest("ASST07-Verify the drop down list values for Asset Type field");
		SoftAssert sa6 = new SoftAssert();
		
		sa6.assertEquals(assetCreationPage.getAssetTypetext(), "Select", 
				"FAIL: Select is not the default Asset Type selected data");
		sa6.assertAll();
	}

	
	//ASST08-Verify if 50 max character length for Asset Type field
	@Test(groups = {"Sanity", "Regression"}, 
			description="ASST08-Verify if 50 max character length for Asset Type field")
	public void ASST08() {
		extentTest = extent.startTest("ASST08-Verify if 50 max character length for Asset Type field");
		SoftAssert sa8 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetType(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetTypetext();
		System.out.println("count of Asset Type text entered: "+actualtextentered.length());
		
		sa8.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 50 characters");
		sa8.assertAll();
	}
	

	//ASST10-Verify the invalid inputs in Asset Type field
	@Test(dataProvider="ASST10", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="ASST10-Verify the invalid inputs in Asset Type field")
	public void ASST10(String Name, String ID, String Type, String Manufacturer, 
			String Location, String ExpAlrtMsg, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST10-Verify the invalid inputs in Asset Type field");
		SoftAssert sa9 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);		
		String ActAlertMsg = assetCreationPage.AlertMsg();
		
		sa9.assertEquals(ActAlertMsg, ExpAlrtMsg, "Fail: Asset Type field accepts Invalid characters");		
		sa9.assertAll();
	}
	
	
	//ASST09-Verify the valid inputs accepted in Asset Type field 
	@Test(dataProvider="ASST09", dataProviderClass=assetCreationUtility.class, groups = {"Sanity", "Regression"}, 
			description="ASST09-Verify the valid inputs accepted in Asset Type field")
	public void ASST09(String Name, String ID, String Type, String Manufacturer, 
			String Location, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST09-Verify the valid inputs accepted in Asset Type field");
		SoftAssert sa10 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);	
		
		sa10.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"Fail: Asset Type field do not accept valid characters");		
		sa10.assertAll();
	}
	
	
	//ASST11-Verify if the Asset types are sorted in alphabetic order 
	@Test(groups = {"Sanity", "Functional"},
			description="ASST11-Verify if the Asset types are sorted in alphabetic order")
	public void ASST11() throws InterruptedException {
		extentTest = extent.startTest("ASST11-Verify if the Asset types are sorted in alphabetic order");
		SoftAssert sa11 = new SoftAssert();
		
		assetCreationPage.assetCreationWithType("Asset1", "1", "Freezer", "AAS", "HYBD");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithType("Asset2", "2", "Bath", "AAS", "HYBD");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("Asset3", "3", "ColdChamber", "AAS", "HYBD");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("Asset4", "4", "DeepFreezer", "AAS", "HYBD");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		

		
		String[] OriginalList = assetCreationPage.getAssetTypelist();
		//System.out.println(Arrays.toString(OriginalList));
		String[] SortedNewList = assetCreationPage.getAssetTypeSortedlist();
		//System.out.println(Arrays.toString(SortedNewList));

		sa11.assertEquals(OriginalList, SortedNewList);
		sa11.assertAll();
	}

	
	// ASST12-Verify the drop down list Manufacturer field displays the option as Select
	@Test(groups = {"Sanity" }, 
			description = "ASST12-Verify the drop down list Manufacturer field displays the option as Select")
	public void ASST12() {
		extentTest = extent.startTest("ASST12-Verify the drop down list Manufacturer field displays the option as Select");
		SoftAssert sa23 = new SoftAssert();

		sa23.assertEquals(assetCreationPage.getAssetManufacturertext(), "Select",
				"FAIL: Select is not the default Asset Manufacturer selected data");
		sa23.assertAll();
	}
	
	// ASST13- Verify if the Manufacturer name accepts up to 100 characters
	@Test(groups = { "Sanity" }, description = "Verify if the Manufacturer name accepts up to 100 characters")
	public void ASST13() throws InterruptedException {
		extentTest = extent.startTest("ASST13 - Verify if the Manufacturer name accepts up to 100 characters");
		SoftAssert sa25 = new SoftAssert();

		String expectedtxt = "123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890a"; // 101 Char input
		// System.out.println("count of Asset Manufacturer text to be entered:
		// "+expectedtxt.length());

		assetCreationPage.enterManufacturerName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetManufacturertext();
		// System.out.println("count of Asset Manufacturer text entered:
		// "+actualtextentered.length());

		sa25.assertEquals(actualtextentered.length(), 100, "FAIL: Asset Manufacturer accepts more than 100 characters");
		sa25.assertAll();
	}


	// ASST14a-Verify the valid inputs accepted in Manufacturer field upper case, lower case,
	// numeric and special characters like Hyphen, slash -Forward and backward- Underscore and
	// space as input
	@Test(dataProvider = "ASST14", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST14-Verify the valid inputs accepted in Manufacturer field")
	public void ASST14a(String Name, String ID, String Type, String Manufacturer, String Location)
			throws InterruptedException {
		extentTest = extent.startTest("ASST14-Verify the valid inputs accepted in Manufacturer field");
		SoftAssert sa27 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa27.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"FAIL: Asset Manufacturer field do not accept Valid characters ");
		sa27.assertAll();
	}
	
		// ASST14b-Verify if the Manufacturers names are sorted in alphabetic order
	@Test(groups = { "Sanity" }, description = "Verify if the Manufacturers names are sorted in alphabetic order")
	public void ASST14b() throws InterruptedException {
		extentTest = extent.startTest("ASST123 - Verify if the Manufacturers names are sorted in alphabetic order");
		SoftAssert sa28 = new SoftAssert();

		String[] OriginalList = assetCreationPage.getAssetMakerlist();
		// System.out.println(Arrays.toString(OriginalList));
		String[] SortedNewList = assetCreationPage.getAssetMakerSortedlist();
		// System.out.println(Arrays.toString(SortedNewList));

		sa28.assertEquals(OriginalList, SortedNewList);
		sa28.assertAll();
	}

	// ASST15-Verify the invalid inputs in Manufacturer field
	@Test(dataProvider = "ASST15", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST15-Verify the invalid inputs in Manufacturer field")
	public void ASST122a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		extentTest = extent
				.startTest("ASST15-Verify the invalid inputs in Manufacturer field");
		SoftAssert sa26 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa26.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg, 
				"FAIL: Asset Manufacturer field accepts InValid characters");
		sa26.assertAll();
	}


	//ASST16- Verify the drop down list values for Location field
	@Test(groups = {"Sanity"}, 
			description="ASST16- Verify the drop down list values for Location field")
	public void ASST16() throws InterruptedException {
		extentTest = extent.startTest("ASST16- Verify the drop down list values for Location field");
		SoftAssert sa34 = new SoftAssert();
		
		sa34.assertEquals(assetCreationPage.getAssetLocationtext(), "Select", 
				"FAIL: Select is not the default Asset Location selected data");		
		
		assetCreationPage.assetCreationWithType("Asset5", "5", "DeepFreezer", "AAS", "INDIA");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
		
		String[] OriginalList = assetCreationPage.getAssetLocationlist();
		//System.out.println(Arrays.toString(OriginalList));

		sa34.assertEquals(OriginalList[1], "INDIA", 
				"FAIL: Asset Location dropdown down list do not reflect the location list data");
		sa34.assertAll();
	}	
	
	
	// ASST17-Verify of 100 max character length for Location field
	@Test(groups = { "Sanity" }, description = "ASST17-Verify of 100 max character length for Location field")
	public void ASST17() throws InterruptedException {
		extentTest = extent.startTest("ASST17-Verify of 100 max character length for Location field");
		SoftAssert sa36 = new SoftAssert();

		String expectedtxt = "123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890a"; // 101 Char input
		// System.out.println("count of Asset Location text to be entered:
		// "+expectedtxt.length());

		assetCreationPage.enterLocation(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetLocationtext();
		// System.out.println("count of Asset Location text entered:
		// "+actualtextentered.length());

		sa36.assertEquals(actualtextentered.length(), 100, "FAIL: Asset Location accepts more than 100 characters");
		sa36.assertAll();
	}
		
	
	// ASST18-Verify the valid inputs accepted in Location field
	@Test(dataProvider = "ASST18", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="ASST18-Verify the valid inputs accepted in Location field")
	public void ASST18(String Name, String ID, String Type, String Manufacturer, String Location)
			throws InterruptedException {
		extentTest = extent.startTest("ASST18-Verify the valid inputs accepted in Location field");
		SoftAssert sa38 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa38.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa38.assertAll();
	}
		
	// ASST19-Verify the invalid inputs in Location field
	@Test(dataProvider = "ASST19", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST19-Verify the invalid inputs in Location field")
	public void ASST19(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		extentTest = extent.startTest("ASST19-Verify the invalid inputs in Location field");
		SoftAssert sa37 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa37.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa37.assertAll();
	}

	
	//ASST20-Verify if 50 max character length for Model field - 
	@Test(groups = {"Sanity", "Regression"}, 
			description="ASST20-Verify if 50 max character length for Model field")
	public void ASST20() {
		extentTest = extent.startTest("ASST20-Verify if 50 max character length for Model field");
		SoftAssert sa13 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterModelName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetModeltext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa13.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 50 characters");
		sa13.assertAll();
	}
	
	
	// ASST21-Verify the valid inputs accepted in Model field
	@Test(dataProvider = "ASST21", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST21-Verify the valid inputs accepted in Model field")
	public void ASST21(String Name, String ID, String Type, String Manufacturer, String Location, String Model)
			throws InterruptedException {
		extentTest = extent.startTest("ASST21-Verify the valid inputs accepted in Model field");
		SoftAssert sa15 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);

		sa15.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"FAIL: Asset Model field not accepting valid characters");
		sa15.assertAll();
	}
	
	
	// ASST22-Verify the invalid inputs in Model field
	@Test(dataProvider = "ASST22", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST22-Verify the invalid inputs in Model field")
	public void ASST22(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST22-Verify the invalid inputs in Model field");
		SoftAssert sa14 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);
		String ActAlertMsg = assetCreationPage.AlertMsg();

		sa14.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Asset Model field accepting Invaid characters");
		sa14.assertAll();
	}
	
	
	//ASST24-Verify the valid inputs accepted in Size field
	@Test(dataProvider="ASST24", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="ASST24-Verify the valid inputs accepted in Size field")
	public void ASST24(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit) throws InterruptedException {
		extentTest = extent.startTest("ASST24-Verify the valid inputs accepted in Size field");
		SoftAssert sa17 = new SoftAssert();		
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		
		sa17.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa17.assertAll();
	}
	
	
	//ASST25-Verify the invalid inputs in Size field
	@Test(dataProvider="ASST25", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="ASST25-Verify the invalid inputs in Size field")
	public void ASST25(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST25-Verify the invalid inputs in Size field");
		SoftAssert sa16 = new SoftAssert();
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa16.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa16.assertAll();
	}
	
	
	//ASST26a-Verify the drop down list values for Size-Units field
	@Test(groups = {"Sanity"}, 
			description="ASST26a-Verify the drop down list values for Size-Units field")
	public void ASST26a() throws InterruptedException {
		extentTest = extent.startTest("ASST26a-Verify the drop down list values for Size-Units field");
		SoftAssert sa22 = new SoftAssert();
		
		try {
			//
			assetCreationPage.assetCreationWithSizeUnit("7", "1", "Freezer", "GAS", "HYB", "10", "Meter Cube");
			assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			assetCreationPage.CloseAlertMsg();
			assetHubPage = assetCreationPage.clickBackBtn();
			
			assetCreationPage=assetHubPage.ClickAddAssetBtn();			

			String[] SizuUnitList = assetCreationPage.getAssetSizeUnitlist();			
			//System.out.println(Arrays.toString(SizuUnitList));
			
			for (int i = 0; i < SizuUnitList.length; i++) {
				if (SizuUnitList[i].contains("Meter Cube")) {
					sa22.assertEquals(SizuUnitList[i], "Meter Cube");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu ft")) {
					sa22.assertEquals(SizuUnitList[i], "cu ft");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu mt")) {
					sa22.assertEquals(SizuUnitList[i], "cu mt");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu in")) {
					sa22.assertEquals(SizuUnitList[i], "cu in");
					sa22.assertAll();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Create an Asset with Unique Name");
		}		
	}
	
	
	//ASST26b
	@Test(groups = {"Sanity"}, 
			description="Verify if by default the units drop down display as Select")
	public void ASST26b() {
		extentTest = extent.startTest("ASST26b-Verify if by default the units drop down display as Select");
		SoftAssert sa18 = new SoftAssert();		
		
		sa18.assertEquals(assetCreationPage.getAssetSizeUnittext(), "Select", 
				"Fail: Select is not the default Asset Size Unit selected data");
		sa18.assertAll();
	}
	
	
	//ASST26c
	@Test(groups = {"Sanity"}, 
			description="Verify if the user is able to type in the desired units")
	public void ASST26c() {
		extentTest = extent.startTest("ASST26c - Verify if the user is able to type in the desired units");
		SoftAssert sa19 = new SoftAssert();		
		
		assetCreationPage.enterAssetSizeUnit("testUnit");
		sa19.assertEquals(assetCreationPage.getAssetSizeUnittext(), "testUnit", 
				"Fail: Unable to enter Asset Size Unit data");
		sa19.assertAll();
	}
	
	
	//ASST27 -Verify if 50 max character length for Size-Units field
	@Test(groups = {"Sanity"}, 
			description="ASST27 -Verify if 50 max character length for Size-Units field")
	public void ASST27() {
		extentTest = extent.startTest("ASST27 -Verify if 50 max character length for Size-Units field");
		SoftAssert sa19 = new SoftAssert();	
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Size Unit text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetSizeUnit(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetSizeUnittext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa19.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Size Unit accepts more than 50 characters");
		sa19.assertAll();
	}
	
	
	//ASST28-Verify the valid inputs accepted in Size-Units field
	@Test(dataProvider = "ASST28", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST28-Verify the valid inputs accepted in Size-Units field")
	public void ASST28(String Name, String ID, String Type, String Manufacturer, String Location, String Size,
			String SizeUnit) throws InterruptedException {
		extentTest = extent.startTest("ASST28-Verify the valid inputs accepted in Size-Units field");
		SoftAssert sa21 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);

		sa21.assertEquals(assetCreationPage.UserLoginPopupVisible(), true,
				"Fail: Asset Unit size not accepting valid unit data");
		sa21.assertAll();
	}

	// ASST29-Verify the invalid inputs in Size-Units field
	@Test(dataProvider = "ASST29", dataProviderClass = assetCreationUtility.class, groups = {
			"Sanity" }, description = "ASST29-Verify the invalid inputs in Size-Units field")
	public void ASST29(String Name, String ID, String Type, String Manufacturer, String Location, String Size,
			String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST29-Verify the invalid inputs in Size-Units field");
		SoftAssert sa20 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);
		String ActAlertMsg = assetCreationPage.AlertMsg();

		sa20.assertEquals(ActAlertMsg, ExpAlrtMsg, "Fail: Asset Unit size accepting Invalid unit data");
		sa20.assertAll();
	}
	*/
	
	// ASST30a-Verify the Last Validated field drop down
	@Test(groups = {"Sanity"}, 
			description="ASST30a-Verify the Last Validated drop down field default data")
	public void ASST30a() throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST30a-Verify the Last Validated drop down field default data");
		SoftAssert sa = new SoftAssert();		
		
		TestUtilities tu = new TestUtilities();
		String expectedDt = tu.get_CurrentDate_inCertainFormat("MM/dd/yyyy");
		//System.out.println("expectedDt: "+expectedDt);
		String [] expDt =  expectedDt.split("/");
		String expDay = expDt[1];
		String expMnth = expDt[0];
		String expYr = expDt[2];
		//System.out.println("Expected Day: "+expDay+" Expected Month: "+expMnth+" Expected Year: "+expYr);
		
		String ActualDt = assetCreationPage.getAsstValidationDatetext();
		//System.out.println("ActualDt: "+ActualDt);
		if (ActualDt.contains("-")) {
			String [] ActDt1 = ActualDt.split("-");
			sa.assertEquals(ActDt1[1], expDay, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertEquals(ActDt1[0], expMnth, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertEquals(ActDt1[2], expYr, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertAll();
		} else {
			String [] ActDt2 = ActualDt.split("/");
			sa.assertEquals(ActDt2[1], expDay, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertEquals(ActDt2[0], expMnth, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertEquals(ActDt2[2], expYr, "Fail: Asset Validation Date field do not reflect the current date");
			sa.assertAll();
		}

	}
	
	// ASST30b-Verify if after selecting Date and clicking OK button should reflect the updated 
	//Date in the Last Validated field
	@Test(groups = {"Sanity"}, dataProvider = "ASST30b", dataProviderClass = assetCreationUtility.class,
			description="ASST30b-Verify if after selecting Date and "
					+ "clicking OK button should reflect the updated Date in the Last Validated field")
	public void ASST30b(String ExpDate) throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST30b-Verify if after selecting Date and "
				+ "clicking OK button should reflect the updated Date in the Last Validated field");
		SoftAssert sa = new SoftAssert();
		
		if (ExpDate.contains("-")) {
			String[] ExpDt = ExpDate.split("-");
			assetCreationPage.selectAssetLastVldtDay(ExpDt[1]); //Enter the Day data
			assetCreationPage.selectAssetLastVldt_Mnth(ExpDt[0]); //Enter the Month Data
			assetCreationPage.selectAssetLastVldt_Yr(ExpDt[2]); //Enter the Year data
			
			String FinalSelectedDateDate = assetCreationPage.getAsstValidationDatetext();
			//System.out.println("Final Date reflected after selection: "+FinalSelectedDateDate);
			
			//The below splitting code is defined, because the date format varies
			//system to system where its displayed as mm-dd-yyyy or mm/dd/yyyy
			if (FinalSelectedDateDate.contains("-")) {
				String [] ActDt1 = FinalSelectedDateDate.split("-");
				sa.assertEquals(ActDt1[1], ExpDt[1], "Fail: Asset Validation Date field do not reflect the Selected date");
				sa.assertEquals(ActDt1[0], ExpDt[0], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt1[2], ExpDt[2], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertAll();
			} else {
				String [] ActDt2 = FinalSelectedDateDate.split("/");
				sa.assertEquals(ActDt2[1], ExpDt[1], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt2[0], ExpDt[0], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt2[2], ExpDt[2], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertAll();
			}
			
		} else {
			String[] ExpDt = ExpDate.split("/");
			assetCreationPage.selectAssetLastVldtDay(ExpDt[1]); //Enter the Day data
			assetCreationPage.selectAssetLastVldt_Mnth(ExpDt[0]); //Enter the Month Data
			assetCreationPage.selectAssetLastVldt_Yr(ExpDt[2]); //Enter the Year data
			
			String FinalSelectedDateDate = assetCreationPage.getAsstValidationDatetext();
			//System.out.println("Final Date reflected after selection: "+FinalSelectedDateDate);
			
			//The below splitting code is defined, because the date format varies
			//system to system where its displayed as mm-dd-yyyy or mm/dd/yyyy
			if (FinalSelectedDateDate.contains("-")) {
				String [] ActDt1 = FinalSelectedDateDate.split("-");
				sa.assertEquals(ActDt1[1], ExpDt[1], "Fail: Asset Validation Date field do not reflect the Selected date");
				sa.assertEquals(ActDt1[0], ExpDt[0], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt1[2], ExpDt[2], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertAll();
			} else {
				String [] ActDt2 = FinalSelectedDateDate.split("/");
				sa.assertEquals(ActDt2[1], ExpDt[1], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt2[0], ExpDt[0], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertEquals(ActDt2[2], ExpDt[2], "Fail: Asset Validation Date field do not reflect the current date");
				sa.assertAll();
			}
		}	

	}
	
	/*
	// ASST31a-Verify the Validation Frequency field: 2 drop downs should be displayed with default 'select'
	@Test(groups = {"Sanity"}, 
			description="ASST31a-Verify the Validation Frequency field: "
					+ "2 drop downs should be displayed with default 'select'")
	public void ASST31a() throws InterruptedException {
		extentTest = extent.startTest("ASST31a-Verify the Validation Frequency field: "
				+ "2 drop downs should be displayed with default 'select'");
		SoftAssert sa29 = new SoftAssert();
		
		sa29.assertEquals(assetCreationPage.getAssetFreqtext(), "Select", 
				"Fail: Select is not the default Asset Frequency field selected data");

		sa29.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), "Select", 
				"Fail: Select is not the default Asset Frequency Interval field selected data");
		
		sa29.assertAll();
	}	
	
	
	// ASST31b-Verify the Validation Frequency field: The list of numbers should be displayed from 1 to 24
	@Test(groups = {"Sanity"}, 
			description="ASST31b: Verify if the First combo box of Validation frequency contains "
			+ "numbers from 1 to 24 sorted in ascending order")
	public void ASST31b() throws InterruptedException {
		extentTest = extent.startTest("ASST31b - Verify if the First combo box of Validation frequency "
				+ "contains numbers from 1 to 24 sorted in ascending order");
		SoftAssert sa30 = new SoftAssert();
		
		String[] expectedFrequencyList = {"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
		//System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyList = assetCreationPage.getAssetFreqlist();
		//System.out.println(Arrays.toString(FrequencyList));

		sa30.assertEquals(FrequencyList, expectedFrequencyList);

		sa30.assertAll();
	}
	

	
	// ASST31c-Verify the Validation Frequency field: Below values should be displayed:
	//Weeks, 	Months, Years
	@Test(groups = {"Sanity"}, 
			description="ASST31c: Verify if the second combo box against the Validation Frequency "
			+ "field lists weeks, months and years")
	public void ASST31c() throws InterruptedException {
		extentTest = extent.startTest("ASST31c- Verify if the second combo box against "
				+ "the Validation Frequency field lists weeks, months and years");
		SoftAssert sa31 = new SoftAssert();
		
		String[] expectedFrequencyList = {"Weeks","Months","Years"};
		//System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyIntrvlList = assetCreationPage.getAssetFreqIntrvllist();
		//System.out.println(Arrays.toString(FrequencyIntrvlList));

		sa31.assertEquals(FrequencyIntrvlList, expectedFrequencyList);

		sa31.assertAll();
	}
	
	
	// ASST31d-Verify the Validation Frequency field: The selected values should be reflected in the field
	@Test(groups = {"Sanity"}, 
			description="ASST31d: Verify if Validation frequency should be selectable "
			+ "by touching the available choices")
	public void ASST31d() throws InterruptedException {
		extentTest = extent.startTest("ASST31d- Verify if Validation frequency should"
				+ " be selectable by touching the available choices");
		SoftAssert sa32 = new SoftAssert();
		
		//Enter text between 1-22; sometimes 23/24 will also work but few times it wont coz of slow App response.
		String expectedFreqSelection = "3";
		assetCreationPage.selectAssetFreq(expectedFreqSelection);

		sa32.assertEquals(assetCreationPage.getAssetFreqtext(), expectedFreqSelection);
		sa32.assertAll();
	}
	
	
	// ASST31e-Verify the Validation Frequency field: The selected values should be reflected in the field
	@Test(groups = {"Sanity"}, 
			description="ASST31e: Verify if Validation frequency Interval combobox should be selectable "
			+ "by touching the available choices")
	public void ASST31e() throws InterruptedException {
		extentTest = extent.startTest("ASST31e-Verify if Validation frequency Interval combobox "
				+ "should be selectable by touching the available choices");
		SoftAssert sa33 = new SoftAssert();
		
		//Enter either Weeks/Months/Years.
		String expectedFreqIntrvlSelection = "Years";
		assetCreationPage.selectAssetFreqIntrvl(expectedFreqIntrvlSelection);
		sa33.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), expectedFreqIntrvlSelection);

		sa33.assertAll();
	}
	*/
	
	
	//ASST32a-Verify the functionality when the Last validation has expired for frequency Week selection
	@Test(groups = {"Sanity"}, 
			description="ASST32a-Verify the functionality when the Last validation has expired", 
			dataProvider = "ASST32a", dataProviderClass = assetCreationUtility.class)
	public void ASST32a(String AName, String AID, String AType, String AManufacturer, 
			String ALocation, String AModel, String ASize, String AUnit, String ALVDt,  
			String AFreq, String AFreqInt, String ADesc) throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST32a-Verify the functionality when the Last validation has expired");
		SoftAssert sa = new SoftAssert();
		
		TestUtilities tu = new TestUtilities();
		String ALVDate = tu.get_CurrentDate_inCertainFormat("MM/dd/yyyy"); 
		//System.out.println("expectedDt: "+ALVDate);
		
		assetCreationPage.assetCreationWithAllFieldEntry(AName, AID, AType, 
				AManufacturer, ALocation, AModel, ASize, AUnit, ALVDate, AFreq, AFreqInt, ADesc);
		
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		//assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Fetch the Height & Width of the Asset tile created above
		String[] AsstDimBeforeLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimBeforeLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) do not change if the Last Validated date 
		//is within the validation frequency range
		sa.assertEquals(AsstDimBeforeLstVldDtChange[1], "91", "FAIL: The Asset Tile width Changed "
				+ "without the Last Validated Date for Asset was modified to 1 Month back");
		
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Changing the Date to just 1 month Back date
		String[] AstLstVldDate = ALVDate.split("/");
		int mnth = Integer.parseInt(AstLstVldDate[0]);
		int newMnth = mnth-1;
		String NewAstLstVldMnth = Integer.toString(newMnth);
		
		String ExpNewAstLstVldDate = NewAstLstVldMnth+"/"+AstLstVldDate[1]+"/"+AstLstVldDate[2];
		//System.out.println(NewAstLstVldDate);
		
		assetCreationPage.selectAssetLastVldt_Mnth(NewAstLstVldMnth);
		assetCreationPage.clickSaveBtn();
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = assetCreationPage.click_BackBtn();
		
		String UpdatedLastVldDt = assetDetailsPage.get_asset_LastValidatedinfo();
		//System.out.println(UpdatedLastVldDt);
		
		//Verify if the updated Last Validated Date is reflected in the corresponding Asset details page
		sa.assertEquals(UpdatedLastVldDt, ExpNewAstLstVldDate, "FAIL: Last Validated Date for Asset didn't change");
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		String[] AsstDimAfterLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimAfterLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) gets changed when the Last Validated date 
		//is outside the validation frequency range
		sa.assertEquals(AsstDimAfterLstVldDtChange[1], "217", "FAIL: The Asset "
				+ "Tile width didn't Change after the Last Validated Date for Asset"
				+ "was modified to 1 Month back");

		sa.assertAll();
	}
	
	/*
	//ASST32b-Verify the functionality when the Last validation has expired for frequency Month selection
	@Test(groups = {"Sanity"}, 
			description="ASST32b-Verify the functionality when the Last validation has expired "
					+ "for frequency Month selection", 
			dataProvider = "ASST32b", dataProviderClass = assetCreationUtility.class)
	public void ASST32b(String AName, String AID, String AType, String AManufacturer, 
			String ALocation, String AModel, String ASize, String AUnit, String ALVDt,  
			String AFreq, String AFreqInt, String ADesc) throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST32b-Verify the functionality when the Last validation has "
				+ "expired for frequency Month selection");
		SoftAssert sa = new SoftAssert();
		
		TestUtilities tu = new TestUtilities();
		String ALVDate = tu.get_CurrentDate_inCertainFormat("MM-dd-yyyy"); 
		//System.out.println("expectedDt: "+ALVDate);
		
		assetCreationPage.assetCreationWithAllFieldEntry(AName, AID, AType, 
				AManufacturer, ALocation, AModel, ASize, AUnit, ALVDate, AFreq, AFreqInt, ADesc);
		
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		//assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Fetch the Height & Width of the Asset tile created above
		String[] AsstDimBeforeLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimBeforeLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) do not change if the Last Validated date 
		//is within the validation frequency range
		sa.assertEquals(AsstDimBeforeLstVldDtChange[1], "91", "FAIL: The Asset "
				+ "Tile width Changed without the Last Validated Date for Asset"
				+ "was modified to 1 Month back");
		
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Changing the Date to just 1 month Back date
		String[] AstLstVldDate = ALVDate.split("-");
		int mnth = Integer.parseInt(AstLstVldDate[0]);
		int newMnth = mnth-1;
		String NewAstLstVldMnth = Integer.toString(newMnth);
		
		String NewAstLstVldDate = NewAstLstVldMnth+"-"+AstLstVldDate[1]+"-"+AstLstVldDate[2];
		//System.out.println(NewAstLstVldDate);
		
		assetCreationPage.selectAssetLastVldt_Mnth(NewAstLstVldMnth);
		assetCreationPage.clickSaveBtn();
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = assetCreationPage.click_BackBtn();
		
		String UpdatedLastVldDt = assetDetailsPage.get_asset_LastValidatedinfo();
		//System.out.println(UpdatedLastVldDt);
		
		//Verify if the updated Last Validated Date is reflected in the corresponding Asset details page
		sa.assertEquals(UpdatedLastVldDt, NewAstLstVldDate, "FAIL: Last Validated Date for Asset didn't change");
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		String[] AsstDimAfterLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimAfterLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) gets changed when the Last Validated date 
		//is outside the validation frequency range
		sa.assertEquals(AsstDimAfterLstVldDtChange[1], "217", "FAIL: The Asset "
				+ "Tile width didn't Change after the Last Validated Date for Asset"
				+ "was modified to 1 Month back");

		sa.assertAll();
	}
	
	
	//ASST32c-Verify the functionality when the Last validation has expired for frequency Year selection
	@Test(groups = {"Sanity"}, 
			description="ASST32c-Verify the functionality when the Last validation has expired "
					+ "for frequency Year selection", 
			dataProvider = "ASST32c", dataProviderClass = assetCreationUtility.class)
	public void ASST32c(String AName, String AID, String AType, String AManufacturer, 
			String ALocation, String AModel, String ASize, String AUnit, String ALVDt,  
			String AFreq, String AFreqInt, String ADesc) throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST32c-Verify the functionality when the Last validation has "
				+ "expired for frequency Year selection");
		SoftAssert sa = new SoftAssert();
		
		TestUtilities tu = new TestUtilities();
		String ALVDate = tu.get_CurrentDate_inCertainFormat("MM-dd-yyyy"); 
		//System.out.println("expectedDt: "+ALVDate);
		
		assetCreationPage.assetCreationWithAllFieldEntry(AName, AID, AType, 
				AManufacturer, ALocation, AModel, ASize, AUnit, ALVDate, AFreq, AFreqInt, ADesc);
		
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		//assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Fetch the Height & Width of the Asset tile created above
		String[] AsstDimBeforeLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimBeforeLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) do not change if the Last Validated date 
		//is within the validation frequency range
		sa.assertEquals(AsstDimBeforeLstVldDtChange[1], "91", "FAIL: The Asset "
				+ "Tile width Changed without the Last Validated Date for Asset"
				+ "was modified to 1 Month back");
		
		assetDetailsPage = assetHubPage.click_assetTile(AName);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Changing the Date to just 1 month Back date
		String[] AstLstVldDate = ALVDate.split("-");
		int yr = Integer.parseInt(AstLstVldDate[2]);
		int newYrMnth = yr-1;
		String NewAstLstVldYr = Integer.toString(newYrMnth);
		
		String NewAstLstVldDate = AstLstVldDate[0]+"-"+AstLstVldDate[1]+"-"+NewAstLstVldYr;
		//System.out.println(NewAstLstVldDate);
		
		assetCreationPage.selectAssetLastVldt_Yr(NewAstLstVldYr);
		assetCreationPage.clickSaveBtn();
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = assetCreationPage.click_BackBtn();
		
		String UpdatedLastVldDt = assetDetailsPage.get_asset_LastValidatedinfo();
		//System.out.println(UpdatedLastVldDt);
		
		//Verify if the updated Last Validated Date is reflected in the corresponding Asset details page
		sa.assertEquals(UpdatedLastVldDt, NewAstLstVldDate, "FAIL: Last Validated Date for Asset didn't change");
		
		assetHubPage = assetDetailsPage.ClickBackBtn();
		String[] AsstDimAfterLstVldDtChange = assetHubPage.assetTile_Dimension(AName);
		//for (String hw : AsstDimAfterLstVldDtChange) {
		//	System.out.println(hw);
		//}
		
		//Verify if the Asset Tile dimensions(Width) gets changed when the Last Validated date 
		//is outside the validation frequency range
		sa.assertEquals(AsstDimAfterLstVldDtChange[1], "217", "FAIL: The Asset "
				+ "Tile width didn't Change after the Last Validated Date for Asset"
				+ "was modified to 1 Month back");

		sa.assertAll();
	}
		
	
	// ASST33-Verify the max character length for Description field
	@Test(groups = {"Sanity"}, 
			description="ASST33-Verify the max character length for Description field")
	public void ASST33() {
		extentTest = extent.startTest("ASST33-Verify the max character length for Description field");
		SoftAssert sa39 = new SoftAssert();

		String expectedtxt = "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890a"; // 251 Char input
		// System.out.println("count of Asset Description text to be entered: "+expectedtxt.length());

		assetCreationPage.enterAstDescription(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetDescriptiontext();
		// System.out.println("count of Asset Description text entered: "+actualtextentered.length());

		sa39.assertEquals(actualtextentered.length(), 250, "FAIL: Asset Description accepts more than 250 characters");
		sa39.assertAll();
	}
	
	
	// ASST34-Verify the inputs accepted in Description field
	@Test(groups = {"Sanity"}, 
			description="ASST34-Verify the inputs accepted in Description field")
	public void ASST136() throws InterruptedException {
		extentTest = extent.startTest("ASST34-Verify the inputs accepted in Description fields");
		SoftAssert sa = new SoftAssert();
		
		String expectedtxt = "\1aA`~!@#$%^&*()_   +=-|][}{';:.,<>?/";
		assetCreationPage.assetCreationWithDesc("AssetDesc", "1", "HeatBath", "Deccan", "KPHB", expectedtxt);
	

		sa.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa.assertAll();
	}
	
	
	//ASST35-Verify the Browse button functionality for uploading images
	@Test(groups = {"Sanity"}, dataProvider = "ASST35", dataProviderClass = assetCreationUtility.class,
			description="ASST35-Verify the Browse button functionality for uploading images")
	public void ASST35(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException, ParseException, AWTException, IOException {
		extentTest = extent.startTest("ASST35-Verify the Browse button functionality for uploading images");
		SoftAssert sa = new SoftAssert();
		
		//Add any image to the Asset Image place holder1 using browse button
		assetCreationPage.click_ImgBrws_Btn();		
		TestUtilities tu = new TestUtilities();
		tu.uploadDoc("VRT_Pro.JPG");
		
		//Capture the expected Image added to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("Expected_VRT_Pro_Img_AsstCreation");
		
		//Create an asset with required details and the above image
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, 
				Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Move to AssetHub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Again select the above Asset created to move to Asset detail page and then click the Edit asset button
		assetDetailsPage = assetHubPage.click_assetTile(Name);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Capture the actual Image saved to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("Actual_VRT_Pro_Img_AsstCreation");
		
		boolean status_ImgCompare = tu.compareImage("Expected_VRT_Pro_Img_AsstCreation", "Actual_VRT_Pro_Img_AsstCreation");
		
		sa.assertEquals(status_ImgCompare, false, "FAIL: The Asset Image saved is not same as what was selected");

		sa.assertAll();
	}
	
	
	//ASST36-Verify the Camera button functionality for uploading images
	@Test(groups = {"Sanity"}, dataProvider = "ASST36", dataProviderClass = assetCreationUtility.class,
			description="ASST36-Verify the Camera button functionality for uploading images")
	public void ASST36(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException, ParseException, AWTException, IOException {
		extentTest = extent.startTest("ASST36-Verify the Camera button functionality for uploading images");
		SoftAssert sa = new SoftAssert();
		
		//Add any image to the Asset Image place holder1 using Camera button
		assetCreationPage.capture_Camera_Img();		
		TestUtilities tu = new TestUtilities();
		
		//Capture the expected Image added to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("Expected_Asst36_AsstCreation");
		
		//Create an asset with required details and the above image
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, 
				Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Move to AssetHub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Again select the above Asset created to move to Asset detail page and then click the Edit asset button
		assetDetailsPage = assetHubPage.click_assetTile(Name);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Capture the actual Image saved to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("Actual_Asst36_AsstCreation");
		
		boolean status_ImgCompare = tu.compareImage("Expected_Asst36_AsstCreation", "Actual_Asst36_AsstCreation");
		
		sa.assertEquals(status_ImgCompare, false, "FAIL: The Asset Image saved is not same as what was captured");

		sa.assertAll();
	}
	
	
	//ASST37 -Verify the Edit functionality for the image
	@Test(groups = {"Sanity"}, dataProvider = "ASST37", dataProviderClass = assetCreationUtility.class,
			description="ASST37 -Verify the Edit functionality for the image")
	public void ASST37(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException, ParseException, AWTException, IOException {
		extentTest = extent.startTest("ASST37 -Verify the Edit functionality for the image");
		SoftAssert sa = new SoftAssert();
		
		//Add any image to the Asset Image place holder1 using browse button
		assetCreationPage.click_ImgBrws_Btn();		
		TestUtilities tu = new TestUtilities();
		tu.uploadDoc("avsLOGO.jpg");
		
		//Capture the expected Image added to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("1stAddedImg_Asst37_AsstCreation");
		
		//Create an asset with required details and the above image
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, 
				Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Move to AssetHub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Again select the above Asset created to move to Asset detail page and then click the Edit asset button
		assetDetailsPage = assetHubPage.click_assetTile(Name);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Click the Image1 Place Holder to change the Image using Edit button
		assetCreationPage.click_Img1_Placeholder_Btn();
		//Click the Image1 edit button to change image
		assetCreationPage.click_Img_Placeholder_Edit_Btn();
		//Add a new image
		tu.uploadDoc("VRT_Pro.JPG");
		
		//Save the asset
		assetCreationPage.clickSaveBtn();
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = assetCreationPage.click_BackBtn();
		
		assetCreationPage = assetDetailsPage.click_assetEditBtn();		
		
		
		//Capture the actual Image saved to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("2ndAddedImg_Asst37_AsstCreation");
		
		boolean status_ImgCompare = tu.compareImage("1stAddedImg_Asst37_AsstCreation", "2ndAddedImg_Asst37_AsstCreation");
		
		sa.assertEquals(status_ImgCompare, true, "FAIL: The Asset new Image added/saved is same to one added previously");

		sa.assertAll();
	}
	
	
	//ASST38 -Verify the Delete functionality for the image
	@Test(groups = {"Sanity"}, dataProvider = "ASST38", dataProviderClass = assetCreationUtility.class,
			description="ASST38 -Verify the Delete functionality for the image")
	public void ASST38(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException, ParseException, AWTException, IOException {
		extentTest = extent.startTest("ASST38 -Verify the Delete functionality for the image");
		SoftAssert sa = new SoftAssert();
		
	
		TestUtilities tu = new TestUtilities();
		//Capture the expected Image (Blank Place holder w/o image) to the Asset placeholder 1		
		assetCreationPage.Capture_AsstImg1("Asst38_ExpBlankImg_AsstCreation");
		
		//Add any image to the Asset Image place holder1 using browse button
		assetCreationPage.click_ImgBrws_Btn();	
		tu.uploadDoc("VRT_Pro.JPG");
		
		//Create an asset with required details and the above image
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, 
				Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Move to AssetHub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Again select the above Asset created to move to Asset detail page and then click the Edit asset button
		assetDetailsPage = assetHubPage.click_assetTile(Name);
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		
		//Click the Image1 Place Holder to change the Image using Edit button
		assetCreationPage.click_Img1_Placeholder_Btn();
		//Click the Image1 edit button to change image
		assetCreationPage.click_Img_Placeholder_Delete_Btn();		
		
		//Save the asset
		assetCreationPage.clickSaveBtn();
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetDetailsPage = assetCreationPage.click_BackBtn();
		
		assetCreationPage = assetDetailsPage.click_assetEditBtn();		
		
		
		//Capture the actual Image of the Asset placeholder 1 after removing the Image		
		assetCreationPage.Capture_AsstImg1("Asst38_ActBlankImg__AsstCreation");
		
		boolean status_ImgCompare = tu.compareImage("Asst38_ExpBlankImg_AsstCreation", "Asst38_ActBlankImg__AsstCreation");
		
		sa.assertEquals(status_ImgCompare, false, "FAIL: The Asset Image is not removed using delete image button");
		sa.assertAll();
	}
	
	
	//ASST39-Verify if the images saved in Asset creation screen are displayed in Asset details screen
	@Test(groups = {"Sanity"}, dataProvider = "ASST39", dataProviderClass = assetCreationUtility.class,
			description="ASST39-Verify if the images saved in Asset creation screen"
					+ " are displayed in Asset details screen")
	public void ASST39(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException, ParseException, AWTException, IOException {
		extentTest = extent.startTest("ASST39-Verify if the images saved in Asset creation "
				+ "screen are displayed in Asset details screen");
		SoftAssert sa = new SoftAssert();
		
		TestUtilities tu = new TestUtilities();
		//Add any image to the Asset Image place holder1 using browse button
		assetCreationPage.click_ImgBrws_Btn();	
		tu.uploadDoc("VRT_Pro.JPG");
				
		//Create an asset with required details and the above image
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, 
				Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
		//Move to AssetHub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		//Again select the above Asset created to move to Asset detail page
		assetDetailsPage = assetHubPage.click_assetTile(Name);

		//Capture the Actual Image (Asset Place holder1 with image)		
		assetDetailsPage.Capture_AsstImg("Asst39_ActImg1__AsstDetails");

		//Expected Image Asst39_ExpImg1__AsstDetails is already present in the Test Data folder
		//which will be compared to the Actual image Asst39_ActImg1__AsstDetails captured.
		boolean status_ImgCompare1 = tu.compareImage("Asst39_ExpImg1__AsstDetails", "Asst39_ActImg1__AsstDetails");
		//System.out.println(status_ImgCompare1);
		
		//Expecting Image diff=false as same image compared
		sa.assertEquals(status_ImgCompare1, false, "FAIL: The Asset Image is not displayed in the Asset Details page");
		sa.assertAll();
	}
	
	
	//ASST40-Verify the functionality when more than 5 mb size image is selected
	@Test(groups = {"Sanity"}, 
			description="ASST40-Verify the functionality when more than 5 mb size image is selected")
	public void ASST40() 
					throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("ASST40-Verify the functionality when more than 5 mb size image is selected");
		SoftAssert sa = new SoftAssert();
		
		TestUtilities tu = new TestUtilities();
		//Add any image to the Asset Image place holder1 using browse button
		assetCreationPage.click_ImgBrws_Btn();	
		tu.uploadDoc("GroupCircle.jpg");
		Thread.sleep(1000);
		
		String ExprtMsg = "Select image file with size less than 5 mb";
		String ActualrtMsg = assetCreationPage.AlertMsg();
		//System.out.println(ActualrtMsg);
		
		//Expecting Image diff=false as same image compared
		sa.assertEquals(ActualrtMsg, ExprtMsg, "FAIL: The Asset Image with size more than 5m is getting saved");
		sa.assertAll();
	}
	
	
	// ASST41-Verify if the Asset details are reflected correctly in Asset Hub screen
	@Test(groups = {"Sanity"}, dataProvider = "ASST41", dataProviderClass = assetCreationUtility.class,  
			description = "ASST41-Verify if the Asset details are reflected correctly in Asset Hub screen")
	public void ASST41(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		extentTest = extent.startTest("ASST41-Verify if the Asset details are reflected correctly in Asset Hub screen");
		SoftAssert sa = new SoftAssert();
		
		//Expected Asset elements (Asset Type, Asset ID, Asset Name) to be edited
		String[] expectedAssetInfo = {Type,ID,Name};
		//System.out.println("exp asst info1:"+Arrays.toString(expectedAssetInfo));
		
		//Asset creation method
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();		
				
		String[] ActualAssetinfo = assetHubPage.assetTile(Name);
		//for (String str2 : ActualAssetinfo) {
		//	System.out.println("Act asst info2: "+str2);
		//}

		sa.assertEquals(ActualAssetinfo, expectedAssetInfo, "Fail: Asset created with wrong information else Not created");
		sa.assertAll();
	}
	
	
	// ASST42-Verify if the Asset details are reflected correctly in Asset Details screen
	@Test(groups = {"Sanity"}, dataProvider = "ASST42", dataProviderClass = assetCreationUtility.class,  
			description = "ASST42-Verify if the Asset details are reflected correctly in Asset Details screen")
	public void ASST42(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST42-Verify if the Asset details are reflected correctly in Asset Details screen");
		SoftAssert sa = new SoftAssert();
		
		//Expected Asset elements (Asset Type, Asset ID, Asset Name) to be edited
		String[] expectedAssetInfo = {ID, Model, Manufacturer, Type, VldDt, Name};
		//System.out.println("exp asst info1:"+Arrays.toString(expectedAssetInfo));
		
		//Asset creation method
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile(Name);

		String[] act_AssetDetailData = assetDetailsPage.get_assetinfo();
		//System.out.println(Arrays.toString(act_AssetDetailData));

		sa.assertEquals(act_AssetDetailData, expectedAssetInfo,
				"FAIL: Mismatch in the Asset data compared between Asset details & Asset creation info");
		sa.assertAll();
		
	}
	
	
	// ASST45-Verify the clear button will discard the entries made during Asset creation
	@Test(dataProvider = "AssetAllData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST45-Verify the clear button will discard the entries made during Asset creation")
	public void ASST45(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description) throws InterruptedException, ParseException {
		
		extentTest = extent.startTest("ASST45-Verify the clear button will discard the entries made during Asset creation");
		SoftAssert sa = new SoftAssert();		

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, 
				Model, Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		
		//Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}	
	
		//Click the CLear button in the Asset creation/details page
		assetCreationPage.clickClearBtn();
		TestUtilities tu = new TestUtilities();
		String ExpDate = tu.get_CurrentDate_inCertainFormat("MM-dd-YYYY");
		System.out.println(ExpDate);
		
		//Validate all the field reset outputs
		sa.assertEquals(assetCreationPage.getAssetName(), "", 
				"FAIL: Asset Name didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getEqpID(), "", 
				"FAIL: Asset ID didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetTypetext(), "Select", 
				"FAIL: Asset Type didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetManufacturertext(), "Select", 
				"FAIL: Asset Manufacturer didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetLocationtext(), "Select", 
				"FAIL: Asset Location didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetModeltext(), "", 
				"FAIL: Asset Model didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetSizetext(), "", 
				"FAIL: Asset Size didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetSizeUnittext(), "Select", 
				"FAIL: Asset SizeUnit didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAsstValidationDatetext(), ExpDate,
				"FAIL: Asset Last Validation Date didn't Clear on clicking the Clear button");		
		sa.assertEquals(assetCreationPage.getAssetFreqtext(), "Select", 
				"FAIL: Asset Frequency didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), "Select", 
				"FAIL: Asset FrequencyInterval didn't Clear on clicking the Clear button");
		sa.assertEquals(assetCreationPage.getAssetDescriptiontext(), "", 
				"FAIL: Asset Description didn't Clear on clicking the Clear button");
		
		sa.assertAll();
	}
	
	
	// ASST46a-Verify the back button functionality in Asset creation screen
	@Test(dataProvider = "AssetAllData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST46a-Verify the back button functionality in Asset creation screen")
	public void ASST46a(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST46a-Verify the back button functionality in Asset creation screen");
		SoftAssert sa42 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();

		sa42.assertEquals(assetCreationPage.discardAlert(), true);
		sa42.assertAll();
	}	
	
	
	// ASST46b-Verify the back button functionality in Asset creation screen
	@Test(dataProvider = "AssetAllData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST46b-Verify if No Option is selected and verify if the application allows the user to stay in the same page")
	public void ASST46b(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST46b - Verify if No Option is selected and verify if the "
				+ "application allows the user to stay in the same page");
		SoftAssert sa43 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();
		//Click the No button in the Discard alert message
		assetCreationPage.discardAlertNoBtn();		

		sa43.assertEquals(assetCreationPage.SaveBtn(), true);
		sa43.assertAll();
	}	

	
	// ASST46c-Verify the back button functionality in Asset creation screen
	@Test(dataProvider = "AssetAllData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="ASST46c-Verify if option�Yes is selected, app discard the changes made and goes back to the Asset Page")
	public void ASST46c(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST46c - Verify if option Yes is selected, app discard the "
				+ "changes made and goes back to the Asset Page");
		SoftAssert sa = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();
		//Click the No button in the Discard alert message
		assetHubPage = assetCreationPage.discardAlertYesBtn();			

		sa.assertEquals(assetHubPage.addAst(), true);
		sa.assertAll();
	}
	
	
	// ASST47-Verify the Save button functionality in Asset creation screen
	@Test(dataProvider = "AssetAllData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="ASST47-Verify the Save button functionality in Asset creation screen")
	public void ASST47(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST47-Verify the Save button functionality in Asset creation screen");
		SoftAssert sa = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, 
				Model, Size, SizeUnit, VldDt, Frequency, FrequencyInterval, Description);
		
		//Check if the UN/PW popup is displayed
		sa.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"Fail: Unauthorised access alert message, thus no UN-PW popped up");
		
		//Check if entering invalid credentials throws unauthorized alert message
		UserLoginPopup(getUID("adminFull"), "abcd"); //Enter User Credentials to Save Asset
		sa.assertEquals(assetCreationPage.AlertMsg(), "Invalid Credential, Please try again", 
				"Fail: able save asset with Invalid User credentials");
		assetCreationPage.CloseAlertMsg();
		
		//Check if Asset successful saved message displayed using valid User credentials
		assetCreationPage.clickSaveBtn();		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		sa.assertEquals(assetCreationPage.AlertMsg(), "Data saved successfully", 
				"Fail: Unauthorised access alert message, thus unable to save asset with Valid User credentials");
		
		
		// Click the Back button in the Asset creation/details page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Expected Asset elements (Asset Type, Asset ID, Asset Name) to be edited
		String[] expectedAssetInfo = {Type,ID,Name};
		//System.out.println("exp asst info1:"+Arrays.toString(expectedAssetInfo));
		
		String[] ActualAssetinfo = assetHubPage.assetTile(Name);
		//for (String str2 : ActualAssetinfo) {
		//	System.out.println("Act asst info2: "+str2);
		//}
		
		//Verify if the Asset created has got correct details reflect as entered during creation
		sa.assertEquals(ActualAssetinfo, expectedAssetInfo, "Fail: Asset created with wrong information else Not created");
		sa.assertAll();

	}
	
	
	//ASST49-Verify the count of Assets in main hub page post New Asset creation
	@Test(dataProvider = "ASST49", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},  
			description = "ASST49-Verify the count of Assets in main hub page post New Asset creation")
	public void ASST49(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDt, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		extentTest = extent.startTest("ASST49-Verify the count of Assets in main hub page post New Asset creation");
		SoftAssert sa2 = new SoftAssert();
		
		assetHubPage = assetCreationPage.discardAlertYesBtn();		
		MainHubPage = assetHubPage.ClickBackBtn();
		int AssetCountInMainHubPage_BeforeNewAssetCreation = Integer.parseInt(MainHubPage.AssetCountInAssetTileOfMainHubPage());
		//System.out.println("AssetCountInMainHubPage_BeforeNewAssetCreation: "+AssetCountInMainHubPage_BeforeNewAssetCreation);
		
		assetHubPage=MainHubPage.ClickAssetTile(); //Move to Asset Hub page
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDt, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();

		//String AssetCountInAssetHubPage = assetHubPage.assetcount();
		//System.out.println("Asst count in AsstHubPage:" + AssetCountInAssetHubPage);
		
		MainHubPage = assetHubPage.ClickBackBtn();
		int AssetCountInMainHubPage_afterAssetCreation = Integer.parseInt(MainHubPage.AssetCountInAssetTileOfMainHubPage());
		//System.out.println("AssetCountInMainHubPage_afterAssetCreation: "+AssetCountInMainHubPage_afterAssetCreation);

		//Verify if After asset creation count in the Asset tile info of Main Hub page has increased by 1
		sa2.assertEquals(AssetCountInMainHubPage_afterAssetCreation, AssetCountInMainHubPage_BeforeNewAssetCreation+1, 
		"FAIL: ASST49 -Mismatch in "
				+ "Asset count compared between Main Hub Page & Asset Hub page");
		sa2.assertAll();
	}
	
	
	//ASST50-Verify the bottom menu options in Asset creation screen
	@Test(description = "ASST50-Verify the bottom menu options in Asset creation screen")
	public void ASST50()
			throws InterruptedException {
		extentTest = extent.startTest("ASST50-Verify the bottom menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();
		
		assetCreationPage.Rt_Click_AstCreation_Buttom_AppBar();

		sa.assertEquals(assetCreationPage.check_Home_Buttom_AppBar_Presence(), true, 
				"FAIL: Home icon/button missing in bottom app bar");
		sa.assertEquals(assetCreationPage.check_Help_Buttom_AppBar_Presence(), true,
				"FAIL: Help icon/button missing in bottom app bar");
		sa.assertEquals(assetCreationPage.check_WndsHelp_Buttom_AppBar_Presence(), true,
				"FAIL: Windows Help icon/button missing in bottom app bar");
		sa.assertEquals(assetCreationPage.check_About_Buttom_AppBar_Presence(), true,
				"FAIL: About icon/button missing in bottom app bar");
		sa.assertAll();
	}
	
	//ASST51-Verify the home btn functionality in bottom menu options in Asset creation screen
	@Test(description = "ASST51-Verify the home btn functionality in bottom menu options"
			+ " in Asset creation screen")
	public void ASST51()
			throws InterruptedException {
		extentTest = extent.startTest("ASST51-Verify the home btn functionality in bottom "
				+ "menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=assetCreationPage.Click_Home_Icon_AppBar();

		sa.assertEquals(MainHubPage.mainPageTitle(), true, 
				"FAIL: Clicking Home icon/button in bottom app bar do not redirect to Mains Hub page");
		sa.assertAll();
	}
	
	//ASST52-Verify the help btn functionality in bottom menu options in Asset creation screen
	@Test(description = "ASST52-Verify the help btn functionality in bottom menu options "
			+ "in Asset creation screen")
	public void ASST52()
			throws InterruptedException {
		extentTest = extent.startTest("ASST52-Verify the help btn functionality in bottom "
				+ "menu options in Asset creation screen");
		SoftAssert sa = new SoftAssert();
		
		assetCreationPage.Click_Help_Icon_AppBar();
		//System.out.println(assetCreationPage.get_AsstCreation_HelpMenu_HdrText());
		sa.assertEquals(assetCreationPage.get_AsstCreation_HelpMenu_HdrText(), 
				"New Asset or Edit Asset", "FAIL: Clicking Help icon/button in bottom app bar"
						+ "do not display the Asset Creation Help context window");
		sa.assertAll();
	}
	
	
	//ASST54-Verify the About btn functionality in bottom menu options in Asset creation screen
	@Test(description = "ASST54-Verify the About btn functionality in bottom menu options in "
			+ "Asset creation screen")
	public void ASST54()
			throws InterruptedException {
		extentTest = extent.startTest("ASST54-Verify the About btn functionality in bottom menu "
				+ "options in Asset creation screen");
		SoftAssert sa = new SoftAssert();
		
		assetCreationPage.Click_About_Icon_AppBar();		
		sa.assertEquals(assetCreationPage.check_About_wndw_Presence(), 
				true, "FAIL: Clicking About icon/button in bottom app bar do not display the About window");
		sa.assertAll();
	}
	*/
}
