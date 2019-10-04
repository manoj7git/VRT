/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;


public class assetCreationTest extends BaseClass{
	
	//Refer TestUtilities Class for Data provider methods
	//Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p	
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	//Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	
	
	//Ensure the User has got rights to create Assets
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", "1", "Welcome1@AM", "Welcome1@AM", "FullAdmin",
				"12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login("1", "Welcome1@AM");
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");

		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();

		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup("1", "Welcome1@AM");
		MainHubPage = UserManagementPage.ClickBackButn();

		AppClose();
		Thread.sleep(1000);

	}
	
	
	//After All the tests are conducted
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage= new LoginPage();
		MainHubPage=LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}

	
	// TearDown of the App
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
	}

	
	
	 //ASST100
	@Test(groups = {"Sanity"}, 
			description="Verify if the asset name text box accepts input only up to 25 characters")
	public void ASST100() throws InterruptedException {
		extentTest = extent.startTest("ASST100-Verify if the asset name text box accepts "
				+ "input only up to 25 characters");
		SoftAssert sa1 = new SoftAssert();
		String expectedtxt = "12345678901234567890123456";  //26 Char input
		//System.out.println("count of Asset name text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetName();
		//System.out.println("count of Assent name text entered: "+actualtextentered.length());
		
		sa1.assertEquals(actualtextentered.length(), 25, "FAIL: Asset name accepts more than 25 characters");
		sa1.assertAll();
	}
	

	
	//ASST101a
	@Test(groups = {"Sanity", "Regression"},dataProvider="getAstNameInvalidTestData", dataProviderClass=assetCreationUtility.class, 
			description="Verify if the Asset Name do not accept In-Valid Data parameters")
	public void ASST101a(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST101a-Verify if the Asset Name do not accept In-Valid Data parameters");
		SoftAssert sa2 = new SoftAssert();
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4];
		String ExpAlrtMsg = (String) dataProvider[5]; 
		String UserName = (String) dataProvider[6]; 
		String Password = (String) dataProvider[7];
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa2.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa2.assertAll();
	}
	

	
	//ASST101b
	@Test(dataProvider="getAstNameValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the Asset Name accepts Valid Data parameters")
	public void ASST101b(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST101b-Verify if the Asset Name accepts Valid Data parameters");
		SoftAssert sa3 = new SoftAssert();
		
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4]; 
		String UserName = (String) dataProvider[5]; 
		String Password = (String) dataProvider[6];
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa3.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa3.assertAll();
	}
	
	
	//ASST102
	@Test(groups = {"Sanity"}, 
			description="Verify if the equipment id text box should accepts input only up to 15 characters")
	public void ASST102() throws InterruptedException {
		extentTest = extent.startTest("ASST102-Verify if the equipment id text box should accepts "
				+ "input only up to 15 characters");
		SoftAssert sa4 = new SoftAssert();
		String expectedtxt = "123456789012345a";  //16 Char input
		System.out.println("count of Equipment ID text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetID(expectedtxt);
		String actualtextentered = assetCreationPage.getEqpID();
		System.out.println("count of Equipment ID text entered: "+actualtextentered.length());
		
		sa4.assertEquals(actualtextentered.length(), 15, "FAIL: Equipment ID accepts more than 100 characters");
		sa4.assertAll();
	}
	
	
	//ASST103a
	@Test(groups = {"Sanity"}, dataProvider="getEqpIDinValidTestData", dataProviderClass=assetCreationUtility.class, 
			description="Verify if the Equipment field do not accept In-Valid Data parameters")
	public void ASST103a(Object ...dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ASST103a-Verify if the Equipment field do not accept In-Valid Data parameters");
		SoftAssert sa4 = new SoftAssert();
		
		String Name = (String) dataProvider[0]; 
		String ID = (String) dataProvider[1];
		String Type = (String) dataProvider[2]; 
		String Manufacturer = (String) dataProvider[3];
		String Location = (String) dataProvider[4]; 
		String ExpAlrtMsg = (String) dataProvider[5]; 
		String UserName = (String) dataProvider[6]; 
		String Password = (String) dataProvider[7];
		
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa4.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa4.assertAll();
	}
	
	
	//ASST103b
	@Test(dataProvider="getEqpIDValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the Equipment field accepts Valid Data parameters")
	public void ASST103b(String Name, String ID, String Type, String Manufacturer, 
			String Location, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST103b-Verify if the Equipment field accepts Valid Data parameters");
		SoftAssert sa5 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa5.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa5.assertAll();
	}
	
	
	//ASST104 - Verify if the asset type combo box by default displays the option as Select
	@Test(groups = {"Sanity", "Regression"}, 
			description="Verify if the asset type combo box by default displays the option as Select")
	public void ASST104() {
		extentTest = extent.startTest("ASST104-Verify if the asset type combo box by default displays the option as Select");
		SoftAssert sa6 = new SoftAssert();
		
		sa6.assertEquals(assetCreationPage.getAssetTypetext(), "Select", 
				"FAIL: Select is not the default Asset Type selected data");
		sa6.assertAll();
	}
	
	
	//ASST105
	@Test(groups = {"Sanity"}, 
			description="Verify if the user can type in the desired asset type name")
	public void ASST105() {
		extentTest = extent.startTest("ASST105-Verify if the user can type in the desired asset type name");
		SoftAssert sa7 = new SoftAssert();
		assetCreationPage.enterAssetType("Oven");
		assetCreationPage.enterManufacturerName("Hybd");
		assetCreationPage.getAssetTypetext();
		
		sa7.assertEquals(assetCreationPage.getAssetTypetext(), "Oven", "FAIL: Unable to enter "
				+ "value in the Asset Type field");
		sa7.assertAll();
	}
	
	
	//ASST106 
	@Test(groups = {"Sanity"}, 
			description="Verify if the asset type combo box allows only 50 character input")
	public void ASST106() {
		extentTest = extent.startTest("ASST106-Verify if the asset type combo box allows only 50 character input");
		SoftAssert sa8 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetType(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetTypetext();
		System.out.println("count of Asset Type text entered: "+actualtextentered.length());
		
		sa8.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 100 characters");
		sa8.assertAll();
	}
	

	//ASST107a 
	@Test(dataProvider="getAstTypeInValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity","Regression"},
			description="Verify if the Asset Type field do not accept In-Valid Data parameters")
	public void ASST107a(String Name, String ID, String Type, String Manufacturer, 
			String Location, String ExpAlrtMsg, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST107a-Verify if the Asset Type field do not accept In-Valid Data parameters");
		SoftAssert sa9 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa9.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa9.assertAll();
	}
	
	
	//ASST107b 
	@Test(dataProvider="getAstTypeValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the Asset Type field accepts Valid Data parameters")
	public void ASST107b(String Name, String ID, String Type, String Manufacturer, 
			String Location, String UserName, String Password) throws InterruptedException {
		extentTest = extent.startTest("ASST107b-Verify if the Asset Type field accepts Valid Data parameters");
		SoftAssert sa10 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);	
		
		sa10.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa10.assertAll();
	}
	
	
	//ASST108 
	@Test(groups = {"Sanity", "Regression"},
			description="Verify if the Asset types are sorted in alphabetic order")
	public void ASST108() throws InterruptedException {
		extentTest = extent.startTest("ASST108-Verify if the Asset types are sorted in alphabetic order");
		SoftAssert sa11 = new SoftAssert();
		
		assetCreationPage.assetCreationWithType("3", "3", "Freezer", "DAS", "AGR");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithType("4", "4", "Bath", "AAS", "MOM");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("5", "5", "ColdChamber", "BAS", "DEL");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("6", "6", "DeepFreezer", "CAS", "BBS");
		assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		

		
		String[] OriginalList = assetCreationPage.getAssetTypelist();
		//System.out.println(Arrays.toString(OriginalList));
		String[] SortedNewList = assetCreationPage.getAssetTypeSortedlist();
		//System.out.println(Arrays.toString(SortedNewList));

		sa11.assertEquals(OriginalList, SortedNewList);
		sa11.assertAll();
	}
	
	
	//ASST109 
	@Test(groups = {"Sanity", "Regression"}, 
			description="Verify if the values in the Asset type combo box are selectable by touching the screen")
	public void ASST109() {
		extentTest = extent.startTest("ASST109-Verify if the values in the Asset type combo box"
				+ " are selectable by touching the screen");
		SoftAssert sa12 = new SoftAssert();
		
		assetCreationPage.SelectAssetType("DeepFreezer");
		String ActualAssetTypeSelected = assetCreationPage.getAssetTypetext();
		
		sa12.assertEquals(ActualAssetTypeSelected, "DeepFreezer");
		sa12.assertAll();
	}
	
	
	//ASST110 - 
	@Test(groups = {"Sanity", "Regression"}, 
			description="Verify if the asset model field accepts input only up to 50 characters")
	public void ASST110() {
		extentTest = extent.startTest("ASST110-Verify if the asset model field accepts input only up to 50 characters");
		SoftAssert sa13 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterModelName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetModeltext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa13.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 50 characters");
		sa13.assertAll();
	}
	
	
	//ASST111a 
	@Test(dataProvider="getAstMdlInValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the Asset Model field do not accept In-Valid Data parameters")
	public void ASST111a(String Name, String ID, String Type, String Manufacturer, String Location, String Model, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST111a-Verify if the Asset Model field do not accept In-Valid Data parameters");
		SoftAssert sa14 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa14.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa14.assertAll();
	}
	
	
	//ASST111b
	@Test(dataProvider="getAstMdlValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Model field accept Valid Data parameters")
	public void ASST111b(String Name, String ID, String Type, String Manufacturer, String Location, String Model) throws InterruptedException {
		extentTest = extent.startTest("ASST111b-Verify if the Asset Model field accept Valid Data parameters");
		SoftAssert sa15 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);		
		
		sa15.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);			
		sa15.assertAll();
	}
	
	
	//ASST112
	@Test(dataProvider="getAstSizeValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Size field accept Valid Data parameters")
	public void ASST112(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit) throws InterruptedException {
		extentTest = extent.startTest("ASST112-Verify if the Asset Size field accept Valid Data parameters");
		SoftAssert sa17 = new SoftAssert();		
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		
		sa17.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa17.assertAll();
	}
	
	
	//ASST113 - Verify if the Asset Size text box accepts inputs as numeric
	//and special characters such as point or comma.
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Size field.
	@Test(dataProvider="getAstSizeInValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Size field do not accept In-Valid Data parameters")
	public void ASST113(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST113-Verify if the Asset Model field accept Valid Data parameters");
		SoftAssert sa16 = new SoftAssert();
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa16.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa16.assertAll();
	}
	
	
	//ASST114
	@Test(groups = {"Sanity"}, 
			description="Verify if by default the units drop down display as Select")
	public void ASST114() {
		extentTest = extent.startTest("ASST114-Verify if by default the units drop down display as Select");
		SoftAssert sa18 = new SoftAssert();		
		
		sa18.assertEquals(assetCreationPage.getAssetSizeUnittext(), "Select", 
				"Fail: Select is not the default Asset Size Unit selected data");
		sa18.assertAll();
	}
	
	
	//ASST115
	@Test(groups = {"Sanity"}, 
			description="Verify if the user is able to type in the desired units")
	public void ASST115() {
		extentTest = extent.startTest("ASST115 - Verify if the user is able to type in the desired units");
		SoftAssert sa19 = new SoftAssert();		
		
		assetCreationPage.enterAssetSizeUnit("testUnit");
		sa19.assertEquals(assetCreationPage.getAssetSizeUnittext(), "testUnit", 
				"Fail: Unable to enter Asset Size Unit data");
		sa19.assertAll();
	}
	
	
	//ASST116
	@Test(groups = {"Sanity"}, 
			description="Verify if the unit combo box allows only up to 50 character input")
	public void ASST116() {
		extentTest = extent.startTest("ASST116 - Verify if the unit combo box allows only up to 50 character input");
		SoftAssert sa19 = new SoftAssert();	
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Size Unit text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetSizeUnit(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetSizeUnittext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa19.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Size Unit accepts more than 50 characters");
		sa19.assertAll();
	}
	
		
	//ASST117a
	@Test(dataProvider="getAstSizeUnitInValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Size Unit field do not accept In-Valid Data parameters")
	public void ASST117a(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest("ASST117a - Verify if the Asset Size Unit field do not accept In-Valid Data parameters");
		SoftAssert sa20 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa20.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa20.assertAll();		
	}
	
	
	//ASST117b
	@Test(dataProvider="getAstSizeUnitValidTestData", dataProviderClass=assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Size Unit field accept Valid Data parameters")
	public void ASST117b(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit) throws InterruptedException {
		extentTest = extent.startTest("ASST117b - Verify if the Asset Size Unit field accept Valid Data parameters");
		SoftAssert sa21 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		
		sa21.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);			
		sa21.assertAll();		
	}
	
	
	//ASST118
	@Test(groups = {"Sanity"}, 
			description="Verify if the drop down box list outs the units used for volumes")
	public void ASST118() throws InterruptedException {
		extentTest = extent.startTest("ASST118 - Verify if the drop down box list outs the units used for volumes");
		SoftAssert sa22 = new SoftAssert();
		
		try {
			//
			assetCreationPage.assetCreationWithSizeUnit("7", "1", "Freezer", "GAS", "HYB", "10", "Meter Cube");
			assetCreationPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			assetCreationPage.CloseAlertMsg();
			assetHubPage = assetCreationPage.clickBackBtn();
			
			assetCreationPage=assetHubPage.ClickAddAssetBtn();			

			String[] SizuUnitList = assetCreationPage.getAssetSizeUnitlist();			
			//System.out.println(Arrays.toString(SizuUnitList));
			
			for (int i = 0; i < SizuUnitList.length; i++) {
				if (SizuUnitList[i].contains("Meter Cube")) {
					sa22.assertEquals(SizuUnitList[i], "Meter Cube");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu ft")) {
					sa22.assertEquals(SizuUnitList[i], "cu ft");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu mt")) {
					sa22.assertEquals(SizuUnitList[i], "cu mt");
					sa22.assertAll();
				} else if(SizuUnitList[i].contains("cu in")) {
					sa22.assertEquals(SizuUnitList[i], "cu in");
					sa22.assertAll();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Create an Asset with Unique Name");
		}		
	}
	
		
	//ASST119
	@Test(groups = {"Sanity"}, 
			description="Verify if the Manufacturer combo box by default displays the option as Select")
	public void ASST119() {
		extentTest = extent.startTest("ASST119 - Verify if the Manufacturer combo box by default displays the option as Select");
		SoftAssert sa23 = new SoftAssert();
		
		sa23.assertEquals(assetCreationPage.getAssetManufacturertext(), "Select", 
				"FAIL: Select is not the default Asset Manufacturer selected data");
		sa23.assertAll();
	}
	
	
	//ASST120
	@Test(groups = {"Sanity"}, 
			description="Verify if the user is able to type in the manufacturer name")
	public void ASST120() {
		extentTest = extent.startTest("ASST120 - Verify if the user is able to type in the manufacturer name");
		SoftAssert sa24 = new SoftAssert();
		assetCreationPage.enterManufacturerName("Hybd");
		assetCreationPage.enterModelName("LTR");
		
		
		sa24.assertEquals(assetCreationPage.getAssetManufacturertext(), "Hybd", "FAIL: Unable to enter "
				+ "value in the Asset Manufacturer field");
		sa24.assertAll();
	}
		
	
	//ASST121
	@Test(groups = {"Sanity"}, 
			description="Verify if the Manufacturer name accepts up to 100 characters")
	public void ASST121() {
		extentTest = extent.startTest("ASST121 - Verify if the Manufacturer name accepts up to 100 characters");
		SoftAssert sa25 = new SoftAssert();
		
		String expectedtxt = "123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890a";  //101 Char input
		//System.out.println("count of Asset Manufacturer text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterManufacturerName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetManufacturertext();
		//System.out.println("count of Asset Manufacturer text entered: "+actualtextentered.length());
		
		sa25.assertEquals(actualtextentered.length(), 100, "FAIL: Asset Manufacturer accepts more than 100 characters");
		sa25.assertAll();
	}
	
	
	//ASST122a
	@Test(dataProvider = "getAstMakerInValidTestData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Manufacturer field do not accept In-Valid Data parameters")
	public void ASST122a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		extentTest = extent.startTest("ASST122a - Verify if the Asset Manufacturer field do not accept In-Valid Data parameters");
		SoftAssert sa26 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa26.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa26.assertAll();
	}
	
	
	// ASST122b - Verify if Manufacturer text box accepts upper case, lower case, numeric and
	// special characters like Hyphen, slash -Forward and backward- Underscore and space as input
	@Test(dataProvider = "getAstMakerValidTestData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Manufacturer field accept Valid Data parameters")
	public void ASST122b(String Name, String ID, String Type, String Manufacturer, String Location)throws InterruptedException {
		extentTest = extent.startTest("ASST122b- Verify if the Asset Manufacturer field accept Valid Data parameters");
		SoftAssert sa27 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa27.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa27.assertAll();
	}
		
		
	// ASST123
	@Test(groups = {"Sanity"}, 
			description="Verify if the Manufacturers names are sorted in alphabetic order")
	public void ASST123() throws InterruptedException {
		extentTest = extent.startTest("ASST123 - Verify if the Manufacturers names are sorted in alphabetic order");
		SoftAssert sa28 = new SoftAssert();

		String[] OriginalList = assetCreationPage.getAssetMakerlist();
		//System.out.println(Arrays.toString(OriginalList));
		String[] SortedNewList = assetCreationPage.getAssetMakerSortedlist();
		//System.out.println(Arrays.toString(SortedNewList));

		sa28.assertEquals(OriginalList, SortedNewList);
		sa28.assertAll();
	}
	
	
	// ASST127
	@Test(groups = {"Sanity"}, 
			description="Verify if the Validation Frequency has 2 combo boxes that display default option as Select")
	public void ASST127() throws InterruptedException {
		extentTest = extent.startTest("ASST127 - Verify if the Validation Frequency has 2 combo boxes that display default option as Select");
		SoftAssert sa29 = new SoftAssert();
		
		sa29.assertEquals(assetCreationPage.getAssetFreqtext(), "Select", 
				"Fail: Select is not the default Asset Frequency field selected data");

		sa29.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), "Select", 
				"Fail: Select is not the default Asset Frequency Interval field selected data");
		
		sa29.assertAll();
	}
	
	
	
	// ASST128
	@Test(groups = {"Sanity"}, 
			description="Verify if the First combo box of Validation frequency contains "
			+ "numbers from 1 to 24 sorted in ascending order")
	public void ASST128() throws InterruptedException {
		extentTest = extent.startTest("ASST128 - Verify if the First combo box of Validation frequency "
				+ "contains numbers from 1 to 24 sorted in ascending order");
		SoftAssert sa30 = new SoftAssert();
		
		String[] expectedFrequencyList = {"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
		//System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyList = assetCreationPage.getAssetFreqlist();
		//System.out.println(Arrays.toString(FrequencyList));

		sa30.assertEquals(FrequencyList, expectedFrequencyList);

		sa30.assertAll();
	}
	

	
	// ASST129
	@Test(groups = {"Sanity"}, 
			description="Verify if the second combo box against the Validation Frequency "
			+ "field lists weeks, months and years")
	public void ASST129() throws InterruptedException {
		extentTest = extent.startTest("ASST129- Verify if the second combo box against "
				+ "the Validation Frequency field lists weeks, months and years");
		SoftAssert sa31 = new SoftAssert();
		
		String[] expectedFrequencyList = {"Weeks","Months","Years"};
		//System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyIntrvlList = assetCreationPage.getAssetFreqIntrvllist();
		//System.out.println(Arrays.toString(FrequencyIntrvlList));

		sa31.assertEquals(FrequencyIntrvlList, expectedFrequencyList);

		sa31.assertAll();
	}
	
	
	// ASST130a
	@Test(groups = {"Sanity"}, 
			description="Verify if Validation frequency should be selectable "
			+ "by touching the available choices")
	public void ASST130a() throws InterruptedException {
		extentTest = extent.startTest("ASST130a- Verify if Validation frequency should"
				+ " be selectable by touching the available choices");
		SoftAssert sa32 = new SoftAssert();
		
		//Enter text between 1-22; sometimes 23/24 will also work but few times it wont coz of slow App response.
		String expectedFreqSelection = "3";
		assetCreationPage.selectAssetFreq(expectedFreqSelection);

		sa32.assertEquals(assetCreationPage.getAssetFreqtext(), expectedFreqSelection);
		sa32.assertAll();
	}
	
	
	// ASST130b
	@Test(groups = {"Sanity"}, 
			description="Verify if Validation frequency Interval combobox should be selectable "
			+ "by touching the available choices")
	public void ASST130b() throws InterruptedException {
		extentTest = extent.startTest("ASST130b-Verify if Validation frequency Interval combobox "
				+ "should be selectable by touching the available choices");
		SoftAssert sa33 = new SoftAssert();
		
		//Enter either Weeks/Months/Years.
		String expectedFreqIntrvlSelection = "Years";
		assetCreationPage.selectAssetFreqIntrvl(expectedFreqIntrvlSelection);
		sa33.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), expectedFreqIntrvlSelection);

		sa33.assertAll();
	}
	
	
	//ASST131
	@Test(groups = {"Sanity"}, 
			description="Verify if the Location combo box by default displays the option as Select")
	public void ASST131() {
		extentTest = extent.startTest("ASST131 - Verify if the Location combo box by default displays the option as Select");
		SoftAssert sa34 = new SoftAssert();
		
		sa34.assertEquals(assetCreationPage.getAssetLocationtext(), "Select", 
				"FAIL: Select is not the default Asset Location selected data");
		sa34.assertAll();
	}
	
	
	//ASST132
	@Test(groups = {"Sanity"}, 
			description="Verify if the user should is able to type in the Location name")
	public void ASST132() {
		extentTest = extent.startTest("ASST132 - Verify if the user should is able to type in the Location name");
		SoftAssert sa35 = new SoftAssert();
		
		assetCreationPage.enterLocation("KPHB");
		sa35.assertEquals(assetCreationPage.getAssetLocationtext(), "KPHB", 
				"FAIL: Unable to type or incorrect data getting enetred into the Location field");
		sa35.assertAll();
	}
	
	
	// ASST133
	@Test(groups = {"Sanity"}, 
			description="Verify if the Location name accepts a character input up to 100")
	public void ASST133() {
		extentTest = extent.startTest("ASST133 - Verify if the Location name accepts a character input up to 100");
		SoftAssert sa36 = new SoftAssert();

		String expectedtxt = "123456789012345678901234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890a"; // 101 Char input
		// System.out.println("count of Asset Location text to be entered:
		// "+expectedtxt.length());

		assetCreationPage.enterLocation(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetLocationtext();
		// System.out.println("count of Asset Location text entered:
		// "+actualtextentered.length());

		sa36.assertEquals(actualtextentered.length(), 100, "FAIL: Asset Location accepts more than 100 characters");
		sa36.assertAll();
	}
		
		
	// ASST134
	@Test(dataProvider = "getAstLocationInValidTestData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the Asset Location field do not accept In-Valid Data parameters")
	public void ASST134a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		extentTest = extent.startTest("ASST134a - Verify if the Asset Location field do not accept In-Valid Data parameters");
		SoftAssert sa37 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa37.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa37.assertAll();
	}
	
	
	// ASST134b
	@Test(dataProvider = "getAstLocationValidTestData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if the Asset Location field accept Valid Data parameters")
	public void ASST134b(String Name, String ID, String Type, String Manufacturer, String Location)
			throws InterruptedException {
		extentTest = extent.startTest("ASST134b- Verify if the Asset Location field accept Valid Data parameters");
		SoftAssert sa38 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa38.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa38.assertAll();
	}
	
	
	// ASST135
	@Test(groups = {"Sanity"}, 
			description="Verify if the description text box accepts input up to 250 characters")
	public void ASST135() {
		extentTest = extent.startTest("ASST135 - Verify if the description text box accepts input up to 250 characters");
		SoftAssert sa39 = new SoftAssert();

		String expectedtxt = "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890a"; // 251 Char input
		// System.out.println("count of Asset Description text to be entered: "+expectedtxt.length());

		assetCreationPage.enterAstDescription(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetDescriptiontext();
		// System.out.println("count of Asset Description text entered: "+actualtextentered.length());

		sa39.assertEquals(actualtextentered.length(), 250, "FAIL: Asset Description accepts more than 250 characters");
		sa39.assertAll();
	}
	
	
	// ASST136
	@Test(groups = {"Sanity"}, 
			description="Verify if the description text box should accept upper case, "
					+ "lower case, numeric, spaces and special characters")
	public void ASST136() throws InterruptedException {
		extentTest = extent.startTest("ASST136- Verify if the description text box should "
				+ "accept upper case, lower case, numeric, spaces and special characters");
		SoftAssert sa40 = new SoftAssert();
		
		String expectedtxt = "\1aA`~!@#$%^&*()_   +=-|][}{';:.,<>?/";
		assetCreationPage.assetCreationWithDesc("AssetDesc", "1", "HeatBath", "Deccan", "KPHB", expectedtxt);
		
		//sa40.assertEquals(actualtextentered.length(), 250, "FAIL: Asset Description field do not accept special characters");
		sa40.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa40.assertAll();
	}
	
	
	// ASST143
	@Test(dataProvider = "getAstALLData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify the cancel button will discard the entries made at the current screen")
	public void ASST143(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description) throws InterruptedException {
		
		extentTest = extent.startTest("ASST143 - Verify the cancel button will discard the entries made at the current screen");
		SoftAssert sa41 = new SoftAssert();		

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, 
				Model, Size, SizeUnit, Frequency, FrequencyInterval, Description);
		
		//Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}
		
		//Click the CLear button in the Asset creation/details page
		assetCreationPage.clickClearBtn();
		
		//Validate all the field reset outputs
		sa41.assertEquals(assetCreationPage.getAssetName(), "", 
				"FAIL: Asset Name didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getEqpID(), "", 
				"FAIL: Asset ID didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetTypetext(), "Select", 
				"FAIL: Asset Type didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetManufacturertext(), "Select", 
				"FAIL: Asset Manufacturer didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetLocationtext(), "Select", 
				"FAIL: Asset Location didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetModeltext(), "", 
				"FAIL: Asset Model didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetSizetext(), "", 
				"FAIL: Asset Size didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetSizeUnittext(), "Select", 
				"FAIL: Asset SizeUnit didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetFreqtext(), "Select", 
				"FAIL: Asset Frequency didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), "Select", 
				"FAIL: Asset FrequencyInterval didn't Clear on clicking the Clear button");
		sa41.assertEquals(assetCreationPage.getAssetDescriptiontext(), "", 
				"FAIL: Asset Description didn't Clear on clicking the Clear button");
		
		sa41.assertAll();
	}
	
	
	// ASST144
	@Test(dataProvider = "getAstALLData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if the back button will -prompt as discard the changes- with Yes or No option")
	public void ASST144(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST144 - Verify if the back button will -prompt as discard the changes- with Yes or No option");
		SoftAssert sa42 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();

		sa42.assertEquals(assetCreationPage.discardAlert(), true);
		sa42.assertAll();
	}
	
	
	
	// ASST145
	@Test(dataProvider = "getAstALLData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"}, 
			description="Verify if No Option is selected and verify if the application allows the user to stay in the same page")
	public void ASST145(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST145 - Verify if No Option is selected and verify if the "
				+ "application allows the user to stay in the same page");
		SoftAssert sa43 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();
		//Click the No buttin in the Discard alert message
		assetCreationPage.discardAlertNoBtn();		

		sa43.assertEquals(assetCreationPage.SaveBtn(), true);
		sa43.assertAll();
	}
	

	
	// ASST146
	@Test(dataProvider = "getAstALLData", dataProviderClass = assetCreationUtility.class, groups = {"Sanity"},
			description="Verify if option�Yes is selected, app discard the changes made and goes back to the Asset Page")
	public void ASST146(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		
		extentTest = extent.startTest("ASST146 - Verify if option Yes is selected, app discard the "
				+ "changes made and goes back to the Asset Page");
		SoftAssert sa44 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBkBtn();
		//Click the No button in the Discard alert message
		assetHubPage = assetCreationPage.discardAlertYesBtn();			

		sa44.assertEquals(assetHubPage.addAst(), true);
		sa44.assertAll();
	}
}
