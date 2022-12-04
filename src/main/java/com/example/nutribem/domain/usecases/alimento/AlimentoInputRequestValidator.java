package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Collections;

public class AlimentoInputRequestValidator extends Validator<Alimento> {
    @Override
    public Notification validate(Alimento alimento) {
        Notification notification = new Notification();

        if (alimento == null) {
            notification.addError("Alimento is null");
            return notification;
        }

        if (nullOrEmpty(alimento.getNome()))
            notification.addError("Nome is null or empty");

        if (nullOrEmpty(alimento.getAcucar()))
            notification.addError("Acucar is null");

        if (nullOrEmpty(alimento.getPorcao()))
            notification.addError("Porcao is null");

        if (nullOrEmpty(alimento.getColesterol()))
            notification.addError("Colesterol is null");

        if (nullOrEmpty(alimento.getCalorias()))
            notification.addError("Calorias is null");

        if (nullOrEmpty(Collections.singleton(alimento.getGluten())))
            notification.addError("Gluten is null");

        if (nullOrEmpty(alimento.getGordurasSaturadas()))
            notification.addError("Gorduras Saturadas is null");

        if (nullOrEmpty(alimento.getSodio()))
            notification.addError("Sodio is null");

        if (nullOrEmpty(alimento.getLactose()))
            notification.addError("Lactose is null");

        return notification;
    }
}
