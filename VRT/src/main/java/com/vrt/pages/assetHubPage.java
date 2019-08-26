package com.vrt.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class assetHubPage extends BaseClass{
	
	// Asset Hub Page Element definition
	WebElement AssetPageTitle = driver.findElementByAccessibilityId("AssetsHeaderTextBlock");
	List <WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView").findElements(By.className("GridViewItem"));
	WebElement AddAssetBtn = driver.findElementByAccessibilityId("AddedAssetsButton");
	WebElement TypeBtn = driver.findElementByAccessibilityId("TypeButton");
	WebElement ManufacturerBtn = driver.findElementByAccessibilityId("ManufacturerButton");
	WebElement LocationBtn = driver.findElementByAccessibilityId("LocationButton");
	WebElement BackBtn = driver.findElementByAccessibilityId("BackButton");
	WebElement SearchBtn = driver.findElementByAccessibilityId("SearchAssetsButton");
	
	
	//Get the Asset Page title
	public String assetPageTitle() {
		return FetchText(AssetPageTitle);
	}		
	
	//Verify the presence of Type Filter Button
	public boolean typeFilterBtn() {
		return IsElementVisibleStatus(TypeBtn);
	}
	
	//Verify the presence of Manufacturer Filter Button
	public boolean manufacturerFilterBtn() {
		return IsElementVisibleStatus(ManufacturerBtn);
	}
	
	//Verify the presence of Location Filter Button 
	public boolean locationFilterBtn() {
		return IsElementVisibleStatus(LocationBtn);
	}
	
	//Verify the presence of Search Asset Button 
	public boolean serachAstBtn() {
		return IsElementVisibleStatus(SearchBtn);
	}
	
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
	
	//Click the Back Button
	public MainHubPage ClickBackBtn() throws InterruptedException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new MainHubPage();
	}
	
	// Get the target Asset tile info in the Asset hub page
	public String[] assetTile(String AssetName) {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		System.out.println("Total Assets created: " + AssetList.size());

		// Declaring an String array
		String[] a = null;

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					a = new String[AssetTileInfoList.size()];

					// Another inner loop to fetch the tile info content once it matched the Asset
					// Name.
					for (int j2 = 0; j2 < a.length; j2++) {
						a[j2] = AssetTileInfoList.get(j2).getText();
					}
					// Getting out of the Asset list after collecting the tile info of the targeted
					// asset
					// System.out.println("2nd Inr loop: "+Arrays.toString(a));
					break;
				}
			}
		}
		// Returning the String object
		return a;
	}
	
	// Get the Asset count info in the Asset hub page
	public String assetcount() {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		//System.out.println("Total Assets created: "+AssetList.size());
		
		//Returning the asset count
		return String.valueOf(AssetList.size());		 
	}
	
	// Right Click anywhere in the Asset Hub Page to invoke the Bottom app status bar
	public void rightclickonAssetPageTitle() throws InterruptedException{		
		Actions ac = new Actions(driver);
		ac.contextClick(AssetPageTitle).build().perform();	
		Thread.sleep(2000);
	}
	
	//Click the App Help button in the Bottom app status bar
	public void clickHelpIcon() throws InterruptedException{
		WebElement appHelp = driver.findElementByName("Help");
		clickOn(appHelp);
		Thread.sleep(1000);
	}
	
	//AssetHub Help window display
	public boolean is_assetHubHelpWindow_Displayed() throws InterruptedException{
		WebElement assetHubHelpElement = driver.findElementByName("Asset Hub");
		return IsElementVisibleStatus(assetHubHelpElement);
	}
	
	// Get the Assets list based on Type in the Asset hub page
	public void assetList_TypeFilter() {
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		System.out.println("Total Assets created: " + AssetType_List.size());

		// Declaring an String array
		String[] a = null;

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetType_List.size(); i++) {
			System.out.println("Asset type : " + AssetType_List.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetType_List.get(i).findElements(By.className("TextBlock"));
			System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			/*// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					a = new String[AssetTileInfoList.size()];

					// Another inner loop to fetch the tile info content once it matched the Asset
					// Name.
					for (int j2 = 0; j2 < a.length; j2++) {
						a[j2] = AssetTileInfoList.get(j2).getText();
					}
					// Getting out of the Asset list after collecting the tile info of the targeted
					// asset
					// System.out.println("2nd Inr loop: "+Arrays.toString(a));
					break;
				}
			}*/
		}
		/*// Returning the String object
		return a;*/
	}
	
	
	
}
