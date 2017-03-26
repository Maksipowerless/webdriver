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
import java.util.concurrent.TimeUnit;

/**
 * Created by maxfromperek on 14.03.17.
 */
public class BaseTest {

    WebDriver driver;
    SoftAssert softAssert;

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
        driver.findElement(By.id("password")).sendKeys(stringPassword );
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @AfterSuite
    public void afterSute(){
        //удаление добавленной задачи
        driver.findElement(By.xpath(("//table[@id='buglist']/tbody/tr[1]/td[1]//span[@class='lbl']"))).click();
        driver.findElement(By.xpath("//select[@name='action']//option[@value='DELETE']")).click();
        driver.findElement(By.xpath("//input[@value='OK']")).click();
        driver.findElement(By.xpath("//input[@value='Delete Issues']")).click();
        driver.close();
    }

}