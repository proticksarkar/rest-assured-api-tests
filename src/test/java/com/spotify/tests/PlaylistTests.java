package com.spotify.tests;

import com.apiautomationtestframework.reporting.Logger;
import com.apiautomationtestframework.listeners.Retry;
import com.spotify.apioperations.PlaylistApiOperation;
import com.spotify.assertions.StatusCode;
import com.spotify.dataproviders.DataTypeName;
import com.spotify.pojo.Error;
import com.spotify.pojo.Playlist;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.spotify.assertions.AssertionHelper.*;
import static com.spotify.dataproviders.PlaylistDataGenerator.getRandomDataFor;
import static com.spotify.dataproviders.PlaylistPropertiesProvider.propertyFileUtil;
import static com.spotify.payloadbuilders.PlaylistBuilder.buildRequestPlaylist;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends TestHook {

    @Story("Create a Playlist")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("example-125")
    @TmsLink("example-127")
    @Description("This scenario tests creating a Playlist with valid details")
    @Test(description = "Create a Playlist with valid details")
    public void shouldBeAbleToCreateAPlaylist() {

        Logger.info("Creating Playlist with valid details");

        Playlist requestPlaylist = buildRequestPlaylist(
                getRandomDataFor(DataTypeName.PLAYLIST_NAME),
                getRandomDataFor(DataTypeName.PLAYLIST_DESCRIPTION),
                false);

        Response response = PlaylistApiOperation.createPlaylist(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylist(responsePlaylist, requestPlaylist);

        Logger.info("Created Playlist with valid details");
    }

    @Story("Fetch a Playlist")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("example-566")
    @TmsLink("example-568")
    @Description("This scenario tests fetching a Playlist with valid id")
    @Test(description = "Fetch a Playlist with valid id")
    public void shouldBeAbleToFetchAPlaylist() throws IOException {
        Logger.info("Fetching Playlist with valid id");

        Playlist requestPlaylist = buildRequestPlaylist(
                propertyFileUtil().getProperty("get_playlist_name"),
                propertyFileUtil().getProperty("get_playlist_description"),
                Boolean.parseBoolean(propertyFileUtil().getProperty("get_playlist_public")));

        Response response = PlaylistApiOperation.getPlaylist(propertyFileUtil().getProperty("get_playlist_id"));
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylist(responsePlaylist, requestPlaylist);

        Logger.info("Fetched Playlist with valid id");
    }

    @Story("Update a Playlist")
    @Description("This scenario tests updating a Playlist with valid details")
    @Test(description = "Update a Playlist with valid details")
    public void shouldBeAbleToUpdateAPlaylist() throws IOException {
        Logger.info("Updating Playlist with valid details");

        Playlist requestPlaylist = buildRequestPlaylist(
                getRandomDataFor(DataTypeName.PLAYLIST_NAME),
                getRandomDataFor(DataTypeName.PLAYLIST_DESCRIPTION),
                false);

        Response response = PlaylistApiOperation.updatePlaylistDetails(
                propertyFileUtil().getProperty("update_playlist_id"), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

        Logger.info("Updated Playlist with valid details");
    }

    @Story("Create a Playlist")
    @Description("This scenario tests creating a Playlist without name")
    @Test(description = "Create a Playlist without name", retryAnalyzer = Retry.class)
    public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
        Logger.info("Trying to create Playlist without name");

        Playlist requestPlaylist = buildRequestPlaylist(
                "",
                getRandomDataFor(DataTypeName.PLAYLIST_DESCRIPTION),
                false);

        Response response = PlaylistApiOperation.createPlaylist(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);

        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_400);

        Logger.info("Unable to create Playlist without name");
    }

    @Story("Create a Playlist")
    @Description("This scenario tests creating a Playlist with invalid token")
    @Test(description = "Create a Playlist with invalid token")
    public void shouldNotBeAbleToCreateAPlaylistWithInvalidToken() throws IOException {
        String invalidToken = propertyFileUtil().getProperty("invalid_token");

        Logger.info("Trying to create Playlist with invalid token");

        Playlist requestPlaylist = buildRequestPlaylist(
                getRandomDataFor(DataTypeName.PLAYLIST_NAME),
                getRandomDataFor(DataTypeName.PLAYLIST_DESCRIPTION),
                false);

        Response response = PlaylistApiOperation.createPlaylist(invalidToken, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);

        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_401);

        Logger.info("Unable to create Playlist with invalid token");
    }

}
