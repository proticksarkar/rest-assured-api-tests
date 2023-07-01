package com.apiautomationtestframework.listeners;

import com.apiautomationtestframework.configloaders.FrameworkConfigLoader;
import com.apiautomationtestframework.reporting.ExtentReportLogger;
import com.apiautomationtestframework.reporting.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count  = 0;
    private String retryMessage;

    @Override
    public boolean retry(final ITestResult result) {
        if (FrameworkConfigLoader.getInstance().retryFailedTest()) {
            if (!result.isSuccess()) {
                if (this.count < FrameworkConfigLoader.getInstance().getMaxRetry()) {
                    retryMessage = "Retrying test " + result.getName() + " with status " +
                            getResultStatusName(result.getStatus()) +
                            " for the " + (this.count + 1) + " time(s).";
                    Logger.warn(retryMessage);
                    ExtentReportLogger.logWarningDetails(retryMessage);
                    this.count++;
                    return true;
                }
            }
        }
        return false;
    }

    private String getResultStatusName(final int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }

}
