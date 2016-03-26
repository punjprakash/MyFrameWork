package com.qait.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Mercury_HomePage extends GetPage {

	WebDriver driver;
	String pagename = "Mercury_HomePage";
	int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

	public Mercury_HomePage(WebDriver driver) {
		super(driver, "Mercury_HomePage");
		this.driver = driver;
	}

	public void loginMercurySite(String loginID, String password) {
		isElementDisplayed("inp_userName");
		element("inp_userName").sendKeys(loginID);
		isElementDisplayed("inp_password");
		element("inp_password").sendKeys(password);
		logMessage("Login " + loginID + " and password " + password + "has enterned"
				+ "\n");
		isElementDisplayed("btn_signIn");
		element("btn_signIn").click();
		logMessage("Step: click btn_signIn\n");

	}
	
	public void clickContinue() {
		isElementDisplayed("btn_continue");
		element("btn_signIn").click();
		logMessage("Step: click btn_continue\n");
	}
}
