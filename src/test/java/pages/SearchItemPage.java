package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suite.SuiteManager;
import util.DriverManager;

import java.util.ArrayList;
import java.util.List;


public class SearchItemPage extends SuiteManager {

   public SearchItemPage(){ PageFactory.initElements(DriverManager.driver,this); }

    @FindBy(css = "a[href*='/t/bags']")
    private WebElement sSelCatBag;

    @FindBy(id="keywords")
    private WebElement sSearchItem;

    @FindBy(css ="input[value='Search']")
    private WebElement searchBtn;

    @FindBy(css ="div[id^='product_']")
   private static List<WebElement> sProducts;

   WebDriverWait wait = new WebDriverWait(DriverManager.driver,10);

    public void enterSearchItem(String value){
        System.out.println("item to search is "+value);
        sSearchItem.click();
        sSearchItem.clear();
        sSearchItem.sendKeys(value);
        System.out.println("searching value");
    }

    public void searchBtn(){
        searchBtn.click();
    }

    public void getSearchURL(String value){
       String stext= DriverManager.driver.getCurrentUrl();
        if  (stext.contains(value)) {
            System.out.println("Window title is as per search item  " + stext);
        } else {
            System.out.println("Window title is not as per search item  " + stext);
        }
    }

    public static ArrayList<String> productsList(String sItem) {
        int intSize=sProducts.size();
        ArrayList<String> sProdName = new ArrayList<String>();
        if (intSize >0) {
            System.out.println("no of product list"+intSize);
            for (int inum=0; inum<intSize;inum++) {
                String sProd = sProducts.get(inum).getText();
                if (sProd.contains(sItem)) {
                    sProdName.add(sProd);
                } else{
                    System.out.println("Product name in the search list" + sProd+" not part of search item");
                }
            }
        } else {
                System.out.println("No products searched for "+ sItem);
            }
        return sProdName;
    }

}
