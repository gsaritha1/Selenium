package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import suite.SuiteManager;
import util.ConfigFileReader;

import java.util.ArrayList;

public class AddCart extends SuiteManager {
    //Reads
    ConfigFileReader config = new ConfigFileReader();
    public int iItemID;
    public String sAttr;
    public String sItemNo;
    @BeforeTest
    public void seachProd(){
        iItemID =Integer.parseInt(config.getProperty("itemID"));
        sAttr= config.getProperty("Attribute");
        sItemNo= config.getProperty("itemNo");
        System.out.println(iItemID);
        System.out.println(sAttr);
    }

    public BasePage basePage;
    public LoginPage loginPage;
    public HomePage homePage;
    public CartViewPage cartviewpage;

    @Test (dataProvider = "loginCredentials", dataProviderClass=testdata.loginCredentials.class)
    public void verifyCart(String sName,String sPass) throws InterruptedException {

        String chromeDriverPath = System.getProperty("user.dir") + "/" + "src/main/resources/drivers/chromedriver";
        String sAttrVal=null;
        String sProd=null;
        String sProdName=null;
        String sPrice =null;
        String sText=null;
        String sTemp=null;
        int intindex=0;
        float fTotal=0;
        //open the web site , login and navigate to home page
        basePage = new BasePage();
        cartviewpage = new CartViewPage();
        loginPage=basePage.clickLoginButton();
        homePage=loginPage.login(sName,sPass);
        //get the info of the item to be selected
        sAttrVal=homePage.selProduct(iItemID,sAttr);
        //add the item to cart
        System.out.println("attribute value is "+sAttrVal);
        sProd=homePage.clickSelProduct(sAttrVal,sAttr);
        sProdName=cartviewpage.addToCartItem();//Ruby on Rails Baseball Jersey;$19.99
        intindex = sProd.lastIndexOf("$");
        sPrice=sProd.substring(intindex+1).trim();//$19.99
        sProd=sProd.substring(0,(intindex-1)).trim();//Ruby on Rails Baseball Jersey
        Assert.assertEquals(sProdName, sProd);
        cartviewpage.addToCart(sItemNo);
        cartviewpage.verifyCartViewPage();
        sText=cartviewpage.cartIconInfo(); //Cart: (13) $262.87
        sTemp=sText.substring(sText.indexOf("(")+1);//13) $262.87
        sTemp=sTemp.substring(0,sTemp.indexOf(")"));//13
        Assert.assertEquals(sItemNo, sTemp);
        sText=sText.substring(sText.indexOf("$")+1);//262.87
        int inum2 = Integer.parseInt(sItemNo);
        //System.out.println(sItemNo);
        float fnum = Float.parseFloat(sPrice);
        fTotal = fnum*inum2;//3*19.99
        Assert.assertEquals(fTotal,Float.parseFloat(sText));
        sText =cartviewpage.getTableVal();//Ruby on Rails Baseball Jersey;$19.99;$59.97
        sTemp=sProd+";$"+sPrice+";$"+String.valueOf(fTotal); //Ruby on Rails Baseball Jersey;$19.99;$59.97
        Assert.assertEquals(sText,sTemp);
        cartviewpage.emptyViewCart();


    }
}
