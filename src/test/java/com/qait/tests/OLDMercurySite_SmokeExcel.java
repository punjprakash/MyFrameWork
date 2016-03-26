package com.qait.tests;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.TestSessionInitiatorExcel;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.Xls_Reader;
//import com.qait.keywords.YamlInformationProvider;
import com.qait.pages.Mercury_BookAFlight;
import com.qait.pages.Mercury_FlightConfirmation;
import com.qait.pages.Mercury_FlightFinder;
import com.qait.pages.Mercury_HomePage;
import com.qait.pages.Mercury_SelectFlight;


public class OLDMercurySite_SmokeExcel {
	TestSessionInitiatorExcel test;
	Hashtable<String,String>  data;
	Xls_Reader dataXLS; 
	String excelFilePath;
	String testCaseName = this.getClass().getSimpleName();
	String appName= "Mercury";
	public Mercury_HomePage Mercury_HomePage;
	public Mercury_FlightFinder Mercury_FlightFinder;
	public Mercury_SelectFlight Mercury_SelectFlight;
	public Mercury_BookAFlight Mercury_BookAFlight;
	public Mercury_FlightConfirmation Mercury_FlightConfirmation;
	
	
	
	OLDMercurySite_SmokeExcel() {
		Reporter.log("****** TEST CASE ID : MercurySite_Smoke ******\n", true);
			
	}

	@Test(priority=1)
	public void Step01_Launch_Application_Under_Test() {

		Reporter.log("****** TEST CASE ID :  ******\n", true);
		String app_url=data.get("app_url");
		test.launchApplication(app_url);
		Reporter.log("****** Step02_TC01_EnterValidLoginandPassword:  ******\n", true);
		//String tcId = "TC01";
		test.Mercury_HomePage.loginMercurySite(
				data.get("userName"),
				data.get("password")
		);
		test.Mercury_FlightFinder.enterFlightDetails(
				data.get("Trip"),
				data.get("Passengers"),
				data.get("DprtFrom"),
				data.get("DprtOnMonth"), 
				data.get("DprtOnDt"), 
				data.get("ArrvngIn"),  
				data.get("RetrnMonth"),
				data.get("RetrnOn")
				);
		test.Mercury_FlightFinder.clickContinue();
		test.Mercury_SelectFlight.enterDepartureAndReturn(
				data.get("DepartFlight"),
				data.get("RetrnFlight")
				);
		test.Mercury_SelectFlight.clickContinue();
		test.Mercury_BookAFlight.enterFlightDetails(
				data.get("FirstName"),
				data.get("LastName"),
				data.get("CCNumber"),
				data.get("CCType")
			);
		test.Mercury_BookAFlight.clickContinue();
		test.Mercury_FlightConfirmation.verifyText(
				data.get("vText")
				);
	
		test.Mercury_FlightConfirmation.clickContinue();
	}
	
	
	@BeforeClass 
	public void OpenBrowserWindow() {
		try{
		
		excelFilePath=Xls_Reader.setdataFilePath(appName);
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		
		dataXLS = new Xls_Reader(excelFilePath);
		data= Xls_Reader.getDataOneRow(testCaseName, dataXLS);
		}
			
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}

	}

	
	@BeforeMethod
	public void Step00_verifyUserIsOnHomePage(ITestResult result) {
		//test.deleteAllCookies();
		test.takescreenshot.takeScreenShotOnException(result);
		//test.homePage.pageRefresh();
	}

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass(alwaysRun = true)
	public void Close_Test_Session() {
		test.closeBrowserSession();
	}
	

}
