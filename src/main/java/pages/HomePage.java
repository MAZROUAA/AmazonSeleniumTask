package pages;

import actions.WebUIActions;

public class HomePage {

    //Selectors
    String homePageURL = "https://www.amazon.eg/";
    String loginTabID="nav-link-accountList-nav-line-1";

    String languageMenuXpath="//span[@class='icp-nav-flag icp-nav-flag-eg icp-nav-flag-lop'] ";

    public String englishRadioButtonXpath="//div[@class='a-radio a-radio-fancy']//input[@value='en_AE']";

    String saveChangesButtonXpath="//input[@aria-labelledby='icp-save-button-announce']";
    String allMenuID="nav-hamburger-menu";
    String cartID="nav-cart-count";




    WebUIActions uiActions = new WebUIActions();

    public void navigateToHome() {
        uiActions.navigateToPage(homePageURL, loginTabID, WebUIActions.Locators.id);
    }
    public void changeLanguage(String languageXpathSelector){
        uiActions.clickOn(languageMenuXpath,WebUIActions.Locators.XPath);
        uiActions.clickOn(languageXpathSelector,WebUIActions.Locators.XPath);
        uiActions.clickOn(saveChangesButtonXpath,WebUIActions.Locators.XPath);


    }

    public void openAllMenu() {
        uiActions.clickOn(allMenuID,WebUIActions.Locators.id);
    }

    public void navigateToCart(){

        uiActions.clickOn(cartID,WebUIActions.Locators.id);

    }


}
