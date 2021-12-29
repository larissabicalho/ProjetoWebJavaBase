package com.javaseleniumtemplate.pages.Projetos;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SelecionarProjetoPage extends PageBase {

    String validarTexto = "//a[@data-toggle='dropdown' and contains(text(),'###')]";

    public void clicarEmGerenciarProjetos(String projectName) {
        //passar para o pageBase
        Actions action = new Actions(driver);
        WebElement list = driver.findElement(By.id("dropdown_projects_menu"));
        list.click();
        action.moveToElement(list);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Now I need to scroll down till find my desire project in the list.

        WebElement Project = driver.findElement(By.xpath("//*[text()= ' " + projectName + " ']"));
        js.executeScript("arguments[0].click();", Project);
    }

    public String verificarSeEValido(String projectName) {
        validarTexto = validarTexto.replace("###", projectName);
        return getText(By.xpath(validarTexto));
    }

}
