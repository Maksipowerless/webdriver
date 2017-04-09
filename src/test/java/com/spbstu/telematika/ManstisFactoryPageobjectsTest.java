package com.spbstu.telematika;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maxfromperek on 04.04.17.
 */
public class ManstisFactoryPageobjectsTest extends BaseFactoryPagebjectsTest {

    String login = "administrator";
    String password = "21750";

    @DataProvider
    public Object[][] dataIssue() {
        return new Object[][]{
                {Arrays.asList(
                        "test summary part1",
                        "test description",
                        "test steps to reproduce",
                        "test additional info",
                        "test tag"
                )},

                {Arrays.asList(
                        "test summary part2",
                        "test description part2",
                        "test steps to reproduce part2",
                        "test additional info part2",
                        "test tag part2"
                )}
        };
    }

    @Test(dataProvider = "dataIssue")
    public void reportIssueTest(List<String> listExepted) {
        MantisSite.issuePage.openIssuePage();
        MantisSite.issuePage.fillFieldsIssue(listExepted);
        MantisSite.issuePage.submitIssue();

        List<String> checkedFields = driver.findElements(By.xpath("//table[@id='buglist']/tbody/tr[*]/td[11]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        actualField = listExepted.get(0);
        softAssert.assertTrue(checkedFields.stream().anyMatch(e -> e.contains(actualField)));
        softAssert.assertAll();
    }

}
