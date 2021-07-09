package com.digitalQA.requests;

import com.digitalQA.bases.RequestRestBase;
import com.digitalQA.utils.GeneralUtils;
import io.restassured.http.Method;

public class DeleteSimulacaoRequest extends RequestRestBase {

    public DeleteSimulacaoRequest(String id) {
        requestService = "/api/v1/simulacoes/" + id;
        method = Method.DELETE;
    }
}