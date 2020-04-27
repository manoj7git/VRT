package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;


import com.vrt.utility.sensorCofigUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;
import com.vrt.utility.TestUtilities;

public class setup_CreationTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public setup_CreationTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

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
	Setup_SensorConfigPage Setup_SensorConfigPage;
	Setup_GroupSensorsPage Setup_GroupSensorsPage;
	Setup_CalculationsPage Setup_CalculationsPage;
	Setup_QualParamPage Setup_QualParamPage;
	Setup_ReviewPage Setup_ReviewPage;

	TestUtilities tu = new TestUtilities();

	// Before Class -All the tests are conducted
	@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, ParseException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ER_" + "Multiple_Setups_CreationTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "Multiple_Setups_CreationTest");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("Multiple setup Creation Test in Progress..");

		
		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();

		// Method to Create Very 1st User with All privilege
		UserManagementPage = LoginPage.DefaultLogin();
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

		/*
		// Method to Create 1st Asset
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		assetCreationPage.assetCreationWithAllFieldEntry("AST050", "50", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", crntDate, "1", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		*/
		AppClose();
		Thread.sleep(500);
		
	}

	// @AfterClass-All the tests are conducted
	@AfterClass
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		//Setup_QualParamPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		//assetDetailsPage = assetHubPage.click_assetTile("AST050");
		//defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		
	}

	// @AfterMethod TearDown of the App
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
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		driver.quit();
	}

	
	// Create Multiple Setups
	@Test(groups = {
			"Regression" }, dataProvider = "SetupCreation_1", dataProviderClass = setupCreationUtility.class, 
					description = "Create Setups")

	public void MultipleSETUP01(String AName, String AID, String AType, String AManufaturer, String ALocation, String SetupName, String SensorCount, String SOP, String LoadDescription, 
			String Comments, String TempCount,	String PrsrCount, String TCSensorLabel, String PrSensorLabel, 
			String BaseTemp, String AlethCondition, String Qstart, String TOD, String Qstop, String Hrs,String Mnts,String Secs,
			String SR,	String TR, String RF_Transmitt, String count) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"Multiple Setup Creation");

		//Create Asset
		assetCreationPage.assetCreation(AName, AID, AType, AManufaturer, ALocation);
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage=assetHubPage.click_assetTile(AName);
		//Define Setup 
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LoadDescription);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Comments);
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		
		//Setup COnfig page
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();	
		int PrNo = Integer.parseInt(PrsrCount);
		
		if (PrNo>0) {
			Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
			Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
			
			Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
			Setup_SensorConfigPage.select_Sensortype_temp();			
			Setup_SensorConfigPage.Enter_Num_To(TempCount);
			Setup_SensorConfigPage.Enter_SensorLabel(TCSensorLabel);
			Setup_SensorConfigPage.Click_assignBtn();
			Setup_SensorConfigPage.select_Sensortype_Pr();
			Setup_SensorConfigPage.Enter_Num_To(PrsrCount);
			Setup_SensorConfigPage.Enter_SensorLabel(PrSensorLabel);
			Setup_SensorConfigPage.Click_assignBtn();
			
		} else {
			Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
			Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
			Setup_SensorConfigPage.select_Sensortype_temp();			
			Setup_SensorConfigPage.Enter_Num_To(TempCount);
			Setup_SensorConfigPage.Enter_SensorLabel(TCSensorLabel);
			Setup_SensorConfigPage.Click_assignBtn();
		}		
		
		int SensorCnt = Integer.parseInt(SensorCount);
		int TCNo = Integer.parseInt(TempCount);
		
		if (!(SensorCnt>(TCNo+PrNo))) {
			Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn();
		} else {
			Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn_withAlert();
		}
		
		//Setup_Group Sensors Config page
		if (!(PrNo>0)) {			
			Setup_GroupSensorsPage.click_DfltGrp_Btn();	
			/*
			Setup_GroupSensorsPage.click_GrpWiring_Btn();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(0);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("temp-loggers.jpg");
			Thread.sleep(1000);*/
		} else {
			Setup_GroupSensorsPage.click_DfltGrp_Btn();	
			/*
			Setup_GroupSensorsPage.click_GrpWiring_Btn();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(0);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("temp-loggers.jpg");
			Setup_GroupSensorsPage.click_Dflt_PrGrp();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(1);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("Pressure.jpg");
			Thread.sleep(1000);*/
		}
		
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		
		//Setup_Claculations page
		float bTemp = Float.parseFloat(BaseTemp);
		if (bTemp >= 0.0) {
			Setup_CalculationsPage.enter_bTemp(BaseTemp);
		}
		Setup_CalculationsPage.select_AlethCondition(AlethCondition);
		
		if (PrNo>0) {
			Setup_CalculationsPage.click_SatTP_btn();
			Setup_CalculationsPage.select_1stTempSensor();
			Setup_CalculationsPage.select_1stPrSensor();			
			Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
			
		} else {
			Setup_QualParamPage = Setup_CalculationsPage.Click_NxtBtn();
		}
				
		//Setup_Qual Parameters page
		int Yr = Integer.parseInt(TOD.split(":")[0]);  
		int Mn = Integer.parseInt(TOD.split(":")[1]);
		int Dt = Integer.parseInt(TOD.split(":")[2]);
		int Hr = Integer.parseInt(TOD.split(":")[3]);
		int Mnt = Integer.parseInt(TOD.split(":")[4]);
		int Se = Integer.parseInt(TOD.split(":")[5]);
		Setup_QualParamPage.select_QualStart_condition(Qstart, Yr,Mn,Dt,Hr,Mnt,Se);//Select TOD Qual Start parameter yr/Mnth/Dt/Hr/Mnt/Sec,
		Setup_QualParamPage.select_QualStop_condition(Qstop, Hrs, Mnts, Secs);
		Setup_QualParamPage.select_SR(SR);
		Setup_QualParamPage.select_TR(SR, TR);
		Setup_QualParamPage.select_RFT(RF_Transmitt);
		
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		//Setup_Review page
		Setup_ReviewPage.click_Save_Btn(Qstart, "Yes");
		//Setup_ReviewPage.click_back_Btn();
		System.out.println("Setup COunt: "+count);
	}
	
	
	/*//Verify 50 setups are created 
	@Test(groups = {"Sanity", "Regression"}, 
			description = "Verify if 50 setups are created")
	public void MultipleSETUP02()
					throws InterruptedException, IOException {
		extentTest = extent.startTest("Verify if 50 setups are created");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage = defineSetupPage.click_YesofAlert_msg();
		//Fetch the total setup Count created
		String TotalSetupCount = assetDetailsPage.setupTile_countdata();		
		//System.out.println(TotalSetupCount);
		
		sa.assertEquals(TotalSetupCount, "50", "FAIL: Incorrect Setup Count displayed or created");				
		sa.assertAll();
	}*/

}