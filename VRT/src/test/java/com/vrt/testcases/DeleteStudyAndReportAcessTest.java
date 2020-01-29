/**
 * @author ruchika.behura
 *
 */
package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;

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
import com.vrt.pages.EquipmentHubPage;
import com.vrt.pages.EquipmentPage;
import com.vrt.utility.TestUtilities;
import com.vrt.pages.IRTDDetailspage;
import com.vrt.pages.IRTDHubPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.AuditPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.Setup_defineSetupPage;



import bsh.ParseException;

public class DeleteStudyAndReportAcessTest extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	EquipmentHubPage EquipmentHubPage;
	EquipmentPage EquipmentPage;
	IRTDDetailspage IRTDDetailspage;
	IRTDHubPage IRTDHubPage;
	FileManagementPage FileManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	@BeforeTest
	public void UserCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.39");
		extent.addSystemInfo("BS Version", "0.6.19");
		extent.addSystemInfo("Lgr Version", "1.2.9");
		extent.addSystemInfo("User Name", "Ruchika");
		extent.addSystemInfo("TestSuiteName", "User Privilages Test");

// Rename the User file (NgvUsers.uxx) if exists
		
/*	renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
	
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		
		//ADMIN user creation

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("DPFR", getUID("Delrprt"), "Start5@5AM", "Start5@5AM","AdminNew", "System Administrator");
		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		LoginPage.EnterUserID("5");
		LoginPage.EnterUserPW("Start5@5AM");
		LoginPage.ClickLoginBtn();
		MainHubPage = LoginPage.EnterNewPWtext("Start@5AM");
		LoginPage=MainHubPage.UserSignOut();
		
		// Supervisor user creation
		
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Sup1", getUID("SysSupervisor"), "3Welcome3@AM", "3Welcome3@AM","Supervisor", "Supervisor");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		LoginPage.EnterUserID("3");
		LoginPage.EnterUserPW("3Welcome3@AM");
		LoginPage.ClickLoginBtn();
		MainHubPage = LoginPage.EnterNewPWtext("Welcome3@AM");
		*/
		//OPERATOR user creation (ADMN096)
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Ope1", getUID("SysOperator"), "4Welcome4@AM", "4Welcome4@AM","Operator", "Operator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		LoginPage.EnterUserID("4");
		LoginPage.EnterUserPW("4Welcome4@AM");
		LoginPage.ClickLoginBtn();
		MainHubPage = LoginPage.EnterNewPWtext("Welcome4@AM");
		
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
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #"); 
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #"); 
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); 
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

	// ADMN051-Verify if Administrator is able to access the default
	// privilege-Delete Delete Pass_Fail reports
	// Pass_Fail reports should be available to perform delete action
	// generate a report manually

	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete Delete Pass_Fail reports")
	public void ADMN051() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN051_Verify if Administrator is able to access the default privilege-Delete Delete Pass_Fail reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("Delrprt"), getPW("Delrprt"));
		assetHubPage = MainHubPage.ClickAssetTile();
		// assetCreationPage = assetHubPage.Click_AddAssetButton();
		// assetCreationPage.assetCreation("DAst1","A6","HeatBath","HYdd","Ind");
		// UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		// assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true);
		s.assertAll();
	}

	// ADMN063-Verify if Administrator is able to access the default
	// privilege-Delete Setups
	// set up Files should be available to perform delete action

	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete Setups")
	public void ADMN063() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN063_Verify if Administrator is able to access the default privilege-Delete Setups");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("Delrprt"), getPW("Delrprt"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_SetupTile();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true);
		s.assertAll();
	}

//ADMN065A-Verify if Administrator is able to access the default privilege-Delete StudyFiles
	// StudyFiles should be available to perform delete action
	// generate a study File manually
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete StudyFiles")
	public void ADMN065A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN065A_Verify if Administrator is able to access the default privilege-Delete StudyFiles");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("Delrprt"), getPW("Delrprt"));
		assetHubPage = MainHubPage.ClickAssetTile();
		// assetCreationPage = assetHubPage.Click_AddAssetButton();
		// assetCreationPage.assetCreation("DAst1","A6","HeatBath","HYdd","Ind");
		// UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		// assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true);
		s.assertAll();
	}

//ADMN065B-Verify if Administrator is able to access the default privilege-Delete_QualReports
	// Qual Reports should be available to perform delete action
	// generate a report manually
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete_Reports")
	public void ADMN065B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN065B_Verify if Administrator is able to access the default privilege-Delete_Reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("Delrprt"), getPW("Delrprt"));
		assetHubPage = MainHubPage.ClickAssetTile();
		// assetCreationPage = assetHubPage.Click_AddAssetButton();
		// assetCreationPage.assetCreation("DAst1","A6","HeatBath","HYdd","Ind");
		// UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		// assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true);
		s.assertAll();
	}

	// ADMN065C-Verify if Administrator is able to access the default
	// privilege-Delete_SetUpReports
	// SetUp Reports Reports should be available to perform delete action
	// generate a report manually
	@Test(groups = {
			"Regression" }, description = "Verify if Administrator is able to access the default privilege-Delete_SetUpReports")
	public void ADMN065C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN065C_Verify if Administrator is able to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("Delrprt"), getPW("Delrprt"));
		assetHubPage = MainHubPage.ClickAssetTile();
		// assetCreationPage = assetHubPage.Click_AddAssetButton();
		// assetCreationPage.assetCreation("DAst1","A6","HeatBath","HYdd","Ind");
		// UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		// assetHubPage = assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("Delrprt"), getPW("Delrprt"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true);
		s.assertAll();
	}

	// ADMN079-Verify Supervisor is unable to access the non-default privilege-Delete Setups
	// set up Files should be available to perform delete action

	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the non-default privilege-Delete Setups")
	public void ADMN079() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN079_Verify Supervisor is unable to access the non-default privilege-Delete Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_SetupTile();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Setups");
		sa.assertAll();
	}

	// ADMN081-Verify Supervisor is unable to access the non-default privilege-Delete StudyFiles_Reports
	// StudyFiles should be available to perform delete action
	// generate a study File manually

	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the non-default privilege-Delete StudyFiles")
	public void ADMN081A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN081A_Verify Supervisor is unable to access the non-default privilege-Delete StudyFiles");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset StudyFiles");
		sa.assertAll();

	}

	// ADMN081B-Verify Supervisor is unable to access the non-default
	// privilege-Delete Reports-Qual
	// Qual Reports should be available to perform delete action
	// generate a report manually
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the default privilege-Delete_Reports")
	public void ADMN081B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN081B_Verify Supervisor is unable to access the default privilege-Delete_Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset reports");
		sa.assertAll();
	}

	// ADMN081C-Verify if Supervisor is able to access the default
	// privilege-Delete_SetUpReports
	// SetUp Reports Reports should be available to perform delete action
	// generate a report manually
	@Test(groups = {
			"Regression" }, description = "Verify Supervisor is unable to access the default privilege-Delete_SetUpReports")
	public void ADMN081C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN081C_Verify Supervisor is unable to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset reports");
		s.assertAll();
	}

	// ADMN081D-Verify if Supervisor is unable to Delete documents from Asset
	// Details-Documents tile

	@Test(groups = { "Regression" }, description = "Verify if Supervisor is unable to Delete Pass_Fail reports")
	public void ADMN081D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN087B_Verify if Supervisor is unable to Delete Pass_Fail reports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("Testing Delete Acess.docx");
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}
	// ADMN081E-Verify if Supervisor is able to access the default privilege-Delete
	// Pass_Fail Report
	// Pass_Fail reports should be available to perform delete action
	// generate a report manually

	@Test(groups = {
			"Regression" }, description = "Verify if Supervisor is able to access the default privilege-Delete Pass_Fail Report")
	public void ADMN081E() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN081E-Verify if Supervisor is able to access the default privilege-Delete Pass_Fail Report");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}

	// ADMN103-Verify if Operator is unable to access the non-default privilege-Delete StudyFiles_Reports
	// StudyFiles should be available to perform delete action
	// generate a study File manually

	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the non-default privilege-Delete StudyFiles")
	public void ADMN103A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103A_Verify Operator is unable to access the non-default privilege-Delete StudyFiles");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_QualTile();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset StudyFiles");
		sa.assertAll();

	}

	// ADMN103B-Verify Operator is unable to access the non-default privilege-Delete
	// Reports-Qual
	// Qual Reports should be available to perform delete action
	// generate a report manually

	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the default privilege-Delete_Reports")
	public void ADMN103B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103B_Verify Operator is unable to access the default privilege-Delete_Reports");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_QualUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset reports");
		sa.assertAll();
	}

	// ADMN103C-Verify if Operator is able to access the default
	// privilege-Delete_SetUpReports
	// SetUp Reports Reports should be available to perform delete action
	// generate a report manually

	@Test(groups = {
			"Regression" }, description = "Verify Operator is unable to access the default privilege-Delete_SetUpReports")
	public void ADMN103C() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN103C_Verify Operator is unable to access the default privilege-Delete_SetUpReports");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_SetupUnderReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Asset reports");
		s.assertAll();
	}

	// ADMN103D-Verify if Operator is unable to Delete documents from Asset
	// Details-Documents tile

	@Test(groups = {
			"Regression" }, description = "Verify if Operator is unable to Delete documents from Asset Details-Documents tile")
	public void ADMN103D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN103D_Verify if Operator is unable to Delete documents from Asset Details-Documents tile");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		assetDetailsPage.uploadDoc_Assetdetails("Testing Delete Acess.docx");
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}
	// ADMN103E-Verify if Operator is able to access the default privilege-Delete
	// Pass_Fail Report
	// Pass_Fail reports should be available to perform delete action
	// generate a report manually

	@Test(groups = {
			"Regression" }, description = "Verify if Operator is able to access the default privilege-Delete Pass_Fail Report")
	public void ADMN103E() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN103E-Verify if Operator is able to access the default privilege-Delete Pass_Fail Report");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("asset41");
		assetDetailsPage.Click_reportsTile();
		assetDetailsPage.Click_PassFailReport();
		assetDetailsPage.Delete_study_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access-Delete Pass_Fail reports");
		s.assertAll();
	}

	// ADMN120-Verify if Operator is able to access the non-default privilege-Delete
	// Set up Files should be available to perform delete action

		@Test(groups = {"Regression" }, description = "Verify Operator is unable to access the non-default privilege-Delete Setups")
		public void ADMN120() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN120_Verify Operator is unable to access the non-default privilege-Delete Setups");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("asset41");
			assetDetailsPage.click_SetupTile();
			assetDetailsPage.Delete_study_report();
			UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator should be unable to access- Delete Setups");
			sa.assertAll();
		}


}
	