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
	
/*	// Get the target Asset tile info in the Asset hub page
	public String[] assetTile(String AssetName) {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			// System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfo = AssetList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(AssetTileInfo.size());
			String str[] = new String[AssetTileInfo.size()];

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfo.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfo.get(j).getText());
				str[j] = AssetTileInfo.get(j).getText();
				if (str[j].contains(AssetName)) {
					// str[j] = AssetTileInfo.get(j).getText();
					String k= (String) str[j];
					// return str;
				}
			}
			// return str;
		}
		return k;
		
		 * int index=0; // Create a copy of stringarray[] return Arrays.copyOf(str,
		 * index); /* String[] Obtainedlist= new String[str]; return Obtainedlist;
		 
	}
*/	

}
