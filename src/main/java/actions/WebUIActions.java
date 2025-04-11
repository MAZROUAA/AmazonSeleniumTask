package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static actions.BrowserActions.driver;
import static org.asynchttpclient.util.Assertions.assertNotNull;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class WebUIActions {
    public static final Duration timeOut=Duration.ofSeconds(10);
    private List<WebElement> elements;

    public void clickOn(String selector,Locators l) {
        By b = returnElementLocatorBy(selector,l);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);

        WebElement element = driver.findElement(b);

        try {

            element.click();

        } catch (Exception e) {
            try{
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click();", element);
            }
            catch(Exception c) {
                System.err.println("Error Clicking On element: " + e.getMessage());
            }
        }

    }

    public void clickOnElements(String xPathSelector) throws InterruptedException {

        By b=new By.ByXPath(xPathSelector);

        List<WebElement> elements = driver.findElements(b);
        int elementsSize = elements.size();
        System.out.println("click on number of elements "+elementsSize);



        for (int i = 0; i < elementsSize; i++) {

            System.out.println("element number "+i);
            elements = driver.findElements(b);
            try {

                waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
                waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);

                elements.get(i).click();

            } catch (Exception e) {
                try{
                    waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
                    waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);
                    JavascriptExecutor executor = (JavascriptExecutor)driver;
                    executor.executeScript("arguments[0].click();", elements.get(i));
                }
                catch(Exception c) {
                    System.err.println("Error Clicking On element: " + e.getMessage());
                }
            }
        }

    }

    public  void setText(String selector,Locators l, String text) {
        By b = returnElementLocatorBy(selector,l);
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        waitUntil(b, ExpectedConditionsEnum.ElementToBeClickable);
        WebElement element =driver.findElement(b);

        try {

            element.sendKeys(text);

        }
            catch(Exception e) {
                System.err.println("Error sending keys to element: " + e.getMessage());
            }
        }



    public WebElement waitUntil(By b, ExpectedConditionsEnum condition) {

        try {
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:

                    element = (new WebDriverWait(driver,timeOut)).until(ExpectedConditions.presenceOfElementLocated(b));
                    return element;

                case ElementToBeClickable:
                    element = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.elementToBeClickable(b));
                    return element;


                default:
                    element = null;
                    System.err.println(" Unsupported Expected condition ");
            }
            return element ;

        } catch (Exception e) {
            System.err.println(" waiting for element: " + e.getMessage());
            return null;
        }
    }

    public void navigateToPage(String url,String selector,Locators l) {
        driver.get(url);
        By b = returnElementLocatorBy(selector,l);
        new WebDriverWait(driver, timeOut).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement element = waitUntil(b, ExpectedConditionsEnum.presenceOfElement);
        assertNotNull(element, "Navigation Failed to this Website "+url);
    }

    public By returnElementLocatorBy(String selector,Locators l){
        switch (l){
            case XPath:
                return new By.ByXPath(selector);

            case id:
                return new By.ById(selector);


            case CSS:
                return new By.ByCssSelector(selector);

            case name:
                return new By.ByName(selector);


            default: return  null;
        }
    }

    public List<WebElement> findMultipleElements(By b){
        waitUntil(b,ExpectedConditionsEnum.presenceOfElement);
        elements = driver.findElements(b);
        return elements;
    }

    public  String getText(By b) {
        waitUntil(b, ExpectedConditionsEnum.presenceOfElement);

        String result = "";
        try {

            result = driver.findElement(b).getText();
        } catch (Exception e) {
            System.out.println(" Can not get text as a result of: " + e);

        }
        return result;

    }




    public enum Locators {
        XPath,
        CSS,
        id,
        name

    }

    public enum ExpectedConditionsEnum{
        presenceOfElement,
        ElementToBeClickable
    }

}
