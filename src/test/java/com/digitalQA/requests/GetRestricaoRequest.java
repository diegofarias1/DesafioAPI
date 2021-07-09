package com.digitalQA.requests;

import com.digitalQA.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetRestricaoRequest extends RequestRestBase {

    public GetRestricaoRequest(String numCPF){

        requestService = "/api/v1/restricoes/" + numCPF;
        method = Method.GET;
    }

}



