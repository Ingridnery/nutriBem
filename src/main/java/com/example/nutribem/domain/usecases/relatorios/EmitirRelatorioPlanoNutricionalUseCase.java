package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.cardapio.CardapioDAO;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;
import com.example.nutribem.domain.usecases.valoresNutricionais.CalculateValoresNutricionaisUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmitirRelatorioPlanoNutricionalUseCase {

    private final CardapioDAO cardapioDAO;
    private final RefeicaoDAO refeicaoDAO;

    public EmitirRelatorioPlanoNutricionalUseCase(CardapioDAO cardapioDAO, RefeicaoDAO refeicaoDAO) {
        this.cardapioDAO = cardapioDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public List<String> emitir(PlanoNutricional planoNutricional, CalculateValoresNutricionaisUseCase calculateValoresNutricionaisUseCase){
        List<String> relatorio = new ArrayList<>();
        cardapioDAO.findAll().forEach(cardapio -> {
            if(cardapio.getPlanoNutricional().getId().equals(planoNutricional.getId())){
                relatorio.add("Plano nutricional"+planoNutricional.getNome()+"\nCardapio "+ cardapio.getId()+" Dia: "+cardapio.getNumeroDia());
                refeicaoDAO.findByCardapio(cardapio.getId()).forEach(refeicao -> {
                    relatorio.add("Refeição "+ refeicao.getId()+" Categoria: "+ refeicao.getCategoria()+" Horário: "+refeicao.getHorario());
                    relatorio.add(calculateValoresNutricionaisUseCase.from(refeicao).toString());
                });
            }
        }
        );

        Collections.sort(relatorio);
        return relatorio;
    }
}
