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
    public Object[][] data() {
        return new Object[][]{
                {"spbstu", Arrays.asList(
                        "https://vk.com/pgpuspb1",
                        "www.spbstu.ru/",
                        "https://openedu.ru/course/spbstu/PRBIM/"
                ),
                },
                {"ifmo", Arrays.asList(
                        "www.ifmo.ru/",
                        "https://vk.com/ifmo.online",
                        "https://vk.com/abit.itmo1"
                ),
                }


        };
    }


    @Test(dataProvider = "data", dataProviderClass = SimpleTest.class)
    public void test1(String searchString, List<String> expected) {
        driver.navigate().to("http://www.google.com");
        WebElement element = driver.findElement(By.id("lst-ib"));
        element.sendKeys(searchString + Keys.ENTER);
        List<String> actual = driver.findElements(By.xpath("//*[@id='res']//div[@class='g']//cite"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        expected.forEach((e) -> softAssert.assertTrue(actual.stream().anyMatch(ee -> ee.contains(e)), String.format("Element: %s is missing",e)));
        //  Assert.assertTrue(elements.stream().anyMatch(e -> e.getText().contains(expected)));
        softAssert.assertAll();
    }

   @Test
    public void testForMantisReportIssue()
   {
       String expected = "test summary";
       //заполнение задачи
      driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm']")).click();
      driver.findElement(By.id("summary")).sendKeys(expected);
      driver.findElement(By.id("description")).sendKeys("test description");
      driver.findElement(By.id("steps_to_reproduce")).sendKeys("test steps to reproduce");
      driver.findElement(By.id("additional_info")).sendKeys("test additional info");driver.findElement(By.id("tag_string")).sendKeys("test tag");
      driver.findElement(By.xpath("//input[@class='btn btn-primary btn-white btn-round']")).click();

       //проверка добавления задачи (через поле summary)
       softAssert.assertTrue(driver.findElement(By.xpath("//table[@id='buglist']/tbody/tr[1]/td[11]")).getText().equals(expected));
       
   }
}