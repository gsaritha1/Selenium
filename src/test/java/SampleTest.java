
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SampleTest {
    public static void main(String[] args) throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "/Users/techops/Downloads/chromedriver");
        WebDriver driver=new ChromeDriver();
        driver.get("https://spree-vapasi-prod.herokuapp.com");
       // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        driver.findElement(By.id("link-to-login")).click();


        driver.findElement(By.name("spree_user[email]")).sendKeys("gsaryash@gmail.com");
        driver.findElement(By.id("spree_user_password")).sendKeys("Test1ng");

        driver.close();
        driver.quit();

    }

}
