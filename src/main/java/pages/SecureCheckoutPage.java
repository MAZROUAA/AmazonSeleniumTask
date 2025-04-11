package pages;

import actions.WebUIActions;

public class SecureCheckoutPage {

    //Selectors

    String addNewAddressButtonID="add-new-address-desktop-sasp-tango-link";
    String changeAddressID="addressChangeLinkId";
    String addNewAddressButton2ID = "add-new-address-popover-link";
    String useThisAddressButtonID="shipToThisAddressButton";



    WebUIActions uiActions = new WebUIActions();

    public void naviagteToAddAddressPage() {

        try {
            uiActions.clickOn(changeAddressID, WebUIActions.Locators.id);
            uiActions.clickOn(addNewAddressButtonID, WebUIActions.Locators.id);
        } catch (Exception e) {
            try {
                uiActions.clickOn(addNewAddressButton2ID, WebUIActions.Locators.id);

            } catch (Exception c) {
                System.out.println(c.getMessage());
            }

        }
    }

    public void useSavedAddress() {

        try {
            uiActions.clickOn(changeAddressID, WebUIActions.Locators.id);
            uiActions.clickOn(useThisAddressButtonID, WebUIActions.Locators.id);
        } catch (Exception e) {

                System.out.println("No saved address + "+ e.getMessage());
            }

        }


    }


