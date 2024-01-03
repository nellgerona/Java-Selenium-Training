package demo.webshop.login;

import base.BaseClass;
import pages.demoWebshop.GiftCardPage;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.demoWebshop.LoginPage;

@Listeners(utilities.TestNGListeners.class)
public class TC003_RemoveGiftCard extends BaseClass {
    @Test
    public void execute(){
        GiftCardPage giftCardPage = new GiftCardPage();

        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("jdl1@test.com");
        loginPage.enterPassword("password1");
        loginPage.clickSignIn();
        loginPage.verifyUserIsLoggedIn();

        giftCardPage.navigateToShoppingCart();
        giftCardPage.clickItemCheckbox();
        giftCardPage.clickUpdateShoppingCartBtn();
        giftCardPage.vfyEmptyCart();
    }
}