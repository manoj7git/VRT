/**
 * @author ruchika.behura
 *
 */

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
import com.vrt.pages.UserManagementPage;
import com.vrt.utility.TestUtilities;

public class UserManagementTest extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;

	@BeforeTest
	public void UserCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");

		// Rename the User file (NgvUsers.uxx) if exists

		
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
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

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #"); // to add name in extent
																								// report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #"); // to add
																										// error/exception
																										// in extent
																										// report

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); // to add screenshot in extent
																							// report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
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

	// ADMN120

	@Test(groups = { "Sanity",
			"Regression" }, description = "ADMN120-Verify able to navigate to User Management screen from Admin screen"
					+ "hub page , user is navigated to the User Management screen screen")
	public void ADMN120() throws InterruptedException {
		extentTest = extent.startTest("ADMN120-Verify able to navigate to User Management screen from Admin screen"
				+ "hub page , user is navigated to the User Management screen screen");
		SoftAssert sa1 = new SoftAssert();

		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true,
				"FAIL: TC-ADMN120 -Incorrect UserManagementPage title or landed into incorrect Page");
		sa1.assertAll();
	}

	// Skipped : ADMN121- Verify with Fresh installation no users are created and
	// database is empty // Above test case is already Tested in LoginTest class
	// LOGIN_001

	// ADMN122-Verify that new user button is enabled

	@Test(groups = { "Sanity", "Regression" }, description = "ADMN122-Verify that new user button is enabled")
	public void ADMN122() throws Exception {
		extentTest = extent.startTest("ADMN122-Verify that new user button is enabled");
		SoftAssert sa2 = new SoftAssert();

		boolean state = UserManagementPage.IsNewUserBtnPresence();
		sa2.assertEquals(state, true, "FAIL: New User Button Not Available");

		sa2.assertAll();
	}

	// ADMN123

	@Test(groups = {
			"Regression" }, description = "ADMN123-Verify if select the New User button and check if a form to fill in the Name, User ID, Password, Confirm Password, Title, User Type, "
					+ "Phone, Email and Assigning privileges is opened up for entries.")
	public void ADMN123() throws Exception {
		extentTest = extent.startTest(
				"LADMN123-ADMN123-Verify if select the New User button and check if a form to fill in the Name, "
						+ "User ID, Password, Confirm Password, Title, User Type, Phone, Email and Assigning privileges is opened up for entries.");

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

	}

	// ADMN124

	@Test(dataProvider = "tcADMN124", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "ADMN124-Verify if mandatory fields are marked on the user management screen-Name, "
					+ "User ID, Password, Confirm Password, Title and User Type")

	public void ADMN124(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN124-Verify if mandatory fields are marked on the user management screen-Name, "
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

	// ADMN125 - Verify if the user name text box do not accept invalid data

	// Verify the User Name field does not accepts any special character other
	// than'-','_', // Space
	// Verify all the validation alert message

	@Test(dataProvider = "tcADMN125", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "Verify the Valdiations  for User Name Field at User Management screen")

	public void ADMN125(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN125-Verify the User Name Field at User Management screen"
				+ " do not accept In-Valid Data parameters");

		SoftAssert sa5 = new SoftAssert();
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

		sa5.assertEquals(ActInvalidFieldAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa5.assertAll();
	}

	// ADMN125a - Verify if the asset name text box accept input as upper case,
	// lower case, // numeric and special character like, hyphen, underscore and
	// space

	@Test(dataProvider = "tcADMN125a", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "Verify the Validations  for User Name Field at User Management screen")

	public void ADMN125a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN125a-Verify the User Name Field at User Management screen"
				+ " do not accept In-Valid Data parameters");

		SoftAssert sa6 = new SoftAssert();

		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		sa6.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa6.assertAll();
	}

	// ADMN125b : Verify User Name field accepts maximum of 35 characters

	@Test(groups = {
			"Regression" }, description = "Verify Name field at User Management screen accepts maximum of 35 characters")
	public void ADMN125b() throws InterruptedException {
		extentTest = extent.startTest("ADMN125b-Verify the User Name Field at User Management screen"
				+ "Verify Name field accepts maximum of 35 characters");

		SoftAssert sa7 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		String expectedtxt = "123456789A123456789A123456789012345Z"; // 36 Char input
		System.out.println("count of User Name text entered: " + expectedtxt.length());
		UserManagementPage.enterNewUserName(expectedtxt);
		String actualtextentered = UserManagementPage.GetUserNametext();
		System.out.println("count of User Name text to be entered: " + actualtextentered.length());

		sa7.assertEquals(actualtextentered.length(), 35,
				"FAIL:ADMN125b Username field accepts more than 35 characters");
		sa7.assertAll();
	}

	// ADMN129-Verify Validations for User ID Field at User management screen
	// do not accept In-Valid Data parameters

	@Test(dataProvider = "tcADMN129", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "Verify Validations for User ID Field at User management screen")

	public void ADMN129(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN129-Verify Validations for User ID Field at User management screen"
				+ " do not accept In-Valid Data parameters");

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

	//// ADMN129a-Verify Validations for User ID Field at User management screen
	// Accept In-Valid Data parameters

	@Test(dataProvider = "tcADMN129a", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "Verify Validations for User ID Field at User management screen")

	public void ADMN129a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN129a-Verify Validations for User ID Field at User management screen"
				+ " accept Valid Data parameters");

		SoftAssert sa9 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa9.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa9.assertAll();
	}

	// ADMN129b: Verify User ID field at User Management screen accepts maximum of
	// 16 characters

	@Test(groups = {
			"Regression" }, description = "Verify User ID field at User Management screen accepts maximum of 16 characters")
	public void ADMN129b() throws InterruptedException {
		extentTest = extent.startTest("ADMN129b-Verify the User Name Field at User Management screen"
				+ "Verify user id field accepts maximum of 16 characters");

		SoftAssert sa10 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		String expectedvalue = "123456789A123456B"; // 16 Char input
		System.out.println("count of User ID text entered: " + expectedvalue.length());
		UserManagementPage.enterNewUserID(expectedvalue);
		String actualvalueentered = UserManagementPage.GetUserIDtext();
		System.out.println("count of User ID text to be entered: " + actualvalueentered.length());

		sa10.assertEquals(actualvalueentered.length(), 16,
				"FAIL:ADMN125b Username field accepts more than 16 characters");
		sa10.assertAll();
	}

	// ADMN133-Verify Validations for unique User ID Field at User management screen

	@Test(dataProvider = "tcADMN133", dataProviderClass = TestUtilities.class, groups = {
			"Regression" }, description = "Verify Validations for  unique User ID Field at User management screen")

	public void ADMN133(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN133-Verify Validations for  unique User ID Field at User management screen");

		SoftAssert sa11 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa11.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa11.assertAll();
	}

}
