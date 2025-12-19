package com.nin.tests;

import com.nin.pages.BasePage;
import com.nin.pages.CustomerPage;
import com.nin.pages.LoginPage;
import common.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import models.Customer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.AllureManager;
import reports.ExtentTestManager;
import testdata.CustomerTestData;
import utils.LogUtils;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
@Epic("CRM System")
@Feature("Customer Management")
public class CustomerTest extends BaseTest {

    private CustomerPage customerPage;
    private LoginPage loginPage;
    private BasePage basePage;

    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
        loginPage = new LoginPage();
        loginPage.loginCMS();
        
        basePage = new BasePage();
        basePage.clickMenuCustomers();
        
        customerPage = new CustomerPage();
    }
    
    @Test(priority = 1)
    @Story("Add new customer successfully")
    @Description("Verify that user can add a new VIP customer with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testAddNewCustomer() {
        LogUtils.info("Executing testAddNewCustomer - Testing add new customer");
        Customer customer = CustomerTestData.getAddNewCustomer1();
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        customerPage.verifyCustomerAddedSuccess();
        LogUtils.info("testAddNewCustomer completed");
    }

    @Test(priority = 2)
    @Story("Edit existing customer phone number")
    @Description("Verify that user can edit phone number of the customer created in testAddNewCustomer")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testEditCustomer() {
        LogUtils.info("Executing testEditCustomer - Editing phone number of customer from testAddNewCustomer");
        
        Customer originalCustomer = CustomerTestData.getAddNewCustomer1();
        String companyName = originalCustomer.getCompany();
        String newPhoneNumber = "0999888887";
        
        customerPage.editCustomerPhone(companyName, newPhoneNumber);
        customerPage.verifyCustomerEditedSuccess();
        
        LogUtils.info("testEditCustomer completed - Updated phone for customer: " + companyName);
    }

    @Test(priority = 3)
    @Story("Delete customer successfully")
    @Description("Verify that user can delete an existing customer")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testDeleteCustomer() {
        LogUtils.info("Executing testDeleteCustomer - Testing delete customer");
        
        String customerToDelete = "ABC Technology Company";
        
        customerPage.clickDeleteCustomer(customerToDelete);
        customerPage.confirmDeleteOk();

        customerPage.verifyCustomerDeleteSuccess();

        customerPage.clickDeleteCustomer(customerToDelete);
        customerPage.confirmDeleteOk();;
        customerPage.verifyCustomerDeleteSuccess();
        LogUtils.info("testDeleteCustomer completed");
    }
}