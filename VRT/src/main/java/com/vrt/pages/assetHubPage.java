package com.vrt.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class assetHubPage extends BaseClass{
	
	
	WebElement AssetPageTitle = null;
	List <WebElement> AssetList = null;
	WebElement AddAssetBtn = null;
	WebElement anyAsstTile = null;
	WebElement TypeBtn = null;
	WebElement ManufacturerBtn = null;
	WebElement LocationBtn = null;
	WebElement BackBtn = null;
	WebElement SearchBtn = null;
	
	private void resetWebElements()
	{
		AssetPageTitle = null;
		AssetList = null;
		AddAssetBtn = null;
		anyAsstTile = null;
		TypeBtn = null;
		ManufacturerBtn = null;
		LocationBtn = null;
		BackBtn = null;
		SearchBtn = null;
	}
	
	private void initWebElements()
	{
		AssetPageTitle = driver.findElementByAccessibilityId("AssetsHeaderTextBlock");
		AssetList = driver.findElementByAccessibilityId("ItemGridView").findElements(By.className("GridViewItem"));
		AddAssetBtn = driver.findElementByAccessibilityId("AddedAssetsButton");
		anyAsstTile = driver.findElementByClassName("GridViewItem");
		TypeBtn = driver.findElementByAccessibilityId("TypeButton");
		ManufacturerBtn = driver.findElementByAccessibilityId("ManufacturerButton");
		LocationBtn = driver.findElementByAccessibilityId("LocationButton");
		BackBtn = driver.findElementByAccessibilityId("BackButton");
		SearchBtn = driver.findElementByAccessibilityId("SearchAssetsButton");
	}
	
	assetHubPage()
	{
		initWebElements();
	}
	
	//Get the Asset Page title
	public String assetPageTitle() {
		return FetchText(AssetPageTitle);
	}		
	
	//Verify the presence of Type Filter Button
	public boolean typeFilterBtn() {
		return IsElementVisibleStatus(TypeBtn);
	}
	
	//Verify the presence of Manufacturer Filter Button
	public boolean manufacturerFilterBtnstate() {
		return IsElementVisibleStatus(ManufacturerBtn);
	}
	
	//Click Manufacturer Filter Button
	public void click_manufacturerFilterBtn() throws InterruptedException {
		clickOn(ManufacturerBtn);
		Thread.sleep(2000);
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
	
	//Click on any Asset
	public assetDetailsPage click_AnyAsset(WebElement e) {
		clickOn(e);
		return new assetDetailsPage();
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
	
	/*// Get the Assets list based on Type in the Asset hub page
	public boolean assetList_TypeFilter() {
		boolean response = false;
		// boolean state;
		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		System.out.println("Total Asset Types created: " + AssetType_List.size());
		int NumberofGroupMatching = 0;
		// Loop for the different Asset type groups created
		for (int i = 0; i < AssetType_List.size(); i++) {
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
			System.out
					.println("Asset type : " + AssetType_List.get(i).findElement(By.className("TextBlock")).getText());

			List<WebElement> Asset_List_againstType = AssetType_List.get(i).findElements(By.className("GridViewItem"));
			System.out.println(" Asset tile info count: " + Asset_List_againstType.size());
			int NumberofMatchingAssets = 0;
			// Fetch all the contents of the Asset tile against the corresponding Asset Type
			// group
			for (int j = 0; j < Asset_List_againstType.size(); j++) {
				String Individual_Asset_Type = Asset_List_againstType.get(j).getText();
				System.out.println("AssetTileInfo: " + Asset_List_againstType.get(j).getText());

				if (Asset_Group_Type_Name.equals(Individual_Asset_Type)) {
					// System.out.println(Individual_Asset_Type);
					if (NumberofMatchingAssets < Asset_List_againstType.size())
						NumberofMatchingAssets = NumberofMatchingAssets + 1;
					// return state = true;
				}

			}
			if (Asset_List_againstType.size() == NumberofMatchingAssets) {
				NumberofGroupMatching = NumberofGroupMatching + 1;
			}
			// return Asset_Group_Type_Name.equals(Individual_Asset_Type);
		}
		if (AssetType_List.size() == NumberofGroupMatching) {
			response = true;
		}

		
		 * // Returning the String object return a;
		 
		return response;
	}
	*/

	//Get the Assets list based on Type filter in the Asset hub page
	public boolean assetList_TypeFilter() {
		boolean response = false;
		
		clickOn(TypeBtn);

		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		//System.out.println("Total Asset Types created: " + AssetType_List.size());

		// Loop for the different Asset type groups created
		for (int i = 0; i < AssetType_List.size(); i++) {
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
			//System.out.println("Asset type data : " + AssetType_List.get(i).findElement(By.className("TextBlock")).getText());

			List<WebElement> Asset_List_against_Type = AssetType_List.get(i).findElements(By.className("GridViewItem"));
			//System.out.println(" Asset tile info count: " + Asset_List_against_Type.size());

			// Fetch all the contents of the Asset tile against the corresponding Asset Type group
			for (int j = 0; j < Asset_List_against_Type.size(); j++) {
				String Individual_Asset_Type = Asset_List_against_Type.get(j).getText();
				//System.out.println("AssetTileInfo: " + Asset_List_against_Type.get(j).getText());

				if (Asset_Group_Type_Name.equals(Individual_Asset_Type)) {
					response = true;
					continue;
				}
				else {
					response = false;
				}
			}
		}
		return response;
	}
		
	// Get the Assets list based on Manufacturer filter in the Asset hub page
	public boolean assetList_ManufacturerFilter() throws InterruptedException {
		boolean response = false;
		
		click_manufacturerFilterBtn();

		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		System.out.println("Total Asset Types_Mfg created: " + AssetType_List.size());
		//int sizeOfColumns = AssetType_List.size();
		// Loop for the different Asset type(Manufacturer) groups created
		for (int i = 0; i < AssetType_List.size(); i++) {			
			
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
			System.out
					.println("Asset Manufacturer data : " + AssetType_List.get(i).findElement(By.className("TextBlock")).getText());

			List<WebElement> Asset_List_against_Manufacturer = AssetType_List.get(i).findElements(By.className("GridViewItem"));			
			System.out.println(" Asset tile info count: " + Asset_List_against_Manufacturer.size());
			//Thread.sleep(1000);
			//int numberOfItemsInColumn = Asset_List_against_Manufacturer.size();
			// Loop through each Asset under the Filter type Group
			//Fetch all the contents of the Asset tile against the corresponding Asset Manufacturer group
			for (int j = 0; j < Asset_List_against_Manufacturer.size(); j++) {
				//CLick the each Asset under the respective Filter Group
				Asset_List_against_Manufacturer.get(j).click();
				Thread.sleep(1000);
				
				WebElement AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
				String Individual_Asset_Mfgdata = AssetDetail_Mfginfo.getText();				
				System.out.println("AssetDetail_MfgInfo: " + Individual_Asset_Mfgdata);

				if (Asset_Group_Type_Name.equals(Individual_Asset_Mfgdata)) {
					response = true;	

					WebElement AsstDetail_BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
					AsstDetail_BackBtn.click();
					Thread.sleep(1000);
					
					resetWebElements();
					initWebElements();
					click_manufacturerFilterBtn();
					
					AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
							.findElements(By.className("GroupItem"));
					Asset_List_against_Manufacturer = AssetType_List.get(i).findElements(By.className("GridViewItem"));
					
					continue;
				} else {
					response = false;
				}
			}
		}
		return response;
	}
	
	
}
