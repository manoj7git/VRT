package com.vrt.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class PreferencesPage extends BaseClass{
	//Page element variable declaration definition
	WebElement PreferencesHeaderText = null;


		
	//Page element Initialize method
	private void initElements()
	{
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");

	}
	
	//Constructor for initializing the page elements
	PreferencesPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		PreferencesHeaderText = null;
		
	}
	
	/*----------------------
	Methods of Preferences Page
	------------------------*/
	// Check if Preferences page is displayed
	public boolean IsPreferences_screenDisplayed() {
		return IsElementEnabledStatus(PreferencesHeaderText);
	}

}
