package com.digitalQA.requests;

import com.digitalQA.bases.RequestRestBase;
import com.digitalQA.utils.GeneralUtils;
import io.restassured.http.Method;

public class PutSimulacaoRequest extends RequestRestBase {

    public PutSimulacaoRequest(String numCPF){
        requestService = "/api/v1/simulacoes/" + numCPF;
        method = Method.PUT;
    }

    public void setJsonBody(String pathJson){
        jsonBody = GeneralUtils.readFileToAString(pathJson);
    }
}



