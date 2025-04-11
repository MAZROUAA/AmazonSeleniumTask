package pages;

import actions.WebUIActions;

public class ProductsFiltersPage {

    //Selectors

    public String freeShippingFilterXpath="//span[text()='Free Shipping']";
    public String newFilterXpath="//span[text()='New']";


    WebUIActions uiActions = new WebUIActions();
    public void selectFilter(String filterXpath){
        uiActions.clickOn(filterXpath, WebUIActions.Locators.XPath);

    }
}
