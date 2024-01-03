package reusables.whizible;

import pages.whizible.HomePage;
import pages.whizible.LoginPage;
import utilities.UserHelper;

public class LoginModule extends UserHelper{
	private static LoginPage loginPage;
	private static HomePage homePage;

	public LoginModule() {
		loginPage = new LoginPage();
		homePage = new HomePage();

	}
	private void validCredentials() {
		homePage.waitToLoadPage();
		homePage.vfySuccessLogin();
		homePage.clickSidebarMenu("Timesheet");
		homePage.clickSidebarMenu("Timesheet Entry");
		homePage.clickLogoutButton();
	}
	
	private void invalidCredentials() {
		loginPage.vfyMsgErrLogin();
	} 
	
	private void nullCredentials() {
		loginPage.vfyMsgErrEmptyTxtUsername();
		loginPage.vfyMsgErrEmptyTxtPassword();
	}
	
	public void vfyLogin(String scenario) {
		
		switch (scenario) {
			case "ALL_VALID":
				validCredentials();
				break;
			case "INVALID_USERNAME":
			case "INVALID_PASSWORD":
			case "ALL_INVALID":
				invalidCredentials();
				break;
			case "NULL_CREDENTIALS":
				nullCredentials();
				break;
			default:
				// do nothing
				break;
		}
	}
	
	public void login(String username, String password){
		loginPage.waitToLoadPage();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
	}
}
