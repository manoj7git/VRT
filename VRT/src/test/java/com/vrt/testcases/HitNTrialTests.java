package com.vrt.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;

public class HitNTrialTests extends BaseClass{
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	
	
	
	@BeforeMethod
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
	}

	// TearDown of the App
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}
	
	@Test
	public void PrivTest() throws InterruptedException {
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile();
		UserManagementPage.clickAnyUserinUserList("User5");
		boolean stat= UserManagementPage.PrivCreateEditAssetgstatus();
		System.out.println(stat);
		UserManagementPage.clickPrivCreateEditAsset();
		System.out.println(UserManagementPage.PrivCreateEditAssetgstatus());
	}

}
