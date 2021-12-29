package com.javaseleniumtemplate.dbsteps.Usuarios;

import com.javaseleniumtemplate.utils.DBUtils;
import com.javaseleniumtemplate.utils.GerarDados;
import com.javaseleniumtemplate.utils.Utils;

public class UsuariosDBSteps {

    private static String queriesPath = "src/test/java/com/javaseleniumtemplate/queries/usuariosqueries/";


    public static void insereUsuario() {
        String query = Utils.getFileContent(queriesPath + "inserirUsuarioNoBanco.sql");
        query = query.replace("$username", GerarDados.nomeUser());
        query = query.replace("$realname", GerarDados.fullName());
        query = query.replace("$email", GerarDados.email());
        DBUtils.getQueryResult(query);
    }

    public static String retornaUsername() {
        String query = Utils.getFileContent(queriesPath + "retornarUsername.sql");

        return DBUtils.getQueryResult(query).get(0);
    }

    public static String retornaEmail() {
        String query = Utils.getFileContent(queriesPath + "retornarEmail.sql");

        return DBUtils.getQueryResult(query).get(0);
    }

    public static void deletarUsuarioDB(String usuario) {
        String query = Utils.getFileContent(queriesPath + "deletarUsuarioNoBanco.sql").replace("$usuario", usuario);
        DBUtils.getQueryResult(query);
    }

    public static void deletarEmailDB(String email) {
        String query = Utils.getFileContent(queriesPath + "deletarEmail.sql").replace("$email", email);
        DBUtils.getQueryResult(query);
    }


}
