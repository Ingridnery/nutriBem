package com.example.nutribem.domain.usecases.valoresNutricionais;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.alimento.AlimentoDAO;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;

import java.util.List;

public class CalculateValoresNutricionaisUseCase {

    private AlimentoDAO alimentoDAO;
    private RefeicaoDAO refeicaoDAO;

    public CalculateValoresNutricionaisUseCase(AlimentoDAO alimentoDAO, RefeicaoDAO refeicaoDAO) {
        this.alimentoDAO = alimentoDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public ValoresNutricionais from(Refeicao refeicao){
        ValoresNutricionais valNutricionais = new ValoresNutricionais();
        refeicaoDAO.findAlimentosFromRefeicao(refeicao).forEach(alimento ->{
            valNutricionais.somar(alimento.calculateValoresNutricionais());
        });

        return valNutricionais;
    }
}
