package keyworks;

import com.aventstack.extentreports.Status;
import drivers.DriverManager;
import reports.AllureManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.ExtentTestManager;
import utils.LogUtils;
import io.qameta.allure.Step;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class ActionKeywords {
    private static int Timeout = 20;


    // Log console
    public static void logConsole(Object message) {
        LogUtils.info(message);
    }

    // Open URL
    @Step("Opening URL: {URL}")
    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        logConsole("Navigate to: " + URL);
        ExtentTestManager.logMessage(Status.INFO, "Open URL: " + URL);
        AllureManager.stepOpenUrl(URL);
    }
    // Get current URL
    @Step("Get current: {URL}")
    public static String getCurrentURL() {
        String currentURL = DriverManager.getDriver().getCurrentUrl();
        logConsole("Current URL: " + currentURL);
        ExtentTestManager.logMessage(Status.INFO, "Current URL: ");
        AllureManager.saveTextLog("URL: " + currentURL);
        return currentURL;
    }


    // Get WebElement
    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    // Get list WebElement
    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    // Get text
    @Step("Getting text from element: {by}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        String text = getWebElement(by).getText();
        logConsole("Get text of element " + by + " is " + text);
        return text;
    }

    // Get Attribute element
    @Step("Getting attribute '{attribute}' from element: {by}")
    public static String getAttributeElement(By by, String attribute) {
        waitForElementVisible(by);
        String value = getWebElement(by).getAttribute(attribute);
        logConsole("Get attribute of element " + by + " is " + value);
        return value;
    }

    // Check element exist
    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            logConsole("Element " + by + "exist");
            return true;
        } else {
            logConsole("Element " + by + "NOT exist");
            return false;
        }
    }

    // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
    public static boolean checkElementExist(By by, int maxRetries, int waitTimeMillis) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement element = getWebElement(by);
                if (element != null) {
                    LogUtils.info("Tìm thấy phần tử ở lần thử thứ " + (retryCount + 1));
                    return true; // Phần tử được tìm thấy
                }
            } catch (NoSuchElementException e) {
                LogUtils.warn("Không tìm thấy phần tử. Thử lại lần " + (retryCount + 1));
                retryCount++;
                try {
                    Thread.sleep(waitTimeMillis); // Chờ trước khi thử lại
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        logConsole("Không tìm thấy phần tử sau " + maxRetries + " lần thử.");
        return false;
    }


    // Check element Displayed
    public static boolean checkElementDisplayed(By by, String msg) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isDisplayed();
        return check;
    }

    public static boolean checkElementDisplayed(By by) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isDisplayed();
        return check;
    }


    // Check element Enable
    public static boolean checkElementEnable(By by) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isEnabled();
        return check;
    }

    // Check search table by column (xử lý cột trên 1 trang)
    public static void checkContainsValueOnTableByColumn(int column, String value) {
        List<WebElement> totalRows = getWebElements(By.xpath("//tbody/tr"));
        logConsole("Number of results for keywords (" + value + "): " + totalRows.size());

        if (totalRows.size() < 1) {
            logConsole("Not found value: " + value);
        } else {
            for (int i = 1; i <= totalRows.size(); i++) {
                boolean res = false;
                WebElement title = getWebElement(By.xpath("//tbody/tr[" + i + "]/td[" + column + "]"));
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                js.executeScript("arguments[0].scrollIntoView(false);", title);
                res = title.getText().toUpperCase().contains(value.toUpperCase());
                logConsole("Row " + i + ": " + title.getText());
                Assert.assertTrue(res, "Row " + i + " (" + title.getText() + ")" + "NOT contains the search value: " + value);
            }
        }
    }

    // Click element
    @Step("Clicking element: {by}")
    public static void clickElement(By by) {
        waitForElementClickable(by);
        getWebElement(by).click();
        logConsole("Click on element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Click on element " + by);
        AllureManager.stepClickElement(by.toString());
    }

    // Click element  với seconds là giá trị thay đổi import org.testng.Assert;
    @Step("Clicking element: {by} with timeout {seconds}s")
    public static void clickElement(By by, int seconds) {
        waitForElementClickable(by, seconds);
        getWebElement(by).click();
        logConsole("Click on element  " + by + " with timeout is " + seconds + " seconds ");
        ExtentTestManager.logMessage(Status.INFO, "Click on element " + by + " with timeout is " + seconds + " seconds ");
    }

    // Clear and sendKeys
    @Step("Clearing and entering text '{text}' in field: {by}")
    public static void clearAndSendKeys(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        sleep(2);
        getWebElement(by).sendKeys(text);
        logConsole("Clear and Set text " + text + " on input " + by);
        ExtentTestManager.logMessage(Status.INFO, "Clear and Set text " + text + " on input " + by);
    }

    // sendKeys
    @Step("Entering text '{text}' in field: {by}")
    public static void sendKeys(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text);
        logConsole("Set text " + text + " on input " + by);
        ExtentTestManager.logMessage(Status.INFO, "Set text " + text + " on input " + by);
        AllureManager.stepEnterText(by.toString(), text);
    }

    // sendKeys với seconds là giá trị thay đổi
    @Step("Entering text '{text}' in field: {by} with timeout {seconds}s")
    public static void sendKeys(By by, String text, int seconds) {
        waitForElementVisible(by, seconds);
        getWebElement(by).sendKeys(text);
        logConsole("Set text " + text + " on input " + by + " with timeout is " + seconds + " seconds ");
        ExtentTestManager.logMessage(Status.INFO, "Set text " + text + " on input " + by + "with timeout is " + seconds + " seconds");
    }

    //Keys.ENTER
    @Step("Entering text '{text}' and pressing ENTER on field: {by}")
    public static void setTextAndKeysENTER(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text, Keys.ENTER);
        waitForPageLoaded();
        sleep(2);
        logConsole("Set text: " + text + " on element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Set text: " + text + " on element " + by + "sendKeys Keys.ENTER");
    }

    // Set text and Keys
    @Step("Entering text '{text}' and pressing key '{key}' on field: {by}")
    public static void setTextAndKeys(By by, String text, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(text, key);
        logConsole("Set text: " + text + " on element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Set text " + text + " set keys " + key + " on element " + by);
    }

    // Verify Equals
    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        logConsole("Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    // Assert Equals
    @Step("Asserting equals: Expected='{expected}', Actual='{actual}'")
    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        logConsole("Assert equals " + actual + " and " + expected);
        try {
            Assert.assertEquals(actual, expected, message);
            AllureManager.stepVerify("Assertion PASSED: " + message);
        } catch (AssertionError e) {
            AllureManager.stepTestFailed(message + ". Expected: '" + expected + "', Actual: '" + actual + "'");
            throw e;
        }
    }

    // Verify Contains
    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        logConsole("Verify contains " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    //Assert Contains
    @Step("Asserting '{actual}' contains '{expected}'")
    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        logConsole("Assert contains " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    // Get image
    @Step("Selecting and uploading image with search text: '{textSearch}'")
    public static void getImage(By browse, By search, String textSearch, By image, By buttonAdd) {
        clickElement(browse);
        setTextAndKeysENTER(search, textSearch);
        sleep(3);
        clickElement(image);
        clickElement(buttonAdd);
        logConsole("Upload image on " + browse);
    }

    // upload file
    @Step("Uploading file: '{filePath}' to element: {by}")
    public static void uploadFileWithLocalForm(By by, String filePath) {
        waitForPageLoaded();
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(getWebElement(by)).click().perform();  //Click to open form upload
        sleep(3);
        // Create Robot class
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path to Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        // Press Control+V to paste
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        // Release the Control V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(2000);
        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        logConsole("Upload File with Local Form: " + filePath);
        if (ExtentTestManager.getTest() != null) {
            logConsole("Upload File with Local Form: " + filePath);
        }
        AllureManager.saveTextLog("Upload File with Local Form: " + filePath);
    }

    //Static droplist
    @Step("Selecting '{optionText}' from static dropdown: {dropdownBy}")
    public static void selectStaticDropdown(By dropdownBy, String optionText) {
        Select dropdown = new Select(getWebElement(dropdownBy));
        dropdown.selectByVisibleText(optionText);
        logConsole("Select " + optionText + " from static dropdown " + dropdownBy);
        ExtentTestManager.logMessage(Status.INFO, "Select " + optionText + " from static dropdown " + dropdownBy);
    }

    //Dynamic dropdown (Show only 1 search result)
    @Step("Selecting '{searchText}' from dynamic dropdown: {dropdownBy}")
    public static void selectDynamicDropdown(By dropdownBy, By searchBy, String searchText) {
        clickElement(dropdownBy);
        waitForPageLoaded();
        setTextAndKeysENTER(searchBy, searchText);
        sleep(4);
        logConsole("Select " + searchText + " from dynamic dropdown " + dropdownBy);
    }

    //*********HTML5, Popup window, Alert******

    public static Boolean verifyHTML5RequiredField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Boolean verifyRequired = (Boolean) js.executeScript("return arguments[0].required;", getWebElement(by));
        logConsole("check HTML5 Required Field exists " + by);
        return verifyRequired;
    }

    public static String getHTML5MessageField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String message = (String) js.executeScript("return arguments[0].validationMessage;", getWebElement(by));
        return message;
    }

    public static Boolean verifyHTML5ValidValueField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Boolean verifyValid = (Boolean) js.executeScript("return arguments[0].validity.valid;", getWebElement(by));
        return verifyValid;
    }

    // Chuyển sang tab mới với URL
    @Step("Switching to window/tab with URL: {url}")
    public static void switchToWindowOrTabByUrl(String url) {
        //Store the ID of the original window
        String originalWindow = DriverManager.getDriver().getWindowHandle();
        waitForPageLoaded();

        //Loop through until we find a new window handle
        for (String windowHandle : DriverManager.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                DriverManager.getDriver().switchTo().window(windowHandle);
                if (DriverManager.getDriver().getCurrentUrl().equals(url)) {
                    break;
                }
            }
        }
    }

    // getText alert
    @Step("Getting text from alert")
    public static String getTextAlert() {
        sleep(2);
        logConsole("Get text ion alert: " + DriverManager.getDriver().switchTo().alert().getText());
        return DriverManager.getDriver().switchTo().alert().getText();
    }

    // Accept alert (click OK)
    @Step("Accepting alert/confirmation dialog")
    public static void acceptAlert() {
        sleep(1);
        DriverManager.getDriver().switchTo().alert().accept();
        logConsole("Accepted alert/confirmation dialog");
    }

    // Dismiss alert (click Cancel)
    @Step("Dismissing alert/confirmation dialog")
    public static void dismissAlert() {
        sleep(1);
        DriverManager.getDriver().switchTo().alert().dismiss();
        logConsole("Dismissed alert/confirmation dialog");
    }


    // ************* Javascript Executor, Actions class, Robot class ************

    //cuộn chuột đến vị trí element (đối tượng By)
    public static void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = getWebElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // cuộn chuột đến vị trí element (đối tượng WebElement)
    public static void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    // di chuyển chuột đến vị trí element
    @Step("Moving mouse to element: {toElement}")
    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    //di chuyển chuột đến vị trí element
    @Step("Hovering mouse over element: {by}")
    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // kéo thả chuột
    @Step("Dragging element {fromElement} and dropping to {toElement}")
    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    // nhấn và giữ chuột
    public static boolean clickAndHoldElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    @Step("Clicking element with JavaScript: {by}")
    public static void clickElementWithJs(By by) {
        waitForElementPresent(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        //Click with JS
        js.executeScript("arguments[0].click();", getWebElement(by));
        logConsole("Click on element with JS: " + by);
    }

    @Step("Pressing ENTER key")
    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_ENTER);
            logConsole("Pressed ENTER key successfully");
            return true;
        } catch (Exception e) {
            logConsole("Error pressing ENTER: " + e.getMessage());
            return false;
        }
    }


    // Tô màu viền đỏ cho element
    public static WebElement highLightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

// ************* Wait ************

    //visibilityOfElementLocated
    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible " + by.toString());
            Assert.fail("Timeout waiting for the element Visible " + by.toString());
        }
    }

    //visibilityOfElementLocated với seconds là giá trị thay đổi
    public static void waitForElementVisible(By by, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible " + by.toString());
            Assert.fail("Timeout waiting for the element Visible " + by.toString());
        }
    }

    // Wait for elementToBeClickable
    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Clickable" + by.toString());
            Assert.fail("Timeout waiting for the element Clickable" + by.toString());
        }
    }

    // Wait for elementToBeClickable  với seconds là giá trị thay đổi
    public static void waitForElementClickable(By by, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Clickable" + by.toString());
            Assert.fail("Timeout waiting for the element Clickable" + by.toString());
        }
    }

    //presenceOfElementLocated
    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exits " + by.toString());
            Assert.fail("Element not exits " + by.toString());
        }
    }

    //presenceOfElementLocated với seconds là giá trị thay đổi
    public static void waitForElementPresent(By by, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exits " + by.toString());
            Assert.fail("Element not exits " + by.toString());
        }
    }

    // Wait For Page Loaded:  chờ trang load xong mới thao tác
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
//        Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
//        Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
//            Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    // Thread.sleep
    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    // chụp màn hình
//    public static void captureScreenImage(String imageName) {
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
////        Get size screen browser
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println(screenSize);
////        Khởi tạo kích thước khung hình với kích cỡ trên
//        Rectangle screenRectangle = new Rectangle(screenSize);
////        Tạo hình chụp với độ lớn khung đã tạo trên
//        BufferedImage image = robot.createScreenCapture(screenRectangle);
////        Lưu hình vào dạng file với dạng png
//        File file = new File("src/test/resources/screenshots/" + imageName + ".png");
//        try {
//            ImageIO.write(image, "png", file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
