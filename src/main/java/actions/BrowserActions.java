package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserActions {

    public static WebDriver driver;

    public void initializingWebDriver(String browser) {
        try{
            if(browser.toLowerCase().equals(BrowserActions.Browsers.chrome.toString())) {
                this.driver = new ChromeDriver();

            }
            else if(browser.toLowerCase().equals( BrowserActions.Browsers.firefox.toString())){
                this.driver = new FirefoxDriver();

            }
        }

        catch(Exception e){
            System.err.println("Error initializing WebDriver: " + e.getMessage());

        }
    }

    public void screenMaximizing(){
        this.driver.manage().window().maximize();
    }

    public void closeDriver(){
        driver.close();
    }




    enum Browsers{
        chrome,
        firefox
    }

}
