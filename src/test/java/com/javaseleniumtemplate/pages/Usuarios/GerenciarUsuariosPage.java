package com.javaseleniumtemplate.pages.Usuarios;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class GerenciarUsuariosPage extends PageBase {
    By clicarEmGerenciarUsuarios = By.xpath("//a[text()='Manage Users']");
    By clicarEmNovaConta = By.xpath("//a[text()='Create New Account']");
    By nomeUsuario = By.id("user-username");
    By nomeVerdadeiro = By.id("user-realname");
    By email = By.id("email-field");
    By clicarCriarUsuario = By.xpath("//input[@value='Create User']");
    By validarMensagemDeErro = By.xpath("//p[@class='bold']");

    public void clicarEmGerenciarUsuarios() {
        click(clicarEmGerenciarUsuarios);
    }

    public void clicarEmNovaConta(){
        click(clicarEmNovaConta);
    }

    public void preencherUsuario(String usuario){
        sendKeys(nomeUsuario, usuario);
    }

    public void preencherNomeVerdadeiro(String nomeVerdadeiroTexto){
        sendKeys(nomeVerdadeiro, nomeVerdadeiroTexto);
    }

    public void preencherEmail(String emailTexto){
        sendKeys(email, emailTexto);
    }

    public void clicarEmCriarUsuario(){
        click(clicarCriarUsuario);
    }

    public String validarTextoErro() {
       return getText(validarMensagemDeErro);
    }

}
