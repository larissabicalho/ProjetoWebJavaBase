package com.javaseleniumtemplate.pages.Projetos;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;


public class SelecionarProjetoPage extends PageBase {

    String validarTexto = "//a[@data-toggle='dropdown' and contains(text(),'###')]";
    String dropProject = "dropdown_projects_menu";

    public void clicarEmGerenciarProjetos(String projectName) {
        dropDrown(projectName, dropProject);
    }

    public String verificarSeEValido(String projectName) {
        validarTexto = validarTexto.replace("###", projectName);
        return getText(By.xpath(validarTexto));
    }

}
