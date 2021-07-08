package com.digitalQA.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.digitalQA.GlobalParameters;
import org.testng.ITestResult;

public class ExtentReportsUtils
{
    public static ExtentReports EXTENT_REPORT = null;
    public static ExtentTest TEST;
    public static ExtentHtmlReporter HTML_REPORTER = null;
    static String reportName = GlobalParameters.REPORT_NAME;
    static String reportsPath = GlobalParameters.REPORT_PATH;
    static String fileName = reportName+".html";
    static String fullReportFilePath = reportsPath + "/"+ reportName +"/" + fileName;

    public static void createReport(){
        if (EXTENT_REPORT == null){
            HTML_REPORTER = new ExtentHtmlReporter(fullReportFilePath);
            EXTENT_REPORT = new ExtentReports();
            EXTENT_REPORT.attachReporter(HTML_REPORTER);
        }
    }

    public static void addTest(String testName, String testCategory){
        TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests",""));
    }

    public static void addTestResult(ITestResult result){
        switch (result.getStatus())
        {
            case ITestResult.FAILURE:
                TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>");
                break;
            case ITestResult.SKIP:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>");
                break;
            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }
    }

    public static void generateReport(){
        EXTENT_REPORT.flush();
    }
}
