package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;

public class ResetPage extends PageBase {
    By emailReset = By.xpath("//input[@id='email-field']");
    By resetButton = By.xpath("//input[@type='submit']");

    public void preencherEmailReset(String email) {
        sendKeys(emailReset, email);
    }

    public void clicarResetButton() {
        click(resetButton);
    }

}
