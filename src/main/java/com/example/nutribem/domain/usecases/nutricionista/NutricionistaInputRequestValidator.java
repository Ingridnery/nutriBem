package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.PasswordsDoesntMatchException;
import com.example.nutribem.domain.usecases.utils.Validator;

public class NutricionistaInputRequestValidator extends Validator<Nutricionista> {
    private static final Integer MINLENGTH=4 ;


    @Override
    public Notification validate(Nutricionista nutricionista) {
        Notification notification = new Notification();

        if(nutricionista == null){
            notification.addError("Nutricionista is null");
            return notification;
        }
        if(nullOrEmpty(nutricionista.getSenha()))
            notification.addError("Senha is null or empty");

        if(nutricionista.getSenha().length()<MINLENGTH){
            notification.addError("Senha does not have four digits");
        }
        if(nullOrEmpty(nutricionista.getNomeUsuario())){
            notification.addError("Nome de usuario is null or empty");
        }
        return notification;
    }
}
