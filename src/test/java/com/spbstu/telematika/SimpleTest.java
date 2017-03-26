package com.spbstu.telematika;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//shift f6 - переименовать

/**
 * Created by maxfromperek on 14.03.17.
 */
public class SimpleTest extends BaseTest {

    @DataProvider
    public Object[][] dataIssue(){
        return new Object[][]{
                {Arrays.asList(
                        "summary",
                        "description",
                        "steps_to_reproduce",
                        "additional_info",
                        "tag_string"
                ),
                Arrays.asList(
                        "test summary",
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

   @Test(dataProvider="dataIssue", dataProviderClass = SimpleTest.class)
    public void testForMantisReportIssue(List<String> listXPath, List<String> listExepted)
   {
       //заполнение задачи(сделал по старинке через for, не знаю как сделать с помощью forEach и l-выражения
       driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm']")).click();
       for(int i=0; i< listXPath.size(); i++)
       {
         driver.findElement(By.id(listXPath.get(i))).sendKeys(listExepted.get(i));
       }

       driver.findElement(By.xpath("//input[@class='btn btn-primary btn-white btn-round']")).click();
       //проверка добавления задачи (через поле summary)
       softAssert.assertTrue(driver.findElement(By.xpath("//table[@id='buglist']/tbody/tr[1]/td[11]")).getText().equals(listExepted.get(0)));
       softAssert.assertAll();
   }
}