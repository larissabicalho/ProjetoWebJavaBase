package com.javaseleniumtemplate.pages.Marcadores;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;

public class GerenciarMarcadorPage extends PageBase {
    By clicarEmGerenciarMarcador = By.xpath("//a[text()='Manage Tags']");
    By nomeMarcador = By.id("tag-name");
    By descricao = By.id("tag-description");
    By clicarCriarMarcador = By.xpath("//input[@value='Create Tag']");
    By clicarApagarMarcador = By.xpath("//input[@value='Delete Tag']");
    String selecionarMarcador = "//a[text()='###']";


    public void clicarEmGerenciarMarcador() {
        click(clicarEmGerenciarMarcador);
    }

    public void preencherNomeMarcador(String marcador) {
        sendKeys(nomeMarcador, marcador);
    }

    public void preencherDescricao(String descricaoTexto) {
        sendKeys(descricao, descricaoTexto);
    }

    public void clicarEmCriarMarcador() {
        click(clicarCriarMarcador);
    }

    public void selecionarMarcadorLink(String nomeMarcador) {
        selecionarMarcador = selecionarMarcador.replace("###", nomeMarcador);
        click(By.xpath(selecionarMarcador));
    }

    public void clicarEmApagarMarcador() {
        click(clicarApagarMarcador);
    }

    public boolean verificarSeExiste(String nomeMarcador) {
        selecionarMarcador = selecionarMarcador.replace("###", nomeMarcador);

        try {
            click(By.xpath(selecionarMarcador));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
