package com.nin.tests;

import com.nin.pages.DashboardPage;
import com.nin.pages.LoginPage;
import common.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.AllureManager;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
@Epic("CRM System")
@Feature("Dashboard Management")
public class DashboardTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMS();
    }

    @Test(priority = 1)
    @Story("Verify Dashboard after login")
    @Description("Verify that Dashboard page displays correctly after successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testDashboardDisplayed() {
        LogUtils.info("Executing testDashboardDisplayed - Verify Dashboard loads successfully");
        
        dashboardPage.verifyDashboardDisplayed();
        
        LogUtils.info("testDashboardDisplayed completed successfully");
        AllureManager.stepTestPassed("Dashboard displayed successfully after login");
    }

    @Test(priority = 2)
    @Story("Verify main navigation menus")
    @Description("Verify that all main menus (Customers, Sales, Projects) are displayed")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testMainMenusDisplayed() {
        LogUtils.info("Executing testMainMenusDisplayed - Verify main navigation menus");
        
        dashboardPage.verifyMainMenusDisplayed();
        
        LogUtils.info("testMainMenusDisplayed completed successfully");
        AllureManager.stepTestPassed("All main menus are displayed correctly");
    }

    @Test(priority = 3)
    @Story("Verify Dashboard statistics")
    @Description("Verify that Dashboard statistics are displayed and can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testDashboardStatistics() {
        LogUtils.info("Executing testDashboardStatistics - Get Dashboard statistics");
        
        // Get các thống kê từ Dashboard
        String invoices = dashboardPage.getTotalInvoicesAwaitingPayment();
        String leads = dashboardPage.getTotalConvertedLeads();
        String projects = dashboardPage.getTotalProjectsInProgress();
        String tasks = dashboardPage.getTotalTasksNotFinished();
        
        // Log ra để show trong report
        LogUtils.info("📊 Dashboard Statistics:");
        LogUtils.info("├─ Invoices Awaiting Payment: " + invoices);
        LogUtils.info("├─ Converted Leads: " + leads);
        LogUtils.info("├─ Projects In Progress: " + projects);
        LogUtils.info("└─ Tasks Not Finished: " + tasks);
        
        // Attach vào Allure Report
        Allure.addAttachment("Dashboard Statistics", 
            "Invoices: " + invoices + "\n" +
            "Leads: " + leads + "\n" +
            "Projects: " + projects + "\n" +
            "Tasks: " + tasks
        );
        
        LogUtils.info("testDashboardStatistics completed successfully");
        AllureManager.stepTestPassed("Dashboard statistics retrieved successfully");
    }

    @Test(priority = 4)
    @Story("Navigate from Dashboard to Customers")
    @Description("Verify that user can navigate from Dashboard to Customers page")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testNavigateToCustomers() {
        LogUtils.info("Executing testNavigateToCustomers - Navigate to Customers page");
        
        dashboardPage.navigateToCustomers();
        
        LogUtils.info("testNavigateToCustomers completed successfully");
        AllureManager.stepTestPassed("Successfully navigated to Customers page");
    }

}
