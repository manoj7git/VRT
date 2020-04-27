/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.Assert;
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
import com.vrt.pages.Setup_CalculationsPage;
import com.vrt.pages.Setup_GroupSensorsPage;
import com.vrt.pages.Setup_QualParamPage;
import com.vrt.pages.Setup_ReviewPage;
import com.vrt.pages.Setup_SensorConfigPage;
import com.vrt.pages.Setup_defineSetupPage;
import com.vrt.pages.SyncInAssetListPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.pages.FileManagementPage;
import com.vrt.pages.SyncInPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;
import com.vrt.utility.setupCreationUtility;


public class HitNTrialTests extends BaseClass {
	
	public HitNTrialTests() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	TestUtilities tu = new TestUtilities();
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


	@BeforeClass
	private void testsetup() throws IOException {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport"+"_assetCreationTest"+".html",true);
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");
		extent.addSystemInfo("BS Version", prop.getProperty("BS_Version"));
		extent.addSystemInfo("Lgr Version", prop.getProperty("Lgr_Version"));
		extent.addSystemInfo("ScriptVersion", prop.getProperty("ScriptVersion"));
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		
		/*System.out.println(prop.getProperty("BS_Version"));
		System.out.println(prop.getProperty("Lgr_Version"));
		System.out.println(prop.getProperty("ScriptVersion"));
		System.out.println(prop.getProperty("User_Name1"));*/

		//Rename the User file (NgvUsers.uxx) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");		
		//Rename the cache Asset file (Asset.txt) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");
	}
	
	
	//After All the tests are conducted
	@AfterClass
	//@AfterTest
	public void endReport(){
		//System.out.println("Check end");
		extent.flush();
		extent.close();
	}
	
	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		
	}
	
	/*@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");			
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.waitforAssetHubPageLoad();
	}*/

	
	/*// TearDown of the App
	@AfterMethod
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
			String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report

		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}*/

	
	/*//Create 25 assets
	@Test(dataProvider="testAsset", dataProviderClass=assetCreationUtility.class, groups = {"Sanity", "Regression"}, 
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
		
	}*/


	/*
	@Test (description="Check for File renaming")
	public void renameFile() throws IOException {

		// create a new file
		String filepath ="C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData";
		
		File file = new File(filepath + "/" + "NgvUsers.uux");
		System.out.println(file.getName());
		System.out.println(file.exists());
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println("No User DB File present");
		} else {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			File backupFile = new File(filepath + "/" + timestamp + "_NgvUsers.uux");
			file.renameTo(backupFile);
		}	
				
	}
	*/
	
/*	@Test (description="enter filepath to the SyncIn Browse field")
	public void syncin() throws Exception {

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		//Thread.sleep(1000);
		LoginPage = new LoginPage();
		//extent.addSystemInfo("VRT Version", LoginPage.get_SWVersion_About_Text());
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));			
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));		
		//SyncInPage.enter_Filepath("syncin");
		SyncInPage.click_FltrBtn();
		SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
		SyncInAssetListPage.click_SelectAllBtn();
		SyncInAssetListPage.click_OkBtn();
		SyncInAssetListPage.click_AlrtYesBtn();
		SyncInAssetListPage.click_Success_alrtMeg_OkBtn();

	  }*/
	
	//ASST02-Verify the valid inputs accepted in Asset name field
	/*@Test(dataProvider="ASST02", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="ASST02-Verify the valid inputs accepted in Asset name field")
	public void ASST02(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST02-Verify the valid inputs accepted in Asset name field");
		SoftAssert sa3 = new SoftAssert();
		
		String Name = (String) dataProvider[0]; 		
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4]; 
		
		System.out.println(Name);
		System.out.println(ID);
		System.out.println(Type);
		System.out.println(Manufacturer);
		System.out.println(Location);

		
		//assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa3.assertEquals(assetCreationPage.UserLoginPopupVisible(), true, 
				"Fail: Asset Name not accepting the Valid characters");		
		sa3.assertAll();
	}*/		
	
	// Create Multiple Setups
	@Test(groups = {
			"Regression" }, dataProvider = "SetupCreation", dataProviderClass = setupCreationUtility.class, 
					description = "Create Setups")

	public void multiplr_SetupsCreation(String SetupName, String SensorCount, String SOP, String LoadDescription, 
			String Comments, String TempCount,	String PrsrCount, String TCSensorLabel, String PrSensorLabel, 
			String BaseTemp, String AlethCondition, String Qstart, String TOD, String Qstop, String Hrs,String Mnts,String Secs,
			String SR,	String TR, String RF_Transmitt, String count) throws InterruptedException, IOException, AWTException, ParseException {
		extentTest = extent.startTest(
				"Multiple Setup Creation");

		SoftAssert sa = new SoftAssert();
		//Define Setup 
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetupName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorCount);
		/*defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LoadDescription);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Comments);*/
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
			/*Setup_GroupSensorsPage.click_GrpWiring_Btn();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(0);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("temp-loggers.jpg");*/
			Thread.sleep(1000);
		} else {
			Setup_GroupSensorsPage.click_DfltGrp_Btn();	
			/*Setup_GroupSensorsPage.click_GrpWiring_Btn();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(0);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("temp-loggers.jpg");
			Setup_GroupSensorsPage.click_Dflt_PrGrp();
			Setup_GroupSensorsPage.Click_WiringImgHoldr(1);
			Setup_GroupSensorsPage.Click_ImgHoldr1_EditBtn();
			Setup_GroupSensorsPage.select_Img("Pressure.jpg");*/
			Thread.sleep(1000);
		}
		
		Setup_CalculationsPage = Setup_GroupSensorsPage.Click_NxtBtn();
		
		//Setup_Claculations page
		/*float bTemp = Float.parseFloat(BaseTemp);
		if (bTemp >= 0.0) {
			Setup_CalculationsPage.enter_bTemp(BaseTemp);
		}
		Setup_CalculationsPage.select_AlethCondition(AlethCondition);*/
		
		if (PrNo>0) {
			/*Setup_CalculationsPage.click_SatTP_btn();
			Setup_CalculationsPage.select_1stTempSensor();
			Setup_CalculationsPage.select_1stPrSensor();*/			
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
		/*Setup_QualParamPage.click_Qstart_DrpDwnBox();
		Setup_QualParamPage.click_Qstop_DrpDwnBox();
		Setup_QualParamPage.click_SR_DrpDwnBox();
		Setup_QualParamPage.click_RFT_DrpDwnBox();*/

		Setup_QualParamPage.select_QualStart_condition(Qstart, Yr,Mn,Dt,Hr,Mnt,Se);//Select TOD Qual Start parameter yr/Mnth/Dt/Hr/Mnt/Sec,
		Setup_QualParamPage.select_QualStop_condition(Qstop, Hrs, Mnts, Secs);
		Setup_QualParamPage.select_SR(SR);
		Setup_QualParamPage.select_TR(SR, TR);
		Setup_QualParamPage.select_RFT(RF_Transmitt);
		
		Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();

		//Setup_Review page
		Setup_ReviewPage.click_Save_Btn(Qstart, "Yes");
		Setup_ReviewPage.click_back_Btn();
		System.out.println("Asset COunt: "+count);
	}


}
