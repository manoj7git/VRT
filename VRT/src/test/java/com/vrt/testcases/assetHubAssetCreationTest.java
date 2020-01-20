package com.vrt.testcases;


import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class assetHubAssetCreationTest extends BaseClass{
	
	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	public ExtentReports extent;
	public ExtentTest extentTest;
	
	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	

	//Before All the tests are conducted
	//@BeforeTest
	@BeforeClass
	public void AssetCreationSetup() throws InterruptedException, IOException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/assetHubAssetCreationTest.html",true);
		extent.addSystemInfo("BS Version", "0.6.32");
		extent.addSystemInfo("Lgr Version", "1.3.14");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetHubTest");

		//Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");		
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");		
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
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
		Thread.sleep(1000);
	}

	//After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport_releaseMomory(){
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		//System.out.println("Reset Webelement memory released");
	}
	
	//Before Method(Test) method
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");			
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.waitforAssetHubPageLoad();
	}

	//TearDown of the App
	@AfterMethod(alwaysRun=true)
	public void Teardown(ITestResult result) throws IOException, InterruptedException {
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
		driver = null;

	}

	
	//Create 25 assets
	@Test(dataProvider="ASSTHB012", dataProviderClass=assetCreationUtility.class, groups = {"Sanity", "Regression"}, 
			description = "Create 25 assets"
			+ "categories model, size, manufacturer,Location and Type")
	public void ASSTHB012a(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDT, String Frequency, String FrequencyInterval, String Description)
					throws InterruptedException, ParseException {
		extentTest = extent.startTest("Create 25 assets");
		
		//Forcibly creating the Assets with Last Validated data as Current date
		//irrespective of what data is provided in the Excel sheet. 
		//Just to save time in the date selection picker thereby reducing the time for creating assets 
		//for any random Lst Vldt Date
		TestUtilities tu = new TestUtilities();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				crntDate, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset		
		
	}
	
	
	//ASSTHB012-Verify 25 assets are created with each categories model, size, manufacturer,Location and Type
	@Test(groups = {"Sanity", "Regression"}, 
			description = "ASSTHB012-Verify 25 assets are created with each "
			+ "categories model, size, manufacturer,Location and Type")
	public void ASSTHB012b()
					throws InterruptedException {
		extentTest = extent.startTest("ASSTHB012-Verify 25 assets are created with each categories model, "
				+ "size, manufacturer,Location and Type");
		SoftAssert sa = new SoftAssert();
		
		//Fetch the Asset Count before creation 25 Assets
		MainHubPage = assetHubPage.clickBackBtn();		
		String AstCount = MainHubPage.AssetCountInAssetTileOfMainHubPage();
		//System.out.println(AstCount_Default);
		int Total_AsstCount = Integer.parseInt(AstCount);
		System.out.println(Total_AsstCount);
		
		sa.assertEquals(Total_AsstCount, 25, "FAIL: TC-ASSTHB012 -Incorrect Asset Count displayed or created");				
		sa.assertAll();
	}

}
