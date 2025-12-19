package com.nin.pages;

import io.qameta.allure.Step;
import keyworks.ActionKeywords;
import models.Customer;
import org.openqa.selenium.By;
import utils.LogUtils;

public class ProjectPage {
    private By btnAddNewProject = By.xpath("//a[normalize-space()='New Project']");
    private By inputSearchProject = By.xpath("//input[@id='search_input']");

    //ul[@id='top_search_dropdown']//a[normalize-space(.)='ABC Technology Company']

    public void clickAddNewProject() {
        ActionKeywords.clickElement(btnAddNewProject);
        LogUtils.info("Clicked on Add New Project button");
    }

    //Action of table
    private By linkView = By.xpath("//a[text()='View']");
    private By linkContacts = By.xpath("//a[text()='Contacts']");
    private By linkDelete = By.xpath("//a[text()='Delete ']");


    //Add Project
    private By inputProjectName = By.xpath("//input[@id='name']");
    private By inputVATNumber = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By inputSearchGroups = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input[@type='search']");
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By inputSearchCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//input[@type='search']");
    private By dropdowDefaultLanguege = By.xpath("//button[@data-id='default_language']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By btnSaveAndCreat = By.xpath("//button[contains(@class,'save-and-add-contact')]");
    private By btnSave = By.xpath("//button[contains(@class,'only-save')]");

    private By alertMessage = By.xpath("//div[@id='alert_float_1']");
    private By alertSuccess = By.xpath("//div[contains(@class,'alert-success')]");
    private By alertError = By.xpath("//div[contains(@class,'alert-danger')]");
    private By errorDuplicateCompany = By.xpath("//div[contains(text(),'Customer already exists') or contains(text(),'already exist')]");


    private void enterCompanyName(String company) {
        ActionKeywords.sendKeys(inputCompany, company);
        LogUtils.info("Entered email: " + (company.isEmpty() ? "[EMPTY]" : company));
    }

    private void enterVATNumber(String vatNumber) {
        ActionKeywords.sendKeys(inputVATNumber, vatNumber);
        LogUtils.info("Entered VAT Number: " + (vatNumber.isEmpty() ? "[EMPTY]" : vatNumber));
    }

    private void enterPhone(String phone) {
        ActionKeywords.sendKeys(inputPhone, phone);
        LogUtils.info("Entered phone: " + (phone.isEmpty() ? "[EMPTY]" : phone));
    }

    private void enterWebsite(String website) {
        ActionKeywords.sendKeys(inputWebsite, website);
        LogUtils.info("Entered website: " + (website.isEmpty() ? "[EMPTY]" : website));
    }

    private void selectGroup(String group) {
        ActionKeywords.clickElement(dropdownGroups);
        ActionKeywords.sendKeys(inputSearchGroups, group);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + group + "']"));
        ActionKeywords.clickElement(dropdownGroups); // Close the dropdown
        LogUtils.info("Selected group: " + group);
    }

    private void selectCurrency(String currency) {
        ActionKeywords.clickElement(dropdownCurrency);
        ActionKeywords.sendKeys(inputSearchCurrency, currency);
        ActionKeywords.clickElement(By.xpath("//span[contains(text(),'" + currency + "')]"));
        LogUtils.info("Selected currency: " + currency);
    }
    private void selectLanguage(String language) {
        ActionKeywords.clickElement(dropdowDefaultLanguege);
        String xpathLanguage = "//span[normalize-space()='" + language + "']";
        System.out.println("Selecting language: " + language);
        ActionKeywords.clickElement(By.xpath(xpathLanguage));
        LogUtils.info("Selected language: " + language);
    }

    private void enterAddress(String address) {
        ActionKeywords.sendKeys(inputAddress, address);
        LogUtils.info("Entered address: " + (address.isEmpty() ? "[EMPTY]" : address));
    }

    private void enterCity(String city) {
        ActionKeywords.sendKeys(inputCity, city);
        LogUtils.info("Entered city: " + (city.isEmpty() ? "[EMPTY]" : city));
    }

    private void enterState(String state) {
        ActionKeywords.sendKeys(inputState, state);
        LogUtils.info("Entered state: " + (state.isEmpty() ? "[EMPTY]" : state));
    }

    private void enterZipCode(String zipCode) {
        ActionKeywords.sendKeys(inputZipCode, zipCode);
        LogUtils.info("Entered zip code: " + (zipCode.isEmpty() ? "[EMPTY]" : zipCode));
    }

    private void selectCountry(String country) {
        ActionKeywords.clickElement(dropdownCountry);
        ActionKeywords.sendKeys(inputSearchCustomer, country);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + country + "']"));
        LogUtils.info("Selected country: " + country);
    }

    private void clickSaveAndCreate() {
        ActionKeywords.clickElement(btnSaveAndCreat);
        LogUtils.info("Clicked on Save and Create button");
    }

    private void clickSave(){
        ActionKeywords.scrollToElement(btnSave);
        ActionKeywords.sleep(1);
        ActionKeywords.clickElement(btnSave);
        LogUtils.info("Clicked on Save button");
    }

    @Step("Fill customer data: {customer}")
    public void fillDataAddNewCustomer(Customer customer) {
        LogUtils.info("Filling customer data for: " + customer.getCompany());
        
        enterCompanyName(customer.getCompany());
        enterVATNumber(customer.getVatNumber());
        enterPhone(customer.getPhone());
        enterWebsite(customer.getWebsite());
        selectGroup(customer.getGroup());
        selectCurrency(customer.getCurrency());
        selectLanguage(customer.getLanguage());
        enterAddress(customer.getAddress());
        enterCity(customer.getCity());
        enterState(customer.getState());
        enterZipCode(customer.getZipCode());
        selectCountry(customer.getCountry());
        clickSave();
        LogUtils.info("Filled all customer information successfully: " + customer.toString());
    }

    public boolean verifyCustomerAddedSuccess() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isSuccess = ActionKeywords.checkElementDisplayed(alertSuccess);
        if (isSuccess) {
            String message = ActionKeywords.getTextElement(alertSuccess);
            LogUtils.info("Customer added successfully: " + message);            
        }
        return isSuccess;
    }

    public boolean verifyCustomerEditedSuccess() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isEditSuccess = ActionKeywords.checkElementDisplayed(alertSuccess);
        if (isEditSuccess) {
            String message = ActionKeywords.getTextElement(alertSuccess);
            LogUtils.info("Customer edit successfully: " + message);            
        }
        return isEditSuccess;
    }

    public boolean verifyCustomerAddedFailed() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isFailed = ActionKeywords.checkElementDisplayed(alertError);
        if (isFailed) {
            String errorMessage = ActionKeywords.getTextElement(alertError);
            LogUtils.error("Failed to add customer: " + errorMessage);
        }
        return isFailed;
    }

    public boolean verifyCustomerDeleteSuccess() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isDeleteSuccess = ActionKeywords.checkElementDisplayed(alertSuccess);
        if (isDeleteSuccess) {
            String message = ActionKeywords.getTextElement(alertSuccess);
            LogUtils.info("Customer deleted successfully: " + message);            
        }
        return isDeleteSuccess;
    }

    public String getAlertMessage() {
        if (ActionKeywords.checkElementDisplayed(alertMessage)) {
            return ActionKeywords.getTextElement(alertMessage);
        }
        return "";
    }

    // ========== SEARCH & ACTION METHODS ==========
    
    @Step("Searching for customer: {companyName}")
    public void searchCustomer(String companyName) {
        ActionKeywords.sendKeys(inputSearchCustomer, companyName);
        ActionKeywords.sleep(2);
        LogUtils.info("Searched for customer: " + companyName);
    }

    @Step("Clicking View for customer: {companyName}")
    public void clickViewCustomer(String companyName) {
        searchCustomer(companyName);
        By customerRow = By.xpath("//ul[@id='top_search_dropdown']//a[normalize-space(.)='" + companyName + "']");

        ActionKeywords.clickElement(customerRow);
        ActionKeywords.sleep(2);
        LogUtils.info("Clicked on customer row to View: " + companyName);
    }

    @Step("Clicking Delete for customer: {companyName}")
    public void clickDeleteCustomer(String companyName) {
        ActionKeywords.mouseHover(hoverAction);
        ActionKeywords.sleep(1);
        ActionKeywords.clickElement(linkDelete);
        ActionKeywords.sleep(1);
        LogUtils.info("Hovered on row and clicked Delete for customer: " + companyName);
    }

    @Step("Confirming delete action")
    public void confirmDelete() {
        ActionKeywords.acceptAlert();
        ActionKeywords.sleep(2);
        LogUtils.info("Confirmed delete action via browser alert");
    }

    // ========== EDIT CUSTOMER ==========
    
    @Step("Editing customer phone number: {newPhone}")
    public void editCustomerPhone(String companyName, String newPhone) {
        LogUtils.info("Editing phone number for customer: " + companyName);
        clickViewCustomer(companyName);
        ActionKeywords.clearAndSendKeys(inputPhone, newPhone);
        clickSave();
        LogUtils.info("217427340274");
        ActionKeywords.sleep(2);
        LogUtils.info("Edited customer phone successfully: " + companyName);
    }
    
    @Step("Editing customer data: {customer}")
    public void editCustomer(Customer customer) {
        LogUtils.info("Editing customer data for: " + customer.getCompany());
        ActionKeywords.clearAndSendKeys(inputCompany, customer.getCompany());
        ActionKeywords.clearAndSendKeys(inputVATNumber, customer.getVatNumber());
        ActionKeywords.clearAndSendKeys(inputPhone, customer.getPhone());
        ActionKeywords.clearAndSendKeys(inputWebsite, customer.getWebsite());
        if (customer.getGroup() != null && !customer.getGroup().isEmpty()) {
            selectGroup(customer.getGroup());
        }
        if (customer.getCurrency() != null && !customer.getCurrency().isEmpty()) {
            selectCurrency(customer.getCurrency());
        }
        if (customer.getLanguage() != null && !customer.getLanguage().isEmpty()) {
            selectLanguage(customer.getLanguage());
        }
        ActionKeywords.clearAndSendKeys(inputAddress, customer.getAddress());
        ActionKeywords.clearAndSendKeys(inputCity, customer.getCity());
        ActionKeywords.clearAndSendKeys(inputState, customer.getState());
        ActionKeywords.clearAndSendKeys(inputZipCode, customer.getZipCode());
        
        if (customer.getCountry() != null && !customer.getCountry().isEmpty()) {
            selectCountry(customer.getCountry());
        }
        
        clickSave();
        ActionKeywords.sleep(3);
        
        LogUtils.info("Edited customer information successfully: " + customer.toString());
    }

    // ========== VERIFY METHODS ==========
    
    @Step("Verifying customer exists in table: {companyName}")
    public boolean verifyCustomerExists(String companyName) {
        searchCustomer(companyName);
        By customerRow = By.xpath("//td[normalize-space()='" + companyName + "']");
        boolean exists = ActionKeywords.checkElementDisplayed(customerRow);
        if (exists) {
            LogUtils.info("Customer found: " + companyName);
        } else {
            LogUtils.info("Customer not found: " + companyName);
        }
        return exists;
    }

    @Step("Verifying customer deleted successfully")
    public boolean verifyCustomerDeleted(String companyName) {
        ActionKeywords.sleep(2);
        searchCustomer(companyName);
        By customerRow = By.xpath("//td[normalize-space()='" + companyName + "']");
        boolean stillExists = ActionKeywords.checkElementExist(customerRow);
        
        if (!stillExists) {
            LogUtils.info("Customer deleted successfully: " + companyName);
            return true;
        } else {
            LogUtils.error("✗ Customer still exists after delete: " + companyName);
            return false;
        }
    }


}