package com.vrt.pages;



import org.openqa.selenium.WebElement;
import com.vrt.base.BaseClass;

import io.qameta.allure.Step;

public class LoginPage extends BaseClass {

	//Main Login Page Element definition	
	WebElement ProductName = driver.findElementByName("ValProbe RT System");	
	WebElement MainLoginUID = driver.findElementByAccessibilityId("LoginIDTextBox");
	WebElement MainLoginPW = driver.findElementByAccessibilityId("PasswordTextBox");
	WebElement MainLoginBtn = driver.findElementByAccessibilityId("LoginButton");
	WebElement MainLoginCnclBtn = driver.findElementByAccessibilityId("CancelButton");

	
	
	/*----------------------
	Methods of Main Login Page
	------------------------*/
	
	@Step("Launch of Main App with Login page...")
	public boolean LaunchAppLoginScreen() 
	{		
		return IsElementEnabledStatus(MainLoginUID);		 
	}
	
	@Step("Check the contents of the Main Login screen")
	public String AppName() 
	{
		return FetchText(ProductName);
	}
	
	@Step("Verify the User ID field presence...")
	public boolean UserIDFieldPresence() 
	{
		return IsElementEnabledStatus(MainLoginUID);
	}
	
	@Step("Enter User ID: {0}")
	public void EnterUserID(String UID) 
	{
		ClearText(MainLoginUID);
		enterText(MainLoginUID, UID);
	}
	
	@Step("Get the User ID field data...")
	public String GetTextUserIDField() 
	{
		return FetchText(MainLoginUID);
	}
	
	@Step("Verify the User PW field presence...")
	public boolean UserPWFieldPresence() 
	{
		return IsElementEnabledStatus(MainLoginPW);
	}
	
	@Step("Enter User PW: {0}")
	public void EnterUserPW(String PW) 
	{
		ClearText(MainLoginPW);
		enterText(MainLoginPW, PW);
	}
	
	@Step("Get the User PW field data...")
	public String GetTextUserPWField() 
	{
		return FetchText(MainLoginPW);
	}
	
	@Step("Verify the change User PW field active or not...")
	public boolean ChangePWCheckBoxEnableStatus() 
	{
		WebElement MainLoginChgPWChckBx = driver.findElementByName("Change Password");
		return IsElementEnabledStatus(MainLoginChgPWChckBx);
	}
	
	@Step("Click Change PW Checkbox...")
	public void ClickChangePWCheckbox() throws InterruptedException 
	{
		WebElement MainLoginChgPWChckBx = driver.findElementByName("Change Password");
		clickOn(MainLoginChgPWChckBx);
		Thread.sleep(1000);
	}
	
	@Step("Verify the LoginBtnEnablestatus...")
	public boolean LoginBtnEnablestatus() 
	{
		return IsElementEnabledStatus(MainLoginBtn);
	}	
	
	@Step("Verify the LoginBtnPresence...")
	public boolean LoginBtnPresence() 
	{
		return MainLoginBtn.isDisplayed();
	}
	
	@Step("Click Login Btn...")
	public void ClickLoginBtn() throws InterruptedException 
	{
		clickOn(MainLoginBtn);
		Thread.sleep(1000);
	}
	
	@Step("Verify the InvalidLoginAlertmsgPresence...")
	public boolean InvalidLoginAlertmsgPresence() 
	{
		WebElement InvalidLoginMsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return IsElementEnabledStatus(InvalidLoginMsg);
	}
	
	@Step("Verify the NewPWFieldPresence...")
	public boolean NewPWFieldPresence() 
	{
		WebElement MainLoginNewPWfield = driver.findElementByAccessibilityId("NewPasswordTextBox");
		return IsElementVisibleStatus(MainLoginNewPWfield);
	}
	
	@Step("Enter User New PW: {0}")
	public void enterNewPW(String NewPW) 
	{
		WebElement MainLoginNewPWfield = driver.findElementByAccessibilityId("NewPasswordTextBox");
		enterText(MainLoginNewPWfield, NewPW);
	}
	
	@Step("Enter User Confirm New PW: {0}")
	public void enterConfNewPW(String NewCPW) 
	{
		WebElement MainLoginConfNewPW = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
		enterText(MainLoginConfNewPW, NewCPW);
	}
	
	@Step("Click New PW Save Btn...")
	public MainHubPage ClickNewPWSaveBtn() throws InterruptedException 
	{
		WebElement MainLoginNewPWSaveBtn = driver.findElementByName("OK");
		clickOn(MainLoginNewPWSaveBtn);
		Thread.sleep(1000);
		
		return new MainHubPage();
	}
	
	@Step("Verify the CancelBtnPresence...")
	public boolean CancelBtnPresence() 
	{
		return IsElementEnabledStatus(MainLoginCnclBtn);
	}
	
	@Step("Click Cancel Btn...")
	public void ClickCancelBtn() 
	{
		clickOn(MainLoginCnclBtn);
	}
	
	@Step("ChangeNewPW Operation...")
	public MainHubPage ChangeNewPW(String UID, String PW, String NPW) throws InterruptedException 
	{
		EnterUserID(UID);
		EnterUserPW(PW);
		ClickChangePWCheckbox();
		ClickLoginBtn();
		Thread.sleep(1000);
		
		//WebElement MainLoginNewPWfield = driver.findElementByAccessibilityId("NewPasswordTextBox");
		//WebElement MainLoginConfNewPW = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
		enterNewPW(NPW);
		enterConfNewPW(NPW);
		ClickNewPWSaveBtn();
		
		return new MainHubPage();
	}
	
	@Step("ChangeNewPWwithoutPWCheckBox Operation...")
	public MainHubPage ChangeNewPWwithoutPWCheckBox(String UID, String PW, String NPW) throws InterruptedException 
	{
		EnterUserID(UID);
		EnterUserPW(PW);
		//ClickChangePWCheckbox();
		ClickLoginBtn();
		Thread.sleep(1000);

		enterNewPW(NPW);
		enterConfNewPW(NPW);
		ClickNewPWSaveBtn();
		
		return new MainHubPage();
	}
	
	@Step("Login method for User OTHER THAN Kaye/411...")
	public MainHubPage Login(String UID, String PW) throws InterruptedException 
	{		
		EnterUserID(UID);
		EnterUserPW(PW);
		ClickLoginBtn();
		Thread.sleep(1000);	
		
		return new MainHubPage();
	}
	
	
	@Step("Login method using Kaye/411")
	public UserManagementPage DefaultLogin() throws InterruptedException 
	{	
		MainLoginUID.sendKeys("Kaye");
		MainLoginPW.sendKeys("411");
		MainLoginBtn.click();
		Thread.sleep(2000);	
		
		return new UserManagementPage();
	}
	
	@Step("Login method for User OTHER THAN Kaye/411...")
	public void LoginEntry(String UID, String PW) throws InterruptedException 
	{		
		EnterUserID(UID);
		EnterUserPW(PW);			
	}
	
	@Step("EnterNewPWtext peration...")
	public MainHubPage EnterNewPWtext(String NPW) throws InterruptedException 
	{		
		enterNewPW(NPW);
		enterConfNewPW(NPW);		
		ClickNewPWSaveBtn();
		
		return new MainHubPage();
	}	

}
