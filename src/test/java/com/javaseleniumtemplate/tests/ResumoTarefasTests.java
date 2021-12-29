package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Resumo.ResumoPage;
import com.javaseleniumtemplate.utils.Arquivos;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class ResumoTarefasTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    ResumoPage resumoPage;

    //Tests
    @Test
    public void verResumoComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        resumoPage = new ResumoPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmResumo();

        Assert.assertTrue(resumoPage.verificarSeElementoExiste());

    }

}
