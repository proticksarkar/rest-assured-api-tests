package com.apiautomationtestframework.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static Properties propertyLoader(String filePath) {
        Properties properties = new Properties();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException exception) {
                exception.printStackTrace();
                throw new RuntimeException("Failed to load properties file " + filePath);
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Properties file not found at " + filePath);
        }

        return properties;
    }

}
