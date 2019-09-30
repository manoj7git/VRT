package com.vrt.pages;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_defineSetupPage extends BaseClass {
	
	// Define Setup page element variable declaration definition
	WebElement DefineSetupPageTitle = null;
	
	private void initializeEelements() {
		DefineSetupPageTitle = driver.findElementByName("Define Setup");
	}
	
	Setup_defineSetupPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Define Setup page
	public boolean defineSetupPage_state() {
		return IsElementVisibleStatus(DefineSetupPageTitle);
	}
	
	// Get the Define Setup page title text
	public String get_defineSetupPage_titletext() {
		return FetchText(DefineSetupPageTitle);
	}

}
