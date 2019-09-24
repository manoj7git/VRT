/**
 * @author manoj.ghadei
 *
 */

package com.vrt.pages;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class assetDetailsPage extends BaseClass{
	
	//Asset Details element variable declaration definition
	WebElement AssetDetailPageTitle = null;
	WebElement AssetDetail_IDinfo = null;
	WebElement AssetDetail_Mdlinfo = null;
	WebElement AssetDetail_Mfginfo = null;
	WebElement AssetDetail_Typeinfo = null;
	WebElement AssetDetail_Dateinfo = null;
	WebElement BackBtn = null;
	WebElement AssetEditBtn = null;
	
	private void initElements() {
		AssetDetailPageTitle = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetDetail_IDinfo = driver.findElementByAccessibilityId("EquipmentIDTextBlock");
		AssetDetail_Mdlinfo = driver.findElementByAccessibilityId("ModelNoTextBlock");
		AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
		AssetDetail_Typeinfo = driver.findElementByAccessibilityId("TypeTextBlock");
		AssetDetail_Dateinfo = driver.findElementByAccessibilityId("LastValidationTextBlock");
		BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
		AssetEditBtn=driver.findElementByAccessibilityId("EditAssetsButton");
	}
	
	assetDetailsPage() {
		super();
		initElements();
	}
	

	
	//Get the Asset details Page header data for the corresponding Asset
	public String assetDetail_PageTitle() {
		return FetchText(AssetDetailPageTitle);
	}
	
	//Get the Asset ID data for the corresponding Asset
	public String get_asset_IDinfo() {
		return FetchText(AssetDetail_IDinfo);
	}
	
	//Get the Asset Model data for the corresponding Asset
	public String get_asset_Modelinfo() {
		return FetchText(AssetDetail_Mdlinfo);
	}
	
	//Get the Asset Manufacturer data for the corresponding Asset
	public String get_asset_Mfginfo() {
		return FetchText(AssetDetail_Mfginfo);
	}
		
	//Get the Asset Type data for the corresponding Asset
	public String get_asset_Typeinfo() {
		return FetchText(AssetDetail_Typeinfo);
	}
	
	//Get the Asset Last Validated data for the corresponding Asset
	public String get_asset_LastValidatedinfo() throws ParseException {
		String Dt = FetchText(AssetDetail_Dateinfo);
		TestUtilities TU = new TestUtilities();
		return TU.convert_StringDate_to_ActualDate_inCertainFormat(Dt);
		
	}
	
	//Check the presence of Asset edit button
	public boolean assetEditBtn_state() {
		return IsElementVisibleStatus(AssetEditBtn);
	}
	
	//Click the Asset edit button
	public assetCreationPage click_assetEditBtn() throws InterruptedException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}
	
	//Click the Back Button
	public assetHubPage ClickBackBtn() throws InterruptedException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new assetHubPage();
	}
	
	//Get the Asset info in Asset details page
	public String[] get_assetinfo() throws ParseException {
		String[] asstDetailinfo = new String[5];
		asstDetailinfo[0] = get_asset_IDinfo();
		asstDetailinfo[1] = get_asset_Modelinfo();
		asstDetailinfo[2] = get_asset_Mfginfo();
		asstDetailinfo[3] = get_asset_Typeinfo();
		asstDetailinfo[4] = get_asset_LastValidatedinfo();
		
		return asstDetailinfo;
	}
	
	//Enum for Month field
	public enum Month {
		Jan(01), Feb(02), Mar(03), Apr(04), May(05), Jun(06), Jul(07), Aug(8), Sep(9), Oct(10), Nov(11), Dec(12);
		private int value;  
		private Month(int value){  
		this.value=value;  
		}  

		//"04", "05", "06", "07", "08", "09", "10", "11", "12";		

		int month(String x) {
			switch (this) {
			case Jan:
				return 01;
			case Feb:
				return 02;			
			case Mar:
				return 03;
			case Apr:
				return 04;
			case May:
				return 05;
			case Jun:
				return 06;
			case Jul:
				return 07;
			case Aug:
				return 8;
			case Sep:
				return 9;
			case Oct:
				return 10;
			case Nov:
				return 11;
			case Dec:
				return 12;
			default:
				throw new AssertionError("Unknown Month " + this);
			}
		}

	}


}
