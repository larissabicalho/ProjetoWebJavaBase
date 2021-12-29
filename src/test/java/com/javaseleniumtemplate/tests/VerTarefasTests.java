package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Issues.VerTarefasPage;
import com.javaseleniumtemplate.utils.Arquivos;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class VerTarefasTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    VerTarefasPage verTarefasPage;

    //Tests
    @Test
    public void verTarefasComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao, usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        Assert.assertTrue(verTarefasPage.verificarSeElementoExiste());


    }

    @Test
    public void exportarParaCSV() throws IOException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao, usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmExportarCSV();

        Assert.assertTrue(verTarefasPage.verificarSeContemArquivoNaPastaCSV());

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }


    @Test
    public void exportarParaExcel() throws IOException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao, usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmExportarExcel();

        Assert.assertTrue(verTarefasPage.verificarSeContemArquivoNaPastaExcel());

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void exportarPrint() throws IOException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao, usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmPrint();
        verTarefasPage.clicarEmExportarWord();

        Assert.assertTrue(verTarefasPage.verificarSeContemArquivoNaPastaDoc());

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }


}
