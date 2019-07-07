package com.vrt.pages;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class MainHubPage extends BaseClass {

	// Main Hub Page Element definition
	WebElement MainUILoggedinUserTitle = driver.findElementByAccessibilityId("UserDesignationTextBlock");
	WebElement MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
	WebElement MainUIAdminTile = driver.findElementByName("Admin");
	WebElement MainUIAssetTile = driver.findElementByName("Assets");
	
	
	
	//Verify the Logged in User credentials
	public String LoggedinUserName() {
		return FetchText(MainUILoggedinUserName);
	}
	
	//Signout Operation
	public LoginPage UserSignOut() throws InterruptedException {
		clickOn(MainUILoggedinUserName);
		Thread.sleep(1000);
		WebElement MainUISignOut = driver.findElementByAccessibilityId("SignoutButton");
		clickOn(MainUISignOut);
		Thread.sleep(1000);
		return new LoginPage();
	}
	
	//Click the Admin Tile
	public UserManagementPage ClickAdminTile() throws InterruptedException {
		clickOn(MainUIAdminTile);
		Thread.sleep(1000);
		return new UserManagementPage();
	}
	
	//Click the Asset Tile
	public assetHubPage ClickAssetTile() throws InterruptedException {
		clickOn(MainUIAssetTile);
		Thread.sleep(1000);
		return new assetHubPage();
	}
	
}
