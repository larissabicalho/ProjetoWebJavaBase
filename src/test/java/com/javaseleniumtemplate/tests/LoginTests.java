package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.pages.LoginPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class LoginTests extends TestBase {
    //Objects
    LoginPage loginPage;
    MainPage mainPage;

    //Tests
    @Test
    public void efetuarLoginComSucesso() throws FileNotFoundException {

        UsuariosDBSteps.insereUsuario();
        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario =  UsuariosDBSteps.retornaUsername();

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();
        Users usersDados = Arquivos.getJsonUsers();
        loginPage.preencherSenha(usersDados.getPassword());
        loginPage.clicarEmLogin();

        Assert.assertEquals(usuario, mainPage.retornaUsernameDasInformacoesDeLogin());

        UsuariosDBSteps.deletarUsuarioDB(usuario);

    }

    @Test
    public void efetuarLoginComEmailErrado() throws FileNotFoundException {

        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario = GlobalStaticParameters.user;

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();
        Users usersDados = Arquivos.getJsonUsers();
        loginPage.preencherSenha(usersDados.getPassword());
        loginPage.clicarEmLogin();

        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaMensagemErro());

    }

    @Test
    public void efetuarLoginComSenhaErrada() throws FileNotFoundException {


        UsuariosDBSteps.insereUsuario();
        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario =  UsuariosDBSteps.retornaUsername();

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();

        loginPage.preencherSenha(GlobalStaticParameters.senha);
        loginPage.clicarEmLogin();

        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaUsernameDasInformacoesDeLogin());

        UsuariosDBSteps.deletarUsuarioDB(usuario);

    }

    @Test
    public void efetuarLoginComEmailBranco() {

        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario =  "";

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();

        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaMensagemErro());


    }

    @Test
    public void efetuarLoginComSenhaBranco() throws FileNotFoundException {


        UsuariosDBSteps.insereUsuario();
        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario =  UsuariosDBSteps.retornaUsername();
        String senha =  "";

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();

        loginPage.preencherSenha(senha);
        loginPage.clicarEmLogin();

        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaMensagemErro());

        UsuariosDBSteps.deletarUsuarioDB(usuario);

    }

    @Test
    public void efetuarLoginComUsuarioBrancoeSenhaBranco() throws FileNotFoundException {

        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario = "";

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();


        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaMensagemErro());

    }

    @Test
    public void efetuarLoginUsernameCaracterEspecial() throws FileNotFoundException {

        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario = GlobalStaticParameters.userComCaracterEspecial;

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();


        Assert.assertEquals(GlobalStaticParameters.mensagemErroEmail, mainPage.retornaMensagemErro());

    }


}
