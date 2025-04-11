package pages;

import actions.WebUIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class ResultsPage {

    //Selectors


    String sortByDropDownID = "a-autoid-0-announce";
    public String highToLowSortXpath = "//a[contains(text(),'High to Low')]";

    public String productsAddToCartButtonsXpath ="//span[@class='a-price-whole' and number(translate(., ',', '')) < 15000] //ancestor::div[contains(@class,'price-instructions-style')]//following-sibling::div[contains(@class,'a-spacing-top-micro')]//following::button[@aria-label='Add to cart']";

    String nextPageButtonXpath = "//a[text()='Next']";



    WebUIActions uiActions = new WebUIActions();
    public void sortBy(String sortTypeXpath){
        uiActions.clickOn(sortByDropDownID,WebUIActions.Locators.id);
        uiActions.clickOn(sortTypeXpath,WebUIActions.Locators.XPath);

    }

    public int addProductsToCartAndGetProductsCount(String productsToAddXpath) throws InterruptedException {
        List<WebElement> elements;

        By b=new By.ByXPath(productsToAddXpath);
        elements = uiActions.findMultipleElements(b);

        while (elements.size()==0 ) {
            try {
                uiActions.clickOn(nextPageButtonXpath,WebUIActions.Locators.XPath);
                elements = uiActions.findMultipleElements(b);
            } catch (NoSuchElementException e) {
                System.out.println("No Products with this description");
            }
        }
        if (elements.size()!=0) {
            System.out.println("Number of products under 15000 in this page :"+elements.size());
            uiActions.clickOnElements(productsToAddXpath);
        }
        return elements.size();

    }


}
