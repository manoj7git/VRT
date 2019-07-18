package com.vrt.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.utility.TestUtilities;

public class assetHubTest extends BaseClass {
	
	//Refer TestUtilities Class for Data provider methods
	//Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p
	
	//Initialization of the Pages
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	
	
	/*@BeforeClass
	public void AssetHubSetup() throws InterruptedException {
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
			MainLoginPage.ChangeNewPWwithoutPWCheckBox("5", "Welcome2@AM", "Welcome5@AM");
		}
		AppClose();
		Thread.sleep(1000);
	}*/

	
	@BeforeMethod
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		MainHubPage=MainLoginPage.Login("5", "Welcome5@AM");
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}

	
	// TearDown of the App
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}
	
	
	// ASST147 - Verify all the changes made to the asset are displayed correctly at Asset Hub Page
	@Test(dataProvider = "tc147", dataProviderClass = TestUtilities.class, groups = "Asset Hub Page")
	public void ASST147(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		SoftAssert sa44 = new SoftAssert();

		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);

		//Enter User Credentials to Save Asset
		UserLoginPopup("5", "Welcome5@AM");


		// Click the Back button in the Asset creation/details page if the Save message is 
		//displayed inorder to move to Asset Hub Page
		if (assetCreationPage.saveAlertMsg()) {
			assetHubPage = assetCreationPage.clickBackBtnOnAssetSave();
		}
		
		
		// Click the No buttin in the Discard alert message
		assetHubPage = assetCreationPage.discardAlertYesBtn();

		sa44.assertEquals(assetHubPage.addAst(), true);
		sa44.assertAll();
	}

}
