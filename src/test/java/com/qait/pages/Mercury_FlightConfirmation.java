package com.qait.pages;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.CommonActions;
import com.qait.automation.utils.ConfigPropertyReader;

public class Mercury_FlightConfirmation extends CommonActions {

	WebDriver driver;
	String pagename = "Mercury_FlightConfirmation";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public Mercury_FlightConfirmation(WebDriver driver) {
		super(driver, "Mercury_FlightConfirmation");
		this.driver = driver;
	}

	public void verifyText(String text) {
		isElementDisplayed("v_Text");
		logMessage("Step: " +text+ " available");
	}
	
	public void clickContinue() {
		isElementDisplayed("lnk_logout");
		element("lnk_logout").click();
		logMessage("Step: click lnk_logout\n");
	}

}
