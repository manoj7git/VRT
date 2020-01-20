/**
 * @author ruchika.behura
 *
 */
package com.vrt.testcases;

import java.awt.AWTException;
import java.io.IOException;

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

public class UserPrivilagesTest extends BaseClass {
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
	
	//@BeforeTest
	@BeforeClass	
	public void UserCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport" + "_UserPrivilagesTest.html", true);
		extent.addSystemInfo("BS Version", "0.6.19");
		extent.addSystemInfo("Lgr Version", "1.2.9");
		extent.addSystemInfo("User Name", "Ruchika");
		extent.addSystemInfo("TestSuiteName", "User Privilages Test");

		// Rename the User file (NgvUsers.uxx) if exists
		 
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
        renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTEquipments");
        renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");

		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
		
	

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		
		//ADMIN user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "Adm1", getUID("SysAdmin"), "1Start@1AM", "AdminNew",
				"1234556", "abc@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysAdmin"), "1Start@1AM", getPW("SysAdmin"));
		LoginPage=MainHubPage.UserSignOut();
		
		// SUPERVISOR user creation (ADMN071)		
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Sup1", getUID("SysSupervisor"), "4Start@4AM", "SUpNew",
				"123345678", "newSup@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "4Start@4AM", getPW("SysSupervisor"));
		LoginPage = MainHubPage.UserSignOut();
		
		// OPERATOR user creation (ADMN096)
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), "Ope1", getUID("SysOperator"), "6Start@6AM", "OperatorNew",
				"12345678", "newOpe@gmail.com");
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(1000);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "6Start@6AM", getPW("SysOperator"));
		LoginPage = MainHubPage.UserSignOut();
		
		AppClose();
		Thread.sleep(1000);
	
}

// After All the tests are conducted
	
	//@AfterTest
	@AfterClass
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
		//MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		//UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
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
			// //to add screen cast/video in extent report
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
	
	
	// ADMN046

		@Test(groups = { "Regression" }, description = "Verify the default privileges for Administrator")
		public void ADMN046() throws Exception {
			extentTest = extent.startTest("ADMN046-Verify the default privileges for Administrator");
            MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
            UserManagementPage = MainHubPage.ClickAdminTile_UMpage(); 
			UserManagementPage.ClickNewUser();
			UserManagementPage.select_UserType("System Administrator");
			Thread.sleep(1000);
			SoftAssert sa1 = new SoftAssert();
			sa1.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus Not Checked");
			sa1.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true, "FAIL: CreateAndEditEquipmentstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: CreateReportsstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true, "FAIL:CreatePassFailtemplatestatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL: DeleteAssetsstatus Not Checked");
			sa1.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL: DeleteSetupstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true, "FAIL:DeleteEquipmentstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true, "FAIL: DeleteStudyFilesReportsstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true, "FAIL:EditPassFailtemplatestatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true, "FAIL:CopyFilesReportsstatus Not Checked");
			sa1.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: Archivedatastatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL:ManualSyncstatus CheckBox Not Checked");
			sa1.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true, "FAIL:Deletepassfailtemplatestatus CheckBox Not Checked");
			UserManagementPage.ClkscrollBar_down();			
			sa1.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus CheckBox Not Checked");
			sa1.assertAll();
		}
		
		
	// ADMN047
		@Test(groups = { "Regression" }, description = "Verify the non- default privileges for Administrator")
		public void ADMN047() throws Exception {
			extentTest = extent.startTest("ADMN047-Verify the non- default privileges for Administrator");
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	        UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
			UserManagementPage.ClickNewUser();
			UserManagementPage.select_UserType("System Administrator");
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false, "FAIL: CheckBox Checked");
			sa.assertEquals(UserManagementPage.RunQualificationstatus(), false, "FAIL: CheckBox Checked");
			sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false, "FAIL: CheckBox Checked");
			sa.assertEquals(UserManagementPage.PrivCreateEditSetupstatus(), false, "FAIL: CheckBox Checked");
			sa.assertAll();

		}
	
	//ADMN048
	@Test(groups = { "Regression" }, description ="Verify if Administrator is able to access the default privilege-Create Equipment"
	  ) public void ADMN048() throws InterruptedException, ParseException,
	  IOException, AWTException { extentTest = extent.
	  startTest("ADMN048_Verify if Administrator is able to access the default privilege-Create Equipment");
	  SoftAssert sa = new SoftAssert(); 
	  MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	  EquipmentHubPage = MainHubPage.ClickEquipmentTile();
	  EquipmentPage = EquipmentHubPage.ClickAddButton();
	  EquipmentPage.EqipCreation_MandatoryFields("320","IRTD","4");
	  UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
	  String ExpAlrtMsg = "Data saved successfully"; 
	  String ActAlertMsg = EquipmentPage.AlertMsg(); 
	  sa.assertEquals(ActAlertMsg,ExpAlrtMsg,"FAIL: Equipment should be created successfully");
	  sa.assertAll();		 
} 
	

	//dependsOnMethods = "ADMN048",
	//ADMN048A
		@Test(groups = {"Regression" },description = "Verify if Administrator is able to access the default privilege Edit Equipment")
		public void ADMN048_1() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN048_1_Verify if Administrator is able to access the default privilege Edit Equipment");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
			IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("320");
			IRTDDetailspage.EditIRTDEquip("Editing");
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			String ExpAlrtMsg = "Equipment \"320\" Updated successfully.";
			String ActAlertMsg = EquipmentHubPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
			sa.assertAll();
			 }
 	
	//dependsOnMethods = "ADMN048",	
	// ADMN064
	@Test(groups = {"Regression" },description = "Verify if Administrator is able to access the default privilege-Delete Equipment")
	public void ADMN064() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN064_Verify if Administrator is able to access the default privilege-Delete Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("320");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		sa.assertEquals(IRTDDetailspage.IRTD_DeletePopupWindow(), true, "FAIL:IRTD_Delete Popup Window should present");	
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		System.out.println("user deleted");
	}
	
	//ADMN049
		@Test(groups = {"Regression" }, 
				description = "Verify if Administrator is able to access the default privilege-Archive")
		public void ADMN049() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN049-Verify if Administrator is able to access the default privilege-Archive");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickArchiveBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
	}
//ADMN050	
		@Test(groups = {"Regression" },description = "Verify if Administrator is able to access the default privilege-SyncIn")
		public void ADMN050() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN050_Verify if Administrator is able to access the default privilege-SyncIn");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickSyncInBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			sa.assertEquals(FileManagementPage.SyncInTextBoxVisible(), true, "FAIL:Sync In TextBox should be Visible");
	}
//ADMN050A
		@Test(groups = {"Regression" }, 
				description = "Verify if Administrator is able to access the default privilege-SyncOut")
		public void ADMN050A() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN050A_Verify if Administrator is able to access the default privilege-SyncOut");
			SoftAssert s5 = new SoftAssert();
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickSyncOutBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			s5.assertEquals(FileManagementPage.SyncOutTextBoxVisible(), true, "FAIL: Sync Out window not present");
	}
//ADMN052
	@Test(groups = {"Regression" }, description = "Verify if Administrator is able to access the default privilege-Access Audit Trail")
	    public void ADMN052() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN052-Verify if Administrator is able to access the default privilege-Access Audit Trail");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		AuditPage = MainHubPage.ClickAuditTitle();
		sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
		}
	
//ADMN053
	@Test(groups = {"Regression" },description = "Verify if Administrator is able to access the default privilege-Camera Access")
	   public void ADMN053() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN053_Verify if Administrator is able to access the default privilege-Camera Access");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Adm1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		sa.assertEquals(UserManagementPage.CameraOnTitleVisible(), true, "FAIL:Camera window should be Visible");	
	}	
	
//ADMN054	
	@Test(groups = {"Regression" },description = "Verify Administrator is unable to access the non-default privilege-Create Asset")
	    public void ADMN054() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN054-Verify Administrator is unable to access the non-default privilege-Create Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:User should be unable to access the non-default privilege-Create Asset");
		sa.assertAll();
	}
	
//ADMN054A
 
	@Test(groups = {"Regression" },description = "Verify Administrator is unable to access the non-default privilege-Edit Asset")
	public void ADMN054A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN054A_Verify Administrator is unable to access the non-default privilege Edit Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Ast2","6002","HeatBath","HYdd","Ind");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage=assetHubPage.click_assetTile("Ast2");
		assetDetailsPage.click_assetEditBtn_alrt();	
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
  }
  
//dependsOnMethods = "ADMN054A",	
//ADMN062
	@Test(groups = {"Regression" },description = "Verify if Administrator is able to access the default privilege-Delete Assets")
	public void ADMN062() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN062-Verify if Administrator is able to access the default privilege-Delete Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Ast2");	
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		sa.assertEquals(assetDetailsPage.Deletepopupwindow(), true, "FAIL:Delete pop up window should be Visible");
	}
		
	
//ADMN055
	@Test(groups = {"Regression" }, 
			description = "Verify Administrator is unable to access the non-default privilege-Create Setups")
	public void ADMN055() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN055-Verify Administrator is unable to access the non-default privilege-Create Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
        assetDetailsPage=assetHubPage.click_assetTile("Ast2");
		assetDetailsPage.click_NewStupCreateBtn_alert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: user should be unable to access the non-default privilege-Create Setups");
		sa.assertAll();
	}
  
//ADMN072
	@Test(groups = { "Regression" }, description = "ADMN072-Verify default privileges  for Supervisor User")
	public void ADMN072() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN072-Verify default privileges  for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Supervisor");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,
				"FAIL:CreaeteEditAssetPrivstatus should Checked");
		sa.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), true,
				"FAIL:CreaeteEditSetupstatus should Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL :CreateReportsstatus should Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,
				"FAIL: RunQualificationstatus should Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: RunCalibrationstatus should Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus should Checked");
		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should Checked");
		
		sa.assertAll();
	}
 
//ADMN073
	@Test(groups = { "Regression" }, description = "Verify the non- default privileges  for Supervisor User")
	public void ADMN073() throws Exception {
		extentTest = extent.startTest("ADMN073-Verify the non- default privileges  for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Supervisor");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		sa.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should not be Checked");
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL: CreatePassFailtemplatestatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), false,
				"FAIL:DeleteAssetsstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), false,
				"FAIL: DeleteSetupstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false,
				"FAIL:DeleteEquipmentstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false,
				"FAIL:DeleteStudyFilesReportsstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL: EditPassFailtemplatestatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false,
				"FAIL:CopyFilesReportsstatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), false,
				"FAIL:Archivedatastatus CheckBox checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL: Deletepassfailtemplatestatus checkbox should not be Checked");

	}
	
	
//ADMN074
	@Test(groups = {"Regression" }, 
			description = "Verify if Supervisor is able to access the default privilege-Create Assets")
	public void ADMN074() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN074-Verify if Supervisor is able to access the default privilege-Create Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage=assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Ayahoo2","4002","HeatBath","HYdd","Ind");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActMsg, ExpMsg, "FAIL:Data saved successfully alert msg should be displayed ");
		sa.assertAll();
}
 //ADMN074A
		@Test(groups = {"Regression" },description = "Verify if Supervisor is able to access the default privilege-Edit Assets")
	public void ADMN074A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN074A-Verify if Supervisor is able to access the default privilege-Edit Access");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage=assetHubPage.click_assetTile("Ayahoo2");
		assetCreationPage=assetDetailsPage.click_assetEditBtn();
			sa.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(),true, "FAIL:EditAssetscreen should be Displayed");
			sa.assertAll();
	}
	
 //dependsOnMethods = "ADMN074",
//ADMN078
		@Test(groups = {"Regression" }, 
				description = "Verify Supervisor is unable to access the non-default privilege-Delete Asset")
		public void ADMN078() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN078-Verify Supervisor is unable to access the non-default privilege-Delete Asset");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage=assetHubPage.click_assetTile("Ayahoo2");
			assetDetailsPage.DeleteAssert();
			UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset");
			sa.assertAll();
		}
		
//ADMN075
	@Test(groups = {"Regression" }, 
			description = "Verify if Supervisor is able to access the default privilege-Audit Trail")
	public void ADMN075() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN202_c-Verify if Supervisor is able to access the default privilege-Audit Trail");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		AuditPage = MainHubPage.ClickAuditTitle();
		sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
		}

	//dependsOnMethods = "ADMN074",
//	ADMN076
	@Test(groups = {"Regression" }, 
			description = "Verify if Supervisor is able to access the default privilege-Camera Access")
	public void ADMN076() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN076-Verify if Supervisor is able to access the default privilege-Camera Access");
		SoftAssert s14 = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.ClickAssetTile();
        assetDetailsPage=assetHubPage.click_assetTile("Ayahoo2");
		assetCreationPage=assetDetailsPage.click_assetEditBtn();
        assetCreationPage.click_CameraIcon();
        s14.assertEquals(assetCreationPage.CameraOnTitleVisible(), true, "FAIL:CameraOn window should be Visible");
			
	}
	
//ADMN077
	@Test(groups = {"Regression" }, 
			description = "Verify Supervisor is unable to access the non-default privilege-Create Equipment")
	public void ADMN077() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN077:Verify Supervisor is unable to access the non-default privilege-Create Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
	    EquipmentHubPage.Alert_ClickAddBtn();
	    String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor should not able to Create Equipment");
		sa.assertAll();
}
	
//ADMN077A
	@Test(groups = {"Regression" }, 
			description = "Verify Supervisor is unable to access the non-default privilege-Edit Equipment")
	public void ADMN077A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN077-Verify Supervisor is unable to access the non-default privilege-Create Equipment");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("200B","IRTD","20");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("200B");
		IRTDDetailspage.EditIRTDEquip("Editing");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
		}
	
	//dependsOnMethods = "ADMN077A",
//ADMN080
	@Test(groups = {"Regression" },description = "Verify Supervisor is unable to access the non-default privilege-Delete Equipments")
	public void ADMN080() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN080:Verify Supervisor is unable to access the non-default privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("320");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = IRTDDetailspage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access Delete Equipments");
		sa.assertAll();
		}

	
//ADMN083
	@Test(groups = {"Regression" }, 
			description = "Verify Supervisor is unable to access the non-default privilege-Archive data")
	public void ADMN083() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN083-Verify Supervisor is unable to access the non-default privilege-Archive data");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor should be unable to access Archive data");
		sa.assertAll();
	}
	
	//ADMN084
		@Test(groups = {"Regression" },description = "Verify if Supervisor user is unable to access Admin")
		public void ADMN084() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN084-Verify if Supervisor is unable to access the non-default privilege-Admin");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			MainHubPage.ClickAdminTile();
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = MainHubPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor should be unable to access the non-default privilege-Admin");
			sa.assertAll();
			}
		
 //dependsOnMethods = "ADMN074",
 //ADMN086
	@Test(groups = {"Regression" },description = "Verify if Supervisor is able to access the default privilege-Create Setups")
	     public void ADMN086() throws InterruptedException, ParseException, IOException, AWTException {
		 extentTest = extent.startTest("ADMN086-_Verify if Supervisor is able to access the default privilege-Create Setups");
		 SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage=assetHubPage.click_assetTile("Ayahoo2");
			Setup_defineSetupPage= assetDetailsPage.click_NewStupCreateBtn();
			sa.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true, "FAIL: set up page should be displayed");	
			
	}
	
//ADMN094
	@Test(groups = {"Regression" },description = "Verify if Supervisor is able to access the default privilege-SyncIn")
	    public void ADMN094() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN094_Verify if Supervisor is able to access the default privilege-SyncIn");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		sa.assertEquals(FileManagementPage.SyncInTextBoxVisible(), true, "FAIL: SyncIn TextBox should be Visible ");
}
	
//ADMN094A
	@Test(groups = {"Regression" },description = "Verify if Supervisor is able to access the default privilege-SyncOut")
	public void ADMN094A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN094A_Verify if Supervisor is able to access the default privilege-SyncOut");
		SoftAssert s22 = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncOutBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s22.assertEquals(FileManagementPage.SyncOutTextBoxVisible(), true, "FAIL: SyncOut TextBox should be Visible ");
} 


//ADMN097
	@Test(groups = { "Regression" }, description = "Verify default privileges  for Operator User")
	public void ADMN097() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN097-Verify default privileges  for Operator User");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.select_UserType("Operator");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,"FAIL: CreaeteEditAssetPrivstatus should be Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: CreateReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,"FAIL: RunQualificationstatus should be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true,"FAIL: RunCalibrationstatus should be Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL: CameraAccessstatus should be Checked");
		
		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should be Checked");

	}
	
//ADMN098
	@Test(groups = { "Regression" }, description = "Verify non-default privileges  for Operator User")
	public void ADMN098() throws Exception {
		extentTest = extent.startTest("ADMN098-Verify non-default privileges  for Operator User");

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		  UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		  UserManagementPage.ClickNewUser();
		  UserManagementPage.select_UserType("Operator");
		  SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		  sa.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false, "FAIL:CreateAndEditEquipmentstatus CheckBox should not be Checked");
		  sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false, "FAIL: CreatePassFailtemplatestatus CheckBox should not be Checked");
		  sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), false, "FAIL:DeleteAssetsstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.DeleteSetupstatus(), false,  "FAIL:DeleteSetupstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false, "FAIL:DeleteEquipmentstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false, "FAIL:DeleteStudyFilesReportsstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false, "FAIL:EditPassFailtemplatestatus should not be Checked");
		  sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false, "FAIL: CopyFilesReportsstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.Archivedatastatus(), false, "FAIL: Archivedatastatus should not be Checked");
		  sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should not be Checked");
		  sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,  "FAIL: Deletepassfailtemplatestatus should not be Checked");
		}

//ADMN099
	@Test(groups = {"Regression" }, description = "Verify if Operator is able to access the default privilege-Create  Assets")
	public void ADMN099() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN099-Verify if Operator is able to access the default privilege-Create  Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("oppo2", "1012", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		sa.assertEquals(ActMsg, ExpMsg, "FAIL: Alert message Not Matched");
		sa.assertAll();
	}
//dependsOnMethods = "ADMN099",
//ADMN100
	@Test( groups = {"Regression" }, description = "Verify if Operator is able to access the default privilege-Edit Assets")
	public void ADMN100() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN100-Verify if Operator is able to access the default privilege-Edit  Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("oppo2");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		s.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true, "FAIL:User should be able to access the default privilege-Edit Assets");
		s.assertAll();
		}
		
	//ADMN100 To ADMN104 should test manually(Report TCs)
	//dependsOnMethods = "ADMN099",
//ADMN105
		@Test( groups = {"Regression" },description = "Verify if Operator is able to access the default privilege-Camera access")
		public void ADMN105() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN105-Verify if Operator is able to access the default privilege-Camera access");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("oppo2");
			assetCreationPage=assetDetailsPage.click_assetEditBtn();
	        assetCreationPage.click_CameraIcon();
	        s.assertEquals(assetCreationPage.CameraOnTitleVisible(), true, "FAIL: Operator should be able to access Camera access");
		} 
		
//dependsOnMethods = "ADMN099", 		
//ADMN106
	@Test(groups = {"Regression" }, description = "Verify if Operator is unable to access the non-default privilege-Delete Assets")
	public void ADMN106() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN106-Verify if Operator is unable to access the non-default privilege-Delete Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("oppo2");
		assetDetailsPage.DeleteAssert();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,"FAIL: User is unable to access Delete Assets");
		s.assertAll();
	}

	//dependsOnMethods = "ADMN099",
 //ADMN116A
	
		@Test(groups = {
				"Regression" }, description = "Verify  Operator is unable to access the non-default privilege-Create Setups")
		public void ADMN116A() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN116A- Verify Operator is unable to access the non-default privilege-Create Setups");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage = assetHubPage.click_assetTile("oppo2");
			assetDetailsPage.click_NewStupCreateBtn_alert();
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			s.assertEquals(ActAlertMsg, ExpAlrtMsg,"FAIL: User Is able to access the non-default privilege-Delete Assets");
			s.assertAll();
		}
	
//ADMN107	
	@Test(groups = {"Regression" }, description = "Verify if Operator is  unable to access the non-default privilege-Archive data")
	public void ADMN107() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN107-Verify if Operator is  unable to access the non-default privilege-Archive data");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should be unable to access Archive data");
		s.assertAll();
	}
	
//ADMN108
	
	@Test(groups = {"Regression" },description = "Verify if Operator is unable to access the non-default privilege-Admin")
	public void ADMN108() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN108-Verify if Operator is unable to access the non-default privilege-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		MainHubPage.ClickAdminTile();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = MainHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should not be able to access Admin");
		sa.assertAll();
	} 
	
//ADMN109
	
	@Test(groups = {"Regression" },description = "Verify if Operator is able to access the non-default privilege-Manual syncIn")
		    public void ADMN109() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN109-Verify if Operator is able to access the non-default privilege-Manual syncIn");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickSyncInBtn();
			UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
			String ExpAlrtMsgForSyncIn = "User do not have permission to perform this operation";
			String ActAlertMsgForSyncIn = FileManagementPage.AlertMsg();
			s.assertEquals(ExpAlrtMsgForSyncIn, ActAlertMsgForSyncIn,"FAIL: Operator is able to access the non-default privilege-Manual sync In");
	}
	
//ADMN109A
		@Test(groups = {"Regression" },description = "Verify if Operator is able to access the non-default privilege-Manual syncOut")
	    public void ADMN109A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN109A-Verify if Operator is able to access the non-default privilege-Manual syncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsgForSyncOut = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncOut = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncOut, ActAlertMsgForSyncOut,"FAIL: Operator is able to access the non-default privilege-Manual syncOut");
		}
		
//ADMN115
	@Test(groups = {"Regression" }, description = "Verify if Operator is able to access the default privilege-Access Audit trail")
	public void ADMN115() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN115-Verify if Operator is able to access the default privilege-Access Audit trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		AuditPage = MainHubPage.ClickAuditTitle();
		s.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL:Operator should be able to access Audit trail");
	}
	
//ADMN117
	@Test(groups = {"Regression" },description = "Verify if Operator is able to access the non-default privilege-Create- Equipments")
	public void ADMN117() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN117-Verify if Operator is able to access the non-default privilege-Create- Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
	    EquipmentHubPage.Alert_ClickAddBtn();
	    String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User is able to create Equipment ");
		s.assertAll();
}
 
//ADMN117A
	@Test(groups = {"Regression" }, 
			description = "Verify if Operator is able to access the non-default privilege-Edit Equipments")
	public void ADMN117A() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN117A-Verify if Operator is able to access the non-default privilege-Edit Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("450","IRTD","21");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		MainHubPage = EquipmentHubPage.ClickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("450");
		IRTDDetailspage.EditIRTDEquip("Editing");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:");
		s.assertAll();
	}
	
//ADMN118
	//dependsOnMethods = "ADMN117A",
	@Test(groups = {"Regression" },description = "Verify if Operator is unable to access the non-default privilege-Delete Equipments")
	public void ADMN118() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN118-Verify if Operator is unable to access the non-default privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("320");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = IRTDDetailspage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should be unable to access Delete Equipments");
		sa.assertAll();
		}

	
	}