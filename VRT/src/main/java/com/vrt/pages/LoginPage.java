package com.vrt.pages;



import org.openqa.selenium.WebElement;
import com.vrt.base.BaseClass;

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
	//Check Launch of Main App with Login page
	public boolean LaunchAppLoginScreen() 
	{		
		return IsElementEnabledStatus(MainLoginUID);		 
	}
	
	//Check the contents of the Main Login screen
	public String AppName() 
	{
		return FetchText(ProductName);
	}
	
	public boolean UserIDFieldPresence() 
	{
		return IsElementEnabledStatus(MainLoginUID);
	}
	
	public void EnterUserID(String UID) 
	{
		ClearText(MainLoginUID);
		enterText(MainLoginUID, UID);
	}
	
	public String GetTextUserIDField() 
	{
		return FetchText(MainLoginUID);
	}
	
	public boolean UserPWFieldPresence() 
	{
		return IsElementEnabledStatus(MainLoginPW);
	}
	
	public void EnterUserPW(String PW) 
	{
		ClearText(MainLoginPW);
		enterText(MainLoginPW, PW);
	}
	
	public String GetTextUserPWField() 
	{
		return FetchText(MainLoginPW);
	}
	
	public boolean ChangePWCheckBoxEnableStatus() 
	{
		WebElement MainLoginChgPWChckBx = driver.findElementByName("Change Password");
		return IsElementEnabledStatus(MainLoginChgPWChckBx);
	}
	
	public void ClickChangePWCheckbox() throws InterruptedException 
	{
		WebElement MainLoginChgPWChckBx = driver.findElementByName("Change Password");
		clickOn(MainLoginChgPWChckBx);
		Thread.sleep(1000);
	}
	
	public boolean LoginBtnEnablestatus() 
	{
		return IsElementEnabledStatus(MainLoginBtn);
	}	
	
	public boolean LoginBtnPresence() 
	{
		return MainLoginBtn.isDisplayed();
	}
	
	public void ClickLoginBtn() throws InterruptedException 
	{
		clickOn(MainLoginBtn);
		Thread.sleep(1000);
	}
	
	public boolean InvalidLoginAlertmsgPresence() 
	{
		WebElement InvalidLoginMsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return IsElementEnabledStatus(InvalidLoginMsg);
	}
	
	public boolean NewPWFieldPresence() 
	{
		WebElement MainLoginNewPWfield = driver.findElementByAccessibilityId("NewPasswordTextBox");
		return IsElementVisibleStatus(MainLoginNewPWfield);
	}
	
	public void enterNewPW(String NewPW) 
	{
		WebElement MainLoginNewPWfield = driver.findElementByAccessibilityId("NewPasswordTextBox");
		enterText(MainLoginNewPWfield, NewPW);
	}
	
	public void enterConfNewPW(String NewCPW) 
	{
		WebElement MainLoginConfNewPW = driver.findElementByAccessibilityId("ConfirmPasswordTextBox");
		enterText(MainLoginConfNewPW, NewCPW);
	}
	
	public MainHubPage ClickNewPWSaveBtn() throws InterruptedException 
	{
		WebElement MainLoginNewPWSaveBtn = driver.findElementByName("OK");
		clickOn(MainLoginNewPWSaveBtn);
		Thread.sleep(1000);
		
		return new MainHubPage();
	}
			
	public boolean CancelBtnPresence() 
	{
		return IsElementEnabledStatus(MainLoginCnclBtn);
	}
	
	public void ClickCancelBtn() 
	{
		clickOn(MainLoginCnclBtn);
	}
	
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
	
	//Login method for User OTHER THAN Kaye/411
	public MainHubPage Login(String UID, String PW) throws InterruptedException 
	{		
		EnterUserID(UID);
		EnterUserPW(PW);
		ClickLoginBtn();
		Thread.sleep(1000);	
		
		return new MainHubPage();
	}
	
	
	//Login method using Kaye/411
	public UserManagementPage DefaultLogin() throws InterruptedException 
	{	
		MainLoginUID.sendKeys("Kaye");
		MainLoginPW.sendKeys("411");
		MainLoginBtn.click();
		Thread.sleep(2000);	
		
		return new UserManagementPage();
	}
	
	//Login method for User OTHER THAN Kaye/411
	public void LoginEntry(String UID, String PW) throws InterruptedException 
	{		
		EnterUserID(UID);
		EnterUserPW(PW);			
	}
	
	
	public MainHubPage EnterNewPWtext(String NPW) throws InterruptedException 
	{		
		enterNewPW(NPW);
		enterConfNewPW(NPW);		
		ClickNewPWSaveBtn();
		
		return new MainHubPage();
	}	

}
