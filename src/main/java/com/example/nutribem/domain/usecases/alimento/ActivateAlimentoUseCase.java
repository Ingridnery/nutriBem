package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;

public class ActivateAlimentoUseCase {

    private AlimentoDAO alimentoDAO;
    private RefeicaoDAO refeicaoDAO;

    public ActivateAlimentoUseCase(AlimentoDAO alimentoDAO, RefeicaoDAO refeicaoDAO) {
        this.alimentoDAO = alimentoDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public boolean activate(Alimento alimento){
        if(alimento.isAtivado())
            throw new IllegalStateException("O alimento já está ativado.");

        alimento.setAtivado(true);
        return alimentoDAO.update(alimento);
    }

    public boolean deactivate(Alimento alimento){
        if(!alimento.isAtivado())
            throw new IllegalStateException("O alimento já está desativado.");

        if(refeicaoDAO.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser desativado.");

        alimento.setAtivado(false);
        return alimentoDAO.update(alimento);
    }
}
