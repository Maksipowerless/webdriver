package com.spbstu.telematika.webdriver.webdriverFactory;

import com.spbstu.telematika.MantisSite;
import com.spbstu.telematika.helper.ResourseLoaderSTU;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by maxfromperek on 09.04.17.
 */
public class BaseFactoryPagebjectsTest {

    WebDriver driver;
    SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuite() {

        //настройка веб драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MantisSite.init(driver);
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void afterSute() {
        driver.close();
    }

    @AfterMethod
    public void afterMethod() {

        //выход user
        MantisSite.issuePage.logout();

        //вход admin
        MantisSite.contactFormPage.fillLoginPasswordForm(ResourseLoaderSTU.getUser("admin"));
        MantisSite.contactFormPage.submitContactForm();
        MantisSite.issuePage.openViewIssuePage();
        MantisSite.issuePage.ckeckRow(ResourseLoaderSTU.getFieldData("issuetest").getSummary());
        MantisSite.issuePage.deleteIssues();
    }
}
