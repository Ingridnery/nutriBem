package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Collection;
import java.util.Date;

public class PlanoNutricionalInputRequestValidator extends Validator<PlanoNutricional> {
    @Override
    public Notification validate(PlanoNutricional planoNutricional) {

        Notification notification = new Notification();
        if(planoNutricional == null){
            notification.addError("Plano nutricional is null");
            return notification;
        }

        if(nullOrEmpty(planoNutricional.getNome()))
            notification.addError("Nome is null or empty");

        if(nullOrEmpty((Collection) planoNutricional.getDataFim()))
            notification.addError("Data fim is null or empty");

        if(nullOrEmpty((Collection) planoNutricional.getDataInicio()))
            notification.addError("Data inicio is null or empty");

        return notification;


    }
}
