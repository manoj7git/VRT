package com.vrt.testcases;

import java.util.Arrays;

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

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(AllureReportListner.class)
public class assetHubTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

	// Initialization of the Pages
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
		
		try {
			MainLoginPage.AlertLogin("5", "Welcome2@AM");

			if ((MainLoginPage.InvalidLoginAlertmsgPresence())) {
				System.out.println("User 5 got full Admin privilege");
				AppClose();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			MainHubPage = new MainHubPage();
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

				AppClose();
				Thread.sleep(1000);
			}
		}		
	}
	 

	@BeforeMethod
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage = new LoginPage();
		MainHubPage = MainLoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();		
	}

	// TearDown of the App
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}

	// ASST147 - Verify all the changes made to the asset are displayed correctly at Asset Hub Page
	//Enter a set of Unique Asset details info in the corresponding excel data sheet "tc147"
	@Test(dataProvider = "tc147", dataProviderClass = TestUtilities.class, groups = "Sanity", 
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
		System.out.println("Act asst info1:"+Arrays.toString(ActualAssetinfo));
/*		for (String str2 : ActualAssetinfo) {
			System.out.println("Act asst info2: "+str2);
		}*/

		sa1.assertEquals(ActualAssetinfo, expectedAssetInfo);;
		sa1.assertAll();
	}

}
