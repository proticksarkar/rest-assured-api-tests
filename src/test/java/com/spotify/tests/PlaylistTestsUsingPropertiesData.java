package com.spotify.tests;

import com.apiautomationtestframework.reporting.Logger;
import com.spotify.apioperations.PlaylistApiOperation;
import com.spotify.assertions.StatusCode;
import com.spotify.pojo.Playlist;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.spotify.assertions.AssertionHelper.assertPlaylist;
import static com.spotify.assertions.AssertionHelper.assertStatusCode;
import static com.spotify.dataproviders.PlaylistPropertiesProvider.propertyFileUtil;
import static com.spotify.payloadbuilders.PlaylistBuilder.buildRequestPlaylist;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTestsUsingPropertiesData extends TestHook {

    @Story("Create a Playlist")
    @Description("This scenario tests creating a Playlist with valid details")
    @Test(description = "Create a Playlist with valid details")
    public void shouldBeAbleToCreateAPlaylistWithPropertiesData() throws IOException {

        Logger.info("Creating Playlist with valid details");

        Playlist requestPlaylist = buildRequestPlaylist(
                propertyFileUtil().getProperty("create_playlist_name"),
                propertyFileUtil().getProperty("create_playlist_description"),
                Boolean.parseBoolean(propertyFileUtil().getProperty("create_playlist_public")));

        Response response = PlaylistApiOperation.createPlaylist(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylist(responsePlaylist, requestPlaylist);

        Logger.info("Created Playlist with valid details");
    }

}
