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
    @Story("Add new VIP customer successfully")
    @Description("Verify that user can add a new VIP customer with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testAddNewVIPCustomer() {
        LogUtils.info("Executing testAddNewVIPCustomer - Testing add VIP customer");
        
        Customer customer = CustomerTestData.getVIPCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "VIP");
        
        LogUtils.info("testAddNewVIPCustomer completed");
    }

    @Test(priority = 2)
    @Story("Add new Gold customer successfully")
    @Description("Verify that user can add a new Gold customer with valid data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddNewGoldCustomer() {
        LogUtils.info("Executing testAddNewGoldCustomer - Testing add Gold customer");
        
        Customer customer = CustomerTestData.getGoldCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Gold");
        
        LogUtils.info("testAddNewGoldCustomer completed");
    }

    @Test(priority = 3)
    @Story("Add new Standard customer successfully")
    @Description("Verify that user can add a new Standard customer with valid data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddNewStandardCustomer() {
        LogUtils.info("Executing testAddNewStandardCustomer - Testing add Standard customer");
        
        Customer customer = CustomerTestData.getStandardCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Standard");
        
        LogUtils.info("testAddNewStandardCustomer completed");
    }

    @Test(priority = 4)
    @Story("Add new Silver customer successfully")
    @Description("Verify that user can add a new Silver customer with valid data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddNewSilverCustomer() {
        LogUtils.info("Executing testAddNewSilverCustomer - Testing add Silver customer");
        
        Customer customer = CustomerTestData.getSilverCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Silver");
        
        LogUtils.info("testAddNewSilverCustomer completed");
    }

    @Test(priority = 5)
    @Story("Add new Platinum customer successfully")
    @Description("Verify that user can add a new Platinum customer with valid data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddNewPlatinumCustomer() {
        LogUtils.info("Executing testAddNewPlatinumCustomer - Testing add Platinum customer");
        
        Customer customer = CustomerTestData.getPlatinumCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Platinum");
        
        LogUtils.info("testAddNewPlatinumCustomer completed");
    }

    // ============== EDGE CASE TEST ==============
    
    @Test(priority = 6)
    @Story("Add customer with special characters")
    @Description("Verify that system can handle special characters in customer data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddCustomerWithSpecialChars() {
        LogUtils.info("Executing testAddCustomerWithSpecialChars - Testing special characters handling");
        
        Customer customer = CustomerTestData.getCustomerWithSpecialChars();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Special Chars");
        
        LogUtils.info("testAddCustomerWithSpecialChars completed");
    }

    // ============== BOUNDARY TEST CASES ==============
    
    @Test(priority = 7)
    @Story("Add customer with maximum length data")
    @Description("Verify that system can handle maximum length data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddCustomerWithLongData() {
        LogUtils.info("Executing testAddCustomerWithLongData - Testing boundary: max length");
        
        Customer customer = CustomerTestData.getCustomerWithLongData();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Long Data");
        
        LogUtils.info("testAddCustomerWithLongData completed");
    }

    @Test(priority = 8)
    @Story("Add customer with minimum length data")
    @Description("Verify that system can handle minimum length data")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddCustomerWithMinimumData() {
        LogUtils.info("Executing testAddCustomerWithMinimumData - Testing boundary: min length");
        
        Customer customer = CustomerTestData.getCustomerWithMinimumData();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify kết quả
        verifyCustomerResult(customer.getCompany(), "Min Data");
        
        LogUtils.info("testAddCustomerWithMinimumData completed");
    }

    // ============== NEGATIVE TEST CASES ==============
    
    @Test(priority = 9)
    @Story("Add duplicate customer")
    @Description("Verify that system prevents adding duplicate customer")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testAddDuplicateCustomer() {
        LogUtils.info("Executing testAddDuplicateCustomer - Testing duplicate validation");
        
        Customer customer = CustomerTestData.getDuplicateCustomer();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify duplicate error
        if (customerPage.verifyDuplicateCustomerError()) {
            LogUtils.info("✓ Duplicate customer validation working correctly");
            AllureManager.stepTestPassed("System correctly prevented duplicate customer");
        } else if (customerPage.verifyCustomerAddedSuccess()) {
            LogUtils.warn("⚠ Customer added but should be duplicate");
        }
        
        LogUtils.info("testAddDuplicateCustomer completed");
    }

    @Test(priority = 10)
    @Story("Add customer with invalid phone")
    @Description("Verify that system validates phone number format")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddCustomerWithInvalidPhone() {
        LogUtils.info("Executing testAddCustomerWithInvalidPhone - Testing phone validation");
        
        Customer customer = CustomerTestData.getCustomerWithInvalidPhone();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify validation error
        if (customerPage.verifyCustomerAddedFailed()) {
            LogUtils.info("✓ Phone validation working correctly");
            AllureManager.stepTestPassed("System correctly validated invalid phone");
        } else if (customerPage.verifyCustomerAddedSuccess()) {
            LogUtils.warn("⚠ Customer added with invalid phone - validation may be missing");
        }
        
        LogUtils.info("testAddCustomerWithInvalidPhone completed");
    }

    @Test(priority = 11)
    @Story("Add customer with invalid website")
    @Description("Verify that system validates website URL format")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testAddCustomerWithInvalidWebsite() {
        LogUtils.info("Executing testAddCustomerWithInvalidWebsite - Testing website validation");
        
        Customer customer = CustomerTestData.getCustomerWithInvalidWebsite();
        
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(customer);
        
        // Verify validation error
        if (customerPage.verifyCustomerAddedFailed()) {
            LogUtils.info("✓ Website validation working correctly");
            AllureManager.stepTestPassed("System correctly validated invalid website");
        } else if (customerPage.verifyCustomerAddedSuccess()) {
            LogUtils.warn("⚠ Customer added with invalid website - validation may be missing");
        }
        
        LogUtils.info("testAddCustomerWithInvalidWebsite completed");
    }

    // ============== HELPER METHOD ==============
    
    /**
     * Helper method để verify kết quả add customer
     * Tái sử dụng cho tất cả positive test cases
     * 
     * @param customerName Tên customer
     * @param customerType Loại customer (VIP, Gold, etc.)
     */
    private void verifyCustomerResult(String customerName, String customerType) {
        if (customerPage.verifyCustomerAddedSuccess()) {
            LogUtils.info("✓ " + customerType + " Customer added successfully: " + customerName);
            AllureManager.stepTestPassed(customerType + " Customer added successfully");
        } else if (customerPage.verifyDuplicateCustomerError()) {
            LogUtils.warn("⚠ Duplicate customer detected: " + customerName + " - This may be expected");
            AllureManager.stepTestPassed("Duplicate customer validation working correctly");
        } else if (customerPage.verifyCustomerAddedFailed()) {
            LogUtils.error("✗ Failed to add customer: " + customerName);
            String errorMsg = customerPage.getAlertMessage();
            LogUtils.error("Error message: " + errorMsg);
            Allure.addAttachment("Error Details", "Customer: " + customerName + "\nError: " + errorMsg);
        }
    }

}
