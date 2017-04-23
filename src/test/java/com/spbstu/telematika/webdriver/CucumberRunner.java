package com.spbstu.telematika.webdriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.asserts.SoftAssert;

/**
 * Created by maxfromperek on 23.04.17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/resourses/features"},
        glue = {"com.spbstu.telematika/webdriver.stepdefs"}
)
public class CucumberRunner {

}
