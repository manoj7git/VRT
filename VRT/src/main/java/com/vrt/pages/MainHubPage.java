/**
 * @author manoj.ghadei
 *
 */

package com.vrt.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class MainHubPage extends BaseClass {

	public MainHubPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	// Main Hub Page Element definition
	WebElement MainUILoggedinUserTitle = driver.findElementByAccessibilityId("UserDesignationTextBlock");
	WebElement MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
	WebElement MainUIAdminTile = driver.findElementByName("Admin");
	WebElement MainUIAssetTile = driver.findElementByName("Assets");
	WebElement AssetCountInfoInAsstTile = driver.findElementByAccessibilityId("TitleCountTextBlock");
	WebElement MainUIPageTitle = driver.findElementByName("ValProbe RT System");
	WebElement MainUIEquipmentTitle = driver.findElementByName("Equipment");
	WebElement FileManagementTitle = driver.findElementByName("File Management");
	WebElement AuditTitle = driver.findElementByName("Audit");

	// Verify the Main Hub Page title name
	public boolean mainPageTitle() {
		return IsElementVisibleStatus(MainUIPageTitle);
	}

	// Is UserDesignation text Block Presence

	public boolean IsUserDesigBlockPresence() {
		WebElement UserDesigTextBlock = driver.findElementByAccessibilityId("UserDesignationTextBlock");
		return IsElementEnabledStatus(UserDesigTextBlock);
	}

	// Verify the Logged in User credentials
	public String LoggedinUserName() {
		return FetchText(MainUILoggedinUserName);
	}

	//
	public String UserNameText() {
		WebElement UserNameText = driver.findElementByAccessibilityId("UserNameTextBlock");
		return FetchText(UserNameText);
	}

	// Sign out Operation
	public LoginPage UserSignOut() throws InterruptedException, IOException {
		clickOn(MainUILoggedinUserName);
		Thread.sleep(500);
		WebElement MainUISignOut = driver.findElementByName("Sign out");
		clickOn(MainUISignOut);
		Thread.sleep(500);
		return new LoginPage();
	}

	// Click the Admin Tile
	public UserManagementPage ClickAdminTile_UMpage() throws InterruptedException, IOException {
		clickOn(MainUIAdminTile);
		Thread.sleep(500);
		return new UserManagementPage();
	}

	// Click the Admin Tile when SuperVisor does not have default access privilege
	public void ClickAdminTile() throws InterruptedException {
		clickOn(MainUIAdminTile);
		Thread.sleep(500);
	}

	// Fetch the alert message when Supervisor is unable to access Archive data
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Click the Asset Tile
	public assetHubPage ClickAssetTile() throws InterruptedException, IOException {
		clickOn(MainUIAssetTile);
		Thread.sleep(500);
		return new assetHubPage();
	}

	// Fetch the Asset count data in the Asset Tile
	public String AssetCountInAssetTileOfMainHubPage() throws InterruptedException {
		String AstCnt = FetchText(AssetCountInfoInAsstTile);
		// System.out.println("AstCnt in Main Hub Page: "+AstCnt);
		return AstCnt;
	}

	// Click the Equipment Tile
	public EquipmentHubPage ClickEquipmentTile() throws InterruptedException, IOException {
		clickOn(MainUIEquipmentTitle);
		Thread.sleep(500);
		return new EquipmentHubPage();
	}

	// Click the Equipment Tile
	public FileManagementPage ClickFileManagementTitle() throws InterruptedException, IOException {
		clickOn(FileManagementTitle);
		Thread.sleep(500);
		return new FileManagementPage();
	}

	// Click the Audit Title
	public AuditPage ClickAuditTitle() throws InterruptedException, IOException {
		clickOn(AuditTitle);
		Thread.sleep(500);
		return new AuditPage();
	}
	// Click the Audit Title
		public void Alert_AuditTitle() throws InterruptedException {
			clickOn(AuditTitle);
			Thread.sleep(500);
		}
}
