package pages.whizible;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class HomePage extends UserHelper {
    
    @FindBy(id="EmployeeName")
    private static WebElement lblEmployeeName;
    
    @FindBy(xpath="//li[@class='dropdown user user-menu']/a")
    private static WebElement ddLogout;
    
    @FindBy(css=".btn#logout")
    private static WebElement btnLogout;

    @FindBy(xpath="//button[@id='btnLogout']")
    private static WebElement modalBtnLogout;
    
    public HomePage() {
    	PageFactory.initElements(driver, this);
    }
    
    public void clickSidebarMenu(String lblMenu) {
    	By locator = By.xpath("//span[text()='" + lblMenu + "']/parent::a");
    	WebElement e = driver.findElement(locator);
    	waitForClickable(e);
    	moveAndHighlightElement(e);
    	e.click();
    	
    	reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), 
    			"Clicked the " + lblMenu);
    }

    public void vfySuccessLogin() {
    	String employeeName;
        waitForElement(lblEmployeeName);
        moveAndHighlightElement(lblEmployeeName);
        
        employeeName = lblEmployeeName.getText();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), 
        		"Successfully logged in using " + employeeName + " credentials");
    }
    
	public void clickLogoutButton() {
		switchToDefaultContent();

		waitForElement(ddLogout);
        moveAndHighlightElement(ddLogout);
        ddLogout.click();
        
        waitForElement(btnLogout);
        moveAndHighlightElement(btnLogout);
        btnLogout.click();
		
		waitForElement(modalBtnLogout);
        moveAndHighlightElement(modalBtnLogout);
        modalBtnLogout.click();
		reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), 
				"Clicked the logout button");
	}
	

}
