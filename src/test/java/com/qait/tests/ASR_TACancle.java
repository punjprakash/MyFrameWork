package com.qait.tests;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qait.automation.TestSessionInitiatorExcel;
import com.qait.automation.utils.Xls_Reader;

import junit.framework.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ASR_TACancle {
	TestSessionInitiatorExcel test;
	String appName= "ASR";
	String excelFilePath;
	Xls_Reader dataXLS; 
	String testCaseName = this.getClass().getSimpleName();
	Hashtable<String,String>  data;
	String serviceID;
	String troubleTicket;
	String agentID;
	String getVnetID;
	String VNetStatus;
	
	
	ASR_TACancle() {
		Reporter.log("****** TEST CASE ID : ASR ******\n", true);
	}
	
	@Test(priority=1)
	public void VFOCreateTicket() throws InterruptedException {
		String app_url;
				
		//Create ticket in VFO
		
		Reporter.log("****** VFO Create Ticket started:  ******\n", true);
		app_url=data.get("VFO_URL");
		test.launchApplication(app_url);
		test.takescreenshot.takeScreenshot();
		test.VFO.loginVFO(data.get("userName"),data.get("password"),data.get("serviceType"));
		test.takescreenshot.takeScreenshot();
		
		
		String ids[]=test.VFO.createTroubleTicket(data.get("template"), data.get("serviceID"));
		troubleTicket=ids[0];
		agentID=ids[1];		
		dataXLS.setCellData(testCaseName,"ticketID",2,troubleTicket);
		dataXLS.setCellData(testCaseName,"agentTRID",2,agentID);
		test.closeBrowserSession();
			
	
		//Reinitite to run in different browser
		
		Reporter.log("****** TestSessionInitiator 2nd time ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		
		app_url=data.get("Remedy_URL");
		test.launchApplication(app_url);
		test.Remedy.loginRemedy(data.get("uNameRemedy"),data.get("passRemedy"));
		Thread.sleep(5000); 
		serviceID=test.Remedy.getServiceID(1);
		int timer = 0; 
		while (!serviceID.equals(data.get("serviceID")) || timer>30 ) {
			serviceID=test.Remedy.getServiceID(1);
			timer=timer+1;	
		}
		Assert.assertEquals(serviceID, data.get("serviceID")); 
		test.Remedy.dblclickServiceID();
		test.Remedy.createWorkOrder("Major","Next AM Dispatch","AIR_DRYER");
		test.Remedy.closeMutipleWindow();
		getVnetID=test.Remedy.getVnetID();
		timer = 0; 
		while (getVnetID.isEmpty() || timer>30 ) {
			getVnetID=test.Remedy.getVnetID();
			timer=timer+1;	
		}
		boolean flag=true;
		if (getVnetID.isEmpty()) {
			flag=false;
		}
		Assert.assertTrue(flag);
		dataXLS.setCellData(testCaseName,"VNetID",2,getVnetID);
		test.Remedy.logoutRemedy();
		test.closeBrowserSession();
				
		
		//Cancle order in VFO
		//Reinitite to run in different browser
		
		Reporter.log("****** TestSessionInitiator 3nd time ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		
		Reporter.log("****** VFO Cancle Ticket  ******\n", true);
		app_url=data.get("VFO_URL");
		test.launchApplication(app_url);
		test.takescreenshot.takeScreenshot();
		test.VFO.loginVFO(data.get("userName"),data.get("password"),data.get("serviceType"));
		test.takescreenshot.takeScreenshot();
		test.VFO.cancleTicket();
		test.VFO.logout();
		test.closeBrowserSession();
		
		//Check in Remedy, WO should not be there any more
		
		Reporter.log("****** TestSessionInitiator 4th time ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		app_url=data.get("Remedy_URL");
		test.launchApplication(app_url);
		test.Remedy.loginRemedy(data.get("uNameRemedy"),data.get("passRemedy"));
		Thread.sleep(5000); 
		String serviceIDAfterCancle=test.Remedy.getServiceID(1);
		String getVnetIDAfterCancle=test.Remedy.getVnetID();
		
		if ((!serviceIDAfterCancle.equalsIgnoreCase(serviceID)) && (!getVnetIDAfterCancle.equalsIgnoreCase(getVnetID))) {
			//Assert.assertEquals("True", "True");
			Reporter.log("WO cancled in VNET\n", true);
		}
		else{
			//Assert.assertEquals("True", "False");
			Reporter.log("WO did not get cancled in VNet\n", true);
		}
		test.Remedy.logoutRemedy();
		test.closeBrowserSession();
			
		
		Reporter.log("****** TestSessionInitiator 5th time ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"ie");
		
		//See in Virya net if order has been cancled
		
		app_url=data.get("Vnet_URL");
		test.launchApplication(app_url);
		test.takescreenshot.takeScreenshot();
		test.VNet.loginVNet(data.get("uNameVNet"),data.get("passVNet"));
		VNetStatus=test.VNet.searchCallGetStatus(getVnetID);
		Reporter.log("VNet Status is " +VNetStatus ,true);
		//CANCELLED 
		Assert.assertEquals(VNetStatus, "CANCELLED"); 
		test.takescreenshot.takeScreenshot();
		test.VNet.logoutVNet();
		test.closeBrowserSession();

	}

		
	@BeforeClass 
	public void OpenBrowserWindow() {
		try{
		excelFilePath=Xls_Reader.setdataFilePath(appName);
		Reporter.log("****** TestSessionInitiator  ******\n", true);
		test = new TestSessionInitiatorExcel(testCaseName,appName,"firefox");
		test.deleteAllCookies();
		Reporter.log("****** Cookies deleted  ******\n", true);
		
		Reporter.log("****** TestInitiated  ******\n", true);
		dataXLS = new Xls_Reader(excelFilePath);
		data= Xls_Reader.getDataOneRow(testCaseName, dataXLS);
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
		//test.closeBrowserSession();
	}
	

}
