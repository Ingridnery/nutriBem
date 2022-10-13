package com.example.nutribem.domain.entities.nutricionista;

import java.util.ArrayList;

public class Nutricionista {

    private String senha;
    private ArrayList<String> dicas;

    public Nutricionista(String senha, ArrayList<String> dicas) {
        this.senha = senha;
        this.dicas = dicas;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<String> getDicas() {
        return dicas;
    }

    public void setDicas(ArrayList<String> dicas) {
        this.dicas = dicas;
    }
}
