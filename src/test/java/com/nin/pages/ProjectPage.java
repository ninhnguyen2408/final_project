package com.nin.pages;

import io.qameta.allure.Step;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;
import utils.LogUtils;

public class ProjectPage {
    private By btnAddNewProject = By.xpath("//a[normalize-space()='New Project']");
    private By inputSearchProject = By.xpath("//input[@id='search_input']");

    public void clickAddNewProject() {
        ActionKeywords.clickElement(btnAddNewProject);
        LogUtils.info("Clicked on Add New Project button");
    }

    //Add Project
    private By inputProjectName = By.xpath("//input[@id='name']");
    private By dropdownnCustomer = By.xpath("//button[@data-id='clientid']");
    private By inputSearchCustomer = By.xpath("//input[@placeholder='Type to search...']");
    private By dropdownBillingType = By.xpath("//button[@title='Project Hours']");

    private By btnSave = By.xpath("//button[normalize-space()='Save']");

    private By alertMessage = By.xpath("//div[@id='alert_float_1']");
    private By alertSuccess = By.xpath("//div[contains(@class,'alert-success')]");
   

    private void enterProjectName(String projectName) {
        ActionKeywords.sendKeys(inputProjectName, projectName);
        LogUtils.info("Entered email: " + (projectName.isEmpty() ? "[EMPTY]" : projectName));
    }

    private void selectCustomer(String customer) {
        ActionKeywords.clickElement(dropdownnCustomer);
        ActionKeywords.sendKeys(inputSearchCustomer, customer);
        ActionKeywords.clickElement(By.xpath("//a[@role='option']//span[@class='text' and text()='" + customer + "']"));
        LogUtils.info("Selected customer: " + customer);
    }

    private void clickSave(){
        ActionKeywords.waitForElementVisible(btnSave, 5);
        ActionKeywords.scrollToElement(btnSave);
        ActionKeywords.sleep(1);
        ActionKeywords.clickElement(btnSave);
        LogUtils.info("Clicked on Save button");
    }

    @Step("Fill customer data: {projectName}, {customer}")
    public void addNewProject(String projectName, String customer) {
        enterProjectName(projectName);
        selectCustomer(customer);
        clickSave();
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

    public String getAlertMessage() {
        if (ActionKeywords.checkElementDisplayed(alertMessage)) {
            return ActionKeywords.getTextElement(alertMessage);
        }
        return "";
    }


}