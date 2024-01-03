package pages.timetracker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class TimeLogsPage extends UserHelper {

	@FindBy(css = "a.jqTransformSelectOpen")
	private static WebElement ddTimeSheetPeriod;

	public TimeLogsPage() {
		PageFactory.initElements(driver, this);
	}
	public void selectTimeSheetPeriod(String value) {
		waitForElement(ddTimeSheetPeriod);
		moveAndHighlightElement(ddTimeSheetPeriod);
		ddTimeSheetPeriod.click();

		WebElement timesheetValue = driver.findElement(By.xpath(String.format("//a[text()='%s']", value)));
		waitForElement(timesheetValue);
		moveAndHighlightElement(timesheetValue);
		timesheetValue.click();

		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Timesheet Period: " + value);
	}

	public void clickLinkDate(String date) {
		WebElement e = driver.findElement(By.xpath(String.format("//a[text()='%s']", date)));

		waitForElement(e);
		moveAndHighlightElement(e);
		e.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Click " + date + " link to edit");
	}

}
