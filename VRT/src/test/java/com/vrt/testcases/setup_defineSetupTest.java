package com.vrt.testcases;

import java.io.IOException;
import java.text.ParseException;

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
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.assetHubPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.setupCreationUtility;

public class setup_defineSetupTest extends BaseClass{

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage SensorConfigPage;

	// Before All the tests are conducted
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {
		
		

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetHubTest");

		/*// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();

		// Method to Create Very 1st User with All privilege
		UserManagementPage=LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
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

		// Method to Create 1st Asset 
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(1000);*/

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
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
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
			//String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// to add screencast/video in extent report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// to add screenshot in extent report
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}
	
	
	// Test Cases
	/*// SET002
	@Test(groups = {
			"Regression" }, description = "SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active")
	public void SET002() throws InterruptedException {
		extentTest = extent
				.startTest("SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		

		sa.assertEquals(assetDetailsPage.get_Setupheader_txt(), "Setups", "FAIL: SET 002-Setup tile is not Active under Asset details page");
		sa.assertAll();
	}
	
	
	// SET009
	@Test(groups = {
			"Regression" }, description = "SET 009- UI_Verify if the setup name created in the _Define Setup_ page is "
					+ "displayed at the top left of the Sensors Configuration page when click on the next tab at Define Setup")
	public void SET009() throws InterruptedException {
		extentTest = extent
				.startTest("SET 009- UI_Verify if the setup name created in the _Define Setup_ page is displayed at the top "
						+ "left of the Sensors Configuration page when click on the next tab at Define Setup");
		SoftAssert sa = new SoftAssert();
		
		String SetupNameEntered = defineSetupPage.get_defineSetupPage_setupName();
		//System.out.println(SetupNameEntered);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		SensorConfigPage=defineSetupPage.click_defineSetupPage_nxtBtn();
		String SetupNameDisplayed = SensorConfigPage.get_SensorConfigurationPage_titletext();
		//System.out.println(SetupNameDisplayed);
		

		sa.assertEquals(SetupNameEntered, SetupNameDisplayed, 
				"FAIL: SET 009-Setup Name not displayed in the header of Sensor Config page of Setup");
		sa.assertAll();
	}
	
	
	// SET010
	@Test(groups = {
			"Regression" }, description = "SET 010- UI_Verify if _New Setup_, _Type of Asset_ , _Equipment Name_ is "
					+ "displayed at the top left of the Define Setup page when clicked on _ (+) New_ button in Asset details page.")
	public void SET010() throws InterruptedException {
		extentTest = extent
				.startTest("SET 010- UI_Verify if _New Setup_, _Type of Asset_ , _Equipment Name_ is displayed at the top left of the "
						+ "Define Setup page when clicked on _ (+) New_ button in Asset details page.");
		SoftAssert sa = new SoftAssert();
		
		String DefineSetupTitleTxt = defineSetupPage.get_defineSetupPage_Titletext();
		String StHd1 = DefineSetupTitleTxt.split("-")[0];
		//System.out.println(StHd1);
		String StHd2 = DefineSetupTitleTxt.split("-")[1];
		//System.out.println(StHd2);
		String StHd3 = DefineSetupTitleTxt.split("-")[2];
		//System.out.println(StHd3);
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		String AstHeaderTxt = assetDetailsPage.assetDetail_PageTitle();
		String ADHd1 = AstHeaderTxt.split(" - ")[0];
		//System.out.println(ADHd1);
		String ADHd2 = AstHeaderTxt.split(" - ")[1];
		//System.out.println(ADHd2);		

		sa.assertEquals(StHd1, "New Setup", 
				"FAIL: SET 010-Define Setup Header text mismatches for New Setup String");
		sa.assertEquals(StHd2, ADHd1, 
				"FAIL: SET 010-Define Setup Header text mismatches for Asset Type String");
		sa.assertEquals(StHd3, ADHd2, 
				"FAIL: SET 010-Define Setup Header text mismatches for Asset Name String");
		sa.assertAll();
	}
	
	
	// SET011
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 011- UI_Verify if _Setup Name_ (mandatory field) "
					+ "is displayed in the _Define Setup_ screen.")
	public void SET011() throws InterruptedException {
		extentTest = extent
				.startTest("SET 011- UI_Verify if _Setup Name_ (mandatory field) is displayed "
						+ "in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		defineSetupPage.click_defineSetupPage_nxtBtn();

		String ExpAlertMsg = "Setup Name is mandatory, please enter Setup Name";
		String ActualAlertMsg = defineSetupPage.get_ButtomBarAlertmsg_txt();
		//System.out.println(ActualAlertMsg);

		sa.assertEquals(ActualAlertMsg, ExpAlertMsg, 
				"FAIL: SET 011-Setup Name field mandatory alert message not displayed or Wrong Alert msg");
		sa.assertAll();
	}
	
	
	// SET012
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 012- UI_Verify if  _Number of Sensors _ (mandatory field)"
					+ " is displayed in the _Define Setup_ screen.")
	public void SET012() throws InterruptedException {
		extentTest = extent
				.startTest("SET 012- UI_Verify if  _Number of Sensors _ (mandatory field) is displayed "
						+ "in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.click_defineSetupPage_nxtBtn();

		String ExpAlertMsg = "Number of Sensors is mandatory, please enter Number of Sensors";
		String ActualAlertMsg = defineSetupPage.get_ButtomBarAlertmsg_txt();
		System.out.println(ActualAlertMsg);

		sa.assertEquals(ActualAlertMsg, ExpAlertMsg, 
				"FAIL: SET 012-Sensor Data field mandatory alert message not displayed or Wrong Alert msg");
		sa.assertAll();
	}
	
	
	// SET013
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 013- UI_Verify if  _ Asset ID_ (prepoluted field) "
					+ "is displayed in the _Define Setup_ screen.")
	public void SET013() throws InterruptedException {
		extentTest = extent
				.startTest("SET 013-Verify if Asset ID (prepoluted field) is displayed in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		String AssetIDTxtinSetup = defineSetupPage.get_AssetID_text();
		//System.out.println(AssetIDTxtinSetup);		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		assetCreationPage = assetDetailsPage.click_AssetEditBtn();
		String AssetIDTxtinAssetEditPage = assetCreationPage.getEqpID();
		//System.out.println(AssetIDTxtinAssetEditPage);

		sa.assertEquals(AssetIDTxtinAssetEditPage, AssetIDTxtinSetup, 
				"FAIL: SET 013-Asset ID field Data do not match with the actual Asset ID created");
		sa.assertAll();
	}
	
	
	// SET017
	@Test(groups = {
			"Sanity", "Regression" }, description = "'SET 017-UI_Verify if the setup name field, "
					+ "by default is displayed as current date and 24 hour time format DD-MMM-YYYY HH-MM-SS")
	public void SET017() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("'SET 017-UI_Verify if the setup name field, by default is displayed "
						+ "as current date and 24 hour time format DD-MMM-YYYY HH-MM-SS");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		
		TestUtilities tu = new TestUtilities();
		String Current_DtTime_txt = tu.get_CurrentDate_inCertainFormat("dd-MMM-YYYY HH:mm:ss");
		String Current_DtTime1 = Current_DtTime_txt.split("0", 2)[1];		
		//System.out.println(Current_DtTime1);
		String[] output = Current_DtTime1.split("\\:");		
		String Current_DtTime2 = output[0]+":"+output[1];
		//System.out.println(Current_DtTime2);
		
		defineSetupPage=assetDetailsPage.click_NewStupCreateBtn();
		String SetupName_txt = defineSetupPage.get_defineSetupPage_setupName();
		//System.out.println(SetupName_txt);	
		
		if (SetupName_txt.equals(Current_DtTime1) || (SetupName_txt.contains(Current_DtTime2))) {
			sa.assertEquals(true, true);
			sa.assertAll();
		} else {
			sa.assertEquals(false, true, "FAIL: SET 017-Setup Name default data does not match with Current Date & Time stamp");
			sa.assertAll();
		}
	}
	
	
	// SET019a
	@Test(groups = {
			"Sanity", "Regression" }, dataProvider="SET019a", dataProviderClass=setupCreationUtility.class,
					description = "SET 019-UI_Verify if the setup name text field allows up to "
					+ "35 character input that comprises of alphanumeric, special characters -,_,: and space.")
	public void SET019a(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 019-UI_Verify if the setup name text field allows up to 35 character input that "
						+ "comprises of alphanumeric, special characters -,_,: and space.");
		SoftAssert sa = new SoftAssert();
		
		String SetUpName = (String) dataProvider[0];
		String ExpString = SetUpName;
		System.out.println(ExpString);
		String SensorNumb = (String) dataProvider[1];		
			
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage=defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration", 
				"FAIL:SET 019- Setup Name do not accept the Valid characters ");		

	}*/
	
	
	// SET019b
	@Test(groups = {
			"Regression" }, dataProvider="SET019b", dataProviderClass=setupCreationUtility.class,
					description = "SET 019-UI_Verify if the setup name text field do not allow "
							+ "invalid data except alphanumeric, special characters -,_,: and space.")
	public void SET019b(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 019-UI_Verify if the setup name text field do not allow invalid data "
						+ "except alphanumeric, special characters -,_,: and space.");
		SoftAssert sa = new SoftAssert();
		
		String SetUpName = (String) dataProvider[0];
		String ExpString = SetUpName;
		System.out.println(ExpString);
		String SensorNumb = (String) dataProvider[1];	
		String ErrorAlertMsg = (String) dataProvider[2];
			
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg, 
				"FAIL:SET 019b- Setup Name accepts the In-Valid characters ");		

	}
	


}
