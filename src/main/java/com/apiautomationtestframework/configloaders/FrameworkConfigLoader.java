package com.apiautomationtestframework.configloaders;

import com.apiautomationtestframework.utilities.PropertyUtil;
import com.apiautomationtestframework.constants.ResourceLocation;

import java.util.Properties;

public class FrameworkConfigLoader {

    private final Properties properties;
    private static FrameworkConfigLoader configLoader;

    private FrameworkConfigLoader() {
        properties = PropertyUtil.propertyLoader(ResourceLocation.FRAMEWORK_CONFIG_PATH);
    }

    public static FrameworkConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new FrameworkConfigLoader();
        }
        return configLoader;
    }

    public boolean retryFailedTest() {
        String prop = properties.getProperty("retry_failed_test");
        if (prop != null)
            return Boolean.parseBoolean(prop);
        else
            throw new RuntimeException("Property retry_failed_test is not specified in the config.properties file");
    }

    public int getMaxRetry() {
        String prop = properties.getProperty("max_retry");
        if (prop != null)
            return Integer.parseInt(prop);
        else
            throw new RuntimeException("Property max_retry is not specified in the config.properties file");
    }

    public boolean generateReportForRemoteExecution() {
        String prop = properties.getProperty("report_generation_remote");
        if (prop != null)
            return Boolean.parseBoolean(prop);
        else
            throw new RuntimeException("Property report_generation_remote is not specified in the config.properties file");
    }

}
