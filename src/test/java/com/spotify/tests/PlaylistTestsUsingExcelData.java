package com.spotify.tests;

import com.apiautomationtestframework.reporting.Logger;
import com.spotify.apioperations.PlaylistApiOperation;
import com.spotify.assertions.StatusCode;
import com.spotify.dataproviders.PlaylistDataProvider;
import com.spotify.pojo.Playlist;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.assertions.AssertionHelper.assertPlaylist;
import static com.spotify.assertions.AssertionHelper.assertStatusCode;
import static com.spotify.payloadbuilders.PlaylistBuilder.buildRequestPlaylist;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTestsUsingExcelData extends TestHook {

    @Story("Create a Playlist")
    @Description("This scenario tests creating a Playlist with valid details")
    @Test(description = "Create a Playlist with valid details",
          dataProvider = "excelPlaylistRequestPayload",
          dataProviderClass = PlaylistDataProvider.class)
    public void shouldBeAbleToCreateAPlaylistWithExcelData(Playlist playlistRequestPayload) {

        Logger.info("Creating Playlist with valid details");

        Playlist requestPlaylist = buildRequestPlaylist(
                playlistRequestPayload.getName(),
                playlistRequestPayload.getDescription(),
                playlistRequestPayload.get_public());

        Response response = PlaylistApiOperation.createPlaylist(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylist(responsePlaylist, requestPlaylist);

        Logger.info("Created Playlist with valid details");


    }

}
