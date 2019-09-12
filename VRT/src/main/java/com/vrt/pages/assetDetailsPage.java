package com.vrt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class assetDetailsPage extends BaseClass{
	
	//Asset Details element variable declaration definition
	WebElement AssetDetailPageTitle = null;
	WebElement AssetDetail_Mfginfo = null;
	WebElement BackBtn = null;
	
	private void initElements() {
		AssetDetailPageTitle = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
		BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
	}
	
	assetDetailsPage() {
		super();
		initElements();
	}
	

	//Get the Manufacturer data for the corresponding Asset
	public String assetDetail_Mfginfo() {
		return FetchText(AssetDetail_Mfginfo);
	}
	
	//Click the Back Button
	public assetHubPage ClickBackBtn() throws InterruptedException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new assetHubPage();
	}	


}
