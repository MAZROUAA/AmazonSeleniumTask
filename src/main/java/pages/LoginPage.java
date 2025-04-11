package pages;

import actions.WebUIActions;
import helpers.Helpers;
public class LoginPage {

    //Selectors

    String loginTapID = "nav-link-accountList";
    String usernameFieldID = "ap_email_login";
    String followUpButtonID = "continue";
    String passwordFieldID = "ap_password";
    String loginButtonID = "signInSubmit";

    WebUIActions uiActions = new WebUIActions();

    public void login() throws InterruptedException {
        //Navigate to login page
        uiActions.clickOn(loginTapID,WebUIActions.Locators.id);

        //Writing Username
        uiActions.setText(usernameFieldID,WebUIActions.Locators.id,Helpers.userData.getProperty("username"));

        //Click on followup button
        uiActions.clickOn(followUpButtonID,WebUIActions.Locators.id);

        //Writing password
        uiActions.setText(passwordFieldID,WebUIActions.Locators.id,Helpers.userData.getProperty("password"));

        //Click on login button
        uiActions.clickOn(loginButtonID,WebUIActions.Locators.id);



    }





}
