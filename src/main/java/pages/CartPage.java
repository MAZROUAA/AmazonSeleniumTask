package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import actions.WebUIActions;
import org.testng.Assert;

import java.util.List;

public class CartPage {

    //Selectors
    String selectedProductsPricesXpath="//div[contains(@class, 'a-section a-spacing-mini sc-list-body sc-java-remote-feature')]//span[@class='a-price-whole']";

    String totalPriceXpath="//span[@id='sc-subtotal-amount-buybox']/span";

    String cartProductsNumberXpath="//span[@id='sc-subtotal-label-buybox']";
    String proceedButtonSelectorName="proceedToRetailCheckout";
    String deleteButtonsXpath="//input[contains(@name,'submit.delete')]";
    String cartProductsNumberID="nav-cart-count";


    WebUIActions uiActions = new WebUIActions();

    public double getSelectedProductsPricesSummation(){
        List<WebElement> elements;
        double total = 0.0;

        By b=new By.ByXPath(selectedProductsPricesXpath);
        elements = uiActions.findMultipleElements(b);

        // Loop through the list of elements to process each price
        for (WebElement element : elements) {

            String priceText = element.getText();
            priceText = priceText.replace(",", "");
            try {
                double price = Double.parseDouble(priceText);
                total += price;
            } catch (Exception e) {
                System.out.println("Invalid price format: " + priceText);
            }
        }

        // Output the total
        System.out.println("Total price: " + total);
        return total;

    }

    public double getTotalPrice(){
        String totalPrice="";

        By b=new By.ByXPath(totalPriceXpath);
        totalPrice=uiActions.getText(b);
        String totalPriceAfterLettersRemoval=totalPrice.replaceAll("[^0-9\\.]", "");

        double price = Double.parseDouble(totalPriceAfterLettersRemoval);

        return price;

    }

    public int getCartProductsCount(){
        String cartProductsCount="";

        By b=new By.ByXPath(cartProductsNumberXpath);
        cartProductsCount=uiActions.getText(b);

        String cartProductsCountAfterLettersRemoval=cartProductsCount.replaceAll("[^0-9\\.]", "");


        int count = Integer.parseInt(cartProductsCountAfterLettersRemoval);

        return count;

    }

    public void clearCart(){

        List<WebElement> elements;
        By deleteButtonsSelctor=new By.ByXPath(deleteButtonsXpath);
        By cartCount=new By.ById(cartProductsNumberID);

        uiActions.waitUntil(deleteButtonsSelctor, WebUIActions.ExpectedConditionsEnum.presenceOfElement);
        uiActions.waitUntil(deleteButtonsSelctor, WebUIActions.ExpectedConditionsEnum.ElementToBeClickable);
        try {

            elements=uiActions.findMultipleElements(deleteButtonsSelctor);
            int elementsSize = elements.size();
            System.out.println("clear cart"+elementsSize);
            if (elements.size()==0){
                return;
            };
            for (int i = 0; i < elementsSize; i++) {
                uiActions.clickOn(deleteButtonsXpath,WebUIActions.Locators.XPath);
                Thread.sleep(2000);
            }



        } catch (Exception e) {
                    System.out.println("The cart is Empty now"+e);
                }

        Assert.assertEquals(uiActions.getText(cartCount),"0");


    }




    public void navigateToSecureCheckOutPage(){
        uiActions.clickOn(proceedButtonSelectorName,WebUIActions.Locators.name);

    }

}
