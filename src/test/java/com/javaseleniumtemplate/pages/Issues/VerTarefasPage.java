package com.javaseleniumtemplate.pages.Issues;

import com.javaseleniumtemplate.bases.PageBase;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VerTarefasPage extends PageBase {
    By botaoImprimirTarefas = By.xpath("//a[text()='Print Reports']");
    By acharTarefa = By.xpath("//td[@class='bug-id']");
    By textoDescricao = By.xpath("//td/textarea[@id='description']");
    By botaoAtualizar = By.xpath("//input[@value='Update Information']");
    By apagarTarefa = By.xpath("//input[@value='Delete']");
    By apagarTarefaConfirmar = By.xpath("//input[@value='Delete Issues']");
    By copiarTarefa = By.xpath("//input[@value='Clone']");
    By criarNovaTarefaCopiada = By.xpath("//input[@value='Submit Issue']");
    By filtrarMonitorados = By.xpath("//a[text()='Monitored By']");
    By selecionarMonitor = By.name("monitor_user_id[]");
    By aplicarFiltro = By.xpath("//input[@value='Apply Filter']");
    By filtrarResolvido = By.xpath("//a[text()='Assigned To']");
    By selecionarResolvido = By.name("handler_id[]");
    By filtrarPrioridade = By.xpath("//a[text()='Priority']");
    By selecionarPrioridade = By.name("priority[]");
    By redefinirFiltro = By.xpath("(//a[@href='view_all_set.php?type=0'])[2]");
    By adicionarTextoAnotacoes = By.xpath("//textarea[@name='bugnote_text']");
    By botaoAdicionarAnotacao = By.xpath("//input[@value='Add Note']");
    By verificarAnotacoes = By.xpath("//td[@class='bugnote-note bugnote-public']");
    By clicarApagar = By.xpath("//button[text()='Delete']");
    By clicarEditar = By.xpath("//button[text()='Edit']");
    By clicarApagarConfirmacao = By.xpath("//input[@value='Delete Note']");
    By selecionarUmaAcao = By.name("action");
    By clicarOk = By.xpath("//input[@value='OK']");
    By confirmarFechamentoTarefa = By.xpath("//input[@value='Close Issues']");
    By estadoFiltro = By.xpath("//a[text()='Status']");
    By selecionarEstadoFechadoFiltro = By.name("status[]");
    By selecionarAlterarStatus = By.xpath("//input[@value='Change Status To:']");
    By confirmarResolucaoTarefa = By.xpath("//input[@value='Resolve Issue']");
    By verificarResolucao = By.xpath("//td[@class='bug-resolution']");
    By selecionarUmStatus = By.name("new_status");
    By selecionarUmUsuario = By.name("handler_id");
    By selecionarAlterarUsuario = By.xpath("//input[@value='Assign To:']");
    By relacionarTarefa = By.name("dest_bug_id");
    By clicarEmAdicionarRelacao = By.xpath("(//input[@value='Add'])[1]");
    By adicionarMarcador = By.name("tag_string");
    By clicarAplicar = By.xpath("//input[@value='Attach']");
    By clicarEmExportar = By.xpath("//a[text()='CSV Export']");
    By clicarEmExportarExcel = By.xpath("//a[text()='Excel Export']");
    By clicarEmPrint = By.xpath("//a[text()='Print Reports']");
    By clicarEmWord = By.xpath("//i[@title='Word 2000']");
    By paraApagar = By.xpath("//span[@class='label label-sm label-default arrowed-in-right']");
    String editarProjetoLink = "//div[@id='unassigned']/div[@class='widget-body']/div[@class='widget-main no-padding']/div[@class='table-responsive']/table/tbody/tr/td/a[text()=###]/.././a[@class='edit']";
    String descricaoValidar = "//*[text()='###']";
    String labelVerificarTag = "//a[text()='###']";
    String verificarRelacoes = "//a[@href='/view.php?id=###']";
    String verificarRelacoesResolvidas="//a[@href='/view.php?id=###']";
    String verificarRelacoesMonitoradas="//a[@href='/view.php?id=###']";
    String verificarRelacoesSub ="//a[@href='view.php?id=###']";
    String clicarEmTarefaSelecionado = "//a[@href='/view.php?id=###']/parent::td/parent::tr/td/div/label/span";
    String selecionarUsuario = "//td[@class='bug-assigned-to']/a[text()='###']";


    public boolean verificarSeElementoExiste() {
        return returnIfElementExists(botaoImprimirTarefas);
    }

    public String idTarefaCerta() {
        return getText(acharTarefa);
    }

    public void editarProjeto(String idProjeto) {
        editarProjetoLink = editarProjetoLink.replace("###", idProjeto);
        click(By.xpath(editarProjetoLink));
    }
    public void limparEPreencher(String descricao) {
        clear(textoDescricao);
        sendKeys(textoDescricao, descricao);
    }

    public String validarTextoModificado(String texto) {
        descricaoValidar = descricaoValidar.replace("###", texto);
        return getText(By.xpath(descricaoValidar));
    }

    public void clicarEmAtualizar() {
        click(botaoAtualizar);
    }

    public void clicarEmApagarTarefa() {
        click(apagarTarefa);
    }

    public void clicarEmApagarTarefaConfirmar() {
        click(apagarTarefaConfirmar);
    }

    public void clicarEmCopiarTarefa() {
        click(copiarTarefa);
    }

    public void clicarEmCriarNovaTarefaCopiada() {
        click(criarNovaTarefaCopiada);
    }

    public String verificarSeExisteTarefa(String idIssue) {
        verificarRelacoes = verificarRelacoes.replace("###", idIssue);
        waitForElement(By.xpath(verificarRelacoes));
        return getText(By.xpath(verificarRelacoes));
    }
    
    public String verificarSeExisteTarefaSub(String idIssue) {
        verificarRelacoesSub = verificarRelacoesSub.replace("###", idIssue);
        waitForElement(By.xpath(verificarRelacoesSub));
        return getText(By.xpath(verificarRelacoesSub));
    }
    
     public String verificarSeExisteTarefaResolvida(String idIssue) {
        verificarRelacoesResolvidas = verificarRelacoesResolvidas.replace("###", idIssue);
        return getText(By.xpath(verificarRelacoesResolvidas));
    }

     public String verificarSeExisteTarefaMonitorada(String idIssue) {
        verificarRelacoesMonitoradas = verificarRelacoesMonitoradas.replace("###", idIssue);
        return getText(By.xpath(verificarRelacoesMonitoradas));
    }

    public void clicarEmRelacionados() {
        click(filtrarResolvido);
    }

    public void selecionarRelacionados(String index) {
        comboBoxSelectByOption(selecionarResolvido, index);
    }

    public void clicarEmMonitoradoPor() {
        click(filtrarMonitorados);
    }

    public void selecionarMonitor(String index) {
        comboBoxSelectByOption(selecionarMonitor, index);
    }

    public void clicarEmAplicarFiltro() {
        click(aplicarFiltro);
    }

    public void clicarEmRedefinirFiltro() {
        click(redefinirFiltro);
    }

    public void clicarEmPrioridade() {
        click(filtrarPrioridade);
    }

    public void selecionarPrioridade(String index) {
        comboBoxSelectByOption(selecionarPrioridade, index);
    }

    public void preencherAnotacoes(String anotacoes) {
        sendKeys(adicionarTextoAnotacoes, anotacoes);
    }

    public void clicarEmAdicionarAnotacao() {
        click(botaoAdicionarAnotacao);
    }

    public void clicarEmAnotacao() {
        waitForElement(verificarAnotacoes);
        click(verificarAnotacoes);
    }

    public void clicarEmApagarAnotacoes() {
        mouseOver(paraApagar);
        click(clicarApagar);
    }

    public void clicarEmApagarAnotacoesConfirmar() {
        click(clicarApagarConfirmacao);
    }

    public String verificarAnotacoes() {
        return getText(verificarAnotacoes);
    }

    public boolean verificarSeOElementoExisteAnotacoes() {
        return returnIfElementExists(verificarAnotacoes);
    }

    public void clicarEmEditarAnotacoes() {
        mouseOver(paraApagar);
        click(clicarEditar);
    }

    public void limparEPreencherAnotacoes(String anotacoesNote) {
        clear(adicionarTextoAnotacoes);
        sendKeys(adicionarTextoAnotacoes, anotacoesNote);
    }

    public void selecionarUmaTarefa(String projectId) {
        clicarEmTarefaSelecionado = clicarEmTarefaSelecionado.replace("###", projectId);
        waitForElement(By.xpath(clicarEmTarefaSelecionado));
        click(By.xpath(clicarEmTarefaSelecionado));
    }

    public void clicarEmSelecionarUmaAcao(String acao) {
        comboBoxSelectByVisibleText(selecionarUmaAcao, acao);
    }

    public void clicarEmOK() {
        click(clicarOk);
    }

    public void clicarEmFecharTarefa() {
        click(confirmarFechamentoTarefa);
    }

    public void clicarEmEstado() {
        click(estadoFiltro);
    }

    public void selecionarEstado(String index) {
        comboBoxSelectByOption(selecionarEstadoFechadoFiltro, index);
    }

    public void clicarEmAlterarStatus() {
        click(selecionarAlterarStatus);
    }

    public void clicarEmResolverTarefa() {
        click(confirmarResolucaoTarefa);
    }

    public String verificarSeEstaResolvido() {
        return getText(verificarResolucao);
    }

    public void clicarEmSelecionarUmStatus(String index) {
        comboBoxSelectByOption(selecionarUmStatus, index);
    }

    public void clicarEmSelecionarUmUsuarioAtribuido(String index) {
        comboBoxSelectByVisibleText(selecionarUmUsuario, index);
    }

    public void clicarEmSelecionarUmUsuario() {
        click(selecionarAlterarUsuario);
    }

    public String verificarSelecionarUmUsuario(String nomeUsuario) {
        selecionarUsuario = selecionarUsuario.replace("###", nomeUsuario);
        waitForElement(By.xpath(selecionarUsuario));
        return getText(By.xpath(selecionarUsuario));
    }

    public void preencherTarefaRelacionada(String idTarefa) {
        sendKeys(relacionarTarefa, idTarefa);
    }


    public String verificarRelacao(String idTarefa) {
        verificarRelacoes = verificarRelacoes.replace("###", idTarefa);
        waitForElement(By.xpath(verificarRelacoes));
        return getText(By.xpath(verificarRelacoes));
    }

    public void adicionarRelacao() {
        click(clicarEmAdicionarRelacao);
    }

    public void adicionarMarcadorIssue(String idMarcador) {
        sendKeys(adicionarMarcador, idMarcador);
    }

    public void clicarEmAplicar() {
        click(clicarAplicar);
    }

    public String verificarTag(String descricaoTag) {
        labelVerificarTag = labelVerificarTag.replace("###", descricaoTag);
        waitForElement(By.xpath(labelVerificarTag));
        return getText(By.xpath(labelVerificarTag));
    }

    public void clicarEmExportarCSV() {
        waitForElement(clicarEmExportar);
        click(clicarEmExportar);
    }

    public void clicarEmExportarWord() {
        click(clicarEmWord);
    }

    public void clicarEmExportarExcel() {
        click(clicarEmExportarExcel);
    }

    public void clicarEmPrint() {
        click(clicarEmPrint);
    }

    public void esperarElemento() {
        wait.until(ExpectedConditions.presenceOfElementLocated(selecionarAlterarUsuario));
    }


}
