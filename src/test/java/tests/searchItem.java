package tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import suite.SuiteManager;
import util.ConfigFileReader;
import util.DriverManager;

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
        sSearchItem= config.getProperty("searchItem");
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
     //  DriverManager.driver.findElement(By.cssSelector(selCat)).click();
       DriverManager.driver.findElement(By.cssSelector(selCat)).click();
        //navigate to bags screen
        text=DriverManager.driver.getTitle();
      // if (text.contains("/t/"+sSelCat)) {
        if (text.contains("/t/bags")) {
            System.out.println(" The title of the page is " +text);
        } else {
            System.out.println(" The title of the page is " +text);
        }
        DriverManager.driver.findElement(By.id("keywords")).clear();
        DriverManager.driver.findElement(By.id("keywords")).sendKeys(sSearchItem);
        Thread.sleep(1000);
        DriverManager.driver.findElement(By.cssSelector("input[value='Search']")).click();

        Thread.sleep(1000);

    }
}
