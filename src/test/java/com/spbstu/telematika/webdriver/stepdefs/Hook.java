package com.spbstu.telematika.webdriver.stepdefs;

import com.spbstu.telematika.MantisSite;
import com.spbstu.telematika.helper.ResourseLoaderSTU;
import cucumber.api.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by maxfromperek on 23.04.17.
 */
public class Hook {
    WebDriver driver;

    @Before
    public void before(Scenario scenario) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--lang=en-GB");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MantisSite.init(driver);
    }

    @After
    public void after(Scenario scenario) {

        //выход user
        MantisSite.issuePage.logout();

        //вход admin
        MantisSite.contactFormPage.fillLoginPasswordForm(ResourseLoaderSTU.getUser("admin"));
        MantisSite.contactFormPage.submitContactForm();
        MantisSite.issuePage.openViewIssuePage();
        MantisSite.issuePage.ckeckRow(ResourseLoaderSTU.getFieldData("issuetest").getSummary());
        MantisSite.issuePage.deleteIssues();
        driver.close();
    }
}
