package com.example.nutribem.domain.entities.alimento;

public class Alimento {

    private String nome;
    private Integer id;
    private Integer porcao;
    private Integer calorias;
    private Integer colesterol;
    private Integer gluten;
    private Float gordurasSaturadas;
    private Float sodio;
    private Float acucar;
   private Float lactose;

    public Alimento(String nome, Integer id, Integer porcao, Integer calorias, Integer colesterol, Integer gluten, Float gordurasSaturadas, Float sodio, Float acucar, Float lactose) {
        this.nome = nome;
        this.id = id;
        this.porcao = porcao;
        this.calorias = calorias;
        this.colesterol = colesterol;
        this.gluten = gluten;
        this.gordurasSaturadas = gordurasSaturadas;
        this.sodio = sodio;
        this.acucar = acucar;
        this.lactose = lactose;
    }
}
