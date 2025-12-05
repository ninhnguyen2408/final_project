package testdata;

import models.Customer;

public class CustomerTestData {

    public static Customer getAddNewCustomer1() {
        return new Customer.Builder()
                .company("ABC Technology Company")
                .vatNumber("123456789")
                .phone("0939200600")
                .website("https://abctech.com")
                .group("VIP")
                .address("123 Nguyen Hue Street")
                .city("Ho Chi Minh")
                .state("HCM Province")
                .zipCode("700000")
                .currency("USD")
                .language("Vietnamese")
                .country("Vietnam")
                .build();
    }

    public static Customer getAddNewCustomer2() {
        return new Customer.Builder()
                .company("Global Import Export Ltd")
                .vatNumber("111222333")
                .phone("0987654321")
                .website("https://globalimex.com")
                .group("Silver")
                .address("321 Hai Ba Trung Street")
                .city("Hue")
                .state("Thua Thien Hue Province")
                .zipCode("530000")
                .currency("EUR")
                .language("English")
                .country("Brazil")
                .build();
    }

}
