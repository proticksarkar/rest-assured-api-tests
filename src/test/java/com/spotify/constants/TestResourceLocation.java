package com.spotify.constants;

import static com.apiautomationtestframework.constants.ResourceLocation.PROJECT_PATH;

public class TestResourceLocation {

    public static final String TEST_RESOURCES_PATH = PROJECT_PATH + "/src/test/resources";
    public static final String CONFIG_PATH = TEST_RESOURCES_PATH + "/config";
    public static final String CONFIG_PROPERTIES_PATH = CONFIG_PATH + "/config.properties";
    public static final String TEST_DATA_PATH = TEST_RESOURCES_PATH + "/testdata";
    public static final String PROPERTIES_TEST_DATA_PATH = TEST_DATA_PATH + "/properties_test_data.properties";
    public static final String JSON_TEST_DATA_PATH = TEST_DATA_PATH + "/json_test_data.json";
    public static final String EXCEL_TEST_DATA_PATH = TEST_DATA_PATH + "/excel_test_data.xlsx";

}
