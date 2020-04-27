/**
 * @author manoj ghadei
 *
 */

package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class SyncInPage extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement SyncInHeaderBtn = null;
	WebElement SyncInBrowseBtn = null;
	WebElement FltrByDtBtn = null;
	WebElement SyncInTextBox = null;
	WebElement SyncInOKbtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		SyncInHeaderBtn = driver.findElementByAccessibilityId("SyncIn");
		SyncInBrowseBtn = driver.findElementByAccessibilityId("SyncInFolderBrowseButton");
		SyncInTextBox = driver.findElementByAccessibilityId("SyncInFolderTextBox");
		FltrByDtBtn = driver.findElementByAccessibilityId("SyncInDateFilterCheckBox");
		SyncInOKbtn = driver.findElementByAccessibilityId("SyncInFolderOKButton");
		

	}
	
	//Constructor for initializing the page elements
	SyncInPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		SyncInHeaderBtn = null;
		SyncInBrowseBtn = null;
		FltrByDtBtn = null;		
		SyncInTextBox = null;
		SyncInOKbtn = null;
		
	}
	
	/*----------------------
	Methods of Sync In Page
	------------------------*/

	// SyncInTextBox is visible
	public boolean SyncInTextBoxVisible() throws InterruptedException {
		return IsElementVisibleStatus(SyncInTextBox);
	}
	
	// Enter sync in folder path
	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		clickOn(SyncInBrowseBtn);
		Thread.sleep(500);
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath1 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\";
		//System.out.println(filepath);
		alert.sendKeys(filepath1);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		String fp2 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\" + pathname;
		//System.out.println(filepath);
		alert.sendKeys(fp2);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}
	
	//Click the Filter button
	public void click_FltrBtn() {
		clickOn(FltrByDtBtn);
	}
	
	// Click the Filter button
	public SyncInAssetListPage click_SyncInOK_btn() throws IOException {
		clickOn(SyncInOKbtn);
		return new SyncInAssetListPage();
	}
	
	//Click the SycnIn Alert confirmation message
	public void click_SyncIn_AlrtMag() {
		clickOn(FltrByDtBtn);
	}

}
