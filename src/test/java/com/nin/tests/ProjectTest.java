package com.nin.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nin.pages.BasePage;
import com.nin.pages.LoginPage;
import com.nin.pages.ProjectPage;

import common.BaseTest;
import reports.ExtentTestManager;
import utils.LogUtils;

public class ProjectTest extends BaseTest {
    private ProjectPage projectPage;
    private LoginPage loginPage;
    private BasePage basePage;

    @BeforeMethod
    public void setup(Method method) {
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
        loginPage = new LoginPage();
        loginPage.loginCMS();
        
        basePage = new BasePage();
        basePage.clickMenuProject();
        
        projectPage = new ProjectPage();
    }

    @Test
    public void testAddNewProject() {
        LogUtils.info("Executing testAddNewProject - Testing add new project");
        projectPage.clickAddNewProject();
        projectPage.addNewProject("Project Alpha", "ABC Technology Company");
        projectPage.verifyCustomerAddedSuccess();
        LogUtils.info("testAddNewProject completed");
    }
    
}
