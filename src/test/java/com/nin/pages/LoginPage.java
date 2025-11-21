package com.nin.pages;

import constants.ConfigData;
import keyworks.ActionKeywords;

import org.openqa.selenium.By;
import org.testng.Assert;
import drivers.DriverManager;
import utils.LogUtils;

public class LoginPage extends BasePage {

    private By title = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessageRequiredEmail = By.xpath("//div[normalize-space(text())='The Email Address field is required.']");
    private By errorMessageRequiredPassword = By.xpath("//div[normalize-space(text())='The Password field is required.']");
    private By invalidMessage = By.xpath("//div[normalize-space(text())='Invalid email or password']");


    private void openLoginPage(){
        ActionKeywords.openURL(ConfigData.URL);
        ActionKeywords.waitForPageLoaded();
        ActionKeywords.waitForElementVisible(buttonLogin);
        LogUtils.info("Opening browser and navigating to URL: " + ConfigData.URL);
    }

    private void enterEmail(String email) {
        ActionKeywords.sendKeys(inputEmail, email);
        LogUtils.info("Entered email: " + (email.isEmpty() ? "[EMPTY]" : email));
    }

    private void enterPassword(String password) {
        ActionKeywords.sendKeys(inputPassword, password);
        LogUtils.info("Entered password: " + (password.isEmpty() ? "[EMPTY]" : "****"));
    }

    private void clickLoginButton() {
        ActionKeywords.clickElement(buttonLogin);
        LogUtils.info("Clicked on Login button");
    }

    public void loginCMS(String email, String password) {
        openLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        ActionKeywords.waitForPageLoaded();
    }

    public DashboardPage loginCMS() {
        openLoginPage();
        enterEmail(ConfigData.Email);
        enterPassword(ConfigData.Password);
        clickLoginButton();
        ActionKeywords.waitForPageLoaded();
        return new DashboardPage();
    }

    // kiểm tra lại test case
    public void verifyLoginSuccess() {
        boolean check = ActionKeywords.checkElementExist(By.xpath("//span[normalize-space()='Dashboard']"), 5, 1000);
        Assert.assertTrue(check, "Login failed or Dashboard not displayed.");
    }

    public void verifyInvalidUsername() {
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        boolean check = ActionKeywords.checkElementExist(errorMessageRequiredEmail);
        Assert.assertTrue(check, "Error message for required email not displayed.");
    }

    public void verifyInvalidPassword() {
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        boolean check = ActionKeywords.checkElementDisplayed(errorMessageRequiredPassword);
        Assert.assertTrue(check, "Error message for required password not displayed.");
    }

    public void verifyBothEmpty(){
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        boolean emailErrorDisplayed = ActionKeywords.checkElementDisplayed(errorMessageRequiredEmail);
        boolean passwordErrorDisplayed = ActionKeywords.checkElementDisplayed(errorMessageRequiredPassword);
        Assert.assertTrue(emailErrorDisplayed, "The Email Address field is required.");
        Assert.assertTrue(passwordErrorDisplayed, "The Password field is required.");
        LogUtils.info("Both email and password required errors verified successfully");
        ActionKeywords.sleep(3);
    }
}
