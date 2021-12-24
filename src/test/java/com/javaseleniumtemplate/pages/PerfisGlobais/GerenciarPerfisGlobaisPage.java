package com.javaseleniumtemplate.pages.PerfisGlobais;

import com.javaseleniumtemplate.bases.PageBase;
import com.javaseleniumtemplate.dbsteps.PerfisGlobais.PerfisGlobaisDBSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class GerenciarPerfisGlobaisPage extends PageBase {
    By clicarEmGerenciarPerfisGlobais = By.xpath("//a[text()='Manage Global Profiles']");
    By nomePlataforma = By.id("platform");
    By nomeSO = By.id("os");
    By versaoSO = By.id("os-version");
    By descricaoPerfil = By.id("description");
    By clicarCriarPerfilGlobal = By.xpath("//input[@value='Add Profile']");

    public void clicarEmGerenciarPerfisGlobais() {click(clicarEmGerenciarPerfisGlobais);}

    public void preencherNomePlataforma(String plataforma){
        sendKeys(nomePlataforma, plataforma);
    }

    public void preencherNomeSo(String SO){
        sendKeys(nomeSO, SO);
    }

    public void preencherVersaoSo(String versao){
        sendKeys(versaoSO, versao);
    }

    public void preencherDescricaoPerfil(String descricao){
        sendKeys(descricaoPerfil, descricao);
    }


    public void clicarEmCriarPerfilGlobal(){ click(clicarCriarPerfilGlobal);}

    public boolean verificarSeExistePerfisGlobais(String texto){
        try {
            Select perfisGlobais = new Select(driver.findElement(By.id("select-profile")));
            perfisGlobais.selectByVisibleText(texto);
            return true;
        }
        catch (Exception e) {
            return false;
        }


    }


}
