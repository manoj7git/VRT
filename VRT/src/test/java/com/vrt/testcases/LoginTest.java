package com.vrt.testcases;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.Listners.AllureReportListner;
import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;

/*import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
*/
@Listeners({AllureReportListner.class})
public class LoginTest extends BaseClass{
	
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	
	
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
	}

	// TearDown of the App
	@AfterMethod(alwaysRun=true)
	public void Teardown() {
		driver.quit();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user can log into "
			+ "the Kaye Application after installation with default Kaye/411 credentials")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if user can log into "
			//+ "the Kaye Application after installation with default Kaye/411 credentials")
	//@Story("LOGIN_001")
	public void LOGIN_001() throws InterruptedException {
		//String result = "";
		//String exception = null;
		SoftAssert sa1 = new SoftAssert();
		UserManagementPage = MainLoginPage.DefaultLogin();

		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true, "FAIL: Unable to Login"
				+" with defualt Kaye/411 login credentials");
		
		sa1.assertAll();
	}
	

	@Test(groups = {"Regression", "Sanity"},description="Verify if clicking on the "
			+ "Kaye application tab opens the Login Screen of the application")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if clicking on the "
			//+ "Kaye application tab opens the Login Screen of the application")
	//@Story("LOGIN_002")
	public void LOGIN_002() throws Exception {			
		SoftAssert sa2 = new SoftAssert();
		
		boolean state = MainLoginPage.LaunchAppLoginScreen();
				
		sa2.assertEquals(state, true, "FAIL: VRT App either didn't launch" 
		+" or Launched but not into LOGIN SCREEN");
		
		sa2.assertAll();		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify  the contents of the Kaye application Login Screen ")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify  the contents of the Kaye application Login Screen")
	//@Story("LOGIN_003")
	public void LOGIN_003() throws Exception {		
		SoftAssert sa3 = new SoftAssert();
		//Validate Product Name
		String expectedAppName = "ValProbe RT System";
		//String ActualAppName = MainLoginPage.AppName();
		sa3.assertEquals(MainLoginPage.AppName(), expectedAppName, "FAIL: Invalid Product Name displayed");
		
		// Validate presence of UserID text field
		sa3.assertEquals(MainLoginPage.UserIDFieldPresence(), true, "FAIL: No UID field present");
		
		//Validate for Password text field presence
		sa3.assertEquals(MainLoginPage.UserPWFieldPresence(), true, "FAIL: No PW field present");
		
		//Validate for Login Button presence
		sa3.assertEquals(MainLoginPage.LoginBtnPresence(), true, "FAIL: LOGIN button is not Present");
		
		// Check for CANCEL Button presence
		sa3.assertEquals(MainLoginPage.CancelBtnPresence(), true, "FAIL: CANCEL button is not displayed");
				
		sa3.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if the input data in the "
			+ "Password field is displayed as astrisk")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the input data in the " + 
			//"Password field is displayed as astrisk")
	//@Story("LOGIN_004")
	public void LOGIN_004() throws InterruptedException {
		SoftAssert sa4 = new SoftAssert();
		
		MainLoginPage.EnterUserPW("abc");
		Thread.sleep(1000);
		String actualPWTxt = MainLoginPage.GetTextUserPWField();
				
		sa4.assertNotEquals(actualPWTxt, "abc", "FAIL: The PW field data is not displayed in Astrisk");
		sa4.assertAll();
	}
	
	
	@Test(groups = {"Regression", "Sanity"}, description="Verify if user can login into "
			+ "the application by entering UserID and Password (Create 1st User) and then clicking on Login button")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if user can login into "
			//+ "the application by entering UserID and Password (Create 1st User) and then clicking on Login button")
	//@Story("LOGIN_005")
	public void LOGIN_005() throws InterruptedException {
		SoftAssert sa5 = new SoftAssert();
		
		//Login using Default Kaye/411
		UserManagementPage=MainLoginPage.DefaultLogin();
		//Create the 1st User
		MainLoginPage = UserManagementPage.FirstUserCreation("User5", "5", "Welcome1@AM", "Welcome1@AM", "System Admin Manager", "123456789", "abc@gmail.com");
		//Login with new User Credentials
		MainHubPage=MainLoginPage.Login("5", "Welcome1@AM");
				
		//Verify if New User is logged in correctly
		sa5.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Incorrect User "
				+ "is logged to the system or unable to Login in");
		
		sa5.assertAll();
	}

	
	@Test(groups = "Regression", description="Verify if the Cancel button resets "
			+ "the UserId and Password fields to Null")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the Cancel button resets "
			//+ "the UserId and Password fields to Null")
	//@Story("LOGIN_006")
	public void LOGIN_006() throws InterruptedException {
		SoftAssert sa6 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("abc");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		String UIDtxt= MainLoginPage.GetTextUserIDField();
		String PWtxt = MainLoginPage.GetTextUserPWField();
				
		sa6.assertEquals(UIDtxt, "", "FAIL: Cancel button unable to clear the UserID field");
		sa6.assertEquals(PWtxt, "", "FAIL: Cancel button unable to clear the Password field");	
		
		sa6.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user is not allowed to login with invalid credentials")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if user is not allowed to login with invalid credentials")
	//@Story("LOGIN_007")
	public void LOGIN_007() throws InterruptedException {
		SoftAssert sa7 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		Thread.sleep(1000);
		
		sa7.assertEquals(MainLoginPage.InvalidLoginAlertmsgPresence(), true, "FAIL: App allowing"
				+" to login with INVALID LOGIN credentials");
		sa7.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user is not allowed to "
			+ "login if the UserId or Password field is left blank")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if user is not allowed to "
			//+ "login if the UserId or Password field is left blank")
	//@Story("LOGIN_008")
	public void LOGIN_008() throws InterruptedException {
		SoftAssert sa8 = new SoftAssert();
		
		sa8.assertEquals(MainLoginPage.LoginBtnEnablestatus(), false, "FAIL: Login button enabled"
				+ " without any UID/PW entry");
		
		sa8.assertAll();
	}
		
	
	@Test(groups = {"Regression"}, description="Verify if the application closes on three unsuccessful login attempts")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the application closes on three unsuccessful login attempts")
	//@Story("LOGIN_009")
	public void LOGIN_009() throws InterruptedException {
		SoftAssert sa9 = new SoftAssert();
		
		MainLoginPage.EnterUserID("1");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("2");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("3");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		
		sa9.assertEquals(MainLoginPage.LaunchAppLoginScreen(), false, "FAIL: App does not SHUTDOWN "
				+"on entering 3 times INVALID User Credentials");
		sa9.assertAll();
	}

	
	@Test(groups = {"Regression"}, description="Verify if the first created admin user "
			+ "is not allowed to change his password during first login instance after user creation")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the first created admin user "
			//+ "is not allowed to change his password during first login instance after user creation")
	//@Story("LOGIN_010")
	public void LOGIN_010() throws InterruptedException {
		SoftAssert sa10 = new SoftAssert();
		
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("Welcome1@AM");
		
		sa10.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: The 1st User is "
		+"allowed to Change its PW on 1st time Login");
		sa10.assertAll();		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if the Change Password tickbox "
			+ "is in enabled state during consecutive logins by the first admin user")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the Change Password tickbox "
			//+ "is in enabled state during consecutive logins by the first admin user")
	//@Story("LOGIN_012")
	public void LOGIN_012() throws InterruptedException {
		SoftAssert sa11 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome1@AM");
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("Welcome1@AM");
		
		sa11.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: The 1st User is "
		+"NOT allowed to Change its PW with Change PW option in disbaled state");
		sa11.assertAll();	
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if checking the "
			+ "Change Password tickbox allows the user to change his password")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if checking the "
			//+ "Change Password tickbox allows the user to change his password")
	//@Story("LOGIN_013")
	public void LOGIN_013() throws InterruptedException {
		SoftAssert sa12 = new SoftAssert();
		
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("Welcome1@AM");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();

		sa12.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: New PW field "
				+ "is not enabled/displayed to Change PW");
		sa12.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if unchecking the "
			+ "Change Password tickbox restricts the user from changing his password")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if unchecking the "
			//+ "Change Password tickbox restricts the user from changing his password")
	//@Story("LOGIN_014")
	public void LOGIN_014() throws InterruptedException {
		SoftAssert sa13 = new SoftAssert();
		
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("Welcome1@AM");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		
		
		try {
			MainLoginPage.ClickChangePWCheckbox();
			
			sa13.assertEquals(MainLoginPage.NewPWFieldPresence(), false, "FAIL: New PW field is enabled/displayed to Change PW"
					+ " even if the Change PW checkbox is unchecked");
			sa13.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user can change "
			+ "the password by entering new password and clicking on the OK button")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if user can change "
			//+ "the password by entering new password and clicking on the OK button")
	//@Story("LOGIN_015")
	public void LOGIN_015() throws InterruptedException {
		SoftAssert sa14 = new SoftAssert();
		
		MainHubPage=MainLoginPage.ChangeNewPW("5", "Welcome1@AM", "Welcome2@AM");
		
		sa14.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Password did not change for the User");
		sa14.assertAll();
	}
		
	
	@Test(groups = {"Regression"}, description="Verify if clicking on the Cancel button"
			+ " in the Change password field restores the previous password")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if clicking on the Cancel button"
			//+ " in the Change password field restores the previous password")
	//@Story("LOGIN_016")
	public void LOGIN_016() throws InterruptedException {
		SoftAssert sa15 = new SoftAssert();
		
		MainLoginPage.LoginEntry("5", "Welcome2@AM");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.enterNewPW("Welcome3@AM");
		MainLoginPage.enterConfNewPW("Welcome3@AM");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");

		sa15.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Cancel button"
				+ " at New PW change did not work as intended");
		sa15.assertAll();
	}
	
	
	//A Sys Admin User created
	@Test(groups = {"Regression", "Sanity"}, description="Verify if subsequent users created "
			+ "are forced to change their password during first login instance")
	//@Severity(SeverityLevel.CRITICAL)
	//@Description("Verify if subsequent users created "
			//+ "are forced to change their password during first login instance")
	//@Story("LOGIN_017-18")
	public void LOGIN_017_018() throws InterruptedException {
		SoftAssert sa16 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateAdminUser("5", "Welcome2@AM", "User1", "1", "Welcome2@AM", "SysAdmin", "123456789", "user1@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("1", "Welcome2@AM");
		MainLoginPage.ClickLoginBtn();
		
		sa16.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: A New User created"
				+ " is not forced to change PW on logging in for 1st time with ChangePWCheck box in Enabled state and in Unchecked state");
		sa16.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: A New User created" 
				+ " is not forced to change PW on logging in for 1st time with New PW field in Disabled/Invisible state");
				
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome1@AM");
		MainHubPage.UserSignOut();
		sa16.assertAll();
	}
	
	//A Sys Supervisor User created	
	@Test(groups = {"Regression"}, description="Verify if the Change Password tickbox"
			+ " is in enabled state during furthur login attempts by the subsequent users")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if the Change Password tickbox"
			//+ " is in enabled state during furthur login attempts by the subsequent users")
	//@Story("LOGIN_019")
	public void LOGIN_019() throws InterruptedException {
		SoftAssert sa17 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateSupervisorUser("5", "Welcome2@AM", "User2", "2", "Welcome1@AM", "SysSupervisor", "123456789", "user2@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("2", "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM");
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.LoginEntry("2", "Welcome2@AM");

		sa17.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: ChangePWCheck box "
				+ "appear to be in Disable state");
		sa17.assertAll();	
	}
	
	//A Sys Operator User created	
	@Test(groups = {"Regression"}, description="Verify if a user"
			+ " is forced to change his password while login, if his password has been changed by the admin user")
	//@Severity(SeverityLevel.BLOCKER)
	//@Description("Verify if a user"
			//+ " is forced to change his password while login, if his password has been changed by the admin user")
	//@Story("LOGIN_020")
	public void LOGIN_020() throws InterruptedException {
		SoftAssert sa18 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateOperatorUser("5", "Welcome2@AM", "User8", "8", "Welcome1@AM", "SysOperator", "123456789", "user8@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("8", "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM"); //User8 forced to Rest PW
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.clickAnyUserinUserList("User8");
		UserManagementPage.enterNewUserPW("Welcome3@AM");
		UserManagementPage.enterNewUserConfPW("Welcome3@AM");
		UserManagementPage.ClickTitlefield();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup("5", "Welcome2@AM");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("8", "Welcome3@AM");
		MainLoginPage.ClickLoginBtn();

		sa18.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: User is NOT "
				+ "forced to change his password while login, if his password has been changed by the admin user");
		sa18.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: User is NOT " 
				+ " forced to change his password while login with New PW field in Disabled/Invisible state");
		
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome8@AM");
		MainHubPage.UserSignOut();
		sa18.assertAll();
	}		

}
