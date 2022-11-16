package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;

import java.util.List;

public class FindCardapioUseCase {

    private CardapioDAO cardapioDAO;

    public FindCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public List<Cardapio> findAll(){
        return cardapioDAO.findAll();
    }
    public List<Cardapio> findByPlanoNutricional(Integer idPlano){
        return cardapioDAO.findByPlanoNutricional(idPlano);
    }
}
