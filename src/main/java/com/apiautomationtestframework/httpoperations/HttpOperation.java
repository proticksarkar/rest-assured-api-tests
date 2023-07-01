package com.apiautomationtestframework.httpoperations;

import com.apiautomationtestframework.specbuilders.RequestSpecificationBuilder;
import com.apiautomationtestframework.specbuilders.ResponseSpecificationBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpOperation {

    // POST operations
    public static Response post(String baseUri, ContentType contentType, LogDetail logDetail,
                                String path, HashMap<String, String> formParams) {
        return
                given(RequestSpecificationBuilder.getAccountRequestSpec(baseUri, contentType, logDetail))
                        .formParams(formParams)
                .when()
                        .post(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    public static Response post(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                                String path, String accessToken, Object requestPayload) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .auth()
                        .oauth2(accessToken)
                        .body(requestPayload)
                .when()
                        .post(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    public static Response post(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                                String path, Map<String, String> queryParams, String accessToken, Object requestPayload) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .queryParams(queryParams)
                        .auth()
                        .oauth2(accessToken)
                        .body(requestPayload)
                .when()
                        .post(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    public static Response post(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                                String path, String accessToken, Map<String, String> headers,
                                Object requestPayload) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .auth()
                        .oauth2(accessToken)
                        .headers(headers)
                        .body(requestPayload)
                .when()
                        .post(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    public static Response post(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                                String path, Map<String, String> queryParams, String accessToken,
                                Map<String, String> headers, Object requestPayload) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .queryParams(queryParams)
                        .auth()
                        .oauth2(accessToken)
                        .headers(headers)
                        .body(requestPayload)
                .when()
                        .post(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    // GET operations
    public static Response get(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                               String path, String accessToken) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .auth()
                        .oauth2(accessToken)
                .when()
                        .get(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

    // PUT operations
    public static Response put(String baseUri, String basePath, ContentType contentType, LogDetail logDetail,
                               String path, String accessToken, Object requestPayload) {
        return
                given(RequestSpecificationBuilder.getRequestSpec(baseUri, basePath, contentType, logDetail))
                        .auth()
                        .oauth2(accessToken)
                        .body(requestPayload)
                .when()
                        .put(path)
                .then()
                        .spec(ResponseSpecificationBuilder.getResponseSpec())
                        .extract()
                        .response();
    }

}
