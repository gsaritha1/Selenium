package util;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver driver;

    public DriverManager() {
        String chromeDriverPath = System.getProperty("user.dir")+"/" + "src/main/resources/drivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
}


