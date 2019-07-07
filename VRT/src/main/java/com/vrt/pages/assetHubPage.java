package com.vrt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class assetHubPage extends BaseClass{
	
	// Asset Hub Page Element definition
	List <WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView").findElements(By.className("GridViewItem"));
	WebElement AddAssetBtn = driver.findElementByAccessibilityId("AddedAssetsButton");
	
	
	//Click the Add Asset Button
	public assetCreationPage ClickAddAssetBtn() throws InterruptedException {
		clickOn(AddAssetBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}
	

}
