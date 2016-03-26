package com.qait.pages;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

//import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Remedy extends GetPage {

	WebDriver driver;
	String pagename = "Remedy";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
	String serviceID="";
	String VnetID="";

	public Remedy(WebDriver driver) {
		super(driver, "Remedy");
		this.driver = driver;
	}

	public void loginRemedy(String loginID, String password) {
		element("inp_userName").sendKeys(loginID);
		element("inp_password").sendKeys(password);
		element("btn_signIn").click();
		logMessage("Step: click btn_signIn\n");
	}
	
	public String getServiceID(int rowNumber) throws InterruptedException {
		element("refresh").click();
		Thread.sleep(3000);
		if (rowNumber==1) {
			isElementDisplayed("serviceID");
			serviceID=element("serviceID").getText();
			logMessage("Step: Getting circuit ID from remedy\n");
			return serviceID;
			
		} else {
			isElementDisplayed("serviceID");
			serviceID=element("serviceID").getText();
			logMessage("Step: Getting circuit ID from remedy\n");
			return serviceID;
		}
		
	}
	public String getVnetID() throws InterruptedException {
		element("refresh").click();
		Thread.sleep(3000);
		isElementDisplayed("VnetID");
		VnetID=element("VnetID").getText();
		logMessage("Step: Getting circuit ID from remedy\n");
		return VnetID;
	}
	public void dblclickServiceID() {
		isElementDisplayed("serviceID");
		doubleClick(element("serviceID"));
		logMessage("Step: double clicked on serviceID \n");
	}
	
	public void closeMutipleWindow() {
		element("btnCloseWO").click();
		holdExecution(2000);
		switchWindow();
		element("btnCloseOP").click();
		holdExecution(2000);
		switchWindow();
		//isElementDisplayed("serviceID");
		//doubleClick(element("serviceID"));
		//logMessage("Step: double clicked on serviceID \n");
	}
	

	public void createWorkOrder(String ticketPriority,String dispatchNotes,String skillSet) {
		switchWindow();
		isElementEnabled("Report", true);
		isElementEnabled("btn_ackldg",true);
		element("btn_ackldg").click();
		logMessage("Step: clicked on acknowledge \n");
		//isElementEnabled("Report", true);
		holdExecution(5000);
		isElementEnabled("imgBilling",true);
		isElementEnabled("Report", true);
		String[] locator = getELementFromFile(pagename, "tcktPrty");
		String script="document.getElementById('"+locator[2]+"').value = '"+ticketPriority+"';";
		executeJavascript(script);
		doubleClick(element("tcktPrty"));
		holdExecution(2000);
		isElementEnabled("Report", true);
		logMessage("Step:Major has been selected");
		//Click on modify
		isElementEnabled("lnkModify", true);
		element("lnkModify").click();
		holdExecution(2000);
		isElementEnabled("Report", true);
		element("lnkDispatch").click();
		element("lnkCreateFrmTckt").click();
		switchWindow();
		//give dispatch and skill set value and submit
		
		isElementEnabled("ddDispatch", true);
		doubleClick(element("ddDispatch"));
		locator = getELementFromFile(pagename, "ddDispatch");
		script="document.getElementById('"+locator[2]+"').value = '"+dispatchNotes+"';";
		executeJavascript(script);
		doubleClick(element("ddDispatch"));
		
		isElementEnabled("ddSkillSet", true);
		doubleClick(element("ddSkillSet"));
		locator = getELementFromFile(pagename, "ddSkillSet");
		script="document.getElementById('"+locator[2]+"').value = '"+skillSet+"';";
		executeJavascript(script);
		doubleClick(element("ddSkillSet"));
		holdExecution(2000);
		element("btnSubmit").click();
		waitForElementToDisable(element("btnSubmit"));
		holdExecution(2000);
			
	}
	
	
	public void verifyVNetIdDissapear() {
		
	}
	public void logoutRemedy() {
		isElementDisplayed("logout");
		element("logout").click();
		
	}
}
