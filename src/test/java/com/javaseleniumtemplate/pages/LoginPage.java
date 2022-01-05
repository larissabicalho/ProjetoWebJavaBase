package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;

public class LoginPage extends PageBase {
    //Mapping
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//input[@type='submit']");
    By botaoDiminuir = By.xpath("//div[@class='sidebar sidebar-fixed responsive compact']");

    //Actions
    public void preencherUsuario(String usuario) {
        sendKeys(usernameField, usuario);
    }

    public void preencherSenha(String senha) {
        sendKeys(passwordField, senha);
    }

    public void clicarEmLogin() {
        click(loginButton);
    }
    
    public void clicarDiminuirIcones(){
        click(botaoDiminuir);
    }
    
    
    public Boolean elementoDiminuirIcones(){
       return returnIfElementExists(botaoDiminuir);
    }

}
