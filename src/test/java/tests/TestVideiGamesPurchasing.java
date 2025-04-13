package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import actions.BrowserActions;
import pages.*;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestVideiGamesPurchasing {


    public final String browser="firefox";

    BrowserActions browserActions =new BrowserActions();

    @BeforeClass

    //A setup function to open the browser
    public void setup() {

        browserActions.initializingWebDriver(browser);

        browserActions.screenMaximizing();
    }

    //the whole tested scenario
    @Test
    public void videoGamesPurchasing() throws InterruptedException {

        //Variables for assertions
        int numberOfProductsAdded=0;
        int cartProuctsCount=0;
        double productsPricesSummation=0;
        double totalPrice=0;
        boolean useAsDefaultAddress=false;


        //Open https://www.amazon.eg/ and login
        HomePage homePageObj=new HomePage();
        homePageObj.navigateToHome();

        //change language to English
        homePageObj.changeLanguage(homePageObj.englishRadioButtonXpath);



        //login
        LoginPage loginPageObj=new LoginPage();
        loginPageObj.login();

        //Clear cart for new purchasing
        homePageObj.navigateToCart();
        CartPage cartPageObj=new CartPage();
        cartPageObj.clearCart();
        homePageObj.navigateToHome();




        //open “All” menu from the left side
        homePageObj.openAllMenu();

        //click on “video games” then choose “all video games”
        AllMenuPage allMenuPageObj=new AllMenuPage();
        allMenuPageObj.selectCategory(allMenuPageObj.videoGamesButtonXpath, allMenuPageObj.allVideoGamesButtonXpath);

        //from the filter menu on the left side add filter “free shipping” & add the filter of condition “ new”
        ProductsFiltersPage productsFiltersPageObj=new ProductsFiltersPage();

        productsFiltersPageObj.selectFilter(productsFiltersPageObj.freeShippingFilterXpath);
        Thread.sleep(1000);

        productsFiltersPageObj.selectFilter(productsFiltersPageObj.newFilterXpath);


        //ResultsPage resultsPageObj=new ResultsPage();
        ResultsPage resultsPageObj=new ResultsPage();
        resultsPageObj.sortBy(resultsPageObj.highToLowSortXpath);


        //add all products below that its cost below 15k EGP, if no product below 15k EGP move to next page
        numberOfProductsAdded=resultsPageObj.addProductsToCartAndGetProductsCount(resultsPageObj.productsAddToCartButtonsXpath);

        //make sure that all products is already added to carts
        homePageObj.navigateToCart();
        cartProuctsCount=cartPageObj.getCartProductsCount();

        //Assertions
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(numberOfProductsAdded);
        softAssert.assertNotNull(cartProuctsCount);
        softAssert.assertEquals(numberOfProductsAdded, cartProuctsCount, "Wrong Products added to Cart");

        //make sure that the total amount of all items is correct with the shipping fees if exist
        productsPricesSummation=cartPageObj.getSelectedProductsPricesSummation();
        totalPrice=cartPageObj.getTotalPrice();

        //Assertions
        softAssert.assertNotNull(productsPricesSummation);
        softAssert.assertNotNull(totalPrice);
        softAssert.assertEquals(productsPricesSummation, totalPrice, "Wrong Price Calculation");

        //Go to checkout page then to address page
        cartPageObj.navigateToSecureCheckOutPage();

        SecureCheckoutPage secureCheckoutPageObj=new SecureCheckoutPage();
        secureCheckoutPageObj.naviagteToAddAddressPage();

        //add address
        AddressPage addressPageObj=new AddressPage();
        addressPageObj.addNewAdsress(useAsDefaultAddress);



    }

    @AfterClass
    public void tearDown() {
        if (BrowserActions.driver != null) {
            browserActions.closeDriver();
        }
    }


}
