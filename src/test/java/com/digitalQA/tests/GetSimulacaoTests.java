package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.requests.GetSimulacaoRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class GetSimulacaoTests extends TestBase {
    GetSimulacaoRequest getSimulacaoRequest;

    @Test
    public void deveConsultarTodasSimulacoes() {
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getSimulacaoRequest = new GetSimulacaoRequest();
        Response response = getSimulacaoRequest.executeRequest();
        ArrayList<String> quantidade = response.body().jsonPath().get();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(quantidade.size() >= 1, "validacao quantidade registros");
    }

    @Test
    public void deveConsultarSimulacoesVazias() {
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        getSimulacaoRequest = new GetSimulacaoRequest();
        Response response = getSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }

    @Test
    public void deveConsultarSimulacaoPeloCpf() {
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo Pegando Valor CPF
        getSimulacaoRequest = new GetSimulacaoRequest();
        Response responseSimulacao = getSimulacaoRequest.executeRequest();
        String numCpf = responseSimulacao.body().jsonPath().get("[0].cpf");

        //Fluxo Consultando Simulacao pelo primeiro CPF encontrado
        getSimulacaoRequest = new GetSimulacaoRequest(numCpf);
        Response response = getSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(response.body().jsonPath().get("cpf").toString().contains(numCpf), "validacao cpf");
    }

    @Test
    public void deveConsultarSimulacaoInexistentePeloCpf() {
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        String numCpf = "99999999999";

        //Fluxo
        getSimulacaoRequest = new GetSimulacaoRequest(numCpf);
        Response response = getSimulacaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(response.body().jsonPath().get("mensagem").toString().contains("CPF 99999999999 não encontrado"), "validacao Mensagem");
    }
}

