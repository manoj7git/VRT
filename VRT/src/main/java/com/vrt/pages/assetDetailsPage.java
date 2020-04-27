/**
 * @author manoj.ghadei
 *
 */

package com.vrt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import org.openqa.selenium.WebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vrt.base.BaseClass;
import com.vrt.utility.TestUtilities;

public class assetDetailsPage extends BaseClass {

	TestUtilities TU = new TestUtilities();
	// Asset Details element variable declaration definition
	WebElement AssetDetailPageTitle = null;
	WebElement AssetDetail_IDinfo = null;
	WebElement AssetDetail_Mdlinfo = null;
	WebElement AssetDetail_Mfginfo = null;
	WebElement AssetDetail_Typeinfo = null;
	WebElement AssetDetail_Dateinfo = null;
	WebElement BackBtn = null;
	WebElement AssetEditBtn = null;
	WebElement Asset_SetupTile = null;
	WebElement Asset_QualTile = null;
	WebElement Asset_ReportsTile = null;
	WebElement Asset_DocsTile = null;
	WebElement SetupTile_Count = null;
	WebElement QualTile_Count = null;
	WebElement ReportsTile_Count = null;
	WebElement DocsTile_Count = null;
	WebElement UploadDocs_Btn = null;
	WebElement VRTappClose_Btn = null;
	WebElement InitiateQual_Btn = null;
	WebElement SetupsHeaderTxt = null;
	WebElement NewSetupCreate_Btn = null;
	WebElement CopySetup_Btn = null;
	WebElement AssetEdit_Btn = null;
	WebElement AssetHub_ImgHldr = null;
	WebElement AssetHub_ImgHldr_NextBtn = null;
	WebElement AssetHub_ImgHldr_PrvsBtn = null;

	private void initElements() {
		AssetDetailPageTitle = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetDetail_IDinfo = driver.findElementByAccessibilityId("EquipmentIDTextBlock");
		AssetDetail_Mdlinfo = driver.findElementByAccessibilityId("ModelNoTextBlock");
		AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
		AssetDetail_Typeinfo = driver.findElementByAccessibilityId("TypeTextBlock");
		AssetDetail_Dateinfo = driver.findElementByAccessibilityId("LastValidationTextBlock");
		BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
		AssetEditBtn = driver.findElementByAccessibilityId("EditAssetsButton");
		Asset_SetupTile = driver.findElementByAccessibilityId("SetupsButton");
		Asset_QualTile = driver.findElementByAccessibilityId("QualificationsButton");
		Asset_ReportsTile = driver.findElementByAccessibilityId("ReportsButton");
		Asset_DocsTile = driver.findElementByAccessibilityId("DocumentsButton");
		SetupTile_Count = driver.findElementByAccessibilityId("SetupsCountTextBlock");
		QualTile_Count = driver.findElementByAccessibilityId("QualificationCountTextBlock");
		ReportsTile_Count = driver.findElementByAccessibilityId("ReportsCountTextBlock");
		DocsTile_Count = driver.findElementByAccessibilityId("DocumentsCountTextBlock");
		VRTappClose_Btn = driver.findElementByAccessibilityId("Close");
		InitiateQual_Btn = driver.findElementByAccessibilityId("StartQualificationButton");
		SetupsHeaderTxt = driver.findElementByAccessibilityId("SetupsHeaderTextBlock");
		NewSetupCreate_Btn = driver.findElementByAccessibilityId("CreateSetupButton");
		CopySetup_Btn = driver.findElementByAccessibilityId("CopySetupButton");
		AssetEdit_Btn = driver.findElementByAccessibilityId("EditAssetsButton");
		AssetHub_ImgHldr = driver.findElementByClassName("Image");

	}

	assetDetailsPage() throws IOException {
		super();
		initElements();
	}

	// Check the presence of Setup tile
	public boolean setupTile_state() {
		return IsElementVisibleStatus(Asset_SetupTile);
	}

	// Check the Setup tile default selected
	public String get_Setupheader_txt() {
		return FetchText(SetupsHeaderTxt);
	}

	// Get the Setup count data form the Setup tile
	public String setupTile_countdata() {
		return FetchText(SetupTile_Count);
	}

	// click on the setup tile
	public void click_SetupTile() {
		clickOn(Asset_SetupTile);
	}
	
	// click on any setup file
	public void click_Setupfile(String SteupName) {
		WebElement Setup = driver.findElementByName(SteupName);
		clickOn(Setup);
	}

	// Check the enable state of Initiate Qual button under Setup tile
	public boolean InitiateQualBtn_state() {
		return IsElementEnabledStatus(InitiateQual_Btn);
	}

	// Click the Initiate Qual button under Setup tile
	public void click_InitiateQualBtn() {
		clickOn(InitiateQual_Btn);
	}

	// Check the presence of Qual tile
	public boolean qualTile_state() {
		return IsElementVisibleStatus(Asset_QualTile);
	}

	// click on the Qual tile
	public void click_QualTile() {
		clickOn(Asset_QualTile);
	}

	// Click on delete icon from the qualsection
	public void click_DeleteBtn() {
		WebElement Deletestudy = driver.findElementByAccessibilityId("DeleteButton");
		clickOn(Deletestudy);
	}

	// Check the presence of Delete Popup Window
	public boolean DeletePopupWindowVisible() {
		WebElement Deletewindow = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(Deletewindow);
	}

	// Get the Qual count data form the Setup tile
	public String qualTile_countdata() {
		return FetchText(QualTile_Count);
	}
	
	//select the qual under qual tile
	public void qualfile() {
		WebElement qualbox = driver.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel");
		clickOn(qualbox);
	}
	
    //click on GenerateReportsForQualButton
	 public void GenerateReportsBtn() {
		WebElement GenerateReports = driver.findElementByAccessibilityId("GenerateReportsForQualButton");
		clickOn(GenerateReports);
	}	
	 
	// Check the presence of Reports tile
	public boolean reportsTile_state() {
		return IsElementVisibleStatus(Asset_ReportsTile);
	}
	
	//Click on Copy to drive
	public void click_Copytodrive() {
		WebElement CopytodriveBox = driver.findElementByName("Copy to drive");
		clickOn(CopytodriveBox);
	}
	
	//Check the presence of Select Folder window is visible
	
	public boolean SelectFolder_PopupWindowVisible() {
		WebElement SelectFolderWindow = driver.findElementByName("Select Folder");
		return IsElementVisibleStatus(SelectFolderWindow);
	}

	// click on the Reports tile
	public void Click_reportsTile() {
		clickOn(Asset_ReportsTile);
	}

	// click on the qualification sub section from Reports tile
	public void Click_QualUnderReport() {
		WebElement QualUnderReport = driver.findElementByAccessibilityId("QualificationReportsButton");
		clickOn(QualUnderReport);
	}

	// click on the Pass/Fail sub section from Reports tile
	public void Click_PassFailReport() {
		WebElement PassFailReport = driver.findElementByAccessibilityId("PassFailReportsButton");
		clickOn(PassFailReport);
	}

	// click on the setup sub section from Reports tile
	public void Click_SetupUnderReport() {
		WebElement SetupUnderReport = driver.findElementByAccessibilityId("SetupReportsButton");
		clickOn(SetupUnderReport);
	}

	// Get the Reports count data form the Setup tile
	public String reportsTile_countdata() {
		return FetchText(ReportsTile_Count);
	}

	// Check the presence of Docs tile
	public boolean docsTile_state() {
		return IsElementVisibleStatus(Asset_DocsTile);
	}

	// Get the Docs count data form the Docs tile
	public String docsTile_countdata() {
		return FetchText(DocsTile_Count);
	}

	// Click the Asset Docs tile button
	public void click_DocsTileBtn() throws InterruptedException {
		clickOn(Asset_DocsTile);
		Thread.sleep(1000);
	}

	// Click the Upload Docs button under Assets Docs tile
	public void click_UploadDocsBtn() throws InterruptedException {
		UploadDocs_Btn = driver.findElementByAccessibilityId("UploadDocumentsButton");
		clickOn(UploadDocs_Btn);
		Thread.sleep(2000);
	}

	// Get the Asset details Page header data for the corresponding Asset
	public String assetDetail_PageTitle() {
		return FetchText(AssetDetailPageTitle);
	}

	// Get the Asset ID data for the corresponding Asset
	public String get_asset_IDinfo() {
		return FetchText(AssetDetail_IDinfo);
	}

	// Get the Asset Model data for the corresponding Asset
	public String get_asset_Modelinfo() {
		return FetchText(AssetDetail_Mdlinfo);
	}

	// Get the Asset Manufacturer data for the corresponding Asset
	public String get_asset_Mfginfo() {
		return FetchText(AssetDetail_Mfginfo);
	}

	// Get the Asset Type data for the corresponding Asset
	public String get_asset_Typeinfo() {
		return FetchText(AssetDetail_Typeinfo);
	}

	// Get the Asset Last Validated data for the corresponding Asset
	public String get_asset_LastValidatedinfo() throws ParseException {
		String Dt = FetchText(AssetDetail_Dateinfo);
		return TU.convert_StringDate_to_ActualDate_inCertainFormat(Dt);
	}

	// Check the presence of Asset edit button
	public boolean assetEditBtn_state() {
		return IsElementVisibleStatus(AssetEditBtn);
	}

	// Click the Asset edit button
	public assetCreationPage click_assetEditBtn() throws InterruptedException, IOException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}

	// Click the Asset edit button to get alert msg..when admin does not have the
	// privilege to edit the asset
	public void click_assetEditBtn_alrt() throws InterruptedException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
	}

	// Fetch the alert message when admin not have the privilege to edit the asset
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Click the Back Button
	public assetHubPage ClickBackBtn() throws InterruptedException, IOException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new assetHubPage();
	}

	// Get the Asset info in Asset details page
	public String[] get_assetinfo() throws ParseException {
		String[] asstDetailinfo = new String[6];
		asstDetailinfo[0] = get_asset_IDinfo();
		asstDetailinfo[1] = get_asset_Modelinfo();
		asstDetailinfo[2] = get_asset_Mfginfo();
		asstDetailinfo[3] = get_asset_Typeinfo();
		asstDetailinfo[4] = get_asset_LastValidatedinfo();
		String[] AsstTitle = assetDetail_PageTitle().split(" - ");
		asstDetailinfo[5] = AsstTitle[1];

		return asstDetailinfo;
	}

	// Click new Setup create button
	public Setup_defineSetupPage click_NewStupCreateBtn() throws InterruptedException, IOException {
		clickOn(NewSetupCreate_Btn);
		Thread.sleep(1000);
		return new Setup_defineSetupPage();
	}

	// Fetch the alert message when does not have access to on new Setup create
	// button
	public void click_NewStupCreateBtn_alert() throws InterruptedException {
		clickOn(NewSetupCreate_Btn);
		Thread.sleep(1000);
	}

	// Click Copy Setup button
	public void click_CopyStup_Btn() throws InterruptedException {
		clickOn(CopySetup_Btn);
		Thread.sleep(1000);
	}

	/*
	 * // Click the Asset Edit button public assetCreationPage click_AssetEditBtn()
	 * throws InterruptedException { clickOn(AssetEdit_Btn); Thread.sleep(1000);
	 * return new assetCreationPage(); }
	 */

	// Click VRT app close button
	public void click_vrtAppcloseBtn() throws InterruptedException {
		clickOn(VRTappClose_Btn);
		Thread.sleep(1000);
	}

	// Upload Documents method under Asset details page
	public void uploadDoc_Assetdetails(String filename) throws AWTException, IOException, InterruptedException {

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\TestData\\" + filename;
		// System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(1000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
	}

	// Verify if duplicate doc alert message displayed
	public String alertMeg_duplicateDocupload_Assetdetails() throws InterruptedException {

		WebElement alertMsg = driver
				.findElementByName("Another file with same name already exists. Please use different name.");
		Thread.sleep(1000);
		// System.out.println(alertMsg.getAttribute("Name"));
		String msg = alertMsg.getAttribute("Name");
		return msg;
	}

	// Verify if More than One Asset required for copy setup alert message displayed
	public String alertMeg_CopyAsset_WithOneAsset() throws InterruptedException {

		WebElement alertMsg = driver.findElementByName("To perform Copy Setup more than 1 asset required.");
		Thread.sleep(1000);
		// System.out.println(alertMsg.getAttribute("Name"));
		String msg = alertMsg.getAttribute("Name");
		return msg;
	}

	// click Next button of the Asset Image Holder
	public void click_ImgNextkBtn() throws InterruptedException {
		clickOn(AssetHub_ImgHldr);
		Thread.sleep(500);
		AssetHub_ImgHldr_NextBtn = driver.findElementByAccessibilityId("NextButtonHorizontal");
		clickOn(AssetHub_ImgHldr_NextBtn);
		clickOn(SetupsHeaderTxt);
		Thread.sleep(5000);
		// clickOn(AssetHub_ImgHldr);
	}

	// click Previous button of the Asset Image Holder
	public void click_ImgPrevkBtn() throws InterruptedException {
		clickOn(AssetHub_ImgHldr);
		Thread.sleep(500);
		AssetHub_ImgHldr_PrvsBtn = driver.findElementByAccessibilityId("PreviousButtonHorizontal");
		clickOn(SetupsHeaderTxt);
		Thread.sleep(5000);
		// clickOn(AssetHub_ImgHldr);
	}

	public void Capture_AsstImg(String Img_Name) throws IOException {
		TestUtilities tu = new TestUtilities();
		tu.capture_element_screenshot(driver, AssetHub_ImgHldr, "TestData", Img_Name);
	}

	// Click on Delete Icon
	public void DeleteAssert() {
		WebElement DeleteIcon = driver.findElementByAccessibilityId("DeleteAssetsButton");
		clickOn(DeleteIcon);
	}

	// Verify delete pop up window displayed
	public boolean Deletepopupwindow() {
		WebElement dp = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(dp);
	}

}
