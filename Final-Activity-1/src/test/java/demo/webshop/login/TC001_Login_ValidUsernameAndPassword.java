package demo.webshop.login;

import base.BaseClass;
import pages.demoWebshop.GiftCardPage;
import pages.demoWebshop.LoginPage;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(utilities.TestNGListeners.class)
public class TC001_Login_ValidUsernameAndPassword extends BaseClass {
    @Test
    public void execute(){
        LoginPage loginPage = new LoginPage();


        loginPage.enterUsername("jdl1@test.com");
        loginPage.enterPassword("password1");
        loginPage.clickSignIn();
        loginPage.verifyUserIsLoggedIn();

    }
}
