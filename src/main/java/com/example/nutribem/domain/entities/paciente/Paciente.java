package com.example.nutribem.domain.entities.paciente;

import java.util.Date;

public class Paciente {

    private Integer id;
    private Integer circunferencia;
    private Integer altura;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String historicoClinicoGeral;
    private String intoleranciaLactose;
    private String intoleranciaGluten;
    private String diabetes;
    private String alergias;
    private String observacoesGerais;
    private String objetivos;
    private Date dataNascimento;
    private Float peso;
    private Character sexo;

    public Paciente(Integer id, Integer circunferencia, Integer altura, String nome, String cpf, String email, String telefone, String historicoClinicoGeral, String intoleranciaLactose, String intoleranciaGluten, String diabetes, String alergias, String observacoesGerais, String objetivos, Date dataNascimento, Float peso, Character sexo) {
        this.id = id;
        this.circunferencia = circunferencia;
        this.altura = altura;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.historicoClinicoGeral = historicoClinicoGeral;
        this.intoleranciaLactose = intoleranciaLactose;
        this.intoleranciaGluten = intoleranciaGluten;
        this.diabetes = diabetes;
        this.alergias = alergias;
        this.observacoesGerais = observacoesGerais;
        this.objetivos = objetivos;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.sexo = sexo;
    }
}
