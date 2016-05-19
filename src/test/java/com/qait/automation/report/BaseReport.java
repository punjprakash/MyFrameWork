package com.qait.automation.report;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.automation.report.*;
import com.qait.automation.report.ReporterHTML.TestStatus;
import com.qait.automation.report.ReporterHTML.updateValue;



public class BaseReport {
	
	public long vScriptStartTime = Calendar.getInstance().getTimeInMillis();
	public static String scriptName = null;
	public static String suiteName = null;
	String browserName = null;
	String Env = "QA";
	WebDriver driver;
	
	
	@BeforeSuite
	public void AtBeforeSuite(ITestContext ic) throws Exception {
		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		scriptName = ic.getCurrentXmlTest().getName().toString();
		suiteName=ic.getSuite().getName().toString();
	}

	@BeforeTest
	public void AtBeforeTest(ITestContext ic) throws Exception {
		System.out.println("I am in Before test of BaseReport");
		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//System.out.println("Inside - " + methodName);
		//scriptName = getClass().getSimpleName();
		scriptName = ic.getCurrentXmlTest().getName().toString();
		new ReporterHTML(scriptName,ReporterHTML.getDateFormat(ReporterHTML.vDatetype8));
		// UPDATE EXCEL, NOTEPAD AND HTML REPORT WITH THE BROWSER NAME
		
		//Capabilities caps =  ((RemoteWebDriver) driver).getCapabilities();
		
		//ReporterHTML.updateReports(updateValue.bName, "FIreFox", "");
		//browserName = "Firefox";
		
		
		//ReporterHTML.updateReports(updateValue.bName, caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString(), "");
		//browserName = caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString();
		//ReporterHTML.updateReports(updateValue.Env, Env, "");

	}

	@AfterTest
	public void AtAfterTest(ITestContext ic) throws IOException, Exception {
		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//System.out.println("Inside - " + methodName);
		String finalScriptStatus;
		
		System.out.println("---------------------"+ic.getFailedTests().toString());
		String sTemp = ic.getFailedTests().toString();
		
		ReporterHTML.updateReports(updateValue.tEndTime, "", "");
		ReporterHTML.updateReports(updateValue.execTime,ReporterHTML.formatIntoHHMMSS(Calendar.getInstance().getTimeInMillis() - vScriptStartTime).toString(), "");
		ReporterHTML.updateReports(updateValue.totalSteps, "", "");
		
		if(!sTemp.contains("FAILURE")){
			finalScriptStatus = "PASS";
			ReporterHTML.updateReports(updateValue.execStatus, "", finalScriptStatus);
			ReporterHTML.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
			
					
		}else{
			finalScriptStatus = "FAIL";
			ReporterHTML.updateReports(updateValue.execStatus, "", finalScriptStatus);
			ReporterHTML.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
		}
		ReporterHTML.LogEvent(TestStatus.INFO, "", "", "-------->[ End Of Script Execution ]<--------");
		
		//Rename the result log
		ReporterHTML.RenameResultLog(finalScriptStatus);
		//REPORTER.takeScreenShot(finalScriptStatus);
		
		
		//String mailFormat = EMAIL.HTMLMailFormat(scriptName, getTestData_POI("TD_ENV").toString(), browserName, REPORTER.ScriptStartTime, REPORTER.ScriptEndTime, REPORTER.ScriptexecTime, REPORTER.ScripttotalSteps, finalScriptStatus);
		//String mailFormat = SendEmailUsingGMailSMTP.HTMLMailFormat(scriptName, Env, browserName, REPORTER.ScriptStartTime, REPORTER.ScriptEndTime, REPORTER.ScriptexecTime, REPORTER.ScripttotalSteps, finalScriptStatus);
		
		//EMAIL.SendEmail(mailFormat);
		//SendEmailUsingGMailSMTP.SendEmail(mailFormat);
	
	}
	
	@AfterSuite
	public void AtAfterSuite(ITestContext ic) throws Exception {
		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		scriptName = ic.getCurrentXmlTest().getName().toString();
		
		
	}
	

}
