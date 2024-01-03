package pages.whizible;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class LoginPage extends UserHelper {
    @FindBy(id="txtLogin")
    private static WebElement txtUsername;
    
    @FindBy(id="txtPassword")
    private static WebElement txtPassword;
    
    @FindBy(xpath="//*[@id='loginformboxformD']//*[text()='Sign In']")
    private static WebElement btnSignIn;
    
    @FindBy(xpath="//*[@id='lblLoginError']")
    private static WebElement lblErrMsgLogin;
    
    @FindBy(xpath="//*[@id='SpanEmailID']")
    private static WebElement lblerrMsgEmptyTxtUsername;
    
    @FindBy(xpath="//*[@id='SpanPassword']")
    private static WebElement lblerrMsgEmptyTxtPassword;
   
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
        waitForElement(txtUsername);
        waitForElement(txtPassword);
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
}
