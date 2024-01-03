package timetracker.manual.timelogs;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.timetracker.LoginPage;
import pages.timetracker.TimeLogsPage;
import pages.timetracker.timelogs.modals.EditTimeLogsModal;
import utilities.ExcelReader;

@Listeners(utilities.TestNGListeners.class)
public class MonthlyTimeLogsEntry extends BaseClass {

	@Test
	public void execute() {

		LoginPage loginPage = new LoginPage();
		TimeLogsPage timeLogsPage = new TimeLogsPage();
		EditTimeLogsModal editTimeLogsModal = new EditTimeLogsModal();

		ExcelReader credentials = new ExcelReader(System.getProperty("user.dir") + testDataLoc, "Login"); 
		ExcelReader timelogs = new ExcelReader(System.getProperty("user.dir") + testDataLoc, "Timelogs_202305");

		loginPage.login(
				credentials.testData("dean_login_credentials", "username"),
				credentials.testData("dean_login_credentials", "password") 
				); 

		String id = "";
		timeLogsPage.selectTimeSheetPeriod(timelogs.testData("1", "period"));
		
		for(int i = 1; i <= timelogs.getDataRowSize(); i++) {
			id = String.valueOf(i);
			
			timeLogsPage.clickLinkDate(timelogs.testData(id, "date"));
			
			editTimeLogsModal.setDate(timelogs.testData(id, "date"));
			editTimeLogsModal.enterTimeIn(
					timelogs.testData(id, "timeIn")
					, timelogs.testData(id, "locationIn")
					, timelogs.testData(id, "inIsNext")
					);

			editTimeLogsModal.enterTimeOut(
					timelogs.testData(id, "timeOut")
					, timelogs.testData(id, "locationOut")
					, timelogs.testData(id, "outIsNext")
					);

			editTimeLogsModal.selectRemarksType(timelogs.testData(id, "remarksType"));
			editTimeLogsModal.enterRemarks(timelogs.testData(id, "remarks"));
			editTimeLogsModal.enterReason(timelogs.testData(id, "reason"));
			editTimeLogsModal.clickSave();
		}
	}
}
