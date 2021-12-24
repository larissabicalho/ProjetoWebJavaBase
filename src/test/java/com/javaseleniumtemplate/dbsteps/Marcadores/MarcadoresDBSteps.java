package com.javaseleniumtemplate.dbsteps.Marcadores;

import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

public class MarcadoresDBSteps {

    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/marcadoresqueries/";


    public static String retornaName(){
        String query = Utils.getFileContent(queriesPath + "retornarMarcador.sql");

        return DBUtils.getQueryResult(query).get(0);
    }

    public static void deletarMarcadorDB(String name){
        String query = Utils.getFileContent(queriesPath + "deletarMarcadorNoBanco.sql").replace("$name", name);
        DBUtils.getQueryResult(query);
    }

    public static void insereMarcador() {
        String query = Utils.getFileContent(queriesPath + "inserirMarcadores.sql");
        query = query.replace("$name", GerarDados.nomeMarcador());
        query = query.replace("$description", GerarDados.descricaoTexto());
        query = query.replace("$date_created", GerarDados.gerarData());
        query = query.replace("$date_updated", GerarDados.gerarData());
        DBUtils.getQueryResult(query);
    }

}
