package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class GetSimulacaoTests extends TestBase {

    @Test
    public void deveConsultarTodasSimulacoes() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();
        ArrayList<String> quantidade =response.body().jsonPath().get();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(quantidade.size()>=1, "validacao quantidade registros");
    }

    @Test
    public void deveConsultarSimulacoesVazias() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }

    @Test
    public void deveConsultarSimulacaoPeloCpf() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";
        int statusCodeEsperado = HttpStatus.SC_OK;

        String numCpf = "34702707090";

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(numCpf)
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(response.body().jsonPath().get("cpf").toString().contains(numCpf), "validacao cpf");
    }

    @Test
    public void deveConsultarSimulacaoInexistentePeloCpf() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        String numCpf = "99999999999";

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(numCpf)
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(response.body().jsonPath().get("mensagem").toString().contains("CPF 99999999999 não encontrado"), "validacao Mensagem");
    }
}

