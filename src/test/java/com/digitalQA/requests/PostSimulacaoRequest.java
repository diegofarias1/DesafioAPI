package com.digitalQA.requests;

import com.digitalQA.bases.RequestRestBase;
import com.digitalQA.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostSimulacaoRequest extends RequestRestBase {

    public PostSimulacaoRequest(){
        requestService = "/api/v1/simulacoes";
        method = Method.POST;
    }

    public void setJsonBody(String pathJson){
        jsonBody = GeneralUtils.readFileToAString(pathJson);
    }
}



