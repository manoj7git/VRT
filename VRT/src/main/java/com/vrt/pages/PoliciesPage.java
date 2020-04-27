/**
 * @author ruchika.behura
 *
 */

package com.vrt.pages;


import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class PoliciesPage extends BaseClass{
	//Page element variable declaration definition
	WebElement PoliciesHeaderText = null;


		
	//Page element Initialize method
	private void initElements()
	{
		PoliciesHeaderText = driver.findElementByAccessibilityId("PoliciesButton");

	}
	
	//Constructor for initializing the page elements
	PoliciesPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		PoliciesHeaderText = null;
		
	}
	
	/*----------------------
	Methods of Policies Page
	------------------------*/
	// Check if Policies page is displayed
	public boolean IsPolicies_screenDisplayed() {
		return IsElementEnabledStatus(PoliciesHeaderText);
	}

}
