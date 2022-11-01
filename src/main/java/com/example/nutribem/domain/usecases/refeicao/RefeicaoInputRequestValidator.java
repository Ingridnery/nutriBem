package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Objects;

public class RefeicaoInputRequestValidator extends Validator<Refeicao> {
    @Override
    public Notification validate(Refeicao refeicao) {
        Notification notification = new Notification();

        if (refeicao == null) {
            notification.addError("Refeicao is null");
            return notification;
        }

        if (Objects.isNull(refeicao.getHorario()))
            notification.addError("Horario is null");

        if (Objects.isNull(refeicao.getCategoria()))
            notification.addError("Categoria is null");

        if (Objects.isNull(refeicao.getCardapio()))
            notification.addError("Cardapio is null");

        return notification;
    }
}
