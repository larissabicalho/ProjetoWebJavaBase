package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class MainPage extends PageBase {
    //Mapping
    By usernameLoginInfoTextArea = By.xpath("//span[@class='user-info']");
    By reportIssueLink = By.xpath("//a[@href='/bug_report_page.php']");
    By mensagemDeErroEmail = By.xpath("//div[@class='alert alert-danger']/p");
    By lostPassword = By.xpath("//a[text()='Lost your password?']");
    By emailFielReset = By.id("email-field");
    By clicarEmGerenciarLink = By.xpath("//span[text()=' Manage ']");
    By clicarVerTarefas = By.xpath("//a[@href='/view_all_bug_page.php']/span");
    By clicarCriarTarefas = By.xpath("//span[text()=' Report Issue ']");
    By clicarAcompanhamentoDeTempo = By.xpath("//a[@href='/billing_page.php']/span");
    By clicarResumo = By.xpath("//a[@href='/summary_page.php']/span");
    By clicarMinhaVisao = By.xpath("//a[@href='/my_view_page.php']/span");
    By atribuidosParaMim = By.xpath("//div[@id='assigned']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    By naoAtribuidosParaMim = By.xpath("//div[@id='unassigned']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    By relatadosPorMim = By.xpath("//div[@id='reported']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    By resolvidosPorMim = By.xpath("//div[@id='resolved']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    By monitoradoPorMim = By.xpath("//div[@id='monitored']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    By modificadosPorMim = By.xpath("//div[@id='recent_mod']/div[@class='widget-header widget-header-small']/div[@class='widget-toolbar no-border hidden-xs']/div[@class='widget-menu']/a[text()='View Issues']");
    String verDetalhesLink = "//div[@id='unassigned']/div[@class='widget-body']/div[@class='widget-main no-padding']/div[@class='table-responsive']/table/tbody/tr/td/a[text()=###]";
    By logoutLink = By.xpath("//a[@href='/logout_page.php']");
    By selecionarAdministrador = By.xpath("(//a[@class='dropdown-toggle'])[2]");
    By verificarSeExisteUsername = By.id("username");

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

    public void clicarEmGerenciar(){
        click(clicarEmGerenciarLink);
    }

    public void clicarEmVerTarefas() { click(clicarVerTarefas); }

    public void clicarEmCriarTarefas() {click(clicarCriarTarefas); }

    public void clicarEmAcompanhamentoDeTempo() { click(clicarAcompanhamentoDeTempo); }

    public void clicarEmResumo(){ click(clicarResumo);}

    public void clicarNoAtribuidos() { click(atribuidosParaMim);}

    public void clicarNãoAtribuidos() { click(naoAtribuidosParaMim);}

    public void clicarRelatados() { click(relatadosPorMim);}

    public void clicarResolvidos() { click(resolvidosPorMim);}

    public void clicarMonitorados() { click(monitoradoPorMim);}

    public void clicarEmMinhaVisao() { click(clicarMinhaVisao);}

    public void clicarEmModificados() { click(modificadosPorMim);}

    public void verTarefaSelecionada(String idIssue) {
        verDetalhesLink = verDetalhesLink.replace("###",idIssue);
        click(By.xpath(verDetalhesLink));}

    public boolean verificarLinkTarefa(String idIssue) {
        verDetalhesLink = verDetalhesLink.replace("###",idIssue);
        return returnIfElementExists(By.xpath(verDetalhesLink));
    }

    public void clicarEmSair(){click(logoutLink);}

    public void clicarEmAdministrador(){click(selecionarAdministrador);}

    public boolean verificarSeExisteOCampoUsername(){
        return returnIfElementExists(verificarSeExisteUsername);
    }


}
