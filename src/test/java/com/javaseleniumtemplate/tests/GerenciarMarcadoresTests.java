package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Marcadores.MarcadoresDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Marcadores.GerenciarMarcadorPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GerenciarMarcadoresTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    GerenciarMarcadorPage gerenciarMarcadorPage;

    //Tests
    @Test
    public void criarMarcadorComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarMarcadorPage = new GerenciarMarcadorPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarMarcadorPage.clicarEmGerenciarMarcador();
        String nomeMarcador = GerarDados.nomeMarcador();
        gerenciarMarcadorPage.preencherNomeMarcador(nomeMarcador);
        String descricao = GerarDados.descricaoTexto();
        gerenciarMarcadorPage.preencherDescricao(descricao);
        gerenciarMarcadorPage.clicarEmCriarMarcador();


        Assert.assertEquals(nomeMarcador, MarcadoresDBSteps.retornaName());

        MarcadoresDBSteps.deletarMarcadorDB(nomeMarcador);
        BuscarIssueDBSteps.deletarBugTexto();

    }

    @Test
    public void deletarMarcadorComSucesso() throws FileNotFoundException {

        MarcadoresDBSteps.insereMarcador();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarMarcadorPage = new GerenciarMarcadorPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarMarcadorPage.clicarEmGerenciarMarcador();


        String nomeMarcador = MarcadoresDBSteps.retornaName();

        gerenciarMarcadorPage.selecionarMarcadorLink(nomeMarcador);

        gerenciarMarcadorPage.clicarEmApagarMarcador();

        gerenciarMarcadorPage.clicarEmApagarMarcador();

        Assert.assertFalse(gerenciarMarcadorPage.verificarSeExiste(nomeMarcador));

    }

    @Test
    public void criarMarcadorSemNomeObrigatorio() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarMarcadorPage = new GerenciarMarcadorPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarMarcadorPage.clicarEmGerenciarMarcador();

        String nomeMarcador = GerarDados.nomeMarcador();
        gerenciarMarcadorPage.clicarEmCriarMarcador();

        Assert.assertFalse(gerenciarMarcadorPage.verificarSeExiste(nomeMarcador));

    }

}
