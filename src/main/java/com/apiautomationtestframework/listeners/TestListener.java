package com.apiautomationtestframework.listeners;

import com.apiautomationtestframework.reporting.ExtentReportLogger;
import com.apiautomationtestframework.reporting.ExtentReportManager;
import com.apiautomationtestframework.reporting.Logger;
import com.apiautomationtestframework.utilities.PropertyFileUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

import static com.apiautomationtestframework.constants.ResourceLocation.EXTENT_REPORT_CONFIG_PATH;

public class TestListener implements ITestListener {

    public static ExtentReports extentReports;
    public static ExtentTest test;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String reportName = ExtentReportManager.getReportName();
        String reportPath = ExtentReportManager.getReportPath() + reportName;

        PropertyFileUtil propertyFileUtil = new PropertyFileUtil(EXTENT_REPORT_CONFIG_PATH);
        try {
            extentReports = ExtentReportManager.createInstance(reportPath,
                    propertyFileUtil.getProperty("execution_report_name"),
                    propertyFileUtil.getProperty("execution_report_title"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportLogger.logPassDetails("Test execution successful!!!");
    }

    public void onTestFailure(ITestResult result) {
        String exceptionMessage = result.getThrowable().getMessage();
        ExtentReportLogger.logFailureDetails(exceptionMessage);

        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br>");
        String formattedStackTrace = "<details>\n" +
                "    <summary>Click Here to see Exception Logs</summary>\n" +
                "    " + stackTrace + "\n" +
                "</details>\n";
        ExtentReportLogger.logExceptionDetails(formattedStackTrace);

        Logger.error("\nException:" + exceptionMessage +
                "\nStackTrace:\n" + stackTrace);
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null)
            extentReports.flush();
    }

}
