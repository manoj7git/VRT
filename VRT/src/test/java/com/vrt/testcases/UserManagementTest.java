package com.vrt.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import com.vrt.Listners.AllureReportListner;
import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;

/*import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;*/

//@Listeners({AllureReportListner.class})

public class UserManagementTest extends BaseClass{
	
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		MainHubPage = MainLoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
}
	@AfterMethod(alwaysRun=true)
	public void Teardown() {
		driver.quit();
	}
	
	
	@Test(groups = {"Sanity", "Regression"}, description = "ADMN120-Verify able to navigate to User Management screen from Admin screen"
			+ "hub page , user is navigated to the User Management screen screen")
	//@Severity(SeverityLevel.NORMAL)
	//@Description("Verify if selecting the User Management screen from Admin screen,"
			//+ " user is navigated to the User Management screen")
	
	//@Story("ADMN120")
	public void ADMN120() throws InterruptedException {
		SoftAssert sa1 = new SoftAssert();

		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), "Assets",
				"FAIL: TC-ASST001 -Incorrect Asset Page title or landed into incorrect Page");
		sa1.assertAll();
	}
	
	}
	
	
