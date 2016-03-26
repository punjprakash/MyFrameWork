package com.qait.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.DataProvider;
import com.qait.automation.utils.YamlReader;
import com.qait.pages.YamlInformationProvider;


public class MercurySite_Smoke {
	TestSessionInitiator test;
	YamlInformationProvider getKeyValue;
	Map<String, Object> mapMercury_testdata;
	YamlInformationProvider getKeyValueAACT;
	String headerName = this.getClass().getSimpleName();
	Map<String, Object> userInfo = null;
	String yamlFilePath=setYamlFilePath();
	String app_url = getYamlValue("app_url_Mercury");
	Map<String, Object> usereInfoMap;
	
	//String app_url_IWEB = getYamlValue("app_url_IWEB");

	/**
	 * Test case number 1 is removed
	 */
	
	//MercurySite_Smoke(Map<String, Object> usereInfoMap)
	MercurySite_Smoke() {
		Reporter.log("****** TEST CASE ID : MercurySite_Smoke ******\n", true);
		//this.userInfo = usereInfoMap;
		//getKeyValueAACT = new YamlInformationProvider(usereInfoMap);
	}

	@Test(priority=1)
	public void Step01_Launch_Application_Under_Test() {
		Reporter.log("****** TEST CASE ID :  ******\n", true);
		test.launchApplication(app_url);
	}
	
	@Test(priority=2,dependsOnMethods="Step01_Launch_Application_Under_Test")
	public void Step02_TC01_EnterValidLoginandPassword() {
		
		Reporter.log("****** Step02_TC01_EnterValidLoginandPassword:  ******\n", true);
		//String tcId = "TC01";
		test.Mercury_HomePage.loginMercurySite(
				getKeyValueAACT.getMercurylogin_HomePage("userName"),
				getKeyValueAACT.getMercurylogin_HomePage("password")
		);
	
		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=3,dependsOnMethods = "Step02_TC01_EnterValidLoginandPassword")
	public void Step03_TC02_findFlight() {
		test.Mercury_FlightFinder.enterFlightDetails(
				getKeyValueAACT.getMercuryflight_FlightFinder("Trip"),
				getKeyValueAACT.getMercuryflight_FlightFinder("Passengers"),
				getKeyValueAACT.getMercuryflight_FlightFinder("DprtFrom"),
				getKeyValueAACT.getMercuryflight_FlightFinder("DprtOnMonth"), 
				getKeyValueAACT.getMercuryflight_FlightFinder("DprtOnDt"), 
				getKeyValueAACT.getMercuryflight_FlightFinder("ArrvngIn"),  
				getKeyValueAACT.getMercuryflight_FlightFinder("RetrnMonth"),
				getKeyValueAACT.getMercuryflight_FlightFinder("RetrnOn")
				);
		test.Mercury_FlightFinder.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=3,dependsOnMethods = "Step03_TC02_findFlight")
	public void Step04_TC03_selectFlight() {
		test.Mercury_SelectFlight.enterDepartureAndReturn(
				getKeyValueAACT.getMercurySelectFlight_SelectFlight("DepartFlight"),
				getKeyValueAACT.getMercurySelectFlight_SelectFlight("RetrnFlight")
				);
		test.Mercury_SelectFlight.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=4,dependsOnMethods = "Step04_TC03_selectFlight")
	public void Step05_TC04_bookAFlight() {
		test.Mercury_BookAFlight.enterFlightDetails(
				getKeyValueAACT.getMercuryBookAFlight_BookAFlight("FirstName"),
				getKeyValueAACT.getMercuryBookAFlight_BookAFlight("LastName"),
				getKeyValueAACT.getMercuryBookAFlight_BookAFlight("CCNumber"),
				getKeyValueAACT.getMercuryBookAFlight_BookAFlight("CCType")
			);
		test.Mercury_BookAFlight.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	@Test(priority=5,dependsOnMethods = "Step05_TC04_bookAFlight")
	public void Step06_TC05_flightConfirmation() {
		test.Mercury_FlightConfirmation.verifyText(
				getKeyValueAACT.getMercury_FlightConf("vText")
				);
	
		test.Mercury_FlightConfirmation.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	@BeforeClass
	public void OpenBrowserWindow() {
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		//test = new TestSessionInitiator(this.getClass().getSimpleName());
		mapMercury_testdata = YamlReader.getYamlValues("MercuryPage");
		getKeyValueAACT = new YamlInformationProvider(mapMercury_testdata);
		
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
