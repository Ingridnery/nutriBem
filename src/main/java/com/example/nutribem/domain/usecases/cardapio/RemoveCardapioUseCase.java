package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class RemoveCardapioUseCase {

    private final CardapioDAO cardapioDAO;

    public RemoveCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public boolean delete(Cardapio cardapio) {

        Validator<Cardapio> validator = new CardapioInpustRequestValidator();
        Notification notification = validator.validate(cardapio);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if (cardapioDAO.findOne(cardapio.getId()).isEmpty())
            throw new EntityAlreadyExistsException("O cardapio '" + cardapio.getId() + "' não está cadastrado.");
        return cardapioDAO.delete(cardapio);
    }
}
