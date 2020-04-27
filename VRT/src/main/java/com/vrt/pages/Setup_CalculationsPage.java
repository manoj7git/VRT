package com.vrt.pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class Setup_CalculationsPage extends BaseClass{
	
	// Calculation page element variable declaration definition
	WebElement CalculationPageTitle = null;
	WebElement Aleth_Btn = null;
	WebElement BTemp_textfield = null;
	WebElement Dvalue_textfield = null;
	WebElement Zvalue_textfield = null;
	WebElement Aleth_DrpDwn = null;
	WebElement SatTP_btn = null;
	WebElement NxtBtn = null;
	
	private void initializeEelements() {
		CalculationPageTitle = driver.findElementByName("Calculations");
		Aleth_Btn = driver.findElementByName("Lethality Calculation");
		BTemp_textfield = driver.findElementByAccessibilityId("BaseTemperatureTextBox");
		Dvalue_textfield = driver.findElementByAccessibilityId("DValueTexBox");
		Zvalue_textfield = driver.findElementByAccessibilityId("ZValueTextBox");
		Aleth_DrpDwn = driver.findElementByAccessibilityId("CalculateLethalityComboBox");
		SatTP_btn = driver.findElementByName("Saturation P/T Calculation");		
		NxtBtn = driver.findElementByName("Qualification Parameters");
		
	}
	
	Setup_CalculationsPage() throws IOException {
		super();
		initializeEelements();
	}
	
	// Release memory
	public void resetWebElements() {
		CalculationPageTitle = null;
		Aleth_Btn = null;
		BTemp_textfield = null;
		Dvalue_textfield = null;
		Zvalue_textfield = null;
		Aleth_DrpDwn = null;
		SatTP_btn = null;
		NxtBtn = null;
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Setup_Calculations Page methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Check the presence of Calculation page
	public boolean GrpsensorPage_state() {
		return IsElementVisibleStatus(CalculationPageTitle);
	}
	
	// Get the Calculation page title text
	public String get_CalculationPage_titletext() {
		return FetchText(CalculationPageTitle);
	}
	
	// Enter ALeth Base Temp data
	public void enter_bTemp(String bTemp) {
		clickOn(BTemp_textfield);
		ClearText(BTemp_textfield);
		enterText(BTemp_textfield, bTemp);
	}
	
	// Select Aleth condition
	public void select_AlethCondition(String ALethCond) throws InterruptedException {
		clickOn(Aleth_DrpDwn);
		Thread.sleep(1000);
		WebElement ALeth_Undefined = driver.findElementByName("Undefined");
		WebElement ALeth_entireStudy = driver.findElementByName("During Entire Cycle");
		WebElement ALeth_Exposure = driver.findElementByName("During Exposure Cycle");

		if (ALethCond.contains("Entire Cycle")) {
			clickOn(ALeth_entireStudy);
			Thread.sleep(500);
		} else if (ALethCond.contains("Exposure Cycle")) {
			clickOn(ALeth_Exposure);
			Thread.sleep(500);
		} else {
			clickOn(ALeth_Undefined);
			Thread.sleep(500);
		}
		
	}
	
	// Click the Sat TP button in left pane
	public void click_SatTP_btn() {
		clickOn(SatTP_btn);
		//clickOn(SatTP_btn);
	}
	
	// Select Temp sensor for SatP
	public void select_1stTempSensor() throws InterruptedException {
		WebElement SatP_TempDrpDwnBox = driver.findElementByAccessibilityId("PressureSensorsComboBox");
		clickOn(SatP_TempDrpDwnBox);
		Thread.sleep(500);
		
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		Thread.sleep(500);
		
		WebElement SatP_selectSensorText = driver.findElementByName("Select Sensor");
		clickOn(SatP_selectSensorText);
		Thread.sleep(500);
	}
	
	// Select Pressure sensor for SatT
	public void select_1stPrSensor() throws InterruptedException {
		WebElement SatP_PrDrpDwnBox = driver.findElementByAccessibilityId("TemperatureSenosrsComboBox");
		clickOn(SatP_PrDrpDwnBox);
		Thread.sleep(500);

		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		Thread.sleep(500);
		
		WebElement SatT_headerText = driver.findElementByName("Saturation temperature of steam");
		clickOn(SatT_headerText);
		Thread.sleep(500);
	}
	
	// Click the Next button to move to Setup Qualification page
	public Setup_QualParamPage Click_NxtBtn() throws IOException, InterruptedException {
		clickOn(NxtBtn);
		//clickOn(NxtBtn);
		Thread.sleep(2000);
		return new Setup_QualParamPage();
	}

}
