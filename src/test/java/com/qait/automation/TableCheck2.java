package com.qait.automation;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.*;


public class TableCheck2 {
	
public static void main(String[] args) {
	
	System.out.println("Punj");
	
	WebDriver driver = new FirefoxDriver();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://reminfwlacv02.corp.pvt/arsys/shared/login.jsp?/arsys/");
	String sRow = "1";
	String sCol = "1";
	driver.findElement(By.id("username-id")).sendKeys("ppp886");
	driver.findElement(By.id("pwd-id")).sendKeys("ppp886");
	driver.findElement(By.name("login")).click();
	
	//Here we are locating the xpath by passing variables in the xpath
	String sCellValue = driver.findElement(By.xpath(".//*[@class='BaseTable']/tbody/tr[" + sRow + "]/td[" + sCol + "]")).getText();
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
}
}
