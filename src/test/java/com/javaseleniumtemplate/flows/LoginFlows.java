package com.javaseleniumtemplate.flows;

import com.javaseleniumtemplate.pages.LoginPage;

By botaoDiminuir = By.xpath("//div[@class='sidebar sidebar-fixed responsive compact']");

public class LoginFlows {
    //Objects and constructor
    LoginPage loginPage;

    public LoginFlows(){
        loginPage = new LoginPage();
    }

    //Flows
    public void efetuarLogin(String username, String password){
        loginPage.preencherUsuario(username);
        loginPage.clicarEmLogin();
        loginPage.preencherSenha(password);
        loginPage.clicarEmLogin();
        if(returnIfElementExists(botaoDiminuir)){
           loginPage.clicarDiminuirIcones();
        }
    }
}
