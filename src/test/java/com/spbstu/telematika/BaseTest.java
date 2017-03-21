package com.spbstu.telematika;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxfromperek on 14.03.17.
 */
public class BaseTest {

    WebDriver driver;

    SoftAssert softAssert;

    @BeforeMethod
    public void BeforeMethod(Method method){
        System.out.println(String.format("Before method: %s", method.getName()));
        }


    @AfterMethod
    public void AfterMethod(ITestResult testResult){
        System.out.println(String.format("After method: %s, status : %s ", testResult.getName(), testResult.getStatus() ));
    }



    @BeforeSuite
    public void beforeSuite() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void afterSute(){
        driver.close();
    }

    @BeforeGroups
    public void beforeGroup(){
        System.out.println("before group");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("after group");
    }
}