package com.nin.tests;

import com.nin.pages.BasePage;
import com.nin.pages.TaskPage;
import com.nin.pages.LoginPage;
import common.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import models.Task;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.AllureManager;
import reports.ExtentTestManager;
import testdata.TaskTestData;
import utils.LogUtils;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
@Epic("CRM System")
@Feature("Task Management")
public class TaskTest extends BaseTest {

    private TaskPage taskPage;
    private LoginPage loginPage;
    private BasePage basePage;

    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
        loginPage = new LoginPage();
        loginPage.loginCMS();

        basePage = new BasePage();
        basePage.clickMenuTask();

        taskPage = new TaskPage();
    }
    
    @Test(priority = 1)
    @Story("Add new task successfully")
    @Description("Verify that user can add a new task with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testAddNewTask() {
        LogUtils.info("Executing testAddNewTask - Testing add new task");
        Task task = TaskTestData.getAddNewTask1();
        taskPage.clickAddNewTask();
        taskPage.fillDataAddNewTask(task);
        
    }
}
