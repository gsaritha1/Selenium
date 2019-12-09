import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;

public class LoginPage {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "/Users/techops/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.navigate().to("https://spree-vapasi-prod.herokuapp.com/");

        driver.findElement(By.id("link-to-login")).click();

        String text=driver.getTitle();
        System.out.println("the window title is " + text);
        text =driver.findElement(By.cssSelector("h3.card-title.mb-0.h6")).getText();
        if (text.equalsIgnoreCase("Login as Existing Customer")) {
            System.out.println("Existing user logins");
        }
        // invalid email
        String tName="gsar4@rediffmailcom";
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
        String tPass="Testing12";
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
        //Invalid email or password
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.contains("Invalid email")) {
            System.out.println(text);
        }

        // wrong Password
        tName="gsar4@rediffmail.com";
        driver.findElement(By.name("spree_user[email]")).clear();
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
        tPass="Testing1";
        driver.findElement(By.name("spree_user[password]")).clear();
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
        //Invalid email or password.
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.contains("Invalid")) {
            System.out.println(text);
        }

        // No username and password
        driver.findElement(By.name("spree_user[email]")).clear();
        driver.findElement(By.name("spree_user[password]")).clear();
        driver.findElement(By.name("commit")).click();
        //Invalid email or password.
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.contains("Invalid")) {
            System.out.println(text);
        }

        //Successful login
        tName="gsar4@rediffmail.com";
        driver.findElement(By.name("spree_user[email]")).clear();
        driver.findElement(By.name("spree_user[email]")).sendKeys(tName);
        tPass="Testing12";
        driver.findElement(By.name("spree_user[password]")).clear();
        driver.findElement(By.name("spree_user[password]")).sendKeys(tPass);
        driver.findElement(By.name("commit")).click();
        //Logged in successfully
        text =driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
        if (text.equalsIgnoreCase("Logged in successfully")) {
            System.out.println(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText());
        }


        Boolean bflag =driver.findElement(By.cssSelector("a[href*='/account']")).isDisplayed();
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

