package com.digitalQA.tests;

import com.digitalQA.bases.TestBase;
import com.digitalQA.requests.GetRestricaoRequest;
import com.digitalQA.utils.ExcelUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetRestricaoTests extends TestBase {
    GetRestricaoRequest getRestricaoRequest;

    @Test
    public void deveVerificarCPFRestricao() throws IOException {
        String excelPath = "src/test/resources/dadosCPF.xlsx";
        String sheetName = "Planilha";
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        int tam = 11;
        int statusCodeEsperado = HttpStatus.SC_OK;

        for (int x = 1; x < tam; x++) {
            //Parâmetros
            String numCPF = excel.getCellData(x, 0).toString();
            String mensagem = "O CPF " + numCPF + " tem problema";

            //Fluxo
            getRestricaoRequest = new GetRestricaoRequest(numCPF);
            Response response = getRestricaoRequest.executeRequest();

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

        //Fluxo
        getRestricaoRequest = new GetRestricaoRequest(numCPF);
        Response response = getRestricaoRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "validacao Status Code");
    }
}