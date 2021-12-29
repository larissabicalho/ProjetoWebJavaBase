package com.javaseleniumtemplate.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExecutarJavaScriptNode {

    public ExecutarJavaScriptNode() {
    }

    public static boolean executaJavaScript(String valor, String valorEsperado) {

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");

        try {
            FileReader reader = new FileReader("src/test/java/com/javaseleniumtemplate/utils/example.js");
            engine.put("valor", valor);
            engine.put("valorEsperado", valorEsperado);
            engine.eval(reader);
            engine.eval("var assert= x(valor,valorEsperado)");
            boolean aleatorio = (Boolean) engine.get("assert");
            return aleatorio;

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(ScriptException e) {
        e.printStackTrace();
    }
       return false;
    }


    public static String executaJavaScriptAleatorio() {

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");

        try {
            FileReader reader = new FileReader("src/test/java/com/javaseleniumtemplate/utils/exampleAleatorio.js");
            engine.put("length", 10);
            engine.eval(reader);
            engine.eval("var s= makeid(length)");

            String aleatorio = String.valueOf(engine.get("s"));

            return aleatorio;


        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}