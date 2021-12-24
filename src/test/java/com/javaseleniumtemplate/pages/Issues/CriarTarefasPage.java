package com.javaseleniumtemplate.pages.Issues;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class CriarTarefasPage extends PageBase {
    By botaoSelecionarProjeto = By.xpath("//input[@value='Select Project']");
    By comboBoxSelecionarProjeto = By.id("select-project-id");
    By resumoArea = By.id("summary");
    By descricaoArea = By.id("description");
    By criarNovaTarefa = By.xpath("//input[@value='Submit Issue']");
    By fazerUpload = By.xpath("//input[@type='file']");
    By clicarDrop = By.xpath("//div[@class='dropzone center dz-clickable']/span");

    public boolean verificarSeElementoExiste(){
       return returnIfElementExists(botaoSelecionarProjeto);
    }

    public void clicarEmSelecionarProjeto(){ click(botaoSelecionarProjeto);}

    public void selecionarNoComboProjeto(String projectId){ comboBoxSelectByOption(comboBoxSelecionarProjeto, projectId);}

    public void preencherResumoArea(String resumo){ sendKeys(resumoArea,resumo);}

    public void preencherDescricaoArea(String descricao){ sendKeys(descricaoArea,descricao);}

    public void clicarEmNovaTarefa() throws InterruptedException { click(criarNovaTarefa);}

    public void clicarNoDropBox() {
        dropBox(fazerUpload);
    }

    public void clicarNoDrop(){
        click(clicarDrop);
    }

    public void preencherArquivo(String file){
        sendKeys(fazerUpload,file);
    }


}
