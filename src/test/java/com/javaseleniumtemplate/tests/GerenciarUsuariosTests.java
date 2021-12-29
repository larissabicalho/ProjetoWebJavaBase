package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Usuarios.GerenciarUsuariosPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.ExecutarJavaScriptNode;
import com.javaseleniumtemplate.utils.GerarDados;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GerenciarUsuariosTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    GerenciarUsuariosPage gerenciarUsuariosPage;

    //Tests
    @Test
    public void criarUsuarioComSucesso() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarUsuariosPage = new GerenciarUsuariosPage();

        String username = GerarDados.nomeUser();
        String fullName = GerarDados.fullName();
        String email = GerarDados.email();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmGerenciar();

        gerenciarUsuariosPage.clicarEmGerenciarUsuarios();
        gerenciarUsuariosPage.clicarEmNovaConta();
        gerenciarUsuariosPage.preencherUsuario(username);
        gerenciarUsuariosPage.preencherNomeVerdadeiro(fullName);
        gerenciarUsuariosPage.preencherEmail(email);
        gerenciarUsuariosPage.clicarEmCriarUsuario();


        Assert.assertEquals(username, UsuariosDBSteps.retornaUsername());

        UsuariosDBSteps.deletarEmailDB(email);
        UsuariosDBSteps.deletarUsuarioDB(username);

    }

    @Test
    public void criarUsuarioErrado() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarUsuariosPage = new GerenciarUsuariosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmGerenciar();
        gerenciarUsuariosPage.clicarEmGerenciarUsuarios();
        gerenciarUsuariosPage.clicarEmNovaConta();
        gerenciarUsuariosPage.clicarEmCriarUsuario();


        Assert.assertEquals(GlobalStaticParameters.mensagemErroUsuario, gerenciarUsuariosPage.validarTextoErro());


    }

    @Test
    public void criarUsuarioComSucessoNodeJS() throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarUsuariosPage = new GerenciarUsuariosPage();

        String username = "USER" + ExecutarJavaScriptNode.executaJavaScriptAleatorio();
        String fullName = GerarDados.fullName();
        String email = GerarDados.email();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmGerenciar();

        gerenciarUsuariosPage.clicarEmGerenciarUsuarios();
        gerenciarUsuariosPage.clicarEmNovaConta();
        gerenciarUsuariosPage.preencherUsuario(username);
        gerenciarUsuariosPage.preencherNomeVerdadeiro(fullName);
        gerenciarUsuariosPage.preencherEmail(email);
        gerenciarUsuariosPage.clicarEmCriarUsuario();


        Assert.assertEquals(username, UsuariosDBSteps.retornaUsername());

        UsuariosDBSteps.deletarEmailDB(email);
        UsuariosDBSteps.deletarUsuarioDB(username);

    }


}
