package com.apiautomationtestframework.reporting;

import com.apiautomationtestframework.listeners.TestListener;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;

import java.util.List;

public class ExtentReportLogger {

    public static void logInfoDetails(String log) {
        TestListener.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }

    public static void logPassDetails(String log) {
        TestListener.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailureDetails(String log) {
        TestListener.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logExceptionDetails(String log) {
        TestListener.extentTest.get().fail(log);
    }

    public static void logWarningDetails(String log) {
        TestListener.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

    public static void logJsonBody(String json) {
        TestListener.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static void logHeaders(List<Header> headersList) {
        String[][] arrayHeaders = headersList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                .toArray(String[][] :: new);
        TestListener.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
    }

}
