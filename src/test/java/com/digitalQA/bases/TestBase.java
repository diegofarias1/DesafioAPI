package com.digitalQA.bases;

import com.digitalQA.GlobalParameters;
import com.digitalQA.utils.ExtentReportsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;

public abstract class TestBase {
    @BeforeSuite
    public void beforSuite(){
        new GlobalParameters();
        ExtentReportsUtils.createReport();
    }

    @BeforeMethod
    public void beforeTest(Method method){ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());}

    @AfterMethod
    public void afterTest(ITestResult result){
        ExtentReportsUtils.addTestResult(result);
    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportsUtils.generateReport();
    }
}
