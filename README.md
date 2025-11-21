# Final Project - Selenium Test Automation Framework

## 📋 Mô tả dự án
Framework tự động hóa kiểm thử Web sử dụng Selenium WebDriver, TestNG, và Maven.

---

## 🛠️ Công nghệ sử dụng
- **Java**: 17
- **Selenium WebDriver**: 4.38.0
- **TestNG**: 7.11.0
- **Maven**: Build tool
- **Allure Report**: Test reporting
- **ExtentReports**: Test reporting
- **JavaFaker**: Generate random test data
- **Apache POI**: Read Excel files
- **Jackson**: Parse JSON files

---

## 📁 Cấu trúc dự án

```
final_project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── constants/         # Các hằng số, config
│   │   │   ├── drivers/           # WebDriver manager
│   │   │   ├── helpers/           # Helper classes
│   │   │   ├── keyworks/          # Action keywords (Selenium wrapper)
│   │   │   ├── reports/           # Report managers
│   │   │   └── utils/             # Utility classes
│   │   └── resources/
│   │       └── log4j2.properties  # Logging config
│   └── test/
│       ├── java/
│       │   ├── com.nin.pages/     # Page Object Model
│       │   ├── com.nin.tests/     # Test cases
│       │   ├── com.nin.dataproviders/  # TestNG DataProviders
│       │   ├── common/            # Base test class
│       │   ├── helpers/           # Test data helpers
│       │   ├── listeners/         # TestNG listeners
│       │   └── utils/             # Test utilities
│       └── resources/
│           ├── configs/           # Test configs
│           └── testdata/          # Test data (JSON, Excel)
├── report/                        # ExtentReport output
├── allure-results/                # Allure report data
├── exports/logs/                  # Application logs
├── pom.xml                        # Maven dependencies
└── .gitignore                     # Git ignore rules
```

---

## 🚀 Hướng dẫn cài đặt trên IntelliJ IDEA

### 1. Cài đặt Prerequisites

#### ✅ Java 17
- Download: [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- Cài đặt và set `JAVA_HOME` environment variable

#### ✅ Maven
- IntelliJ đã tích hợp Maven (không cần cài thêm)
- Hoặc download: [Apache Maven](https://maven.apache.org/download.cgi)

#### ✅ IntelliJ IDEA
- Download: [IntelliJ IDEA Community/Ultimate](https://www.jetbrains.com/idea/download/)

---

### 2. Clone Project

```bash
git clone https://github.com/ninhnguyen2408/final_project.git
cd final_project
```

---

### 3. Mở Project trong IntelliJ

**Cách 1: Mở từ Welcome Screen**
1. Mở IntelliJ IDEA
2. Click **Open**
3. Chọn folder `final_project`
4. Click **OK**

**Cách 2: Từ Menu**
1. `File` → `Open...`
2. Chọn folder `final_project`
3. Click **OK**

IntelliJ sẽ tự động detect Maven project và import dependencies.

---

### 4. Cấu hình Project

#### ✅ Set Java SDK
1. `File` → `Project Structure` (Ctrl+Alt+Shift+S)
2. `Project` → `SDK` → Chọn **Java 17**
3. `Project language level` → Chọn **17**
4. Click **Apply** → **OK**

#### ✅ Reload Maven Dependencies
1. Mở tab **Maven** (bên phải)
2. Click icon **Reload All Maven Projects** (🔄)
3. Chờ Maven download dependencies

#### ✅ Enable Annotation Processing (cho Lombok)
1. `File` → `Settings` (Ctrl+Alt+S)
2. `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`
3. Check ✅ **Enable annotation processing**
4. Click **Apply** → **OK**

---

### 5. Cài đặt Plugin (Khuyến nghị)

`File` → `Settings` → `Plugins` → Tìm và cài đặt:

- ✅ **TestNG** - Chạy test TestNG
- ✅ **Allure** - Xem Allure report
- ✅ **Maven Helper** - Quản lý Maven dependencies

---

### 6. Cấu hình Browsers

Framework hỗ trợ: Chrome, Firefox, Edge

**WebDriverManager tự động download driver** - Không cần cài thủ công!

---

## ▶️ Chạy Tests

### Cách 1: Chạy từ Test Class

1. Mở file test: `src/test/java/com/nin/tests/CustomerTest.java`
2. Click chuột phải vào class/method
3. Chọn **Run 'CustomerTest'** hoặc **Run 'testAddNewCustomer()'**

### Cách 2: Chạy từ Maven

**Trong IntelliJ:**
1. Mở tab **Maven** (bên phải)
2. `final_project` → `Lifecycle` → Double-click **test**

**Từ Terminal trong IntelliJ:**
```bash
mvn clean test
```

### Cách 3: Chạy với TestNG XML

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Xem Test Reports

### Allure Report

**Cách 1: Command Line**
```bash
# Generate và mở report
mvn allure:serve

# Hoặc generate vào folder
mvn allure:report
```

**Cách 2: IntelliJ Plugin**
1. Click tab **Allure** (dưới cùng)
2. Click **Serve Report**

### ExtentReports

Mở file: `report/ExtentReport/ExtentReport.html` bằng browser

---

## 🎯 Chạy Test với Data Sources khác nhau

### 1. Hard-coded Data (Default)
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer
```

### 2. Random Data (Faker)
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=FAKER
```

### 3. Excel Data
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=EXCEL
```

### 4. JSON Data
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=JSON
```

---

## 🔧 Cấu hình

### File Config: `src/test/resources/configs/configs.properties`

```properties
# Browser config
BROWSER=chrome
HEADLESS=false

# Application URL
url=https://crm.anhtester.com/admin/authentication

# Credentials
email=admin@example.com
password=123456
```

### Thay đổi Browser khi chạy test:
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

---

## 📝 Viết Test Case mới

### 1. Tạo Page Object

**File:** `src/test/java/com/nin/pages/NewPage.java`
```java
public class NewPage {
    private By inputField = By.id("field");
    
    public void enterData(String data) {
        ActionKeywords.sendKeys(inputField, data);
    }
}
```

### 2. Tạo Test Class

**File:** `src/test/java/com/nin/tests/NewTest.java`
```java
public class NewTest extends BaseTest {
    
    @Test
    public void testNewFeature() {
        NewPage page = new NewPage();
        page.enterData("test data");
    }
}
```

---

## 🐛 Troubleshooting

### ❌ Lỗi: Maven dependencies không download

**Giải pháp:**
```bash
# Xóa Maven cache và reimport
mvn clean
mvn dependency:purge-local-repository
mvn clean install
```

Trong IntelliJ: `Maven` tab → Right-click project → `Reload project`

### ❌ Lỗi: Java version không đúng

**Giải pháp:**
- Kiểm tra: `File` → `Project Structure` → `SDK` = Java 17
- Terminal: `java -version` phải là 17

### ❌ Lỗi: Cannot find ChromeDriver

**Giải pháp:**
- WebDriverManager tự động download
- Kiểm tra internet connection
- Xóa cache: `C:\Users\{user}\.m2\repository\webdriver`

### ❌ Lỗi: Test chạy nhưng không thấy report

**Giải pháp:**
```bash
# Allure: Cài Allure command line
mvn allure:serve

# ExtentReport: Mở file HTML trực tiếp
report/ExtentReport/ExtentReport.html
```

---

## 📚 Tài liệu tham khảo

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)
- [Allure Report](https://docs.qameta.io/allure/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 👥 Tác giả

- **Ninh Nguyen** - [GitHub](https://github.com/ninhnguyen2408)

---

## 📄 License

This project is for educational purposes.

---

## 🎓 Notes

- Framework sử dụng **Page Object Model (POM)** pattern
- Test data được quản lý qua **DataProvider + Helper** pattern
- Hỗ trợ **parallel execution** với TestNG
- Tích hợp **CI/CD ready** với Maven

---

**Happy Testing! 🚀**
