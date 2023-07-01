package com.spotify.tests;

import com.apiautomationtestframework.reporting.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestHook {

    @BeforeMethod
    public synchronized void beforeEachTest(Method method) {
        Logger.info("STARTING TEST: " + method.getName());
    }

    @AfterMethod
    public synchronized void afterEachTest(ITestResult result, Method method) {
        Logger.info("TEST EXECUTION COMPLETED: " + method.getName());
    }

}
