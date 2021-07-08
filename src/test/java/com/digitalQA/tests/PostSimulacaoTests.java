package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSimulacaoTests extends TestBase {

    @Test
    public void deveCriarSimulacaoComSucesso() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes";

        int statusCodeEsperado = HttpStatus.SC_CREATED;
        String jsonBody2 ="{ \"nome\": \"Diego Farias\", \"cpf\": 34702707090, \"email\": \"email@email.com\", \"valor\": 1200, \"parcelas\": 3, \"seguro\": true}";

        //Fluxo
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody2)
                .when()
                .post()
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertEquals(response.body().jsonPath().get("nome"), "Diego Farias", "validacao nome");
        Assert.assertEquals(response.body().jsonPath().get("cpf"), "34702707090", "validacao cpf");
        Assert.assertEquals(response.body().jsonPath().get("email"), "email@email.com", "validacao email");
        Assert.assertEquals(response.body().jsonPath().get("valor").toString(), "1200", "validacao valor");
        Assert.assertEquals(response.body().jsonPath().get("parcelas").toString(), "3", "validacao parcelas");
        Assert.assertEquals(response.body().jsonPath().get("seguro").toString(), "true", "validacao seguro");
    }

    @Test
    public void deveCriarSimulacaoComFalha() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String jsonBody2 ="{ \"nome\": \"Diego Farias\", \"cpf\": 34702707088, \"email\": \"email@email.com\", \"valor\": 1200, \"parcelas\": 3, \"seguro\": true}";
        String mensagem = "CPF duplicado";

        //Fluxo
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody2)
                .when()
                .post()
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertTrue(response.body().jsonPath().get("mensagem").toString().contains(mensagem), "validacao mensagem");
    }
}
