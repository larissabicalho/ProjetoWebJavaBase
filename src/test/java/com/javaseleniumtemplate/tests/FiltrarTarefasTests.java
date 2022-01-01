package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Issues.AssignedPage;
import com.javaseleniumtemplate.pages.Issues.VerTarefasPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class FiltrarTarefasTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    VerTarefasPage verTarefasPage;

    //Tests
    @Test
    public void verTarefasMonitoradasComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);
        BuscarIssueDBSteps.inserirMonitoramento(idIssue);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmMonitoradoPor();
        verTarefasPage.selecionarMonitor(GlobalStaticParameters.opcaoMonitoramento);
        verTarefasPage.clicarEmAplicarFiltro();

        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue).replaceFirst("^0+(?!$)","");

        verTarefasPage.clicarEmRedefinirFiltro();

        Assert.assertEquals(idIssue, tarefa.replaceFirst("^0+(?!$)",""));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarMonitoramento(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }

    @Test
    public void verTarefasResolvidasComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueResolvido(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());



        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmRelacionados();
        verTarefasPage.selecionarRelacionados(GlobalStaticParameters.opcaoMonitoramento);
        verTarefasPage.clicarEmAplicarFiltro();

        String tarefa = verTarefasPage.verificarSeExisteTarefaResolvida(idIssue).replaceFirst("^0+(?!$)","");

        verTarefasPage.clicarEmRedefinirFiltro();

        Assert.assertEquals(idIssue, tarefa.replaceFirst("^0+(?!$)",""));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }


    @Test
    public void verTarefasFilterUrgenteComSucesso() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssueUrgente(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);


        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmVerTarefas();

        verTarefasPage.clicarEmPrioridade();
        verTarefasPage.selecionarPrioridade(GlobalStaticParameters.filtarUrgente);
        verTarefasPage.clicarEmAplicarFiltro();

        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue).replaceFirst("^0+(?!$)","");

        verTarefasPage.clicarEmRedefinirFiltro();

        Assert.assertEquals(idIssue, tarefa.replaceFirst("^0+(?!$)",""));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }

    @Test
    public void fecharUmaTarefa() throws FileNotFoundException {

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
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmVerTarefas();

        verTarefasPage.selecionarUmaTarefa(idIssue);
        verTarefasPage.clicarEmSelecionarUmaAcao(GlobalStaticParameters.acao);
        verTarefasPage.clicarEmOK();
        verTarefasPage.clicarEmFecharTarefa();
        verTarefasPage.clicarEmEstado();
        verTarefasPage.selecionarEstado(GlobalStaticParameters.estado);
        verTarefasPage.clicarEmAplicarFiltro();

        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue).replaceFirst("^0+(?!$)","");

        verTarefasPage.clicarEmRedefinirFiltro();

        Assert.assertEquals(idIssue, tarefa.replaceFirst("^0+(?!$)",""));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarMonitoramento(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void editarVerTarefasComSucesso() throws FileNotFoundException {


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
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmMinhaVisao();


        verTarefasPage.editarProjeto("000"+idIssue);


        String descricaoModificada = GerarDados.descricaoTextoModificado();
        verTarefasPage.limparEPreencher(descricaoModificada);
        verTarefasPage.clicarEmAtualizar();

        Assert.assertEquals(descricaoModificada,verTarefasPage.validarTextoModificado(descricaoModificada));

        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }



}
