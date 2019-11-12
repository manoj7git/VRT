/**
 * @author manoj.ghadei
 *
 */

package com.vrt.utility;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.vrt.base.BaseClass;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TestUtilities extends BaseClass {
	
	
	public String convert_StringDate_to_ActualDate_inCertainFormat(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd MMM yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM-d-yyyy : "+strDate);
		return strDate;
	}
	
	//Rqd Date format MM-dd-YYYY = 12-31-2019
	public String get_CurrentDate_inCertainFormat(String dtFormat) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat(dtFormat);		
		Date date = new Date();
		
		String strDate = formating.format(date);
		//System.out.println(formating.format(date));
		return strDate;
	}

	
	//Capture Screenshot of a particular WebElement 
	public void capture_element_screenshot(WebDriver driver, WebElement element, String DestinationFldrName, String screenshotName) throws IOException {
        // Take screen shot of whole page
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        // Calculate the width and height of the webElement
        Point p = element.getLocation();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
 
        // Create Rectangle of same width as of Web Element
        Rectangle rect = new Rectangle(width, height);
 
        BufferedImage img = null;
        img = ImageIO.read(screenShot);
 
        //Crop Image of web element from the screen shot
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
 
        // write cropped image into File Object
        ImageIO.write(dest, "png", screenShot);
 
        //Copy Image into particular directory
		String destination = System.getProperty("user.dir") + "/"+ DestinationFldrName + "/" + screenshotName + ".png";
        FileUtils.copyFile(screenShot, new File(destination));

	}
	
	//Comparison of two images placed at the default Test Data Folder
	public boolean compareImage(String ExpImgName, String ActImgName) throws IOException {
		boolean state = false;
		
		String exp_ImgLocation = System.getProperty("user.dir") + "/TestData/" + ExpImgName + ".png";
        BufferedImage expectedImage = ImageIO.read(new File(exp_ImgLocation));
        
		String act_ImgLocation = System.getProperty("user.dir") + "/TestData/" + ActImgName + ".png";
        BufferedImage actualImage = ImageIO.read(new File(act_ImgLocation));
        
       /* WebElement logoImageElement = driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImageElement);
        BufferedImage actualImage = logoImageScreenshot.getImage();*/
               
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        if(diff.hasDiff()==true)
        {
         System.out.println("Images are Not Same");
         state = diff.hasDiff();
         return state;
        }
        else {
         System.out.println("Images are Same");
         state = diff.hasDiff();
         return state;
        }
		
	}
	
	
	
	// Method to call the below method to capture screenshot when a Test Fails
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\manoj.ghadei\\git\\VRT\\VRT\\Screenshots" + "_" + timestamp + ".png"));
		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png"));
	}

	public static String getFailedTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String getPassTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/PassTCScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	// Upload Documents method under Asset details page
	public void uploadDoc(String filename) throws AWTException, IOException, InterruptedException{

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\TestData\\" + filename;
		//System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}

}
