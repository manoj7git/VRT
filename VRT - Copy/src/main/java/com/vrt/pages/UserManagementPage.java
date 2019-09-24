/**
 * @author ruchika.behura
 *
 */

package com.vrt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class UserManagementPage extends BaseClass{
	
	// UserManagement Page Element definition
	WebElement UMHeaderText = driver.findElementByName("User Management");
	WebElement NewUserUMBtn = driver.findElementByAccessibilityId("NewUserButton");
	WebElement UNUMField = driver.findElementByAccessibilityId("NameTextBox");
	WebElement UserIDUMField = driver.findElementByAccessibilityId("UserIDTextBox");
	WebElement PWUMField = driver.findElementByAccessibilityId("PasswordTextBox");
	WebElement ConPWUMField = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
	WebElement TitleUMField = driver.findElementByAccessibilityId("EditableTextBox");
	WebElement UserTypeUMDropDown = driver.findElementByAccessibilityId("UserTypeComboBox");	
	WebElement PhoneUMField = driver.findElementByAccessibilityId("PhoneTextBox");
	WebElement EmailUMField = driver.findElementByAccessibilityId("EmailTextBox");
	WebElement DeleteUMBtn = driver.findElementByName("Delete");
	
	WebElement UMAssetPriv = driver.findElementByAccessibilityId("AssetsPrivlegesCheckBox");
	WebElement UMSetupPriv = driver.findElementByAccessibilityId("SetupCreationCheckBox");
	WebElement UMQualPriv = driver.findElementByAccessibilityId("QualificationExecutionCheckBox");
	WebElement UMCalPriv = driver.findElementByAccessibilityId("CalibrationExecutionCheckBox");	
	
	WebElement SaveUMBtn = driver.findElementByName("Save");
	WebElement CancelUMBtn = driver.findElementByName("Cancel");
	

	/*----------------------
	Methods of UserManagement Page
	------------------------*/
	// Check if UserManagement page is displayed
	public boolean IsUMscreenDisplayed() {
		return IsElementEnabledStatus(UMHeaderText);
	}	
	
	//Click NewUser button
	public void ClickNewUser() {
		clickOn(NewUserUMBtn);
	}
	
	//Enter User Name text
	public void enterNewUserName(String NewUN) {
		ClearText(UNUMField);
		enterText(UNUMField, NewUN);
	}
	
	//Fetch User Name text
	public String GetUserNametext() {
		return FetchText(UNUMField);
	}
	
	//Enter User ID text
	public void enterNewUserID(String NewUID) {
		ClearText(UserIDUMField);
		enterText(UserIDUMField, NewUID);
	}
	
	//Enter PW text
	public void enterNewUserPW(String NewPW) {
		ClearText(PWUMField);
		enterText(PWUMField, NewPW);
	}
	
	//Enter ConfirmPW text
	public void enterNewUserConfPW(String NewCPW) {
		ClearText(ConPWUMField);
		enterText(ConPWUMField, NewCPW);		
	}
	
	//Enter Title text
	public void enterNewUserTitle(String Title) {
		ClearText(TitleUMField);
		enterText(TitleUMField, Title);
	}
	
	//Enter Title text
	public void ClickTitlefield() {
		clickOn(TitleUMField);
	}
	
	//Click the UserType Dropdown List
	public void ClickUserTypeDropDown() throws InterruptedException 
	{
		clickOn(UserTypeUMDropDown);
		Thread.sleep(1000);
	}
	
	//Select Sys Admin from the UserType drop-down list
	public void SelectAdministrator() 
	{
		WebElement UMAdministrator = driver.findElementByName("System Administrator");
		clickOn(UMAdministrator);
	}
	
	//Select Supervisor from the UserType drop-down list
	public void SelectSupervisor() 
	{
		WebElement UMSupervisor = driver.findElementByName("Supervisor");
		clickOn(UMSupervisor);
	}
	
	//Select Operator from the UserType drop-down list
	public void SelectOperator() 
	{
		WebElement UMOperator = driver.findElementByName("Operator");
		clickOn(UMOperator);
	}	
	
	//Enter Phone text
	public void enterNewUserPhone(String Phone) {
		ClearText(PhoneUMField);
		enterText(PhoneUMField, Phone);
	}
	
	//Enter email text
	public void enterNewUserEmail(String email) {
		ClearText(EmailUMField);
		enterText(EmailUMField, email);
	}
	
	//Click Save button
	public void ClickNewUserSaveButton() throws InterruptedException {
		clickOn(SaveUMBtn);
		Thread.sleep(1000);
	}
	
	//Click Back Button to move to AdminTile
	public MainHubPage ClickBackButn() {
		WebElement BackUMBtn = driver.findElementByAccessibilityId("BackButton");
		clickOn(BackUMBtn);
		return new MainHubPage();
	}
	
	//Create First User of the System
	public LoginPage FirstUserCreation(String NewUN,String NewUID,String NewPW,String NewCPW,String Title,String Phone,String email) throws InterruptedException {
		ClickNewUser();	
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewCPW);
		enterNewUserTitle(Title);
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();
				
		return new LoginPage();		
	}
	
	//Create a New Admin User
	public void CreateAdminUser(String UID, String PW, String NewUN,String NewUID,String NewPW,String Title,String Phone,String email) throws InterruptedException {
		ClickNewUser();	
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);		
		ClickUserTypeDropDown();
		SelectAdministrator();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();
		
		UserLoginPopup(UID, PW);
		
		WebElement SaveAlertmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		if (IsElementVisibleStatus(SaveAlertmsg)) {
			String NewUsertext = SaveAlertmsg.getText();
			if (NewUsertext.contains(NewUN)) {
				System.out.println("New Admin User: "+NewUN+" is created successfuly");
			}
		}	
	}

	
	//Create a New Supervisor User
	public void CreateSupervisorUser(String UID, String PW, String NewUN,String NewUID,String NewPW,String Title,String Phone,String email) throws InterruptedException {
		ClickNewUser();	
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);
		ClickUserTypeDropDown();
		SelectSupervisor();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();
		
		UserLoginPopup(UID, PW);
		
		WebElement SaveAlertmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		if (IsElementVisibleStatus(SaveAlertmsg)) {
			String NewUsertext = SaveAlertmsg.getText();
			if (NewUsertext.contains(NewUN)) {
				System.out.println("New Supervisor User "+NewUN+" is created successfuly");
			}
		}	
	}

	
	//Create a New Operator User
	public void CreateOperatorUser(String UID, String PW, String NewUN, String NewUID, String NewPW, String Title, String Phone,
			String email) throws InterruptedException 
	{
		ClickNewUser();	
		enterNewUserName(NewUN);
		enterNewUserID(NewUID);
		enterNewUserPW(NewPW);
		enterNewUserConfPW(NewPW);
		enterNewUserTitle(Title);
		ClickUserTypeDropDown();
		SelectOperator();
		enterNewUserPhone(Phone);
		enterNewUserEmail(email);
		ClickNewUserSaveButton();
		
		UserLoginPopup(UID, PW);

		WebElement SaveAlertmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		if (IsElementVisibleStatus(SaveAlertmsg)) {
			String NewUsertext = SaveAlertmsg.getText();
			if (NewUsertext.contains(NewUN)) {
				System.out.println("New Operator User " + NewUN + " is created successfuly");
			}
		}
	}	
		
	// Select/Click any User in the UserList Panel
	public void clickAnyUserinUserList(String UN) throws InterruptedException {
		List<WebElement> Userslist = driver.findElementByAccessibilityId("UsersListBox")
				.findElements(By.className("ListBoxItem"));
		//System.out.println("Total Number of Users created: " + Userslist.size());
		Userslist.get(0).click();
		
		for (int i = 0; i < Userslist.size(); i++) {
			
			String UNtext1 = GetUserNametext();
			//System.out.println(UNtext1);
			if (UNtext1.equalsIgnoreCase(UN)) {
				clickOn(UNUMField);
				break;
			} else {
				Actions ac = new Actions(driver);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			}		
		}
	}
	
	//check/select Create/Edit Asset Privilege checkbox
	public void clickPrivCreateEditAsset() throws InterruptedException {
		clickOn(UMAssetPriv);
		Thread.sleep(1000);
	}
	
	//Verify if Create/Edit Asset Privilege checked/selected or not
	public boolean PrivCreateEditAssetgstatus() {
		return checkboxSelectStatus(UMAssetPriv);
	}
	
	//check/select Run Qual Privilege checkbox
	public void clickPrivRunQual() throws InterruptedException {
		clickOn(UMQualPriv);
		Thread.sleep(1000);
	}
	
	//Verify if Run Qual Privilege checked/selected or not
	public boolean PrivRunQualstatus() {
		return checkboxSelectStatus(UMQualPriv);
	}
	
	//check/select Create/Edit Setup Privilege checkbox
	public void clickPrivCreateEditSetup() throws InterruptedException {
		clickOn(UMSetupPriv);
		Thread.sleep(1000);
	}
	
	//Verify if Create/Edit Setup Privilege checked/selected or not
	public boolean PrivCreateEditSetupstatus() {
		return checkboxSelectStatus(UMSetupPriv);
	}
	
	//check/select Run Cal Privilege checkbox
	public void clickPrivRunCal() throws InterruptedException {
		clickOn(UMCalPriv);
		Thread.sleep(1000);
	}
	
	//Verify if Run Cal Privilege checked/selected or not
	public boolean PrivRunCalstatus() {
		return checkboxSelectStatus(UMCalPriv);
	}
	

}
