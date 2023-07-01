package com.spotify.dataproviders;

import com.apiautomationtestframework.utilities.PropertyFileUtil;
import com.spotify.constants.TestResourceLocation;

public class PlaylistPropertiesProvider {

    public static PropertyFileUtil propertyFileUtil() {
        PropertyFileUtil propertyFileUtil = new PropertyFileUtil(TestResourceLocation.PROPERTIES_TEST_DATA_PATH);
        return propertyFileUtil;
    }

}
