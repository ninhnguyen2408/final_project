package helpers;

import com.github.javafaker.Faker;
import utils.ExcelUtils;
import utils.JsonUtils;
import utils.LogUtils;

import java.util.Locale;

public class CustomerDataHelper {
    
    // Enum để chọn nguồn data
    public enum DataSource {
        EXCEL, JSON, FAKER, HARDCODE
    }
    
    private static DataSource currentSource = DataSource.HARDCODE; // Mặc định
    private static final Faker faker = new Faker(new Locale("en-US"));
    
    /**
     * Setter để thay đổi nguồn data
     */
    public static void setDataSource(DataSource source) {
        currentSource = source;
        LogUtils.info("Data source changed to: " + source);
    }
    
    /**
     * Method chính - tự động chọn nguồn data
     */
    public static Object[][] getCustomerData() {
        LogUtils.info("Getting customer data from: " + currentSource);
        switch (currentSource) {
            case EXCEL:
                return getDataFromExcel();
            case JSON:
                return getDataFromJson();
            case FAKER:
                return getDataFromFaker();
            default:
                return getHardCodedData();
        }
    }
    
    /**
     * Đọc từ Excel
     */
    private static Object[][] getDataFromExcel() {
        String filePath = "src/test/resources/testdata/customer.xlsx";
        return ExcelUtils.getTestData(filePath, "Sheet1");
    }
    
    /**
     * Đọc từ JSON
     */
    private static Object[][] getDataFromJson() {
        String filePath = "src/test/resources/testdata/customer.json";
        return JsonUtils.getTestData(filePath);
    }
    
    /**
     * Generate từ Faker (random)
     */
    private static Object[][] getDataFromFaker() {
        int numberOfTests = 2; // Tạo 2 bộ data
        Object[][] data = new Object[numberOfTests][12];
        
        for (int i = 0; i < numberOfTests; i++) {
            data[i][0] = faker.company().name();
            data[i][1] = faker.number().digits(9);
            data[i][2] = faker.phoneNumber().cellPhone();
            data[i][3] = "https://" + faker.internet().domainName();
            data[i][4] = "VIP"; // Fixed
            data[i][5] = faker.address().streetAddress();
            data[i][6] = faker.address().city();
            data[i][7] = faker.address().state();
            data[i][8] = faker.address().zipCode();
            data[i][9] = "USD"; // Fixed
            data[i][10] = "English"; // Fixed
            data[i][11] = "United States"; // Fixed
            
            LogUtils.info("Generated Faker data #" + (i+1) + ": " + data[i][0]);
        }
        return data;
    }
    
    /**
     * Hard-coded data (fallback)
     */
    private static Object[][] getHardCodedData() {
        LogUtils.info("Using hard-coded data");
        return new Object[][] {
            {
                "ABC Technology Company",
                "123456789",
                "0939200600",
                "https://abctech.com",
                "VIP",
                "123 Nguyen Hue Street, District 1",
                "Ho Chi Minh",
                "Ho Chi Minh",
                "700000",
                "USD",
                "Vietnamese",
                "Vietnam"
            }
        };
    }
    
    /**
     * Hybrid: Faker + Fix một số field
     */
    public static Object[][] getCustomerDataWithSpecificGroup(String group, String currency) {
        LogUtils.info("Generating Faker data with Group: " + group + ", Currency: " + currency);
        return new Object[][] {
            {
                faker.company().name(),
                faker.number().digits(9),
                faker.phoneNumber().cellPhone(),
                "https://" + faker.internet().domainName(),
                group, // Fixed group
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                currency, // Fixed currency
                "Vietnamese",
                "Vietnam"
            }
        };
    }
}
