package testdata;

import models.Customer;

public class CustomerTestData {

    public static Customer getVIPCustomer() {
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

    public static Customer getGoldCustomer() {
        return new Customer.Builder()
                .company("XYZ Trading JSC")
                .vatNumber("987654321")
                .phone("0912345678")
                .website("https://xyztrading.vn")
                .group("Gold")
                .address("456 Le Loi Street")
                .city("Hanoi")
                .state("Hanoi Province")
                .zipCode("100000")
                .currency("EUR")
                .language("English")
                .country("Vietnam")
                .build();
    }

    public static Customer getStandardCustomer() {
        return new Customer.Builder()
                .company("Tech Startup Hub")
                .vatNumber("555666777")
                .phone("0901234567")
                .website("https://techstartup.vn")
                .group("Standard")
                .address("789 Tran Hung Dao Street")
                .city("Da Nang")
                .state("Da Nang Province")
                .zipCode("550000")
                .currency("VND")
                .language("Vietnamese")
                .country("Vietnam")
                .build();
    }

    public static Customer getSilverCustomer() {
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
                .currency("GBP")
                .language("English")
                .country("Vietnam")
                .build();
    }

    public static Customer getPlatinumCustomer() {
        return new Customer.Builder()
                .company("Elite Business Solutions")
                .vatNumber("999888777")
                .phone("0966123456")
                .website("https://elitebiz.vn")
                .group("Platinum")
                .address("555 Nguyen Trai Street")
                .city("Can Tho")
                .state("Can Tho Province")
                .zipCode("900000")
                .currency("JPY")
                .language("Vietnamese")
                .country("Vietnam")
                .build();
    }

    public static Customer getCustomerWithSpecialChars() {
        return new Customer.Builder()
                .company("Công ty TNHH Việt Nam")
                .vatNumber("0123456789")
                .phone("+84-939-200-600")
                .website("https://việt-nam.com")
                .group("VIP")
                .address("123 Đường Nguyễn Huệ, Quận 1")
                .city("Thành phố Hồ Chí Minh")
                .state("TP.HCM")
                .zipCode("700000")
                .currency("USD")
                .language("Vietnamese")
                .country("Vietnam")
                .build();
    }

    public static Customer getCustomerWithLongData() {
        return new Customer.Builder()
                .company("Very Long Company Name That Tests The Maximum Character Limit For This Field")
                .vatNumber("123456789012345")
                .phone("0939200600999888")
                .website("https://verylongdomainnameforboundarytesting.com")
                .group("VIP")
                .address("123 Very Long Street Name To Test The Maximum Character Limit, District 1")
                .city("Very Long City Name")
                .state("Very Long State Name")
                .zipCode("7000001234")
                .currency("USD")
                .language("Vietnamese")
                .country("Vietnam")
                .build();
    }

    public static Customer getCustomerWithMinimumData() {
        return new Customer.Builder()
                .company("Min Data Co")
                .vatNumber("1")
                .phone("1")
                .website("http://a.co")
                .group("Standard")
                .address("1")
                .city("A")
                .state("A")
                .zipCode("1")
                .currency("USD")
                .language("English")
                .country("Vietnam")
                .build();
    }

    public static Customer getDuplicateCustomer() {
        return getVIPCustomer();
    }

    public static Customer getCustomerWithInvalidPhone() {
        return new Customer.Builder()
                .company("Invalid Phone Test")
                .vatNumber("123456789")
                .phone("abc-xyz") // Invalid phone
                .website("https://test.com")
                .group("Standard")
                .address("123 Test Street")
                .city("Test City")
                .state("Test State")
                .zipCode("123456")
                .currency("USD")
                .language("English")
                .country("Vietnam")
                .build();
    }


    public static Customer getCustomerWithInvalidWebsite() {
        return new Customer.Builder()
                .company("Invalid Website Test")
                .vatNumber("123456789")
                .phone("0939200600")
                .website("not-a-valid-url") // Invalid website
                .group("Standard")
                .address("123 Test Street")
                .city("Test City")
                .state("Test State")
                .zipCode("123456")
                .currency("USD")
                .language("English")
                .country("Vietnam")
                .build();
    }

}
