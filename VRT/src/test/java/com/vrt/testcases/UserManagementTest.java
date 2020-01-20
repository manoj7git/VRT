/**
 * @author ruchika.behura
 *
 */

package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

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
import com.vrt.pages.UserManagementPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.userManagementUtility;
import com.vrt.pages.AuditPage;
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.EquipmentPage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.IRTDDetailspage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.FileManagementPage;

public class UserManagementTest extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	AuditPage AuditPage;
	EquipmentHubPage EquipmentHubPage;
	EquipmentPage EquipmentPage;
	IRTDHubPage IRTDHubPage;
	IRTDDetailspage IRTDDetailspage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	FileManagementPage FileManagementPage;
	static String un = "User1";

	//@BeforeTest
	@BeforeClass
	public void UserCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/ExtentReport" + "_UserManagementTest" + ".html", true);
		extent.addSystemInfo("BS Version", "0.6.18");
		extent.addSystemInfo("Lgr Version", "1.3.1");
		extent.addSystemInfo("Csript Version", "1.0.0.0");
		extent.addSystemInfo("User Name", "Ruchika");
		extent.addSystemInfo("TestSuiteName", "User Management Test");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
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

		AppClose();
		Thread.sleep(1000);
	}

	
	// After All the tests are conducted
	//@AfterTest
	@AfterClass
	public void endReport() {
		extent.flush();
		extent.close();
		System.out.println("left AT in UMTest");
	}
	
	
	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		System.out.println("left BM in UMTest");
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #"); // to add name in extent
																								// report
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); // to add screenshot in extent
																							// report

			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screenshot/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();

	}

// ADMN001 to ADMN005 covered in login class

	
// ADMN006
	@Test(groups = { "Sanity", "Regression" }, description = "ADMN006-Verify that new user button is enabled")
	public void ADMN006() throws Exception {
		extentTest = extent.startTest("ADMN006-Verify that new user button is enabled");

		SoftAssert sa2 = new SoftAssert();

		boolean state = UserManagementPage.IsNewUserBtnPresence();

		sa2.assertEquals(state, true, "FAIL: New User Button Not Available");
		sa2.assertAll();
	}
	
	
// ADMN007
	@Test(groups = { "Regression" }, description = "Verify if select the New User button and "
			+ "check if a form to fill in the Name, User ID, Password, Confirm Password, Title, User Type, "
			+ "Phone, Email and Assigning privileges is opened up for entries.")
	public void ADMN007() throws Exception {
		extentTest = extent
				.startTest("ADMN007-Verify if select the New User button and check if a form to fill in the Name, "
						+ "User ID, Password, Confirm Password, Title, User Type, Phone, Email and "
						+ "Assigning privileges is opened up for entries.");

		// Click on NewUser button
		UserManagementPage.ClickNewUser();
		SoftAssert sa3 = new SoftAssert();

		// Validate presence of UserName text field
		sa3.assertEquals(UserManagementPage.UserNameFieldPresence(), true, "FAIL: No UName field present");

		// Validate presence of UserID text field
		sa3.assertEquals(UserManagementPage.UserIDFieldPresence(), true, "FAIL: No UID field present");

		// Validate presence of password text field
		sa3.assertEquals(UserManagementPage.PassworFieldPresence(), true, "FAIL: No PWD field present");

		// Validate presence of password text field
		sa3.assertEquals(UserManagementPage.ConPassworFieldPresence(), true, "FAIL: No Confirm PWD field present");

		// Validate presence of Title text field
		sa3.assertEquals(UserManagementPage.TitleFieldPresence(), true, "FAIL: No Title field present");

		// Validate presence of User Type DropDown field
		sa3.assertEquals(UserManagementPage.UserTypeField_EnableState(), true, "FAIL: No Title field present");

		// Validate presence of Phone text field
		sa3.assertEquals(UserManagementPage.PhoneFieldPresence(), true, "FAIL: No Phone field present");

		// Validate presence of Email text field
		sa3.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Email text field
		sa3.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Privilege check box field
		sa3.assertEquals(UserManagementPage.PrivillagecheckboxPresence(), true, "FAIL: No Privilege field present");
		sa3.assertAll();

	}

// ADMN008
	@Test(dataProvider = "tcADMN008", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "ADMN124-Verify if mandatory fields are marked on the user management screen-Name, "
					+ "User ID, Password, Confirm Password, Title and User Type")

	public void ADMN008(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN007-Verify if mandatory fields are marked on the user management screen-Name, "
						+ "User ID, Password, Confirm Password, Title and User Type");

		SoftAssert sa4 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActBlankFieldAlertMsg = UserManagementPage.AlertMsg();

		sa4.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa4.assertAll();
	}
	
// ADMN009
	@Test(dataProvider = "tcADMN009", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs allowed for User Name Field at User Management screen")

	public void ADMN009(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN009-Verify the Valid inputs allowed  for User Name Field at User Management screen");

		SoftAssert sa5 = new SoftAssert();

		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		sa5.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa5.assertAll();
	}
	
// ADMN009A
	@Test(groups = {
			"Regression" }, description = "Verify Name field at User Management screen accepts maximum of 35 characters")
	public void ADMN009A() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN009A-Verify Name field at User Management screen accepts maximum of 35 characters");

		SoftAssert sa6 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		String expectedtxt = "123456789A123456789A123456789012345Z"; // 36 Char input
		//System.out.println("count of User Name text entered: " + expectedtxt.length());
		UserManagementPage.enterNewUserName(expectedtxt);
		String actualtextentered = UserManagementPage.GetUserNametext();
		//System.out.println("count of User Name text to be entered: " + actualtextentered.length());

		sa6.assertEquals(actualtextentered.length(), 35, "FAIL: Username field accepts more than 35 characters");
		sa6.assertAll();
	}
	
// ADMN010
	@Test(dataProvider = "tcADMN010", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Invalid inputs allowed  for User Name Field at User Management screen")

	public void ADMN010(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN009-Verify the Invalid inputs allowed  for User Name Field at User Management screen");

		SoftAssert sa7 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActInvalidFieldAlertMsg = UserManagementPage.AlertMsg();

		sa7.assertEquals(ActInvalidFieldAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa7.assertAll();
	}
	

// ADMN011
	@Test(dataProvider = "tcADMN011", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Valid inputs allowed for User ID Field at User management screen")

	public void ADMN011(String Name, String UserID, String Password, String ConfirmPassword, String Title, String UserType) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN011-Verify Valid inputs allowed for User ID Field at User management screen");

		SoftAssert sa9 = new SoftAssert();

		// System.out.println(UserID);
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa9.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa9.assertAll();
	}
	
	
// ADMN011A
	@Test(groups = {
			"Regression" }, description = "Verify User ID field at User Management screen accepts maximum of 16 characters")
	public void ADMN011A() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN011A-Verify User ID field at User Management screen accepts maximum of 16 characters");
		SoftAssert sa10 = new SoftAssert();
		UserManagementPage.ClickNewUser();

		String expectedvalue = "123456789A123456B"; // 16 Char input
		System.out.println("count of User ID text entered: " + expectedvalue.length());
		UserManagementPage.enterNewUserID(expectedvalue);
		String actualvalueentered = UserManagementPage.GetUserIDtext();
		System.out.println("count of User ID text to be entered: " + actualvalueentered.length());

		sa10.assertEquals(actualvalueentered.length(), 16, "FAIL:Username field accepts more than 16 characters");
		sa10.assertAll();
	}

// ADMN012
	@Test(dataProvider = "tcADMN012", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Invalid inputs allowed for User ID Field at User management screen")

	public void ADMN012(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN012-Verify Invalid inputs allowed for User ID Field at User management screen");

		SoftAssert sa8 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActInvalidUIDAlertMsg = UserManagementPage.AlertMsg();

		sa8.assertEquals(ActInvalidUIDAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa8.assertAll();
	}
	

//ADMN013
	@Test(groups = { "Regression" }, description = "Verify Unique user ID functionality at User management screen")

	public void ADMN013() throws InterruptedException {
		extentTest = extent.startTest("ADMN013-Verify Unique user ID functionality at User management screen");
		SoftAssert sa11 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("super1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "AdminNew", "System Administrator");

		String ExpAlrtMsg = "User Id already in use. Please try different User Id";
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa11.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User ID Field do not accept unique data");
		sa11.assertAll();
	}
	
	
	
	// ADMN014	
	@Test(groups = { "Regression" }, description = "Verify Unique user Name functionality at User management screen")

	public void ADMN014() throws InterruptedException {
		extentTest = extent.startTest("ADMN014-Verify Unique user Name functionality at User management screen");
		SoftAssert sa12 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(un, "1a", getPW("adminFull"), getPW("adminFull"), "AdminNew",
				"System Administrator");
		String ExpAlrtMsg = "User Name already in use. Please try different User Name";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa12.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User NAme should be unique");
		sa12.assertAll();
	}
	
	
	// ADMN014A
	@Test(groups = { "Regression" }, description = "Verify Save button before/After entering value to password field")

	public void ADMN014A() throws InterruptedException {
		extentTest = extent.startTest("ADMN014A-Verify Save button before/After entering value to password field");
		SoftAssert sa13 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UM_SaveBtnVerification("usx", "45z", "", getPW("adminFull"), "Sr Manager",
				"System Administrator");
		sa13.assertEquals(UserManagementPage.IsSaveButtonEnable(), false, "Fail:Save Button Should be Disable");

		// Verify Save button is Enable After entering value to password and confirm
		// password field

		UserManagementPage.UM_SaveBtnVerification("usx", "45z", getPW("adminFull"), getPW("adminFull"), "Sr Manager",
				"System Administrator");
		sa13.assertEquals(UserManagementPage.IsSaveButtonEnable(), true, "Fail:Save Button Must be Enable");
		sa13.assertAll();
	}
	

	// ADMN014B
	@Test(groups = {
			"Regression" }, description = "Verify Save button before/After entering value to Confirm password field")

	public void ADMN014B() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN014B-Verify Save button before/After entering value to Confirm password field");

		SoftAssert sa14 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UM_SaveBtnVerification("usx", "45z", "66", "", "Sr Manager", "System Administrator");
		sa14.assertEquals(UserManagementPage.IsSaveButtonEnable(), false, "Fail:Save Button Should be Disable");

		UserManagementPage.UM_SaveBtnVerification("usx", "45z", getPW("adminFull"), getPW("adminFull"), "Sr Manager",
				"System Administrator");
		sa14.assertEquals(UserManagementPage.IsSaveButtonEnable(), true, "Fail:Save Button Must be Enable");
		sa14.assertAll();
	}
	
	//Tc ADMN015 to ADMN018 will be cover in policies module
	
	
	// ADMN019
	@Test(groups = {
			"Regression" }, description = "Verify the password mismatch functionality in User Management Screen")

	public void ADMN019() throws InterruptedException {
		extentTest = extent.startTest("ADMN019-Verify the password mismatch functionality in User Management Screen");
		SoftAssert sa15 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.enterNewUserName("AzAA");
		UserManagementPage.enterNewUserID("45z");
		UserManagementPage.enterNewUserPW(getPW("adminFull"));
		UserManagementPage.enterNewUserConfPW("Welcome2@AM");
		UserManagementPage.ClickPhone();
		String ExpAlrtMsg = "Password and confirm password should match";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa15.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert Message Not Matched");
		UserManagementPage.enterNewUserTitle("TestManager");
		UserManagementPage.select_UserType("System Administrator");

		sa15.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);
		sa15.assertAll();
	}
	
	
	// ADMN020
	@Test(groups = { "Regression" }, description = "Verify the save btn is disable when both password and"
			+ "confirm password are not entered in User Management Screen")

	public void ADMN020() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN020-Verify the save btn is disabl when both password and confirm password are not entered in User Management Screen");
		SoftAssert sa16 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UM_SaveBtnVerification("AzAA", "45z", "", "", "Sr Manager", "System Administrator");
		// UserManagementPage.UMDisablesaveButton2("ATestA", "55z", "Sr Manager",
		// "System Administrator");
		sa16.assertEquals(UserManagementPage.IsSaveButtonEnable(), false, "Save Button Should be Disable");
		sa16.assertAll();
	}
	

	// ADMN021
	@Test(dataProvider = "tcADMN021", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs allowed for Title field validations")

	public void ADMN021(String Name, String UserID, String Password, String ConfirmPassword, String Title, String UserType) throws InterruptedException {
		extentTest = extent.startTest("ADMN021-Verify the Valid inputs allowed for Title field validations");

		SoftAssert s18 = new SoftAssert();

		// System.out.println(UserID);
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		s18.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		s18.assertAll();
	}
	

	// ADMN021a
	@Test(groups = {"Regression" }, 
			description = "Verify the Valid inputs-Max Char allowed for Title field validations")

	public void ADMN021a() throws InterruptedException {
		extentTest = extent.startTest("ADMN021a-Verify the Valid inputs-Max Char allowed for Title field validations");
		
		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();

		String expectedvalue = "12345678901234567890123456789012345678901234567890b"; // 51 Char input		
		UserManagementPage.enterNewUserTitle(expectedvalue);
		String actualvalueentered = UserManagementPage.get_UserTitle();

		sa.assertEquals(actualvalueentered.length(), 50, "FAIL:Username field accepts more than 50 characters");
		sa.assertAll();
	}
	
	
	// ADMN022	
	@Test(dataProvider = "tcADMN022", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Invalid inputs not allowed for Title field validations")

	public void ADMN022(String Name, String UserID, String Password, String ConfirmPassword, 
			String Title, String UserType, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ADMN022-Verify the Invalid inputs not allowed for Title field validations");

		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message should be displayed");
		sa.assertAll();
	}
	
	
	// ADMN023-This TC Can be tested while testing user privileges

	// ADMN024

	@Test(groups = { "Regression" }, description = "Verify the Invalid inputs for User type field validations")

	public void ADMN024() throws InterruptedException {
		extentTest = extent.startTest("ADMN023-Verify the Invalid inputs for User type field validations");

		SoftAssert s19 = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Aop", "A90", getPW("adminFull"), getPW("adminFull"), "Admin",
				"Select");
		String ExpAlrtMsg = "Please select valid user type";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s19.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as-Please select valid user type");
		s19.assertAll();
	}

	// ADMN025

	@Test(dataProvider = "tcADMN025", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs for Phone number field")

	public void ADMN025(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN025-Verify the Valid inputs for Phone number field");

		SoftAssert sa20 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		sa20.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa20.assertAll();
	}

	// ADMN026

	@Test(dataProvider = "tcADMN026", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Invalid inputs for Phone number field")

	public void ADMN026(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN026-Verify the Invalid inputs for Phone number field");

		SoftAssert sa = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		String ExpAlrtMsg = (String) dataProvider[8];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as-Phone accepts only numbers");
		sa.assertAll();
	}

	// ADMN027

	@Test(groups = { "Regression" }, description = "Verify that Email field is non-mandatory")

	public void ADMN027() throws InterruptedException {
		extentTest = extent.startTest("ADMN027-Verify that  Email field is non-mandatory");
		SoftAssert sa21 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields("tesb", "UI6", getPW("adminFull"), getPW("adminFull"),
				"titlrr", "System Administrator", "9023456789", "");
		sa21.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa21.assertAll();
	}

	// ADMN028

	@Test(dataProvider = "tcADMN028", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Invalid formats for Email Field at User Management Screen")

	public void ADMN028(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN028-Verify Invalid formats for Email Field at User Management Screen");

		SoftAssert sa = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		String ExpAlrtMsg = (String) dataProvider[8];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as-Please enter a valid Email ID");
		sa.assertAll();
	}

	// ADMN029

	@Test(dataProvider = "tcADMN029", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify valid formats for Email Field at User Management Screen")
	public void ADMN029(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN029-Verify Valid formats for  Email Field at User Management Screen");

		SoftAssert sa = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa.assertAll();
	}

	// ADMN030

	@Test(groups = { "Regression" }, description = "Verify Valid validation for Photo field")
	public void ADMN030() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN030-Verify Valid validation for Photo field");
		UserManagementPage.ClickNewUser();
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_UploadBrowseBtn();
		UserManagementPage.upload_UserImage("UserimageValid");
		System.out.println("Image Uploaded Successfully");
	}
	

	// ADMN031
	@Test(groups = { "Regression" }, description = "Verify Invalid validation for Photo field")
	public void ADMN031() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN031-Verify Invalid validation for Photo field");
		UserManagementPage.ClickNewUser();
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_UploadBrowseBtn();
		UserManagementPage.upload_UserImage("UserimageInValid.jpg");
		SoftAssert sa22 = new SoftAssert();
		String ExpAlrtMsg = "Select image file with size less than 5 mb";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa22.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Alert message should be displayed as-Select image file with size less than 5 mb ");
		sa22.assertAll();
	}
	

	
// ADMN032

	@Test(groups = { "Regression" }, description = "Verify the Disable User Account check Box validations")
	public void ADMN032() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN032-Verify the Disable User Account check Box validations");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();
		sa.assertEquals(UserManagementPage.IsDisableUserCheckBox_Disable(), false,
				"FAIL: Check Box should be in disable state For new user");
		sa.assertAll();
	}

	
// ADMN032A

	@Test(groups = { "Regression" }, description = "Verify Application should not allow to login with disable User id")
	public void ADMN032A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN032A-Verify Application should not allow to login with disable User id ");

		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMPrivilages("dsbl1", getUID("Dsbluser"), "Start@1AM", "Start@1AM", "AdminNew",
				"System Administrator");
		//UserManagementPage.clickPrivCreateEditAsset();
		//UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("dsbl1");
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		LoginPage.EnterUserID("1D");
		LoginPage.EnterUserPW("Start@1AM");
		LoginPage.ClickLoginBtn();
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User Should Not be Allowed To Login");
		sa.assertAll();
	}
	
	
//ADMN033

	@Test(groups = { "Regression" }, description = "Verify if a user can not disable his own account")
	public void ADMN033() throws InterruptedException {
		extentTest = extent.startTest("ADMN033-Verify if a user can not disable his own account");
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(UserManagementPage.DisableAlertMsgVisible(), true,
				"Fail: user should not able to disable his own account");
		sa.assertAll();
	}
	

//ADMN034
	@Test(groups = { "Regression" }, description = "Verify the functionality when disabled user credentials "
			+ "are given in authentication window of Equipment-Logger details screen")
	public void ADMN034() throws InterruptedException {
		extentTest = extent.startTest("ADMN034-Verify the functionality when disabled user credentials are given in "
				+ "authentication window of Equipment-Logger details screen");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("ADMN034", "034", "Start@1AM", "Start@1AM", "AdminNew",
				"System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		MainHubPage = UserManagementPage.ClickBackButn();
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("ADMN034");
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.BaseStation_EqipCreation_MandatoryFields("ADMN034", "102", "ADMN034");
		UserLoginPopup("034", "Start@1AM");
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = EquipmentPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Disable user should not be able to create equipments");
		sa.assertAll();
	}
	

	
	//ADMN034A
	@Test(groups = {"Regression" }, description = "ADMN034A-Verify the functionality when disabled "
			+ "user credentials are given in authentication window of Edit Equipment")
	public void ADMN034A() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN034A-Verify the functionality when disabled user credentials are "
				+ "given in authentication window of Edit Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("102B", "202", "IRTD");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("102B");
		IRTDDetailspage.EditIRTDEquip("test");
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = IRTDDetailspage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to edit equipments");
		sa.assertAll();
	}

	//dependsOnMethods = { "ADMN032A", "ADMN034A" }
	// ADMN034B
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of delete Equipment")
	public void ADMN034B() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN034B-Verify the functionality when disabled user credentials are given in authentication window of delete Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("102B");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = IRTDDetailspage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to delete equipments");
		sa.assertAll();
	}

//ADMN036
	//dependsOnMethods = "ADMN032A"
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of Assets screen")
	public void ADMN036() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN036-Verify the functionality when disabled user credentials are given in authentication window of Assets screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("DUAst2", "201A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to create asserts");
		sa.assertAll();
	}

	// ADMN036A
	//dependsOnMethods = "ADMN032A",
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of Edit in Assets screen")
	public void ADMN036A() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN036A-Verify the functionality when disabled user credentials are given in authentication window of Edit in Assets screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("DUAst3", "202A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("DUAst3");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to Edit in Assets screen");
		sa.assertAll();
	}

	// ADMN036B
	//dependsOnMethods = { "ADMN032A", "ADMN036A" },
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of delete Assets screen")
	public void ADMN036B() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN036A-Verify the functionality when disabled user credentials are given in authentication window of delete Assets screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("DUAst3");
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to delete in Assets screen");
		sa.assertAll();
	}

//ADMN042
	@Test(groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of Admin screen-Create")
	public void ADMN042() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN042-Verify the functionality when disabled user credentials are given in authentication window of Admin screen-create");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Eqp3", getUID("SysAdmin"), "1Start@1AM", "1Start@1AM",
				"AdminNew", "System Administrator");
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to create new user ");
		sa.assertAll();
	}

//ADMN042A
	@Test(groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of Admin screen-Edit")
	public void ADMN042A() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN042A-Verify the functionality when disabled user credentials are given in authentication window of Admin screen-Edit");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to save any user details ");
		sa.assertAll();
	}

//ADMN042B
	@Test(groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of Admin screen-Delete")
	public void ADMN042B() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN042A-Verify the functionality when disabled user credentials are given in authentication window of Admin screen-Delete");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to delete any user details ");
		sa.assertAll();
	}

	// ADMN043
	//dependsOnMethods = "ADMN032A",
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of File Management screen")
	public void ADMN043() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN043-Verify the functionality when disabled user credentials are given in authentication window of File Management screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to access File SyncIn ");
		sa.assertAll();
	}

	// ADMN043A
	//dependsOnMethods = "ADMN032A",
	@Test( groups = {
			"Regression" }, description = "Verify the functionality when disabled user credentials are given in authentication window of File Management screen")
	public void ADMN043A() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN043A-Verify the functionality when disabled user credentials are given in authentication window of File Management screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncOutBtn();
		UserLoginPopup(getUID("Dsbluser"), getPW("Dsbluser"));
		String ExpAlrtMsg = "User account has been disabled, please contact administrator";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:  Disable user should not be able to access File SyncIn ");
		sa.assertAll();
	}

// ADMN044
	@Test(groups = { "Regression" }, description = "Verify User Creation for an Administator User")
	public void ADMN044() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN044-Verify User Creation for an Administrator User");

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("TA13", getUID("TestAdmin"), "EStart@5AM", "EStart@5AM", "Admin",
				"System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		SoftAssert s = new SoftAssert();
		String Expalrt = "TA13";
		String actalrt = UserManagementPage.AlertMsg();
		System.out.println(actalrt);
		String rplc = actalrt.replace("\"", "").replace(".", "").replace(":", "");
		// String[] actwords = rplc.split("\\s");
		String[] actwords = rplc.split(" ");
		String Actstr = Arrays.toString(actwords);
		// String Actstr = String.join(" ", actwords);

		for (String w1 : actwords) {
			System.out.println(w1);
		}

		s.assertEquals(Actstr.contains(Expalrt), true, "fail: user saved message should be displayed");
		s.assertEquals(actwords[2], Expalrt, "fail: user saved message should be displayed");
		s.assertAll();

	}

// ADMN045
	//dependsOnMethods = "ADMN044",
	@Test( groups = {
			"Regression" }, description = "ADMN045-Verify Reset pwd functionality for second user-Admin")
	public void ADMN045() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN045-Verify Reset pwd functionality for second user-Admin");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.EnterUserID(getUID("TestAdmin"));
		LoginPage.EnterUserPW("EStart@5AM");
		LoginPage.ClickLoginBtn();
		MainHubPage = LoginPage.EnterNewPWtext("Start@5AM");
		// System.out.println("Password Reset Successfully");
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("TestAdmin"), getPW("TestAdmin"));
		SoftAssert sa25 = new SoftAssert();
		String ExpUname = "TA13";
		String ActUname = MainHubPage.UserNameText();
		sa25.assertEquals(ActUname, ExpUname, "Fail-User Name should be available ");
		sa25.assertAll();
	}

// ADMN045A
	//dependsOnMethods = { "ADMN044", "ADMN045" }, 
	@Test(groups = {
			"Regression" }, description = "Verify user should not be able to login with the Pre-Reset password")
	public void ADMN045A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN045A-Verify user should not be able to login with the Pre-Reset password");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.EnterUserID(getUID("TestAdmin"));
		LoginPage.EnterUserPW("EStart@5AM");
		LoginPage.ClickLoginBtn();
		String ExptedAlert = "Invalid Credential, Please try again";
		String ActAlert = LoginPage.AlertMsg();
		SoftAssert sa26 = new SoftAssert();
		sa26.assertEquals(ActAlert, ExptedAlert,
				"FAIL:Invalid Credential, Please try again message should be displayed");
		sa26.assertAll();
	}
	

//ADMN069
	@Test(groups = { "Regression" }, description = "Verify mandatory login for a new user before saving any changes")
	public void ADMN069() throws InterruptedException, ParseException, IOException, AWTException {
		
		extentTest = extent.startTest("ADMN069-Verify mandatory login for a new user before saving any changes");
		SoftAssert s = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMPrivilages("NewU1", getUID("Newuser"), "Start@7AM", "Start@7AM", "AdminNew",
				"System Administrator");
		//UserManagementPage.clickPrivCreateEditAsset();
		//UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));		
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("TestX", "11X", "1Start@1AM", "1Start@1AM", "Admin", "Operator");
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExptedAlert = "Please login the system at least once";
		String ActAlert = UserManagementPage.AlertMsg();		
		s.assertEquals(ActAlert, ExptedAlert, "FAIL: Alert message should be displayed");
		s.assertAll();
	}
	
	
	
	//ADMN069A
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Equipment Creation")
	public void ADMN069A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN069A-Verify mandatory login for a new user before saving any changes-Equipment Creation");
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("ADMN069A", "69A", "1Start@1AM", "1Start@1AM", "Admin", "Operator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.BaseStation_EqipCreation_MandatoryFields("ADMN069A", "20A", "ADMN069A");
		UserLoginPopup("69A", "1Start@1AM");
		String ExptedAlert = "Please login the system at least once";
		String ActAlert = EquipmentPage.AlertMsg();
		SoftAssert s = new SoftAssert();
		s.assertEquals(ActAlert, ExptedAlert, "FAIL: Alert message should be displayed");
		s.assertAll();
	}

	
//ADMN069B
	//dependsOnMethods = "ADMN034A",
	@Test( groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Equipment Edit")
	public void ADMN069B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN069B-Verify mandatory login for a new user before saving any changes-Equipment-Edit");
		MainHubPage = UserManagementPage.ClickBackButn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("1022B", "2202", "IRTD");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("1022B");
		IRTDDetailspage.EditIRTDEquip("test");
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExptedAlert = "Please login the system at least once";
		String ActAlert = IRTDDetailspage.AlertMsg();
		SoftAssert s = new SoftAssert();
		s.assertEquals(ActAlert, ExptedAlert,
				"FAIL: Alert message should be displayed as Please login the system at least once ");
		s.assertAll();
	}

//ADMN069C
	//dependsOnMethods = "ADMN034A", 
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Equipment Delete")
	void ADMN069C() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN069C-Verify mandatory login for a new user before saving any changes-Equipment Delete");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("102B");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = IRTDDetailspage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once ");
		sa.assertAll();
	}

//ADMN069D
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Aseert Creation")
	public void ADMN069D() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN069D-Verify mandatory login for a new user before saving any changes-Aseert Creation");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("NWAst2", "201A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once ");
		sa.assertAll();
	}

//ADMN069E
	//dependsOnMethods = "ADMN036A",
	@Test( groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Aseert Edit")
	public void ADMN069E() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN069E-Verify mandatory login for a new user before saving any changes-Aseert Edit");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("DUAst3");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once ");
		sa.assertAll();
	}

//ADMN069F
	//dependsOnMethods = "ADMN036A",
	@Test( groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Aseert Delete")
	public void ADMN069F() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN069F-Verify mandatory login for a new user before saving any changes-Aseert Delete");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("DUAst3");
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once");
		sa.assertAll();
	}

//ADMN069G
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Admin screen-Edit")
	public void ADMN069G() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN069G-Verify mandatory login for a new user before saving any changes-Admin screen-Edit");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once");
		sa.assertAll();
	}

//ADMN069H
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Admin screen-Delete")
	public void ADMN069H() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN069H-Verify mandatory login for a new user before saving any changes-Admin screen-Delete");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once");
		sa.assertAll();
	}

//ADMN069I
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-File Management screen")
	public void ADMN069I() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN069I-Verify mandatory login for a new user before saving any changes-File Management screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once");
		sa.assertAll();
	}

//ADMN069J
	@Test(groups = {
			"Regression" }, description = "Verify mandatory login for a new user before saving any changes-Admin screen-Delete File Management screen")
	public void ADMN069J() throws InterruptedException {
		extentTest = extent.startTest(
				"ADMN069J-Verify the functionality when disabled user credentials are given in authentication window of File Management screen");
		SoftAssert sa = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncOutBtn();
		UserLoginPopup(getUID("Newuser"), getPW("Newuser"));
		String ExpAlrtMsg = "Please login the system at least once";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Alert message should be displayed as Please login the system at least once");
		sa.assertAll();
	}

//ADMN126
	//dependsOnMethods = { "ADMN044", "ADMN045" }, 
	@Test(groups = {
			"Regression" }, description = "Verify the delete button functionality in user management screen")
	public void ADMN126() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN126-Verify the delete btn functionality in user mgmt screen");
		SoftAssert s27 = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("TA13");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		s27.assertEquals(UserManagementPage.Delete_confirmationPopupvisible(), true);
		s27.assertAll();
	}

//ADMN126A
	//dependsOnMethods = { "ADMN044", "ADMN045" }, 
	@Test(groups = {
			"Regression" }, description = "Verify NO button functionality from the Delete confirmation pop-up")
	public void ADMN126A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN126A-Verify NO button functionality from the Delete confirmation pop-up");
		SoftAssert sa28 = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("TA13");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickNoBtn();
		// Check for user name available
		String ExpUName = "TA13";
		String ActUName = UserManagementPage.GetUserNametext();
		sa28.assertEquals(ActUName, ExpUName, "FAIL:User name should be available");
		sa28.assertAll();
	}

//ADMN127
	//dependsOnMethods = { "ADMN044", "ADMN045" },
	@Test( groups = {
			"Regression" }, description = "Verify user is able to delete the selected account")
	public void ADMN127() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN127-Verify user is able to delete the selected account");
		SoftAssert sa29 = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("TA13");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickYesBtn();
		String Expalrt = "TA13";
		String actrslt = UserManagementPage.AlertMsg();
		System.out.println(actrslt);
		String rplc = actrslt.replace("\"", "").replace(".", "").replace(":", "");
		// String[] actwords = rplc.split("\\s");
		String[] actwords = rplc.split(" ");
		String Actstr = Arrays.toString(actwords);
		for (String w : actwords) {
			System.out.println(w);
		}

		sa29.assertEquals(Actstr.contains(Expalrt), true, "fail:User Deleted message should be displayed");
		sa29.assertEquals(actwords[2], Expalrt, "fail:User Deleted message should be displayed");
		sa29.assertAll();
	}

//ADMN128
	//dependsOnMethods = { "ADMN044", "ADMN045", "ADMN127" }, 
	@Test(groups = {
			"Regression" }, description = "Verify the deleted user should not be able to login to the application")
	public void ADMN128() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN128_Verify the deleted user should not be able to login to the application");
		SoftAssert sa30 = new SoftAssert();
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		LoginPage.InvalidLogin(getUID("TestAdmin"), getPW("TestAdmin"));
		String ExpAlrtMsg = "Invalid Credential, Please try again.";
		String ActAlertMsg = LoginPage.AlertMsg();
		sa30.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Invalid Credential, Please try again message Not displaying");
		sa30.assertAll();
	}

//ADMN129
	//dependsOnMethods = { "ADMN044", "ADMN045", "ADMN127" },
	@Test( groups = {
			"Regression" }, description = "ADMN129-Verify Create a new user by Entering already deleted  User ID ")
	public void ADMN129() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN129-Verify Create a new user by Entering already deleted  User ID ");
		SoftAssert s31 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("TA13", getUID("TestAdmin"), "EStart@5AM", "EStart@5AM", "Admin",
				"System Administrator");
		String ExpAlrtMsg = "UserId already in use and in deleted list, Please try different UserID";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s31.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Alert meg should be display as-UserId already in use and in deleted list");
		s31.assertAll();
	}

//ADMN132
	@Test(groups = { "Regression" }, description = "Verify the cancel button functionality")
	public void ADMN132() throws InterruptedException {
		extentTest = extent.startTest("ADMN132-Verify the cancel button functionality");
		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.ClickCancelBtn();
		// Validate UserName text field is not enabled
		sa.assertEquals(UserManagementPage.UserNameFieldPresence(), false, "FAIL:UName field should be disable");
		sa.assertAll();
	}
	

}
