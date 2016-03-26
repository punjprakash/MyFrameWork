package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.utils.LayoutValidation;

public class GetPage extends BaseUi {

	protected WebDriver webdriver;
	String pageName;
	LayoutValidation layouttest;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
		layouttest = new LayoutValidation(driver, pageName);
	}

	public void testPageLayout(List<String> tagsToBeTested) {
		layouttest.checklayout(tagsToBeTested);
	}

	public void testPageLayout(String tagToBeTested) {
		testPageLayout(Arrays.asList(tagToBeTested));
	}

	public void testPageLayout() {
		testPageLayout(Arrays.asList(getProperty("./Config.properties",
				"browser")));
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId
					.trim()));
		}
	}

	protected WebElement element(String elementToken)
			throws NoSuchElementException {
		return element(elementToken, "");
	}
	
	protected boolean elementExist(String elementToken)throws NoSuchElementException {
		By locator = getLocator(elementToken, "");
		logMessage("Element is searched :-- " + elementToken);
		Boolean isPresent = webdriver.findElements(locator).size() > 0;
		logMessage("Element is searched :-- " + webdriver.findElements(locator).size());
		return isPresent;
	}
	
	protected void clickByJavascript(String locator) {
		
		WebElement elem = element(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",elem );
		//((JavascriptExecutor) driver).executeScript(script);
	}
			
	protected WebElement elementEnter(String elementToken, String replacement)
			throws NoSuchElementException {
		WebElement elem = null;
		By locator = getLocator(elementToken, replacement);
		elem=webdriver.findElement(locator);
		webdriver.findElement(locator).sendKeys(Keys.ENTER);
		return elem;
	}


	protected WebElement element(String elementToken, String replacement)
			throws NoSuchElementException {
		WebElement elem = null;
		By locator = getLocator(elementToken, replacement);
		try {
			elem = wait.waitForElementToBeVisible(webdriver
					.findElement(locator));
			logMessage("Element is searched :-- " + elementToken);
		} catch (TimeoutException excp) {
			throw new NoSuchElementException("Element " + elementToken
					+ " with locator " + locator.toString().substring(2)
					+ " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver
				.findElements(getLocator(elementToken, replacement)));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected void isStringMatching(String actual, String expected) {
		Assert.assertEquals(actual, expected);
		logMessage("ACTUAL STRING : " + actual);
		logMessage("EXPECTED STRING :" + expected);
		logMessage("String compare Assertion passed.");
	}

	protected boolean isElementDisplayed(String elementName,
			String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result, "ASSERT FAILED: element '" + elementName
				+ "with text " + elementTextReplace + "' is not displayed.");
		logMessage("ASSERT PASSED: element " + elementName + " with text "
				+ elementTextReplace + " is displayed.");
		return result;
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText().trim(), expectedText,
				"ASSERT FAILED: element '" + elementName
						+ "' Text is not as expected: ");
		logMessage("ASSERT PASSED: element " + elementName
				+ " is visible and Text is " + expectedText);
	}

	protected void verifyElementTextContains(String elementName,
			String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("ASSERT FAILED: element '" + elementName
				+ "' Text is not as expected: ", element(elementName).getText()
				.trim(), containsString(expectedText));
		logMessage("ASSERT PASSED: element " + elementName
				+ " is visible and Text is " + expectedText);
	}

	protected boolean isElementDisplayed(String elementName)
			throws NoSuchElementException {
		boolean result = wait.waitForElementToBeVisible(element(elementName))
				.isDisplayed();
		assertTrue(result, "ASSERT FAILED: element '" + elementName
				+ "' is not displayed.");
		logMessage("ASSERT PASSED: element " + elementName + " is displayed.");
		return result;
	}

	protected boolean isElementEnabled(String elementName, boolean expected) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = expected && element(elementName).isEnabled();
		assertTrue(result, "ASSERT FAILED: element '" + elementName
				+ "' is  ENABLED :- " + !expected);
		logMessage("ASSERT PASSED: element " + elementName + " is enabled :- "
				+ expected);
		return result;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	public String getCompleteStateName(String abbreviatedName) {
		switch (StateAbbreviations.valueOf(abbreviatedName)) {
		case AL:
			return "Alabama";
		case AK:
			return "Alaska";
		case AZ:
			return "Arizona";
		case AR:
			return "Arkansas";
		case CA:
			return "California";
		case CO:
			return "Colorado";
		case CT:
			return "Connecticut";
		case DE:
			return "Delaware";
		case FL:
			return "Florida";
		case GA:
			return "Georgia";
		case HI:
			return "Hawaii";
		case ID:
			return "Idaho";
		case IL:
			return "Illinois";
		case IN:
			return "Indiana";
		case IA:
			return "Iowa";
		case KS:
			return "Kansas";
		case KY:
			return "Kentucky";
		case LA:
			return "Louisiana";
		case ME:
			return "Maine";
		case MD:
			return "Maryland";
		case MA:
			return "Massachusetts";
		case MI:
			return "Michigan";
		case MN:
			return "Minnesota";
		case MS:
			return "Mississippi";
		case MO:
			return "Missouri";
		case MT:
			return "Montana";
		case NE:
			return "Nebraska";
		case NV:
			return "Nevada";
		case NH:
			return "New Hampshire";
		case NJ:
			return "New Jersey";
		case NM:
			return "New Mexico";
		case NY:
			return "New York";
		case NC:
			return "North Carolina";
		case ND:
			return "North Dakota";
		case OH:
			return "Ohio";
		case OK:
			return "Oklahoma";
		case OR:
			return "Oregon";
		case PA:
			return "Pennsylvania";
		case RI:
			return "Rhode Island";
		case SC:
			return "South Carolina";
		case SD:
			return "South Dakota";
		case TN:
			return "Tennessee";
		case TX:
			return "Texas";
		case UT:
			return "Utah";
		case VT:
			return "Vermont";
		case VA:
			return "Virginia";
		case WA:
			return "Washington";
		case WV:
			return "West Virginia";
		case WI:
			return "Wisconsin";
		case WY:
			return "Wyoming";
		default:
			return null;
		}
	}

}
