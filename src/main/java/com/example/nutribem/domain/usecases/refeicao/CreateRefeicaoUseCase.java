package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CreateRefeicaoUseCase {
    private final RefeicaoDAO dao;

    public CreateRefeicaoUseCase(RefeicaoDAO dao) {
        this.dao = dao;
    }

    public Integer insert(Refeicao refeicao) {
        Validator<Refeicao> validator = new RefeicaoInputRequestValidator();
        Notification notification = validator.validate(refeicao);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return dao.create(refeicao);
    }
}
