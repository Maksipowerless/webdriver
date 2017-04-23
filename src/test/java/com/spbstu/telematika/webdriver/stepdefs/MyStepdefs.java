package com.spbstu.telematika.webdriver.stepdefs;

import com.spbstu.telematika.MantisSite;
import com.spbstu.telematika.factoryPageObjects.entities.User;
import com.spbstu.telematika.helper.ResourseLoaderSTU;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

/**
 * Created by maxfromperek on 23.04.17.
 */
public class MyStepdefs {
    SoftAssert softAssert;

    @Given("^Open Mantis Site$")
    public void openMantisSite() throws Throwable {
        MantisSite.open();
    }

    @And("^Login as \"([^\"]*)\"$")
    public void loginAs(String userID) throws Throwable {
        User user = ResourseLoaderSTU.getUser(userID);
        MantisSite.contactFormPage.fillLoginPasswordForm(user);
        MantisSite.contactFormPage.submitContactForm();
    }

    @And("^Open Report Issue Page$")
    public void openReportIssuePage() throws Throwable {
        MantisSite.issuePage.openReportIssuePage();
    }

    @When("^Fill issue Form with \"([^\"]*)\"$")
    public void fillIssueFormWith(String data) throws Throwable {
        MantisSite.issuePage.fillFieldsIssue(ResourseLoaderSTU.getFieldData(data));
    }

    @And("^Submit Issue Form$")
    public void submitIssueForm() throws Throwable {
        MantisSite.issuePage.submitIssue();
    }

    @And("^Logout$")
    public void logout() throws Throwable {
        MantisSite.issuePage.logout();
    }

    @And("^Open View Issue Page$")
    public void openViewIssuePage() throws Throwable {
        MantisSite.issuePage.openViewIssuePage();
    }

    @Then("^Result contains \"([^\"]*)\"$")
    public void resultContains(String data) throws Throwable {
        softAssert = new SoftAssert();
        softAssert.assertTrue(MantisSite.issuePage.isExistRowSummary(ResourseLoaderSTU.
                getFieldData("issuetest").getSummary()));
        softAssert.assertAll();
    }

    //чтобы заполнить данные из DataTable перегружен метод fillLoginPasswordForm, который принимает map и по ключам заполняет поля
    @And("^Login with data below$")
    public void loginWithDataBelow(DataTable dataTable) throws Throwable {
        Map<String,String> map = dataTable.asMap(String.class, String.class);
        MantisSite.contactFormPage.fillLoginPasswordForm(map);
        MantisSite.contactFormPage.submitContactForm();
    }
}
