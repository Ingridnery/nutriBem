package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.usecases.utils.Notification;

import java.util.ArrayList;

import static com.example.nutribem.domain.usecases.utils.Validator.nullOrEmpty;

public class RecuperaSenhaUseCase {

    private final NutricionistaDAO dao;
    Notification notification = new Notification();
    private static final int MINLENGTH = 4;

    public RecuperaSenhaUseCase(NutricionistaDAO dao) {
        this.dao = dao;
    }

    public ArrayList<String> dicasSenha(String userName) {
        if (nullOrEmpty(userName)) {
            notification.addError("Nome de usuario is null or empty");
        }
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        return dao.getDicasSenhaFromUserName(userName);
    }

    public Boolean recuperaSenha(String senha, String userName) {

        if (nullOrEmpty(userName))
            notification.addError("Nome de usuario is null or empty");

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if (nullOrEmpty(senha))
            notification.addError("Senha is null or empty");

        if (senha.length() < MINLENGTH)
            notification.addError("Senha does not have four digits");

        String senhaDB = dao.getSenhaFromUserName(userName);

        if (!senhaDB.equals(senha)) {
            notification.addError("Senha invalida!");
        }
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return true;


    }

}
