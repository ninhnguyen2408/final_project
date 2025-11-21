package com.nin.dataproviders;

import helpers.CustomerDataHelper;
import org.testng.annotations.DataProvider;

public class CustomerDataProvider {

    /**
     * DataProvider chính - gọi Helper để lấy data
     * Helper tự động chọn nguồn data (Excel/JSON/Faker/HardCode)
     */
    @DataProvider(name = "customerData")
    public static Object[][] getCustomerData() {
        return CustomerDataHelper.getCustomerData();
    }
    
    /**
     * DataProvider cho VIP customers với Fakerl
     */
    @DataProvider(name = "vipCustomers")
    public static Object[][] getVipCustomers() {
        return CustomerDataHelper.getCustomerDataWithSpecificGroup("VIP", "USD");
    }
    
    /**
     * DataProvider cho Gold customers với Faker
     */
    @DataProvider(name = "goldCustomers")
    public static Object[][] getGoldCustomers() {
        return CustomerDataHelper.getCustomerDataWithSpecificGroup("Gold", "EUR");
    }
}
