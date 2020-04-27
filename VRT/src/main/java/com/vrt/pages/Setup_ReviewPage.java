package com.vrt.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class Setup_ReviewPage extends BaseClass{
	
	// Review page element variable declaration definition
	WebElement ReviewPageTitle = null;
	WebElement SaveSetup_Btn = null;
	WebElement QStart_Text = null;
	WebElement Bottom_VScrollBar = null;
	WebElement Back_btn = null;
	
	
	private void initializeEelements() {
		ReviewPageTitle = driver.findElementByName("Review");
		QStart_Text = driver.findElementByAccessibilityId("StartQualificationTextBlock");			
		SaveSetup_Btn = driver.findElementByAccessibilityId("SaveSetupButton");	
		Bottom_VScrollBar = driver.findElementByAccessibilityId("VerticalSmallIncrease");
		Back_btn = driver.findElementByAccessibilityId("GoButton");
	}
	
	Setup_ReviewPage() throws IOException {
		super();
		initializeEelements();
	}
	
	// Release memory
	public void resetWebElements() {
		ReviewPageTitle = null;
		QStart_Text = null;
		SaveSetup_Btn = null;
		Bottom_VScrollBar = null;
		Back_btn = null;
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Review Page methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// Check the presence of Review page
	public boolean ReviewPage_state() {
		return IsElementVisibleStatus(ReviewPageTitle);
	}
	
	// Get the Review page title text
	public String get_ReviewPage_titletext() {
		return FetchText(ReviewPageTitle);
	}
	
	// Get the Qual Start text text
	public String get_QStart_text() {
		return FetchText(QStart_Text);
	}
	
	// Click save button
	public void click_Save_Btn(String QStart, String TODAlertAction) throws InterruptedException {
		
		if (!QStart.equals("Manual")) {
			clickOn(SaveSetup_Btn);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			click_Yes_TODAlertMsg_Btn(TODAlertAction);
			Thread.sleep(1000);
		} else {
			clickOn(SaveSetup_Btn);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			Thread.sleep(1000);
		}
	}
	
	// Click expected action option like "Yes or No or Cancel" as input to the method
	// to perform the corresponding TOD Save Alert message
	public void click_Yes_TODAlertMsg_Btn(String Alert_Action) throws InterruptedException {		
		WebElement TOD_Alert_Yes_Btn = driver.findElementByName("Yes");
		WebElement TOD_Alert_No_Btn = driver.findElementByName("No");
		WebElement TOD_Alert_Cancel_Btn = driver.findElementByName("Cancel");
		
		if (Alert_Action.contains("Yes")) {
			clickOn(TOD_Alert_Yes_Btn);
			Thread.sleep(1000);
		} else if (Alert_Action.contains("No")) {
			clickOn(TOD_Alert_No_Btn);
			Thread.sleep(1000);
		} else {
			clickOn(TOD_Alert_Cancel_Btn);
			Thread.sleep(1000);
		}
	}
	
	//Click the bottom vertical scroll bar
	public void click_bottom_Scrollbar(int numberofclicks) {		
		for (int i = 1; i <= numberofclicks; i++) {
			clickOn(Bottom_VScrollBar);
		}
		
	}
	
	//Click the Back button of Review page
	public void click_back_Btn() throws InterruptedException {		
		clickOn(Back_btn);
		Thread.sleep(2000);
		
	}

}
