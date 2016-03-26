package com.qait.pages;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.getpageobjects.CommonActions;
import com.qait.automation.utils.ConfigPropertyReader;

public class VNet extends CommonActions {

	WebDriver driver;
	String pagename = "VNet";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public VNet(WebDriver driver) {
		super(driver, "VNet");
		this.driver = driver;
	}

	public void loginVNet(String loginID, String password) {
		switchToFrame(element("topFrame"));
		element("inp_userName").sendKeys(loginID);
		element("inp_password").clear();
		element("inp_password").sendKeys(password);
		element("btn_signIn").click();
		holdExecution(5000);
		logMessage("Step: click btn_signIn\n");
		

	}
	
	public String searchCallGetStatus(String VNetId) {
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		isElementEnabled("lnkSearch", true);
		element("lnkSearch").click();
		switchToiFrame(0);
		element("inp_callID").sendKeys(VNetId) ;
		element("btnSearch").submit();
		//element("btnSearch").click();
		holdExecution(5000);
		String status=element("txtStatus").getText();
		logMessage("Step: Status"+status);
		//hover(element("txtStatus"));
		return status;
		
	}
	
	
	public void closeVnetTicket(String VNetId) {
		
		holdExecution(2000);
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		element("home").click();
		searchCallGetStatus(VNetId);
		holdExecution(2000);
		selectDropDownValue(element("selGoTo"), 4);
		holdExecution(5000);
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		switchToiFrame(0);
		holdExecution(2000);
		
		element("ckBoxCR").sendKeys(" ");
		holdExecution(2000);
		//clickable(element("lnkSubmitRprtEve"));
		//element("lnkSubmitRprtEve").click();
		clickByJavascript("lnkSubmitRprtEve");
		holdExecution(2000);
		//element("lnkSubmitRprtEve").submit();
		element("linkNotes").click();
		holdExecution(2000);
		clickByJavascript("lnkclrDLR");
		//element("lnkclrDLR").click();
		//clickByJavascript("lnkclrDLR");
		holdExecution(2000);
		String circuit=element("crkIDCLRDLR").getText();
		logMessage("Circuit ID in VNET"+ circuit +"\n");
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		element("home").click();
		searchCallGetStatus(VNetId);
		holdExecution(2000);
		selectDropDownValue(element("selGoTo"), 2);
		holdExecution(2000);
		selectProvidedTextFromDropDown(element("ddfltID"), "88_NO_TROUBLE_FOUND");
		holdExecution(5000);
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		switchToiFrame(0);
		isElementEnabled("btnaddFault", true);
		element("btnaddFault").submit();
		holdExecution(2000);
		clickByJavascript("linkReportEvent");
		holdExecution(2000);
		
		element("checkBoxsrvcCenComp").sendKeys(" ");
		selectProvidedTextFromDropDown(element("ddCompleteReason"), "Customer Advised OK to Close");
		element("inpReportRemarks").sendKeys("Automation testing completed");
		holdExecution(2000);
		//element("lnkSubComp").submit();
		clickByJavascript("lnkSubComp");
		holdExecution(20000);
		String status= element("txtSCenter").getText();
		logMessage("Statas of woerk in VNET"+ status +"\n");
			
	}
	
	
	
	
	public void logoutVNet() {
		switchToDefaultContent();
		switchToFrame(element("topFrame"));
		isElementDisplayed("logout");
		element("logout").click();
		logMessage("Step: VNet is logged out\n");
	}
}
