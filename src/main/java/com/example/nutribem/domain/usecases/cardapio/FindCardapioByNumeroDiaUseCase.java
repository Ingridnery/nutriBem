package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Optional;

public class FindCardapioByNumeroDiaUseCase {

    private CardapioDAO cardapioDAO;

    public FindCardapioByNumeroDiaUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public Optional<Cardapio> findByNumeroDia(Integer numeroDia){

        if(Validator.nullOrEmpty(numeroDia))
            throw new IllegalArgumentException("O numero do dia não pode ser nulo!.");
        if(cardapioDAO.findByNumeroDia(numeroDia).isEmpty())
            throw new EntityNotFoundException("O cardapio com número de dia: '"+ numeroDia +"' não está cadastrado.");
        return cardapioDAO.findByNumeroDia(numeroDia);
    }
}
