package com.vrt.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class LoginTest_TestLink extends BaseClass{
	
	//public static String DEV_KEY = "29cfec13796bda5ba7ff0c07231cbd35"; //Your API Key	
	public static String DEV_KEY = "79242ea137d04ec6332beb734592d305"; //Vittal API Key
	public static String SERVER_URL = "http://10.17.17.216/testlink/lib/api/xmlrpc/v1/xmlrpc.php"; //your testlink server url
	public static String PROJECT_NAME = "SampleProjects"; 
	public static String PLAN_NAME = "Sanity test1";
	public static String BUILD_NAME = "Build1.0.0.26";
	public static String result = "";
	public static String exception = null;
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
	
	
	@Test(groups = "Login_Screen", description="Verify if user can log into "
			+ "the Kaye Application after installation with default Kaye/411 credentials")
	public void LOGIN_001() throws InterruptedException, TestLinkAPIException {
		//String result = "";
		//String exception = null;
		SoftAssert sa1 = new SoftAssert();
		UserManagementPage = MainLoginPage.DefaultLogin();
		
		try {
			if (UserManagementPage.IsUMscreenDisplayed()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-2", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-2", exception, result);
		}
		
		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true, "FAIL: Unable to Login"
				+" with defualt Kaye/411 login credentials");
		
		sa1.assertAll();
	}
	

	@Test(groups = "Login_Screen",description="Verify if clicking on the "
			+ "Kaye application tab opens the Login Screen of the application")
	public void LOGIN_002() throws Exception {			
		SoftAssert sa2 = new SoftAssert();
		
		boolean state = MainLoginPage.LaunchAppLoginScreen();
		
		try {
			if (MainLoginPage.LaunchAppLoginScreen()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-3", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-3", exception, result);
		}
		
		sa2.assertEquals(state, true, "FAIL: VRT App either didn't launch" 
		+" or Launched but not into LOGIN SCREEN");
		
		sa2.assertAll();		
	}
	
	
	@Test(groups = "Login_Screen", description="Verify  the contents of the Kaye application Login Screen ")
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
		
		try {
			if (MainLoginPage.AppName().contains(expectedAppName) && MainLoginPage.UserIDFieldPresence() 
					&& MainLoginPage.UserPWFieldPresence() && MainLoginPage.LoginBtnPresence() && MainLoginPage.CancelBtnPresence()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-4", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-4", exception, result);
		}
		
		sa3.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if the input data in the "
			+ "Password field is displayed as astrisk")
	public void LOGIN_004() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa4 = new SoftAssert();
		
		MainLoginPage.EnterUserPW("abc");
		Thread.sleep(1000);
		String actualPWTxt = MainLoginPage.GetTextUserPWField();
		
		try {
			if (!actualPWTxt.contains("abc")) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-5", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-5", exception, result);
		}
		
		sa4.assertNotEquals(actualPWTxt, "abc", "FAIL: The PW field data is not displayed in Astrisk");
		sa4.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if user can login into "
			+ "the application by entering UserID and Password (Create 1st User) and then clicking on Login button")
	public void LOGIN_005() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa5 = new SoftAssert();
		
		//Login using Default Kaye/411
		UserManagementPage=MainLoginPage.DefaultLogin();
		//Create the 1st User
		MainLoginPage = UserManagementPage.FirstUserCreation("User5", "5", "aaaaaa", "aaaaaa", "System Admin Manager", "123456789", "abc@gmail.com");
		//Login with new User Credentials
		MainHubPage=MainLoginPage.Login("5", "aaaaaa");
		
		try {
			if (MainHubPage.LoggedinUserName().contains("User5")) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-6", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-6", exception, result);
		}
		
		//Verify if New User is logged in correctly
		sa5.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Incorrect User "
				+ "is logged to the system or unable to Login in");
		
		sa5.assertAll();
	}

	
	@Test(groups = "Login_Screen", description="Verify if the Cancel button resets "
			+ "the UserId and Password fields to Null")
	public void LOGIN_006() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa6 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("abc");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		String UIDtxt= MainLoginPage.GetTextUserIDField();
		String PWtxt = MainLoginPage.GetTextUserPWField();
		
		try {
			if (UIDtxt.contains("") && PWtxt.contains("")) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-7", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-7", exception, result);
		}
		
		sa6.assertEquals(UIDtxt, "", "FAIL: Cancel button unable to clear the UserID field");
		sa6.assertEquals(PWtxt, "", "FAIL: Cancel button unable to clear the Password field");	
		
		sa6.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if user is not allowed to login with invalid credentials")
	public void LOGIN_007() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa7 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		Thread.sleep(1000);
		
		try {
			if (MainLoginPage.InvalidLoginAlertmsgPresence()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-8", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-8", exception, result);
		}
		
		sa7.assertEquals(MainLoginPage.InvalidLoginAlertmsgPresence(), true, "FAIL: App allowing"
				+" to login with INVALID LOGIN credentials");
		sa7.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if user is not allowed to "
			+ "login if the UserId or Password field is left blank")
	public void LOGIN_008() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa8 = new SoftAssert();
		
		try {
			if (!MainLoginPage.LoginBtnEnablestatus()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-9", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-9", exception, result);
		}
		
		sa8.assertEquals(MainLoginPage.LoginBtnEnablestatus(), false, "FAIL: Login button enabled"
				+ " without any UID/PW entry");
		
		sa8.assertAll();
	}
		
	
	@Test(groups = "Login_Screen", description="Verify if the application closes on three unsuccessful login attempts")
	public void LOGIN_009() throws InterruptedException, TestLinkAPIException {
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
		
		try {
			if (!MainLoginPage.LaunchAppLoginScreen()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-10", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-10", exception, result);
		}
		
		sa9.assertEquals(MainLoginPage.LaunchAppLoginScreen(), false, "FAIL: App does not SHUTDOWN "
				+"on entering 3 times INVALID User Credentials");
		sa9.assertAll();
	}

	
	@Test(groups = "Login_Screen", description="Verify if the first created admin user "
			+ "is not allowed to change his password during first login instance after user creation")
	public void LOGIN_010() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa10 = new SoftAssert();
		
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("aaaaaa");
		
		try {
			if (!MainLoginPage.ChangePWCheckBoxEnableStatus()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-11", null, result);
			}else {
				result = TestLinkAPIResults.TEST_FAILED;				
				updateTestLinkResult("TC00-11", exception, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-11", exception, result);
		}
		
		sa10.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: The 1st User is "
		+"allowed to Change its PW on 1st time Login");
		sa10.assertAll();		
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if the Change Password tickbox "
			+ "is in enabled state during consecutive logins by the first admin user")
	public void LOGIN_012() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa11 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "aaaaaa");
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("aaaaaa");
		
		try {
			if (MainLoginPage.ChangePWCheckBoxEnableStatus()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-13", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-13", exception, result);
		}
		
		sa11.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: The 1st User is "
		+"NOT allowed to Change its PW with Change PW option in disbaled state");
		sa11.assertAll();	
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if checking the "
			+ "Change Password tickbox allows the user to change his password")
	public void LOGIN_013() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa12 = new SoftAssert();
		
		MainLoginPage.EnterUserID("5");
		MainLoginPage.EnterUserPW("aaaaaa");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		
		try {
			if (MainLoginPage.NewPWFieldPresence()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-14", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-14", exception, result);
		}
		
		sa12.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: New PW field "
				+ "is not enabled/displayed to Change PW");
		sa12.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if unchecking the "
			+ "Change Password tickbox restricts the user from changing his password")
	public void LOGIN_014() throws InterruptedException, TestLinkAPIException {
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
			
			if (MainLoginPage.NewPWFieldPresence()) {
				result = TestLinkAPIResults.TEST_FAILED;
				updateTestLinkResult("TC00-15", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_PASSED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-15", exception, result);
		}		
		
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if user can change "
			+ "the password by entering new password and clicking on the OK button")
	public void LOGIN_015() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa14 = new SoftAssert();
		
		MainHubPage=MainLoginPage.ChangeNewPW("5", "aaaaaa", "Welcome2@AM");
		
		try {
			if (MainHubPage.LoggedinUserName().contains("User5")) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-16", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-16", exception, result);
		}	
		
		sa14.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Password did not change for the User");
		sa14.assertAll();
	}
		
	
	@Test(groups = "Login_Screen", description="Verify if clicking on the Cancel button"
			+ " in the Change password field restores the previous password")
	public void LOGIN_016() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa15 = new SoftAssert();
		
		MainLoginPage.LoginEntry("5", "Welcome2@AM");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.enterNewPW("Welcome1@AM");
		MainLoginPage.enterConfNewPW("Welcome1@AM");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		
		try {
			if (MainHubPage.LoggedinUserName().contains("User5")) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-17", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-17", exception, result);
		}
		
		sa15.assertEquals(MainHubPage.LoggedinUserName(), "User5", "FAIL: Cancel button"
				+ " at New PW change did not work as intended");
		sa15.assertAll();
	}
	
	
	@Test(groups = "Login_Screen", description="Verify if subsequent users created "
			+ "are forced to change their password during first login instance")
	public void LOGIN_017_018() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa16 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile();
		
		UserManagementPage.CreateAdminUser("5", "Welcome2@AM", "User1", "1", "aaaaaa", "SysAdmin", "123456789", "user1@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("1", "aaaaaa");
		MainLoginPage.ClickLoginBtn();
		
		try {
			if (!MainLoginPage.ChangePWCheckBoxEnableStatus() && MainLoginPage.NewPWFieldPresence()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-18", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-18", exception, result);
		}
		
		sa16.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: A New User created"
				+ " is not forced to change PW on logging in for 1st time with ChangePWCheck box in Enabled state and in Unchecked state");
		sa16.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: A New User created" 
				+ " is not forced to change PW on logging in for 1st time with New PW field in Disabled/Invisible state");
				
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome1@AM");
		MainHubPage.UserSignOut();
		sa16.assertAll();
	}
	
		
	@Test(groups = "Login_Screen", description="Verify if the Change Password tickbox"
			+ " is in enabled state during furthur login attempts by the subsequent users")
	public void LOGIN_019() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa17 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile();
		
		UserManagementPage.CreateSupervisorUser("5", "Welcome2@AM", "User2", "2", "aaaaaa", "SysSupervisor", "123456789", "user2@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("2", "aaaaaa");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM");
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.LoginEntry("2", "Welcome2@AM");
		
		try {
			if (MainLoginPage.ChangePWCheckBoxEnableStatus()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-20", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-20", exception, result);
		}
		
		sa17.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: ChangePWCheck box "
				+ "appear to be in Disable state");
		sa17.assertAll();	
	}
	
		
	@Test(groups = "Login_Screen", description="Verify if a user"
			+ " is forced to change his password while login, if his password has been changed by the admin user")
	public void LOGIN_020() throws InterruptedException, TestLinkAPIException {
		SoftAssert sa18 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile();
		
		UserManagementPage.CreateOperatorUser("5", "Welcome2@AM", "User8", "8", "aaaaaa", "SysOperator", "123456789", "user8@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("8", "aaaaaa");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM");
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainHubPage=MainLoginPage.Login("5", "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile();
		
		UserManagementPage.clickAnyUserinUserList("User8");
		UserManagementPage.enterNewUserPW("Welcome1@AM");
		UserManagementPage.enterNewUserConfPW("Welcome1@AM");
		UserManagementPage.ClickTitlefield();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup("5", "Welcome2@AM");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry("8", "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		
		try {
			if (!MainLoginPage.ChangePWCheckBoxEnableStatus() && MainLoginPage.NewPWFieldPresence()) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("TC00-21", null, result);
			}
			
		} catch (Exception e) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult("TC00-21", exception, result);
		}
		
		sa18.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: User is NOT "
				+ "forced to change his password while login, if his password has been changed by the admin user");
		sa18.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: User is NOT " 
				+ " forced to change his password while login with New PW field in Disabled/Invisible state");
		
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome8@AM");
		MainHubPage.UserSignOut();
		sa18.assertAll();
	}
		
	
	private void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException{
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME, testCase, BUILD_NAME, exception, result);
	}
}
