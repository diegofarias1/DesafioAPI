package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteSimulacaoTests extends TestBase {

    @Test
    public void deveDeletarSimulacaoComSucesso() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";

        //Fluxo
        Response responseGet = given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();
        String id = responseGet.body().jsonPath().get("[0].id").toString();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(id)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void deveDeletarSimulacaoIdInvalido() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/simulacoes/";

        //Fluxo
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("0")
                .then()
                .statusCode(200) //na documentação não existe status 404
                .extract().response();
    }
}

