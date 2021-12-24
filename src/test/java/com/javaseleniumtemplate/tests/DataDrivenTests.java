package com.javaseleniumtemplate.tests;


import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Marcadores.MarcadoresDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Issues.CriarTarefasPage;
import com.javaseleniumtemplate.pages.Issues.VerTarefasPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.pages.Marcadores.GerenciarMarcadorPage;
import com.javaseleniumtemplate.pages.Projetos.GerenciarProjetosPage;
import com.javaseleniumtemplate.pages.Usuarios.GerenciarUsuariosPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;


public class DataDrivenTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    GerenciarProjetosPage gerenciarProjetosPage;
    GerenciarMarcadorPage gerenciarMarcadorPage;
    GerenciarUsuariosPage gerenciarUsuariosPage;
    CriarTarefasPage criarTarefasPage;
    VerTarefasPage verTarefasPage;


    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "src/test/java/com/javaseleniumtemplate/utils/TestData.xls";
    public static String FilePath2 = "src/test/java/com/javaseleniumtemplate/utils/Marcador.xls";
    public static String FilePath3 = "src/test/java/com/javaseleniumtemplate/utils/Usuario.xls";
    public static String FilePath4 = "src/test/java/com/javaseleniumtemplate/utils/Issue.xls";
    public static String SheetName = "TestData";
    public static HSSFSheet Sheet;
    ReadExcel ex = new ReadExcel();

    @DataProvider
    public static Object[][] getProjetoData() throws Exception {

        Sheet = ReadExcel.DataSheet(FilePath, SheetName);
        // Get username and passsword from testdata.xls
        int records = Sheet.getPhysicalNumberOfRows();
        int rows = 1;

        Object[][] values = new Object[records][rows];
            //loop over the rows
           for (int j = 0; j < (records) ; j++) {
               if (Sheet.getRow(j) != null) {
                     values[j][rows-1] = String.valueOf(Sheet.getRow(j).getCell(rows - 1));
                  }
            }

        return values;
    }

    @DataProvider
    public static Object[][] getMarcadorData() throws Exception {

        Sheet = ReadExcel.DataSheet(FilePath2, SheetName);
        // Get username and passsword from testdata.xls
        int records = Sheet.getPhysicalNumberOfRows();
        int rows = 2;

        Object[][] values = new Object[records][rows];
        //loop over the rows
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < (records); j++) {
                if (Sheet.getRow(j) != null) {
                    values[j][i] = String.valueOf(Sheet.getRow(j).getCell(i));
                }
            }
        }
        return values;
    }

    @DataProvider
    public static Object[][] getUsuarioData() throws Exception {

        Sheet = ReadExcel.DataSheet(FilePath3, SheetName);
        // Get username and passsword from testdata.xls
        int records = Sheet.getPhysicalNumberOfRows();
        int rows = 3;

        Object[][] values = new Object[records][rows];
        //loop over the rows
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < (records); j++) {
                if (Sheet.getRow(j) != null) {
                    values[j][i] = String.valueOf(Sheet.getRow(j).getCell(i));
                }
            }
        }
        return values;
    }

    @DataProvider
    public static Object[][] getIssueData() throws Exception {

        Sheet = ReadExcel.DataSheet(FilePath4, SheetName);
        // Get username and passsword from testdata.xls
        int records = Sheet.getPhysicalNumberOfRows();
        int rows = 2;

        Object[][] values = new Object[records][rows];
        //loop over the rows
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < (records); j++) {
                System.out.println(j);
                if (Sheet.getRow(j) != null) {
                    values[j][i] = String.valueOf(Sheet.getRow(j).getCell(i));
                }
            }
        }
        return values;
    }


    @Test(dataProvider = "getProjetoData")
    public void criarTesteDataDriven(String projetoName) throws Exception {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarProjetosPage = new GerenciarProjetosPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarProjetosPage.clicarEmGerenciarProjetos();
        gerenciarProjetosPage.clicarEmNovoProjeto();

        gerenciarProjetosPage.preencherNomeProjeto(projetoName);
        gerenciarProjetosPage.clicarEmCriarProjeto();


        Assert.assertEquals(projetoName, ProjetosDBSteps.retornaName());

        ProjetosDBSteps.deletarProjetoDB(projetoName);

    }


    @Test(dataProvider = "getMarcadorData")
    public void criarMarcadorComSucesso(String marcadorName, String descricao) throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();

        gerenciarMarcadorPage = new GerenciarMarcadorPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        mainPage.clicarEmGerenciar();

        gerenciarMarcadorPage.clicarEmGerenciarMarcador();
        gerenciarMarcadorPage.preencherNomeMarcador(marcadorName);
        gerenciarMarcadorPage.preencherDescricao(descricao);
        gerenciarMarcadorPage.clicarEmCriarMarcador();


        Assert.assertEquals(marcadorName, MarcadoresDBSteps.retornaName());

        MarcadoresDBSteps.deletarMarcadorDB(marcadorName);

    }


    @Test(dataProvider = "getUsuarioData")
    public void criarUsuarioComSucesso(String username, String fullName, String email) throws FileNotFoundException {

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        gerenciarUsuariosPage = new GerenciarUsuariosPage();

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

    @Test(dataProvider = "getIssueData")
    public void criarTarefasComSucesso(String sumario, String descricao) throws FileNotFoundException, InterruptedException {

        ProjetosDBSteps.insereProjeto();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        criarTarefasPage = new CriarTarefasPage();
        verTarefasPage = new VerTarefasPage();

        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

     //   String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);

        mainPage.clicarEmCriarTarefas();


     //   criarTarefasPage.selecionarNoComboProjeto(idProjeto);
     //   criarTarefasPage.clicarEmSelecionarProjeto();

        criarTarefasPage.preencherResumoArea(sumario);
        criarTarefasPage.preencherDescricaoArea(descricao);
        criarTarefasPage.clicarEmNovaTarefa();

        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        String tarefa = verTarefasPage.verificarSeExisteTarefa(idIssue);

        Assert.assertEquals(idIssue,  tarefa.replaceFirst("^0+(?!$)",""));

        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTexto();
        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));

    }


}
