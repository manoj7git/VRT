package com.vrt.pages;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_defineSetupPage extends BaseClass {
	
	// Define Setup page element variable declaration definition
	WebElement DefineSetupPageName = null;
	WebElement DefineSetupPageTitle = null;
	WebElement DefineSetup_backBtn = null;	
	WebElement DefineSetup_SetupName_txtBx = null;
	WebElement DefineSetup_Sensordata_txtBx = null;
	WebElement DefineSetup_AssetID_txtBx = null;
	WebElement Next_Btn = null;
	
	private void initializeEelements() {
		DefineSetupPageName = driver.findElementByName("Define Setup");
		DefineSetupPageTitle = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		DefineSetup_backBtn = driver.findElementByAccessibilityId("GoButton");
		DefineSetup_SetupName_txtBx = driver.findElementByAccessibilityId("SetupNameTextBox");
		DefineSetup_Sensordata_txtBx = driver.findElementByAccessibilityId("ContentElement");
		DefineSetup_AssetID_txtBx = driver.findElementByAccessibilityId("VessalTextBox");
		Next_Btn = driver.findElementByAccessibilityId("NextButton");
		
	}
	
	Setup_defineSetupPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Define Setup page
	public boolean defineSetupPage_state() {
		return IsElementVisibleStatus(DefineSetupPageName);
	}
	
	// Get the Define Setup page Name text
	public String get_defineSetupPage_Nametext() {
		return FetchText(DefineSetupPageName);
	}
	
	// Get the Define Setup page Title text
	public String get_defineSetupPage_Titletext() {
		return FetchText(DefineSetupPageTitle);
	}
	
	// Click the Define Setup page Back button
	public void click_defineSetupPage_backBtn() throws InterruptedException {
		clickOn(DefineSetup_backBtn);
		Thread.sleep(1000);
	}
	
	// Click Yes to Alert message
	public assetDetailsPage click_YesofAlert_msg() throws InterruptedException {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		
		if (IsElementVisibleStatus(alrtmsg)) {
			WebElement alrtmsg_YesBtn = driver.findElementByName("Yes");	
			clickOn(alrtmsg_YesBtn);
			Thread.sleep(1000);
			return new assetDetailsPage();
		} else {
			System.out.println("No Alert message displayed");
			return new assetDetailsPage();
		}
	}
	
	// Click No to Alert message
	public void click_NoofAlert_msg() throws InterruptedException {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		
		if (IsElementVisibleStatus(alrtmsg)) {
			WebElement alrtmsg_NoBtn = driver.findElementByName("No");	
			clickOn(alrtmsg_NoBtn);
		} else {
			System.out.println("No Alert message displayed");
		}

	}
	
	// Clear Setup Name	
	public void clear_defineSetupPage_setupName() {
		ClearText(DefineSetup_SetupName_txtBx);
	}
		
	// Enter Setup Name	
	public void enter_defineSetupPage_setupName(String setUpNm) {
		enterText(DefineSetup_SetupName_txtBx, setUpNm);
	}
	
	// Get Setup Name data
	public String get_defineSetupPage_setupName() {
		return FetchText(DefineSetup_SetupName_txtBx);
	}
	
	// Clear Sensor count data	
	public void clear_defineSetupPage_SensorCount() {
		ClearText(DefineSetup_Sensordata_txtBx);
	}
	
	// Click Sensor count data field
	public void click_defineSetupPage_SensorCountField() {
		clickOn(DefineSetup_Sensordata_txtBx);
	}	
	
	// Enter Sensor count data
	public void enter_defineSetupPage_SensorCount(String sensorCnt) {
		enterText(DefineSetup_Sensordata_txtBx, sensorCnt);
	}
	
	// Get the Asset ID text for the Asset ID test field
	public String get_AssetID_text() {
		return FetchText(DefineSetup_AssetID_txtBx);
	}
	
	// Get text of the Button Bar Alert message
	public String get_ButtomBarAlertmsg_txt() {
		WebElement alrtmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(alrtmsg);
	}
	
	
	
	
	
	
	
	
	// Click the Next button in the Define Setup page to move to Sensor Config page
	public Setup_SensorConfigPage click_defineSetupPage_nxtBtn() throws InterruptedException {
		clickOn(Next_Btn);
		Thread.sleep(1000);
		return new Setup_SensorConfigPage();
	}
	

}
