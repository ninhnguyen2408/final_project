package com.nin.tests;

import com.nin.dataproviders.CustomerDataProvider;
import com.nin.pages.BasePage;
import com.nin.pages.CustomerPage;
import com.nin.pages.LoginPage;
import common.BaseTest;
import helpers.CustomerDataHelper;
import helpers.CustomerDataHelper.DataSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    private CustomerPage customerPage;

    /**
     * Setup data source trước khi chạy test
     * Có thể thay đổi bằng cách truyền parameter: -DdataSource=EXCEL
     */
    @BeforeClass
    public void setupDataSource() {
        String dataSource = System.getProperty("dataSource", "HARDCODE");
        
        switch (dataSource.toUpperCase()) {
            case "EXCEL":
                CustomerDataHelper.setDataSource(DataSource.EXCEL);
                break;
            case "JSON":
                CustomerDataHelper.setDataSource(DataSource.JSON);
                break;
            case "FAKER":
                CustomerDataHelper.setDataSource(DataSource.FAKER);
                break;
            default:
                CustomerDataHelper.setDataSource(DataSource.HARDCODE);
        }
    }

    @BeforeMethod
    public void setupCustomerPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginCMS();
        
        BasePage basePage = new BasePage();
        basePage.clickMenuCustomers();
        
        customerPage = new CustomerPage();
    }

    /**
     * Test với data từ Helper (tự động chọn nguồn)
     */
    @Test(dataProvider = "customerData", dataProviderClass = CustomerDataProvider.class)
    public void testAddNewCustomer(String company, String vatNumber, String phone, String website,
                                   String group, String address, String city, String state,
                                   String zipCode, String currency, String language, String country) {
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(company, vatNumber, phone, website, group,
                                           address, city, state, zipCode, currency, language, country);
    }

    /**
     * Test với VIP customers (luôn dùng Faker)
     */
    @Test(dataProvider = "vipCustomers", dataProviderClass = CustomerDataProvider.class)
    public void testAddVipCustomer(String company, String vatNumber, String phone, String website,
                                   String group, String address, String city, String state,
                                   String zipCode, String currency, String language, String country) {
        customerPage.clickAddNewCustomer();
        customerPage.fillDataAddNewCustomer(company, vatNumber, phone, website, group,
                                           address, city, state, zipCode, currency, language, country);
    }

}
