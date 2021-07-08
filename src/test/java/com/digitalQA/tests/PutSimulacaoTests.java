package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class PutSimulacaoTests extends TestBase {

    @Test
    public void deveAlterarSimulacaoComSucesso() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes";

        int statusCodeEsperado = HttpStatus.SC_OK;
        String numCpf = "34702707090";
        String jsonBody2 ="{ \"nome\":\"Nome alterado\",\"cpf\":\"34702707090\",\"email\":\"fulano@gmail.com\",\"valor\":1200,\"parcelas\":3,\"seguro\":true}";

        //Fluxo
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody2)
                .when()
                .put(numCpf)
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
        Assert.assertEquals(response.body().jsonPath().get("nome"), "Nome alterado", "validacao nome");
        Assert.assertEquals(response.body().jsonPath().get("cpf"), numCpf, "validacao cpf");
        Assert.assertEquals(response.body().jsonPath().get("email"), "fulano@gmail.com", "validacao email");
        Assert.assertEquals(response.body().jsonPath().get("valor").toString(), "1200.0", "validacao valor");
        Assert.assertEquals(response.body().jsonPath().get("parcelas").toString(), "3", "validacao parcelas");
        Assert.assertEquals(response.body().jsonPath().get("seguro").toString(), "true", "validacao seguro");
    }

    @Test
    public void alterarSimulacaoComCpfInexistente() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes";

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        String jsonBody2 ="{ \"id\":11,\"nome\":\"Nome alterado\",\"cpf\":\"11111111111\",\"email\":\"fulano@gmail.com\",\"valor\":11000,\"parcelas\":3,\"seguro\":true}";

        //Fluxo
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody2)
                .when()
                .put("00000000000")
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }
}
