package com.vrt.pages;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_SensorConfigPage extends BaseClass{
	
	// Sensor Configuration page element variable declaration definition
	WebElement SensorConfigPageTitle = null;
	
	private void initializeEelements() {
		SensorConfigPageTitle = driver.findElementByName("Sensors Configuration");
	}
	
	Setup_SensorConfigPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Sensor Configuration page
	public boolean sensorConfigPage_state() {
		return IsElementVisibleStatus(SensorConfigPageTitle);
	}
	
	// Get the Sensor Configuration page title text
	public String get_SensorConfigurationPage_titletext() {
		return FetchText(SensorConfigPageTitle);
	}

}
