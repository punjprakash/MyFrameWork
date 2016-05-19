package com.qait.tests;


import java.util.Random;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Match {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.match.com";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
 
  //@Test
  public void testMatchdotcom() throws Exception {
	  
	driver.manage().deleteAllCookies();
    driver.get(baseUrl + "/cpx/en-us/match/IndexPage-10/");
    
    try {
    	assertTrue(false);
	} catch (Error e) {
		// TODO: handle exception
		System.out.println("We are in expection block");
	}
    

    WebElement manWoman;
    
    
    //boolean rest =isElementPresent(By.name("gc"));
    try {
     manWoman=driver.findElement(By.name("gc11"));
    } catch (Exception e) {
    	 manWoman=null;
    	System.out.println("error in Webelement");

    }
    
    try {
    boolean rest=manWoman.isDisplayed();
    System.out.println(rest);
    } catch (Throwable e) {
    	System.out.println("We are in expection block");
	}
 
    
    new Select(driver.findElement(By.name("gc"))).selectByVisibleText("Man");
    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
    new Select(driver.findElement(By.name("tr"))).selectByVisibleText("Woman");
    driver.findElement(By.cssSelector("select[name=\"tr\"] > option[value=\"2\"]")).click();
    new Select(driver.findElement(By.name("lage"))).selectByVisibleText("21");
    driver.findElement(By.cssSelector("option[value=\"21\"]")).click();
    new Select(driver.findElement(By.name("uage"))).selectByVisibleText("23");
    driver.findElement(By.cssSelector("select[name=\"uage\"] > option[value=\"23\"]")).click();
    driver.findElement(By.name("pc")).clear();
    driver.findElement(By.name("pc")).sendKeys("75067");
    driver.findElement(By.cssSelector("button.button.button-primary")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("akshat");
    driver.findElement(By.name("email")).clear();

    int random=getRandomNumberInRange(1000,5000);
    String userName="hakunamta"+random+"@gmail.com";
    
    driver.findElement(By.name("email")).sendKeys(userName);
    
    new Select(driver.findElement(By.id("birthMonth"))).selectByVisibleText("Apr");
    driver.findElement(By.cssSelector("#birthMonth > option[value=\"4\"]")).click();
    new Select(driver.findElement(By.id("birthDay"))).selectByVisibleText("6");
    driver.findElement(By.cssSelector("#birthDay > option[value=\"6\"]")).click();
    new Select(driver.findElement(By.id("birthYear"))).selectByVisibleText("1984");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("usr")).clear();
    driver.findElement(By.id("usr")).sendKeys("hakunamanata");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	driver.findElement(By.partialLinkText("start matching")).click();
	//reset implicit wait
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //Had to use it because sometime next is coming , sometime not   
    
    if (driver.findElements(By.name("Next")).size()>0 && !(driver.findElements(By.name("Next")).get(0).getAttribute("type")).contains("hidden")){
    	
    	driver.findElement(By.name("Next")).click();
    }
  
    closeAlertAndGetItsText();
    
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    
    while (driver.findElements( By.id("SelfEssay_Text") ).size()== 0) {

    	 driver.findElement(By.linkText("keep going »")).click();
    	
	}
    
    
    driver.findElement(By.id("SelfEssay_Text")).click();
    driver.findElement(By.id("SelfEssay_Text")).sendKeys("thiais corect thiais corect thiais corect thiais corect thiais corect thiais corect thiais corect thiais corectv thiais corect thiais corect thiais corect thiais corect thiais corect thiais corect thiais corect thiais corect");
    driver.findElement(By.linkText("keep going »")).click();

  if (driver.findElements(By.name("Next")).size()>0 && !(driver.findElements(By.name("Next")).get(0).getAttribute("type")).contains("hidden")){
    	
    	driver.findElement(By.name("Next")).click();
    
    	
    }
  if (driver.findElements(By.name("Next")).size()>0 && !(driver.findElements(By.name("Next")).get(0).getAttribute("type")).contains("hidden")){
  	
  	driver.findElement(By.name("Next")).click();
  }

    driver.findElement(By.xpath("//a[contains(.,'Next Step »')]")).click();
    
    //Logout
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
   //xapth to get the name : //dd/a
    driver.findElement(By.cssSelector("span")).click();
	
	
	//get the name of the favourite
    String fName=driver.findElement(By.xpath("//dd/a")).getText();
		
    //driver.findElement(By.linkText("Callmebigmike")).click();
    driver.findElement(By.linkText("QQuick view")).click();
    driver.findElement(By.partialLinkText("FAVORITE")).click();
	

	//Signing out
    //mouse mover
    WebElement element =driver.findElement(By.xpath("//span[@class='icon']"));
    Actions hoverOver = new Actions(driver);
	hoverOver.moveToElement(element).build().perform();
    driver.findElement(By.id("ctl00_matchHeader_ctl00_PrimaryNavigationRepeater1_ctl09_Repeater1_ctl03_HyperLink4")).click();
    						
    driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys(userName);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("akshat");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    Thread.sleep((3000));
	
    
    WebElement element1 =driver.findElement(By.xpath("//span[contains(.,'Connections ')]"));
   	hoverOver.moveToElement(element1).build().perform();
    driver.findElement(By.cssSelector("#ctl00_matchHeader_ctl00_PrimaryNavigationRepeater1_ctl05_HyperLink3 > div > span")).click();
    driver.findElement(By.linkText("FFavorites")).click();
    driver.findElement(By.xpath("//form[@id='list-page']/div/label/span")).click();
    
  //get the name of the favourite
    String fName2=driver.findElement(By.cssSelector("h3 > a")).getText();
    
    System.out.println(fName2+" " +fName);
    
    try {
      assertTrue(fName.equalsIgnoreCase(fName2));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (Exception e) {
      //System.out.println(e);
      return false;
      
    }
  }

  //NoSuchElementException
  
  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
