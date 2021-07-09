package com.digitalQA.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.net.URI;
import java.util.Map;

public class RestAssuredUtils {
    public static Response executeRestRequest(String url,
                                              String requestService,
                                              Method method,
                                              Map<String,String> headers,
                                              String jsonBody
    ) {

        RequestSpecification requestSpecification = RestAssured.given();
        for (Map.Entry<String, String> header : headers.entrySet()){
            requestSpecification.headers(header.getKey(), header.getValue());
        }

        if (jsonBody != null) {
            requestSpecification.body(jsonBody);
        }

        return requestSpecification.request(method, URI.create(url + requestService));
    }

}