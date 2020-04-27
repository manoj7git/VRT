package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class Setup_GroupSensorsPage extends BaseClass{
	
	TestUtilities tu = new TestUtilities();
	
	// Group Sensor page element variable declaration definition
	WebElement GrpSensorPageTitle = null;
	WebElement DefaultGrp_Btn = null;
	WebElement GrpWiring_Btn = null;
	
	WebElement NxtBtn = null;
	
	private void initializeEelements() {
		GrpSensorPageTitle = driver.findElementByName("Group Sensors");
		DefaultGrp_Btn = driver.findElementByAccessibilityId("DefaultGroupButton");
		GrpWiring_Btn = driver.findElementByAccessibilityId("GroupsGraphButton");
		NxtBtn = driver.findElementByAccessibilityId("NextButton");
		
	}
	
	Setup_GroupSensorsPage() throws IOException {
		super();
		initializeEelements();
	}
	
	// Release memory
	public void resetWebElements() {
		GrpSensorPageTitle = null;
		DefaultGrp_Btn = null;
		GrpWiring_Btn = null;
		NxtBtn = null;
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Group Sensor Config Page methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// Check the presence of Group Sensor page
	public boolean GrpsensorPage_state() {
		return IsElementVisibleStatus(GrpSensorPageTitle);
	}
	
	// Get the Group Sensor page title text
	public String get_GrpsensorPage_titletext() {
		return FetchText(GrpSensorPageTitle);
	}
	
	// Click the Default Group button
	public void click_DfltGrp_Btn() throws InterruptedException {
		clickOn(DefaultGrp_Btn);
		Thread.sleep(1000);
	}
	
	// Click the Wiring Group button
	public void click_GrpWiring_Btn() throws InterruptedException {
		clickOn(GrpWiring_Btn);
		Thread.sleep(1000);
	}
	
	// Click the Default Temperature Group
	public void click_Dflt_TempGrp() {
		WebElement Dflt_TempGrp = driver.findElementByName("Temperature");
		clickOn(Dflt_TempGrp);
	}
	
	// Click the  Default Pressure Group
	public void click_Dflt_PrGrp() {
		WebElement Dflt_PrGrp = driver.findElementByName("Pressure");
		clickOn(Dflt_PrGrp);
	}
	
	// Click the Wiring img place holder
	public void Click_WiringImgHoldr(int imgHldrNo) throws InterruptedException {
		List<WebElement> WiringImgHoldrs = driver.findElementsByClassName("Image");
		//System.out.println(WiringImgHoldrs.size());
		WiringImgHoldrs.get(imgHldrNo).click();
		Thread.sleep(1000);
	}
	
	// Click Image edit icon for adding/replacing image
	public void Click_ImgHoldr1_EditBtn() throws InterruptedException {
		WebElement ImgHoldr_EditBtn = driver.findElementByAccessibilityId("ChangeImageBrowser");
		clickOn(ImgHoldr_EditBtn);
		Thread.sleep(500);
	}
	
	// Upload Image for the image holder
	public void select_Img(String filename) throws InterruptedException, AWTException  {
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(500);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + filename;
		//System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(1000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);

		// switch back
		//driver.switchTo().activeElement();
		clickOn(GrpSensorPageTitle);
	}
	
	// Click the Next button to move to Setup Calculations page
	public Setup_CalculationsPage Click_NxtBtn() throws InterruptedException, IOException {
		clickOn(NxtBtn);
		Thread.sleep(1000);
		return new Setup_CalculationsPage();
	}

}
