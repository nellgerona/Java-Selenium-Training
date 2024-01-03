package pages.demoWebshop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class LoginPage extends UserHelper {
    @FindBy(id="Email")
    private static WebElement txtUsername;

    @FindBy(id="Password")
    private static WebElement txtPassword;

    @FindBy(xpath="//*[@value=\"Log in\"]")
    private static WebElement btnSignIn;

    @FindBy(xpath="//*[text()='Log in']")
    private static WebElement btnSigninHomePage;

    @FindBy(xpath="//*[text()='Login was unsuccessful. Please correct the errors and try again.']")
    private static WebElement lblErrMsgLogin;

    @FindBy(xpath="//*[text()='No customer account found']")
    private static WebElement  lblerrMsgEmptyTxtPassword;

    @FindBy(xpath="//*[text()='The credentials provided are incorrect']")
    private static WebElement  lblerrMsgEmptyTxtUsername;

    @FindBy(xpath="//*[text()='Log out']")
    private static WebElement  btnLogout;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void vfyMsgErrLogin(){
        waitForElement(lblErrMsgLogin);
        moveAndHighlightElement(lblErrMsgLogin);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that the invalid login error message is displayed");
    }

    public void vfyMsgErrEmptyTxtUsername(){
        waitForElement(lblerrMsgEmptyTxtUsername);
        moveAndHighlightElement(lblerrMsgEmptyTxtUsername);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that the empty username field message is displayed");
    }

    public void vfyMsgErrEmptyTxtPassword(){
        waitForElement(lblerrMsgEmptyTxtPassword);
        moveAndHighlightElement(lblerrMsgEmptyTxtPassword);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that the empty password field message is displayed");
    }

    public void verifyUserIsLoggedOut() {
        waitForElement(btnSigninHomePage);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that the User is logged out");
    }

    public void enterUsername(String value) {
        waitForElement(txtUsername);
        moveAndHighlightElement(txtUsername);
        txtUsername.sendKeys(value);
        System.out.println(value);

        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Entered the username");
    }

    public void enterPassword(String value) {
        waitForElement(txtPassword);
        moveAndHighlightElement(txtPassword);
        txtPassword.sendKeys(value);
        System.out.println(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Entered the password");
    }

    public void clickSignIn() {
        waitForElement(btnSignIn);
        moveAndHighlightElement(btnSignIn);
        btnSignIn.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Clicked the Sign In button");
    }

    public void clickLogout() {
        waitForElement(btnLogout);
        moveAndHighlightElement(btnLogout);
        btnLogout.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Clicked the Logout button");
    }

    public void verifyUserIsLoggedIn() {
        waitForElement(btnLogout);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that the User is logged In");
    }
}
