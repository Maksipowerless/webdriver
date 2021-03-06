package com.spbstu.telematika.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//shift f6 - переименовать
//ctr+alt+m - создать метод

/**
 * Created by maxfromperek on 14.03.17.
 */
public class SimpleTest extends BaseTest {

    @DataProvider
    public Object[][] dataIssue() {
        return new Object[][]{
                {Arrays.asList(
                        "summary",
                        "description",
                        "steps_to_reproduce",
                        "additional_info",
                        "tag_string"
                ),
                        Arrays.asList(
                                "test summary part1",
                                "test description",
                                "test steps to reproduce",
                                "test additional info",
                                "test tag"
                        ),},

                {Arrays.asList(
                        "summary",
                        "description",
                        "steps_to_reproduce",
                        "additional_info",
                        "tag_string"
                ),
                        Arrays.asList(
                                "test summary part2",
                                "test description part2",
                                "test steps to reproduce part2",
                                "test additional info part2",
                                "test tag part2"
                        ),}
        };
    }

    @Test(dataProvider = "dataIssue", dataProviderClass = SimpleTest.class)
    public void testForMantisReportIssue(List<String> listXPath, List<String> listExepted) {
        actualField = listExepted.get(0);
        //заполнение задачи(сделал по старинке через for, не знаю как сделать с помощью forEach и l-выражения
        driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm']")).click();
        for (int i = 0; i < listXPath.size(); i++) {
            driver.findElement(By.id(listXPath.get(i))).sendKeys(listExepted.get(i));
        }

        driver.findElement(By.xpath("//input[@class='btn btn-primary btn-white btn-round']")).click();
        //проверка добавления задачи (через поле summary)
        List<String> checkedFields = driver.findElements(By.xpath("//table[@id='buglist']/tbody/tr[*]/td[11]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        softAssert.assertTrue(checkedFields.stream().anyMatch(e -> e.contains(actualField)));
        softAssert.assertAll();
    }
}