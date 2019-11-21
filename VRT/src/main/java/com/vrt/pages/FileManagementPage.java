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

public class FileManagementPage extends BaseClass {

	// FileManagementPage Element definition
	WebElement ArchiveBtn = null;
	WebElement ArchiveTextBox = null;
	WebElement SyncInBtn = null;
	WebElement SyncInTextBox = null;
	WebElement SyncOutBtn = null;
	WebElement SyncOutBtnTextBox = null;

	private void initElements() {
		ArchiveBtn = driver.findElementByAccessibilityId("Archive");
		SyncInBtn = driver.findElementByAccessibilityId("SyncIn");
		SyncOutBtn = driver.findElementByAccessibilityId("SyncOut");

	}

	FileManagementPage() {
		super();
		initElements();

	}

	// Click Archive Button
	public void ClickArchiveBtn() throws InterruptedException {
		clickOn(ArchiveBtn);
		Thread.sleep(1000);
	}

	// Archive TextBox is Visible
	public boolean ArchiveTextBoxVisible() throws InterruptedException {
		ArchiveTextBox = driver.findElementByAccessibilityId("ArchiveFolderTextBox");
		return IsElementVisibleStatus(ArchiveTextBox);
	}

	// Click SyncIn Button
	public void ClickSyncInBtn() throws InterruptedException {
		clickOn(SyncInBtn);
		Thread.sleep(1000);
	}

	// SyncInTextBox is visible
	public boolean SyncInTextBoxVisible() throws InterruptedException {
		SyncInTextBox = driver.findElementByAccessibilityId("SyncInFolderTextBox");
		return IsElementVisibleStatus(SyncInTextBox);
	}

	// Click SyncIn Button
	public void ClickSyncOutBtn() throws InterruptedException {
		clickOn(SyncOutBtn);
		Thread.sleep(1000);
	}

	// SyncInTextBox is visible
	public boolean SyncOutTextBoxVisible() throws InterruptedException {
		SyncOutBtnTextBox = driver.findElementByAccessibilityId("SyncOutFolderTextBox");
		return IsElementVisibleStatus(SyncOutBtnTextBox);
	}

	// Fetch the alert message when a user does not have privilege to access
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}
}
