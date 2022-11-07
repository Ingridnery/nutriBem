package com.example.nutribem.domain.entities.alimento;

import com.example.nutribem.domain.entities.refeicao.Refeicao;

import java.util.Objects;

public class Alimento {

    private String nome;
    private Integer id;
    private Integer porcao;
    private Integer calorias;
    private Integer colesterol;
    private Boolean gluten;
    private Float gordurasSaturadas;
    private Float sodio;
    private Float acucar;
    private Float lactose;
    private Boolean ativado;

    private Refeicao refeicao;

    public Alimento(String nome, Integer id, Integer porcao, Integer calorias, Integer colesterol, Boolean gluten, Float gordurasSaturadas, Float sodio, Float acucar, Float lactose, Boolean ativado, Refeicao refeicao) {
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
        this.ativado = ativado;
        this.refeicao = refeicao;
    }

    public Alimento(String nome, Integer porcao, Integer calorias, Integer colesterol, Boolean gluten, Float gordurasSaturadas, Float sodio, Float acucar, Float lactose) {
        this.nome = nome;
        this.id = null;
        this.porcao = porcao;
        this.calorias = calorias;
        this.colesterol = colesterol;
        this.gluten = gluten;
        this.gordurasSaturadas = gordurasSaturadas;
        this.sodio = sodio;
        this.acucar = acucar;
        this.lactose = lactose;
        this.ativado = true;
    }

    public Alimento(String nome, Integer id, Integer porcao, Integer calorias, Integer colesterol, Boolean gluten, Float gordurasSaturadas, Float sodio, Float acucar, Float lactose, Boolean ativado) {
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
        this.ativado = ativado;
    }



    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(this.id != null)
            throw new IllegalArgumentException("O alimento "+this.nome+" já possui um id associado.");
        this.id = id;
    }

    public Integer getPorcao() {
        return porcao;
    }

    public void setPorcao(Integer porcao) {
        this.porcao = porcao;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Integer getColesterol() {
        return colesterol;
    }

    public void setColesterol(Integer colesterol) {
        this.colesterol = colesterol;
    }

    public Boolean getGluten() {
        return gluten;
    }

    public void setGluten(Boolean gluten) {
        this.gluten = gluten;
    }

    public Float getGordurasSaturadas() {
        return gordurasSaturadas;
    }

    public void setGordurasSaturadas(Float gordurasSaturadas) {
        this.gordurasSaturadas = gordurasSaturadas;
    }

    public Float getSodio() {
        return sodio;
    }

    public void setSodio(Float sodio) {
        this.sodio = sodio;
    }

    public Float getAcucar() {
        return acucar;
    }

    public void setAcucar(Float acucar) {
        this.acucar = acucar;
    }

    public Float getLactose() {
        return lactose;
    }

    public void setLactose(Float lactose) {
        this.lactose = lactose;
    }

    public Boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alimento alimento = (Alimento) o;
        return Objects.equals(id, alimento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
