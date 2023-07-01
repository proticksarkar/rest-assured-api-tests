package com.apiautomationtestframework.specbuilders;

import com.apiautomationtestframework.listeners.RestAssuredListener;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {

    public static RequestSpecification getAccountRequestSpec(String baseUri, ContentType contentType,
                                                             LogDetail logDetail) {
        return new RequestSpecBuilder()
                .addFilter(new RestAssuredListener())
                .addFilter(new AllureRestAssured())
                .setBaseUri(baseUri)
                .setContentType(contentType)
                .log(logDetail)
                .build();
    }

    public static RequestSpecification  getRequestSpec(String baseUri, String basePath, ContentType contentType,
                                                       LogDetail logDetail) {
        return new RequestSpecBuilder()
                .addFilter(new RestAssuredListener())
                .addFilter(new AllureRestAssured())
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(contentType)
                .log(logDetail)
                .build();
    }

}
