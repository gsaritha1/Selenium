package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suite.SuiteManager;
import util.DriverManager;

public class HomePage extends SuiteManager {

    @FindBy(xpath ="//div[contains(@class,'alert')]" )
    private WebElement loginAlert;

    WebDriverWait wait = new WebDriverWait(DriverManager.driver,10);

    public HomePage(){
        PageFactory.initElements(DriverManager.driver,this);
    }

    public void verifyHomepage() {

        loginAlert = wait.until(ExpectedConditions.visibilityOf(loginAlert));
        String text = loginAlert.getText();
        if (text.equalsIgnoreCase("Logged in successfully")) {
            System.out.println("user logged in successfully and navigated to Home page");
        } else {
            System.out.println("The message is "+ text);

        }
    }

}
