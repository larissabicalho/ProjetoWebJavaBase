package com.javaseleniumtemplate.utils;

import com.github.javafaker.Faker;

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
            return faker.name().firstName() +" "+ faker.name().lastName();
        }
        public static String email(){
            Faker faker = new Faker(LOCALE);
            return faker.internet().emailAddress();
        }



}
