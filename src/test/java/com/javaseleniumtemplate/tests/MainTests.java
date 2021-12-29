package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Issues.AssignedPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class MainTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    AssignedPage assignedPage;

    //Tests
    @Test
    public void assignedComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueHandlerId(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmMinhaVisao();
        mainPage.clicarNoAtribuidos();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void UnAssignedComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.clicarNãoAtribuidos();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void relatadosComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.clicarRelatados();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void resolvidoComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueResolvido(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.clicarResolvidos();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }


    @Test
    public void monitoradosComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueResolvido(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);
        BuscarIssueDBSteps.inserirMonitoramento(idIssue);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmMinhaVisao();
        mainPage.clicarMonitorados();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarMonitoramento(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void modificadosComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueHandlerId(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        assignedPage = new AssignedPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.clicarEmModificados();

        Assert.assertTrue(assignedPage.verificarAssigned(idIssue));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void fazerLogout() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmAdministrador();
        mainPage.clicarEmSair();

        Assert.assertTrue(mainPage.verificarSeExisteOCampoUsername());


    }


}
