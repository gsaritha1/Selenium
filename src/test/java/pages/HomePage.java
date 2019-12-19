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

import static org.testng.Assert.assertNotEquals;

public class HomePage extends SuiteManager {

   // public  String sProdID=null;
    @FindBy(xpath ="//div[contains(@class,'alert')]" )
    private WebElement loginAlert;

    @FindBy(css ="div[id^='product_']")
    private static List<WebElement> sProducts;


    @FindBy(xpath ="//*[@id='product-description']/h1")
   private WebElement sProdname;

    WebDriverWait wait = new WebDriverWait(DriverManager.driver,10);

    public HomePage(){
        PageFactory.initElements(DriverManager.driver,this);
    }

    public void verifyHomepage() {

        loginAlert = wait.until(ExpectedConditions.visibilityOf(loginAlert));
        String text = loginAlert.getText();
        if (text.equalsIgnoreCase("Logged in successfully")) {
            System.out.println("user logged in successfully and navigated to Home page");
            Assert.assertEquals(text,"Logged in successfully");
        } else {
            System.out.println("The message is "+ text);
            assertNotEquals(text,"Logged in successfully");

        }
    }

    public static String selProduct(int iItem, String sAttr) {
        int intSize=sProducts.size();
       String sProdID=null;
        if (intSize >0) {
            System.out.println("no of product list" + intSize);
             sProdID = sProducts.get(iItem).getAttribute(sAttr);
        }
        return sProdID;
    }

    public static String clickSelProduct(String value,String attribute) {
        String sText = null;
        if (attribute.equalsIgnoreCase("id")) {
            sText= DriverManager.driver.findElement(By.id(value)).getText();
            DriverManager.driver.findElement(By.id(value)).click();
            System.out.println("Selected product is " +sText);
        }
        return sText;
    }
}
