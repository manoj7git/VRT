/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.vrt.base.BaseClass;
import com.vrt.pages.LoginPage;
import com.vrt.pages.MainHubPage;
import com.vrt.pages.UserManagementPage;
import com.vrt.pages.assetCreationPage;
import com.vrt.pages.assetHubPage;
import com.vrt.pages.assetDetailsPage;
import com.vrt.utility.TestUtilities;
import com.vrt.utility.assetCreationUtility;


public class HitNTrialTests extends BaseClass {
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;

	@BeforeClass
	private void testsetup() throws IOException {
		//Rename the User file (NgvUsers.uxx) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");		
		//Rename the cache Asset file (Asset.txt) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
	}
	
	
	//Before All the tests are conducted
	@BeforeTest
	private void setExtent() throws IOException {
		//Rename the cache Asset file (Asset.txt) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		
		//Rename the Asset folder (Asset) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Hit&Trail");

	}
	
	
	//After All the tests are conducted
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	/*@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");			
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.waitforAssetHubPageLoad();
	}*/

	
	/*// TearDown of the App
	@AfterMethod
	public void Teardown(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getName()+" #"); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getThrowable()+" #"); //to add error/exception in extent report
			
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName()+" #");
			String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report

		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}*/

	
	/*//Create 25 assets
	@Test(dataProvider="testAsset", dataProviderClass=assetCreationUtility.class, groups = {"Sanity", "Regression"}, 
			description = "Create 25 assets"
			+ "categories model, size, manufacturer,Location and Type")
	public void ASSTHB012a(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String VldDT, String Frequency, String FrequencyInterval, String Description)
					throws InterruptedException, ParseException {
		extentTest = extent.startTest("Create 25 assets");
		
		//Forcibly creating the Assets with Last Validated data as Current date
		//irrespective of what data is provided in the Excel sheet. 
		//Just to save time in the date selection picker thereby reducing the time for creating assets 
		//for any random Lst Vldt Date
		TestUtilities tu = new TestUtilities();
		String crntDate = tu.get_CurrentDate_inCertainFormat("MM/dd/YYYY");
		
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();		
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				crntDate, Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset		
		
	}*/


	/*
	@Test (description="Check for File renaming")
	public void renameFile() throws IOException {

		// create a new file
		String filepath ="C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData";
		
		File file = new File(filepath + "/" + "NgvUsers.uux");
		System.out.println(file.getName());
		System.out.println(file.exists());
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println("No User DB File present");
		} else {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			File backupFile = new File(filepath + "/" + timestamp + "_NgvUsers.uux");
			file.renameTo(backupFile);
		}	
				
	}
	*/
	
	@Test (description="get Last Week Date")
	public void getLastWeekDate() throws IOException {
		TestUtilities tu = new TestUtilities();
		String bkDt = tu.getBackDate_weeks(53);
		System.out.println("Back date: " +bkDt);
		
		//tu.getFutureDate_weeks(1);
	  }
	
				
	
	
	
/*	@Test
	public void fetchAssetUnit() {
		assetCreationPage.selectAssetSizeUnit("cu ft");		
	}*/
	
}
