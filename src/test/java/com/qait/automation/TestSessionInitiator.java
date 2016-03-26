package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.automation.utils.TakeScreenshot;
import com.qait.keywords.*;

public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String Punj;
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
	 * Page object Initiation done
	 */

	public TestSessionInitiator(String testname,String browserType) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname,browserType);
	}

	private void testInitiator(String testname,String browserType) {
		setYamlFilePath();
		_configureBrowser(browserType);
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser(String browserType) {
		driver = wdfactory.getDriver(_getSessionConfig(),browserType);
		driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver",
				"seleniumserverhost", "timeout", "driverpath" };
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
