package com.spotify.apioperations;

import com.apiautomationtestframework.httpoperations.HttpOperation;
import com.spotify.pojo.Playlist;
import com.spotify.dataloaders.ConfigLoader;
import com.spotify.auth.TokenManager;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.spotify.constants.Route.*;

public class PlaylistApiOperation {

    public static Response createPlaylist(Playlist requestPlaylist) {
        return HttpOperation.post(
                BASE_URI, BASE_PATH, ContentType.JSON, LogDetail.ALL,
                USERS_PATH + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS_PATH,
                TokenManager.getAccessToken(), requestPlaylist);
    }

    public static Response createPlaylist(String accessToken, Playlist requestPlaylist) {
        return HttpOperation.post(
                BASE_URI, BASE_PATH, ContentType.JSON, LogDetail.ALL,
                USERS_PATH + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS_PATH,
                accessToken, requestPlaylist);
    }

    public static Response getPlaylist(String playlistId) {
        return HttpOperation.get(
                BASE_URI, BASE_PATH, ContentType.JSON, LogDetail.ALL,
                PLAYLISTS_PATH + "/" + playlistId, TokenManager.getAccessToken());
    }

    public static Response updatePlaylistDetails(String playlistId, Playlist requestPlaylist) {
        return HttpOperation.put(
                BASE_URI, BASE_PATH, ContentType.JSON, LogDetail.ALL,
                PLAYLISTS_PATH + "/" + playlistId, TokenManager.getAccessToken(), requestPlaylist);
    }

}
