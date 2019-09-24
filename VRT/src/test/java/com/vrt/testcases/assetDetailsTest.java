package com.vrt.testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetHubPage;
import com.vrt.utility.TestUtilities;

public class assetDetailsTest extends BaseClass {

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

	// Before All the tests are conducted
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetHubTest");

		
		  //Rename the User file (NgvUsers.uxx) if exists
		  renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData"
		  , "NgvUsers.uux"); //Rename the cache Asset file (Asset.txt) if exists
		  renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache"
		  , "Asset.txt"); //Rename the Asset folder (Asset) if exists
		  renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles",
		  "Assets");
		  
		  LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App"); Thread.sleep(1000); LoginPage
		  = new LoginPage();
		  
		  //Method to Create Very 1st User with All privilege UserManagementPage =
		  LoginPage.DefaultLogin(); LoginPage =
		  UserManagementPage.FirstUserCreation("User1", getUID("adminFull"),
		  getPW("adminFull"), getPW("adminFull"), "FullAdmin", "12345678",
		  "abc@gmail.com"); MainHubPage = LoginPage.Login(getUID("adminFull"),
		  getPW("adminFull")); UserManagementPage =
		  MainHubPage.ClickAdminTile_UMpage();
		  UserManagementPage.clickAnyUserinUserList("User1");
		  
		  UserManagementPage.clickPrivRunQual();
		  UserManagementPage.clickPrivCreateEditAsset();
		  UserManagementPage.clickPrivCreateEditSetup();
		  UserManagementPage.clickPrivRunCal();
		  
		  UserManagementPage.ClickNewUserSaveButton();
		  UserLoginPopup(getUID("adminFull"), getPW("adminFull")); MainHubPage =
		  UserManagementPage.ClickBackButn();
		  
		  //Method to Create 1st Asset assetHubPage=MainHubPage.ClickAssetTile();
		  assetCreationPage=assetHubPage.ClickAddAssetBtn();
		  assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath",
		  "AAS", "Hyderabad", "VRT-RF", "2", "cu", "5", "Weeks", "1st Asset Creation");
		  UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		  
		  AppClose(); Thread.sleep(1000);
		 
	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
	}

	// TearDown of the App
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #"); 
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #"); 
			// to add screenshot in extent report
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());			
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// to add screencast/video in extent report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// to add screenshot in extent report
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			
		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}

	// ASST016
	@Test(groups = { "Sanity", "Regression" }, description = "ASST016-Verify if selecting the target Asset "
			+ "tile in Asset hub page , user is navigated to the target Asset Details screen "
			+ "with Asset name & Asset Type info displayed in the Header")
	public void ASST016() throws InterruptedException {
		extentTest = extent.startTest("ASST016-Verify whether the type of the asset - eg- "
				+ "sterilizer and the name of the asset is displayed at the left top of the "
				+ "Asset details page is as per selected asset");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL: TC-ASST016 -Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
	}
	
	
	// ASST017
	@Test(groups = { "Sanity", "Regression" }, description = "ASST017-Verify if Edit Icon is present"
			+ " at the right top corner of assets detail page and opens the Edit asset - asset details "
			+ "screen with the possibility to edit the selected asset")
	public void ASST017() throws InterruptedException {
		extentTest = extent.startTest("ASST017-Verify if clicking Edit Icon in assets "
				+ "detail page opens the Edit asset");
		SoftAssert sa = new SoftAssert();
		
		//Verify Asset Edit button present or not in Asset details page
		sa.assertEquals(assetDetailsPage.assetEditBtn_state(), true, "FAIL: "
				+ "TC-ASST017 -Asset Edit button not present in Asset details page");
		
		if (assetDetailsPage.assetEditBtn_state()) {			
			assetCreationPage=assetDetailsPage.click_assetEditBtn();
		}
		
		//Verify clicking Asset edit button takes one to Asset creation page in edit mode
		sa.assertEquals(assetCreationPage.get_newAssetCreatePagetitle(), "Edit Asset",
				"FAIL: TC-ASST017 -Incorrect AssetCreation Page title in Asset Edit mode or landed into incorrect Page");
		sa.assertAll();
	}
	
	
	//ASST018
	@Test(groups = { "Sanity", "Regression" }, description = "ASST018-Verify if clicking on "
			+ "Back Button at the left top to return to Assets Hub page")
	public void ASST018() throws InterruptedException {
		extentTest = extent.startTest(
				"ASST018-Verify if clicking on Back Button at the left top to return to Assets Hub page");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();

		sa.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST018 -Incorrect Asset Hub Page title or landed into incorrect Page");
		sa.assertAll();
	}
	
	
	//ASST019 = Manual Test
	
	
	//ASST020
	@Test(groups = { "Sanity", "Regression" }, description = "ASST020-Verify if the data displayed "
			+ "in the assets detail page is exactly same as the information given for asset in Create new Asset page")
	public void ASST020() throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST020-Verify if the data displayed in the assets detail page "
				+ "is exactly same as the information given for asset in Create new Asset page");
		SoftAssert sa = new SoftAssert();

		String[] act_AssetDetailData = assetDetailsPage.get_assetinfo();
		//System.out.println(Arrays.toString(act_AssetDetailData));
		
		assetCreationPage=assetDetailsPage.click_assetEditBtn();
		String[] act_AssetCreationData = assetCreationPage.get_assetCreationinfo();
		//System.out.println(Arrays.toString(act_AssetCreationData));
		

		sa.assertEquals(act_AssetDetailData, act_AssetCreationData,
				"FAIL: TC-ASST020 -Mismatch in the Asset data compared between Asset details & Asset creation Page");
		sa.assertAll();
	}
	
	
	//ASST021
	@Test(groups = { "Sanity", "Regression" }, description = "ASST020-Verify if the data displayed "
			+ "in the assets detail page is exactly same as the information given for asset in Create new Asset page")
	public void ASST021() throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST020-Verify if the data displayed in the assets detail page "
				+ "is exactly same as the information given for asset in Create new Asset page");
		SoftAssert sa = new SoftAssert();

		String[] act_AssetDetailData = assetDetailsPage.get_assetinfo();
		//System.out.println(Arrays.toString(act_AssetDetailData));
		
		assetCreationPage=assetDetailsPage.click_assetEditBtn();
		String[] act_AssetCreationData = assetCreationPage.get_assetCreationinfo();
		//System.out.println(Arrays.toString(act_AssetCreationData));
		

		sa.assertEquals(act_AssetDetailData, act_AssetCreationData,
				"FAIL: TC-ASST020 -Mismatch in the Asset data compared between Asset details & Asset creation Page");
		sa.assertAll();
	}

}
