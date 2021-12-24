package com.javaseleniumtemplate.dbsteps.Projetos;

import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

import java.util.ArrayList;

public class ProjetosDBSteps {

    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/projetosqueries/";

    public static void insereProjeto() {
        String query = Utils.getFileContent(queriesPath + "inserirProjeto.sql");
        query = query.replace("$name", GerarDados.nomeProjeto());
        query = query.replace("$file_path", GlobalStaticParameters.file_path);
        query = query.replace("$description", GlobalStaticParameters.description);
        DBUtils.getQueryResult(query);
    }

    public static String retornaName(){
        String query = Utils.getFileContent(queriesPath + "retornarProjeto.sql");
        return DBUtils.getQueryResult(query).get(0);
    }

    public static void deletarProjetoDB(String name){
        String query = Utils.getFileContent(queriesPath + "deletarProjetoNoBanco.sql").replace("$name", name);
        DBUtils.getQueryResult(query);
    }


    public static ArrayList<String> retornaDadosProjeto() {
        String queryResultado = Utils.getFileContent(queriesPath + "retornaDadosProjeto.sql");
        return DBUtils.getQueryResult(queryResultado);
    }

}
