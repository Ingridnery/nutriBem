package com.example.nutribem.domain.usecases.valoresNutricionais;

import java.util.Objects;

public class ValoresNutricionais {
    private Integer calorias;
    private Integer colesterol;
    private Boolean gluten;
    private Float gordurasSaturadas;
    private Float sodio;
    private Float acucar;
    private Float lactose;

    public ValoresNutricionais(Integer calorias, Integer colesterol, Boolean gluten, Float gordurasSaturadas, Float sodio, Float acucar, Float lactose) {
        this.calorias = calorias;
        this.colesterol = colesterol;
        this.gluten = gluten;
        this.gordurasSaturadas = gordurasSaturadas;
        this.sodio = sodio;
        this.acucar = acucar;
        this.lactose = lactose;
    }

    public ValoresNutricionais() {
        this.calorias = 0;
        this.colesterol = 0;
        this.gluten = false;
        this.gordurasSaturadas = (float) 0;
        this.sodio = (float) 0;
        this.acucar = (float) 0;
        this.lactose = (float) 0;
    }

    public void somar(ValoresNutricionais novo){
        this.calorias += novo.getCalorias();
        this.colesterol += novo.getColesterol();
        if(!this.gluten) this.gluten = novo.getGluten();
        this.gordurasSaturadas += novo.getGordurasSaturadas();
        this.sodio += novo.getSodio();
        this.acucar += novo.getAcucar();
        this.lactose += novo.getLactose();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValoresNutricionais that = (ValoresNutricionais) o;
        return Objects.equals(calorias, that.calorias) && Objects.equals(colesterol, that.colesterol) && Objects.equals(gluten, that.gluten) && Objects.equals(gordurasSaturadas, that.gordurasSaturadas) && Objects.equals(sodio, that.sodio) && Objects.equals(acucar, that.acucar) && Objects.equals(lactose, that.lactose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calorias, colesterol, gluten, gordurasSaturadas, sodio, acucar, lactose);
    }

    @Override
    public String toString() {
        return "ValoresNutricionais{" +
                "calorias=" + calorias +
                ", colesterol=" + colesterol +
                ", gluten=" + gluten +
                ", gordurasSaturadas=" + gordurasSaturadas +
                ", sodio=" + sodio +
                ", acucar=" + acucar +
                ", lactose=" + lactose +
                '}';
    }
}
