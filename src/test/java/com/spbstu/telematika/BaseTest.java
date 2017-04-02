package com.spbstu.telematika;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by maxfromperek on 14.03.17.
 */
public class BaseTest {

    WebDriver driver;
    SoftAssert softAssert;
    String actualField;

    @BeforeSuite
    public void beforeSuite() {

        //настройка веб драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        softAssert = new SoftAssert();

        String stringUsername = "administrator";
        String stringPassword = "21750";

        //ввод логина и пароля
        driver.navigate().to("http://localhost/mantisbt/login_page.php");
        driver.findElement(By.id("username")).sendKeys(stringUsername);
        driver.findElement(By.id("password")).sendKeys(stringPassword);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @AfterMethod
    public void afterMethod() {
        //удаление добавленной задачи

        List<String> checkedFields = driver.findElements(By.xpath("//table[@id='buglist']/tbody/tr[*]/td[11]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        //делаю через for, потому что не знаю как сделать с помощью l-выражений и foreach
        for(int i=0; i<checkedFields.size(); i++ ) {
            if(checkedFields.get(i).equals(actualField))
            {
                driver.findElement(By.xpath(("//table[@id='buglist']/tbody/tr['$i']/td[1]//span[@class='lbl']"))).click();
                driver.findElement(By.xpath("//select[@name='action']//option[@value='DELETE']")).click();
                driver.findElement(By.xpath("//input[@value='OK']")).click();
                driver.findElement(By.xpath("//input[@value='Delete Issues']")).click();
                break;
            }
        }
    }

    @AfterSuite
    public void afterSute() {
        driver.close();
    }

}