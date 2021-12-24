package com.javaseleniumtemplate.defaultParameters;

import com.javaseleniumtemplate.utils.GerarDados;

public class GlobalStaticParameters {

    //projectsParameteres
    public static String file_path = "/temp";
    public static String description = "Descricao" + " " + GerarDados.numeroAleatorio();

    //usersParameteres
    public static String userPadrao = "administrator";
    public static String user = "teste@gmail.com";
    public static String userComCaracterEspecial = "la&*#!@gmail.com";
    public static String senha = "1233";


// mensagem de erro

    public static String mensagemErroUsuario = "APPLICATION ERROR #805";
    public static String mensagemErroEmail = "Your account may be disabled or blocked or the username/password you entered is incorrect.";


    //optionMonitoramento

    public static String opcaoMonitoramento = "1";
    public static String filtarUrgente = "50";
    public static String acao ="Close";
    public static String estado = "90";
    public static String resolver = "80";
    public static String resolvido = "fixed";

    public static String csv ="C:\\Users\\larissaBicalho\\Downloads\\administrator.csv";
    public static String doc ="C:\\Users\\larissaBicalho\\Downloads\\administrator.doc";
    public static String xml ="C:\\Users\\larissaBicalho\\Downloads\\administrator.xml";

    public static String attachment = "src/test/java/com/javaseleniumtemplate/utils";
    public static String file = "teste.txt";


}

