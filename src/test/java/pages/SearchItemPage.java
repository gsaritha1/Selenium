package pages;

import org.openqa.selenium.By;
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

    //public BasePage(WebDriver driver) {
    public SearchItemPage(){
        PageFactory.initElements(DriverManager.driver,this);
    }

    @FindBy(css = "a[href*='/t/bags']")
    private WebElement sSelCatBag;

    @FindBy(id="keywords")
    private WebElement sSearchItem;

    @FindBy(css ="input[value='Search']")
    private WebElement searchBtn;

    @FindBy(tagName ="title" )
    private  WebElement stitle;
    @FindBy(css ="div[id^='product_']")
   private static WebElement sProducts;

   // private WebElement getTitle;
    //DriverManager.driver.findElement(By.cssSelector("input[value='Search']")).click();
   WebDriverWait wait = new WebDriverWait(DriverManager.driver,10);

    public void enterSearchItem(String value){
        System.out.println("item to search is "+value);
        sSearchItem.click();
        sSearchItem.clear();
        sSearchItem.sendKeys(value);
        System.out.println("searching value");
    }

    public void searchBtn(String value){
        searchBtn.click();
        stitle= wait.until(ExpectedConditions.visibilityOf(stitle));
        String stext=stitle.getText();
        if  (stext.contains(value)) {
            System.out.println("Window title is as per search item  " + stext);
        } else {
            System.out.println("Window title is not as per search item  " + stext);
        }
    }

    public static ArrayList<String> productsList(String keyword) {
        List <WebElement> prodList = (List<WebElement>) sProducts;
        Integer intSize=prodList.size();
        boolean bflag =false;
        ArrayList<String> sProdName = new ArrayList<String>();
        if (intSize >0) {
            System.out.println("no of product list"+prodList.size());

            for (int inum=0; inum<intSize;inum++) {
                String sProd = prodList.get(1).getText();
                System.out.println("Product name is " + sProd);
                if (sProd.contains(keyword)) {
                    sProdName.add(sProd);
                    bflag = true;
                } else{
                    bflag = false;
                }
            }
        } else {
                System.out.println("No products searched for "+ keyword);
            }
        return sProdName;
    }

}
