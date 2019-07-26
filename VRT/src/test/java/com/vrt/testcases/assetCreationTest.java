package com.vrt.testcases;



//import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.Listners.AllureReportListner;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetCreationPage;

import com.vrt.utility.TestUtilities;

@Listeners(AllureReportListner.class)
public class assetCreationTest extends BaseClass{
	
	//Refer TestUtilities Class for Data provider methods
	//Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p
	
	//Initialization of the Pages
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	
	
	@BeforeClass
	public void AssetCreationSetup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage = new LoginPage();

		// Verify if Asset Edit creation privilege is enabled for the respective User or not
		MainHubPage = MainLoginPage.Login("5", "Welcome2@AM");

		UserManagementPage = MainHubPage.ClickAdminTile();
		UserManagementPage.clickAnyUserinUserList("User5");

		boolean stat = UserManagementPage.PrivCreateEditAssetgstatus();
		if (!stat) {
			UserManagementPage.clickPrivCreateEditAsset();
			UserManagementPage.ClickNewUserSaveButton();
			UserLoginPopup("5", "Welcome2@AM");
			MainHubPage = UserManagementPage.ClickBackButn();
			MainLoginPage = MainHubPage.UserSignOut();
			MainLoginPage.ChangeNewPWwithoutPWCheckBox("5", "Welcome2@AM", getPW("adminFull"));
		}
		AppClose();
		Thread.sleep(1000);
	}
	
	
	@BeforeMethod
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		MainHubPage=MainLoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}

	
	// TearDown of the App
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}

	
	
	@Test(groups = "Sanity", 
			description="Verify if the asset name text box accepts input only up to 25 characters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the asset name text box accepts input only up to 25 characters")
	@Story("ASST100")
	public void ASST100() throws InterruptedException {
		SoftAssert sa1 = new SoftAssert();
		String expectedtxt = "12345678901234567890123456";  //26 Char input
		System.out.println("count of Asset name text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetName();
		System.out.println("count of Assent name text entered: "+actualtextentered.length());
		
		sa1.assertEquals(actualtextentered.length(), 25, "FAIL: Asset name accepts more than 25 characters");
		sa1.assertAll();
	}
	

	
	//ASST101a - Verify if the asset name text box do not accept invalid data parameters except 
	//input as upper case, lower case, numeric and special character like, hyphen, underscore, Slash -Forward and backward- and space
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Name field.
	@Test(dataProvider="getAstNameInvalidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Name do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Name do not accept In-Valid Data parameters")
	@Story("ASST101a")
	public void ASST101a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg, String UserName, String Password) throws InterruptedException {
		SoftAssert sa2 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa2.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa2.assertAll();
	}
	

	
	//ASST101b - Verify if the asset name text box accept input as upper case, lower case, 
	//numeric and special character like, hyphen, underscore, Slash -Forward and backward- and space
	@Test(dataProvider="getAstNameValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity", 
			description="Verify if the Asset Name accepts Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Name accepts Valid Data parameters")
	@Story("ASST101b")
	public void ASST101b(String Name, String ID, String Type, String Manufacturer, String Location, String UserName, String Password) throws InterruptedException {
		SoftAssert sa3 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa3.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa3.assertAll();
	}
	
	
	//ASST102 - Verify if the equipment id text box should accepts input only up to 15 characters
	@Test(groups = "Sanity", 
			description="Verify if the equipment id text box should accepts input only up to 15 characterss")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the equipment id text box should accepts input only up to 15 characters")
	@Story("ASST102")
	public void ASST102() throws InterruptedException {
		SoftAssert sa4 = new SoftAssert();
		String expectedtxt = "123456789012345a";  //16 Char input
		System.out.println("count of Equipment ID text to be entered: "+expectedtxt.length());
		assetCreationPage.enterAssetID(expectedtxt);
		String actualtextentered = assetCreationPage.getEqpID();
		System.out.println("count of Equipment ID text entered: "+actualtextentered.length());
		
		sa4.assertEquals(actualtextentered.length(), 15, "FAIL: Equipment ID accepts more than 100 characters");
		sa4.assertAll();
	}
	
	
	//ASST103a - Verify if the equipment id text field do not accept invalid data parameters except 
	//input as upper case, lower case, numeric and special character like, hyphen, underscore, Slash -Forward and backward-, comma and Period
	//Verify all the validation alert message observed with invalid Data parameters input to the Eqip field.
	@Test(dataProvider="getEqpIDinValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Equipment field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Equipment field do not accept In-Valid Data parameters")
	@Story("ASST103a")
	public void ASST103a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg, String UserName, String Password) throws InterruptedException {
		SoftAssert sa4 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa4.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa4.assertAll();
	}
	
	
	//ASST103b - Verify if the Equipment ID text box accept input as upper case, lower case, 
	//numeric and special character like, hyphen, underscore, Slash -Forward and backward, comma and Period
	@Test(dataProvider="getEqpIDValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity", description="Verify if the Equipment field accepts Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Equipment field accepts Valid Data parameters")
	@Story("ASST103b")
	public void ASST103b(String Name, String ID, String Type, String Manufacturer, String Location, String UserName, String Password) throws InterruptedException {
		SoftAssert sa5 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);	
		
		sa5.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa5.assertAll();
	}
	
	
	//ASST104 - Verify if the asset type combo box by default displays the option as Select
	@Test(groups = "Sanity", 
			description="Verify if the asset type combo box by default displays the option as Select")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the asset type combo box by default displays the option as Select")
	@Story("ASST104")
	public void ASST104() {
		SoftAssert sa6 = new SoftAssert();
		
		sa6.assertEquals(assetCreationPage.getAssetTypetext(), "Select", 
				"FAIL: Select is not the default Asset Type selected data");
		sa6.assertAll();
	}
	
	
	//ASST105-- Verify if the user can type in the desired asset type name
	@Test(groups = "Sanity", 
			description="Verify if the user can type in the desired asset type name")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the user can type in the desired asset type name")
	@Story("ASST105")
	public void ASST105() {
		SoftAssert sa7 = new SoftAssert();
		assetCreationPage.enterAssetType("Oven");
		assetCreationPage.enterManufacturerName("Hybd");
		assetCreationPage.getAssetTypetext();
		
		sa7.assertEquals(assetCreationPage.getAssetTypetext(), "Oven", "FAIL: Unable to enter "
				+ "value in the Asset Type field");
		sa7.assertAll();
	}
	
	
	//ASST106 - Verify if the asset type combo box allows only 50 character input
	@Test(groups = "Sanity", 
			description="Verify if the asset type combo box allows only 50 character input")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the asset type combo box allows only 50 character input")
	@Story("ASST106")
	public void ASST106() {
		SoftAssert sa8 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetType(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetTypetext();
		System.out.println("count of Asset Type text entered: "+actualtextentered.length());
		
		sa8.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 100 characters");
		sa8.assertAll();
	}
	

	//ASST107a - Verify if Asset type text box accepts upper case, lower case, numeric and 
	//special characters like Hyphen, Period, slash (Forward and backward),Comma, Underscore and space as input
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Type field.
	@Test(dataProvider="getAstTypeInValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Type field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Type field do not accept In-Valid Data parameters")
	@Story("ASST107a")
	public void ASST107a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg, String UserName, String Password) throws InterruptedException {
		SoftAssert sa9 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa9.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa9.assertAll();
	}
	
	
	//ASST107b - Verify if the Asset type text box accept input as upper case, lower case, 
	//numeric and special character like, hyphen, underscore, Slash -Forward and backward, comma and Period
	@Test(dataProvider="getAstTypeValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity", 
			description="Verify if the Asset Type field accepts Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Type field accepts Valid Data parameters")
	@Story("ASST107b")
	public void ASST107b(String Name, String ID, String Type, String Manufacturer, String Location, String UserName, String Password) throws InterruptedException {
		SoftAssert sa10 = new SoftAssert();
		assetCreationPage.assetCreationWithType(Name, ID, Type, Manufacturer, Location);	
		
		sa10.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa10.assertAll();
	}

	
	//ASST108 - Verify if the Asset types are sorted in alphabetic order
	@Test(groups = "Sanity",
			description="Verify if the Asset types are sorted in alphabetic order")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset types are sorted in alphabetic order")
	@Story("ASST108")
	public void ASST108() throws InterruptedException {
		SoftAssert sa11 = new SoftAssert();
		
		assetCreationPage.assetCreationWithType("3", "3", "Freezer", "DAS", "AGR");
		assetCreationPage.UserLoginPopup(getUN("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithType("4", "4", "Bath", "AAS", "MOM");
		assetCreationPage.UserLoginPopup(getUN("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("5", "5", "ColdChamber", "BAS", "DEL");
		assetCreationPage.UserLoginPopup(getUN("adminFull"), getPW("adminFull"));
		assetCreationPage.CloseAlertMsg();
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithType("6", "6", "DeepFreezer", "CAS", "BBS");
		assetCreationPage.UserLoginPopup(getUN("adminFull"), getPW("adminFull"));
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
	
	
	//ASST109 - Verify if the values in the Asset type combo box are selectable by touching the screen
	@Test(groups = "Sanity", 
			description="Verify if the values in the Asset type combo box are selectable by touching the screen")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the values in the Asset type combo box are selectable by touching the screen")
	@Story("ASST109")
	public void ASST109() {
		SoftAssert sa12 = new SoftAssert();
		
		assetCreationPage.SelectAssetType("DeepFreezer");
		String ActualAssetTypeSelected = assetCreationPage.getAssetTypetext();
		
		sa12.assertEquals(ActualAssetTypeSelected, "DeepFreezer");
		sa12.assertAll();
	}
	
	
	//ASST110 - Verify if the asset model field accepts input only up to 50 characters
	@Test(groups = "Sanity", 
			description="Verify if the asset model field accepts input only up to 50 characters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the asset model field accepts input only up to 50 characters")
	@Story("ASST110")
	public void ASST110() {
		SoftAssert sa13 = new SoftAssert();
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Type text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterModelName(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetModeltext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa13.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Type accepts more than 50 characters");
		sa13.assertAll();
	}
	
	
	//ASST111a - Verify Asset Model field should accept upper case, lower case, numeric 
	//and special characters like Hyphen, Period, Forward slash, Comma, Underscore and space as input
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Model field.
	@Test(dataProvider="getAstMdlInValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity", 
			description="Verify if the Asset Model field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Model field do not accept In-Valid Data parameters")
	@Story("ASST111a")
	public void ASST111a(String Name, String ID, String Type, String Manufacturer, String Location, String Model, String ExpAlrtMsg) throws InterruptedException {
		SoftAssert sa14 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa14.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa14.assertAll();
	}
	
	
	//ASST111b - Verify Asset Model field should accept upper case, lower case, numeric 
	//and special characters like Hyphen, Period, Forward slash, Comma, Underscore and space as input
	@Test(dataProvider="getAstMdlValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Model field accept Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Model field accept Valid Data parameters")
	@Story("ASST111b")
	public void ASST111b(String Name, String ID, String Type, String Manufacturer, String Location, String Model) throws InterruptedException {
		SoftAssert sa15 = new SoftAssert();
		assetCreationPage.assetCreationWithModel(Name, ID, Type, Manufacturer, Location, Model);		
		
		sa15.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);			
		sa15.assertAll();
	}

	
	//ASST112 - Verify Asset Size field should accept upper case, lower case, numeric 
	// and special characters such as point or comma
	@Test(dataProvider="getAstSizeValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Size field accept Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Size field accept Valid Data parameters")
	@Story("ASST112")
	public void ASST112(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit) throws InterruptedException {
		SoftAssert sa17 = new SoftAssert();
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		
		sa17.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);		
		sa17.assertAll();
	}
	
	
	//ASST113 - Verify if the Asset Size text box accepts inputs as numeric
	//and special characters such as point or comma.
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Size field.
	@Test(dataProvider="getAstSizeInValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Size field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Size field do not accept In-Valid Data parameters")
	@Story("ASST113")
	public void ASST113(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		SoftAssert sa16 = new SoftAssert();
		assetCreationPage.assetCreationWithSize(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa16.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa16.assertAll();
	}
	
	
	//ASST114 - Verify if by default the units drop down display as Select
	@Test(groups = "Sanity", 
			description="Verify if by default the units drop down display as Select")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if by default the units drop down display as Select")
	@Story("ASST114")
	public void ASST114() {
		SoftAssert sa18 = new SoftAssert();		
		
		sa18.assertEquals(assetCreationPage.getAssetSizeUnittext(), "Select", 
				"Fail: Select is not the default Asset Size Unit selected data");
		sa18.assertAll();
	}
	
	
	//ASST115 - Verify if the user is able to type in the desired units
	@Test(groups = "Sanity", 
			description="Verify if the user is able to type in the desired units")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the user is able to type in the desired units")
	@Story("ASST115")
	public void ASST115() {
		SoftAssert sa19 = new SoftAssert();		
		
		assetCreationPage.enterAssetSizeUnit("testUnit");
		sa19.assertEquals(assetCreationPage.getAssetSizeUnittext(), "testUnit", 
				"Fail: Unable to enter Asset Size Unit data");
		sa19.assertAll();
	}
	
	
	//ASST116 - Verify if the unit combo box allows only up to 50 character input
	@Test(groups = "Sanity", 
			description="Verify if the unit combo box allows only up to 50 character input")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the unit combo box allows only up to 50 character input")
	@Story("ASST116")
	public void ASST116() {
		SoftAssert sa19 = new SoftAssert();	
		
		String expectedtxt = "12345678901234567890123456789012345678901234567890a";  //51 Char input
		System.out.println("count of Asset Size Unit text to be entered: "+expectedtxt.length());
		
		assetCreationPage.enterAssetSizeUnit(expectedtxt);
		String actualtextentered = assetCreationPage.getAssetSizeUnittext();
		System.out.println("count of Asset Model text entered: "+actualtextentered.length());
		
		sa19.assertEquals(actualtextentered.length(), 50, "FAIL: Asset Size Unit accepts more than 50 characters");
		sa19.assertAll();
	}
	
		
	//ASST117a - Verify if unit text box accepts upper case, lower case, numeric and 
	//special characters like Hyphen, Period, slash -Forward and backward- ,Comma, Underscore and space as input
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Size unit field.
	@Test(dataProvider="getAstSizeUnitInValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Size Unit field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Size Unit field do not accept In-Valid Data parameters")
	@Story("ASST117a")
	public void ASST117a(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit, String ExpAlrtMsg) throws InterruptedException {
		SoftAssert sa20 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();
		
		sa20.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);		
		sa20.assertAll();		
	}
	
	
	//ASST117b - Verify if unit text box accepts upper case, lower case, numeric and 
	//special characters like Hyphen, Period, slash -Forward and backward- ,Comma, Underscore and space as input
	@Test(dataProvider="getAstSizeUnitValidTestData", dataProviderClass=TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Size Unit field accept Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Size Unit field accept Valid Data parameters")
	@Story("ASST117b")
	public void ASST117b(String Name, String ID, String Type, String Manufacturer, String Location, String Size, String SizeUnit) throws InterruptedException {
		SoftAssert sa21 = new SoftAssert();
		assetCreationPage.assetCreationWithSizeUnit(Name, ID, Type, Manufacturer, Location, Size, SizeUnit);		
		
		sa21.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);			
		sa21.assertAll();		
	}
	
	
	//ASST118 - Verify if the drop down box list outs the units used for volumes like cubic feet cu ft,
	// cubic meter-cu m-, cubic inches-cu in- etc,in abbreviated form along with the user defined units
	@Test(groups = "Sanity", 
			description="Verify if the drop down box list outs the units used for volumes")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the drop down box list outs the units used for volumes")
	@Story("ASST118")
	public void ASST118() throws InterruptedException {
		SoftAssert sa22 = new SoftAssert();
		
		try {
			//
			assetCreationPage.assetCreationWithSizeUnit("7", "1", "Freezer", "GAS", "HYB", "10", "Meter Cube");
			assetCreationPage.UserLoginPopup(getUN("adminFull"), getPW("adminFull"));
			assetCreationPage.CloseAlertMsg();
			assetHubPage = assetCreationPage.clickBackBtnOnAssetSave();
			
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
	
		
	//ASST119 - Verify if the Manufacturer combo box by default displays the option as Select
	@Test(groups = "Sanity", 
			description="Verify if the Manufacturer combo box by default displays the option as Select")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Manufacturer combo box by default displays the option as Select")
	@Story("ASST119")
	public void ASST119() {
		SoftAssert sa23 = new SoftAssert();
		
		sa23.assertEquals(assetCreationPage.getAssetManufacturertext(), "Select", 
				"FAIL: Select is not the default Asset Manufacturer selected data");
		sa23.assertAll();
	}
	
	
	//ASST120-- Verify if the user is able to type in the manufacturer name
	@Test(groups = "Sanity", 
			description="Verify if the user is able to type in the manufacturer name")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the user is able to type in the manufacturer name")
	@Story("ASST120")
	public void ASST120() {
		SoftAssert sa24 = new SoftAssert();
		assetCreationPage.enterManufacturerName("Hybd");
		assetCreationPage.enterModelName("LTR");
		
		
		sa24.assertEquals(assetCreationPage.getAssetManufacturertext(), "Hybd", "FAIL: Unable to enter "
				+ "value in the Asset Manufacturer field");
		sa24.assertAll();
	}
		
	
	//ASST121 - Verify if the Manufacturer name accepts up to 100 characters
	@Test(groups = "Sanity", 
			description="Verify if the Manufacturer name accepts up to 100 characters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Manufacturer name accepts up to 100 characters")
	@Story("ASST121")
	public void ASST121() {
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
	
	
	//ASST122a - Verify if Manufacturer text box accepts upper case, lower case, numeric and 
	//special characters like Hyphen, slash -Forward and backward- Underscore and space as input
	//Verify all the validation alert message observed with invalid Data parameters input to the Asset Manufacturer field.
	@Test(dataProvider = "getAstMakerInValidTestData", dataProviderClass = TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Manufacturer field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Manufacturer field do not accept In-Valid Data parameters")
	@Story("ASST122a")
	public void ASST122a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		SoftAssert sa26 = new SoftAssert();
		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa26.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa26.assertAll();
	}
	
	
	// ASST122b - Verify if Manufacturer text box accepts upper case, lower case, numeric and
	// special characters like Hyphen, slash -Forward and backward- Underscore and space as input
	@Test(dataProvider = "getAstMakerValidTestData", dataProviderClass = TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Manufacturer field accept Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Manufacturer field accept Valid Data parameters")
	@Story("ASST122b")
	public void ASST122b(String Name, String ID, String Type, String Manufacturer, String Location)throws InterruptedException {
		SoftAssert sa27 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa27.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa27.assertAll();
	}
		
		
	// ASST123 - Verify if the Manufacturers names are sorted in alphabetic order
	@Test(groups = "Sanity", 
			description="Verify if the Manufacturers names are sorted in alphabetic order")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Manufacturers names are sorted in alphabetic order")
	@Story("ASST123")
	public void ASST123() throws InterruptedException {
		SoftAssert sa28 = new SoftAssert();

		String[] OriginalList = assetCreationPage.getAssetMakerlist();
		//System.out.println(Arrays.toString(OriginalList));
		String[] SortedNewList = assetCreationPage.getAssetMakerSortedlist();
		//System.out.println(Arrays.toString(SortedNewList));

		sa28.assertEquals(OriginalList, SortedNewList);
		sa28.assertAll();
	}
	
	
	// ASST127 - Verify if the Validation Frequency has 2 combo boxes that display default option as Select
	@Test(groups = "Sanity", 
			description="Verify if the Validation Frequency has 2 combo boxes that display default option as Select")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Validation Frequency has 2 combo boxes that display default option as Select")
	@Story("ASST127")
	public void ASST127() throws InterruptedException {
		SoftAssert sa29 = new SoftAssert();
		
		sa29.assertEquals(assetCreationPage.getAssetFreqtext(), "Select", 
				"Fail: Select is not the default Asset Frequency field selected data");

		sa29.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), "Select", 
				"Fail: Select is not the default Asset Frequency Interval field selected data");
		
		sa29.assertAll();
	}
	
	
	
	// ASST128 - Verify if the First combo box of Validation frequency contains numbers 
	//from 1 to 24 sorted in ascending order
	@Test(groups = "Sanity", 
			description="Verify if the First combo box of Validation frequency contains "
			+ "numbers from 1 to 24 sorted in ascending order")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the First combo box of Validation frequency contains "
			+ "numbers from 1 to 24 sorted in ascending order")
	@Story("ASST128")
	public void ASST128() throws InterruptedException {
		SoftAssert sa30 = new SoftAssert();
		
		String[] expectedFrequencyList = {"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
		System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyList = assetCreationPage.getAssetFreqlist();
		System.out.println(Arrays.toString(FrequencyList));

		sa30.assertEquals(FrequencyList, expectedFrequencyList);

		sa30.assertAll();
	}
	

	
	// ASST129 - Verify if the second combo box against the Validation 
	//Frequency field lists weeks, months and years
	@Test(groups = "Sanity", 
			description="Verify if the second combo box against the Validation Frequency "
			+ "field lists weeks, months and years")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the second combo box against the Validation Frequency "
			+ "field lists weeks, months and years")
	@Story("ASST129")
	public void ASST129() throws InterruptedException {
		SoftAssert sa31 = new SoftAssert();
		
		String[] expectedFrequencyList = {"Weeks","Months","Years"};
		//System.out.println(Arrays.toString(expectedFrequencyList));
		String[] FrequencyIntrvlList = assetCreationPage.getAssetFreqIntrvllist();
		//System.out.println(Arrays.toString(FrequencyIntrvlList));

		sa31.assertEquals(FrequencyIntrvlList, expectedFrequencyList);

		sa31.assertAll();
	}
	
	
	// ASST130a - Verify if Validation frequency can be selected by touching
	//the available choices on both combo boxes
	@Test(groups = "Sanity", 
			description="Verify if Validation frequency should be selectable "
			+ "by touching the available choices")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if Validation frequency should be selectable "
			+ "by touching the available choices")
	@Story("ASST130a")
	public void ASST130a() throws InterruptedException {
		SoftAssert sa32 = new SoftAssert();
		
		//Enter text between 1-22; sometimes 23/24 will also work but few times it wont coz of slow App response.
		String expectedFreqSelection = "3";
		assetCreationPage.selectAssetFreq(expectedFreqSelection);

		sa32.assertEquals(assetCreationPage.getAssetFreqtext(), expectedFreqSelection);
		sa32.assertAll();
	}
	
	
	// ASST130b - Verify if Validation frequency Interval combobox can be selected by touching
	//the available choices
	@Test(groups = "Sanity", 
			description="Verify if Validation frequency Interval combobox should be selectable "
			+ "by touching the available choices")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if Validation frequency Interval combobox should be selectable "
			+ "by touching the available choices")
	@Story("ASST130b")
	public void ASST130b() throws InterruptedException {
		SoftAssert sa33 = new SoftAssert();
		
		//Enter either Weeks/Months/Years.
		String expectedFreqIntrvlSelection = "Years";
		assetCreationPage.selectAssetFreqIntrvl(expectedFreqIntrvlSelection);
		sa33.assertEquals(assetCreationPage.getAssetFreqIntrvltext(), expectedFreqIntrvlSelection);

		sa33.assertAll();
	}
	
	
	//ASST131 - Verify if the Location combo box by default displays the option as Select
	@Test(groups = "Sanity", 
			description="Verify if the Location combo box by default displays the option as Select")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Location combo box by default displays the option as Select")
	@Story("ASST131")
	public void ASST131() {
		SoftAssert sa34 = new SoftAssert();
		
		sa34.assertEquals(assetCreationPage.getAssetLocationtext(), "Select", 
				"FAIL: Select is not the default Asset Location selected data");
		sa34.assertAll();
	}
	
	
	//ASST132 - Verify if the user should is able to type in the Location name
	@Test(groups = "Sanity", 
			description="Verify if the user should is able to type in the Location name")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the user should is able to type in the Location name")
	@Story("ASST132")
	public void ASST132() {
		SoftAssert sa35 = new SoftAssert();
		
		assetCreationPage.enterLocation("KPHB");
		sa35.assertEquals(assetCreationPage.getAssetLocationtext(), "KPHB", 
				"FAIL: Unable to type or incorrect data getting enetred into the Location field");
		sa35.assertAll();
	}
	
	
	// ASST133 - Verify if the Location name accepts a character input up to 100
	@Test(groups = "Sanity", 
			description="Verify if the Location name accepts a character input up to 100")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Location name accepts a character input up to 100")
	@Story("ASST133")
	public void ASST133() {
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
		
		
	// ASST134a - Verify if Location text box do not accept invalid Data parameters
	// Verify all the validation alert message observed with invalid Data parameters
	// input to the Asset Location field.
	@Test(dataProvider = "getAstLocationInValidTestData", dataProviderClass = TestUtilities.class, groups = "Sanity", 
			description="Verify if the Asset Location field do not accept In-Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Location field do not accept In-Valid Data parameters")
	@Story("ASST134a")
	public void ASST134a(String Name, String ID, String Type, String Manufacturer, String Location, String ExpAlrtMsg)
			throws InterruptedException {
		SoftAssert sa37 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);
		String ActBlankFieldAlertMsg = assetCreationPage.AlertMsg();

		sa37.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa37.assertAll();
	}
	
	
	// ASST134b - Verify if Location text box accepts upper case, lower case, numeric and
	// special characters like Hyphen, slash -Forward and backward- Underscore and space as input
	@Test(dataProvider = "getAstLocationValidTestData", dataProviderClass = TestUtilities.class, groups = "Sanity",
			description="Verify if the Asset Location field accept Valid Data parameters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset Location field accept Valid Data parameters")
	@Story("ASST134b")
	public void ASST134b(String Name, String ID, String Type, String Manufacturer, String Location)
			throws InterruptedException {
		SoftAssert sa38 = new SoftAssert();

		assetCreationPage.assetCreation(Name, ID, Type, Manufacturer, Location);

		sa38.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa38.assertAll();
	}
	
	
	// ASST135 - Verify if the description text box accepts input up to 250 characters
	@Test(groups = "Sanity", 
			description="Verify if the description text box accepts input up to 250 characters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the description text box accepts input up to 250 characters")
	@Story("ASST135")
	public void ASST135() {
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
	
	
	// ASST136 - Verify if the description text box should accept upper case, lower case, numeric, 
	//spaces and special characters
	@Test(groups = "Sanity", 
			description="Verify if the description text box should accept upper case, lower case, numeric, spaces and special characters")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the description text box should accept upper case, lower case, numeric, spaces and special characters")
	@Story("ASST136")
	public void ASST136() throws InterruptedException {
		SoftAssert sa40 = new SoftAssert();
		
		String expectedtxt = "\1aA`~!@#$%^&*()_   +=-|][}{';:.,<>?/";
		assetCreationPage.assetCreationWithDesc("AssetDesc", "1", "HeatBath", "Deccan", "KPHB", expectedtxt);
		
		//sa40.assertEquals(actualtextentered.length(), 250, "FAIL: Asset Description field do not accept special characters");
		sa40.assertEquals(assetCreationPage.UserLoginPopupVisible(), true);
		sa40.assertAll();
	}
	
	
	
	// ASST143 - Verify the cancel button will discard the entries made at the current screen
	@Test(dataProvider = "getAstALLData", dataProviderClass = TestUtilities.class, groups = "Sanity", 
			description="Verify the cancel button will discard the entries made at the current screen")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify the cancel button will discard the entries made at the current screen")
	@Story("ASST143")
	public void ASST143(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description) throws InterruptedException {
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
	

	
	// ASST144 - Verify if the back button will -prompt as discard the changes- with Yes or No option
	@Test(dataProvider = "getAstALLData", dataProviderClass = TestUtilities.class, groups = "Sanity", 
			description="Verify if the back button will -prompt as discard the changes- with Yes or No option")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the back button will -prompt as discard the changes- with Yes or No option")
	@Story("ASST144")
	public void ASST144(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa42 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBackBtn();

		sa42.assertEquals(assetCreationPage.discardAlert(), true);
		sa42.assertAll();
	}

	
	
	// ASST145 - Verify if No Option is selected and verify if the application allows the user to stay in the same page
	@Test(dataProvider = "getAstALLData", dataProviderClass = TestUtilities.class, groups = "Sanity", 
			description="Verify if No Option is selected and verify if the application allows the user to stay in the same page")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if No Option is selected and verify if the application allows the user to stay in the same page")
	@Story("ASST145")
	public void ASST145(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa43 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBackBtn();
		//Click the No buttin in the Discard alert message
		assetCreationPage.discardAlertNoBtn();		

		sa43.assertEquals(assetCreationPage.SaveBtn(), true);
		sa43.assertAll();
	}
	

	
	// ASST146 - Verify if option�Yes is selected, app discard the changes made and goes back to the Asset Page
	@Test(dataProvider = "getAstALLData", dataProviderClass = TestUtilities.class, groups = "Sanity",
			description="Verify if option�Yes is selected, app discard the changes made and goes back to the Asset Page")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if option�Yes is selected, app discard the changes made and goes back to the Asset Page")
	@Story("ASST146")
	public void ASST146(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa44 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Closing the User credential Pop if its visible
		if (assetCreationPage.UserLoginPopupVisible()) {
			assetCreationPage.UserLoginPopupClose();
		}

		// Click the Back button in the Asset creation/details page
		assetCreationPage.clickBackBtn();
		//Click the No buttin in the Discard alert message
		assetHubPage = assetCreationPage.discardAlertYesBtn();			

		sa44.assertEquals(assetHubPage.addAst(), true);
		sa44.assertAll();
	}
}
