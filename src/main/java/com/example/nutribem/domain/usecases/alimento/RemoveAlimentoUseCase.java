package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class RemoveAlimentoUseCase {
    private AlimentoDAO dao;

    public RemoveAlimentoUseCase(AlimentoDAO dao) {
        this.dao = dao;
    }

    public boolean remove(Alimento alimento){
        if(alimento == null || dao.findOne(alimento.getId()).isEmpty())
            throw new EntityNotFoundException("Alimento não encontrado.");
        if(dao.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser excluído.");
        return dao.delete(alimento);
    }

    public boolean remove(Integer id){
        Objects.requireNonNull(id);

        Alimento alimento = dao.findOne(id).orElseThrow(() -> new EntityNotFoundException("Alimento não encontrado."));

        if(dao.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser excluído.");

        return dao.deleteByKey(id);
    }
}
