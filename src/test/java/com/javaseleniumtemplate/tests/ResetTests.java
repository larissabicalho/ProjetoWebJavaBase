package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.pages.LoginPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.ResetPage;
import com.javaseleniumtemplate.utils.Arquivos;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class ResetTests extends TestBase {
    //Objects
    LoginPage loginPage;
    MainPage mainPage;
    ResetPage resetPage;

    //Tests
    @Test
    public void efetuarResetDeSenhaComSucesso() throws FileNotFoundException {

        UsuariosDBSteps.insereUsuario();
        //Objects instances
        loginPage = new LoginPage();
        mainPage = new MainPage();
        resetPage = new ResetPage();


        //Parameteres
        String usuario =  UsuariosDBSteps.retornaUsername();
        String email =  UsuariosDBSteps.retornaEmail();

        //Test
        loginPage.preencherUsuario(usuario);
        loginPage.clicarEmLogin();

        mainPage.clicarEmEsqueceuSenha();
        resetPage.preencherEmailReset(email);
        resetPage.clicarResetButton();

        Assert.assertFalse(mainPage.verificarSeExisteOCampoEmail());

        UsuariosDBSteps.deletarUsuarioDB(usuario);

    }


}
