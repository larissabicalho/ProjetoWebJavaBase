package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Projetos.GerenciarProjetosPage;
import com.javaseleniumtemplate.pages.Projetos.SelecionarProjetoPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class SelecionarProjetoTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    SelecionarProjetoPage selecionarProjetoPage;

    //Tests
    @Test
    public void criarProjetoComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String projectName = ProjetosDBSteps.retornaName();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        selecionarProjetoPage = new SelecionarProjetoPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        selecionarProjetoPage.clicarEmGerenciarProjetos(projectName);

        String nomeProjeto = projectName.replace(" ","");
        Assert.assertEquals(nomeProjeto,selecionarProjetoPage.verificarSeEValido(projectName).replace(" ",""));

        ProjetosDBSteps.deletarProjetoDB(projectName);

    }


}
