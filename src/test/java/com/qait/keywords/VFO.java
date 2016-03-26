package com.qait.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

import junit.framework.Assert;

public class VFO extends GetPage {

	WebDriver driver;
	String pagename = "VFO";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public VFO(WebDriver driver) {
		super(driver, "VFO");
		this.driver = driver;
	}

	public void loginVFO(String loginID, String password,String serviceType) {
		isElementDisplayed("inp_userName");
		element("inp_userName").sendKeys(loginID);
		//isElementDisplayed("inp_password");
		element("inp_password").clear();
		element("inp_password").sendKeys(password);
		logMessage("Login " + loginID + " and password " + password + "has enterned"
				+ "\n");
		selectProvidedTextFromDropDown(element("sel_serviceType"),serviceType);
		element("btn_signIn").click();
		logMessage("Step: click btn_signIn\n");

	}
	
	public String[] createTroubleTicket(String templateName,String serviceID) {
		switchToFrame(element("mainFrame"));
		hover(element("menuOrder"));
		element("lnkCreateOrder").click();
		selectProvidedTextFromDropDown(element("template"), templateName);
		isElementEnabled("serviceId", true);
		element("serviceId").clear();
		element("serviceId").sendKeys(serviceID);
		element("Next").click();
		element("submit").click();
		element("reload").click();
		holdExecution(2000);
		element("reload").click();
		element("backList").click();
		String Ids [] ={element("ticketID").getText(),element("agentID").getText()};
		return Ids;
		
	}
	
	public void verifyAndStoreTicket() {
		

	}
	public void searchTicket() {
		
	}
	public void cancleTicket() {
		switchToFrame(element("mainFrame"));
		element("radioSelect").click();
		hover(element("menuOrder"));
		element("lnkCancle").click();
		element("addInfo").sendKeys("Cancled by Automation Team");
		element("submit").click();
		element("reload").click();
		holdExecution(2000);
		element("reload").click();
		element("backList").click();
		element("reload").click();
		holdExecution(2000);
		Assert.assertEquals("Closed", element("state").getText());
	}
	
	public void checkStatus(String stateTicket,String Ticket) {
		switchToFrame(element("mainFrame"));
		String state=element("state").getText();
		String ticketID=element("ticketID").getText();
		if (stateTicket.equalsIgnoreCase(state)&& Ticket.equalsIgnoreCase(ticketID)){
			logMessage("Tickets are matching");
		}
		else{
			logMessage("Tickets are not matching");
		}
			
	}
	public void logout() {
		
	}
}
