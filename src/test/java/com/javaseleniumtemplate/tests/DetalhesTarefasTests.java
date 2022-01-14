package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.dbsteps.Issues.BuscarIssueDBSteps;
import com.javaseleniumtemplate.dbsteps.Projetos.ProjetosDBSteps;
import com.javaseleniumtemplate.dbsteps.Usuarios.UsuariosDBSteps;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.enums.Users;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.Issues.VerTarefasPage;
import com.javaseleniumtemplate.pages.MainPage;
import com.javaseleniumtemplate.utils.Arquivos;
import com.javaseleniumtemplate.utils.GerarDados;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DetalhesTarefasTests extends TestBase {

    LoginFlows loginFlows;
    MainPage mainPage;
    VerTarefasPage verTarefasPage;

    //Tests
    @Test
    public void detalhesVerTarefasComSucesso() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);


        Assert.assertEquals(idIssue, verTarefasPage.idTarefaCerta().replaceFirst("^0+(?!$)",""));


        System.out.println("detalhesVerTarefasComSucesso" + ProjetosDBSteps.retornaDadosProjeto().get(1));
        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void copiarTarefasComSucesso() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);

        verTarefasPage.clicarEmCopiarTarefa();
        verTarefasPage.clicarEmCriarNovaTarefaCopiada();

        String idIssueCopiada = BuscarIssueDBSteps.retornaDadosIssue().get(0);

        Assert.assertEquals(idIssueCopiada, verTarefasPage.idTarefaCerta().replaceFirst("^0+(?!$)",""));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssueCopiada);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssueCopiada);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }

    @Test
    public void atualizarStatusUmaTarefa() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);

        verTarefasPage.clicarEmSelecionarUmStatus(GlobalStaticParameters.resolver);
        verTarefasPage.clicarEmAlterarStatus();
        verTarefasPage.clicarEmResolverTarefa();


        Assert.assertEquals(verTarefasPage.verificarSeEstaResolvido(), GlobalStaticParameters.resolvido);





        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarMonitoramento(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }


    @Test
    public void atribuirStatusUmaTarefa() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        String idIssue = BuscarIssueDBSteps.retornaDadosIssue().get(0);
        UsuariosDBSteps.insereUsuario();

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());


        String username = UsuariosDBSteps.retornaUsername();


        mainPage.clicarEmMinhaVisao();
        mainPage.verTarefaSelecionada("000"+idIssue);

        verTarefasPage.clicarEmSelecionarUmUsuarioAtribuido(username);
        verTarefasPage.clicarEmSelecionarUmUsuario();


        Assert.assertEquals(verTarefasPage.verificarSelecionarUmUsuario(username), username);



        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        UsuariosDBSteps.deletarUsuarioDB(username);
        BuscarIssueDBSteps.deletarMonitoramento(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }


    @Test
    public void relacionarStatusUmaTarefa() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        BuscarIssueDBSteps.insereTexto();
        String idTextoIssue2 = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTextoIssue2);
        ArrayList<String> idsIssues = BuscarIssueDBSteps.retornaDadosTodasIssueIdProjeto(idProjeto);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.verTarefaSelecionada("000"+idsIssues.get(0));

        verTarefasPage.preencherTarefaRelacionada(idsIssues.get(3));
        verTarefasPage.adicionarRelacao();


        Assert.assertEquals(verTarefasPage.verificarSeExisteTarefaSub(idsIssues.get(3)).replaceFirst("^0+(?!$)",""), idsIssues.get(3));


        String idIssue = idsIssues.get(0);
        String idIssue1 = idsIssues.get(3);


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarRelationship(idsIssues.get(0));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarIssueId(idIssue1);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue1);
        BuscarIssueDBSteps.deletarTextoId(idTexto);
        BuscarIssueDBSteps.deletarTextoId(idTextoIssue2);

    }

    @Test
    public void anotacoesVerTarefasComSucesso() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);

        String anotacaoTexto = GerarDados.descricaoAnotacoes();

        verTarefasPage.preencherAnotacoes(anotacaoTexto);
        verTarefasPage.clicarEmAdicionarAnotacao();


        Assert.assertEquals(anotacaoTexto, verTarefasPage.verificarAnotacoes());


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        String idNote = BuscarIssueDBSteps.retornarIdBugNote();
        String idNoteText = BuscarIssueDBSteps.retornarIdBugNoteText();
        BuscarIssueDBSteps.deletarNoteText(idNoteText);
        BuscarIssueDBSteps.deletarNote(idNote);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }


    @Test
    public void apagarAnotacoesVerTarefasComSucesso() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);

        String anotacaoTexto = GerarDados.descricaoAnotacoes();

        verTarefasPage.preencherAnotacoes(anotacaoTexto);
        verTarefasPage.clicarEmAdicionarAnotacao();
        verTarefasPage.clicarEmAnotacao();
        verTarefasPage.clicarEmApagarAnotacoes();
        verTarefasPage.clicarEmApagarAnotacoesConfirmar();

        Assert.assertFalse(verTarefasPage.verificarSeOElementoExisteAnotacoes());



        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void editarAnotacoesVerTarefasComSucesso() throws FileNotFoundException {

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
        mainPage.verTarefaSelecionada("000"+idIssue);


        String anotacaoTexto = GerarDados.descricaoAnotacoes();

        verTarefasPage.preencherAnotacoes(anotacaoTexto);
        verTarefasPage.clicarEmAdicionarAnotacao();
        verTarefasPage.clicarEmAnotacao();
        verTarefasPage.clicarEmEditarAnotacoes();

        String anotacoesTextoModificado = GerarDados.descricaoAnotacoesModificado();

        verTarefasPage.limparEPreencherAnotacoes(anotacoesTextoModificado);
        verTarefasPage.clicarEmAtualizar();


        Assert.assertEquals(anotacoesTextoModificado, verTarefasPage.verificarAnotacoes());



        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        String idNote = BuscarIssueDBSteps.retornarIdBugNote();
        String idNoteText = BuscarIssueDBSteps.retornarIdBugNoteText();
        BuscarIssueDBSteps.deletarNoteText(idNoteText);
        BuscarIssueDBSteps.deletarNote(idNote);
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);

    }

    @Test
    public void adicionarMarcadorUmaTarefa() throws FileNotFoundException {

        ProjetosDBSteps.insereProjeto();
        String idProjeto = ProjetosDBSteps.retornaDadosProjeto().get(0);
        BuscarIssueDBSteps.insereTexto();
        String idTexto = BuscarIssueDBSteps.retornaDadosTexto().get(0);
        BuscarIssueDBSteps.insereIssue(idProjeto, idTexto);
        ArrayList<String> idsIssues = BuscarIssueDBSteps.retornaDadosTodasIssueIdProjeto(idProjeto);

        loginFlows = new LoginFlows();
        mainPage = new MainPage();
        verTarefasPage = new VerTarefasPage();


        Users usersDados = Arquivos.getJsonUsersPadrao();
        loginFlows.efetuarLogin(GlobalStaticParameters.userPadrao,usersDados.getPassword());

        mainPage.clicarEmMinhaVisao();
        mainPage.verTarefaSelecionada("000"+idsIssues.get(0));

        String descricaoTag = GerarDados.tagName();

        verTarefasPage.adicionarMarcadorIssue(descricaoTag);
        verTarefasPage.clicarEmAplicar();

        Assert.assertEquals(descricaoTag, verTarefasPage.verificarTag(descricaoTag));


        BuscarIssueDBSteps.deletarTags();
        BuscarIssueDBSteps.deletarBugTags();

        String idIssue = idsIssues.get(0);





        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarBugTags();
        BuscarIssueDBSteps.deletarTags();
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);


    }

    @Test
    public void apagarTarefasComSucesso() throws FileNotFoundException {

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

        mainPage.verTarefaSelecionada("000"+idIssue);

        verTarefasPage.clicarEmApagarTarefa();
        verTarefasPage.clicarEmApagarTarefaConfirmar();

        Assert.assertFalse(mainPage.verificarLinkTarefa("000"+idIssue));


        ProjetosDBSteps.deletarProjetoDB(ProjetosDBSteps.retornaDadosProjeto().get(1));
        BuscarIssueDBSteps.deletarIssueId(idIssue);
        BuscarIssueDBSteps.deletarBugHistory(idIssue);
        BuscarIssueDBSteps.deletarTextoId(idTexto);
    }

}
