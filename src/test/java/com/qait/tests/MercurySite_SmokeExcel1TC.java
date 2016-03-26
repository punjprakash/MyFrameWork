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


public class MercurySite_SmokeExcel1TC {
	TestSessionInitiatorExcel test;
	
	//YamlInformationProvider getKeyValue;
	
	//Map<String, Object> mapMercury_testdata;
	
	//Hashtable<String,String>  getKeyValueAACT;
	
	Xls_Reader dataXLS; 
	
	
	String testCaseName = this.getClass().getSimpleName();
	
	//WebDriver driver1=new FirefoxDriver();
	//Map<String, Object> userInfo = null;
	
	String appName= "ASR";
	
	//****************************chnage it
	String excelFilePath=Xls_Reader.setdataFilePath(appName);
	
	//String app_url = getYamlValue("app_url_Mercury");
	
	//String app_url =ConfigPropertyReader.getProperty("./Config.properties", "app_url_Mercury");
	
	
	//Map<String, Object> usereInfoMap;
	
	//String app_url_IWEB = getYamlValue("app_url_IWEB");

	/**
	 * Test case number 1 is removed
	 */
	

	MercurySite_SmokeExcel1TC() {
		Reporter.log("****** TEST CASE ID : MercurySite_Smoke ******\n", true);
		//this.userInfo = usereInfoMap;
		//getKeyValueAACT = new YamlInformationProvider(usereInfoMap);
	}


	
	
	/*@Test(priority=1,dataProvider="getLoginData")
	public void OpenBrowserWindow(Hashtable<String,String> data) {
		try{
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName);
		Reporter.log("****** TestSessionInitiator2  ******\n", true);
		
		//*********************Change it
		//dataXLS = new Xls_Reader(excelFilePath);
		}
		
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	*/
	@Test(priority=1,dataProvider="getLoginData")
	public void Step01_Launch_Application_Under_Test(Hashtable<String,String> data) {
		Reporter.log("****** TEST CASE ID :  ******\n", true);
		String app_url=data.get("app_url");
		test.launchApplication(app_url);
		//test = new TestSessionInitiatorExcel(testCaseName,appName);
	}
	
	
	@Test(priority=2,dependsOnMethods="Step01_Launch_Application_Under_Test",dataProvider="getLoginData")
	public void Step02_TC01_EnterValidLoginandPassword(Hashtable<String,String> data) {
		
		Reporter.log("****** Step02_TC01_EnterValidLoginandPassword:  ******\n", true);
		//String tcId = "TC01";
		test.Mercury_HomePage.loginMercurySite(
				data.get("userName"),
				data.get("password")
		);
	
		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=3,dependsOnMethods = "Step02_TC01_EnterValidLoginandPassword",dataProvider="getLoginData")
	public void Step03_TC02_findFlight(Hashtable<String,String> data) {
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

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=3,dependsOnMethods = "Step03_TC02_findFlight",dataProvider="getLoginData")
	public void Step04_TC03_selectFlight(Hashtable<String,String> data) {
		test.Mercury_SelectFlight.enterDepartureAndReturn(
				data.get("DepartFlight"),
				data.get("RetrnFlight")
				);
		test.Mercury_SelectFlight.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	@Test(priority=4,dependsOnMethods = "Step04_TC03_selectFlight",dataProvider="getLoginData")
	public void Step05_TC04_bookAFlight(Hashtable<String,String> data) {
		test.Mercury_BookAFlight.enterFlightDetails(
				data.get("FirstName"),
				data.get("LastName"),
				data.get("CCNumber"),
				data.get("CCType")
			);
		test.Mercury_BookAFlight.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	@Test(priority=5,dependsOnMethods = "Step05_TC04_bookAFlight",dataProvider="getLoginData")
	public void Step06_TC05_flightConfirmation(Hashtable<String,String> data) {
		test.Mercury_FlightConfirmation.verifyText(
				data.get("vText")
				);
	
		test.Mercury_FlightConfirmation.clickContinue();

		//test.asmErrorPage.verifyASMErrorNotPresent(YamlReader
			//	.getYamlValue("ASM_URLRejectedErrorMsz"));
		//test.homePage.verifyCurrentTab("About You");
	}
	
	
	@BeforeClass 
	public void OpenBrowserWindow1() {
		try{
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		//test = new TestSessionInitiatorExcel(testCaseName,appName);
		Reporter.log("****** TestSessionInitiator2  ******\n", true);
		
		//*********************Change it
		dataXLS = new Xls_Reader(excelFilePath);
		System.out.println("OpenBrowserWindow1");
		}
		
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		
		
		//getKeyValueAACT=Xls_Reader.getData(testCaseName, dataXLS);
		//mapMercury_testdata = YamlReader.getYamlValues("MercuryPage");
		//getKeyValueAACT = new YamlInformationProvider(mapMercury_testdata);
		
	}

	
	@DataProvider
	public Object[][] getLoginData(){
		return Xls_Reader.getData(testCaseName, dataXLS);
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
