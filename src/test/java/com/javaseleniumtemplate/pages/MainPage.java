package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class MainPage extends PageBase {
    //Mapping
    By usernameLoginInfoTextArea = By.xpath("//span[@class='user-info']");
    By reportIssueLink = By.xpath("//a[@href='/bug_report_page.php']");
    By mensagemDeErroEmail = By.xpath("//div[@class='alert alert-danger']/p");
    By lostPassword = By.xpath("//a[text()='Perdeu a sua senha?']");
    By emailFielReset = By.id("email-field");

    //Actions
    public String retornaUsernameDasInformacoesDeLogin(){
        return getText(usernameLoginInfoTextArea);
    }

    public String retornaMensagemErro(){
        return getText(mensagemDeErroEmail);
    }

    public void clicarEmEsqueceuSenha(){
        click(lostPassword);
    }

    public void clicarEmReportIssue(){
        click(reportIssueLink);
    }

    public boolean verificarSeExisteOCampoEmail(){
       return returnIfElementExists(emailFielReset);
    }

}
