package tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import suite.SuiteManager;
import util.ConfigFileReader;
import util.DriverManager;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;


public class searchItem extends SuiteManager {
    //Reads
    ConfigFileReader config = new ConfigFileReader();
    String sSelCat;
    String sSearchItem;
    @BeforeTest
    public void seachProd(){
        sSelCat= config.getProperty("selectCat");
        System.out.println("select category is "+sSelCat);
        sSearchItem= config.getProperty("searchBrandItem");
        System.out.println("Search item is "+sSearchItem);
    }

    @Test
    public void verifySearchItem() throws InterruptedException {

        String chromeDriverPath = System.getProperty("user.dir") + "/" + "src/main/resources/drivers/chromedriver";
        String text;
        String selCat = null;
        if (sSelCat.equalsIgnoreCase("bags")) {
            selCat = "a[href*='/t/bags']";
            System.out.println(sSelCat);
        }

       DriverManager.driver.findElement(By.cssSelector(selCat)).click();
        //navigate to bags screen
        text=DriverManager.driver.getTitle();
        if (text.contains("/t/bags")) {
            System.out.println(" The title of the page is " +text);
        } else {
            System.out.println(" The title of the page is " +text);
        }
        DriverManager.driver.findElement(By.id("keywords")).clear();
        DriverManager.driver.findElement(By.id("keywords")).sendKeys(sSearchItem);
       // Thread.sleep(1000);
        DriverManager.driver.findElement(By.cssSelector("input[value='Search']")).click();
        //DriverManager
       // Thread.sleep(1000);
       // a[id^='id_prefix_']
        String sCss="div[id^='product_']";
        List<WebElement> prodList = DriverManager.driver.findElements(By.cssSelector(sCss));
       // List<WebElement> prodList = DriverManager.driver.findElements(By.id("products"));
        //List<WebElement> prodList = DriverManager.driver.findElements(By.cssSelector("div.car.w-100"));
               // class("card w-100"));
       Integer intSize=prodList.size();
        System.out.println("no of product list"+prodList.size());
        Thread.sleep(1000);
        if (intSize >1) {
            String sProd=prodList.get(1).getText();
            String sId = prodList.get(1).getAttribute("id");
            System.out.println("Product name is "+sProd);
            System.out.println("Attribute name is "+sId);
            DriverManager.driver.findElement(By.id(sId)).click();
            Thread.sleep(30000);

        }
              //  List<WebElement> links = driver.findElements(By.className("svg-bkgd01 xi8"));//return an empty list if elements not
    }
}
