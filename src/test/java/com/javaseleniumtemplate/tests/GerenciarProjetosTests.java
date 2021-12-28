package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Categoria.CategoriaDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Projetos.GerenciarProjetosPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.ExecutarJavaScriptNode;
import com.javaseleniumtemplate.utils.GerarDados;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GerenciarProjetosTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    GerenciarProjetosPage gerenciarProjetosPage;

    //Tests
    @Test
    public void criarProjetoComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.clicarEmNovoProjeto();

        String nomeProjeto = GerarDados.nomeProjeto();
        gerenciarProjetosPage.preencherNomeProjeto(nomeProjeto);
        gerenciarProjetosPage.clicarEmCriarProjeto();


        Assert.assertEquals(nomeProjeto, ProjetosDBSteps.retornaName());

        ProjetosDBSteps.deletarProjetoDB(nomeProjeto);

    }

    @Test
    public void criarProjetoComNodeJS() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.clicarEmNovoProjeto();

        String nomeProjeto = "PROJETO" + ExecutarJavaScriptNode.executaJavaScriptAleatorio();
        gerenciarProjetosPage.preencherNomeProjeto(nomeProjeto);
        gerenciarProjetosPage.clicarEmCriarProjeto();


        Assert.assertEquals(nomeProjeto, ProjetosDBSteps.retornaName());

        ProjetosDBSteps.deletarProjetoDB(nomeProjeto);

    }


    @Test
    public void criarCategoria() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        String categoria = GerarDados.categoriaName();
        gerenciarProjetosPage.preencherCategoria(categoria);
        gerenciarProjetosPage.clicarEmCategoria();

        Assert.assertEquals(categoria, gerenciarProjetosPage.verificarSeACategoriaExiste(categoria));

        CategoriaDBSteps.deletarCategoriaDB(categoria);
    }

    @Test
    public void editarCategoria() throws FileNotFoundException {


        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        CategoriaDBSteps.insereCategoria();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        String categoria = CategoriaDBSteps.retornaCategoriaName();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.selecionarCategoriaAlterar(categoria);
        String categoriaAlterada = GerarDados.categoriaNameAlterar();
        gerenciarProjetosPage.preencherCategoriaAlterar(categoriaAlterada);
        gerenciarProjetosPage.clicarEmAtualizarCategoria();

        Assert.assertEquals(categoriaAlterada, gerenciarProjetosPage.verificarSeACategoriaExiste(categoriaAlterada));

        CategoriaDBSteps.deletarCategoriaDB(categoriaAlterada);
    }


    @Test
    public void deletarCategoria() throws FileNotFoundException {


        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        CategoriaDBSteps.insereCategoria();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        String categoria = CategoriaDBSteps.retornaCategoriaName();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.selecionarCategoriaApagar(categoria);
        gerenciarProjetosPage.clicarEmApagarCategoria();

        Assert.assertFalse(gerenciarProjetosPage.verificarSeACategoriaExisteApagar(categoria));
    }

    @Test
    public void criarProjetoAssertComNode() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.clicarEmNovoProjeto();

        String nomeProjeto = "PROJETO" + ExecutarJavaScriptNode.executaJavaScriptAleatorio();
        gerenciarProjetosPage.preencherNomeProjeto(nomeProjeto);
        gerenciarProjetosPage.clicarEmCriarProjeto();

        Boolean validar = ExecutarJavaScriptNode.executaJavaScript(nomeProjeto, ProjetosDBSteps.retornaName());

        Assert.assertTrue(validar);

        ProjetosDBSteps.deletarProjetoDB(nomeProjeto);

    }






}
