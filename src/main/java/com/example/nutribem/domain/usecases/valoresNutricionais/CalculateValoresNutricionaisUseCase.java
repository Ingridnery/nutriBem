package com.example.nutribem.domain.usecases.valoresNutricionais;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.alimento.AlimentoDAO;

import java.util.List;

public class CalculateValoresNutricionaisUseCase {

    private AlimentoDAO dao;

    public CalculateValoresNutricionaisUseCase(AlimentoDAO dao) {
        this.dao = dao;
    }

    public ValoresNutricionais from(Refeicao refeicao){
        ValoresNutricionais valNutricionais = new ValoresNutricionais();
        dao.findAlimentosFromRefeicao(refeicao).forEach(alimento ->{
            valNutricionais.somar(alimento.calculateValoresNutricionais());
        });

        return valNutricionais;
    }
}
