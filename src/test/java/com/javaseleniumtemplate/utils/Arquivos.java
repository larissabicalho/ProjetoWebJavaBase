package com.javaseleniumtemplate.utils;

import com.javaseleniumtemplate.enums.Users;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Arquivos {
    public static Users getJsonUsers() throws FileNotFoundException{
        try{
            return Users.get("src/test/resources/json/userDados.json");

        } catch (FileNotFoundException ex) {
           throw new FileNotFoundException("Não foi feita a leitura do arquivo json");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Users getJsonUsersPadrao() throws FileNotFoundException{
        try{
            return Users.get("src/test/resources/json/userDadosAdmin.json");

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi feita a leitura do arquivo json");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
