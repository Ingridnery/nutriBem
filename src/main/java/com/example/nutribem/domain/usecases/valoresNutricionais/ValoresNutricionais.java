package com.example.nutribem.domain.usecases.valoresNutricionais;

import java.util.Objects;

public class ValoresNutricionais {
    private Integer calorias;
    private Integer colesterol;
    private Boolean gluten;
    private Double gordurasSaturadas;
    private Double proteinas;
    private Double sodio;
    private Double acucar;
    private Double lactose;

    public ValoresNutricionais(Integer calorias, Integer colesterol, Boolean gluten, Double gordurasSaturadas, Double proteinas, Double sodio, Double acucar, Double lactose) {
        this.calorias = calorias;
        this.colesterol = colesterol;
        this.gluten = gluten;
        this.gordurasSaturadas = gordurasSaturadas;
        this.proteinas = proteinas;
        this.sodio = sodio;
        this.acucar = acucar;
        this.lactose = lactose;
    }

    public ValoresNutricionais() {
        this.calorias = 0;
        this.colesterol = 0;
        this.gluten = false;
        this.gordurasSaturadas = 0.0;
        this.proteinas = 0.0;
        this.sodio = 0.0;
        this.acucar = 0.0;
        this.lactose = 0.0;
    }

    public void somar(ValoresNutricionais novo) {
        this.calorias += novo.getCalorias();
        this.colesterol += novo.getColesterol();
        if (!this.gluten) this.gluten = novo.getGluten();
        this.gordurasSaturadas += novo.getGordurasSaturadas();
        this.proteinas += novo.getProteinas();
        this.sodio += novo.getSodio();
        this.acucar += novo.getAcucar();
        this.lactose += novo.getLactose();
    }

    public void subtrair(ValoresNutricionais novo) {
        this.calorias -= novo.getCalorias();
        this.colesterol -= novo.getColesterol();
        if (this.gluten && novo.getGluten()) this.gluten = false;
        this.gordurasSaturadas -= novo.getGordurasSaturadas();
        this.proteinas -= novo.getProteinas();
        this.sodio -= novo.getSodio();
        this.acucar -= novo.getAcucar();
        this.lactose -= novo.getLactose();
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

    public Double getGordurasSaturadas() {
        return gordurasSaturadas;
    }

    public void setGordurasSaturadas(Double gordurasSaturadas) {
        this.gordurasSaturadas = gordurasSaturadas;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getSodio() {
        return sodio;
    }

    public void setSodio(Double sodio) {
        this.sodio = sodio;
    }

    public Double getAcucar() {
        return acucar;
    }

    public void setAcucar(Double acucar) {
        this.acucar = acucar;
    }

    public Double getLactose() {
        return lactose;
    }

    public void setLactose(Double lactose) {
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
