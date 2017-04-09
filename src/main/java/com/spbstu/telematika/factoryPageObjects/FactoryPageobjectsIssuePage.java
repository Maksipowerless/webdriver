package com.spbstu.telematika.factoryPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by maxfromperek on 09.04.17.
 */
public class FactoryPageobjectsIssuePage {

    @FindBy(css = "div ul a")
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

    @FindBy(xpath = "//input[@class='btn btn-primary btn-white btn-round']")
    WebElement buttonSubmitUssue;

    @FindBy(xpath = "//select[@name='action']//option[@value='DELETE']")
    WebElement menuDelete;

    @FindBy(xpath = "//input[@value='OK']")
    WebElement buttonChoseOk;

    @FindBy(xpath = "//input[@value='Delete Issues']")
    WebElement buttonDeleteIssues;

    public void openIssuePage() {
        this.buttonReportIssue.click();
    }

    public void fillFieldsIssue(List<String> lst){
        this.fieldSummary.sendKeys(lst.get(0));
        this.fieldDescription.sendKeys(lst.get(1));
        this.fieldStepToReproduce.sendKeys(lst.get(2));
        this.fieldAdditionalInfo.sendKeys(lst.get(3));
        this.fieldTagString.sendKeys(lst.get(4));
    }
    public void submitIssue(){
        buttonSubmitUssue.click();
    }

    public void deleteIssues(){
        this.menuDelete.click();
        this.buttonChoseOk.click();
        this.buttonDeleteIssues.click();
    }
}
