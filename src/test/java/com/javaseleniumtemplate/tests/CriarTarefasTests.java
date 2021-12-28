package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.GlobalParameters;
import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Issues.VerTarefasPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Issues.CriarTarefasPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class CriarTarefasTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    CriarTarefasPage criarTarefasPage;
    VerTarefasPage verTarefasPage;

    //Tests
    @Test
    public void criarTarefasComSucesso() throws FileNotFoundException, InterruptedException {

        ProjetosDBSteps.insereProjeto();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        criarTarefasPage = new CriarTarefasPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmCriarTarefas();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
       // criarTarefasPage.selecionarNoComboProjeto(idProjeto);
      //  criarTarefasPage.clicarEmSelecionarProjeto();
        criarTarefasPage.preencherResumoArea(GerarDados.sumarioIssue());
        criarTarefasPage.preencherDescricaoArea(GerarDados.descricaoTexto());
        criarTarefasPage.clicarEmNovaTarefa();

        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);
        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue);

        Assert.assertEquals(idIssue , tarefa.replaceFirst("^0+(?!$)",""));


        System.out.println(ProjetosDBSteps.retornaDadosProjeto().get(1));
        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarTexto();
        BuscarIssueDBSteps.deletarBugHistory(idIssue);

    }

    @Test
    public void criarTarefasComAttachmentSucesso() throws FileNotFoundException, InterruptedException {

        File CAMINHO_DOCUMENTOS_ORIGEM = new File(GlobalStaticParameters.attachment);
        ProjetosDBSteps.insereProjeto();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        criarTarefasPage = new CriarTarefasPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmCriarTarefas();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        // criarTarefasPage.selecionarNoComboProjeto(idProjeto);
        //  criarTarefasPage.clicarEmSelecionarProjeto();
        criarTarefasPage.preencherResumoArea(GerarDados.sumarioIssue());
        criarTarefasPage.preencherDescricaoArea(GerarDados.descricaoTexto());

        criarTarefasPage.clicarNoDropBox();
        criarTarefasPage.clicarNoDrop();

        if(GlobalParameters.EXECUTION.equals("local")) {
            criarTarefasPage.preencherArquivo(CAMINHO_DOCUMENTOS_ORIGEM.getAbsolutePath()+"\\"+GlobalStaticParameters.file);
        } else {
            criarTarefasPage.preencherArquivo(GlobalParameters.UPLOAD + GlobalStaticParameters.file);
        }

        criarTarefasPage.clicarEmNovaTarefa();

        verTarefasPage.esperarElemento();

        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);
        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue);

        Assert.assertEquals(idIssue , tarefa.replaceFirst("^0+(?!$)",""));


        System.out.println(ProjetosDBSteps.retornaDadosProjeto().get(1));
        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarAttachment();
        BuscarIssueDBSteps.deletarTexto();
        BuscarIssueDBSteps.deletarBugHistory(idIssue);

    }

}
