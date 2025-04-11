package pages;

import actions.WebUIActions;
public class AllMenuPage {

    //Selectors

    String seeAllButtonXpath="//a[@aria-label='See All Categories']";
    public String videoGamesButtonXpath="//div[text()='Video Games']";
    public String allVideoGamesButtonXpath="//a[text()='All Video Games']";



    WebUIActions uiActions = new WebUIActions();

    public void selectCategory(String categoryButtonXpath ,String categoryAllProductsButtonXpath){
        uiActions.clickOn(seeAllButtonXpath,WebUIActions.Locators.XPath);
        uiActions.clickOn(categoryButtonXpath,WebUIActions.Locators.XPath);
        uiActions.clickOn(categoryAllProductsButtonXpath,WebUIActions.Locators.XPath);

    }
}
