package com.spotify.dataproviders;

import com.apiautomationtestframework.utilities.ExcelFileUtil;
import com.apiautomationtestframework.utilities.JsonFileUtil;
import com.spotify.constants.TestResourceLocation;
import com.spotify.pojo.Playlist;
import com.spotify.pojo.PlaylistRequestPayload;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class PlaylistDataProvider {

    @DataProvider(name = "jsonPlaylistRequestPayload")
    public Object[][] providePayloadDataFromJson() throws IOException {
        JsonFileUtil<PlaylistRequestPayload> jsonFileReader = new JsonFileUtil<>(PlaylistRequestPayload.class);
        PlaylistRequestPayload jsonTestData = jsonFileReader.readJsonFile(TestResourceLocation.JSON_TEST_DATA_PATH);
        List<Playlist> playlistRequestPayloads = jsonTestData.getPlaylistRequestPayload();
        return getPayloadData(playlistRequestPayloads);
    }

    @DataProvider(name = "excelPlaylistRequestPayload")
    public Object[][] providePayloadDataFromExcel() throws IOException {
        ExcelFileUtil excelFileReader = new ExcelFileUtil(TestResourceLocation.EXCEL_TEST_DATA_PATH);
        List<Playlist> playlistRequestPayloads = excelFileReader.readExcelFile(Playlist.class);
        return getPayloadData(playlistRequestPayloads);
    }

    private <T> Object[][] getPayloadData(List<T> payload) {
        Object[][] payloadData = new Object[payload.size()][1];
        for (int i = 0; i < payload.size(); i++) {
            payloadData[i][0] = payload.get(i);
        }
        return payloadData;
    }

}
