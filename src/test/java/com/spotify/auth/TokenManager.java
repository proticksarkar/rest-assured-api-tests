package com.spotify.auth;

import com.apiautomationtestframework.httpoperations.HttpOperation;
import com.apiautomationtestframework.reporting.Logger;
import com.spotify.dataloaders.ConfigLoader;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.constants.Route.*;

public class TokenManager {

    private static String accessToken;
    private static Instant expiryTime;
    private static final int bufferDurationInSeconds = 300;

    public synchronized static String getAccessToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                Logger.info("Renewing Access Token...");
                Response response = renewAccessToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - bufferDurationInSeconds);
            }
            else {
                Logger.info("Access Token is valid!");
            }
        }
        catch (Exception exception) {
            throw new RuntimeException("ABORT!!! Failed to fetch Access Token!");
        }

        Logger.info("Access Token renewal completed!");
        return accessToken;
    }

    private static Response renewAccessToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

        Response response = HttpOperation.post(
                ACCOUNT_BASE_URI, ContentType.URLENC, LogDetail.ALL,
                API_PATH + TOKEN_PATH, formParams);

        if(response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Failed to renew Access Token!");
        }

        return response;
    }

}
