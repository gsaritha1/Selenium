package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suite.SuiteManager;
import util.DriverManager;


public class BasePage extends SuiteManager {

    //public BasePage(WebDriver driver) {
    public BasePage(){
        PageFactory.initElements(DriverManager.driver,this);
    }
    @FindBy(id ="link-to-login")
    private WebElement loginButton;

    WebDriverWait wait = new WebDriverWait(DriverManager.driver,10);

    public LoginPage clickLoginButton() {

        //guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i")));
        loginButton= wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return new LoginPage();
    }
}
