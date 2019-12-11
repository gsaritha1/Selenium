package Spree;

import java.util.concurrent.TimeUnit;
import java.io.*;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;

public class Checkout {

   public static void main(String[] args) {
        // TODO Auto-generated method stub
       System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/" + "src/main/resources/chromedriver");
       String sURL="https://spree-vapasi-prod.herokuapp.com/";
       String text=null;
       String tName=null;
       String tPass=null;
       String sProduct =null;
       String sText =null;
       Boolean bflag = false;
       WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.navigate().to(sURL);

        driver.findElement(By.id("link-to-login")).click();

         text=driver.getTitle();
        System.out.println("the window title is " + text);
        text =driver.findElement(By.cssSelector("h3.card-title.mb-0.h6")).getText();
        if (text.equalsIgnoreCase("Login as Existing Customer")) {
            System.out.println("Existing user logins");
        }
        //login existing customer
         tName="gsar@rediffmail.com";
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
         tPass="Testing12";
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
        //Invalid email or password
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.equalsIgnoreCase("Logged in successfully")) {
            System.out.println(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText());
        }

        driver.findElement(By.cssSelector("a[href*='/t/bags']")).click();
        //navigate to bags screen
        text=driver.getTitle();
        if (text.contains("/t/bags")) {
            System.out.println(" The title of the page is " +text);
        }

        //Select a product
         sProduct="Ruby on Rails Bag";
        driver.findElement(By.xpath("//span[contains(text(),'Ruby on Rails Bag')]")).click();

        //navigate products detail screen
         sText = driver.findElement(By.xpath("//*[@id=\"product-description\"]/h1")).getText();
         bflag = sText.equalsIgnoreCase(sProduct);
        if (bflag==true) {
            System.out.println("Select product is " +sProduct);
        }
        String snum="2";
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys(snum);
        String sPrice=driver.findElement(By.xpath("//*[@id=\"product-price\"]/div/span[1]")).getText();
        driver.findElement(By.id("add-to-cart-button")).click();

        //shopping cart
        sText = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h1")).getText();
        bflag = sText.equalsIgnoreCase("Shopping Cart");
        if (bflag==true) {
            System.out.println("user is in " + sText +" page");
        }
        //verifying the product name
        sText = driver.findElement(By.cssSelector("a[href='/products/ruby-on-rails-bag']")).getText();
        bflag = sText.equalsIgnoreCase(sProduct);
        if (bflag==true) {
            System.out.println("user selected" + sProduct);

        }
        //verifying the price tag
        sText = driver.findElement(By.xpath("//*[@id=\"line_items\"]/tr/td[3]")).getText();
        bflag = sText.equalsIgnoreCase(sPrice);
        if (bflag==true) {
            System.out.println("Value of " + sProduct + "is " + sPrice);

        }

        //verifying the Qunatity
        sText = driver.findElement(By.xpath("//*[@id=\"line_items\"]/tr/td[4]")).getText();
        bflag = sText.equalsIgnoreCase(snum);
        if (bflag==true) {
            System.out.println("quantity Selected by user " + snum);

        }
        //verifying the value
        sText = driver.findElement(By.xpath("//*[@id=\"line_items\"]/tr/td[5]")).getText();
        sText=sText.substring(1);
        String sprice = sPrice.substring(1);
        float fnum = Float.parseFloat(sprice);
        float fValue = fnum*(Float.parseFloat(snum));

        if (fValue == Float.parseFloat(sText)) {
            System.out.println("Total amount to be paid is " + sText);

        } else {
            System.out.println("Total amount to be paid is and Calculated amount are different " + fValue);
        }

        driver.findElement(By.name("checkout")).click();

        //Checkout page
        text=driver.getTitle();
        if (text.contains("Checkout")) {
            System.out.println(" The title of the page is " +text);
        }

        sText = driver.findElement(By.cssSelector("a[href='/cart']")).getText();
        System.out.println("Value of the cart is " +sText);

        sText = driver.findElement(By.xpath("//*[@id=\"checkout_form_address\"]/div[1]/div/div/label")).getText();
        System.out.println("Email of customer is " +sText);
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        driver.findElement(By.cssSelector("a[href='#']")).click();
        driver.findElement(By.cssSelector("a[href='/logout']")).click();

        driver.close();
        driver.quit();
    }

}

