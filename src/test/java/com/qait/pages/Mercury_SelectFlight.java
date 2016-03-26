package com.qait.pages;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Mercury_SelectFlight extends GetPage {

	WebDriver driver;
	String pagename = "Mercury_SelectFlight";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public Mercury_SelectFlight(WebDriver driver) {
		super(driver, "Mercury_SelectFlight");
		this.driver = driver;
	}
	public void enterDepartureAndReturn(String deprtFlight, String returnFlight) {
	
		if (deprtFlight.equalsIgnoreCase("Blue Skies Airlines 361")) {
			isElementDisplayed("rb_bka361");
			element("rb_bka361").click();
		} else {
			isElementDisplayed("rb_bka360");
			element("rb_bka360").click();
		}
		
		if (deprtFlight.equalsIgnoreCase("Blue Skies Airlines 630")) {
			isElementDisplayed("rb_bka630");
			element("rb_bka630").click();
		} else {
			isElementDisplayed("rb_bka631");
			element("rb_bka631").click();
		}
	
	}
	
	public void clickContinue() {
		isElementDisplayed("btn_continue");
		element("btn_continue").click();
		logMessage("Step: click btn_continue\n");
	}


}
