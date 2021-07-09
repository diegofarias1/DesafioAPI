package com.digitalQA.requests;

import com.digitalQA.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetSimulacaoRequest extends RequestRestBase {

    public GetSimulacaoRequest(){

        requestService = "/api/v1/simulacoes/";
        method = Method.GET;
    }
    public GetSimulacaoRequest(String numCPF){

        requestService = "/api/v1/simulacoes/" + numCPF;
        method = Method.GET;
    }

}



