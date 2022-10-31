package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;


public class UpdateCardapioUseCase {

    private CardapioDAO cardapioDAO;

    public UpdateCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;

    }

    public Boolean update (Cardapio cardapio){
        Validator<Cardapio> validator = new CardapioInpustRequestValidator();
        Notification notification = validator.validate(cardapio);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        return cardapioDAO.update(cardapio);
    }
}
