package com.example.nutribem.domain.entities.alimento;

import com.example.nutribem.domain.usecases.valoresNutricionais.ValoresNutricionais;

import java.util.Objects;

public class Alimento {

    private String nome;
    private Integer id;
    private Integer porcao;
    private Integer calorias;
    private Integer colesterol;
    private Boolean gluten;
    private Double gordurasSaturadas;
    private Double proteinas;
    private Double sodio;
    private Double acucar;
    private Double lactose;
    private Boolean ativado;

    public Alimento(String nome, Integer porcao, Integer calorias, Integer colesterol, Boolean gluten, Double gordurasSaturadas, Double proteinas, Double sodio, Double acucar, Double lactose) {
        this.nome = nome;
        this.proteinas = proteinas;
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

    public Alimento() {
    }

    public ValoresNutricionais calculateValoresNutricionais() {
        return new ValoresNutricionais(getCalorias(), getColesterol(), getGluten(),
                getGordurasSaturadas(), proteinas, getSodio(), getAcucar(), getLactose());
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
        if (this.id != null)
            throw new IllegalArgumentException("O alimento " + this.nome + " já possui um id associado.");
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

    @Override
    public String toString() {
        return "Alimento{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", porcao=" + porcao +
                ", calorias=" + calorias +
                ", colesterol=" + colesterol +
                ", gluten=" + gluten +
                ", gordurasSaturadas=" + gordurasSaturadas +
                ", sodio=" + sodio +
                ", acucar=" + acucar +
                ", lactose=" + lactose +
                ", ativado=" + ativado +
                '}';
    }
}
