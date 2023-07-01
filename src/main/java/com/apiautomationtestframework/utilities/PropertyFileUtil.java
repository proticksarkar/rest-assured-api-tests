package com.apiautomationtestframework.utilities;

import java.io.*;
import java.util.Properties;

public class PropertyFileUtil {

    private String filePath;

    public PropertyFileUtil(String filePath) {
        this.filePath = filePath;
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }
        return properties;
    }

    private void saveProperties(Properties properties) throws IOException {
        try (OutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, null);
        }
    }

    public String getProperty(String key) throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) throws IOException {
        Properties properties = loadProperties();
        properties.setProperty(key, value);
        saveProperties(properties);
    }

    public void removeProperty(String key) throws IOException {
        Properties properties = loadProperties();
        properties.remove(key);
        saveProperties(properties);
    }

}
