package com.nin.pages;

import io.qameta.allure.Step;
import keyworks.ActionKeywords;
import models.Customer;
import org.openqa.selenium.By;
import utils.LogUtils;

public class TaskPage {
    private By btnAddNewTask = By.xpath("//a[normalize-space()='New Task']");
    private By inputSearch = By.xpath("//input[@id='search_input']");

    public void clickAddNewCustomer() {
        ActionKeywords.clickElement(btnAddNewTask);
        LogUtils.info("Clicked on Add New Task button");
    }

    //Tab Customer Details
    private By checkBoxPublic = By.xpath("//input[@id='task_is_public']");
    private By checkBoxBillable = By.xpath("//input[@id='task_is_billable']");
    private By linkAttachFiles = By.xpath("//a[normalize-space()='Attach Files']");
    private By inputSubject = By.xpath("//input[@id='name']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");
    private By selectStartDate = By.xpath("");
    private By selectDueDate = By.xpath("");
    private By dropdowPriority = By.xpath("//button[@title='Medium']");
    private By dropdowRepeatEvery = By.xpath("//button[@data-id='repeat_every']");
    private By dropdowRelatedTo = By.xpath("//button[@data-id='rel_type']");
    private By dropdowAssignees = By.xpath("//button[@data-id='assignees']");
    private By dropdowFollowers = By.xpath("//button[@data-id='followers[]']");
    private By dropdownTag = By.xpath("//input[@placeholder='Tag']");
    private By inputTaskDescription = By.xpath("//textarea[@id='description']");
    private By btnClose = By.xpath("//button[text()='Close']");
    private By btnSave = By.xpath("//button[text()='Save']");
    private By iconClose = By.xpath("");
    //
    private By alertMessage = By.xpath("//div[@id='alert_float_1']");
    private By alertSuccess = By.xpath("//div[contains(@class,'alert-success')]");
    private By alertError = By.xpath("//div[contains(@class,'alert-danger')]");



    private void setPublic(boolean value) {
        if (value) {
            ActionKeywords.clickElement(checkBoxPublic);
            LogUtils.info("Set task as public");
        } else {
            ActionKeywords.clickElement(checkBoxPublic); // toggle off
            LogUtils.info("Set task as not public");
        }
    }

    private void setBillable(boolean value) {
        if (value) {
            ActionKeywords.clickElement(checkBoxBillable);
            LogUtils.info("Set task as billable");
        } else {
            ActionKeywords.clickElement(checkBoxBillable);
            LogUtils.info("Set task as not billable");
        }
    }

    private void clickAttachFiles() {
        ActionKeywords.clickElement(linkAttachFiles);
        LogUtils.info("Clicked Attach Files link");
    }

    private void enterSubject(String subject) {
        ActionKeywords.sendKeys(inputSubject, subject);
        LogUtils.info("Entered subject: " + (subject.isEmpty() ? "[EMPTY]" : subject));
    }

    private void enterHourlyRate(String rate) {
        ActionKeywords.sendKeys(inputHourlyRate, rate);
        LogUtils.info("Entered hourly rate: " + (rate.isEmpty() ? "[EMPTY]" : rate));
    }

    private void selectStartDate(String date) {
        ActionKeywords.clickElement(selectStartDate);
        ActionKeywords.sendKeys(selectStartDate, date);
        LogUtils.info("Selected start date: " + date);
    }

    private void selectDueDate(String date) {
        ActionKeywords.clickElement(selectDueDate);
        ActionKeywords.sendKeys(selectDueDate, date);
        LogUtils.info("Selected due date: " + date);
    }

    private void selectPriority(String priority) {
        ActionKeywords.clickElement(dropdowPriority);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + priority + "']"));
        LogUtils.info("Selected priority: " + priority);
    }

    private void selectRepeatEvery(String value) {
        ActionKeywords.clickElement(dropdowRepeatEvery);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + value + "']"));
        LogUtils.info("Selected repeat every: " + value);
    }

    private void selectRelatedTo(String related) {
        ActionKeywords.clickElement(dropdowRelatedTo);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + related + "']"));
        LogUtils.info("Selected related to: " + related);
    }

    private void selectAssignee(String assignee) {
        ActionKeywords.clickElement(dropdowAssignees);
        ActionKeywords.sendKeys(dropdowAssignees, assignee);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + assignee + "']"));
        LogUtils.info("Selected assignee: " + assignee);
    }

    private void addFollower(String follower) {
        ActionKeywords.clickElement(dropdowFollowers);
        ActionKeywords.sendKeys(dropdowFollowers, follower);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + follower + "']"));
        LogUtils.info("Added follower: " + follower);
    }

    private void addTag(String tag) {
        ActionKeywords.sendKeys(dropdownTag, tag);
        ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + tag + "']"));
        LogUtils.info("Added tag: " + tag);
    }

    private void enterTaskDescription(String description) {
        ActionKeywords.sendKeys(inputTaskDescription, description);
        LogUtils.info("Entered task description: " + (description.isEmpty() ? "[EMPTY]" : description));
    }

    private void clickClose() {
        ActionKeywords.clickElement(btnClose);
        LogUtils.info("Clicked Close button");
    }

    private void clickSave() {
        ActionKeywords.clickElement(btnSave);
        LogUtils.info("Clicked Save button");
    }

    private void clickIconClose() {
        ActionKeywords.clickElement(iconClose);
        LogUtils.info("Clicked icon close");
    }

    public void fillDataAddNewTask(String subject, String hourlyRate, String priority, String repeatEvery, String relatedTo, String assignee, String follower, String tag, String description, boolean isPublic, boolean isBillable) {
        enterSubject(subject);
        enterHourlyRate(hourlyRate);
        selectPriority(priority);
        selectRepeatEvery(repeatEvery);
        selectRelatedTo(relatedTo);
        selectAssignee(assignee);
        addFollower(follower);
        addTag(tag);
        enterTaskDescription(description);
        setPublic(isPublic);
        setBillable(isBillable);
        clickSave();
        ActionKeywords.sleep(3);
        LogUtils.info("Filled all task information successfully");
    }

    public boolean verifyTaskAddedSuccess() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isSuccess = ActionKeywords.checkElementDisplayed(alertSuccess);
        if (isSuccess) {
            String message = ActionKeywords.getTextElement(alertSuccess);
            LogUtils.info("Task added successfully: " + message);
        }
        return isSuccess;
    }

    public boolean verifyTaskAddedFailed() {
        ActionKeywords.waitForElementVisible(alertMessage, 5);
        boolean isFailed = ActionKeywords.checkElementDisplayed(alertError);
        if (isFailed) {
            String errorMessage = ActionKeywords.getTextElement(alertError);
            LogUtils.error("Failed to add Task: " + errorMessage);
        }
        return isFailed;
    }



    public String getAlertMessage() {
        if (ActionKeywords.checkElementDisplayed(alertMessage)) {
            return ActionKeywords.getTextElement(alertMessage);
        }
        return "";
    }


}