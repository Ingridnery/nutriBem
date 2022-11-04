package com.example.nutribem.domain.usecases.alimento;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class AlimentoDTO {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "serving_portion")
    private Float serving_portion;
    @CsvBindByName(column = "calories")
    private Float calories;
    @CsvBindByName(column = "cholesterol")
    private Float cholesterol;
    @CsvBindByName(column = "saturated_fat")
    private Float saturated_fat;
    @CsvBindByName(column = "sodium")
    private Float sodium;
    @CsvBindByName(column = "sugars")
    private Float sugars;
    @CsvBindByName(column = "lactose")
    private Float lactose;
    @CsvBindByName(column = "gluten")
    private Float gluten;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getServing_portion() {
        return serving_portion;
    }

    public void setServing_portion(Float serving_portion) {
        this.serving_portion = serving_portion;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Float getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(Float saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public Float getSugars() {
        return sugars;
    }

    public void setSugars(Float sugars) {
        this.sugars = sugars;
    }

    public Float getLactose() {
        return lactose;
    }

    public void setLactose(Float lactose) {
        this.lactose = lactose;
    }

    public Float getGluten() {
        return gluten;
    }

    public void setGluten(Float gluten) {
        this.gluten = gluten;
    }

    @Override
    public String toString() {
        return "AlimentoDTO{" +
                "name='" + name + '\'' +
                ", serving_portion=" + serving_portion +
                ", calories=" + calories +
                ", cholesterol=" + cholesterol +
                ", saturated_fat=" + saturated_fat +
                ", sodium=" + sodium +
                ", sugars=" + sugars +
                ", lactose=" + lactose +
                ", gluten=" + gluten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlimentoDTO that = (AlimentoDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(serving_portion, that.serving_portion) && Objects.equals(calories, that.calories) && Objects.equals(cholesterol, that.cholesterol) && Objects.equals(saturated_fat, that.saturated_fat) && Objects.equals(sodium, that.sodium) && Objects.equals(sugars, that.sugars) && Objects.equals(lactose, that.lactose) && Objects.equals(gluten, that.gluten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, serving_portion, calories, cholesterol, saturated_fat, sodium, sugars, lactose, gluten);
    }
}
