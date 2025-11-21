package com.nin.tests;


import com.nin.pages.DashboardPage;
import com.nin.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import listeners.TestListener;
import reports.AllureManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.ExtentTestManager;
import utils.LogUtils;

import io.qameta.allure.*;
import java.lang.reflect.Method;

@Listeners(TestListener.class)
@Epic("POS System")
@Feature("User Authentication")
public class LoginTest extends BaseTest {
    LoginPage LoginPage = new LoginPage();

    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        // Tạo test mới cho mỗi test case
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
    }

    @Test(priority = 1)
    @Story("Login successfully")
    @Description("Verify that user can login successfully with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginSuccess() {
        LogUtils.info("Executing testLoginSuccess - Testing successful login");
        LoginPage.loginCMS();
        LoginPage.verifyLoginSuccess();
        LogUtils.info("testLoginSuccess completed successfully");
        AllureManager.stepTestPassed("User logged in successfully");
    }

    @Test(priority = 2)
    @Story("Test login fail with invalid username")
    @Description("Verify that user can not login fail with invalid username")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testInvalidEmail() {
        LogUtils.info("Executing testInvalidEmail - Testing login fail with invalid username");
        LoginPage.loginCMS(ConfigData.InvalidEmail, ConfigData.Password);
        LoginPage.verifyInvalidUsername();
        LogUtils.info("testInvalidEmail completed successfully");
        AllureManager.stepTestPassed("Invalid username login attempt verified successfully");
    }

    @Test(priority = 3)
    @Story("Test login fail with invalid password")
    @Description("Verify that user can not login fail with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testInvalidPassword() {
        LogUtils.info("Executing testInvalidPassword - Testing login with invalid password");
        LoginPage.loginCMS(ConfigData.Email, ConfigData.InvalidPassword);
        LoginPage.verifyInvalidPassword();
        LogUtils.info("testInvalidPassword completed successfully");
        AllureManager.stepTestPassed("Invalid password login attempt verified successfully");
    }

    @Test(priority = 3)
    @Story("Test login fail with invalid password")
    @Description("Verify that user can not login fail with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testBothEmpty() {
        LogUtils.info("Executing testInvalidPassword - Testing login with invalid password");
        LoginPage.loginCMS("","");
        LoginPage.verifyBothEmpty();
        LogUtils.info("testInvalidPassword completed successfully");
        AllureManager.stepTestPassed("Invalid password login attempt verified successfully");
    }

}
