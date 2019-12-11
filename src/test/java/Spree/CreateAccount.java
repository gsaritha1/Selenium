package Spree;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;

public class CreateAccount {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/" + "src/main/resources/chromedriver");
        String sURL="https://spree-vapasi-prod.herokuapp.com/";
        String text=null;
        String tName=null;
        String tPass=null;
        Boolean bflag = false;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.navigate().to(sURL);

        driver.findElement(By.id("link-to-login")).click();

         text=driver.getTitle();
        System.out.println("the window title is " + text);
        driver.findElement(By.cssSelector("a[href*='signup']")).click();
         tName=driver.findElement(By.cssSelector("h3.card-title.mb-0.h6")).getText();
        System.out.println("Creating an account for  " +tName);

        // invalid email
        tName="gsar41@rediffmailcom";
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
         tPass="Testing12";
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.id("spree_user_password_confirmation")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
		/*1 error prohibited this record from being saved:
		There were problems with the following fields:
		Email is invalid)*/
        text =driver.findElement(By.id("errorExplanation")).getText();
        if (text.contains("Email is invalid")) {
            System.out.println(driver.findElement(By.id("errorExplanation")).getText());
        }

        // Password too short
        tName="gsar41@rediffmail.com";
        driver.findElement(By.name("spree_user[email]")).clear();
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
        tPass="Test";//"Testing12";
        driver.findElement(By.name("spree_user[password]")).clear();
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.id("spree_user_password_confirmation")).clear();
        driver.findElement(By.id("spree_user_password_confirmation")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
		/*1 error prohibited this record from being saved:
		There were problems with the following fields:
		Password is too short (minimum is 6 characters)*/
        text=driver.findElement(By.id("errorExplanation")).getText();
        if (text.contains("Password is too short")) {
            System.out.println(driver.findElement(By.id("errorExplanation")).getText());
        }

        //Successful login
        tName="gsar41@rediffmail.com";
        driver.findElement(By.name("spree_user[email]")).clear();
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
        tPass="Testing12";
        driver.findElement(By.name("spree_user[password]")).clear();
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.id("spree_user_password_confirmation")).clear();
        driver.findElement(By.id("spree_user_password_confirmation")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
        //Welcome! You have signed up successfully
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.equalsIgnoreCase("Welcome! You have signed up successfully")) {
            System.out.println(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText());
        }


         bflag =driver.findElement(By.cssSelector("a[href*='/account']")).isDisplayed();
        if (bflag) {
            System.out.println("User logged into their account");
        } else {
            System.out.println("user logged into some other screen");
        }

        driver.findElement(By.cssSelector("a[href*='/account']")).click();
        text=driver.getTitle();
        if (text.contains("account")) {
            System.out.println(" The title of the page is " +text);
        }

        driver.findElement(By.cssSelector("a[href*='/logout']")).click();
        text=driver.getTitle();
        if (text.equalsIgnoreCase("https://spree-vapasi-prod.herokuapp.com/")) {
            System.out.println("User is in home page");
        }
        driver.close();
        driver.quit();

    }

}

