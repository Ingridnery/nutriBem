package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class UpdateRefeicaoUseCase {
    private final RefeicaoDAO dao;

    public UpdateRefeicaoUseCase(RefeicaoDAO dao) {
        this.dao = dao;
    }

    public boolean update(Refeicao refeicao) {
        Validator<Refeicao> validator = new RefeicaoInputRequestValidator();
        Notification notification = validator.validate(refeicao);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Refeicao oldRefeicao = dao.findOne(refeicao.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Refeicao nao encontrada.")
                );

        if (!refeicao.getCardapio().equals(oldRefeicao.getCardapio()))
            throw new IllegalArgumentException("Cardapio da refeicao nao pode ser alterado.");

        return dao.update(refeicao);
    }
}
