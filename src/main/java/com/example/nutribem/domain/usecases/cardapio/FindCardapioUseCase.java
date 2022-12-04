package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;

import java.util.List;

public class FindCardapioUseCase {

    private final CardapioDAO cardapioDAO;

    public FindCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public List<Cardapio> findAll() {
        return cardapioDAO.findAll();
    }

    public List<Cardapio> findByPlanoNutricional(Integer idPlano) {
        return cardapioDAO.findByPlanoNutricional(idPlano);
    }
}
