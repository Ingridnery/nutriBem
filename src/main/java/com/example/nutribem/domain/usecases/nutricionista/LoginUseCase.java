package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.usecases.utils.Notification;

import static com.example.nutribem.domain.usecases.utils.Validator.nullOrEmpty;

public class LoginUseCase {

    private final NutricionistaDAO dao;
    private static final int MINLENGTH = 4;

    public LoginUseCase(NutricionistaDAO nutricionistaDAO) {
        this.dao = nutricionistaDAO;
    }

    public Boolean login(String userName, String senha) {
        Notification notification = new Notification();

        if (nullOrEmpty(senha)) {
            notification.addError("Senha não pode ser vazia!");
        }


        if (nullOrEmpty(userName)) {
            notification.addError("Nome de usuario is null or empty");
        }

        if (!dao.getSenhaFromUserName(userName).equals(senha)) {
            notification.addError("Senha invalida!");
        }

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return true;
    }
}
