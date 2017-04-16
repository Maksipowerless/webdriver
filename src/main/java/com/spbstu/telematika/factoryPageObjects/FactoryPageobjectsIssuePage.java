package com.spbstu.telematika.factoryPageObjects;

import com.spbstu.telematika.factoryPageObjects.entities.FieldData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by maxfromperek on 09.04.17.
 */
public class FactoryPageobjectsIssuePage {

    @FindBy(xpath = "//a[contains(text(), 'Report Issue')]")
    WebElement buttonReportIssue;

    @FindBy(id = "summary")
    WebElement fieldSummary;

    @FindBy(id = "description")
    WebElement fieldDescription;

    @FindBy(id = "steps_to_reproduce")
    WebElement fieldStepToReproduce;

    @FindBy(id = "additional_info")
    WebElement fieldAdditionalInfo;

    @FindBy(id = "tag_string")
    WebElement fieldTagString;

    @FindBy(xpath = "//input[contains(@class, 'btn btn-primary')]")
    WebElement buttonSubmitUssue;

    @FindBy(xpath = "//select[@name='action']//option[@value='DELETE']")
    WebElement menuDelete;

    @FindBy(xpath = "//input[@value='OK']")
    WebElement buttonChoseOk;

    @FindBy(xpath = "//input[@value='Delete Issues']")
    WebElement buttonDeleteIssues;

    @FindBy(xpath="//*[@class='user-info']")
    WebElement buttonUserInfo;

    @FindBy(xpath ="//a[@href='/mantisbt/logout_page.php']")
    WebElement menuLogout;

    @FindBy(xpath = "//span[contains(text(),'View Issues')]")
    WebElement buttonViewIssue;

    public void openReportIssuePage() {
        this.buttonReportIssue.click();
    }

    public void fillFieldsIssue(FieldData fieldData){
        this.fieldSummary.sendKeys(fieldData.getSummary());
        this.fieldDescription.sendKeys(fieldData.getDescription());
        this.fieldStepToReproduce.sendKeys(fieldData.getSteptoreproduce());
        this.fieldAdditionalInfo.sendKeys(fieldData.getAdditionalinfo());
        this.fieldTagString.sendKeys(fieldData.getTagstring());
    }
    public void submitIssue(){
        buttonSubmitUssue.click();
    }

    public void deleteIssues(){
        this.menuDelete.click();
        this.buttonChoseOk.click();
        this.buttonDeleteIssues.click();
    }

    public void logout(){
        this.buttonUserInfo.click();
        this.menuLogout.click();
    }

    public void openViewIssuePage() {this.buttonViewIssue.click();}
}
