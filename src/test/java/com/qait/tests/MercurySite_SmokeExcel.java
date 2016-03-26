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


public class MercurySite_SmokeExcel {
	TestSessionInitiatorExcel test;
	//Map<String, Object> mapMercury_testdata;
	
	Hashtable<String,String>  data;
	Xls_Reader dataXLS; 
	String testCaseName = this.getClass().getSimpleName();
	//Map<String, Object> userInfo = null;
	
	String appName= "ASR";
	//String excelFilePath=Xls_Reader.setdataFilePath(appName);
	
	MercurySite_SmokeExcel(Hashtable<String,String> data) {
		Reporter.log("****** TEST CASE ID : MercurySite_Smoke ******\n", true);
		this.data = data;
		//getKeyValueAACT = new YamlInformationProvider(usereInfoMap);
	}


	
	@Test(priority=1)
	public void Step01_Launch_Application_Under_Test() {

		Reporter.log("****** TEST CASE ID :  ******\n", true);
		String app_url=data.get("app_url");
		test.launchApplication(app_url);
		
	}
	
	
	@Test(priority=2,dependsOnMethods="Step01_Launch_Application_Under_Test")
	public void Step02_TC01_EnterValidLoginandPassword() {
		
		Reporter.log("****** Step02_TC01_EnterValidLoginandPassword:  ******\n", true);
		//String tcId = "TC01";
		test.Mercury_HomePage.loginMercurySite(
				data.get("userName"),
				data.get("password")
		);
	

	}
	
	@Test(priority=3,dependsOnMethods = "Step02_TC01_EnterValidLoginandPassword")
	public void Step03_TC02_findFlight() {
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

	}
	
	@Test(priority=3,dependsOnMethods = "Step03_TC02_findFlight")
	public void Step04_TC03_selectFlight() {
		test.Mercury_SelectFlight.enterDepartureAndReturn(
				data.get("DepartFlight"),
				data.get("RetrnFlight")
				);
		test.Mercury_SelectFlight.clickContinue();

	}
	
	@Test(priority=4,dependsOnMethods = "Step04_TC03_selectFlight")
	public void Step05_TC04_bookAFlight() {
		test.Mercury_BookAFlight.enterFlightDetails(
				data.get("FirstName"),
				data.get("LastName"),
				data.get("CCNumber"),
				data.get("CCType")
			);
		test.Mercury_BookAFlight.clickContinue();

	}
	@Test(priority=5,dependsOnMethods = "Step05_TC04_bookAFlight")
	public void Step06_TC05_flightConfirmation() {
		test.Mercury_FlightConfirmation.verifyText(
				data.get("vText")
				);
	
		test.Mercury_FlightConfirmation.clickContinue();


	}
	
	
	@BeforeClass 
	public void OpenBrowserWindow() {
		try{
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		//test = new TestSessionInitiatorExcel(testCaseName,appName);
		//dataXLS = new Xls_Reader(excelFilePath);
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
