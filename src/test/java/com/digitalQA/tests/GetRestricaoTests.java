package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.utils.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class GetRestricaoTests extends TestBase {

    @Test
    public void deveVerificarCPFRestricao() throws IOException {
        String excelPath = "src/test/resources/dadosCPF.xlsx";
        String sheetName = "Planilha";
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        RestAssured.baseURI = "http://localhost:8080/api/v1/restricoes/";
        int tam = 11;
        int statusCodeEsperado = HttpStatus.SC_OK;

        for (int x = 1; x < tam; x++) {
            //Parâmetros
            String numCPF = excel.getCellData(x, 0).toString();
            String mensagem = "O CPF " + numCPF + " tem problema";

            //Fluxo
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(numCPF)
                    .then()
                    .extract().response();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
            Assert.assertTrue(response.body().jsonPath().get("mensagem").toString().contains(mensagem), "validacao mensagem");
        }
    }

    @Test
    public void deveVerificarCPFSemRestricao() {
        //Parâmetros
        String numCPF = "66414919004";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;
        RestAssured.baseURI = "http://localhost:8080/api/v1/restricoes/";

        //Fluxo
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(numCPF)
                .then()
                .extract().response();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }
}
