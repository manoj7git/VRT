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
import com.vrt.pages.GroupSensorspage;

import com.vrt.utility.TestUtilities;
import com.vrt.utility.sensorCofigUtility;
import com.vrt.utility.setupCreationUtility;
import com.vrt.utility.userManagementUtility;

public class setup_SensorConfigTest extends BaseClass{
	
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
		Setup_SensorConfigPage Setup_SensorConfigPage;
		GroupSensorspage GroupSensorspage;

		// Before All the tests are conducted
		@BeforeTest
		public void AssetCreationSetup() throws InterruptedException, IOException {		

			extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
			extent.addSystemInfo("VRT Version", "1.0.0.37");
			extent.addSystemInfo("BS Version", "0.6.13");
			extent.addSystemInfo("Lgr Version", "1.2.6");
			extent.addSystemInfo("User Name", "Manoj");
			extent.addSystemInfo("TestSuiteName", "AssetHubTest");
			
			/*
		// Rename the User file (NgvUsers.uxx) if exists
	renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");

		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");

		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
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

			// Method to Create 1st Asset 
			assetHubPage=MainHubPage.ClickAssetTile();
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
					"cu", "11/20/2019", "5", "Weeks", "1st Asset Creation");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

			AppClose();
			Thread.sleep(1000);
			*/
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
			defineSetupPage.clear_defineSetupPage_setupName();
			defineSetupPage.enter_defineSetupPage_setupName("test");
			defineSetupPage.click_defineSetupPage_SensorCountField();
			defineSetupPage.enter_defineSetupPage_SensorCount("300");
			Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
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
				//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
				//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

			}
			extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

			driver.quit();
		}
		
		/*
	// Test Cases
		// SET001
		@Test(groups = {
				"Regression" }, description = "SC001-Verify the details displayed in Sensor Configuration screen")
		public void SET002() throws InterruptedException {
			extentTest = extent
					.startTest("SC001-Verify the details displayed in Sensor Configuration screen");
			SoftAssert sa = new SoftAssert();
			
			sa.assertEquals(Setup_SensorConfigPage.SensorConfig_assertname_state(), true,
					"FAIL: SC001- It should be available");
			sa.assertEquals(Setup_SensorConfigPage.sensorConfigPage_state(), true,
					"FAIL: SC002- It should be available");
			sa.assertEquals(Setup_SensorConfigPage.AddSensors_btn_state(), true,
					"FAIL: SC002- It should be available");
			sa.assertEquals(Setup_SensorConfigPage.ConfigureSensors_btn_state(), true,
					"FAIL: SC002- It should be available");
			sa.assertEquals(Setup_SensorConfigPage.ConfigureSensors_btn_state(), true,
					"FAIL: SC002- It should be available");
			sa.assertAll();
		}
	
//SC002-Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected	

	@Test(groups = {"Regression" }, description = "Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected")
	    public void SC002() throws InterruptedException {
		extentTest = extent.startTest("SC002:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = Setup_SensorConfigPage.Alertmsg_txt();
		String expalert = "Please configure atleast one sensor to proceed.";
	    sa.assertEquals(actalert, expalert,"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}
	
	// SC003-Verify that on-click of the Down arrow icon for Add Sensors section
	// expands and displays the Sensor types
	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Down arrow icon for Add Sensors section expands and displays the Sensor types")
	public void SC003() throws InterruptedException {
		extentTest = extent.startTest(
				"SC003-Verify that on-click of the Down arrow icon for Add Sensors section expands and displays the Sensor types");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Field_state(),true,"FAIL: Temperature_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Humidity_Field_state(), true, "FAIL: Humidity_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Field_state(), true, "FAIL: Pressure_Field should be visible ");
		sa.assertEquals(Setup_SensorConfigPage.Add_btnField_state(), true, "FAIL: Add_btnField should be visible ");
		sa.assertAll();
	}

	//SC003_1- Verify the default value displayed in Temperature field under Add sensors section

	@Test(groups = {
			"Regression" }, description = "Verify the default value displayed in Temperature field under Add sensors section")
	public void SC003_1() throws InterruptedException {
		extentTest = extent.startTest(
				"SC003_1-Verify the default value displayed in Temperature field under Add sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		String TempcountDisplayed = Setup_SensorConfigPage.get_Temperature_text();
		System.out.println(TempcountDisplayed);
		sa.assertEquals(TempcountDisplayed,"0",
				"FAIL: SC003_1 - the default value should be displayed in Temperature field ");
		sa.assertAll();
	}

		
//SC004-Verify that max 3 characters are accepted in Temperature field under Add sensors section

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC004", dataProviderClass = sensorCofigUtility.class, description = "Verify that max 3 characters are accepted in Temperature field under Add sensors section")

	public void SC004(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("SC004-Verify that max 3 characters are accepted in Temperature field under Add sensors section");

		SoftAssert sa = new SoftAssert();
		String TempCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
	
		String EnteredTempcount = Setup_SensorConfigPage.get_Temperature_text();
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		sa.assertEquals(EnteredTempcount, DisplayedTempcount,
				"FAIL: Maximum 3 characters should be allowed in Temperature field ");
		sa.assertAll();

	}
	
	//SC005-Verify the valid inputs accepted in Temperature field under Add Sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC005", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs accepted in Temperature field under Add Sensors section")

	public void SC005(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("SC005-Verify the valid inputs accepted in Temperature field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String TempCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(1000);
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Sensor_Titlestate(),true,
				"FAIL: Temperature_Sensor_Title should be visible ");
		sa.assertAll();
	}
	
	
	//SC006-Verify the invalid inputs that are not accepted in Temperature field under Add Sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC006", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs that are not accepted in Temperature field under Add Sensors section")

	public void SC006(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("SC006-Verify the invalid inputs that are not accepted in Temperature field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String TempCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(1000);
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		sa.assertEquals(DisplayedTempcount,"0",
				"FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}		


//SC007-Verify that when more than 300 is entered in Temperature field, the count is rounded off to 300
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC007", dataProviderClass = sensorCofigUtility.class, description = "Verify that when more than 300 is entered in Temperature field, the count is rounded off to 300")

	public void SC007(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest(
				"SC007-Verify that when more than 300 is entered in Temperature field, the count is rounded off to 300 ");

		SoftAssert sa = new SoftAssert();
		String TempCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(TempCount);
		Thread.sleep(1000);
		String DisplayedTempcount = Setup_SensorConfigPage.get_Temperature_text();
		System.out.println(DisplayedTempcount);
		sa.assertEquals(DisplayedTempcount, "300", "FAIL:Temperature count more than 300 should be rounded off to 300");
		sa.assertAll();
	}

	
	//SC008-Verify that by clicking on Add button will add the number of sensors on the left pane for Temperature as mentioned in the field
	@Test(groups = {
			"Regression" }, description = "Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured")
	public void SC025() throws InterruptedException {
		extentTest = extent.startTest(
				"SC027-Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.SensorDetails();
		}
	

  //SC009-Verify that when 0 is added in the Temperature field it will delete the sensors on the left pane
	
		@Test(groups = {"Regression" }, description = "Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected")
		    public void SC009() throws InterruptedException {
			extentTest = extent.startTest("SC002:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
			SoftAssert sa = new SoftAssert();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
			sa.assertEquals(Setup_SensorConfigPage.Temperature_Sensor_Titlestate(), true,
					"FAIL: Temperature_Sensor_Titlestate should be visible ");
			Setup_SensorConfigPage.Enter_TemperatureCount_textField("0");
			Setup_SensorConfigPage.Click_GroupSensors_btn();
			String actalert = Setup_SensorConfigPage.Alertmsg_txt();
			String expalert = "Please configure atleast one sensor to proceed.";
		    sa.assertEquals(actalert, expalert,"FAIL: Application should display the alert message as there is no sensors added ");
			sa.assertAll();
		}
	
	//SC010 - Verify that a validation message is displayed when more number of sensors are added in the Temperature field than those defined in define setup screen
	@Test(groups = {
			"Regression" }, description = "Verify that a validation message is displayed when more number of sensors are added in the Temperature field than those defined in define setup screen")
	public void SC010() throws InterruptedException {
		extentTest = extent.startTest("Verify that a validation message is displayed when more number of sensors are added in the Temperature field than those defined in define setup screen");
		SoftAssert sa = new SoftAssert();
		//Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		defineSetupPage = Setup_SensorConfigPage.DefineSetup_back_btn();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		String Actalert = Setup_SensorConfigPage.Alertmsg_txt();
		String Expectalert = "You are trying to add more number of sensors than defined";
		sa.assertEquals(Actalert,Expectalert, "FAIL:alert message not matched");
		sa.assertAll();
	}
	
	//SC011-Verify the default value displayed in Humidity field under Add sensors section

		@Test(groups = {
				"Regression" }, description = "Verify the default value displayed in Humidity field under Add sensors section")
		public void SC011() throws InterruptedException {
			extentTest = extent.startTest(
					"SC11-Verify the default value displayed in Humidity field under Add sensors section");
			SoftAssert sa = new SoftAssert();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			String HmdcountDisplayed = Setup_SensorConfigPage.get_Humidity_text();
			System.out.println(HmdcountDisplayed);
			sa.assertEquals(HmdcountDisplayed,"0",
					"FAIL: SC11 - the default value should be displayed in Humididty field ");
			sa.assertAll();
		}

			
	//SC012-Verify that max 3 characters are accepted in Humidity field under Add sensors section

		@Test(groups = {
				"Regression" }, dataProvider = "TcSC012", dataProviderClass = sensorCofigUtility.class, description = "Verify that max 3 characters are accepted in Humidity field under Add sensors section")

		public void SC012(Object... dataProvider) throws InterruptedException {
			extentTest = extent.startTest("SC012-Verify that max 3 characters are accepted in Humidity field under Add sensors section");

			SoftAssert sa = new SoftAssert();
			String HmdCount = (String) dataProvider[0];

			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		
			String EnteredTempcount = Setup_SensorConfigPage.get_Humidity_text();
			String DisplayedTempcount = Setup_SensorConfigPage.get_Humidity_text();
			sa.assertEquals(EnteredTempcount, DisplayedTempcount,
					"FAIL: Maximum 3 characters should be allowed in Humidity field ");
			sa.assertAll();

		}

	// SC013-Verify the valid inputs accepted in Humidity field under Add Sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC013", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs accepted in Humidity field under Add Sensors section")

	public void SC013(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("SC013-Verify the valid inputs accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String HmdCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		Thread.sleep(1000);
		sa.assertEquals(Setup_SensorConfigPage.Humidity_Sensor_Titlestate(), true,
				"FAIL: Humidity_Sensor_Title should be visible ");
		sa.assertAll();
	}

//SC014- Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section
	
	@Test(groups = {"Regression" }, dataProvider = "TcSC014", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section")

	public void SC014(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("SC014-Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String HmdCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
		Thread.sleep(1000);
		String DisplayedHmdcount = Setup_SensorConfigPage.get_Humidity_text();
		sa.assertEquals(DisplayedHmdcount, "0", "FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}

//SC015-Verify that when more than 300 is entered in Humidity field, the count is rounded off to 300
		
		@Test(groups = {
				"Regression" }, dataProvider = "TcSC015", dataProviderClass = sensorCofigUtility.class, description = "Verify that when more than 300 is entered in Humidity field, the count is rounded off to 300")

		public void SC015(Object... dataProvider) throws InterruptedException {
			extentTest = extent.startTest(
					"SC015-Verify that when more than 300 is entered in Humidity field, the count is rounded off to 300 ");

			SoftAssert sa = new SoftAssert();
			String HmdCount = (String) dataProvider[0];

			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_HumidityCount_textField(HmdCount);
			Thread.sleep(1000);
			String DisplayedHmdtextboxcount = Setup_SensorConfigPage.get_Humidity_text();
			System.out.println(DisplayedHmdtextboxcount);
			sa.assertEquals(DisplayedHmdtextboxcount, "300", "FAIL:Humidity count more than 300 should be rounded off to 300");
			sa.assertAll();
		}
	
 //SC016-Verify that by clicking on Add button will add the number of sensors on the left pane for Humidity as mentioned in the field
		@Test(groups = {"Regression" }, description = "Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected")
	    public void SC016() throws InterruptedException {
		extentTest = extent.startTest("SC016:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		//String EnterTempcount = Setup_SensorConfigPage.get_Humidity_text();
		//System.out.println("Ented TemperatureCount " + EnterTempcount);
		//Thread.sleep(1000);
		String[] Displayedsensorcnt = Setup_SensorConfigPage.SensorCount();
		//sa.assertEquals(EnterTempcount,Displayedsensorcnt, "FAIL:count should be same");
		//sa.assertAll();
		
		}
		
  //SC017-Verify that when 0 is added in the Humidity field it will delete the sensors on the left pane
		
			@Test(groups = {"Regression" }, description = "Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected")
			    public void SC009() throws InterruptedException {
				extentTest = extent.startTest("SC017:Verify that sensors pane on the left side will display no sensors and will be blank till no sensor types are defined and selected");
				SoftAssert sa = new SoftAssert();
				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
				sa.assertEquals(Setup_SensorConfigPage.Humidity_Sensor_Titlestate(), true,
						"FAIL: Temperature_Sensor_Titlestate should be visible ");
				Setup_SensorConfigPage.Enter_HumidityCount_textField("0");
				Setup_SensorConfigPage.Click_GroupSensors_btn();
				String actalert = Setup_SensorConfigPage.Alertmsg_txt();
				String expalert = "Please configure atleast one sensor to proceed.";
			    sa.assertEquals(actalert, expalert,"FAIL: Application should display the alert message as there is no sensors added ");
				sa.assertAll();
			}
			
	//SC018-Verify that a validation message is displayed when more number of sensors are added in the Humidity field than those defined in define setup screen
		@Test(groups = {"Regression" }, description = "Verify that a validation message is displayed when more number of sensors are added in the Humidity field than those defined in define setup screen")
		public void SC010() throws InterruptedException {
			extentTest = extent.startTest("SC018-Verify that a validation message is displayed when more number of sensors are added in the Humidity field than those defined in define setup screen");
			SoftAssert sa = new SoftAssert();
			//Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			defineSetupPage = Setup_SensorConfigPage.DefineSetup_back_btn();
			defineSetupPage.clear_defineSetupPage_SensorCount();
			defineSetupPage.click_defineSetupPage_SensorCountField();
			defineSetupPage.enter_defineSetupPage_SensorCount("1");
			Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
			String Actalert = Setup_SensorConfigPage.Alertmsg_txt();
			String Expectalert = "You are trying to add more number of sensors than defined";
			sa.assertEquals(Actalert,Expectalert, "FAIL:alert message not matched");
			sa.assertAll();
		}
	
		
	//SC019-Verify the default value displayed in Pressure field under Add sensors section

	@Test(groups = {
			"Regression" }, description = "Verify the default value displayed in Pressure field under Add sensors section")
	public void SC019() throws InterruptedException {
		extentTest = extent
				.startTest("SC019-Verify the default value displayed in Pressure field under Add sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		String PrescountDisplayed = Setup_SensorConfigPage.get_pressure_text();
		System.out.println(PrescountDisplayed);
		sa.assertEquals(PrescountDisplayed, "0",
				"FAIL: SC019 - the default value should be displayed in pressure field ");
		sa.assertAll();
	}
	
	// SC020-Verify that max 3 characters are accepted in Pressure field under Add sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC020", dataProviderClass = sensorCofigUtility.class, description = "Verify that max 3 characters are accepted in Pressure field under Add sensors section")

	public void SC020(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest(
				"SC020-Verify that max 3 characters are accepted in Pressure field under Add sensors section");

		SoftAssert sa = new SoftAssert();
		String PrsrCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);

		String EnteredPresrcount = Setup_SensorConfigPage.get_Pressure_text();
		Thread.sleep(1000);
		String DisplayedPresrcount = Setup_SensorConfigPage.get_Pressure_text();
		sa.assertEquals(EnteredPresrcount, DisplayedPresrcount,
				"FAIL: Maximum 3 characters should be allowed in pressure field ");
		sa.assertAll();

	}
 

 //SC021-Verify the valid inputs accepted in Pressure field under Add Sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC021", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs accepted in Pressure field under Add Sensors section")

	public void SC021(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("SC021-Verify the valid inputs accepted in Pressure field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String PrsrCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(1000);
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Sensor_Titlestate(), true,
				"FAIL:Pressure_Sensor_Titlestate should be visible ");
		sa.assertAll();
	}
	
//SC022-Verify the invalid inputs that are not accepted in Pressure  field under Add Sensors section
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC022", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section")

	public void SC014(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest(
				"SC022-Verify the invalid inputs that are not accepted in Humidity field under Add Sensors section");

		SoftAssert sa = new SoftAssert();
		String PrsrCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(1000);
		String DisplayedPrsrcount = Setup_SensorConfigPage.get_Pressure_text();
		sa.assertEquals(DisplayedPrsrcount, "0", "FAIL:Application by default should display 0 for invalid data entry");
		sa.assertAll();
	}
	
	// SC023-Verify that when more than 300 is entered in Pressure field, the count is rounded off to 300

	@Test(groups = {
			"Regression" }, dataProvider = "TcSC023", dataProviderClass = sensorCofigUtility.class, description = "Verify that when more than 300 is entered in Pressure field, the count is rounded off to 300")

	public void SC023(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest(
				"SC023-Verify that when more than 300 is entered in Pressure field, the count is rounded off to 300");

		SoftAssert sa = new SoftAssert();
		String PrsrCount = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField(PrsrCount);
		Thread.sleep(1000);
		String DisplayedPresrtextboxcount = Setup_SensorConfigPage.get_Pressure_text();
		System.out.println(DisplayedPresrtextboxcount);
		sa.assertEquals(DisplayedPresrtextboxcount, "300",
				"FAIL: Pressure count more than 300 should be rounded off to 300");
		sa.assertAll();
	}

	//SC024-Verify that by clicking on Add button will add the number of sensors on the left pane for Pressure as mentioned in the field
	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on Add button will add the number of sensors on the left pane for Pressure as mentioned in the field")
	public void SC016() throws InterruptedException {
		extentTest = extent.startTest(
				"'SC024-Verify that by clicking on Add button will add the number of sensors on the left pane for Pressure as mentioned in the field");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("4");
		// String Entersencount = Setup_SensorConfigPage.get_Humidity_text();
		// System.out.println("Ented TemperatureCount " + EnterTempcount);
		// Thread.sleep(1000);
		String[] Displayedsensorcnt = Setup_SensorConfigPage.SensorCount();
		// sa.assertEquals(Entersencount,Displayedsensorcnt, "FAIL:count should be
		// same");
		// sa.assertAll();

	}

	// SC025-Verify that when 0 is added in the Pressure field it will delete the sensors on the left pane

	@Test(groups = {
			"Regression" }, description = "Verify that when 0 is added in the Pressure field it will delete the sensors on the left pane")
	public void SC025() throws InterruptedException {
		extentTest = extent.startTest(
				"SC025-Verify that when 0 is added in the Pressure field it will delete the sensors on the left pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_Sensor_Titlestate(), true,
				"FAIL: Temperature_Sensor_Titlestate should be visible ");
		Setup_SensorConfigPage.Enter_PressureCount_textField("0");
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		String actalert = Setup_SensorConfigPage.Alertmsg_txt();
		String expalert = "Please configure atleast one sensor to proceed.";
		sa.assertEquals(actalert, expalert,
				"FAIL: Application should display the alert message as there is no sensors added ");
		sa.assertAll();
	}

	// SC026-Verify that a validation message is displayed when more number of sensors are added in the Pressure field than those defined in define setup screen
	@Test(groups = {
			"Regression" }, description = "Verify that a validation message is displayed when more number of sensors are added in the Pressure field than those defined in define setup screen")
	public void SC010() throws InterruptedException {
		extentTest = extent.startTest(
				"SC026-Verify that a validation message is displayed when more number of sensors are added in the Pressure field than those defined in define setup screen");
		SoftAssert sa = new SoftAssert();
		// Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		defineSetupPage = Setup_SensorConfigPage.DefineSetup_back_btn();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		Setup_SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		String Actalert = Setup_SensorConfigPage.Alertmsg_txt();
		String Expectalert = "You are trying to add more number of sensors than defined";
		sa.assertEquals(Actalert, Expectalert, "FAIL:alert message not matched");
		sa.assertAll();
	}	
		
//SC027
	@Test(groups = {
			"Regression" }, description = "Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured")
	public void SC025() throws InterruptedException {
		extentTest = extent.startTest(
				"SC027-Verify that all the sensors added on the left pane will have label names as sensor 1,snesor 2 and so on until they are configured");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("5");
		Setup_SensorConfigPage.SensorDetails();
		}

	//SC028-Verify if Vertical scroll bar will be displayed when the number of rows exceeds the display view
	@Test(groups = {
			"Regression" }, description = "Verify if Vertical scroll bar will be displayed when the number of rows exceeds the display view")
	public void SC028() throws InterruptedException {
		extentTest = extent.startTest(
				"SC028-Verify if Vertical scroll bar will be displayed when the number of rows exceeds the display view");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("300");
		
		sa.assertEquals(Setup_SensorConfigPage.IsDiaplayingVerticalScrollBar(),true, "FAIL:Vertical scroll bar will be displayed as the number of rows exceeds the display view");
		sa.assertAll();
	}

	// SC029-Verify that on-click of the Up arrow icon for Add sensors section will collapse the section
	@Test(groups = {
			"Regression" }, description = "Verify that on-click of the Up arrow icon for Add sensors section will collapse the section")
	public void SC029() throws InterruptedException {
		extentTest = extent.startTest(
				"SC029-Verify that on-click of the Up arrow icon for Add sensors section will collapse the section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.Temperature_Field_state(),true,"FAIL: Temperature_Field should be visible ");
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		
	}
	
//SC030-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed
	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC028() throws InterruptedException {
		extentTest = extent.startTest(
				"SC030-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		sa.assertEquals(Setup_SensorConfigPage.Temperature_filters(),true,"FAIL: Temperature_filters should be filtered only emperature sensors ");
		sa.assertEquals(Setup_SensorConfigPage.Humidity_filters(),true,"FAIL: Humidity_filters should be filtered only Humidity sensors ");
		sa.assertEquals(Setup_SensorConfigPage.Pressure_filters(),true,"FAIL: Pressure_filters should be filtered only pressure sensors ");		
		sa.assertAll();
}


//SC031
	@Test(groups = {"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC031() throws InterruptedException {
		extentTest = extent.startTest(
				"SC031-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortypevisible(), true, "FAIL: Sensortype should be visible");
		sa.assertAll();
	}
	
 //SC032
	@Test(groups = {"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC032() throws InterruptedException {
		extentTest = extent.startTest(
				"SC032-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortypevisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfieldvisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsTofieldvisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsSensorLabelvisible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsAutoNumber_CheckBox_visible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_visible(), true, "FAIL: Sensortype should be visible");
		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_visible(), true, "FAIL: Sensortype should be visible");
		sa.assertAll();
	}
	
	//SC033-Verify that after sensors are added,only the sensor type drop down will be enabled and 
	//all other fields will be disabled under Configure sensors section on the right pane
	
	@Test(groups = {"Regression" }, description = "Verify that after sensors are added,only the sensor type drop down will be enabled and all other fields will be disabled under Configure sensors section on the right pane")
	public void SC033() throws InterruptedException {
		extentTest = extent.startTest(
				"SC033-Verify that after sensors are added,only the sensor type drop down will be enabled and all other fields will be disabled under Configure sensors section on the right pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortype_Enable(), true, "FAIL: Sensortype should be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsTofield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsSensorLabel_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsAutoNumber_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsAssign_Button_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertAll();	
	}
	
   
  //SC034-Verify that when clicked on the Sensors on the left pane, will enable the Sensor type field and sensor label text boxes and disabled the From and To fields.

	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC034() throws InterruptedException {
		extentTest = extent.startTest(
				"SC034-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		
		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		sa.assertEquals(Setup_SensorConfigPage.IsSensortype_Enable(), true, "FAIL: Sensortype should be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsFromfield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertEquals(Setup_SensorConfigPage.IsTofield_Enable(), false, "FAIL: Sensortype should not be Enable");
		sa.assertAll();	
		
	}
	
	//SC035 - MANUALLY
	
	//SC036-Verify that user can do multiple selections of sensors of same type
	

	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC036() throws InterruptedException {
		extentTest = extent.startTest(
				"SC036-Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("4");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		Setup_SensorConfigPage.Enter_PressureCount_textField("4");
		
		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor_Multi();
		
		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor_Multi();
	
	Setup_SensorConfigPage.Click_Pressure();
	Setup_SensorConfigPage.Click_Prsr_sensor();
	Setup_SensorConfigPage.Click_Psr_sensor_Multi();
   }

	// SC037-Verify that when clicked sensors from different sensor types on the
	// left pane will display that sensor type in the sensor type drop down in Configure sensors section
	@Test(groups = {
			"Regression" }, description = "Verify that by clicking on the filters on the left pane for temp,pressure and humidity, the respective sensors are displayed")
	public void SC037() throws InterruptedException {
		extentTest = extent.startTest(
				"SC037-Verify that when clicked sensors from different sensor types on the left pane will display that sensor type in the sensor type drop down in Configure sensors section");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");

		Setup_SensorConfigPage.Clickon_Temperature();
		Setup_SensorConfigPage.Click_Temp_sensor();
		String ExpTempsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpTempsenrtxt);
		String ActTempsenrtxt = "Temperature     (°C)";
		sa.assertEquals(ActTempsenrtxt, ExpTempsenrtxt,
				"FAIL:Temperature(°C) should be displayed in Sensor type field");
		
		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		String ExpHmdsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpHmdsenrtxt);
		String ActHmdsenrtxt = "Humidity     (%RH)";
		sa.assertEquals(ActTempsenrtxt, ExpTempsenrtxt,
				"FAIL: Humidity(%RH) should be displayed in Sensor type field");
		Setup_SensorConfigPage.Click_Pressure();
		Setup_SensorConfigPage.Click_Prsr_sensor();
		String ExpPrsrsenrtxt = Setup_SensorConfigPage.get_Sensortype_text();
		System.out.println(ExpPrsrsenrtxt);
		String ActPrsrsenrtxt = "Pressure      (Bar)";
		sa.assertEquals(ActPrsrsenrtxt, ExpPrsrsenrtxt,
				"FAIL: Pressures(Bar) should be displayed in Sensor type field");
				sa.assertAll();	
	}
  		
  //SC038

	@Test(groups = {
			"Regression" }, description = "Verify that the sensor type drop down displays Temperature only when temperature sensors are added")
	public void SC038() throws InterruptedException {
		extentTest = extent.startTest(
				"SC038-Verify that the sensor type drop down displays Temperature only when temperature sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsTemperature_visible_Drpdwnlist(),true,
				"FAIL: Temperature should be displayed in Sensor type drop down list");
				sa.assertAll();	
	}
	
	
	// SC039-Verify that the sensor type drop down displays Pressure only when pressure sensors are added
	@Test(groups = {
			"Regression" }, description = "Verify that the sensor type drop down displays Pressure only when pressure sensors are added")
	    public void SC039() throws InterruptedException {
		extentTest = extent.startTest(
				"SC039-Verify that the sensor type drop down displays Pressure only when pressure sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_PressureCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.IsPressure_visible_Drpdwnlist(), true,
				"FAIL: Pressure should be displayed in Sensor type drop down list");
				sa.assertAll();	
	}
	
	// SC040
	@Test(groups = {
			"Regression"}, description = "Verify that the sensor type drop down displays Humidity only when humidity sensors are added")
	public void SC040() throws InterruptedException {
		extentTest = extent.startTest(
				"SC040-Verify that the sensor type drop down displays Humidity only when humidity sensors are added");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		
		Setup_SensorConfigPage.Enter_HumidityCount_textField("2");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		
		sa.assertEquals(Setup_SensorConfigPage.IsHumidity_visible_Drpdwnlist(), true,
				"FAIL: Humidity should be displayed in Sensor type drop down list");
				sa.assertAll();	
	}
	
	//SC041-Verify that the max char length for From field is 1 when the no. of sensors added are in single digit
		
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC041", dataProviderClass = sensorCofigUtility.class, description = "Verify that the max char length for From field is 1 when the no. of sensors added are in single digit")

	public void SC041(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC041-Verify that the max char length for From field is 1 when the no. of sensors added are in single digit");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();

		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 1, "FAIL: max char length for From field should be 1");
		sa.assertAll();
	}
 	
  //SC042-Verify that the max char length for From field is 2 when the no. of sensors added are in double digits
		
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC042", dataProviderClass = sensorCofigUtility.class, description = "Verify that the max char length for From field is 2 when the no. of sensors added are in double digits")

	public void SC042(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC042-Verify that the max char length for From field is 2 when the no. of sensors added are in double digits");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String Fromcnt = (String) dataProvider[1];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
        Thread.sleep(1000);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());
				
		sa.assertEquals(Dis_FromTextBox.length(), 2, "FAIL:  max char length for From field should be 2");
		sa.assertAll();
	}
	
 //SC043-Verify that the max char length for From field is 3 when the no. of sensors added are in triple digits	
	
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC043", dataProviderClass = sensorCofigUtility.class, description = "Verify that the max char length for From field is 2 when the no. of sensors added are in double digits")

	public void SC043(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC043-Verify that the max char length for From field is 3 when the no. of sensors added are in triple digits");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String Fromcnt = (String) dataProvider[1];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(1000);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox.length());

		sa.assertEquals(Dis_FromTextBox.length(), 3, "FAIL:  max char length for From field should be 3");
		sa.assertAll();
}
	
	//SC044-Verify the default value displayed in From field

	@Test(groups = { "Regression" }, description = "Verify the default value displayed in From field")
	public void SC044() throws InterruptedException {
		extentTest = extent.startTest("SC044-Verify the default value displayed in From field");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

		Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		//System.out.println(Dis_FromTextBox);
		
		sa.assertEquals(Dis_FromTextBox, "1", "FAIL: 1 should be displayed by default");
		sa.assertAll();
	}

	// SC046-Verify the invalid inputs in From field
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC046", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs in From field")

	public void SC046(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC046-Verify the invalid inputs in From field");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String Fromcnt = (String) dataProvider[1];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Thread.sleep(1000);
		String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
		System.out.println(Dis_FromTextBox);

		sa.assertEquals(Dis_FromTextBox, "1", "FAIL:  It should not accept invalid values");
		sa.assertAll();
	}
 
	// SC046a-Verify the invalid inputs in From field
		@Test(groups = {
				"Regression" }, dataProvider = "TcSC046a", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs in From field")

		public void SC046a(Object... dataProvider) throws InterruptedException, ParseException {
			extentTest = extent.startTest(
					"SC046a-Verify the invalid inputs in From field");
			SoftAssert sa = new SoftAssert();

			String sesrno = (String) dataProvider[0];
			String Fromcnt = (String) dataProvider[1];

			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
			Thread.sleep(1000);
			String Dis_FromTextBox = Setup_SensorConfigPage.get_Fromfield_text();
			System.out.println(Dis_FromTextBox);

			sa.assertEquals(Dis_FromTextBox, "5", "FAIL: It should not accept more than sensor values");
			sa.assertAll();
		}

		
 // SC047-Verify that when a greater value is entered in From field than the To field the Assign button is disabled in sensor configuration screen
	@Test(description = "Verify that when a greater value is entered in From field than the To field the Assign button is disabled in sensor configuration screen")

	public void SC047() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC047-Verify that when a greater value is entered in From field than the To field the Assign button is disabled in sensor configuration screen");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From("10");
		Setup_SensorConfigPage.Enter_Num_To("2");
		Thread.sleep(1000);
		sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), false,"FAIL: The Assign button should be disabled in snensor configuration screen");
		sa.assertAll();
	}

 //SC048-Verify that the max char length for To field is 1 when the no. of sensors added are in single digit
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC048", dataProviderClass = sensorCofigUtility.class, description = "Verify that the max char length for To field is 1 when the no. of sensors added are in single digit")

	public void SC048(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC048-Verify that the max char length for To field is 1 when the no. of sensors added are in single digit");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String Fromcnt = (String) dataProvider[1];
		String Tocnt = (String) dataProvider[2];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
		Setup_SensorConfigPage.Enter_Num_To(Tocnt);
		
		String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
		System.out.println(Dis_ToTextBox.length());

		sa.assertEquals(Dis_ToTextBox.length(), 1, "FAIL: max char length for To field should be 1");
		sa.assertAll();
	}

	
//SC049-Verify that the max char length for To field is 2 when the no. of sensors added are in double digits
	
@Test(groups = {
		"Regression" }, dataProvider = "TcSC049", dataProviderClass = sensorCofigUtility.class, description = "Verify that the max char length for To field is 2 when the no. of sensors added are in double digits")

public void SC049(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC049-Verify that the max char length for To field is 2 when the no. of sensors added are in double digits");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String Fromcnt = (String) dataProvider[1];
	String Tocnt = (String) dataProvider[2];

	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
	Setup_SensorConfigPage.Enter_Num_To(Tocnt);
	
	String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
	System.out.println(Dis_ToTextBox.length());

	sa.assertEquals(Dis_ToTextBox.length(), 2, "FAIL: max char length for To field should be 2");
	sa.assertAll();
}


//SC050-Verify that the max char length for To field is 3 when the no. of sensors added are in triple digits

@Test(groups = {
		"Regression" }, dataProvider = "TcSC050", dataProviderClass = sensorCofigUtility.class, description = "SC050-Verify that the max char length for To field is 3 when the no. of sensors added are in triple digits")

public void SC050(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC050-Verify that the max char length for To field is 3 when the no. of sensors added are in triple digits");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String Fromcnt = (String) dataProvider[1];
	String Tocnt = (String) dataProvider[2];

	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
	Setup_SensorConfigPage.Enter_Num_To(Tocnt);
	
	Thread.sleep(1000);
	String Dis_FromTextBox = Setup_SensorConfigPage.get_Tofield_text();
	System.out.println(Dis_FromTextBox.length());

	sa.assertEquals(Dis_FromTextBox.length(), 3, "FAIL:  max char length for To field should be 3");
	sa.assertAll();
}

		
	//SC051-Verify the default value displayed in To field	
		
		@Test(groups = { "Regression" }, description = "Verify the default value displayed in To field")
		    public void SC051() throws InterruptedException {
			extentTest = extent.startTest("SC044-Verify the default value displayed in To field");
			SoftAssert sa = new SoftAssert();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();

			Setup_SensorConfigPage.Enter_TemperatureCount_textField("2");
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Click_Temp_sensor();
			String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
			//System.out.println(Dis_ToTextBox);
			
			sa.assertEquals(Dis_ToTextBox, "1", "FAIL: 1 should be displayed by default");
			sa.assertAll();
		}

	 // SC052-Verify the valid inputs in To field
		
		@Test(groups = {"Regression" }, dataProvider = "TcSC052", dataProviderClass = sensorCofigUtility.class, description = "SC052-Verify the valid inputs in To field")

		public void SC052(Object... dataProvider) throws InterruptedException, ParseException {
			extentTest = extent.startTest(
					"SC052-Verify the valid inputs in To field");
			SoftAssert sa = new SoftAssert();

			String sesrno = (String) dataProvider[0];
			String Fromcnt = (String) dataProvider[1];
			String 	Tocnt = (String) dataProvider[1];

			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
			Setup_SensorConfigPage.Enter_Num_To(Tocnt);

			sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), false,"FAIL: The Assign button should be Enable in snensor configuration screen");
			sa.assertAll();
		}
	 
	//SC053-Verify the invalid inputs in To field
			@Test(groups = {
					"Regression" }, dataProvider = "TcSC053", dataProviderClass = sensorCofigUtility.class, description = "Verify the invalid inputs in To field")

			public void SC053(Object... dataProvider) throws InterruptedException, ParseException {
				extentTest = extent.startTest(
						"SC053-Verify the invalid inputs in To field");
				SoftAssert sa = new SoftAssert();

				String sesrno = (String) dataProvider[0];
				String Fromcnt = (String) dataProvider[1];
				String 	Tocnt = (String) dataProvider[2];

				Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
				Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
				Setup_SensorConfigPage.Click_Temp_sensor();
				Setup_SensorConfigPage.Click_Temp_sensor();
				Setup_SensorConfigPage.Enter_Num_From(Fromcnt);
				Setup_SensorConfigPage.Enter_Num_To(Tocnt);
				
				String Dis_ToTextBox = Setup_SensorConfigPage.get_Tofield_text();
				System.out.println(Dis_ToTextBox);

				sa.assertEquals(Dis_ToTextBox, "", "FAIL:  It should not accept invalid values");
				sa.assertAll();
			}
		
	// SC054-Verify that when clicked on the Sensors on the left pane, the first
	// text box of sensor lable field will display the label name of that sensor-
	// sensor 1,sensor2, etc as per selection
	
		@Test(groups = {
			"Regression" }, description = "Verify the invalid inputs in To field")

	public void SC054() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC053-Verify the invalid inputs in To field");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("12");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Temp_sensor();
		
		String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();
		String 	ExptsnsrlblText = "Sensor 1";
		sa.assertEquals(ActualsnsrlblText,ExptsnsrlblText, "FAIL:  It should be sensor1");
		
		Setup_SensorConfigPage.Click_Temp_sensor2();
		
		String ActualsnsrlblText1 = Setup_SensorConfigPage.get_SensorLabel_text();
		String 	ExptsnsrlblText1 = "Sensor 2";
		
		sa.assertEquals(ActualsnsrlblText1,ExptsnsrlblText1, "FAIL:  It should be sensor2");

		sa.assertAll();
	}

	
 //SC055-Verify that when selected the sensor type from the drop down, the first text box of sensor lable field will display blank		
	@Test(groups = {
			"Regression" }, description = "Verify that when selected the sensor type from the drop down, the first text box of sensor lable field will display blank")

	public void SC055() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SC055-Verify that when selected the sensor type from the drop down, the first text box of sensor lable field will display blank");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		Setup_SensorConfigPage.Click_Sensortype_field();
		String actsensttxt = Setup_SensorConfigPage.get_Sensortype_text();
		String ExptsensText = "";
        //System.out.println(actsensttxt);
		sa.assertEquals(actsensttxt, ExptsensText, "FAIL:  It should be blank");

		sa.assertAll();

	}
	
	//SC056-Verify that max 8 characters are allowed in Sensor Label-first text box
	
		@Test(groups = {
		"Regression" }, dataProvider = "TcSC056", dataProviderClass = sensorCofigUtility.class, description = "Verify that max 8 characters are allowed in Sensor Label-first text box")

     public void SC056(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC056-Verify that max 8 characters are allowed in Sensor Label-first text box");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String sensrlbl = (String) dataProvider[1];
	
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);
	
	String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();
	sa.assertEquals(ActualsnsrlblText.length(), 8,"FAIL: max 8 characters are allowed in Sensor Label-first text box");

	sa.assertAll();
	
		}
			
	//SC057-Verify that when selected the sensor type from the drop down, the sensor label-first text box will be blank and enabled
			
		@Test(groups = {
		"Regression" }, description = "Verify that when selected the sensor type from the drop down, the sensor label-first text box will be blank and enabled")

    public void SC055() throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC057-Verify that when selected the sensor type from the drop down, the sensor label-first text box will be blank and enabled");
	SoftAssert sa = new SoftAssert();
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
	Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
	Setup_SensorConfigPage.Click_Sensortype_temp();
	String ActualsnsrlblText = Setup_SensorConfigPage.get_SensorLabel_text();
	
	sa.assertEquals(ActualsnsrlblText,"", "FAIL:the sensor label-first text box will be blank");
	sa.assertEquals(Setup_SensorConfigPage.IsSensorLabel_Enable(),true, "FAIL:the sensor label-first text box will be Enable");
	
	sa.assertAll();
		
		}
			
	//SC058-Verify the valid inputs for Sensor Label-First text box
		
		@Test(groups = {
		"Regression" }, dataProvider = "TcSC058", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs for Sensor Label-First text box")

     public void SC056(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC058-Verify the valid inputs for Sensor Label-First text box");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String sensrlbl = (String) dataProvider[1];
	
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);

	sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), true, "FAIL:Assign button should be enable for valid data ");

	sa.assertAll();
	}
	
		
	//SC059-Verify the invalid inputs for Sensor Label-First text box
		@Test(groups = {
		"Regression" }, dataProvider = "TcSC059", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs for Sensor Label-First text box")

     public void SC059(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC059-Verify the valid inputs for Sensor Label-First text box");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String sensrlbl = (String) dataProvider[1];
	
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter_SensorLabel(sensrlbl);

	sa.assertEquals(Setup_SensorConfigPage.IsAssignButton_Enable(), false, "FAIL:Assign button should be disable forInvalid data ");

	sa.assertAll();
	}
	
	//SC060-Verify that when clicked on the Sensors on the left pane, the second text box of sensor lable field will by default display 01
		@Test(groups = {
		"Regression" },description = "SC060-Verify that when clicked on the Sensors on the left pane, the second text box of sensor lable field will by default display 01")

     public void SC056() throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC060--Verify that when clicked on the Sensors on the left pane, the second text box of sensor label field will by default display 01");
	SoftAssert sa = new SoftAssert();
	
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
	Setup_SensorConfigPage.Click_Temp_sensor();
			sa.assertEquals(Setup_SensorConfigPage.get_SensorLabel_Num_text(),"01", "FAIL: the second text box of sensor label field will by default display 01 ");

	sa.assertAll();
	}
		
	//SC061-Verify that when selected the sensor type from the drop down, the second text box of sensor label field will by default display as 01
		
		@Test(groups = {
		"Regression" }, description = "Verify that when selected the sensor type from the drop down, the second text box of sensor label field will by default display as 01")

    public void SC055() throws InterruptedException, ParseException {
	extentTest = extent.startTest(
			"SC061-SC061-Verify that when selected the sensor type from the drop down, the second text box of sensor label field will by default display as 01");
	SoftAssert sa = new SoftAssert();
	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
	Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
	Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
	Setup_SensorConfigPage.Click_Sensortype_temp();
	
	sa.assertEquals(Setup_SensorConfigPage.get_SensorLabel_Num_text(),"01", "FAIL:the sensor label-first text box will be Enable");
	
	sa.assertAll();
		}	
		
 //SC062-Verify that max 3 characters are allowed in Sensor Label-Second text box
	@Test(groups = {
			"Regression" }, dataProvider = "TcSC062", dataProviderClass = sensorCofigUtility.class, description = "Verify that max 2 characters are allowed in Sensor Label-Second text box")

	public void SC062(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC062-Verify that max 2 characters are allowed in Sensor Label-Second text box");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String sensrlblno = (String) dataProvider[1];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
		String snlen = Setup_SensorConfigPage.get_SensorLabel_Num_text();

		sa.assertEquals(snlen.length(), 2, "FAIL: max 2 characters are allowed in Sensor Label-Second text box");

		sa.assertAll();
	}
	
	//SC063-Verify the valid inputs for Sensor Label-second text box	
		@Test(groups = {"Regression" }, dataProvider = "TcSC063", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs for Sensor Label-second text box")

    public void SC063(Object... dataProvider) throws InterruptedException, ParseException {
	extentTest = extent.startTest("SC063-Verify the valid inputs for Sensor Label-second text box");
	SoftAssert sa = new SoftAssert();

	String sesrno = (String) dataProvider[0];
	String sensrlblno = (String) dataProvider[1];

	Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
	Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
	Setup_SensorConfigPage.Click_Temp_sensor();
	Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
	Setup_SensorConfigPage.Click_assignBtn();
	
	GroupSensorspage = Setup_SensorConfigPage.Click_nextbtn_GroupSensors();

	sa.assertEquals(GroupSensorspage.GroupSensorspageName_state(), true, "FAIL: Group Sensors pageName should be displayed");

	sa.assertAll();
}

//SC064-Verify the invalid inputs for Sensor label-second text box
	
		@Test(groups = {"Regression" }, dataProvider = "TcSC064", dataProviderClass = sensorCofigUtility.class, description = "Verify the valid inputs for Sensor Label-second text box")

	    public void SC064(Object... dataProvider) throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC064-Verify the valid inputs for Sensor Label-second text box");
		SoftAssert sa = new SoftAssert();

		String sesrno = (String) dataProvider[0];
		String sensrlblno = (String) dataProvider[1];

		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField(sesrno);
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter__Num_SensorLabel(sensrlblno);
		Setup_SensorConfigPage.Click_assignBtn();
		
		String ActAlert = Setup_SensorConfigPage.Alertmsg_txt();
		String ExpAlert = "Please provide valid Sensor Label Number.";
				
		sa.assertEquals(ActAlert,ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();
	}
	
//SC065-Verify that the Auto numbering checkbox is always checked and disabled
		@Test(groups = {"Regression" }, description = "Verify the valid inputs for Sensor Label-second text box")

	    public void SC064() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC064-Verify the valid inputs for Sensor Label-second text box");
		SoftAssert sa = new SoftAssert();

		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		sa.assertEquals(Setup_SensorConfigPage.Is_Autonumber_checkedIn(),true, "FAIL: Alert message should be displayed");
		sa.assertAll();	
	}
		
//SC065.1_Verify that unique sensor label names are allowed
		@Test(groups = {"Regression" }, description = "Verify that unique sensor label names are allowed")

	    public void SC065_1() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC065_1_Verify that unique sensor label names are allowed");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter__Num_SensorLabel("02");
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorConfigPage.Click_assignBtn();
		
		String ActAlert = Setup_SensorConfigPage.Alertmsg_txt();
		String ExpAlert = "Sensor Label should be unique..";
		sa.assertEquals(ActAlert,ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();	
}
		
		
	//SC065.2_Verify that validation message is displayed when selected one sensor type and assigning label name for another sensor type
		
		@Test(groups = {"Regression" }, description = "Verify that validation message is displayed when selected one sensor type and assigning label name for another sensor type")

	    public void SC065_2() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC065_2_Verify that validation message is displayed when selected one sensor type and assigning label name for another sensor type");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Enter_HumidityCount_textField("4");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_Sensortype_Hmd();
		Setup_SensorConfigPage.Enter_SensorLabel("test");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActAlert = Setup_SensorConfigPage.Alertmsg_txt();
		String ExpAlert = "Selected sensors and selected sensor type are different. Please choose same type..";
		sa.assertEquals(ActAlert,ExpAlert, "FAIL: Alert message should be displayed");
		sa.assertAll();	
		}	
	
//SC066-Verify if the _Assign_ button is activated only when the Sensor Label and the Sensor Type text boxes inputs are provided.
		
		@Test(groups = {"Regression" }, description = "Verify if the _Assign_ button is activated only when the Sensor Label and the Sensor Type text boxes inputs are provided")

	    public void SC065_2() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC066-Verify if the _Assign_ button is activated only when the Sensor Label and the Sensor Type text boxes inputs are provided.");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");
		Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
		
		sa.assertEquals( Setup_SensorConfigPage.IsAssign_Button_Enable(),false,"FAIL: Alert message should be displayed");
		Setup_SensorConfigPage.Click_Sensortype_field();
		Setup_SensorConfigPage.Click_Sensortype_temp();
		Setup_SensorConfigPage.Enter_SensorLabel("ABC");
		sa.assertEquals( Setup_SensorConfigPage.IsAssign_Button_Enable(),true,"FAIL: Alert message should be displayed");
		sa.assertAll();		
		}	
		
	*/	
	// This script has some issues 
		
	//SC066_1-Verify that on-click of the Assign button will assign the sensor labels to the respective sensors on the left pane
		@Test(groups = {"Regression" }, description = "Verify that on-click of the Assign button will assign the sensor labels to the respective sensors on the left pane")

	    public void SC066_1() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SC066_1-Verify that on-click of the Assign button will assign the sensor labels to the respective sensors on the left pane");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("5");	
		
		Setup_SensorConfigPage.Enter_HumidityCount_textField("5");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel("test1");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActsenTxt = Setup_SensorConfigPage.get_sensortext();
		//System.out.println(ActsenTxt);
		String ExpTxt = "test1";
		sa.assertEquals( ActsenTxt,ExpTxt,"FAIL: text should be matched");
		Setup_SensorConfigPage.Clickon_Humidity();
		Setup_SensorConfigPage.Click_Hmd_sensor();
		Setup_SensorConfigPage.Enter_SensorLabel("ABC");
		Setup_SensorConfigPage.Click_assignBtn();
		String ActHmdTxt1 = Setup_SensorConfigPage.get_sensortext();
		//System.out.println(ActHmdTxt1);
		String ExphmdTxt1 = "ABC";
		sa.assertEquals( ActHmdTxt1,ExphmdTxt1,"FAIL: text should be matched");
		
		sa.assertAll();		
		}

	/*
	
		 
 //SC067-Verify that when less number of sensors are configured than those mentioned in Max sensors field in Define setup scrfeen will display a validation message	
		
		@Test(groups = {"Regression" }, description = "Verify that when less number of sensors are configured than those mentioned in Max sensors field in Define setup scrfeen will display a validation message")

	    public void SC067() throws InterruptedException, ParseException {
		extentTest = extent.startTest("Verify that when less number of sensors are configured than those mentioned in Max sensors field in Define setup scrfeen will display a validation message");
		SoftAssert sa = new SoftAssert();
		Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
		Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
		Setup_SensorConfigPage.Click_Temp_sensor();
		Setup_SensorConfigPage.Click_assignBtn();
		Setup_SensorConfigPage.Click_GroupSensors_btn();
		
	    sa.assertEquals( Setup_SensorConfigPage.Is_lessnumberSenAlertBox_Visible(),true,"FAIL: text should be matched");	
		sa.assertAll();	
		}
		
//SC068-Verify that Description button is enabled only when atleast one sensor is configured
		@Test(groups = {"Regression" }, description = "Verify that Description button is enabled only when atleast one sensor is configured")
		public void SC068() throws InterruptedException, ParseException {
			extentTest = extent.startTest("SC068-Verify that Description button is enabled only when atleast one sensor is configured");
			SoftAssert sa = new SoftAssert();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Click_assignBtn();
			
			sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(),true,"FAIL: Should be enabled");
			
			sa.assertAll();
}

   //SC069-Verify that on-click of the Description button will display the Sensor Description screen
		@Test(groups = {"Regression" }, description = "Verify that on-click of the Description button will display the Sensor Description screen")
		public void SC069() throws InterruptedException, ParseException {
			extentTest = extent.startTest("SC069-Verify that on-click of the Description button will display the Sensor Description screen");
			SoftAssert sa = new SoftAssert();
			Setup_SensorConfigPage.Click_Addsensors_Expanderbtn();
			Setup_SensorConfigPage.Enter_TemperatureCount_textField("10");
			Setup_SensorConfigPage.Click_Temp_sensor();
			Setup_SensorConfigPage.Click_assignBtn();
			
			sa.assertEquals(Setup_SensorConfigPage.IsDescription_Button_Enable(),true,"FAIL: Should be enabled");
			
			sa.assertAll();
           }
           */
        
}