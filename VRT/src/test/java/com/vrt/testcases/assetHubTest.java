/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeClass;
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


public class assetHubTest extends BaseClass {

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
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/assetHubTest.html",true);
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
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
	}

	//TearDown of the App
	@AfterMethod(alwaysRun=true)
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
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report
		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report		
		
		driver.quit();
		driver = null;
	}

	
	//ASSTHB001-Verify if selecting the Assets tile from the main hub page , 
	//user is navigated to the Asset Details screen
	@Test(groups = {"Sanity", "Regression"}, description = "ASSTHB001-Verify if selecting "
			+ "the Assets tile from the main hub page , user is navigated to the Asset Details screen")
	public void ASSTHB001() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB001-Verify if selecting the Assets tile from "
				+ "the main hub page , user is navigated to the Asset Details screen");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST001 -Incorrect Asset Page title or landed into incorrect Page");
		sa.assertAll();	
	}	
	
	
	// ASSTHB002-Verify if with  fresh installation, no assets should be displayed
	@Test(groups = {"Regression"}, description = "ASSTHB002-Verify if with  "
			+ "fresh installation, no assets should be displayed")
	public void ASSTHB002() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB002-Verify if with  "
				+ "fresh installation, no assets should be displayed");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetHubPage.assetcount(), "0",
				"FAIL: TC-ASST002 -Already assets present in the system which "
				+ "suggests its not an Fresh Installation may have been upgraded");
		sa.assertAll();
	}
	

	// ASSTHB003-Verify if Predefined Sort Options namely -Type, 
	//Manufacturer and Location are present
	@Test(groups = {"Regression"}, description = "ASSTHB003-Verify if Predefined Sort "
			+ "Options namely -Type, Manufacturer and Location are present")
	public void ASSTHB003() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB003-Verify if Predefined Sort Options "
				+ "namely -Type, Manufacturer and Location are present");
		SoftAssert sa3 = new SoftAssert();

		sa3.assertEquals(assetHubPage.typeFilterBtnstate(), true, "FAIL: TC-ASST004 -Type FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.manufacturerFilterBtnstate(), true, "FAIL: TC-ASST004 -Manufacturer FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.locationFilterBtnstate(), true, "FAIL: TC-ASST004 -Location FIlter is absent from the Assethub Page");
		sa3.assertAll();
	}
	
	
	//ASSTHB004-ASSTHB004-Verify  if Add New and Search -magnifier Icons
	//are present at the right top corner of the assets page
	@Test(groups = {"Regression"}, description = "ASSTHB004-Verify  if Add New and Search -magnifier Icons"
	 +"are present at the right top corner of the assets page")
	public void ASSTHB004() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB004-Verify  if Add New and Search -magnifier Icons"
	 +"are present at the right top corner of the assets page");
		SoftAssert sa4 = new SoftAssert();

		sa4.assertEquals(assetHubPage.serachAstBtn_state(), true, "FAIL: TC-ASST005 -Asset Search button missing");
		sa4.assertEquals(assetHubPage.addAst(), true, "FAIL: TC-ASST005 -Asset Add button missing");
		
		sa4.assertAll();
	}
		
	
	//ASSTHB005- Verify  if clicking on New icon opens a New Asset creation page
	@Test(groups = {"Regression"}, description = "ASSTHB005-Verify  if clicking "
			+ "on New icon opens a New Asset creation page")
	public void ASSTHB005() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB005-Verify  if clicking on New icon "
				+ "opens a New Asset creation page");
		SoftAssert sa5 = new SoftAssert();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();

		sa5.assertEquals(assetCreationPage.newAssetCreatePagetitle(), true, "FAIL: TC-ASST006 -Landed "
				+ "to Wrong page instead of New Asset creation page");				
		sa5.assertAll();
	}
	
	
	//ASSTHB006a-Verify the search functionality in Asset hub page
	@Test(groups = {"Regression"},  description = "ASSTHB006a-Verify the seacrh fucntionality "
			+ "in Asset hub page", 
			dataProvider="ASSTHB006a", dataProviderClass=assetCreationUtility.class)
	public void ASSTHB006a(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDT, String Frequency, String FrequencyInterval, 
			String Description) throws InterruptedException {
		extentTest = extent.startTest("ASSTHB006a-Verify the seacrh fucntionality in Asset hub page");
		SoftAssert sa = new SoftAssert();
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDT, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Expected search Asset elements(Asset Type, Asset ID, Asset Name) info
		String[] expectedAssetInfo = {Type,ID,Name};
		//System.out.println("exp asst info"+Arrays.toString(expectedAssetInfo));

		assetHubPage.click_serachAstBtn();
		if (assetHubPage.searchAstTxtfiled_state()) {
			assetHubPage.enter_serachAsttxt(Name);
		}
		
		assetHubPage.click_serachAstBtn();
		assetHubPage.click_serachAstBtn();		

		//Verify if only One asset is displayed based on Search criteria
		sa.assertEquals(assetHubPage.asset_Count(), 1, "FAIL: Serach Asset not found");
		
		String[] ActualAssetinfo = assetHubPage.assetTile(Name);
		//System.out.println("Actual asst info:"+Arrays.toString(ActualAssetinfo));	

		//Verify if the expected Asset info is same as the searched Asset info
		sa.assertEquals(ActualAssetinfo, expectedAssetInfo,
				"FAIL: ASSTHB006a- Mismatch in the searched Asset & display asset details");
		sa.assertAll();
	}

		
	//ASSTHB006b-Verify the search functionality in Asset hub page
	@Test(dataProvider="ASSTHB006b", dataProviderClass=assetCreationUtility.class, groups = {"Regression"},  
			description = "ASSTHB006b-Verify if No record found"
			+ " message displayed if incorrect Asset Name entered in the Search"
			+ "Functionality of Asset hub page")
	public void ASSTHB006b(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDT, String Frequency, String FrequencyInterval, 
			String Description) throws InterruptedException {
		extentTest = extent.startTest("ASSTHB006b-Verify if No record found" 
				+" message displayed if incorrect Asset Name entered in the Search"  
				+"Functionality of Asset hub page");
		SoftAssert sa = new SoftAssert();
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDT, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Expected search Asset elements(Asset Type, Asset ID, Asset Name) info
		//String[] expectedAssetInfo = {Type,ID,Name};
		//System.out.println("exp asst info"+Arrays.toString(expectedAssetInfo));

		assetHubPage.click_serachAstBtn();
		if (assetHubPage.searchAstTxtfiled_state()) {
			assetHubPage.enter_serachAsttxt("1"+Name);
		}		
		//System.out.println(assetHubPage.asset_Count());		
		
		//Verify if only One asset is displayed based on Search criteria
		sa.assertEquals(assetHubPage.asset_Count(), 0, "FAIL:Some random Asset information found");
		sa.assertEquals(assetHubPage.NoRecordFoundMsg(), "No record found", 
				"Fail: Some random Asset information found");
		sa.assertAll();
	}
	
	
	//ASSTHB007-Verify if clicking on Back Button at the left top to return to main Hub page
	@Test(groups = {"Regression"}, description = "ASSTHB007-Verify if clicking on Back "
			+ "Button at the left top to return to main Hub page")
	public void ASSTHB007() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB007-Verify if clicking on Back "
				+ "Button at the left top to return to main Hub page");
		SoftAssert sa = new SoftAssert();
		
		MainHubPage=assetHubPage.ClickBackBtn();

		sa.assertEquals(MainHubPage.mainPageTitle(), true, "FAIL: TC-ASSTHB007 -Landed "
				+ "to Wrong page instead of Main Hub page");				
		sa.assertAll();
	}
	
	
	//ASSTHB008-Verify if click on the Type filter user is able to filter all the assets correctly by Asset Type
	@Test(dataProvider="ASSTHB008", dataProviderClass=assetCreationUtility.class, groups = {"Regression"}, 
			description = "ASSTHB008-Verify if click on the Type filter user is able to filter all the assets "
					+ "correctly by Asset Type")
	public void ASSTHB008(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDT, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException {
		extentTest = extent.startTest("ASSTHB008-Verify if click on the Type filter user is able to filter all "
				+ "the assets correctly by Asset Type");
		SoftAssert sa8 = new SoftAssert();
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				VldDT, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Check for the Asset Filter method
		boolean state = assetHubPage.assetList_TypeFilter();
		System.out.println(state);

		sa8.assertEquals(state, true, "FAIL: TC-ASSTHB008 -Type filter is not working in the Asset Hub page");				
		sa8.assertAll();
	}
	
	
	//ASSTHB009-Verify if click on the Manufacturer Filter user is able to filter all the assets correctly by Asset Manufacturer
	@Test(groups = { "Regression" }, description = "ASSTHB009-Verify if "
			+ "click on the Manufacturer Filter user is able to filter all the assets correctly by Asset Manufacturer")
	public void ASSTHB009()
			throws InterruptedException, IOException {
		extentTest = extent.startTest("ASSTHB009-Verify if click on the Manufacturer Filter user is able to filter "
				+ "all the assets correctly by Asset Manufacturer");
		SoftAssert sa9 = new SoftAssert();

		// Check for the Asset Filter method
		boolean state = assetHubPage.assetList_ManufacturerFilter();
		System.out.println(state);

		sa9.assertEquals(state, true, "FAIL: TC-ASSTHB009 -Manufacturer filter is not working in the Asset Hub page");
		sa9.assertAll();
	}
	
	
	//ASSTHB010-Verify if click on the Location type , User is able to filter all the assets correctly by Asset Location type
	@Test(groups = { "Regression" }, description = "ASSTHB010-Verify if click on the Location type , User is able to "
			+ "filter all the assets correctly by Asset Location type")
	public void ASSTHB010()
			throws InterruptedException, IOException {
		extentTest = extent.startTest("ASSTHB010-Verify if click on the Location type , User is able to filter all "
				+ "the assets correctly by Asset Location type");
		SoftAssert sa10 = new SoftAssert();

		// Check for the Asset Filter method
		boolean state = assetHubPage.assetList_LocationFilter();
		//System.out.println(state);

		sa10.assertEquals(state, true, "FAIL: TC-ASSTHB010 -Location filter is not working in the Asset Hub page");
		sa10.assertAll();
	}
	
	//ASSTHB011-Verify if the help section in the Asset hub page is displayed by clicking Help icon
	@Test(groups = {"Sanity", "Regression"}, description = "ASSTHB011-Verify if the help section in the "
			+ "Asset hub page is displayed by clicking Help icon")
	public void ASSTHB011() throws InterruptedException {
		extentTest = extent.startTest("ASSTHB011-Verify if the help section in the "
			+ "Asset hub page is displayed by clicking Help icon");
		SoftAssert sa7 = new SoftAssert();
		
		assetHubPage.rightclickonAssetPageTitle();
		assetHubPage.clickHelpIcon();

		sa7.assertEquals(assetHubPage.is_assetHubHelpWindow_Displayed(), true, "FAIL: TC-ASSTHB011 -AssetHub Help"
				+ "window did not appear or wrong Help window displayed");				
		sa7.assertAll();
	}
	

}
