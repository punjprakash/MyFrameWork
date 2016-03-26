package com.qait.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalSeleniumLibrary {
                
private static WebDriver driver;
                
                // Open ie browser
                public static WebDriver startIEBrowser()
                {
                                return driver = new InternetExplorerDriver();                    
                }
                
                // Open firefox browser
                public static WebDriver startFirefoxBrowser()
                {
                                return driver = new FirefoxDriver();
                }
                
                // Open chrome browser
                public static WebDriver startChromeBrowser()
                {
                                System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");                         
                   return driver = new ChromeDriver();
                }
                
                // Enter Text-box
                public static void enterTextField(String inputString, By by)            
                {
                                WebElement textFieldElement = driver.findElement(by);
                                textFieldElement.clear();                             
                                textFieldElement.sendKeys(inputString);
                }
                
                // Select Drop-Down Element
                public static void selectDropDown(String beSelectedValue, By by)
                {
                                Select dropDownElement = new Select(driver.findElement(by));
                                dropDownElement.selectByVisibleText(beSelectedValue);
                }
                
                // Click button
                public static void clickButton(By by)
                {
                                WebElement buttonElement = driver.findElement(by);
                                buttonElement.click();
                }
                
                
                // Select Checkbox
                public static void selectCheckBox(By by)
                {
                                // locate check box and click it if it is not checked
                                WebElement firstCheckBox = driver.findElement(by);
                                
                                boolean checkBoxStatus = false;                               
                                checkBoxStatus = firstCheckBox.isSelected();
                                
                                if (checkBoxStatus == false)
                                {
                                                firstCheckBox.click();
                                }                              
                }
                
                // Unselect checkbox
                public static void unselectCheckBox(By By)
                {
                                // locate check box and click it to remove check if it is already checked
                                WebElement firstCheckBox = driver.findElement(By);
                                
                                boolean checkBoxStatus = false;                               
                                checkBoxStatus = firstCheckBox.isSelected();
                                
                                if (checkBoxStatus == true)
                                {
                                                firstCheckBox.click();
                                }                              
                }
                
                // Delect radio button by index
                public static void selectRadioButton(By by, int index)
                {
                                //Locate radio button group
                                WebElement radio = driver.findElement(by);
                                List<WebElement> radioButtonGroup = radio.findElements(By.tagName("input"));                        
                                radioButtonGroup.get(index).click();
                }
                
                
                // Select radio button by label name
                public static void selectRadioButton(By by, String labelName)
                {
                                //Locate radio button group
                                WebElement radio = driver.findElement(by);
                                
                                // selecting by label
                                List<WebElement> radioButtonGroupLabels = radio.findElements(By.tagName("label"));
                                for (WebElement labelElement: radioButtonGroupLabels)
                                {
                                                if(labelElement.getText().contains(labelName))
                                                {
                                                                labelElement.click();
                                                                break;
                                                }
                                }
                }
                
                
                
                public static void hoverOverElement(WebElement Menu, By SubMenu ) throws Exception
    {
        //Hover the first Menu Element
        Actions action = new Actions(driver);        
        action.moveToElement(Menu);
        action.perform();
        customWait(1);
        
        //Selects the second sub-menu element
        driver.findElement(SubMenu).click();        
    }
                
                
                // Capture screenshot
                /*public static String captureScreenshot(String imageName, String saveLocationPath) throws Exception
                {
                                String timeStamp = getCurrentTime();
                                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.File);                              
                                FileUtils.copyFile(screenshot, new File(saveLocationPath + imageName + "_"+ timeStamp + ".png"));
                                return saveLocationPath + imageName + "_"+ timeStamp + ".png";
                }*/
                
                
                // generate current time
                public static String getCurrentTime()
                {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());                               
                                return timeStamp;
                }
                
                
                
                public static void customWait(int inSeconds) throws Exception
                {
                                Thread.sleep(inSeconds * 1000);
                }
                
/*           public static void xlDataRead(String spath) throws Exception
                {
                                File myxl = new File(spath);
                                FileInputStream stream = new FileInputStream(myxl);
                                HSSFWorkbook workbook = new HSSFWorkbook(stream);
                                HSSFSheet sheet = workbook.getSheetAt(0);
                                int XDRows = sheet.getLastRowNum()+1;
                                int XDCells = sheet.getRow(0).getLastCellNum();
                                String[][] XData = new String[XDRows][XDCells];
                                for(int i = 0; i < XDRows; i++)
                                {
                                                HSSFRow row = sheet.getRow(i);
                                                for(int j = 0; j < XDCells; j++);
                                                {
                                                                HSSFCell cell = row.getCell(j);
                                                                String value = CellToString(cell);
                                                                XData[i][j] = value;
                                                }
                                }
                }*/
                
                public static WebElement expliciteWaitForElement (By by, int seconds){
                                WebElement myDynamicElement = (new WebDriverWait(driver, seconds))
                                                                  .until(ExpectedConditions.presenceOfElementLocated(by));
                                return myDynamicElement;
                }
                
}
