package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseClass;

public class UserHelper{
	private final Duration TIMEOUT = Duration.ofSeconds(10);
	
	protected BaseClass bcObj = new BaseClass();
	protected WebDriver driver = bcObj.getDriver();
	private WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
	private JavascriptExecutor jse = (JavascriptExecutor)driver;
	
    /**
     * Sets to adjust the zoom level of a webpage.
     *
     * @param percentage is an integer.
     * @return void.
     */
	public void setBrowserZoom(int percentage) {
		jse.executeScript("document.body.style.zoom = '"+ percentage + "%';");
	}
	
	/**
     * Set text value in the text box.
     *
     * @param selector is of string data type for Javascript syntax to selector a text box element 
     * @param value is of string data type as the input value in the element 
     * @return void.
     */
	public void jseSetValue(String selector, String value) {
		// CLEAR TEXT
		jse.executeScript(String.format("document.querySelector('%s').value = ''", selector));
		
		// INPUT VALUE
		jse.executeScript(String.format("document.querySelector('%s').value = '%s'"
				, selector
				, value));
	}
	
	/**
     * Selects the visible text in the dropdown.
     *
     * @param e is a WebElement 
     * @param value is of string data type as the value to be selected in the element 
     * @return void.
     */
	public void selectByVisibleText(WebElement e, String value) {
		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(value);
	}

	/**
     * Selects the value in the option tags.
     *
     * @param e is a WebElement 
     * @param value is of string data type as the value to be selected in the element 
     * @return void.
     */
	public void selectByValue(WebElement e, String value){
		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public List<WebElement> selectOptions(WebElement e) {
		Select dropdown = new Select(e);
		return dropdown.getOptions();
	}

	/**
     * Command to perform a double click on the element.
     *
     * @param e is a WebElement 
     * @return void.
     */
	public void doubleClick(WebElement e) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e).doubleClick().build().perform();
	}

	/**
     * Commands the element to simulate mouse hover.
     *
     * @param e is a WebElement 
     * @return void.
     */
	public void hover(WebElement e) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e).build().perform();
	}

	/**
     * Command to assert the expected text against actual text.
     *
     * @param keyword is a String. "EQUAL" or "CONTAINS". Not case sensitive.
     * @param actualText is a String
     * @param expectedText is a String
     * @return void.
     */
	public void validateText(String keyword, String actualText, String expectedText) {
		switch (keyword.toUpperCase()) {
		case "EQUAL":
			Assert.assertEquals(actualText, expectedText);
			break;
		case "CONTAINS":
			Assert.assertTrue(actualText.contains(expectedText));
		}
	}

	// Windows-------------------------------------------------------------
	
	/**
     * Sets the current window as the parent window.
     *
     * @return String: name of the current window.
     */
	public String setParentWindow() {
		String parentWindow = driver.getWindowHandle();
		return parentWindow;
	}

	/**
     * Gets all the window handles available in the current browser instance.
     *
     * @return Set<String>: Returns all the window handle values.
     */
	public Set<String> getWindowHandles() {
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}

	/**
     * Used to switch from parent window to child window.
     *
     * @param parentWindow is a String. Name of the parent window
     * @param windowHandles is a Set<String>. List of windows
     * @return void
     */
	public void switchToChildWindow(String parentWindow, Set<String> windowHandles) {
		try {
			Set<String> windows = windowHandles;
			Iterator<String> iterator = windows.iterator();
			while (iterator.hasNext()) {
				String childWindow = iterator.next();
				if (!parentWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to switch to child window. " + e.getStackTrace());
		}
	}

	/**
     * Used to switch from from child window to parent window.
     *
     * @param windowHandles is a Set<String>. List of windows
     * @return void
     */
	public void switchToParentWindow(Set<String> windowHandles) {
		try {
			Set<String> windows = windowHandles;
			List<Object> windowsList = Arrays.asList(windows.toArray());
			driver.switchTo().window(windowsList.get(0).toString());
		} catch (Exception e) {
			System.out.println("Unable to switch to parent window. " + e.getStackTrace());
		}
	}

	// Frames--------------------------------------------------------------
	/**
     * Switch back to the main frame/main document which contains the iframes.
     *
     * @return void
     */
	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
			Wait(1000);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());

		} catch (Exception e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());
		}
	}

	/**
     * Switch back to the parent frame.
     *
     * @return void
     */
	public void switchToParentFrame() {
		try {
			driver.switchTo().parentFrame();
			Wait(1000);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());

		} catch (Exception e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());
		}
	}

	/**
     * Switch to the frame with index value
     *
     * @param index is an Integer. An index of the iframe.
     * @return void
     */
	public void switchToFrameByIndex(int index) {

		try {
			driver.switchTo().frame(index);
			Wait(1000);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());

		} catch (Exception e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());
		}
	}

	/**
     * Switch to the frame with name/ID value.
     *
     * @param value is an String. Name of the frame.
     * @return void
     */
	public void switchToFrameByNameorID(String value) {
		try {
			driver.switchTo().frame(value);
			Wait(1000);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());

		} catch (Exception e) {
			System.out.println("Unable to locate frame. " + e.getStackTrace());
		}
	}

	/**
     * Switch to the frame using its WebElement locator.
     *
     * @param locator is By object. Locator of the element.
     * @return void
     */
	public void switchToFrameByLocator(By locator) {
		try {
			WebElement frameElement = driver.findElement(locator);
			if (frameElement.isDisplayed()) {
				driver.switchTo().frame(frameElement);
				Wait(1000);
			} else {
				System.out.println("Unable to locate frame.");
			}
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame ." + e.getStackTrace());
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with is not attached to the page document. " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to locate to frame. " + e.getStackTrace());
		}
	}

	// Alerts--------------------------------------------------------------
	/**
     * Function to check if an alert pop-up message is present.
     *
     * @return boolean
     */
	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
     * Command to accept (click OK) the alert pop-up message.
     *
     * @return void
     */
	public void acceptAlert() {
		boolean alert = false;
		wait = new WebDriverWait(driver, TIMEOUT);

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			alert = true;
		} catch (TimeoutException e) {
			Reporter.log("No Alert present!");
			alert = false;
		}

		if (alert == true) {
			driver.switchTo().alert().accept();
		} else {
			return;
		}

		waitToLoadPage();
	}

	/**
     * Command to dismiss (click Cancel) the alert pop-up message.
     *
     * @return void
     */
	public void dismissAlert() {
		boolean alert = false;

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			alert = true;
		} catch (TimeoutException e) {
			Reporter.log("No Alert present!");
			alert = false;
		}

		if (alert == true) {
			driver.switchTo().alert().dismiss();
		} else {
			return;
		}

		waitToLoadPage();
	}

	/**
     * Command to get the text value of the alert pop-up message.
     *
     * @return void
     */
	public String getAlertText() {
		boolean alert = false;
		wait = new WebDriverWait(driver, TIMEOUT);

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			alert = true;
		} catch (TimeoutException e) {
			Reporter.log("No Alert present!");
			alert = false;
		}

		if (alert == true) {
			return driver.switchTo().alert().getText();

		} else {
			return "No Alert Message!";
		}
	}

	/**
     * Command to set text to prompt alert pop-up message.
     *
     * @param value as String. Input value of the pop-up alert
     * @return void
     */
	public void setAlertText(String value) {
		boolean alert = false;

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			alert = true;
		} catch (TimeoutException e) {
			Reporter.log("No Alert present!");
			alert = false;
		}

		if (alert == true) {
			driver.switchTo().alert().sendKeys(value);
		} else {
			return;
		}

		waitToLoadPage();
	}

	// Move and Highlight---------------------------------------------------
	/**
     * Highlights the target element in the page.
     *
     * @param e as WebElement. Locator of the element
     * @return void
     */
	public void moveAndHighlightElement(WebElement e) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e).build().perform();
		jse.executeScript("arguments[0].scrollIntoView(true);", e);
		jse.executeScript("arguments[0].style.border='2px solid blue'", e);
	}

	// Wait-----------------------------------------------------------------
	/**
     * Wait for the element to load.
     *
     * @param e as WebElement. Locator of the element.
     * @return void
     */
	public void waitForElement(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	/**
     * Wait for the element to be clickable.
     *
     * @param e as WebElement. Locator of the element.
     * @return void
     */
	public void waitForClickable(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	/**
     * Wait for the element to be clickable.
     *
     * @param e as WebElement. Locator of the element.
     * @return void
     */
	public void waitToLoadPage() {
		String state = jse.executeScript("return document.readyState;").toString();

		while (!state.equalsIgnoreCase("complete")) {
			try {
				if (state.equalsIgnoreCase("complete")) {
					break;
				} else {
					driver.manage().timeouts().pageLoadTimeout(TIMEOUT);
				}
			} catch (Exception ex) {
				driver.navigate().refresh();
			}
		}
	}

	/**
     * Wait/Pause command
     *
     * @param ms as integer. Pause time is in milliseconds.
     * @return void
     */
	public void Wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Alternative to WebElement.click(). Used when normal click is not working.
     *
     * @param e as WebElement. Locator of the element.
     * @return void
     */
	public void actionsClick(WebElement e) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e).click().build().perform();
	}

	
	public void uploadaFile(String fileName) {

		try {
			String filePath = System.getProperty("user.dir") + "\\sikuli_snap\\";
			String inputFilePath = System.getProperty("user.dir") + "\\testData\\";

			Screen s = new Screen();
			Pattern fileInputTextBox = new Pattern(filePath + "FileTextBox.png");
			Pattern openButton = new Pattern(filePath + "OpenButton.png");
			s.wait(fileInputTextBox, 30);
			s.type(fileInputTextBox, inputFilePath + fileName);
			s.click(openButton);
		} 
		catch (FindFailed e) {
			e.printStackTrace();
		}

	}

	// ReportLogs-----------------------------------------------------------
	/**
     * Function used to tag the test case methods as pass, 
     * to take screenshots and attach them to the extent HTML report 
     * and copy the screenshot to the destination folder. 
     * (//extentReports//screenshots).
     *
     * @param methodName as String. Name of the function to be logged in the report.
     * @param desc as String. Description of the function to be logged in the report.
     * @return void
     */
	public void reportPass(String methodName, String desc) {
		String SrcBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		LocalDateTime now = LocalDateTime.now();
		String dt = now.toString().replace(":", ".");
		
		// copies screenshot to destLoc
		String scrFilePath = System.getProperty("user.dir") + "\\extentReports\\screenshots\\";
		String scrFileWithPath = scrFilePath + methodName + "_" + dt + ".png";

		File SrcFile = OutputType.FILE.convertFromBase64Png(SrcBase64);
		File DestFile = new File(scrFileWithPath);
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		TestNGListeners.extentTest.get().pass(desc,
				MediaEntityBuilder.createScreenCaptureFromBase64String(SrcBase64).build());

		Reporter.log(methodName + ": "+ desc);
	}

	public void reportFail(String methodName, String desc) {
		Assert.fail(methodName + ": "+ desc);
	}
}
