package com.vrt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;

public class assetHubPage extends BaseClass{
	
	// Asset Hub Page Element definition
	List <WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView").findElements(By.className("GridViewItem"));
	WebElement AddAssetBtn = driver.findElementByAccessibilityId("AddedAssetsButton");
	
	
	//Verify the presence of Add Asset Button
	public boolean addAst() {
		return IsElementVisibleStatus(AddAssetBtn);
	}
	
	//Click the Add Asset Button
	public assetCreationPage ClickAddAssetBtn() throws InterruptedException {
		clickOn(AddAssetBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}
	
	//Get the Asset tile info in the Asset hub page
	public void assetTile(String AssetName) throws InterruptedException {
		clickOn(AddAssetBtn);
		Thread.sleep(1000);
		//return new assetCreationPage();
	}
	

}
