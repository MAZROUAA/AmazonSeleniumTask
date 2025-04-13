package pages;

import actions.WebUIActions;
import helpers.Helpers;

public class AddressPage {

    //Selectors
    String fullNameFieldID = "address-ui-widgets-enterAddressFullName";
    String phoneNumberFieldID = "address-ui-widgets-enterAddressPhoneNumber";
    String streetNameFieldID = "address-ui-widgets-enterAddressLine1";
    String buildingNameFieldID = "address-ui-widgets-enter-building-name-or-number";
    String cityFieldID = "address-ui-widgets-enterAddressCity";
    String districtFieldID ="address-ui-widgets-enterAddressDistrictOrCounty";
    String governorateFieldID="address-ui-widgets-enterAddressStateOrRegion";
    String nearestLandMarkFieldID = "address-ui-widgets-landmark";
    String defaultAddressCheckboxID = "address-ui-widgets-use-as-my-default";
    String addAddressButtonXpath = "//span[@id='checkout-primary-continue-button-id']";



    WebUIActions uiActions = new WebUIActions();
    public void addNewAdsress( Boolean useAsDefaultAddress) throws InterruptedException {
        uiActions.setText(fullNameFieldID,WebUIActions.Locators.id, Helpers.addressData.getProperty("fullName"));
        uiActions.setText(phoneNumberFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("phoneNumber"));
        uiActions.setText(streetNameFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("streetName"));
        uiActions.setText(buildingNameFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("buildingName"));
        uiActions.setText(cityFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("city"));
        Thread.sleep(2500);
        uiActions.setText(districtFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("district"));
        uiActions.setText(nearestLandMarkFieldID,WebUIActions.Locators.id,Helpers.addressData.getProperty("nearestLandMark"));

        if(useAsDefaultAddress)
        {
        uiActions.clickOn(defaultAddressCheckboxID,WebUIActions.Locators.id);}
        uiActions.clickOn(addAddressButtonXpath,WebUIActions.Locators.XPath);
    }


}
