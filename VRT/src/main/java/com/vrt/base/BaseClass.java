/**
 * @author manoj.ghadei
 *
 */

package com.vrt.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.poifs.property.DirectoryProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vrt.utility.TestUtilities;

import io.appium.java_client.windows.WindowsDriver;



public class BaseClass {	
	
	//public static final String testDir = "C:\\rsrcoutput\\"; 
	public static Properties prop;
	//Declare the Windows Driver and make it Public/Static to be used throughout the classes
	public static WindowsDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();


	//Calling Constructor method
	public BaseClass() throws IOException {
		try {
			prop = new Properties();
			//Below Path will be used whle creating an Jar/exe file where the config file will be 
			//placed present in the jar.exe path location.
			//FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties");

			//Below Path will be used while executing scripts from Eclipse IDE.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	// Launch App/Setup Configuration Function
	//Initialize Windows Driver
	public static void LaunchApp(String Url) throws InterruptedException {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "WindowsPC");
            capabilities.setCapability("app", Url);
            capabilities.setCapability("ms:waitForAppLaunch", "10");
	
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			tdriver.set(driver);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	

	// Check if Element is present to be able to click or not
	public WebElement checkingElementClickable(WebElement element, long WaitTime) {
		return new WebDriverWait(driver, WaitTime).until(ExpectedConditions.elementToBeClickable(element));
	}

	// ClickOn any Element method
	public void clickOn(WebElement element) {
		try {
			checkingElementClickable(element, 5).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Check Visibility of the Element method
	public WebElement checkingVisibilityOfElement(WebElement element, long WaitTime) {
		return new WebDriverWait(driver, WaitTime).until(ExpectedConditions.visibilityOf(element));
	}
	
	// Enter Text to any Edit field method
	public void enterText(WebElement element, String text) {
		try {
			checkingVisibilityOfElement(element, 5).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Enter Text to any Edit field method
	public void enterNumeric(WebElement element, int num) {
		try {
			String text = String.valueOf(num);
			checkingVisibilityOfElement(element, 5).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Clear any Edit filed method
	public void ClearText(WebElement element) {
		try {
			checkingVisibilityOfElement(element, 5).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Text for any Element method
	public String FetchText(WebElement element) {
		String text = null;
		try {
			text=checkingVisibilityOfElement(element, 5).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return text;
	}
	
	
	//Very if the Element is Enabled or Not Method
	public boolean IsElementEnabledStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 5).isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	
	//Very if the Element is Visible or Not Method
	public boolean IsElementVisibleStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 10).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	
	//Very if any check-box Element is checked/slected or Not Method
	public boolean checkboxSelectStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 5).isSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	

	//Login Popup function to be called where invoked in respective pages 
	public void UserLoginPopup(String UN, String PW) throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		WebElement LgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		WebElement LgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		WebElement LgInOK = driver.findElementByAccessibilityId("ValidateUserOK");
		if (LgInPopup.isDisplayed()) {
			LgInUID.sendKeys(UN);
			LgInPW.sendKeys(PW);
			LgInOK.click();
			Thread.sleep(1000);
		}		
	}
	
	//Close the App any time using the Top right Close button 
	public void AppClose() throws InterruptedException {
		WebElement AppCloseBtn = driver.findElementByName("Close ValProbe RT");
		clickOn(AppCloseBtn);		
		Thread.sleep(1000);				
	} 
	
	
	// function to remove all occurrences of an element from an array
	public static String[] removeDuplicateStringinArray(String[] stringarray, String removalItem) {		
			// Move all other elements to beginning 
			int index = 0; 
			for (int i=0; i<stringarray.length; i++) {
				if (!(stringarray[i].equals(removalItem))) 
					stringarray[index++] = stringarray[i]; 
			}
			// Create a copy of stringarray[]
			return Arrays.copyOf(stringarray, index); 		
	}
	
	//Defining a User list with Credentials using HashMap
	//HashMap is basically used in App where there is a 
	//Different Users/Roles : Permission Based User Management System 
	//with Unique UN:PW (kay Value) comibination with no duplicates to be used
	public static HashMap<String, String> getUIDCredentials() {
		HashMap<String, String> UserMap = new HashMap<String, String>();
		
		UserMap.put("adminFull", "1:Welcome1@AM");		
		//User roles for privilege and customized privilege
		UserMap.put("SysAdmin", "2:Start@1AM");
		UserMap.put("SysSupervisor", "3:Welcome3@AM");
		UserMap.put("SysOperator", "4:Welcome4@AM");	
		
		UserMap.put("TestAdmin", "2:Start@5AM");
		UserMap.put("Dsbluser", "1D:Start@1AM");
		UserMap.put("Newuser", "1N:Start@7AM");
		//UserMap.put("Delrprt", "5:Start@5AM");
		return UserMap;
	}
	
	//Fetch the UserName credentials from the above GetUNCredentials HashMap method
	  public static String getUID(String role) {
		String UID = getUIDCredentials().get(role).split(":")[0];
		return UID;
	}
	
 //Fetch the Password credentials from the above GetUNCredentials HashMap method
	public static String getPW(String role) {
		String PW = getUIDCredentials().get(role).split(":")[1];
		return PW;
	}
	
	//File renaming/creating method
	public void renameFile(String filePath, String fileName) throws IOException {

		// File path
		String filepath = filePath;
		File file = new File(filepath + "/" + fileName);
		
		//System.out.println(file.exists());
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println("Target File: " +fileName+ " NOT present");
		} else {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			File backupFile = new File(filepath + "/" + timestamp + "_"+fileName);
			file.renameTo(backupFile);
			System.out.println("Target file renamed");
		}				
	}
	
	//Get the Sw version info from the About window on clicking About icon of the bottom apps bar
		public String get_SWVersion_About_Text() throws InterruptedException {
			Actions ac = new Actions(driver);
			ac.contextClick().build().perform();
			
			WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
			clickOn(bottomMenu_About_Icon);
			Thread.sleep(500);
			WebElement SWVersion_About_info = driver.findElementByAccessibilityId("SoftwareVersion");
			
			String[] SWVer = FetchText(SWVersion_About_info).split(":");
			
			return SWVer[1];
		}
	
		
		/*//Ref: https://stackoverflow.com/questions/14637107/runnable-jar-file-not-found-exception/14637240#14637240
		public String getRsrcFile(String path, String fileName) throws Exception {
			
		    InputStream in = this.getClass().getResourceAsStream(path);
		    new File(testDir).mkdirs();
		    String newFilePath = testDir + fileName;
		    FileOutputStream out = new FileOutputStream (newFilePath);
		    byte[] buffer = new byte[1024];
		    int len = in.read(buffer);
		    while (len != -1) {
		        out.write(buffer, 0, len);
		        len = in.read(buffer);
		    }
		    out.close();
		    return newFilePath;
		    Runtime rt = Runtime.getRuntime();
		    String command = adobePath + " " + pdfFilePath;
		    rt.exec(command);
		}*/
	
}
