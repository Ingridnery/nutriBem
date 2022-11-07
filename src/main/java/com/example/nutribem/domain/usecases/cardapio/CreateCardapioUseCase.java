package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.planoNutricional.PlanoNutricionalInputRequestValidator;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CreateCardapioUseCase {

    private CardapioDAO cardapioDAO;

    public CreateCardapioUseCase(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }

    public Integer insert(Cardapio cardapio){
        Validator<Cardapio> validator = new CardapioInpustRequestValidator();
        Notification notification = validator.validate(cardapio);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if(cardapioDAO.findOne(cardapio.getId()).isPresent())
            throw new EntityAlreadyExistsException("O cardapio '"+ cardapio.getId() +"' já está cadastrado.");
        return cardapioDAO.create(cardapio);
    }
}
