package com.digitalQA.bases;

import com.digitalQA.GlobalParameters;
import com.digitalQA.utils.RestAssuredUtils;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public abstract class RequestRestBase {
    protected String url = GlobalParameters.URL_DEFAULT;
    protected String requestService = null;
    protected Method method = null;
    protected String jsonBody = null;
    protected Map<String, String> headers = new HashMap<String, String>();


    public RequestRestBase(){
        config = RestAssuredConfig.newConfig().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        enableLoggingOfRequestAndResponseIfValidationFails();
        headers.put("content-type", "application/json");
    }

    public Response executeRequest() {
        Response response = RestAssuredUtils.executeRestRequest(url, requestService, method,headers,jsonBody);
        return response;
    }
}
