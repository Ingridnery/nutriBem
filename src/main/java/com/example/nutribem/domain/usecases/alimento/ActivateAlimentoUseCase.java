package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;

public class ActivateAlimentoUseCase {

    private AlimentoDAO dao;

    public ActivateAlimentoUseCase(AlimentoDAO dao) {
        this.dao = dao;
    }

    public boolean activate(Alimento alimento){
        if(alimento.isAtivado())
            throw new IllegalStateException("O alimento já está ativado.");

        alimento.setAtivado(true);
        return dao.update(alimento);
    }

    public boolean deactivate(Alimento alimento){
        if(!alimento.isAtivado())
            throw new IllegalStateException("O alimento já está desativado.");

        if(dao.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser desativado.");

        alimento.setAtivado(false);
        return dao.update(alimento);
    }
}
