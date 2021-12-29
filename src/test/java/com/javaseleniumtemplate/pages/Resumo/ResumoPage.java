package com.javaseleniumtemplate.pages.Resumo;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;

public class ResumoPage extends PageBase {
    By showResumo = By.xpath("//ul[@class='nav nav-tabs padding-18']/li/a");

    public boolean verificarSeElementoExiste() {
        return returnIfElementExists(showResumo);
    }


}
