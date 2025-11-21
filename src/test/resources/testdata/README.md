# Customer Test Data - Usage Guide

## 📚 Pattern: DataProvider + Helper

Framework này hỗ trợ **4 cách truyền test data**:

---

## 1️⃣ HARDCODE (Mặc định)
Data được hard-code trong `CustomerDataHelper.java`

**Chạy test:**
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer
```

---

## 2️⃣ EXCEL
Data đọc từ file `customer.xlsx`

**Setup:**
1. Tạo file Excel với cấu trúc (xem `README_EXCEL_TEMPLATE.md`)
2. Lưu vào: `src/test/resources/testdata/customer.xlsx`
3. Row 1: Header (Company, VatNumber, Phone, ...)
4. Row 2+: Test data

**Chạy test:**
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=EXCEL
```

---

## 3️⃣ JSON
Data đọc từ file `customer.json`

**Setup:**
File JSON đã có sẵn tại: `src/test/resources/testdata/customer.json`

**Chạy test:**
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=JSON
```

---

## 4️⃣ FAKER (Random Data)
Data được generate tự động bằng JavaFaker

**Chạy test:**
```bash
mvn test -Dtest=CustomerTest#testAddNewCustomer -DdataSource=FAKER
```

---

## 🎯 Test Methods

### `testAddNewCustomer`
- Sử dụng data source được chọn ở `@BeforeClass`
- Mặc định: HARDCODE
- Có thể thay đổi bằng `-DdataSource`

### `testAddVipCustomer`
- Luôn dùng Faker để generate VIP customer
- Không phụ thuộc vào `-DdataSource`

---

## 🔧 Thay đổi Data Source

### Cách 1: Command line (khuyến nghị)
```bash
mvn test -DdataSource=EXCEL
mvn test -DdataSource=JSON
mvn test -DdataSource=FAKER
```

### Cách 2: Sửa code trong CustomerTest.java
```java
@BeforeClass
public void setupDataSource() {
    CustomerDataHelper.setDataSource(DataSource.EXCEL); // Thay đổi ở đây
}
```

---

## 📂 Cấu trúc Files

```
src/test/
├── java/
│   ├── helpers/
│   │   └── CustomerDataHelper.java      ← Logic chọn data source
│   ├── com.nin.dataproviders/
│   │   └── CustomerDataProvider.java    ← DataProvider gọi Helper
│   ├── com.nin.tests/
│   │   └── CustomerTest.java            ← Test cases
│   └── utils/
│       ├── ExcelUtils.java              ← Đọc Excel
│       └── JsonUtils.java               ← Đọc JSON
└── resources/
    └── testdata/
        ├── customer.json                ← JSON data
        ├── customer.xlsx                ← Excel data (cần tạo)
        └── README.md                    ← File này
```

---

## ⚠️ Lưu ý

1. **Excel file**: Cần tự tạo theo template
2. **JSON file**: Đã có sẵn, có thể edit
3. **Faker**: Data random mỗi lần chạy
4. **Dependencies**: Đã add vào pom.xml (JavaFaker, Apache POI, Jackson)

---

## 🚀 Best Practices

- **Dev/Local**: Dùng FAKER (nhanh, không cần file)
- **CI/CD**: Dùng HARDCODE hoặc JSON (ổn định)
- **Regression**: Dùng EXCEL (dễ maintain cho tester)
- **Load Test**: Dùng FAKER (data đa dạng)

---

## 📝 Thêm Data Source mới

Để thêm nguồn data khác (CSV, Database):

1. Thêm enum vào `DataSource` trong `CustomerDataHelper`
2. Tạo method `getDataFromXXX()` trong `CustomerDataHelper`
3. Thêm case trong switch của `getCustomerData()`
4. Không cần sửa Test code!
