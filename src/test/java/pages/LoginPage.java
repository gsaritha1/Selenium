package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import suite.SuiteManager;
import util.DriverManager;

public class LoginPage extends SuiteManager {

    public LoginPage() {
        PageFactory.initElements(DriverManager.driver,this);
    }

    @FindBy(name="spree_user[email]")
    private WebElement usernameField;

    @FindBy(name="spree_user[password]")
    private WebElement passwordField;

    @FindBy(name="commit")
    private WebElement submitBtn;

    public void enterValue(WebElement field, String value){
        field.click();
        field.clear();
        field.sendKeys(value);
    }

    public HomePage login(String username, String password){
        enterValue(usernameField,username);
        enterValue(passwordField,password);
        submitBtn.click();
        return new HomePage();
    }
}
