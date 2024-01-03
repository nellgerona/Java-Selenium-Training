package pages.demoWebshop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;
public class GiftCardPage extends UserHelper {


    @FindBy(xpath="//*[@class=\"top-menu\"]//a[contains(text(),\"Gift Cards\")]")
    private static WebElement btnGiftCardMenu;
    //Use for navigation to GiftCard Page

    @FindBy(xpath="//select[@id=\"products-orderby\"]")
    private static WebElement liSortbyHightoLow;
    //Use for selecting High to Low when sorting

    @FindBy(xpath="//select[@id=\"products-viewmode\"]")
    private static WebElement selectView;
    //Use for selecting view

    @FindBy(xpath="//*[@class=\"details\"]//*[@class=\"description\"][contains(text(),\"$100\")]/following-sibling::div//*[@class=\"buttons\"]//input[@type=\"button\"]")
    private static WebElement selectGiftCard;
    //Use for selecting $100 Giftcard

    @FindBy(xpath="//*[@class=\"giftcard\"]//label[contains(text(),\"Recipient's Name:\")]/following-sibling::input")
    private static WebElement textRecipientName;
    //Use for Typing Recipients's Name

    @FindBy(xpath="//label[contains(text(),\"Message:\")]//following-sibling::textarea")
    private static WebElement textMessage;
    //Use for Typing Message

    @FindBy(xpath="//label[contains(text(),\"Qty:\")]//following-sibling::input[@type=\"text\"]")
    private static WebElement textQuantity;
    //Use for Quantity Input

    @FindBy(xpath="//label[contains(text(),\"Qty:\")]//following-sibling::input[@value=\"Add to cart\"]\n")
    private static WebElement btnAddItemToCart;
    //Use for Adding Item to Cart


    @FindBy(xpath="//*[@id=\"topcartlink\"]//*[text()='Shopping cart']")
    private static WebElement btnShoppingCart;
    //Use for navigating to Shopping Cart


    @FindBy(xpath="//*[@class=\"cart-item-row\"]//*[@class=\"product-name\"][contains(text(),\"$100\")]")
    private static WebElement cartGiftCard;
    //Use for validating that gift card is added to shopping cart


    @FindBy(xpath="//*[@class=\"cart-item-row\"]//input[@type=\"checkbox\"]")
    private static WebElement btnItemCheckbox;
    //Use for selecting item for deletion

    @FindBy(xpath="//*[@class=\"common-buttons\"]//input[@value=\"Update shopping cart\"]")
    private static WebElement btnUpdateShoppingCart;
    //Use for Updating Cart

    @FindBy(xpath="//*[@class=\"order-summary-content\"][contains(text(),\"empty\")]")
    private static WebElement msgEmptyCart;
    //Use for validating cart is empty

    @FindBy(xpath="//*[@id=\"bar-notification\"]/span[@title=\"Close\"]")
    private static WebElement closeProductAddedtoCart;
    //Use for closing notification that item is added to cart



    public GiftCardPage() {
        PageFactory.initElements(driver, this);
    }




    public void navigateToGiftCards() {
        waitForElement(btnGiftCardMenu);
        moveAndHighlightElement(btnGiftCardMenu);
        btnGiftCardMenu.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Clicked Gift Card in the Menu");
    }

    public void sortPrice() {
        waitForElement(liSortbyHightoLow);
        moveAndHighlightElement(liSortbyHightoLow);
        liSortbyHightoLow.click();
        selectByVisibleText(liSortbyHightoLow,"Price: High to Low");
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Sort Gift Card Prices");
    }

    public void selectGridView() {
        waitForElement(selectView);
        moveAndHighlightElement(selectView);
        selectView.click();
        selectByVisibleText(selectView,"List");
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Grid View");
    }

    public void selectGiftCard() {
        waitForElement(selectGiftCard);
        moveAndHighlightElement(selectGiftCard);
        selectGiftCard.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select GiftCard");
    }

    public void enterRecipientName(String value) {
        waitForElement(textRecipientName);
        moveAndHighlightElement(textRecipientName);
        textRecipientName.sendKeys(value);
        System.out.println(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Recipient Name");
    }

    public void enterMessage(String value) {
        waitForElement(textMessage);
        moveAndHighlightElement(textMessage);
        textMessage.sendKeys(value);
        System.out.println(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Message");
    }

    public void enterQuantity(String value) {
        waitForElement(textQuantity);
        moveAndHighlightElement(textQuantity);
        textQuantity.clear();
        textQuantity.sendKeys(value);
        System.out.println(value);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Enter Quantity");
    }

    public void clickAddItemtoCart() {
        waitForElement(btnAddItemToCart);
        moveAndHighlightElement(btnAddItemToCart);
        btnAddItemToCart.click();
        closeProductAddedtoCart.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Add Item to Card");
    }

    public void navigateToShoppingCart() {
        waitToLoadPage();
        waitForElement(btnShoppingCart);
        moveAndHighlightElement(btnShoppingCart);
        btnShoppingCart.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Navigate to Shopping Cart");
    }

    public void vfyItemIsAddedToCart() {
        waitForElement(cartGiftCard);
        moveAndHighlightElement(cartGiftCard);
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Successfully verified that Gift Card is added to shopping cart");
    }

    public void clickItemCheckbox() {
        waitForElement(btnItemCheckbox);
        moveAndHighlightElement(btnItemCheckbox);
        btnItemCheckbox.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Item Checkbox");
    }

    public void clickUpdateShoppingCartBtn() {
        waitForElement(btnUpdateShoppingCart);
        moveAndHighlightElement(btnUpdateShoppingCart);
        btnUpdateShoppingCart.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Select Item Checkbox");
    }

    public void vfyEmptyCart() {
        waitForElement(msgEmptyCart);
        moveAndHighlightElement(msgEmptyCart);
        msgEmptyCart.click();
        reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Shopping Cart is Empty");
    }
}
