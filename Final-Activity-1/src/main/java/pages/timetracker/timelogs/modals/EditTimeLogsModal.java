package pages.timetracker.timelogs.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.TimeParser;
import utilities.UserHelper;

public class EditTimeLogsModal extends UserHelper {
	private String date;

	@FindBy(id = "editTimeSheetSave")
	private static WebElement btnSave;
	
	@FindBy(id = "editTimeSheetCancel")
	private static WebElement btnCancel;
	
	public EditTimeLogsModal() {
		PageFactory.initElements(driver, this);
	}

	public void setDate(String date) {
		this.date = date;
	}

	/* *****************************
	 *  	WEB LOCATORS 
	 * *****************************
	 */
	private String selectRow() {
		return "//td[text()='" + date + "']/ancestor::tr[@class='manualEditLogsRows']";
	}

	private String getTimeInCell() {
		return selectRow() + "//td[3]";
	}

	private String getTimeInDivId() {
		try {
			WebElement div = driver.findElement(By.xpath(getTimeInCell() + "//div[2]"));
			return div.getAttribute("id");
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found in " 
					+ Thread.currentThread().getStackTrace()[1].getMethodName());
			System.out.println(err.getMessage());
			return null;
		}
	}

	private String getTimeOutCell() {
		return selectRow() + "//td[4]";
	}

	private String getTimeOutDivId() {
		try {
			WebElement div = driver.findElement(By.xpath(getTimeOutCell() + "//div[2]"));
			return div.getAttribute("id");
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found in " 
					+ Thread.currentThread().getStackTrace()[1].getMethodName());
			System.out.println(err.getMessage());
			return null;
		}

	}

	private String getRemarksCell() {
		return selectRow() + "//td[5]";
	}

	private String getReasonCell() {
		return selectRow() + "//td[6]";
	}

	/* *****************************
	 *  	WEB ELEMENTS 
	 * *****************************
	 */

	// TIME-IN - CHECKBOX ELEMENT 
	private WebElement chkboxTimeIn() {
		try {
			return driver.findElement(By.xpath(getTimeInCell() + "//*[contains(@class,'manualCheckIn')]/input"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-IN AM/PM - DROPDOWN LIST ELEMENT 
	private WebElement ddTimeInPeriod() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeInDivId() + " > select.manualFieldInDayPart"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-IN LOCATION - DROPDOWN LIST ELEMENT
	private WebElement ddTimeInLocation() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeInDivId() + " > select.manualFieldInLocation"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-IN IS NEXT DAY - CHECKBOX ELEMENT
	private WebElement chkboxTimeInIsNextDay() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeInDivId() + " > input.manualFieldInNextDay"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	/********************************/
	// TIME-OUT - CHECKBOX ELEMENT 
	private WebElement chkboxTimeOut() {
		try {
			return driver.findElement(By.xpath(getTimeOutCell() + "//*[contains(@class,'manualCheckOut')]/input"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-OUT AM/PM - DROPDOWN LIST ELEMENT 
	private WebElement ddTimeOutPeriod() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeOutDivId() + " > select.manualFieldOutDayPart"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-OUT LOCATION - DROPDOWN LIST ELEMENT
	private WebElement ddTimeOutLocation() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeOutDivId() + " > select.manualFieldOutLocation"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	// TIME-OUT IS NEXT DAY - CHECKBOX ELEMENT
	private WebElement chkboxTimeOutIsNextDay() {
		try {
			return driver.findElement(By.cssSelector("#" + getTimeInDivId() + " > input.manualFieldOutNextDay"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	private WebElement ddRemarks() {
		try {
			return driver.findElement(By.xpath(getRemarksCell() + "//select[contains(@class,'ddlRemarks')]"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}
	
	private WebElement txtRemarks() {
		try {
			return driver.findElement(By.xpath(getRemarksCell() + "//input"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	private WebElement txtReason() {
		try {
			return driver.findElement(By.xpath(getReasonCell() + "//input"));
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found: " + err.getMessage());
			return null;
		}
	}

	/* *****************************
	 *  	ACTIONS 
	 * *****************************
	 */
	public void clickChkboxTimeIn() {
		WebElement e = chkboxTimeIn();
		waitForElement(e);
		moveAndHighlightElement(e);
		e.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Tick Manual Time In");
	}

	public void enterTimeInHr(String value) {
		jseSetValue("#" + getTimeInDivId() + " > input.manualFieldTimeInHr", value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Manual Time In - HH: " + value);
	}

	public void enterTimeInMin(String value) {
		jseSetValue("#" + getTimeInDivId() + " > input.manualFieldTimeInMin", value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Manual Time In - MM: " + value);
	}

	public void selectTimeInPeriod(String value) {
		WebElement e = ddTimeInPeriod();
		waitForElement(e);
		moveAndHighlightElement(e);
		selectByVisibleText(e, value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Time In - Period: " + value);
	}

	public void selectTimeInLocation(String value) {
		WebElement e = ddTimeInLocation();
		waitForElement(e);
		moveAndHighlightElement(e);
		selectByVisibleText(e, value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Time In - Location: " + value);
	}

	public void clickChkTimeInIsNextDay() {
		WebElement e = chkboxTimeInIsNextDay();
		waitForElement(e);
		moveAndHighlightElement(e);
		e.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Tick Time In - Is Next Day?");
	}

	public void clickChkboxTimeOut() {
		WebElement e = chkboxTimeOut();
		waitForElement(e);
		moveAndHighlightElement(e);
		e.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Tick Manual Time In");
	}

	public void enterTimeOutHr(String value) {
		jseSetValue("#" + getTimeOutDivId() + " > input.manualFieldTimeOutHr", value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Manual Time Out - HH: " + value);
	}

	public void enterTimeOutMin(String value) {
		jseSetValue("#" + getTimeOutDivId() + " > input.manualFieldTimeOutMin", value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Manual Time Out - MM: " + value);
	}

	public void selectTimeOutPeriod(String value) {
		WebElement e = ddTimeOutPeriod();
		waitForElement(e);
		moveAndHighlightElement(e);
		selectByVisibleText(e, value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Time Out - Period: " + value);
	}

	public void selectTimeOutLocation(String value) {
		WebElement e = ddTimeOutLocation();
		waitForElement(e);
		moveAndHighlightElement(e);
		selectByVisibleText(e, value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Time Out - Location: " + value);
	}

	public void clickChkTimeOutIsNextDay(String date) {
		WebElement e = chkboxTimeOutIsNextDay();
		waitForElement(e);
		moveAndHighlightElement(e);
		e.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Tick Time Out - Is Next Day?");
	}	

	public void enterRemarks(String value) {
		WebElement e = txtRemarks();

		waitForElement(e);
		moveAndHighlightElement(e);
		e.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		e.sendKeys(value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Manual time logs Remarks");
	}
	
	public void selectRemarksType(String value) {
		WebElement e = ddRemarks();

		waitForElement(e);
		moveAndHighlightElement(e);
		selectByVisibleText(e, value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Manual time logs Remarks Type");
	}
	
	public void enterReason(String value) {
		WebElement e = txtReason();

		waitForElement(e);
		moveAndHighlightElement(e);
		e.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		e.sendKeys(value);
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter manual time logs reason");
	}
	
	public void clickSave() {
		waitForElement(btnSave);
		moveAndHighlightElement(btnSave);
		btnSave.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Save Time logs");
	}
	
	public void clickCancel() {
		waitForElement(btnCancel);
		moveAndHighlightElement(btnCancel);
		btnCancel.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Save Time logs");
	}
	
	/*******************************
	 * REUSABLES
	 * ***************************
	 **/
	public void enterTimeIn(String time, String location, String isNextday) {
		if (time == "" || location == "" || isNextday == "") {
			return;
		}
		
		if(chkboxTimeIn().getAttribute("checked") == null) {
			clickChkboxTimeIn();
		}

		TimeParser t = new TimeParser(time);
		enterTimeInHr(t.getHour());
		enterTimeInMin(t.getMinute());
		selectTimeInPeriod(t.getPeriod());
		selectTimeInLocation(location);
		if(isNextday == "1") {
			clickChkTimeInIsNextDay();
		}
	}
	
	public void enterTimeOut(String time, String location, String isNextday) {
		if (time == "" || location == "" || isNextday == "") {
			return;
		}
		
		if(chkboxTimeOut().getAttribute("checked") == null) {
			clickChkboxTimeOut();
		}

		TimeParser t = new TimeParser(time);
		enterTimeOutHr(t.getHour());
		enterTimeOutMin(t.getMinute());
		selectTimeOutPeriod(t.getPeriod());
		selectTimeOutLocation(location);
		
		if(isNextday == "1") {
			clickChkTimeInIsNextDay();
		}

	}
}
