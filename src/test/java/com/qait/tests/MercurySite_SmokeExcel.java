package com.qait.tests;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;
import com.qait.automation.TestSessionInitiator;
import com.qait.automation.TestSessionInitiatorExcel;
import com.qait.automation.report.*;
import com.qait.automation.report.ReporterHTML.TestStatus;
import com.qait.automation.report.ReporterHTML.updateValue;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.Xls_Reader;
//import com.qait.keywords.YamlInformationProvider;
import com.qait.pages.*;
import com.qait.automation.report.*;


//@Listeners(value=ReportingIReporterInterface.class)
public class MercurySite_SmokeExcel extends BaseReport {
	TestSessionInitiatorExcel test;
	Hashtable<String,String>  data;
	Xls_Reader dataXLS; 
	String excelFilePath;
	String testCaseName = this.getClass().getSimpleName();
	String appName= "Mercury";
	WebDriver driver;
	public Mercury_HomePage Mercury_HomePage;
	public Mercury_FlightFinder Mercury_FlightFinder;
	public Mercury_SelectFlight Mercury_SelectFlight;
	public Mercury_BookAFlight Mercury_BookAFlight;
	public Mercury_FlightConfirmation Mercury_FlightConfirmation;
	public BaseReport BaseReport;
	String Env = "QA";
	SoftAssert softAssert = new SoftAssert();
	Assertion hardAssert = new Assertion();
		
		
	MercurySite_SmokeExcel() {
		Reporter.log("****** TEST CASE ID : MercurySite_Smoke ******\n", true);
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("Mercury Test");
		 
	}
	@Test(priority=0,description="testReporterOne")
	public void Step01_Launch_Application_Under_Test() {
		/*
		Capabilities caps =  ((RemoteWebDriver) driver).getCapabilities();
		try {
			ReporterHTML.updateReports(updateValue.bName, caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString(), "");
			ReporterHTML.updateReports(updateValue.Env, Env, "");
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//browserName = caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString();
			
		
		Reporter.log("****** TEST CASE ID :  ******\n", true);
		String app_url=data.get("app_url");
		test.launchApplication(app_url);
		ReporterHTML.LogEvent(TestStatus.PASS, "Open App", "Application has been opened","This is mercury login");
		Reporter.log("****** Step02_TC01_EnterValidLoginandPassword:  ******\n", true);
		Log.info("This is my info");
		//String tcId = "TC01";
		Mercury_HomePage.loginMercurySite(
				data.get("userName"),
				data.get("password")
		);
		ReporterHTML.LogEvent(TestStatus.PASS, "Login", "Login has been completed","This is mercury login");
		
		
		Mercury_FlightFinder.enterFlightDetails(
				data.get("Trip"),
				data.get("Passengers"),
				data.get("DprtFrom"),
				data.get("DprtOnMonth"), 
				data.get("DprtOnDt"), 
				data.get("ArrvngIn"),  
				data.get("RetrnMonth"),
				data.get("RetrnOn")
				);
		
		ReporterHTML.LogEvent(TestStatus.PASS, "Enter Flight Details", "Flight Details have been entered","This is mercury Flight");
		
	
	}
	
	
	@Test(priority=0,description="testReporterOne")
	public void Step02_TestApplicationUnderTest(){
		//This is example of classic Page Object Model
		Mercury_SelectFlight = Mercury_FlightFinder.clickContinue();
		
		ReporterHTML.LogEvent(TestStatus.PASS, "clickContinue", "clickContinue has been done","This is mercury clickContinue");
		
		Mercury_SelectFlight.enterDepartureAndReturn(
				data.get("DepartFlight"),
				data.get("RetrnFlight")
				);
		ReporterHTML.LogEvent(TestStatus.PASS, "enterDepartureAndReturn", "enterDepartureAndReturn has been done","This is mercury enterDepartureAndReturn");
		
		Mercury_SelectFlight.clickContinue();
		Mercury_BookAFlight.enterFlightDetails(
				data.get("FirstName"),
				data.get("LastName"),
				data.get("CCNumber"),
				data.get("CCType")
			);
		ReporterHTML.LogEvent(TestStatus.PASS, "enterFlightDetails", "enterFlightDetails has been done","This is mercury enterFlightDetails");
		
		Mercury_BookAFlight.clickContinue();
		Mercury_FlightConfirmation.verifyText(
				data.get("vText")
				);
		ReporterHTML.LogEvent(TestStatus.PASS, "verifyText", "Text is not valid","This is mercury verifyText");
		
		//hardAssert.assertTrue(false);
		//softAssert.assertTrue(false);
		/*
		try {
			assertTrue(false);
		} catch (Error e) {
			// It will catch error not exception
			//you can give object>Throwable>Error>VirtualMachineError>AssertionError 
			
			System.out.println("This is test to assert");
		}
		*/
	
		Mercury_FlightConfirmation.clickContinue();
		ReporterHTML.LogEvent(TestStatus.PASS, "clickContinue", "clickContinue has been done","This is mercury clickContinue");
		ReporterHTML.softAssert.assertAll();
		//softAssert.assertAll();
	}
	
	
	@BeforeClass 
	public void OpenBrowserWindow() {
		try{
		
		excelFilePath=Xls_Reader.setdataFilePath(appName);
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		driver=test.getDriver();
		Mercury_HomePage = new Mercury_HomePage(driver);
		Mercury_FlightFinder = new Mercury_FlightFinder(driver);
		//Mercury_SelectFlight = new Mercury_SelectFlight(driver);
		Mercury_BookAFlight = new Mercury_BookAFlight(driver);
		Mercury_FlightConfirmation = new Mercury_FlightConfirmation(driver);
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
