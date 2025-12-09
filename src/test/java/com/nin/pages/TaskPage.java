package com.nin.pages;

import io.qameta.allure.Step;
import keyworks.ActionKeywords;
import models.Customer;
import models.Task;

import org.openqa.selenium.By;
import utils.LogUtils;

public class TaskPage {
    private By btnAddNewTask = By.xpath("//a[normalize-space()='New Task']");
    private By inputSearch = By.xpath("//input[@id='search_input']");

    //Tab Customer Details
    private By checkBoxPublic = By.xpath("//input[@id='task_is_public']");
    private By checkBoxBillable = By.xpath("//input[@id='task_is_billable']");
    private By linkAttachFiles = By.xpath("//a[normalize-space()='Attach Files']");
    private By inputSubject = By.xpath("//input[@id='name']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");
    private By selectStartDate = By.xpath("//input[@id='startdate']");
    private By selectDueDate = By.xpath("//input[@id='duedate']");
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


    public void clickAddNewTask() {
        ActionKeywords.clickElement(btnAddNewTask);
        LogUtils.info("Clicked on Add New Task button");
    }

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

    private void selectStartDate(String startDate) {
        ActionKeywords.clickElement(selectStartDate);
        ActionKeywords.sendKeys(selectStartDate, startDate);
        LogUtils.info("Selected start date: " + startDate);
    }

    private void selectDueDate(String dueDate) {
        ActionKeywords.clickElement(selectDueDate);
        ActionKeywords.sendKeys(selectDueDate, dueDate);
        LogUtils.info("Selected due date: " + dueDate);
    }

    private void selectPriority(String priority) {
        if (priority != null && !priority.isEmpty()) {
            ActionKeywords.clickElement(dropdowPriority);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + priority + "']"));
            LogUtils.info("Selected priority: " + priority);
        } else {
            LogUtils.info("Skipped priority selection - empty value");
        }
    }

    private void selectRepeatEvery(String value) {
        if (value != null && !value.isEmpty()) {
            ActionKeywords.clickElement(dropdowRepeatEvery);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + value + "']"));
            LogUtils.info("Selected repeat every: " + value);
        } else {
            LogUtils.info("Skipped repeat every selection - empty value");
        }
    }

    private void selectRelatedTo(String related) {
        if (related != null && !related.isEmpty()) {
            ActionKeywords.clickElement(dropdowRelatedTo);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + related + "']"));
            LogUtils.info("Selected related to: " + related);
        } else {
            LogUtils.info("Skipped related to selection - empty value");
        }
    }

    private void selectAssignee(String assignee) {
        if (assignee != null && !assignee.isEmpty()) {
            ActionKeywords.clickElement(dropdowAssignees);
            ActionKeywords.sendKeys(dropdowAssignees, assignee);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + assignee + "']"));
            LogUtils.info("Selected assignee: " + assignee);
        } else {
            LogUtils.info("Skipped assignee selection - empty value");
        }
    }

    private void addFollower(String follower) {
        if (follower != null && !follower.isEmpty()) {
            ActionKeywords.clickElement(dropdowFollowers);
            ActionKeywords.sendKeys(dropdowFollowers, follower);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + follower + "']"));
            LogUtils.info("Added follower: " + follower);
        } else {
            LogUtils.info("Skipped follower - empty value");
        }
    }

    private void addTag(String tag) {
        if (tag != null && !tag.isEmpty()) {
            ActionKeywords.sendKeys(dropdownTag, tag);
            ActionKeywords.clickElement(By.xpath("//span[normalize-space()='" + tag + "']"));
            LogUtils.info("Added tag: " + tag);
        } else {
            LogUtils.info("Skipped tag - empty value");
        }
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
        ActionKeywords.clickElementWithJs(btnSave);
        LogUtils.info("Clicked Save button");
    }

    private void clickIconClose() {
        ActionKeywords.clickElement(iconClose);
        LogUtils.info("Clicked icon close");
    }

    public void fillDataAddNewTask(String subject, String hourlyRate, String startDate, String dueDate,String priority, String repeatEvery, String relatedTo, String assignee, String follower, String tag, String description, boolean isPublic, boolean isBillable) {
        enterSubject(subject);
        enterHourlyRate(hourlyRate);
        selectStartDate(startDate);
        selectDueDate(dueDate);
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

    @Step("Fill task data: {task}")
    public void fillDataAddNewTask(Task task) {
        LogUtils.info("Filling task data for: " + task.getSubject());
        //setPublic(task.isPublic());
        enterSubject(task.getSubject());
        enterHourlyRate(task.getHourlyRate());
        selectStartDate(task.getStartDate());
        selectDueDate(task.getDueDate());
        selectPriority(task.getPriority());
        selectRepeatEvery(task.getRepeatEvery());
        selectRelatedTo(task.getRelatedTo());
        ActionKeywords.sleep(2);
        selectAssignee(task.getAssignee());
        ActionKeywords.sleep(2);

        addFollower(task.getFollower());
        ActionKeywords.sleep(2);

        addTag(task.getTag());
        enterTaskDescription(task.getDescription());
        clickSave();
        ActionKeywords.sleep(3);
        LogUtils.info("Filled all task information successfully: " + task.toString());
    }


}