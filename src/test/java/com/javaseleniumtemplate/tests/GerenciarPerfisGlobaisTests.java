package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Marcadores.MarcadoresDBSteps;
import com.javaseleniumtemplate.dbsteps.PerfisGlobais.PerfisGlobaisDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Marcadores.GerenciarMarcadorPage;
import com.javaseleniumtemplate.pages.PerfisGlobais.GerenciarPerfisGlobaisPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GerenciarPerfisGlobaisTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    GerenciarPerfisGlobaisPage gerenciarPerfisGlobaisPage;

    //Tests
    @Test
    public void criarPerfisGlobaisComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarPerfisGlobaisPage = new GerenciarPerfisGlobaisPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarPerfisGlobaisPage.clicarEmGerenciarPerfisGlobais();
        String nomePlataforma = GerarDados.nomePlataforma();

        gerenciarPerfisGlobaisPage.preencherNomePlataforma(nomePlataforma);

        String nomeSO = GerarDados.nomeSO();
        gerenciarPerfisGlobaisPage.preencherNomeSo(nomeSO);

        String nomeVersaoSO = GerarDados.nomeVersaoSO();
        gerenciarPerfisGlobaisPage.preencherVersaoSo(nomeVersaoSO);

        String descricao = GerarDados.descricaoTexto();
        gerenciarPerfisGlobaisPage.preencherDescricaoPerfil(descricao);


        gerenciarPerfisGlobaisPage.clicarEmCriarPerfilGlobal();


       Assert.assertEquals(nomePlataforma, PerfisGlobaisDBSteps.retornaPlataforma());

       PerfisGlobaisDBSteps.deletarPerfisGlobaisDB(nomePlataforma);

    }

    @Test
    public void criarPerfisGlobaisComErro() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();

        gerenciarPerfisGlobaisPage = new GerenciarPerfisGlobaisPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarPerfisGlobaisPage.clicarEmGerenciarPerfisGlobais();

        String nomePlataforma = GerarDados.nomePlataforma();

        gerenciarPerfisGlobaisPage.clicarEmCriarPerfilGlobal();

        Assert.assertFalse(gerenciarPerfisGlobaisPage.verificarSeExistePerfisGlobais(nomePlataforma));

    }

}
