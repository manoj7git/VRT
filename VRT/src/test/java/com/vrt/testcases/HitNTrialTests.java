package com.vrt.testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;

public class HitNTrialTests extends BaseClass {
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;

	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage = new LoginPage();
		MainHubPage = MainLoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		//assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}

	
/*	// TearDown of the App
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}*/

	/*
	 * @Test public void PrivTest() throws InterruptedException {
	 * MainHubPage=MainLoginPage.Login("5", "Welcome5@AM");
	 * UserManagementPage=MainHubPage.ClickAdminTile();
	 * UserManagementPage.clickAnyUserinUserList("User5"); boolean stat=
	 * UserManagementPage.PrivCreateEditAssetgstatus(); System.out.println(stat);
	 * UserManagementPage.clickPrivCreateEditAsset();
	 * System.out.println(UserManagementPage.PrivCreateEditAssetgstatus()); }
	 */

/*	@Test
	public void fetchAssetlist() {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		System.out.println("Total Assets count: " + AssetList.size());

		for (int i = 0; i < AssetList.size(); i++) {
			System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfo = AssetList.get(i).findElements(By.className("TextBlock"));
			//System.out.println(AssetTileInfo.size());
			for (int j = 0; j < AssetTileInfo.size(); j++) {
				//System.out.println("AssetTileInfo: "+AssetTileInfo.get(j).getText());
				if (AssetTileInfo.get(j).getText().equals("7")) {
					System.out.println("AssetTileInfo (AssetName): "+AssetTileInfo.get(j).getText());
					AssetTileInfo.get(j).click();
				}
			}
		}
		
		
	}
	*/
	
	
	/*@Test
	public void retryFailTC() {
		Assert.assertEquals(true, false);		
	}*/
	
	@Test
	public void fetchAssettypelist() throws InterruptedException {
		boolean state1 = assetHubPage.assetList_TypeFilter();
		System.out.println(state1);
		/*boolean state2 = assetHubPage.assetList_ManufacturerFilter();
		System.out.println(state2);*/
		
		Assert.assertEquals(state1, true);	
		//Assert.assertEquals(state2, true);	
	}
	
	
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
	
/*	@Test (description="Check for File renaming")
	public void renameFile2() throws IOException {
		//Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
				
	}*/
	
	
/*	@Test
	public void fetchAssetUnit() {
		assetCreationPage.selectAssetSizeUnit("cu ft");		
	}*/
	
}
