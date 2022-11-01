package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;

import java.util.Objects;

public class RemoveRefeicaoUseCase {
    private RefeicaoDAO dao;

    public RemoveRefeicaoUseCase(RefeicaoDAO dao) {
        this.dao = dao;
    }

    public boolean remove(Integer id) {
        if (dao.findOne(id).isEmpty())
            throw new EntityNotFoundException("Refeicao não encontrada.");

        return dao.deleteByKey(id);
    }

    public boolean remove(Refeicao refeicao) {
        if (Objects.isNull(refeicao))
            throw new IllegalArgumentException("Refeicao não pode ser nulo.");

        return remove(refeicao.getId());
    }
}
