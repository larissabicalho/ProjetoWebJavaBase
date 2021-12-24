package com.javaseleniumtemplate.dbsteps.Issues;

import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

import java.util.ArrayList;

public class BuscarIssueDBSteps {
    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/issuesqueries/";



    public static void insereIssue(String projectId, String idTexto) {
        String query = Utils.getFileContent(queriesPath + "inserirIssue.sql");
        query = query.replace("$project_id", projectId);
        query = query.replace("$bug_text_id", idTexto);
        query = query.replace("$summary", GerarDados.sumarioIssue());
        query = query.replace("$date_submitted", GerarDados.gerarData());
        query = query.replace("$last_updated", GerarDados.gerarData());
        DBUtils.getQueryResult(query);
    }


    public static void insereIssueHandlerId(String projectId, String idTexto) {
        String query = Utils.getFileContent(queriesPath + "inserirIssueHandler.sql");
        query = query.replace("$project_id", projectId);
        query = query.replace("$bug_text_id", idTexto);
        query = query.replace("$summary", GerarDados.sumarioIssue());
        query = query.replace("$date_submitted", GerarDados.gerarData());
        query = query.replace("$last_updated", GerarDados.gerarData());
        DBUtils.getQueryResult(query);
    }

    public static void insereIssueResolvido(String projectId, String idTexto) {
        String query = Utils.getFileContent(queriesPath + "inserirIssueHandlerResolvido.sql");
        query = query.replace("$project_id", projectId);
        query = query.replace("$bug_text_id", idTexto);
        query = query.replace("$summary", GerarDados.sumarioIssue());
        query = query.replace("$date_submitted", GerarDados.gerarData());
        query = query.replace("$last_updated", GerarDados.gerarData());
        DBUtils.getQueryResult(query);
    }

    public static ArrayList<String> retornaDadosIssue() {
        String queryResultado = Utils.getFileContent(queriesPath + "retornarDadosIssue.sql");
        return DBUtils.getQueryResult(queryResultado);
    }

    public static ArrayList<String> retornaDadosTexto() {
        String queryResultado = Utils.getFileContent(queriesPath + "retornarDadosTexto.sql");
        return DBUtils.getQueryResult(queryResultado);
    }


    public static void insereTexto() {
        String query = Utils.getFileContent(queriesPath + "inserirTextoIssue.sql");
        query = query.replace("$description", GerarDados.descricaoTexto());
        DBUtils.getQueryResult(query);
    }


    public static void deletarIssueId(String idIssue) {
        String query = Utils.getFileContent(queriesPath + "deletarIssueTodas.sql");
        query = query.replace("$idIssue", idIssue);
        DBUtils.getQueryResult(query);
    }

    public static void deletarTextoId(String idTexto) {
        String query = Utils.getFileContent(queriesPath + "deletarTextoTodos.sql");
        query = query.replace("$idTexto", idTexto);
        DBUtils.getQueryResult(query);
    }

    public static void inserirMonitoramento(String idIssue) {
        String query = Utils.getFileContent(queriesPath + "inserirMonitoramentoAdm.sql");
        query = query.replace("$bug_id", idIssue);
        DBUtils.getQueryResult(query);
    }

    public static void deletarMonitoramento(String idIssue) {
        String query = Utils.getFileContent(queriesPath + "deletarMonitored.sql");
        query = query.replace("$bug_id", idIssue);
        DBUtils.getQueryResult(query);
    }

    public static void insereIssueUrgente(String projectId, String idTexto) {
        String query = Utils.getFileContent(queriesPath + "inserirIssueUrgente.sql");
        query = query.replace("$project_id", projectId);
        query = query.replace("$bug_text_id", idTexto);
        query = query.replace("$summary", GerarDados.sumarioIssue());
        query = query.replace("$date_submitted", GerarDados.gerarData());
        query = query.replace("$last_updated", GerarDados.gerarData());
        DBUtils.getQueryResult(query);
    }

    public static String retornarIdBugNote() {
        String query = Utils.getFileContent(queriesPath + "retornarIdNote.sql");
        return DBUtils.getQueryResult(query).get(0);
    }

    public static String retornarIdBugNoteText() {
        String query = Utils.getFileContent(queriesPath + "retornarIdNoteText.sql");
        return DBUtils.getQueryResult(query).get(0);
    }

    public static void deletarNoteText(String idNoteText) {
        String query = Utils.getFileContent(queriesPath + "deletarBugNoteText.sql");
        query = query.replace("$idNoteText", idNoteText);
        DBUtils.getQueryResult(query);
    }

    public static void deletarNote(String idNote) {
        String query = Utils.getFileContent(queriesPath + "deletarBugNote.sql");
        query = query.replace("$idNote", idNote);
        DBUtils.getQueryResult(query);
    }

    public static void deletarTexto() {
        String query = Utils.getFileContent(queriesPath + "deletarTexto.sql");
        DBUtils.getQueryResult(query);
    }

    public static void deletarBugTexto() {
        String query = Utils.getFileContent(queriesPath + "deletarNoteTexto.sql");
        DBUtils.getQueryResult(query);
    }

    public static ArrayList<String> retornaDadosTodasIssueIdProjeto(String idProjeto) {
        String queryResultado = Utils.getFileContent(queriesPath + "retornarDadosTodasIssueIdProjeto.sql");
        queryResultado = queryResultado.replace("$project_id", idProjeto);
        return DBUtils.getQueryResult(queryResultado);
    }

    public static void deletarRelationship(String idIssue) {
        String query = Utils.getFileContent(queriesPath + "deletaRelationships.sql");
        query = query.replace("$source_bug_id", idIssue);
        DBUtils.getQueryResult(query);
    }

    public static void deletarTags() {
        String query = Utils.getFileContent(queriesPath + "deletarTags.sql");
        DBUtils.getQueryResult(query);
    }

    public static void deletarBugTags() {
        String query = Utils.getFileContent(queriesPath + "deletarBugTags.sql");
        DBUtils.getQueryResult(query);
    }
    public static void deletarBugHistory(String idBugHistory) {
        String query = Utils.getFileContent(queriesPath + "deletarBugHistory.sql");
        query = query.replace("$bug_id", idBugHistory);
        DBUtils.getQueryResult(query);
    }

    public static void deletarAttachment() {
        String query = Utils.getFileContent(queriesPath + "deletarAttachment.sql");
        DBUtils.getQueryResult(query);
    }

}
