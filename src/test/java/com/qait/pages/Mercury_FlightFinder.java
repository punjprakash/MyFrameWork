package com.qait.pages;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Mercury_FlightFinder extends GetPage {

	WebDriver driver;
	String pagename = "Mercury_FlightFinder";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
	
	public Mercury_FlightFinder(WebDriver driver) {
		super(driver, "Mercury_FlightFinder");
		this.driver = driver;
	}

	public void enterFlightDetails(String flightType, String passangersNo,
			String departingFrom, String departingOnMonth, String departingOnDay, String arrivingIn,
			String returiningOnMonth, String returiningOnDay) {
	
		if (flightType.equalsIgnoreCase("roundtrip")) {
			isElementDisplayed("rb_roundTrip");
			element("rb_roundTrip").click();
		} else {
			isElementDisplayed("oneway");
			element("oneway").click();
		}
		logMessage("Step: " +flightType+ " entered");
		
		isElementDisplayed("lst_pssngr");
		element("lst_pssngr").sendKeys(passangersNo);
		logMessage("Step: " +passangersNo+ " entered");
		
		isElementDisplayed("lst_dprtFrmCity");
		element("lst_dprtFrmCity").sendKeys(departingFrom);
		logMessage("Step: " +departingFrom+ " entered");
		
		isElementDisplayed("lst_dprtOnMnth");
		element("lst_dprtOnMnth").sendKeys(departingOnMonth);
		logMessage("Step: " +departingOnMonth+ " entered ");
		
		isElementDisplayed("lst_dprtOnDt");
		element("lst_dprtOnDt").sendKeys(departingOnDay);
		logMessage("Step: " +departingOnDay+ " entered");
		
		isElementDisplayed("lst_arrvInCity");
		element("lst_arrvInCity").sendKeys(arrivingIn);
		logMessage("Step: " +arrivingIn+ " entered");
		
		isElementDisplayed("lst_rtnOnMnth");
		element("lst_rtnOnMnth").sendKeys(returiningOnMonth);
		logMessage("Step: " +returiningOnMonth+ " entered");
		
		isElementDisplayed("lst_rtnOnDt");
		element("lst_rtnOnDt").sendKeys(returiningOnDay);
		logMessage("Step: " +returiningOnDay+ " entered");
	
	}
	
	public void clickContinue() {
		isElementDisplayed("btn_continue");
		element("btn_continue").click();
		logMessage("Step: click btn_continue\n");
	}


}
