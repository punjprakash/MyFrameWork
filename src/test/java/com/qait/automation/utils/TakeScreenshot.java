/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.utils;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 *
 
 */
public class TakeScreenshot {
    
    WebDriver driver;
    String testname;
    String screenshotPath = "/target/Screenshots";
    
    public TakeScreenshot(String testname, WebDriver driver) {
        this.driver = driver;
        this.testname = testname;
    }
    
    public void takeScreenshot() {
        screenshotPath = (getProperty("screenshot-path") != null) ? getProperty("screenshot-path") : screenshotPath;
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        // DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_a");
        Date date = new Date();
        String date_time = dateFormat.format(date);
        
        //Dateformat for file
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_a");
        Date date1 = new Date();
        String date_time1 = dateFormat1.format(date1);
      
        File file = new File(System.getProperty("user.dir") + File.separator + screenshotPath + File.separator + this.testname
                + File.separator + date_time);
        boolean exists = file.exists();
        if (!exists) {
            new File(System.getProperty("user.dir") + File.separator + screenshotPath + File.separator + this.testname
                    + File.separator + date_time).mkdir();
        }
        
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       
        
        
        try {
            String saveImgFile = System.getProperty("user.dir") + File.separator + screenshotPath
                    + File.separator + this.testname + File.separator + date_time
                    + File.separator + date_time1+" screenshot.png";
            Reporter.log("Save Image File Path : " + saveImgFile, true);
            FileUtils.copyFile(scrFile, new File(saveImgFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void takeScreenShotOnException(ITestResult result) {
        String takeScreenshot = getProperty("take-screenshot");
        if (result.getStatus() == ITestResult.FAILURE) {
            Reporter.log("FAILURE occured at " + DateUtil.converttimestamp(System.currentTimeMillis()), true);
            if (takeScreenshot.equalsIgnoreCase("true") || takeScreenshot.equalsIgnoreCase("yes")) {
                try {
                    if (driver != null) {
                        takeScreenshot();
                    }
                } catch (Exception ex) {
                    Reporter.log("Driver is null while taking screen shot", true);
                }
            }
        }
    }
    
}
