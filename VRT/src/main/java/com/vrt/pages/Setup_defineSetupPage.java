package com.vrt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class Setup_defineSetupPage extends BaseClass {
	
	// Define Setup page element variable declaration definition
	private WebElement DefineSetupPageName = null;
	private WebElement DefineSetupPageTitle = null;
	private WebElement DefineSetup_backBtn = null;	
	private WebElement DefineSetup_SetupName_txtBx = null;
	private WebElement DefineSetup_Sensordata_txtBx = null;
	private WebElement DefineSetup_AssetID_txtBx = null;
	private WebElement DefineSetup_SOP_txtBx = null;
	private WebElement DefineSetup_LoadDesc_txtBx = null;
	private WebElement DefineSetup_Cmnts_txtBx = null;
	private WebElement SensorConfiguration_Btn = null;
	
	private void initializeEelements() {
		DefineSetupPageName = driver.findElementByName("Define Setup");
		DefineSetupPageTitle = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		DefineSetup_backBtn = driver.findElementByAccessibilityId("GoButton");
		DefineSetup_SetupName_txtBx = driver.findElementByAccessibilityId("SetupNameTextBox");
		DefineSetup_Sensordata_txtBx = driver.findElementByAccessibilityId("ContentElement");
		DefineSetup_AssetID_txtBx = driver.findElementByAccessibilityId("VessalTextBox");
		DefineSetup_SOP_txtBx = driver.findElementByAccessibilityId("SopProtocolTextBox");
		DefineSetup_LoadDesc_txtBx = driver.findElementByAccessibilityId("LoadDescTextBox");
		DefineSetup_Cmnts_txtBx = driver.findElementByAccessibilityId("CommentTextBox");
		SensorConfiguration_Btn = driver.findElementByAccessibilityId("NextButton");
		
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
	
	// Check the presence of Alert message on clicking the Setup Back Button
	public boolean visible_AlertMsg_state() {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		return IsElementVisibleStatus(alrtmsg);
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
	
	// Get the sensor text
	public String get_Sensorcount_text() {
		return FetchText(DefineSetup_Sensordata_txtBx);
	}
	
	// Get the Asset ID text for the Asset ID test field
	public String get_AssetID_text() {
		return FetchText(DefineSetup_AssetID_txtBx);
	};
	
	// Verify the Asset ID Field is enable or not 
		public boolean AssetIDEnable() {
			//return IsElementEnabledStatus(DefineSetup_AssetID_txtBx);
			//DefineSetup_AssetID_txtBx.isDisplayed();
			//return DefineSetup_AssetID_txtBx.isDisplayed();
			return IsElementEnabledStatus(DefineSetup_AssetID_txtBx);
		}
	
	// Get text of the Button Bar Alert message
	public String get_ButtomBarAlertmsg_txt() {
		WebElement alrtmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(alrtmsg);
	}
	
	// Check the presence of SOP field in Define Setup page
	public boolean visible_SOPField() {
		return IsElementVisibleStatus(DefineSetup_SOP_txtBx);
	}
	
	// Click SOP field
	public void click_defineSetupPage_SOPField() {
		clickOn(DefineSetup_SOP_txtBx);
	}	
	
	// Clear Sensor count data	
	public void clear_defineSetupPage_SOP() {
		ClearText(DefineSetup_SOP_txtBx);
	}
	
	// Enter SOP data
	public void enter_defineSetupPage_SOP(String SOPdt) {
		enterText(DefineSetup_SOP_txtBx, SOPdt);
	}
	
	// Check the presence of Load Description field in Define Setup page
	public boolean visible_LoadDescField() {
		return IsElementVisibleStatus(DefineSetup_LoadDesc_txtBx);
	}
	
	// Click Load Description field
	public void click_defineSetupPage_LoadDescField() {
		clickOn(DefineSetup_LoadDesc_txtBx);
	}	
	
	// Clear Load Description data field
	public void clear_defineSetupPage_LoadDesc() {
		ClearText(DefineSetup_LoadDesc_txtBx);
	}
	
	// Enter Load Description data
	public void enter_defineSetupPage_LoadDesc(String LDdt) {
		enterText(DefineSetup_LoadDesc_txtBx, LDdt);
	}
	
	// Check the presence of comments field in Define Setup page
	public boolean visible_Comments_Field() {
		return IsElementVisibleStatus(DefineSetup_Cmnts_txtBx);
	}
	
	// Click comments field
	public void click_defineSetupPage_commentsField() {
		clickOn(DefineSetup_Cmnts_txtBx);
	}	
	
	// Clear comments data field
	public void clear_defineSetupPage_comments() {
		ClearText(DefineSetup_Cmnts_txtBx);
	}
	
	// Enter comments data
	public void enter_defineSetupPage_comments(String Cmntdt) {
		enterText(DefineSetup_Cmnts_txtBx, Cmntdt);
	}
	
	// Fetch comments field data
	public String get_defineSetupPage_comments_txtData() {
		return FetchText(DefineSetup_Cmnts_txtBx);
	}
	
// Click the Next button in the Define Setup page to move to Sensor Config page
	public Setup_SensorConfigPage click_defineSetupPage_nxtBtn() throws InterruptedException {
		clickOn(SensorConfiguration_Btn);
		Thread.sleep(1000);
		return new Setup_SensorConfigPage();
	}
	
	//Right click on the Asset Creation page to invoke the bottom apps bar
		public void Rt_Click_AstCreation_Buttom_AppBar() {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
		}
		
		//Verify the presence of Home button in the bottom apps bar
		public boolean check_Home_Buttom_AppBar_Presence() {
			WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
			return IsElementVisibleStatus(bottomMenu_Home_Icon);
		}
		
		//Verify the presence of Apps Help icon/button in the bottom apps bar
		public boolean check_Help_Buttom_AppBar_Presence() {
			WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
			return IsElementVisibleStatus(bottomMenu_AppHelp_Icon);
		}
		
		//Verify the presence of WndsHelp Help icon/button in the bottom apps bar
		public boolean check_WndsHelp_Buttom_AppBar_Presence() {
			WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
			return IsElementVisibleStatus(bottomMenu_WndsHelp_Icon);
		}
		
		//Verify the presence of About Help icon/button in the bottom apps bar
		public boolean check_About_Buttom_AppBar_Presence() {
			WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
			return IsElementVisibleStatus(bottomMenu_About_Icon);
		}
		
		//Click on the Home icon of the bottom apps bar to move to Main Hub page
		public MainHubPage Click_Home_Icon_AppBar() throws InterruptedException {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
			
			WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
			clickOn(bottomMenu_Home_Icon);
			Thread.sleep(1000);
			WebElement alrtmsg_Yes = driver.findElementByName("Yes");	
			clickOn(alrtmsg_Yes);
			return new MainHubPage();
		}
		
		//Click on the Help icon of the bottom apps bar to move to Main Hub page
		public void Click_Help_Icon_AppBar() throws InterruptedException {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
			
			WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
			clickOn(bottomMenu_AppHelp_Icon);
			Thread.sleep(1000);
		}
		
		//Click on the WndsHelp icon of the bottom apps bar
		public void Click_WndsHelp_Icon_AppBar() throws InterruptedException {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
			
			WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
			clickOn(bottomMenu_WndsHelp_Icon);
			Thread.sleep(1000);		
		}
		
		//Verify the presence of "how do you want to open this file" window is displaying 
				public  void check_openfile_window_Presence() throws InterruptedException {
					driver.switchTo().activeElement();
				WebElement openfile_window = driver.findElementByName("How do you want to open this file?");
				Thread.sleep(3000);		
					//return IsElementVisibleStatus(openfile_window);
					WebElement openfile_Adobe = driver.findElementByName("Adobe Reader");
					clickOn(openfile_Adobe);
					WebElement ConfirmButton_ok = driver.findElementByAccessibilityId("ConfirmButton");
					clickOn(ConfirmButton_ok);
					driver.switchTo().activeElement();
				}
		
		
		//Click on the About icon of the bottom apps bar to invoke the ABout window
		public void Click_About_Icon_AppBar() throws InterruptedException {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
			
			WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
			clickOn(bottomMenu_About_Icon);
			Thread.sleep(500);
		}
		
		//Get the setup define Help context header text on clicking Help icon of the bottom apps bar
		public String get_setupdefine_HelpMenu_HdrText() {
			WebElement setupdefine_HelpMenu = driver.findElementByAccessibilityId("helpHeader");
			return FetchText(setupdefine_HelpMenu);
		}
		
		//Verify the presence of About window on clicking the ABout icon in the bottom apps bar
		public boolean check_About_wndw_Presence() {
			WebElement About_Wndw = driver.findElementByName("About");
			return IsElementVisibleStatus(About_Wndw);
		}
		
		
		//Get the Sw version info from the About window on clicking About icon of the bottom apps bar
		public String get_SWVersion_About_Text() {
			WebElement SWVersion_About_info = driver.findElementByAccessibilityId("SoftwareVersion");
			return FetchText(SWVersion_About_info);
		}
		
	

}
