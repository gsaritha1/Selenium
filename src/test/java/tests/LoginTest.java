package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import suite.SuiteManager;
import util.ConfigFileReader;
import util.DriverManager;

public class LoginTest extends SuiteManager {
    //Reads
    /*ConfigFileReader config = new ConfigFileReader();
    String sName;
    String sPass;
    @BeforeTest
    public void loginCredentials(){
         sName = config.getProperty("user_name");
         sPass = config.getProperty("user_password");
    }*/


    @Test (dataProvider = "loginCredentials", dataProviderClass=testdata.loginCredentials.class)
     public void verifyLoginLogout(String sName,String sPass){
    // TODO Auto-generated method stub
        String chromeDriverPath = System.getProperty("user.dir") + "/" + "src/main/resources/drivers/chromedriver";
     //   String tName="gsar@rediffmail.com";
       // String tPass="Testing12";


        WebElement loginLink = DriverManager.driver.findElement(By.id("link-to-login"));
        loginLink.click();
        DriverManager.driver.findElement(By.id("link-to-login")).click();

        String text=DriverManager.driver.getTitle();
        System.out.println("the window title is " + text);
        text =DriverManager.driver.findElement(By.cssSelector("h3.card-title.mb-0.h6")).getText();
        if (text.equalsIgnoreCase("Login as Existing Customer")) {
            System.out.println("Existing user logins");
            //Successful login

            DriverManager.driver.findElement(By.name("spree_user[email]")).clear();
            DriverManager.driver.findElement(By.name("spree_user[email]")).sendKeys(sName);

            DriverManager.driver.findElement(By.name("spree_user[password]")).clear();
            DriverManager.driver.findElement(By.name("spree_user[password]")).sendKeys(sPass);
            DriverManager.driver.findElement(By.name("commit")).click();
            //Logged in successfully
            text = DriverManager.driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
            if (text.equalsIgnoreCase("Logged in successfully")) {
                System.out.println(DriverManager.driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText());
            }


            Boolean bflag = DriverManager.driver.findElement(By.cssSelector("a[href*='/account']")).isDisplayed();
            if (bflag) {
                System.out.println("User logged into their account");
            } else {
                System.out.println("user logged into some other screen");
            }

            DriverManager.driver.findElement(By.cssSelector("a[href*='/account']")).click();
            text = DriverManager.driver.getTitle();
            if (text.contains("account")) {
                System.out.println(" The title of the page is " + text);
            }

            DriverManager.driver.findElement(By.cssSelector("a[href*='/logout']")).click();
            text = DriverManager.driver.getTitle();
            if (text.equalsIgnoreCase("https://spree-vapasi-prod.herokuapp.com/")) {
                System.out.println("User is in home page");
            }
        }else {
            System.out.println("New Customer ! Needs to rigister");
        }
        }
    }

