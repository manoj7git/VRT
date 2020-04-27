package com.vrt.pages;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vrt.utility.TestUtilities;

import com.vrt.base.BaseClass;

public class Setup_QualParamPage extends BaseClass {
	TestUtilities tu = new TestUtilities();
	
	// Qualification Parameters page element variable declaration definition	
	WebElement QualParamsPageTitle = null;
	WebElement QStart_text = null;
	WebElement QStart_DrpDwn = null;
	WebElement QStop_text = null;
	WebElement QStop_DrpDwn = null;
	WebElement SR_text = null;
	WebElement SR_DrpDwn = null;
	WebElement TR_text = null;
	WebElement TR_DrpDwn = null;
	WebElement RFT_DrpDwn = null;
	WebElement RFT_text = null;
	WebElement NxtBtn = null;

	private void initializeEelements() {
		QualParamsPageTitle = driver.findElementByName("Qualification Parameters");
		QStart_text = driver.findElementByAccessibilityId("StartQualificationTextBlock");
		QStart_DrpDwn = driver.findElementByAccessibilityId("StartQualificationComboBox");
		QStop_text = driver.findElementByAccessibilityId("StopQualificationTextBlock");
		QStop_DrpDwn = driver.findElementByAccessibilityId("StopQualificationComboBox");
		SR_text = driver.findElementByName("Sampling Rate");
		SR_DrpDwn = driver.findElementByAccessibilityId("FileRateComboBox");
		TR_text = driver.findElementByName("Transmission Rate");
		TR_DrpDwn = driver.findElementByAccessibilityId("DisplayRate");
		RFT_text = driver.findElementByName("RF Transmit Threshold");
		RFT_DrpDwn = driver.findElementByAccessibilityId("_rfCommToggleThresholdSettingsComboBox");
		NxtBtn = driver.findElementByAccessibilityId("NextButton");
		
		
	}

	Setup_QualParamPage() throws IOException {
		super();
		initializeEelements();
	}

	// Release memory
	public void resetWebElements() {
		QualParamsPageTitle = null;
		QStart_text = null;
		QStart_DrpDwn = null;
		QStop_text = null;
		QStop_DrpDwn = null;
		SR_text = null;
		SR_DrpDwn = null;
		TR_text = null;
		TR_DrpDwn = null;
		RFT_DrpDwn = null;
		RFT_text = null;
		NxtBtn = null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Qual parameters Page methods
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Check the presence of Qualification Parameters page
	public boolean QualParamsPage_state() {
		return IsElementVisibleStatus(QualParamsPageTitle);
	}

	// Get the Qualification Parameters page title text
	public String get_QualParamsPage_titletext() {
		return FetchText(QualParamsPageTitle);
	}

	// Click the Qual Start dropdown box
	public void click_Qstart_DrpDwnBox() {
		clickOn(QStart_DrpDwn);
	}

	// Click the Qual Stop dropdown box
	public void click_Qstop_DrpDwnBox() {
		clickOn(QStop_DrpDwn);
	}

	// Select TOD Qual Start parameter with how much Date/time ahead one wants to
	// set to start Qual
	// If one wants to add 1 to yr/Mnth/Dt/Hr/Mnt/Sec, then enter the parameters
	// like
	// Time of the Day, 1,1,1,1,1,1,1
	public void select_QualStart_condition(String QStart, int dt, int mnth, int Yr, int hr, int mnt, int sec)
			throws InterruptedException, ParseException {
		Actions ac = new Actions(driver);
		if (QStart.equals("Time Of Day")) {
			click_Qstart_DrpDwnBox();
			Thread.sleep(1000);
			WebElement QSt_TOD = driver.findElementByName("Time Of Day");
			clickOn(QSt_TOD);
			Thread.sleep(500);
			enter_TOD_QualStart(dt, mnth, Yr, hr, mnt, sec);
		}
	}

	// Enter default time of the day by adding 1 Hr to QStart parameter
	public void enter_TOD_QualStart(int dt, int mnth, int Yr, int hr, int mnt, int sec)
			throws InterruptedException, ParseException {

		WebElement TOD_Hr = driver.findElementByAccessibilityId("Hours");
		WebElement TOD_Mnt = driver.findElementByAccessibilityId("Min");
		WebElement TOD_Sec = driver.findElementByAccessibilityId("Sec");

		Actions ac = new Actions(driver);
		WebElement TOD_DtPkr = driver.findElementByAccessibilityId("PART_PickerButton");
		String TOD_DtTxt11 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
		////System.out.println("TOD_DtTxt11: " + TOD_DtTxt11);
		String TOD_dd1 = TOD_DtTxt11.split("-")[0];
		////System.out.println("TOD_dd1: " + TOD_dd1);
		String TOD_mm1 = TOD_DtTxt11.split("-")[1];
		////System.out.println("TOD_mm1: " + TOD_mm1);

		if (TOD_dd1.equals("31") && TOD_mm1.equals("12")) {
			clickOn(TOD_DtPkr);
			Thread.sleep(500);
			ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN)
					.build().perform();
			clickOn(TOD_DtPkr);
			ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(500);

			String TOD_DtTxt22 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
			////System.out.println("TOD_DtTxt22:" + TOD_DtTxt22);
			String TOD_dd2 = TOD_DtTxt22.split("-")[0];
			//System.out.println("TOD_dd2:" + TOD_dd2);
			String TOD_mm2 = TOD_DtTxt22.split("-")[1];
			//System.out.println("TOD_mm2: " + TOD_mm2);

			//System.out.println("Is TOD_dd1 = TOD_dd2: " + TOD_dd1.equals(TOD_dd2));

			if (!TOD_dd1.equals(TOD_dd2) && TOD_mm2.equals(TOD_mm1)) {
				clickOn(TOD_DtPkr);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				clickOn(TOD_DtPkr);
				ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN)
						.build().perform();
				Thread.sleep(500);

				String TOD_DtTxt33 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
				//System.out.println("TOD_DtTxt33: " + TOD_DtTxt33);
				String TOD_dd3 = TOD_DtTxt33.split("-")[0];
				//System.out.println("TOD_dd3: " + TOD_dd3);
				String TOD_mm3 = TOD_DtTxt33.split("-")[1];
				//System.out.println("TOD_mm3: " + TOD_mm3);
				String TOD_yy3 = TOD_DtTxt33.split("-")[2];
				//System.out.println("TOD_yy3: " + TOD_yy3);

				String newDtTime = tu.add_to_Crrnt_DateandTimeStamp(dt, mnth, Yr, hr, mnt, sec);
				//System.out.println("Expected newDtTime: " + newDtTime);
				String[] newDtTime1 = newDtTime.split(":");
				//System.out.println("New Day: " + newDtTime1[0]);
				//System.out.println("New Mnth: " + newDtTime1[1]);
				//System.out.println("New Yr: " + newDtTime1[2]);
				String final_dd = TOD_dd3;
				String final_mm = TOD_mm3;
				String final_yy = TOD_yy3;

				// Change the Year
				while (!newDtTime1[2].equals(final_yy)) {
					clickOn(TOD_DtPkr);
					ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN)
							.sendKeys(Keys.RETURN).build().perform();
					Thread.sleep(500);
					String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
					final_yy = f_TOD_DtTxt.split("-")[2];
					//System.out.println("final_yy: " + final_yy);
				}
				// Change the Month
				for (int i = 1; i <= 12; i++) {
					if (newDtTime1[1].equals(final_mm)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_mm = f_TOD_DtTxt.split("-")[1];
						//System.out.println("final_mm: " + final_mm);
					}
				}
				// Change the Date
				for (int j = 1; j <= 31; j++) {
					if (newDtTime1[0].equals(final_dd)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_dd = f_TOD_DtTxt.split("-")[0];
						//System.out.println("final_dd: " + final_dd);
					}
				}

				// Change the Hour
				clickOn(TOD_Hr);
				ClearText(TOD_Hr);
				enterText(TOD_Hr, newDtTime1[3]);
				// Change the Minute
				clickOn(TOD_Mnt);
				ClearText(TOD_Mnt);
				enterText(TOD_Mnt, newDtTime1[4]);
				// Change the Sec
				clickOn(TOD_Sec);
				ClearText(TOD_Sec);
				enterText(TOD_Sec, newDtTime1[5]);
				Thread.sleep(1000);
				String TOD_DtTxt4 = TOD_DtPkr.getText();
				String TOD_DtTxt44 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt4);
				//System.out.println("Final TOD selection:" + TOD_DtTxt44 + ":" + TOD_Hr.getText() + ":"
				//		+ TOD_Mnt.getText() + ":" + TOD_Sec.getText());
				clickOn(QStart_text);
				
			} else {
				//System.out.println("else H1 TOD_DtPkr:" + TOD_DtPkr.getText());
				clickOn(TOD_DtPkr);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				clickOn(TOD_DtPkr);
				ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN)
						.build().perform();
				Thread.sleep(500);

				String TOD_DtTxt3 = TOD_DtPkr.getText();
				String TOD_DtTxt33 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt3);
				//System.out.println(TOD_DtTxt33);
				String TOD_dd3 = TOD_DtTxt33.split("-")[0];
				//System.out.println(TOD_dd3);
				String TOD_mm3 = TOD_DtTxt33.split("-")[1];
				//System.out.println(TOD_mm3);
				String TOD_yy3 = TOD_DtTxt33.split("-")[2];
				//System.out.println(TOD_yy3);

				String newDtTime = tu.add_to_Crrnt_DateandTimeStamp(dt, mnth, Yr, hr, mnt, sec);
				//System.out.println("Expected newDtTime: " + newDtTime);
				String[] newDtTime1 = newDtTime.split(":");
				//System.out.println("New Day: " + newDtTime1[0]);
				//System.out.println("New Mnth: " + newDtTime1[1]);
				//System.out.println("New Yr: " + newDtTime1[2]);
				String final_dd = TOD_dd3;
				String final_mm = TOD_mm3;
				String final_yy = TOD_yy3;

				// Change the Year
				while (!newDtTime1[2].equals(final_yy)) {
					clickOn(TOD_DtPkr);
					ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN)
							.sendKeys(Keys.RETURN).build().perform();
					Thread.sleep(500);
					String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
					final_yy = f_TOD_DtTxt.split("-")[2];
					//System.out.println(final_yy);
				}
				// Change the Month
				for (int i = 1; i <= 12; i++) {
					if (newDtTime1[1].equals(final_mm)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_mm = f_TOD_DtTxt.split("-")[1];
						//System.out.println(final_mm);
					}
				}
				// Change the Date
				for (int j = 1; j <= 31; j++) {
					if (newDtTime1[0].equals(final_dd)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_dd = f_TOD_DtTxt.split("-")[0];
						//System.out.println(final_dd);
					}
				}

				// Change the Hour
				clickOn(TOD_Hr);
				ClearText(TOD_Hr);
				enterText(TOD_Hr, newDtTime1[3]);
				// Change the Minute
				clickOn(TOD_Mnt);
				ClearText(TOD_Mnt);
				enterText(TOD_Mnt, newDtTime1[4]);
				// Change the Sec
				clickOn(TOD_Sec);
				ClearText(TOD_Sec);
				enterText(TOD_Sec, newDtTime1[5]);
				Thread.sleep(1000);
				String TOD_DtTxt4 = TOD_DtPkr.getText();
				String TOD_DtTxt44 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt4);
				//System.out.println("Final TOD selection:" + TOD_DtTxt44 + ":" + TOD_Hr.getText() + ":"
				//		+ TOD_Mnt.getText() + ":" + TOD_Sec.getText());
				clickOn(QStart_text);
			}

		} else {
			// Verifying If the DtPkr 1st item is same as the items reflected in the DtPkt
			// txt box
			// And if not accordingly selecting the DD/MM.
			// This code will work where the Host system has got difference in the 1st Item
			// of DtPckr
			clickOn(TOD_DtPkr);
			Thread.sleep(500);
			ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(500);

			String TOD_DtTxt22 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
			//System.out.println("TOD_DtTxt22:" + TOD_DtTxt22);
			String TOD_dd2 = TOD_DtTxt22.split("-")[0];
			//System.out.println("TOD_dd2:" + TOD_dd2);
			String TOD_mm2 = TOD_DtTxt22.split("-")[1];
			//System.out.println("TOD_mm2: " + TOD_mm2);

			//System.out.println("Is TOD_dd1 = TOD_dd2: " + TOD_dd1.equals(TOD_dd2));

			if (!TOD_dd1.equals(TOD_dd2) && TOD_mm2.equals(TOD_mm1)) {
				clickOn(TOD_DtPkr);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);

				String TOD_DtTxt33 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
				//System.out.println("TOD_DtTxt33: " + TOD_DtTxt33);
				String TOD_dd3 = TOD_DtTxt33.split("-")[0];
				//System.out.println("TOD_dd3: " + TOD_dd3);
				String TOD_mm3 = TOD_DtTxt33.split("-")[1];
				//System.out.println("TOD_mm3: " + TOD_mm3);
				String TOD_yy3 = TOD_DtTxt33.split("-")[2];
				//System.out.println("TOD_yy3: " + TOD_yy3);

				String newDtTime = tu.add_to_Crrnt_DateandTimeStamp(dt, mnth, Yr, hr, mnt, sec);
				//System.out.println("Expected newDtTime: " + newDtTime);
				String[] newDtTime1 = newDtTime.split(":");
				//System.out.println("New Day: " + newDtTime1[0]);
				//System.out.println("New Mnth: " + newDtTime1[1]);
				//System.out.println("New Yr: " + newDtTime1[2]);
				String final_dd = TOD_dd3;
				String final_mm = TOD_mm3;
				String final_yy = TOD_yy3;

				// Change the Year
				while (!newDtTime1[2].equals(final_yy)) {
					clickOn(TOD_DtPkr);
					ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN)
							.sendKeys(Keys.RETURN).build().perform();
					Thread.sleep(500);
					String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
					final_yy = f_TOD_DtTxt.split("-")[2];
					//System.out.println(final_yy);
				}
				// Change the Month
				for (int i = 1; i <= 12; i++) {
					if (newDtTime1[1].equals(final_mm)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_mm = f_TOD_DtTxt.split("-")[1];
						//System.out.println(final_mm);
					}
				}
				// Change the Date
				for (int j = 1; j <= 31; j++) {
					if (newDtTime1[0].equals(final_dd)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_dd = f_TOD_DtTxt.split("-")[0];
						//System.out.println(final_dd);
					}
				}

				// Change the Hour
				clickOn(TOD_Hr);
				ClearText(TOD_Hr);
				enterText(TOD_Hr, newDtTime1[3]);
				// Change the Minute
				clickOn(TOD_Mnt);
				ClearText(TOD_Mnt);
				enterText(TOD_Mnt, newDtTime1[4]);
				// Change the Sec
				clickOn(TOD_Sec);
				ClearText(TOD_Sec);
				enterText(TOD_Sec, newDtTime1[5]);
				Thread.sleep(1000);
				String TOD_DtTxt4 = TOD_DtPkr.getText();
				String TOD_DtTxt44 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt4);
				//System.out.println("Final TOD selection:" + TOD_DtTxt44 + ":" + TOD_Hr.getText() + ":"
				//		+ TOD_Mnt.getText() + ":" + TOD_Sec.getText());
				clickOn(QStart_text);
			} else {
				//System.out.println("else part-TOD_DtPkr:" + TOD_DtPkr.getText());
				clickOn(TOD_DtPkr);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);

				String TOD_DtTxt3 = TOD_DtPkr.getText();
				String TOD_DtTxt33 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt3);
				//System.out.println(TOD_DtTxt33);
				String TOD_dd3 = TOD_DtTxt33.split("-")[0];
				//System.out.println(TOD_dd3);
				String TOD_mm3 = TOD_DtTxt33.split("-")[1];
				//System.out.println(TOD_mm3);
				String TOD_yy3 = TOD_DtTxt33.split("-")[2];
				//System.out.println(TOD_yy3);

				String newDtTime = tu.add_to_Crrnt_DateandTimeStamp(dt, mnth, Yr, hr, mnt, sec);
				//System.out.println("Expected newDtTime: " + newDtTime);
				String[] newDtTime1 = newDtTime.split(":");
				//System.out.println("New Day: " + newDtTime1[0]);
				//System.out.println("New Mnth: " + newDtTime1[1]);
				//System.out.println("New Yr: " + newDtTime1[2]);
				String final_dd = TOD_dd3;
				String final_mm = TOD_mm3;
				String final_yy = TOD_yy3;

				// Change the Year
				while (!newDtTime1[2].equals(final_yy)) {
					clickOn(TOD_DtPkr);
					ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN)
							.sendKeys(Keys.RETURN).build().perform();
					Thread.sleep(500);
					String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
					final_yy = f_TOD_DtTxt.split("-")[2];
					//System.out.println(final_yy);
				}
				// Change the Month
				for (int i = 1; i <= 12; i++) {
					if (newDtTime1[1].equals(final_mm)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_mm = f_TOD_DtTxt.split("-")[1];
						//System.out.println(final_mm);
					}
				}
				// Change the Date
				for (int j = 1; j <= 31; j++) {
					if (newDtTime1[0].equals(final_dd)) {
						break;
					} else {
						clickOn(TOD_DtPkr);
						ac.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
						Thread.sleep(500);
						String f_TOD_DtTxt = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtPkr.getText());
						final_dd = f_TOD_DtTxt.split("-")[0];
						//System.out.println(final_dd);
					}
				}

				// Change the Hour
				clickOn(TOD_Hr);
				ClearText(TOD_Hr);
				enterText(TOD_Hr, newDtTime1[3]);
				// Change the Minute
				clickOn(TOD_Mnt);
				ClearText(TOD_Mnt);
				enterText(TOD_Mnt, newDtTime1[4]);
				// Change the Sec
				clickOn(TOD_Sec);
				ClearText(TOD_Sec);
				enterText(TOD_Sec, newDtTime1[5]);
				Thread.sleep(1000);
				String TOD_DtTxt4 = TOD_DtPkr.getText();
				String TOD_DtTxt44 = tu.convert_StringDate_to_ActualDate_inCertainFormat3(TOD_DtTxt4);
				//System.out.println("Final TOD selection:" + TOD_DtTxt44 + ":" + TOD_Hr.getText() + ":"
				//		+ TOD_Mnt.getText() + ":" + TOD_Sec.getText());
				clickOn(QStart_text);
			}
		}
	}

	// Select Qual Stop parameter
	public void select_QualStop_condition(String QStop, String Hrs, String Mnts, String Secs)
			throws InterruptedException {
		Actions ac = new Actions(driver);
		if (QStop.equals("Cycle Time")) {
			click_Qstop_DrpDwnBox();
			// click_Qstop_DrpDwnBox();
			Thread.sleep(500);

			// WebElement QSt_Manual = driver.findElementByName("Manual");
			WebElement QStp_CT = driver.findElementByName("Cycle Time");
			clickOn(QStp_CT);
			Thread.sleep(500);
			enter_CT_QualStop(Hrs, Mnts, Secs);
			Thread.sleep(1000);
		}
	}

	// Enter Cycle Time Hr/Mnts/Secs data to Qual Stop CT parameter
	public void enter_CT_QualStop(String Hrs, String Mnts, String Secs) {
		WebElement CT_Hr = driver.findElementByAccessibilityId("HoursStopQualification");
		WebElement CT_Mnt = driver.findElementByAccessibilityId("MinStopQualification");
		WebElement CT_Sec = driver.findElementByAccessibilityId("SecStopQualification");

		// Change the Hour
		clickOn(CT_Hr);
		ClearText(CT_Hr);
		enterText(CT_Hr, Hrs);
		// Change the Minute
		clickOn(CT_Mnt);
		ClearText(CT_Mnt);
		enterText(CT_Mnt, Mnts);
		// Change the Sec
		clickOn(CT_Sec);
		ClearText(CT_Sec);
		enterText(CT_Sec, Secs);
		//System.out.println("Final CT selection: " + CT_Hr.getText() + ":" + CT_Mnt.getText() + ":" + CT_Sec.getText());
		clickOn(QStop_text);
	}
	
	// Click the Sampling Rate dropdown box
	public void click_SR_DrpDwnBox() {
		clickOn(SR_DrpDwn);
	}	

	// Select Sampling Rate
	public void select_SR(String SR) throws InterruptedException {

		//System.out.println("SRField default data: " + SR_DrpDwn.getText());
		Actions ac = new Actions(driver);

		if (SR.equals("1 Second") || SR.equals("2 Seconds") || SR.equals("3 Seconds")) {
			for (int i = 0; i <= 2; i++) {
				click_SR_DrpDwnBox();
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				if (FetchText(SR_DrpDwn).contains(SR)) {
					clickOn(SR_text);
					break;
				}
			}
			//System.out.println("SR Field selected data: " + FetchText(SR_DrpDwn));
			//System.out.println("------------");
		} else if (SR.equals("5 Seconds")){
			//System.out.println("SR Field selected data: " + FetchText(SR_DrpDwn));
			//System.out.println("------------");
			Thread.sleep(500);
		} else {
			for (int i = 0; i <= 7; i++) {
				click_SR_DrpDwnBox();
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("SR Field data: " + FetchText(SR_DrpDwn));				
				if (FetchText(SR_DrpDwn).contains(SR)) {
					clickOn(SR_text);
					break;
				}				
			}
			//System.out.println("SR Field selected data: " + FetchText(SR_DrpDwn));
			//System.out.println("------------");
		}
	}

	// Click the Transmission Rate dropdown box
	public void click_TR_DrpDwnBox() {
		clickOn(TR_DrpDwn);
	}
	
	// Select Transmission Rate based on Sampling Rate selction
	public void select_TR(String SR, String TR) throws InterruptedException {
		//System.out.println("SR excel data: " + SR);
		//System.out.println("tr excel data: " + TR);
		//System.out.println("TRField default data: " + TR_DrpDwn.getText());
		Actions ac = new Actions(driver);

		if (SR.equals("1 Second")) {
			switch (TR) {
			case "3 Seconds":
				click_TR_DrpDwnBox();
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "5 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("2 Seconds")) {
			//System.out.println("In SR 2 sec & TR selection");
			switch (TR) {
			case "6 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("3 Seconds")) {
			//System.out.println("In SR 3 sec & TR selection");
			switch (TR) {
			case "6 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "12 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("5 Seconds")) {
			//System.out.println("In SR 5 sec & TR selection");
			switch (TR) {
			case "5 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "20 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("10 Seconds")) {
			//System.out.println("In SR 10 sec & TR selection");
			switch (TR) {
			case "20 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("20 Seconds")) {
			//System.out.println("In SR 20 sec & TR selection");
			switch (TR) {
			case "20 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("30 Seconds")) {
			//System.out.println("In SR 30 sec & TR selection");
			switch (TR) {
			case "20 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("1 Minute")) {
			//System.out.println("In SR 1 mnt & TR selection");
			switch (TR) {
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("2 Minutes")) {
			//System.out.println("In SR 2 mnt & TR selection");
			switch (TR) {
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("3 Minutes")) {
			//System.out.println("In SR 3 mnt & TR selection");
			switch (TR) {
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("5 Minutes")) {
			//System.out.println("In SR 5 mnt & TR selection");
			switch (TR) {
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else if (SR.equals("10 Minutes")) {
			switch (TR) {
			case "10 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			case "30 Seconds":
				clickOn(TR_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("TR Field selected data: " + FetchText(TR_DrpDwn));
				clickOn(TR_text);
				break;
			default:
				break;
			}
		} else {
			System.out.println("Incorrect TR input in excel");
		}

	}

	// Click the RFT dropdown box
	public void click_RFT_DrpDwnBox() {
		clickOn(RFT_DrpDwn);
	}
	
	// Select RF Threshold
	public void select_RFT(String RFT) throws InterruptedException {
		int rft = Integer.parseInt(RFT.split(",")[0]);
		//System.out.println("rft excel data: " + rft);
		int RFTField = Integer.parseInt(RFT_DrpDwn.getText().split(",")[0]);
		//System.out.println("RFTField default data: " + RFTField);
		Actions ac = new Actions(driver);

		if (rft > RFTField) {
			click_RFT_DrpDwnBox();
			Thread.sleep(500);
			ac.sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).build().perform();	
			clickOn(RFT_text);
			Thread.sleep(500);
			//System.out.println("RFT Field selected data: " + FetchText(RFT_DrpDwn));

		} else if (rft < RFTField) {
			for (int i = 0; i <= 2; i++) {
				clickOn(RFT_DrpDwn);
				Thread.sleep(500);
				ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(500);
				//System.out.println("RFT Field selected data: " + FetchText(RFT_DrpDwn));
				if (FetchText(RFT_DrpDwn).contains(RFT)) {
					clickOn(RFT_text);
					break;
				}
			}
		}
	}

	// Click the Next button to move to Setup Review page
	public Setup_ReviewPage Click_NxtBtn() throws IOException, InterruptedException {
		clickOn(NxtBtn);
		Thread.sleep(1000);
		return new Setup_ReviewPage();
	}

}
