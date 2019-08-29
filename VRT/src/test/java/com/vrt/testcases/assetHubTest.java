package com.vrt.testcases;

import java.io.IOException;
import java.util.Arrays;

/*import org.apache.commons.mail.EmailException;
import org.apache.tools.ant.taskdefs.SendEmail;
import org.testng.annotations.AfterClass;*/
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.Listners.AllureReportListner;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.utility.TestUtilities;
//import com.vrt.utility.emailUtility;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(AllureReportListner.class)
public class assetHubTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;

	@BeforeClass
	public void AssetCreationSetup() throws InterruptedException, IOException {

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");

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

	/*@AfterClass
	public void email() throws EmailException {
		emailUtility.sendEmail();
	}*/
	
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();		
	}

	// TearDown of the App
	@AfterMethod(alwaysRun=true)
	public void Teardown() {
		driver.quit();
	}

	
	@Test(groups = {"Sanity", "Regression"}, description = "ASST001-Verify if selecting the Assets tile from the main "
			+ "hub page , user is navigated to the Asset Details screen")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verify if selecting the Assets tile from the main hub page,"
			+ " user is navigated to the Asset Details screen")
	@Story("ASST001")
	public void ASST001() throws InterruptedException {
		SoftAssert sa1 = new SoftAssert();

		sa1.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST001 -Incorrect Asset Page title or landed into incorrect Page");
		sa1.assertAll();
	}
	
	
	
	
	// ASST002-Verify if with  fresh installation, no assets should be displayed
/*	@Test(groups = {"Regression"}, description = "ASST002-Verify if with  fresh installation,"
			+ " no assets should be displayed")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if with  fresh installation, no assets should be displayed")
	@Story("ASST002")
	public void ASST002() throws InterruptedException {
		SoftAssert sa2 = new SoftAssert();

		sa2.assertEquals(assetHubPage.assetcount(), "0",
				"FAIL: TC-ASST002 -Already assets present in the system which "
				+ "suggests its not an Fresh Installation may have been upgraded");
		sa2.assertAll();
	}*/
	
	
	//ASST003 TC = ASST149 below
	
	
	
	//ASST004-Verify if Predefined Sort Options namely -Type, Model, Size, Manufacturer and Location are present
/*	@Test(groups = {"Regression"}, description = "ASST004-Verify if Predefined Sort Options namely -Type,"
			+ "Manufacturer and Location are present")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST004-Verify if Predefined Sort Options namely -Type, Manufacturer and Location are present")
	@Story("ASST004")
	public void ASST004() throws InterruptedException {
		SoftAssert sa3 = new SoftAssert();

		sa3.assertEquals(assetHubPage.typeFilterBtn(), true, "FAIL: TC-ASST004 -Type FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.manufacturerFilterBtn(), true, "FAIL: TC-ASST004 -Manufacturer FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.locationFilterBtn(), true, "FAIL: TC-ASST004 -Location FIlter is absent from the Assethub Page");
		sa3.assertAll();
	}*/
	
	
	
	
	//ASST005-Verify  if Add New and Search -magnifier Icons are present at the right top corner of the assets page
/*	@Test(groups = {"Regression"}, description = "ASST005-Verify  if Add New and Search -magnifier Icons"
	 +"are present at the right top corner of the assets page")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST005-Verify if Add New and Search Icons are present at the right top corner of the assets page")
	@Story("ASST005")
	public void ASST005() throws InterruptedException {
		SoftAssert sa4 = new SoftAssert();

		sa4.assertEquals(assetHubPage.serachAstBtn(), true, "FAIL: TC-ASST005 -Asset Search button missing");
		sa4.assertEquals(assetHubPage.addAst(), true, "FAIL: TC-ASST005 -Asset Add button missing");
		
		sa4.assertAll();
	}*/
	
	
	
	
	//ASST006-Verify  if clicking on New icon opens a New Asset creation page
/*	@Test(groups = {"Regression"}, description = "ASST006-Verify  if clicking on New icon opens a New Asset creation page")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST006-Verify  if clicking on New icon opens a New Asset creation page")
	@Story("ASST006")
	public void ASST006() throws InterruptedException {
		SoftAssert sa5 = new SoftAssert();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();

		sa5.assertEquals(assetCreationPage.newAssetCreatePagetitle(), true, "FAIL: TC-ASST006 -Landed "
				+ "to Wrong page instead of New Asset creation page");				
		sa5.assertAll();
	}*/
	
	
	
	
	//ASST007-Verify if clicking on Back Button at the left top to return to main Hub page
	/*@Test(groups = {"Regression"}, description = "ASST007-Verify if clicking on Back Button at the left top to return to main Hub page")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST007-Verify if clicking on Back Button at the left top to return to main Hub page")
	@Story("ASST007")
	public void ASST007() throws InterruptedException {
		SoftAssert sa6 = new SoftAssert();
		
		MainHubPage=assetHubPage.ClickBackBtn();

		sa6.assertEquals(MainHubPage.mainPageTitle(), true, "FAIL: TC-ASST007 -Landed "
				+ "to Wrong page instead of Main Hub page");				
		sa6.assertAll();
	}*/
	

	
/*	@Test(groups = {"Sanity", "Regression"}, description = "ASST008-Verify if the help section in the "
			+ "Asset hub page is displayed by clicking Help icon")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST008-Verify if the help section in the Asset hub page is displayed by clicking Help icon")
	@Story("ASST008")
	public void ASST008() throws InterruptedException {
		SoftAssert sa7 = new SoftAssert();
		
		assetHubPage.rightclickonAssetPageTitle();
		assetHubPage.clickHelpIcon();

		sa7.assertEquals(assetHubPage.is_assetHubHelpWindow_Displayed(), true, "FAIL: TC-ASST008 -AssetHub Help"
				+ "window did not appear or wrong Help window displayed");				
		sa7.assertAll();
	}
	*/
	
	/*
	 * Conduct the Below tests after conducting the Asset Creation Test cases
	 */
	
	
	/*@Test(groups = {"Sanity", "Regression"}, description = "ASST011-Verify if click on the Type filter "
			+ "user is able to filter all the assets correctly by Asset Type")
	@Severity(SeverityLevel.NORMAL)
	@Description("ASST011-Verify if click on the Type filter user is able to filter all the assets correctly by Asset Type")
	@Story("ASST011")
	public void ASST011() throws InterruptedException {
		SoftAssert sa8 = new SoftAssert();
		
		assetCreationPage = assetHubPage.ClickAddAssetBtn();

		sa8.assertEquals(assetCreationPage.newAssetCreatePagetitle(), true, "FAIL: TC-ASST010 -Clicking on "
				+ "Add Asset Icon, do not Redirect to the New Asset creation screen ");				
		sa8.assertAll();
	}*/
	
	
	//ASST011-Verify if click on the Type filter user is able to filter all the assets correctly by Asset Type
		/*@Test(groups = {"Sanity", "Regression"}, description = "ASST011-Verify if click on the Type filter "
				+ "user is able to filter all the assets correctly by Asset Type")
		@Severity(SeverityLevel.NORMAL)
		@Description("ASST011-Verify if click on the Type filter user is able to filter all the assets correctly by Asset Type")
		@Story("ASST011")
		public void ASST012() throws InterruptedException {
			SoftAssert sa8 = new SoftAssert();
			
			assetCreationPage = assetHubPage.ClickAddAssetBtn();

			sa8.assertEquals(assetCreationPage.newAssetCreatePagetitle(), true, "FAIL: TC-ASST010 -Clicking on "
					+ "Add Asset Icon, do not Redirect to the New Asset creation screen ");				
			sa8.assertAll();
		}*/
	
	/*// ASST147 - Verify all the changes made to the asset are displayed correctly at Asset Hub Page
	//Enter a set of Unique Asset details info in the corresponding excel data sheet "tc147"
	@Test({"Sanity", "Regression"}, dataProvider = "tc147", dataProviderClass = TestUtilities.class, groups = "Sanity", 
			description = "Verify all the changes made to the asset are displayed correctly at Asset Hub Page")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify all the changes made to the asset are displayed correctly at Asset Hub Page")
	@Story("ASST147")
	public void ASST147(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa1 = new SoftAssert();
		
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Enter User Credentials to Save Asset
		UserLoginPopup(getUN("adminFull"), getPW("adminFull"));

		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		

		String[] expectedAssetInfo = {"HeatBath","147b","Asset147b"};
		System.out.println("exp asst info1:"+Arrays.toString(expectedAssetInfo));
		
		String[] ActualAssetinfo = assetHubPage.assetTile(Name);
		System.out.println("Actual asst info1:"+Arrays.toString(ActualAssetinfo));
		for (String str2 : ActualAssetinfo) {
			System.out.println("Act asst info2: "+str2);
		}

		sa1.assertEquals(ActualAssetinfo, expectedAssetInfo);;
		sa1.assertAll();
	}
	*/
	
	/*// ASST149_ASST003 - Verify if the number of assets present in the Asset Hub page
	// is equal to the count displayed in the Asset tile of the Main Hub page	
	// Enter a set of Unique Asset details info in the corresponding excel data sheet "tc149"
	@Test({"Sanity", "Regression"}, dataProvider = "tc149", dataProviderClass = TestUtilities.class, groups = "Sanity", description = "Verify "
			+ "if the Asset count info is equal to the assets created in the Asset Hub page")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify if the Asset count info is equal to the assets created in the Asset Hub page")
	@Story("ASST149_ASST003")
	public void ASST149_ASST003(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa2 = new SoftAssert();

		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		// Enter User Credentials to Save Asset
		UserLoginPopup(getUN("adminFull"), getPW("adminFull"));

		// Click the Back button in the Asset creation/details page & click the Save
		// message if displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();

		String AssetCountInAssetHubPage = assetHubPage.assetcount();
		System.out.println("Asst count in AsstHubPage:" + AssetCountInAssetHubPage);
		
		MainHubPage = assetHubPage.ClickBackBtn();
		String AssetCountInMainHubPage = MainHubPage.AssetCountInAssetTileOfMainHubPage();

		sa2.assertEquals(AssetCountInAssetHubPage, AssetCountInMainHubPage, "FAIL: TC149 -Mismatch in Asset count");
		sa2.assertAll();
	}
	*/	
}
