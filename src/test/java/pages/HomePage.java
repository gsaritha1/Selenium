package pages;

import org.openqa.selenium.support.PageFactory;
import suite.SuiteManager;
import util.DriverManager;

public class HomePage extends SuiteManager {

    public HomePage(){
        PageFactory.initElements(DriverManager.driver,this);
    }

}
