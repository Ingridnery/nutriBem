package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CardapioInpustRequestValidator extends Validator<Cardapio> {
    @Override
    public Notification validate(Cardapio cardapio) {
        Notification notification = new Notification();
        if (cardapio == null) {
            notification.addError("Cardapio is null");
            return notification;
        }

        if (nullOrEmpty(cardapio.getNumeroDia()))
            notification.addError("Numero dia is null or empty");
        if (nullOrEmpty(cardapio.getPlanoNutricional().getId()))
            notification.addError("Id Plano Nutricional is null or empty");

        return notification;
    }
}
