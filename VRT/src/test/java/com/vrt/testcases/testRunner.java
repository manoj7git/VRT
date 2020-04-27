/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import java.io.FileNotFoundException;


import org.testng.TestNG;

import com.vrt.Listners.ExtentReporterNG;

public class testRunner {
	
	static TestNG testng;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException {		

	        
		ExtentReporterNG er = new ExtentReporterNG();

		testng = new TestNG();
		//testng.setTestClasses(new Class[] {assetCreationTest.class});
		testng.setTestClasses(new Class[] {HitNTrialTests.class});
		//testng.setTestClasses(new Class[] {LoginTest.class});
		//testng.setTestClasses(new Class[] {UserManagementTest.class, UserPrivilagesTest.class, UM_customized_UserPrivilagesTest.class});
		//testng.setTestClasses(new Class[] {assetCreationTest.class, assetHubTest.class, assetHubAssetCreationTest.class});
		//testng.setTestClasses(new Class[] {LoginTest.class, assetDetailsTest.class});
		//testng.addListener(er);
		//testng.addListener(er);
		testng.run();			
	}

}
