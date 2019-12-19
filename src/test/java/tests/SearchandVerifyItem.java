package tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchItemPage;
import suite.SuiteManager;
import util.ConfigFileReader;

import java.util.ArrayList;

public class SearchandVerifyItem extends SuiteManager {
    //Reads
    ConfigFileReader config = new ConfigFileReader();
    public String sItem;
    public String sTitle;
    @BeforeTest
    public void seachProd(){
        sItem= config.getProperty("searchItems");
        sTitle= config.getProperty("searchTitle");
        System.out.println(sItem);
        System.out.println(sTitle);
    }

    public BasePage basePage;
    public LoginPage loginPage;
    public HomePage homePage;
    public SearchItemPage searchItemPage;

    @Test (dataProvider = "loginCredentials", dataProviderClass=testdata.loginCredentials.class)
    public void verifySearchItem(String sName,String sPass) throws InterruptedException {

        String chromeDriverPath = System.getProperty("user.dir") + "/" + "src/main/resources/drivers/chromedriver";
        ArrayList<String> prodList = new ArrayList<String>( );
        basePage = new BasePage();
        searchItemPage = new SearchItemPage();

        loginPage=basePage.clickLoginButton();
        homePage = loginPage.login(sName,sPass);

        searchItemPage.enterSearchItem(sItem);
        searchItemPage.searchBtn();
        searchItemPage.getSearchURL(sTitle);
        prodList = searchItemPage.productsList(sItem);
        for (String stext: prodList)
        {
            System.out.println("Products searched are "+ stext);
        }
    }
}
