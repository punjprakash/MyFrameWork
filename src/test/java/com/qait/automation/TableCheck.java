package com.qait.automation;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.*;




public class TableCheck {
	
public static void main(String[] args) {
	
	System.out.println("Punj");
	WebDriver driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://www.toolsqa.com/automation-practice-table");
	// Take screenshot and store as a file format

	TakesScreenshot take=new FirefoxDriver();
	
	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//File src= take.getScreenshotAs(getScreenshotAs(OutputType.FILE);
	try {
	 // now copy the  screenshot to desired location using copyFile //method
	FileUtils.copyFile(src, new File("C:/error.png"));
	}
	 
	catch (IOException e)
	 {
	  System.out.println(e.getMessage());
	 
	 }
	
	
	/*
	String sRow = "1";
	String sCol = "1";
	
	//Here we are locating the xpath by passing variables in the xpath
	String sCellValue = driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + sRow + "]/td[" + sCol + "]")).getText();
	System.out.println(sCellValue);
	String sRowValue = "Clock Tower Hotel";
	
	//First loop will find the 'ClOCK TWER HOTEL' in the first column
	for (int i=1;i<=5;i++){
		String sValue = null;
		sValue = driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + i + "]/th")).getText();
			if(sValue.equalsIgnoreCase(sRowValue)){
				// If the sValue match with the description, it will initiate one more inner loop for all the columns of 'i' row 
				for (int j=1;j<=5;j++){
					String sColumnValue= driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + i + "]/td["+ j +"]")).getText();
					System.out.println(sColumnValue);
				}
			break;
			}
		}
	driver.close();
	*/
}
}
