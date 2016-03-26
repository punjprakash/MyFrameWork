package com.qait.pages;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.CommonActions;
import com.qait.automation.utils.ConfigPropertyReader;

public class Mercury_BookAFlight extends CommonActions {

	WebDriver driver;
	String pagename = "Mercury_BookAFlight";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public Mercury_BookAFlight(WebDriver driver) {
		super(driver, "Mercury_BookAFlight");
		this.driver = driver;
	}
	public void enterFlightDetails(String fstName, String lastName,
			String creditcardNo, String creditCard) {
	
		isElementDisplayed("inp_FirstName");
		element("inp_FirstName").sendKeys(fstName);
		logMessage("Step: " +fstName+ " entered");
		
		isElementDisplayed("inp_lastName");
		element("inp_lastName").sendKeys(lastName);
		logMessage("Step: " +lastName+ " entered");
		
		isElementDisplayed("inp_credCardNaumber");
		element("inp_credCardNaumber").sendKeys(creditcardNo);
		logMessage("Step: " +creditcardNo+ " entered ");
		
		isElementDisplayed("lst_creditCard");
		element("lst_creditCard").sendKeys(creditCard);
		logMessage("Step: " +creditCard+ " entered");
			
	}
	
	public void clickContinue() {
		isElementDisplayed("btn_continue");
		element("btn_continue").click();
		logMessage("Step: click btn_continue\n");
	}

}
