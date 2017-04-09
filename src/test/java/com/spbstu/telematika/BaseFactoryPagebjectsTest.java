package com.spbstu.telematika;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by maxfromperek on 09.04.17.
 */
public class BaseFactoryPagebjectsTest {

    WebDriver driver;
    SoftAssert softAssert;

    String login = "administrator";
    String password = "21750";
    String actualField;

    @BeforeSuite
    public void beforeSuite() {

        //настройка веб драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MantisSite.init(driver);
        softAssert = new SoftAssert();

        //авторизация
        MantisSite.open();
        MantisSite.contactFormPage.fillLoginPasswordForm(login, password);
        MantisSite.contactFormPage.submitContactForm();
    }


    @AfterSuite
    public void afterSute() {
        driver.close();
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
                i++;
                driver.findElement(By.xpath("//table[@id='buglist']/tbody/tr[" + i + "]/td[1]//span[@class='lbl']")).click();
                MantisSite.issuePage.deleteIssues();
                break;
            }
        }
    }
}
