package pages.sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class SamplePage extends UserHelper {
	
    @FindBy(id="Username")
    private static WebElement txtUsername;
	
    @FindBy(xpath="//*[@id='Password']")
    private static WebElement txtPassword;
    
    @FindBy(css="#CssSelector > tag .attribute")
    private static WebElement btnLogin;
    
	public SamplePage() {
		PageFactory.initElements(driver, this);
	}
	
    public void enterUsername(String value) {
        waitForElement(txtUsername);
        moveAndHighlightElement(txtUsername);
        txtUsername.sendKeys(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Entered the username");
    }	
    
    public void enterPassword(String value) {
    	waitForElement(txtPassword);
        moveAndHighlightElement(txtPassword);
        txtPassword.sendKeys(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Entered the password");
    }
    
    public void clickLogin() {
    	waitForElement(btnLogin);
        moveAndHighlightElement(btnLogin);
        btnLogin.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Clicked the Log In button");
    }
    
    // REUSABLE ACTION THAT INVOLVES THIS PAGE CLASS ONLY
    public void login(String username, String password) {
    	enterUsername(username);
    	enterPassword(password);
    	clickLogin();
    }
}
