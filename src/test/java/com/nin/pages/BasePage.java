package com.nin.pages;


import keyworks.ActionKeywords;
import org.openqa.selenium.By;

public class BasePage {


    protected By menuDashboard = By.xpath("//span[normalize-space(text())='Dashboard']");
    protected By menuCustomers = By.xpath("//span[normalize-space(text())='Customers']");
    protected By menuConTracts = By.xpath("//span[normalize-space(text())='Contacts']");
    protected By menuProjects = By.xpath("//span[normalize-space(text())='Projects']");
    protected By menuTasks = By.xpath("//span[normalize-space(text())='Tasks']");
    protected By buttonLogout = By.xpath("//div[normalize-space()='Logout']/a");

    public void clickMenuDashboard() {
        ActionKeywords.waitForElementVisible(menuDashboard);
        ActionKeywords.clickElement(menuDashboard);
    }

    public void clickMenuCustomers() {
        ActionKeywords.waitForElementVisible(menuCustomers);
        ActionKeywords.clickElement(menuCustomers);
    }

    public void clickMenuContracts() {
        ActionKeywords.waitForElementVisible(menuConTracts);
        ActionKeywords.clickElement(menuConTracts);
    }

    public void clickMenuProject() {
        ActionKeywords.waitForElementVisible(menuProjects);
        ActionKeywords.clickElement(menuProjects);
    }

    public void clickMenuTask() {
        ActionKeywords.waitForElementVisible(menuTasks);
        ActionKeywords.clickElement(menuTasks);
    }

    public void clickLogOut() {
        ActionKeywords.clickElement(buttonLogout);
    }

}