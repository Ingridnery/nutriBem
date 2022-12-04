package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;

import java.util.Objects;

public class RemoveAlimentoUseCase {
    private final AlimentoDAO alimentoDAO;
    private final RefeicaoDAO refeicaoDAO;

    public RemoveAlimentoUseCase(AlimentoDAO alimentoDAO, RefeicaoDAO refeicaoDAO) {
        this.alimentoDAO = alimentoDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public boolean remove(Alimento alimento) {
        if (alimento == null || alimentoDAO.findOne(alimento.getId()).isEmpty())
            throw new EntityNotFoundException("Alimento não encontrado.");
        if (refeicaoDAO.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser excluído.");
        return alimentoDAO.delete(alimento);
    }

    public boolean remove(Integer id) {
        Objects.requireNonNull(id);

        Alimento alimento = alimentoDAO.findOne(id).orElseThrow(() -> new EntityNotFoundException("Alimento não encontrado."));

        if (refeicaoDAO.isInAnyRefeicao(alimento))
            throw new IllegalStateException("Um alimento que está em uma refeição não pode ser excluído.");

        return alimentoDAO.deleteByKey(id);
    }
}
