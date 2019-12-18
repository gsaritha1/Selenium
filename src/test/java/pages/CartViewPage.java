package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import suite.SuiteManager;
import util.DriverManager;

import java.util.ArrayList;
import java.util.List;


public class CartViewPage extends SuiteManager {

    public CartViewPage() {
        PageFactory.initElements(DriverManager.driver, this);
    }

    @FindBy(id = "content")
    private WebElement viewCart;

    @FindBy(css = "h1.product-title.mt-2")
    private WebElement prodName;

    @FindBy(id = "quantity")
    private WebElement itemQty;

    @FindBy(id = "add-to-cart-button")
    private WebElement addItem;

    @FindBy(xpath = "//*[@id=\"link-to-cart\"]/a")
    private WebElement cartIcon;

    @FindBy(xpath = "//*[@id=\"line_items\"]/tr")
    private static List<WebElement> cartTableRow;

    @FindBy(xpath = "//*[@id=\"line_items\"]/th")
    //*[@id="line_items"]/tr[1]/td[3]
    private static List<WebElement> cartTableCol;

    @FindBy(css="a.delete")
     private WebElement deleteItem;

    WebDriverWait wait = new WebDriverWait(DriverManager.driver, 20);
    //Get the product name to be added to cart
    public String addToCartItem() {
        String sText = null;
        sText = prodName.getText();
        System.out.println("Product name is" + sText);
        return sText;
    }
    //Add item to cart
    public void addToCart(String sItemCnt) {
        itemQty.clear();
        itemQty.sendKeys(sItemCnt);
        addItem.click();
    }
    //verify if navigated to cart page
    public void verifyCartViewPage() {
        boolean bflag;
        bflag = wait.until(ExpectedConditions.textToBePresentInElement(viewCart, "Shopping Cart"));// .visibilityOf(viewCart));
        Assert.assertTrue(bflag, "The Selected item got added to cart");
    }
    //verify if the item is added to cart from cart icon
    public String cartIconInfo() {
        String sText;
        sText = cartIcon.getText();
        return sText;
    }
    //get the complete information of the product in cart page like price , total count
    public String getTableVal() {
        String sText = null;
     //   int rowCnt = cartTableRow.size();
       // int colCnt = cartTableRow.get(0).findElements(By.xpath("//*[@id=\"line_items\"]/th")).size();
        //Ruby on Rails Baseball Jersey;$19.99
        sText = DriverManager.driver.findElement(By.xpath("//*[@id='line_items']/tr/td[2]/h4/a")).getText();
        //Ruby on Rails Baseball Jersey;$19.99
        sText = sText.trim() + ";" + DriverManager.driver.findElement(By.xpath("//*[@id=\"line_items\"]/tr/td[3]")).getText();
        //Ruby on Rails Baseball Jersey;$19.99;3
      //  sText = sText.trim() + ";" + DriverManager.driver.findElement(By.xpath("//*[@id=\"order_line_items_attributes_0_quantity\"]")).getText();
        //Ruby on Rails Baseball Jersey;$19.99;$59.97
        sText = sText.trim() + ";" + DriverManager.driver.findElement(By.xpath("//*[@id='line_items']/tr/td[5]")).getText();
        return sText;
    }
    //empty the cart
    public void emptyViewCart() {
        String sText =null;
        Boolean bflag =false;

        while (bflag==false) {
            deleteItem.click();
            sText = cartIconInfo();
            if (sText.contains("Empty")) {
                bflag = true;
                Assert.assertTrue(bflag,"Cart is Empty");
            }
        }
    }

}
