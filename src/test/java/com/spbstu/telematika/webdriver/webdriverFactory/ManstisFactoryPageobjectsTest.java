package com.spbstu.telematika.webdriver.webdriverFactory;


import com.spbstu.telematika.MantisSite;
import com.spbstu.telematika.helper.ResourseLoaderSTU;
import com.spbstu.telematika.webdriver.webdriverFactory.BaseFactoryPagebjectsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maxfromperek on 04.04.17.
 */
public class ManstisFactoryPageobjectsTest extends BaseFactoryPagebjectsTest {

    @Test
    public void adminReportIssueUserCheckTest() {

        //авторизация admin
        MantisSite.open();
        MantisSite.contactFormPage.fillLoginPasswordForm(ResourseLoaderSTU.getUser("admin"));
        MantisSite.contactFormPage.submitContactForm();

        //заполнение и добавление задачи
        MantisSite.issuePage.openReportIssuePage();
        MantisSite.issuePage.fillFieldsIssue(ResourseLoaderSTU.getFieldData("issuetest"));
        MantisSite.issuePage.submitIssue();

        //выход admin
        MantisSite.issuePage.logout();

        //авторизация user
        MantisSite.contactFormPage.fillLoginPasswordForm(ResourseLoaderSTU.getUser("user"));
        MantisSite.contactFormPage.submitContactForm();

        //проверка задачи
        MantisSite.issuePage.openViewIssuePage();

        softAssert.assertTrue(MantisSite.issuePage.isDeleteRowSummary(ResourseLoaderSTU.
                getFieldData("issuetest").getSummary()));
        softAssert.assertAll();
    }
}
