package com.javaseleniumtemplate.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
public class GerarDados {
    private static final Locale LOCALE = new Locale("en","US");

    public static String nomeUser(){
            Faker faker = new Faker(LOCALE);
            return faker.name().firstName();
        }

        public static String fullName(){
            Faker faker = new Faker(LOCALE);
            return faker.name().firstName() +" "+ faker.name().lastName().replace("'","");
        }
        public static String email(){
            Faker faker = new Faker(LOCALE);
            return faker.internet().emailAddress();
        }

    public static String numeroAleatorio(){
        return String.valueOf(new Random().nextInt(100));

    }

    public static String getRandomString(int i)
    {

        byte[] bytearray;
        String mystring;
        StringBuffer thebuffer;

        bytearray = new byte[256];
        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, Charset.forName("UTF-8"));

        // Create the StringBuffer
        thebuffer = new StringBuffer();

        for (int m = 0; m < mystring.length(); m++) {

            char n = mystring.charAt(m);

            if (((n >= 'A' && n <= 'Z')
                    || (n >= '0' && n <= '9'))
                    && (i > 0)) {

                thebuffer.append(n);
                i--;
            }
        }

        // resulting string
        return thebuffer.toString();
    }

      public static String nomeProjeto () {
        return "Projeto Teste Larissa" + " " + GerarDados.getRandomString(3) + GerarDados.numeroAleatorio();

    }

    public static String nomeMarcador () {
        return "Marcador Teste Larissa" + " " + GerarDados.getRandomString(3) + GerarDados.numeroAleatorio();

    }

    public static String descricaoTexto() {
      return "Descrição Teste Larissa" +" " + GerarDados.getRandomString(20);
    }

    public static String gerarData() {
        int i = (int) (new Date().getTime()/1000);
        return String.valueOf(i);
    }

    public static String nomePlataforma() { return "Plataforma" +" " + GerarDados.getRandomString(3);}

    public static String nomeSO() { return "Windows" +" " + GerarDados.getRandomString(3);}

    public static String nomeVersaoSO() { return "Versao"+ " " + GerarDados.numeroAleatorio();}

    public static String sumarioIssue() {
        return "Sumario Larissa" + " " + "V"+GerarDados.getRandomString(10);
    }

    public static String descricaoTextoModificado() {
        return "Descrição Teste Larissa Modificado" +" " + GerarDados.getRandomString(20);
    }

    public static String descricaoAnotacoes() {
        return "Anotacoes Teste Larissa" +" " + GerarDados.getRandomString(20);
    }

    public static String descricaoAnotacoesModificado() {
        return "Anotacoes Teste Larissa Modificado" +" " + GerarDados.getRandomString(20);
    }

    public static String tagName() {
        return "Tag Larissa" + " " + GerarDados.getRandomString(10);

    }

    public static String categoriaName() {
        return "Categoria Larissa" + " " + GerarDados.getRandomString(10);

    }

    public static String categoriaNameAlterar() {
        return "Categoria Larissa Alterada" + " " + GerarDados.getRandomString(10);

    }
}
