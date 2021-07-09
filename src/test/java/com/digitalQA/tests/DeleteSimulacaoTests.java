package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.requests.DeleteSimulacaoRequest;
import com.digitalQA.requests.PostSimulacaoRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteSimulacaoTests extends TestBase {
    PostSimulacaoRequest postSimulacaoRequest;
    DeleteSimulacaoRequest deleteSimulacaoRequest;

    @Test
    public void deveDeletarSimulacaoComSucesso() {
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo Inserindo dados delecao
        postSimulacaoRequest = new PostSimulacaoRequest();
        postSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PostSimulacaoJson.json");
        Response responseGet = postSimulacaoRequest.executeRequest();

        //Fluxo Deletando registro
        String id = responseGet.body().jsonPath().get("id").toString();
        deleteSimulacaoRequest = new DeleteSimulacaoRequest(id);
        Response response = deleteSimulacaoRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }

    @Test
    public void deveDeletarSimulacaoIdInvalido() {
        int statusCodeEsperado = HttpStatus.SC_OK;
        String id = "99999";

        //Fluxo Deletando registro
        deleteSimulacaoRequest = new DeleteSimulacaoRequest(id);
        Response response = deleteSimulacaoRequest.executeRequest();
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code"); // A API SEMPRE RETORNA OK  MESMO COM UM ID INVALIDO OU INEXISTENTE.
    }
}

