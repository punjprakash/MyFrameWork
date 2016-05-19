package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;
import static com.qait.automation.utils.Xls_Reader.setdataFilePath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.automation.report.BaseReport;
import com.qait.automation.utils.TakeScreenshot;
import com.qait.pages.*;

public class TestSessionInitiatorExcel {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects
	 */
	
	public Mercury_HomePage Mercury_HomePage;
	public Mercury_FlightFinder Mercury_FlightFinder;
	public Mercury_SelectFlight Mercury_SelectFlight;
	public Mercury_BookAFlight Mercury_BookAFlight;
	public Mercury_FlightConfirmation Mercury_FlightConfirmation;


	public TakeScreenshot takescreenshot;
	public BaseReport BaseReport;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
	
		Mercury_HomePage = new Mercury_HomePage(driver);
		Mercury_FlightFinder = new Mercury_FlightFinder(driver);
		Mercury_SelectFlight = new Mercury_SelectFlight(driver);
		Mercury_BookAFlight = new Mercury_BookAFlight(driver);
		Mercury_FlightConfirmation = new Mercury_FlightConfirmation(driver);
	
	}

	/**
	 * 
	 * Page object Initiation done
	 */

	public TestSessionInitiatorExcel(String testname,String appName,String browserType) {
		wdfactory = new WebDriverFactory();
		try{
			testInitiator(testname,appName,browserType);
			}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			
		}
	}

	private void testInitiator(String testname,String appName,String browserType) {
		try{
			//******************** change layer
		setdataFilePath(appName);
		_configureBrowser(browserType);
		//_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	private void _configureBrowser(String browserType) {
		try{
		driver = wdfactory.getDriver(_getSessionConfig(),browserType);
	
		driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier","env","browser", "seleniumserver",
				"seleniumserverhost", "timeout", "driverpath","driverpathIE" };
		Map<String, String> config = new HashMap<>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("baseurl"));
	}

	public void launchApplication(String baseurl) {
		Reporter.log("\nThe application url is :- " + baseurl, true);
		Reporter.log(
				"The test browser is :- " + _getSessionConfig().get("browser")
						+ "\n", true);
		deleteAllCookies();
		driver.get(baseurl);
		
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
		} catch (IOException e1) {
		}
		driver.quit();
		
		
	}

	public void deleteAllCookies(){
		driver.manage().deleteAllCookies();
	}
	
	
	
	public void closeBrowserWindow() {
		driver.close();
	}
}
