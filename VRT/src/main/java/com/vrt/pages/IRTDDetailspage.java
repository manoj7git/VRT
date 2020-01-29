/**
 * @author ruchika.behura
 *
 */

package com.vrt.pages;

import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.vrt.pages.EquipmentPage;
import com.vrt.base.BaseClass;

public class IRTDDetailspage extends BaseClass {

	// EquipmentHubPage Element definition
	WebElement IRTDHeader = null;
	WebElement DeleteBtn = null;		
	WebElement EntrAssertname = null;
	WebElement ClkSaveBtn = null;

	// Button1
	private void initElements() {
		IRTDHeader = driver.findElementByAccessibilityId("EquipmentsHeaderTextBlock");
		DeleteBtn = driver.findElementByAccessibilityId("DeleteEquipmentsButton");

	}

	IRTDDetailspage() {
		super();
		initElements();

	}

	// IRTD Details page Header is presence...
	public boolean IRTD_DetailsHeaderPresence() {
		return IsElementVisibleStatus(IRTDHeader);
	}

	//Delete Equipment
	public void clickDeleteEquipmentIcon() {
		clickOn(DeleteBtn);
	}

	// Delete Pop up Window is presence...
	public boolean IRTD_DeletePopupWindow() {
		WebElement DeletePopupWindow = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(DeletePopupWindow);
	}

	// clickYesBtn
	public void ClickYesBtn() {
		if (!IRTD_DeletePopupWindow()) {
			System.out.println("IRTD delete popup didnt display");
		} else {
			WebElement YesBtn = driver.findElementByAccessibilityId("Button1");
			clickOn(YesBtn);
		}
	}

	// Edit Asset Name
	public void EditAssertname(String an) {
		EntrAssertname = driver.findElementByAccessibilityId("AssetNameTextBox");
		ClearText(EntrAssertname);
		enterText(EntrAssertname, an);
	}

	// Click on save button after edited
	public void ClickSaveButton() throws InterruptedException {
		ClkSaveBtn = driver.findElementByAccessibilityId("SaveButton");
		clickOn(ClkSaveBtn);
		Thread.sleep(1000);
	}

	//Edit Functionality for IRTD Equipments				
	public void EditIRTDEquip(String an) throws InterruptedException {
		EditAssertname(an);
		ClickSaveButton();
	}

	//Fetch the alert message when Supervisor not have the privilege to edit the equipments
	/*public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}*/
	
}
