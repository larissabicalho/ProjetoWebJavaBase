package com.javaseleniumtemplate.dbsteps.Categoria;

import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

public class CategoriaDBSteps {

    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/categoriaqueries/";

    public static void deletarCategoriaDB(String name){
        String query = Utils.getFileContent(queriesPath + "deletarCategoria.sql").replace("$name", name);
        DBUtils.getQueryResult(query);
    }

    public static void insereCategoria() {
        String query = Utils.getFileContent(queriesPath + "inserirCategoria.sql");
        query = query.replace("$name", GerarDados.categoriaName());
        DBUtils.getQueryResult(query);
    }

    public static String retornaCategoriaName(){
        String query = Utils.getFileContent(queriesPath + "retornarCategoria.sql");
        return DBUtils.getQueryResult(query).get(0);
    }


}
