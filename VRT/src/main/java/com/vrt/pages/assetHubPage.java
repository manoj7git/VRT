package com.vrt.pages;

import java.util.Arrays;
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
	
	// Get the target Asset tile info in the Asset hub page
	public String[] assetTile(String AssetName) {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		System.out.println("Total Assets created: "+AssetList.size());
		
		//Declaring an String array
		String[] a = null;

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			System.out.println(" Asset tile info count: "+AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				//System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					a = new String[AssetTileInfoList.size()];
					
					//Another inner loop to fetch the tile info content once it matched the Asset Name.
					for (int j2 = 0; j2 < a.length; j2++) {
						a[j2]=AssetTileInfoList.get(j2).getText();
					}
					//Getting out of the Asset list after collecting the tile info of the targeted asset
					//System.out.println("2nd Inr loop: "+Arrays.toString(a));	
					break;
				}				
			}
		}
		//Returning the String object
		return a;		 
	}
	
	
}
