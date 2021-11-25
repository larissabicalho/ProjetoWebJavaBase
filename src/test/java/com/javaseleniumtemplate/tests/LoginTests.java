package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.pages.LoginPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    //Objects
    LoginPage loginPage;
    MainPage mainPage;

    //Tests
    @Test
    public void efetuarLoginComSucesso(){

        UsuariosDBSteps.insereUsuario();
        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();

        //Parameteres
        String usuario =  UsuariosDBSteps.retornaUsername();
        String senha = UsuariosDBSteps.retornaSenhaDoUsuarioDB(usuario);

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();
        String senhaX = Utils.readFileToAString("src/test/java/resources/json/userDados.json");
        loginPage.preencherSenha(senha);
        loginPage.clicarEmLogin();

        Assert.assertEquals(usuario, mainPage.retornaUsernameDasInformacoesDeLogin());

        UsuariosDBSteps.deletarUsuarioDB(usuario);

    }

}
