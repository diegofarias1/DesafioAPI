package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.requests.DeleteSimulacaoRequest;
import com.digitalQA.requests.PostSimulacaoRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostSimulacaoTests extends TestBase {
    PostSimulacaoRequest postSimulacaoRequest;
    DeleteSimulacaoRequest deleteSimulacaoRequest;

    @Test
    public void deveCriarSimulacaoComSucesso() {
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postSimulacaoRequest = new PostSimulacaoRequest();
        postSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PostSimulacaoJson.json");
        Response response = postSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertEquals(response.body().jsonPath().get("nome"), "Diego Farias", "validacao nome");
        Assert.assertEquals(response.body().jsonPath().get("cpf"), "24540898089", "validacao cpf");
        Assert.assertEquals(response.body().jsonPath().get("email"), "email@email.com", "validacao email");
        Assert.assertEquals(response.body().jsonPath().get("valor").toString(), "1200", "validacao valor");
        Assert.assertEquals(response.body().jsonPath().get("parcelas").toString(), "3", "validacao parcelas");
        Assert.assertEquals(response.body().jsonPath().get("seguro").toString(), "true", "validacao seguro");

        //Tratando massa de dados
        String id = response.body().jsonPath().get("id").toString();
        deleteSimulacaoRequest = new DeleteSimulacaoRequest(id);
        deleteSimulacaoRequest.executeRequest();
    }

    @Test
    public void deveCriarSimulacaoComFalha() {
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String mensagem = "CPF duplicado";

        //Fluxo
        postSimulacaoRequest = new PostSimulacaoRequest();
        postSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PostSimulacaoDuplicadoJson.json");
        postSimulacaoRequest.executeRequest();
        postSimulacaoRequest.setJsonBody("src/test/java/com/digitalQA/jsons/PostSimulacaoDuplicadoJson.json");
        Response responseDuplicado = postSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDuplicado.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(responseDuplicado.body().jsonPath().get("mensagem").toString().contains(mensagem), "validacao mensagem");
    }
}
