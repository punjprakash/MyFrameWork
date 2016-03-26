package com.qait.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.assertEquals;

public class CurrentBillingStatement {
                   
                   public WebDriver driver ;
                   public String URL;
                   public String FirstName,LastName,MailID,AccountNumber,PIN;
                   public  SoftAssert  s_assert = new SoftAssert();
                  
                   
                   @BeforeTest
                   public void setUp()
                   {
                                	
                	   			  
                                   URL= "https://conv03.ftr.com/";
                                   FirstName = "MCLAUGHLIN";
                                   LastName = "ROBERT";
                                   AccountNumber = "28157360000725015";
                                   PIN = "3702";
                                   MailID = "kuraf1213+2@gmail.com";
                                   driver = GlobalSeleniumLibrary.startFirefoxBrowser();
                                   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                                   driver.manage().window().maximize();
                                   driver.get(URL);
                   
                   }
                   
                   @AfterTest
                   public void cleanUp()
                   {
                                   System.out.println("Test Completed,Logged out");
                                   //driver.close();
                                   //driver.quit();
                   }
                   
                   @Test(priority =1)
                   public void test() throws Exception{
                	   
                	
                                   
                   // clicking pay bill
                   WebElement SignIn = driver.findElement(By.linkText("Sign In"));
                   SignIn.click();
                    
                  // Enter User name  
                   GlobalSeleniumLibrary.expliciteWaitForElement(By.id("txtID"), 10);
                   GlobalSeleniumLibrary.enterTextField("kuraf1213+2@gmail.com",By.id("txtID"));
                   
                   //Enter PW
                   GlobalSeleniumLibrary.expliciteWaitForElement(By.id("txtPassword"), 10);
                   GlobalSeleniumLibrary.enterTextField("Hap1tohtaptur",By.id("txtPassword"));
                   
                   //click sign in 
                   GlobalSeleniumLibrary.clickButton(By.id("btnSignIn"));
                   GlobalSeleniumLibrary.customWait(3);
                   
                   // Verify account summary page displayed 
                   List<WebElement> AccountSummary = driver.findElements(By.cssSelector(".account-header"));
                   System.out.println("verify text: " + AccountSummary.get(0).getText());
                   assertEquals("Account Summary",AccountSummary.get(0).getText());
                   
                   //Verify Account number displayed
                   WebElement AccountNumber = driver.findElement(By.cssSelector(".static-i.ng-binding"));
                   System.out.println("verify text: " + AccountNumber.getText());
                   assertEquals("281-573-6000-072501-5 MARY A.",driver.findElement(By.cssSelector(".static-i.ng-binding")).getText());

                   GlobalSeleniumLibrary.customWait(3);

                   // Verify Previous Balance  displayed 
                   GlobalSeleniumLibrary.expliciteWaitForElement(By.cssSelector(".col-md-9.gray.nopad-right"), 10);
                   List<WebElement> PreviousBalance = driver.findElements(By.cssSelector(".col-md-9.gray.nopad-right"));
                   System.out.println("verify text: " + PreviousBalance.get(0).getText());
                   assertEquals("Previous Balance",PreviousBalance.get(0).getText());

                   // Verify Balance Forward displayed
                   //assertEquals("Balance Forward",driver.findElement(By.xpath(".//*[@id='summary']/div/div[5]/div[1]/div[1]/strong")).getText());
                   
                   
                   // Verify New Charges due date displayed need to parameterize
                   GlobalSeleniumLibrary.expliciteWaitForElement(By.cssSelector(".col-md-9.nopad-left.nopad-right>p"), 10);
                   List<WebElement> NewChargesDue = driver.findElements(By.cssSelector(".col-md-9.nopad-left.nopad-right>p"));
                   System.out.println("verify text: " + NewChargesDue.get(2).getText());
                   assertEquals("New Charges Due Date Dec 28, 2015",NewChargesDue.get(2).getText());
                  
                   
                   Actions action = new Actions(driver);
                   WebElement mainMenu = driver.findElement(By.linkText("My Bills"));
                   action.moveToElement(mainMenu).moveToElement(driver.findElement(By.id("mnuStCurrent"))).click().build().perform();
                   
                   
                   //Steep 14 verify in current bill 
                   //GlobalSeleniumLibrary.expliciteWaitForElement(By.cssSelector(".intro-txt"), 20);
                   //List<WebElement> CurrentBillIntro = driver.findElements(By.cssSelector(".intro-txt"));
                   //System.out.println("verify text: " + CurrentBillIntro.get(2).getText());
                   //assertEquals("Here is a detailed view of your current Frontier bill. Click the plus sign (+) to expand each section and reveal line item charges.",CurrentBillIntro.get(2).getText());
                   
                   
                   WebElement accountSummary = driver.findElement(By.linkText("Account Summary"));
                   //action.moveToElement(accountSummary).moveToElement(driver.findElement(By.cssSelector(selector))).click().build().perform();
                   action.moveToElement(accountSummary).moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Account Summary')])[2]"))).click().build().perform();
                   
                   
                   
                   //Verify previous balance
                   WebElement previousBalance = driver.findElement(By.cssSelector(".col-md-9.gray.nopad-right"));
              
                   ////div[@class='col-md-9 gray nopad-right']
                   System.out.println("Verify text: "+previousBalance.getText());
                   assertEquals("Previous Balance",previousBalance.getText());
                   
                   //Step 15 Validate the display of the Account field on the Current Bill screen.
                   //Verify Account number displayed
                   WebElement AccountNumberCurBil = driver.findElement(By.cssSelector(".static-i.ng-binding"));
                   System.out.println("verify text: " + AccountNumberCurBil.getText());
                   assertEquals("281-573-6000-072501-5 MARY A.",driver.findElement(By.cssSelector(".static-i.ng-binding")).getText());
                   
                   //Verify Balance Forward
                   WebElement balanceForward = driver.findElement(By.cssSelector(".col-sm-8.nopad-left.nopad-right"));
                   System.out.println("Verify text: "+balanceForward.getText());
                   //s_assert.assertEquals(balanceForward.getText().trim(), "Balance Forward@@@","The life is Zen");
                   	assertEquals(balanceForward.getText().trim(), "Balance Forward@@@","The life is Zen");
                    System.out.println("Verify text after assert: "+balanceForward.getText());
                   
                   
                   
                   //assertEquals("Balance Forward ",balanceForward.getText().trim());
                  // s_assert.assertEquals("Balance Forward@@@   ",balanceForward.getText().trim());
                   s_assert.assertEquals(balanceForward.getText().trim(), "Balance Forward@@@","The life is Zen");
                   System.out.println("Verified text: "+balanceForward.getText());
                 
                   
                   
                   //WebElement postive =driver.findElement(By.cssSelector("H4:contains (FRONTIER MONTHLY SERVICE CHARGES)"));???
                   
                   WebElement postive =driver.findElement(By.xpath("//h4[contains(.,' FRONTIER MONTHLY SERVICE CHARGES')]"));
                   
                   //h4[contains(.,' FRONTIER MONTHLY SERVICE CHARGES$33.74')]'
                   
                   postive.click();
                                          
                   //Click the plus sign to expand 
                   //WebElement ftrServicecharge = driver.findElement(By.cssSelector(".fa.fa-1x.fa-plus-circle"));
                   //H4:contains("FRONTIER MONTHLY SERVICE CHARGES")
                   
                   GlobalSeleniumLibrary.expliciteWaitForElement(By.cssSelector(".panel-title.ng-binding.ng-scope"), 10);
                   List<WebElement> ftrServicecharge = driver.findElements(By.cssSelector(".panel-title.ng-binding.ng-scope"));
                   ftrServicecharge.get(0).click();
                   GlobalSeleniumLibrary.customWait(1);
                   assertEquals("TOTAL MONTHLY SERVICE CHARGES",driver.findElements(By.cssSelector(".col-md-7.ng-binding")).get(3).getText());
                   ftrServicecharge.get(1).click();
                   GlobalSeleniumLibrary.customWait(1);
                   assertEquals("TOTAL TAXES AND OTHER CHARGES",driver.findElements(By.cssSelector(".col-md-7.ng-binding")).get(3).getText());
                   s_assert.assertAll();
          
                 

   }
                   /*@Test(priority =2)
                   public void test2 () throws Exception{
                	   
                	    //Verify Balance Forward
                       WebElement balanceForward = driver.findElement(By.cssSelector(".col-sm-8.nopad-left.nopad-right"));
                       System.out.println("Verify text2: "+balanceForward.getText());
                       //s_assert.assertEquals(balanceForward.getText().trim(), "Balance Forward@@@","The life is Zen");
                       	assertEquals(balanceForward.getText().trim(), "Balance Forward@@@","The life is Zen");
                        System.out.println("Verify text after assert: "+balanceForward.getText());
                   }*/
                
}
