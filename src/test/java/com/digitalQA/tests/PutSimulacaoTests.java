package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.requests.DeleteSimulacaoRequest;
import com.digitalQA.requests.PostSimulacaoRequest;
import com.digitalQA.requests.PutSimulacaoRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutSimulacaoTests extends TestBase {
    PostSimulacaoRequest postSimulacaoRequest;
    PutSimulacaoRequest putSimulacaoRequest;
    DeleteSimulacaoRequest deleteSimulacaoRequest;

    @Test
    public void deveAlterarSimulacaoComSucesso() {
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo Post para manipulação de dados
        postSimulacaoRequest = new PostSimulacaoRequest();
        postSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PostSimulacaoJson.json");
        Response responsePost = postSimulacaoRequest.executeRequest();

        // Fluxo Put alterando dado com sucesso
        String numCpf = responsePost.body().jsonPath().get("cpf");
        putSimulacaoRequest = new PutSimulacaoRequest(numCpf);
        putSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PutSimulacaoJson.json");
        Response response = putSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertEquals(response.body().jsonPath().get("nome"), "Nome Alterado", "validacao nome");
        Assert.assertEquals(response.body().jsonPath().get("cpf"), numCpf, "validacao cpf");
        Assert.assertEquals(response.body().jsonPath().get("email"), "email@email.com", "validacao email");
        Assert.assertEquals(response.body().jsonPath().get("valor").toString(), "1200.00", "validacao valor");
        Assert.assertEquals(response.body().jsonPath().get("parcelas").toString(), "3", "validacao parcelas");
        Assert.assertEquals(response.body().jsonPath().get("seguro").toString(), "true", "validacao seguro");

        //Tratando massa de dados
        String id = responsePost.body().jsonPath().get("id").toString();
        deleteSimulacaoRequest = new DeleteSimulacaoRequest(id);
        deleteSimulacaoRequest.executeRequest();
    }

    @Test
    public void alterarSimulacaoComCpfInexistente() {
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        putSimulacaoRequest = new PutSimulacaoRequest("99999999999");
        putSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PutSimulacaoJson.json");
        Response response = putSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }
}