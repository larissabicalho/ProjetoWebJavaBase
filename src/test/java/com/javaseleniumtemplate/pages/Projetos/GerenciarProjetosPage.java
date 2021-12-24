package com.javaseleniumtemplate.pages.Projetos;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class GerenciarProjetosPage extends PageBase {
    By GerenciarProjetos = By.xpath("//a[text()='Manage Projects']");
    By NovoProjeto = By.xpath("//button[text()='Create New Project']");
    By nomeProjeto = By.id("project-name");
    By clicarAdicionarProjeto = By.xpath("//input[@value='Add Project']");
    By categoriaLabel = By.xpath("//input[@name='name']");
    By clicarCategoria = By.xpath("//input[@value='Add Category']");
    By atualizarCategoria = By.xpath("//input[@value='Update Category']");
    String verificarCategoria = "//tr/td[text()='###']";
    By categoriaAlterar = By.id("proj-category-name");
    String alterarCategoria = "//tr/td[text()='###']/../td/div/div/form/fieldset/button[text()='Edit']";
    String apagarCategoria = "//tr/td[text()='###']/../td/div/div[2]/form/fieldset/button[text()='Delete']";
    By apagarCategoriaConfirmar = By.xpath("//input[@value='Delete Category']");

    public void clicarEmGerenciarProjetos() {
        click(GerenciarProjetos);
    }

    public void clicarEmNovoProjeto(){
        click(NovoProjeto);
    }

    public void preencherNomeProjeto(String projeto){
        sendKeys(nomeProjeto, projeto);
    }

    public void clicarEmCriarProjeto(){
        click(clicarAdicionarProjeto);
    }

    public boolean verificarSeElementoExiste(){
       return returnIfElementExists(clicarAdicionarProjeto);
    }

    public void preencherCategoria(String categoria){
        sendKeys(categoriaLabel, categoria);
    }

    public void clicarEmCategoria(){click(clicarCategoria);}

    public String verificarSeACategoriaExiste(String categoria){
        verificarCategoria = verificarCategoria.replace("###",categoria);
        return getText(By.xpath(verificarCategoria));
    }

    public boolean verificarSeACategoriaExisteApagar(String categoria){
        verificarCategoria = verificarCategoria.replace("###",categoria);
        return returnIfElementExists(By.xpath(verificarCategoria));
    }

    public void selecionarCategoriaAlterar(String categoria){
        alterarCategoria = alterarCategoria.replace("###",categoria);
         click(By.xpath(alterarCategoria));
    }

    public void preencherCategoriaAlterar(String categoria){
        clear(categoriaAlterar);
        sendKeys(categoriaAlterar, categoria);
    }

    public void selecionarCategoriaApagar(String categoria){
        apagarCategoria = apagarCategoria.replace("###",categoria);
        click(By.xpath(apagarCategoria));
    }

    public void clicarEmAtualizarCategoria(){click(atualizarCategoria);}

    public void clicarEmApagarCategoria(){click(apagarCategoriaConfirmar);}

}
