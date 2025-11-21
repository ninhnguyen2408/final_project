package reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import drivers.DriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureManager {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return "[" + LocalDateTime.now().format(DATE_FORMAT) + "] " + message;
    }

    // Screenshot attachments for Allure
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    // Screenshot with custom name
    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String screenshotName) {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    // Page source attachment
    @Attachment(value = "Page Source", type = "text/html")
    public static String savePageSource() {
        return DriverManager.getDriver().getPageSource();
    }

    // Browser logs attachment
    @Attachment(value = "Browser Console Logs", type = "text/plain")
    public static String saveBrowserLogs() {
        try {
            return DriverManager.getDriver().manage().logs().get("browser").getAll().toString();
        } catch (Exception e) {
            return "Unable to capture browser logs: " + e.getMessage();
        }
    }

    // JSON attachment
    @Attachment(value = "{0}", type = "application/json")
    public static String saveJsonData(String fileName, Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (Exception e) {
            return "Error serializing data: " + e.getMessage();
        }
    }

    // Browser information
    @Attachment(value = "Browser Information", type = "text/plain")
    public static String saveBrowserInfo() {
        WebDriver driver = DriverManager.getDriver();
        StringBuilder info = new StringBuilder();
        info.append("Browser: ").append(driver.getClass().getSimpleName()).append("\n");
        info.append("Current URL: ").append(driver.getCurrentUrl()).append("\n");
        info.append("Page Title: ").append(driver.getTitle()).append("\n");
        info.append("Window Size: ").append(driver.manage().window().getSize()).append("\n");
        info.append("Timestamp: ").append(LocalDateTime.now().format(DATE_FORMAT));
        return info.toString();
    }

    // Step methods for better reporting
    @Step("Opening URL: {url}")
    public static void stepOpenUrl(String url) {
        saveTextLog("Navigating to: " + url);
    }

    @Step("Clicking element: {elementName}")
    public static void stepClickElement(String elementName) {
        saveTextLog("Clicking on: " + elementName);
    }

    @Step("Entering text in {fieldName}: {text}")
    public static void stepEnterText(String fieldName, String text) {
        saveTextLog("Entering text '" + text + "' in field: " + fieldName);
    }

    @Step("Verifying {description}")
    public static void stepVerify(String description) {
        saveTextLog("Verification: " + description);
    }

    @Step("Test failed: {reason}")
    public static void stepTestFailed(String reason) {
        saveTextLog("Test FAILED: " + reason);
        saveScreenshotPNG("Failure Screenshot");
        saveBrowserInfo();
        savePageSource();
    }

    @Step("Test passed: {description}")
    public static void stepTestPassed(String description) {
        saveTextLog("Test PASSED: " + description);
    }
}

