package com.nin.pages;

import io.qameta.allure.Step;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.LogUtils;

public class DashboardPage extends BasePage {

    // Locators - Dashboard Elements
    private By titleDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuSales = By.xpath("//span[normalize-space()='Sales']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By userProfile = By.xpath("//li[@class='icon header-user-profile']");
    private By btnLogout = By.xpath("//a[normalize-space()='Logout']");
    
    // Dashboard Statistics
    private By totalInvoiceAwaitingPayment = By.xpath("//span[contains(text(),'Invoices Awaiting Payment')]/parent::div//span[@class='text-dark']");
    private By totalConvertedLeads = By.xpath("//span[contains(text(),'Converted Leads')]/parent::div//span[@class='text-dark']");
    private By totalProjectsInProgress = By.xpath("//span[contains(text(),'Projects In Progress')]/parent::div//span[@class='text-dark']");
    private By totalTasksNotFinished = By.xpath("//span[contains(text(),'Tasks Not Finished')]/parent::div//span[@class='text-dark']");

    /**
     * Verify Dashboard hiển thị thành công sau login
     * Kiểm tra title "Dashboard" xuất hiện
     */
    @Step("Verify Dashboard displayed successfully")
    public void verifyDashboardDisplayed() {
        ActionKeywords.waitForPageLoaded();
        ActionKeywords.waitForElementVisible(titleDashboard, 10);
        boolean isDisplayed = ActionKeywords.checkElementDisplayed(titleDashboard);
        Assert.assertTrue(isDisplayed, "Dashboard title should be displayed after login");
        LogUtils.info("✓ Dashboard displayed successfully");
    }

    /**
     * Verify các menu chính hiển thị
     * Customers, Sales, Projects phải có
     */
    @Step("Verify main menus are displayed")
    public void verifyMainMenusDisplayed() {
        Assert.assertTrue(ActionKeywords.checkElementDisplayed(menuCustomers), "Customers menu should be visible");
        Assert.assertTrue(ActionKeywords.checkElementDisplayed(menuSales), "Sales menu should be visible");
        Assert.assertTrue(ActionKeywords.checkElementDisplayed(menuProjects), "Projects menu should be visible");
        LogUtils.info("✓ All main menus are displayed");
    }

    /**
     * Get thống kê "Invoices Awaiting Payment"
     * @return số lượng invoice đang chờ thanh toán
     */
    @Step("Get total invoices awaiting payment")
    public String getTotalInvoicesAwaitingPayment() {
        ActionKeywords.waitForElementVisible(totalInvoiceAwaitingPayment, 5);
        String total = ActionKeywords.getTextElement(totalInvoiceAwaitingPayment);
        LogUtils.info("Total Invoices Awaiting Payment: " + total);
        return total;
    }

    /**
     * Get thống kê "Converted Leads"
     * @return số lượng leads đã convert
     */
    @Step("Get total converted leads")
    public String getTotalConvertedLeads() {
        ActionKeywords.waitForElementVisible(totalConvertedLeads, 5);
        String total = ActionKeywords.getTextElement(totalConvertedLeads);
        LogUtils.info("Total Converted Leads: " + total);
        return total;
    }

    /**
     * Get thống kê "Projects In Progress"
     * @return số lượng project đang chạy
     */
    @Step("Get total projects in progress")
    public String getTotalProjectsInProgress() {
        ActionKeywords.waitForElementVisible(totalProjectsInProgress, 5);
        String total = ActionKeywords.getTextElement(totalProjectsInProgress);
        LogUtils.info("Total Projects In Progress: " + total);
        return total;
    }

    /**
     * Get thống kê "Tasks Not Finished"
     * @return số lượng task chưa hoàn thành
     */
    @Step("Get total tasks not finished")
    public String getTotalTasksNotFinished() {
        ActionKeywords.waitForElementVisible(totalTasksNotFinished, 5);
        String total = ActionKeywords.getTextElement(totalTasksNotFinished);
        LogUtils.info("Total Tasks Not Finished: " + total);
        return total;
    }

    /**
     * Logout khỏi hệ thống
     * Click vào user profile > chọn Logout
     */
    @Step("Logout from system")
    public void logout() {
        ActionKeywords.clickElement(userProfile);
        ActionKeywords.waitForElementVisible(btnLogout, 3);
        ActionKeywords.clickElement(btnLogout);
        ActionKeywords.waitForPageLoaded();
        LogUtils.info("✓ Logged out successfully");
    }

    /**
     * Navigate to Customers page từ Dashboard
     */
    @Step("Navigate to Customers page")
    public CustomerPage navigateToCustomers() {
        clickMenuCustomers();
        LogUtils.info("✓ Navigated to Customers page");
        return new CustomerPage();
    }

}
