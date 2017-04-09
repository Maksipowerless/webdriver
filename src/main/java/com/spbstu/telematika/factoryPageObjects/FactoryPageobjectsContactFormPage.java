package com.spbstu.telematika.factoryPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by maxfromperek on 04.04.17.
 */
public class FactoryPageobjectsContactFormPage {

   @FindBy(id = "username" )
    WebElement username;

   @FindBy(id = "password")
    WebElement password;

   @FindBy(css = "[type = 'submit']")
    WebElement buttonSubmit;

   public void fillLoginPasswordForm(String login, String password){
       this.username.sendKeys(login);
       this.password.sendKeys(password);
   }

    public void submitContactForm() {
        this.buttonSubmit.click();
    }

}
