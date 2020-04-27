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

public class AuditPage extends BaseClass {
	
	// FileManagementPage Element definition
	WebElement Audit_HeadTitle = null;
	

	
	private void initElements() {
		Audit_HeadTitle = driver.findElementByName("Audit Trail");	

	}
	
	AuditPage() throws IOException
	{
		super();
		initElements();

	}
	

	// Audit TextBox is Visible
		public boolean AuditHeadTitleVisible() throws InterruptedException {
			return IsElementVisibleStatus(Audit_HeadTitle);
		}	
		
// Fetch the alert message when a user does not have privilege to access
		public String AlertMsg() {
			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return FetchText(Msg);
		}
}