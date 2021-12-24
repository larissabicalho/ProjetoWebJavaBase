package com.javaseleniumtemplate.dbsteps.PerfisGlobais;

import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

public class PerfisGlobaisDBSteps {

    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/perfisglobaisqueries/";

    public static void deletarPerfisGlobaisDB(String platform){
        String query = Utils.getFileContent(queriesPath + "deletarPerfisGlobaisNoBanco.sql").replace("$platform", platform);
        DBUtils.getQueryResult(query);
    }


    public static String retornaPlataforma(){
        String query = Utils.getFileContent(queriesPath + "retornarPerfisGlobais.sql");

        return DBUtils.getQueryResult(query).get(0);
    }
}
