package reports;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        AllureManager.saveTextLog("Test Started: " + result.getMethod().getMethodName());
        AllureManager.saveBrowserInfo();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureManager.stepTestPassed(result.getMethod().getMethodName());
        AllureManager.saveScreenshotPNG("Test Success Screenshot");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";
        AllureManager.stepTestFailed(errorMessage);
        AllureManager.saveScreenshotPNG("Test Failure Screenshot");
        AllureManager.savePageSource();
        AllureManager.saveBrowserLogs();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        AllureManager.saveTextLog("Test Skipped: " + result.getMethod().getMethodName());
        AllureManager.saveScreenshotPNG("Test Skipped Screenshot");
    }
}