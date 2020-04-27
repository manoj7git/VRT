package com.vrt.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.base.BaseClass;

public class Setup_SensorDescriptionPage extends BaseClass {
	
	// Define Setup page element variable declaration definition
	private WebElement SensorDescriptionPageName = null;
	private WebElement SelectSensorTypeBy_field = null;
	private WebElement PageCloseIcon_field = null;
	private WebElement SerialNo_field = null;
	private WebElement Label_field = null;
	private WebElement SensorType_field = null;
	private WebElement Description_rightpanefield = null;
	private WebElement Description_Leftpanefield = null;
	private WebElement Type_field = null;
	private WebElement Close_button = null;
	private WebElement Ok_button = null;
	private WebElement AutoNumberCheckBox = null;
	private WebElement SensorType_ComboBox = null;
		
	
	
	void initializeEelements() {
		SensorDescriptionPageName = driver.findElementByName("Sensor Description");
		SelectSensorTypeBy_field = driver.findElementByName("Select Sensor Type By :");
		PageCloseIcon_field = driver.findElementByAccessibilityId("CloseButton");
		SerialNo_field = driver.findElementByName("No.");
		Label_field = driver.findElementByName("Label");
		Type_field = driver.findElementByName("Type");
		Type_field = driver.findElementByName("Type");
		Description_Leftpanefield = driver.findElementByName("Description");
		Description_rightpanefield = driver.findElementByName("Description: ");
		Close_button = driver.findElementByName("Close");
		Ok_button = driver.findElementByName("OK");
		AutoNumberCheckBox = driver.findElementByAccessibilityId("SensorLabelAutoNumberCheckBox");
		SensorType_ComboBox = driver.findElementByAccessibilityId("SensorTypeSimComboBox");
		
	}
	
	Setup_SensorDescriptionPage() throws IOException {
		super();
		initializeEelements();
	}
	// Check the presence of SensorDescription page
	public boolean IsSensorDescriptionPage_Visible() {
		return IsElementVisibleStatus(SensorDescriptionPageName);
	}
	
	// Check the presence of SensorDescription page
		public boolean IsSelectSensorTypeBy_Visible() {
			return IsElementVisibleStatus(SelectSensorTypeBy_field);
		}
		
	// Check the presence of Close Icon 
				public boolean IsPageCloseIcon_field_Visible() {
					return IsElementVisibleStatus(PageCloseIcon_field);
				}	
		
	// Check the presence of Serial No
				public boolean IsSerialNo_field_Visible() {
					return IsElementVisibleStatus(SerialNo_field);
				}	
				
	// Check the presence of Label 
				public boolean IsLabel_Visible() {
					return IsElementVisibleStatus(Label_field);
				}
				
	// Check the presence of Type_field
				public boolean IsType_field_Visible() {
					return IsElementVisibleStatus(Type_field);
				}
				
	// Check the presence of SensorType_field
				public boolean IsSensorType_field_Visible() {
					return IsElementVisibleStatus(SensorType_field);
				}				
				
	// Check the presence of Description_field 
				public boolean IsLeftPane_Description_field_Visible() {
					return IsElementVisibleStatus(Description_Leftpanefield);
				}
				
	// Check the presence of Right pane Description_field 
				public boolean IsRightpane_Description_field_Visible() {
					return IsElementVisibleStatus(Description_rightpanefield);
				}	
				
	// Check the presence of Close_button
				public boolean IsClose_buttonVisible() {
					return IsElementVisibleStatus(Close_button);
				}	
							
	// Check the presence of OK_button
				public boolean IsOk_buttonVisible() {
					return IsElementVisibleStatus(Ok_button);
				}
	// Check the presence of OK_button
				public boolean Is_SensorLabelAutoNumber_checked() {
					return checkboxSelectStatus(AutoNumberCheckBox);
				}
				
	//get text from type field	
			
				public String get_Typetext() {
					List<WebElement> senrList = driver.findElementByAccessibilityId("PART_ScrollViewer").findElements(By.className("TextBlock"));
					return FetchText(senrList.get(2));
				}	
				
	//Click on SensorType_ComboBox
				
				public void click_SensorType_Field(){
					clickOn(SensorType_ComboBox);
					}
				
	//Check Temp displayed in combo box
				public boolean IsTemp_Incombobox_Visible() {
					List<WebElement> TempIncombobox = driver.findElementsByClassName("ComboBoxItem");
					return IsElementVisibleStatus(TempIncombobox.get(1));
				}
	//Check Hmd displayed in combo box
				public boolean IsHmd_Incombobox_Visible() {
					List<WebElement> HmdIncombobox = driver.findElementsByClassName("ComboBoxItem");
					return IsElementVisibleStatus(HmdIncombobox.get(2));
				}
				
	//Check Pressure displayed in combo box
				public boolean IsPres_Incombobox_Visible() {
					List<WebElement> PresIncombobox = driver.findElementsByClassName("ComboBoxItem");
					return IsElementVisibleStatus(PresIncombobox.get(3));
				}
		//txtFrom
				public String get_from_text() {
					WebElement txtFrom_field = driver.findElementByAccessibilityId("txtFrom");
					return FetchText(txtFrom_field);
				}
		//txtFrom
				public String get_To_text() {
					WebElement txtTo_field = driver.findElementByAccessibilityId("txtTo");
					return FetchText(txtTo_field);
				}
}
				
		//isSelected()		
				
