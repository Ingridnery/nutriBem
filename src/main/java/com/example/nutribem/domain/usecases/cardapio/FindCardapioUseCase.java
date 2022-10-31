package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;

import java.util.List;

public class FindCardapioUseCase {

    private CardapioDAO cardapioDAO;

    public FindCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public List<Cardapio> findAll(){
        return cardapioDAO.findAll();
    }
}
