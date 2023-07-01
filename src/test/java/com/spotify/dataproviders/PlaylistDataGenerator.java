package com.spotify.dataproviders;

import com.apiautomationtestframework.utilities.DataGeneratorUtil;

public class PlaylistDataGenerator {

    public static String getRandomDataFor(DataTypeName dataTypeName) {
        switch (dataTypeName) {
            case PLAYLIST_NAME:
                return "Playlist name " + DataGeneratorUtil.faker.regexify("[A-Za-z0-9 ,_-]{20}");
            case PLAYLIST_DESCRIPTION:
                return "Playlist description " + DataGeneratorUtil.faker.regexify("[A-Za-z0-9 ,_-@./#&]{50}");
            default:
                return "Data type unavailable";
        }
    }

}
