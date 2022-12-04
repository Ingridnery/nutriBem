package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Objects;

public class CreateCardapioUseCase {

    private final CardapioDAO cardapioDAO;

    public CreateCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public Integer insert(Cardapio cardapio) {
        Validator<Cardapio> validator = new CardapioInpustRequestValidator();
        Notification notification = validator.validate(cardapio);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if (Objects.nonNull(cardapio.getId()) && cardapioDAO.findOne(cardapio.getId()).isPresent())
            throw new EntityAlreadyExistsException("O cardapio '" + cardapio.getId() + "' já está cadastrado.");
        return cardapioDAO.create(cardapio);
    }
}
