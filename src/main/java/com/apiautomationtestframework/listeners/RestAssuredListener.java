package com.apiautomationtestframework.listeners;

import com.apiautomationtestframework.reporting.ExtentReportLogger;
import com.apiautomationtestframework.reporting.Logger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredListener implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {
        Response response = context.next(requestSpec, responseSpec);

        // Logging in File
        // Request Logs
        Logger.info("\nHTTP method => " + requestSpec.getMethod()
                    + "\nAPI endpoint => " + requestSpec.getURI()
                    + "\nRequest headers\n" + requestSpec.getHeaders()
                    + "\nRequest body\n" + requestSpec.getBody());
        // Response  Logs
        Logger.info("\nResponse status => " + response.getStatusCode()
                    + "\nResponse headers\n" + response.getHeaders()
                    + "\nResponse body\n" + response.getBody().prettyPrint());

        // Logging in Extent Report
        // Request Logs
        ExtentReportLogger.logInfoDetails("HTTP method => " + requestSpec.getMethod());
        ExtentReportLogger.logInfoDetails("API endpoint => " + requestSpec.getURI());
        ExtentReportLogger.logInfoDetails("Request headers\n");
        ExtentReportLogger.logHeaders(requestSpec.getHeaders().asList());
        ExtentReportLogger.logInfoDetails("Request body\n");
        ExtentReportLogger.logJsonBody(requestSpec.getBody());
        // Response  Logs
        ExtentReportLogger.logInfoDetails("Response status => " + response.getStatusCode());
        ExtentReportLogger.logInfoDetails("Response headers\n");
        ExtentReportLogger.logHeaders(response.getHeaders().asList());
        ExtentReportLogger.logInfoDetails("Response body\n");
        ExtentReportLogger.logJsonBody(response.getBody().prettyPrint());

        return response;
    }

}
