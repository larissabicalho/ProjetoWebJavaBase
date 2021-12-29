package com.javaseleniumtemplate.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor(force = true)
public class Users {
    @NonNull
    @JsonProperty("password")
    private String password;

    public static Users get(String arquivo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(arquivo), Users.class);
    }

    public String getPassword() {
        return password;
    }

    public void setSenha(String senha) {
        this.password = senha;
    }
}
