package sample.module;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.sample.SamplePage;
import utilities.ExcelReader;

@Listeners(utilities.TestNGListeners.class)
public class SampleTestCase extends BaseClass {

	@Test
	public void execute() {

		SamplePage samplePage = new SamplePage();

		ExcelReader credentials = new ExcelReader(System.getProperty("user.dir") + testDataLoc, "Login"); 
		
		samplePage.login(
				credentials.testData("login_credentials", "username"),
				credentials.testData("login_credentials", "password") 
				); 
		samplePage.getWindowHandles();
		samplePage.clickLogin();
	}
}
