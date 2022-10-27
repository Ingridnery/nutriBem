package com.example.nutribem.domain.entities.nutricionista;

import java.util.ArrayList;

public class Nutricionista {

    private String senha;
    private ArrayList<String> dicas;

    private String nomeUsuario;

    public Nutricionista(String senha, String nomeUsuario) {
        this.senha = senha;
        this.nomeUsuario = nomeUsuario;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
