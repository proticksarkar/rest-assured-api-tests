package com.apiautomationtestframework.reporting;

import com.apiautomationtestframework.configloaders.FrameworkConfigLoader;
import com.apiautomationtestframework.utilities.PropertyFileUtil;
import com.apiautomationtestframework.constants.ResourceLocation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.apiautomationtestframework.utilities.DateTimeUtil;

import java.io.IOException;

public class ExtentReportManager {

    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;

    public static ExtentReports createInstance(String reportPath, String executionReportName,
                                               String executionReportTitle) {
        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName(executionReportName);
        extentSparkReporter.config().setDocumentTitle(executionReportTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        PropertyFileUtil propertyFileUtil = new PropertyFileUtil(ResourceLocation.EXTENT_REPORT_CONFIG_PATH);
        try {
            extentReports.setSystemInfo("Application", propertyFileUtil.getProperty("application_name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));

        return extentReports;
    }

    public static String getReportPath() {
        String reportPath = ResourceLocation.PROJECT_PATH + "/target/extent-reports/";
        return reportPath;
    }

    public static String getReportName() {
        String reportName;
        if (FrameworkConfigLoader.getInstance().generateReportForRemoteExecution())
            reportName = "test_execution_report" + ".html";
        else
            reportName = "test_execution_report-" + DateTimeUtil.getLocalDateTime() + ".html";
        return reportName;
    }

}
