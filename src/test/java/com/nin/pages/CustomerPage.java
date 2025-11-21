package com.nin.pages;

import keyworks.ActionKeywords;
import org.openqa.selenium.By;
import utils.LogUtils;

public class CustomerPage {
    private By titleCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By btnAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By inputSearchCustomer = By.xpath("//input[@id='search_input']");

    public void clickAddNewCustomer() {
        ActionKeywords.clickElement(btnAddNewCustomer);
        LogUtils.info("Clicked on Add New Customer button");
    }

    //Action of table

    private By linkView = By.xpath("//a[text()='View']");
    private By linkContacts = By.xpath("//a[text()='Contacts']");
    private By linkDelete = By.xpath("//a[text()='Delete ']");


    //Tab Customer Details
    private By inputCompany = By.xpath("//input[@id='company']");
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

    private void selectLangauge(String language) {
        ActionKeywords.clickElement(dropdowDefaultLanguege);
        String xpathLanguage = "//span[normalize-space()='" + language + "']";
        System.out.println("Selecting language: " + language);
        ActionKeywords.clickElement(By.xpath(xpathLanguage));
    }

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

    private void clicksave(){
        ActionKeywords.clickElement(btnSave);
        LogUtils.info("Clicked on Save button");
    }

    public void fillDataAddNewCustomer(String company, String vatNumber, String phone, String website, String group, String address, String city, String state, String zipCode, String currency, String language, String country) {
        enterCompanyName(company);
        enterVATNumber(vatNumber);
        enterPhone(phone);
        enterWebsite(website);
        selectGroup(group);
        selectCurrency(currency);
        selectLanguage(language);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        selectCountry(country);
        clicksave();
        ActionKeywords.sleep(3);
        LogUtils.info("Filled all customer information successfully");
    }


}