package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FindRefeicaoUseCase {
    private final RefeicaoDAO dao;

    public FindRefeicaoUseCase(RefeicaoDAO dao) {
        this.dao = dao;
    }

    public List<Refeicao> findByCardapio(Integer cardapio) {
        if (Validator.nullOrEmpty(cardapio))
            throw new IllegalArgumentException("ID do cardapio não pode ser nulo.");

        return dao.findByCardapio(cardapio);
    }

    public Optional<Refeicao> findById(Integer idRefeicao) {
        Objects.requireNonNull(idRefeicao, "ID da refeição não pode ser nulo.");
        return dao.findOne(idRefeicao);
    }

    public List<Refeicao> findByCardapio(Cardapio cardapio) {
        if (Objects.isNull(cardapio))
            throw new IllegalArgumentException("Cardapio não pode ser nulo.");

        return findByCardapio(cardapio.getId());
    }

    public List<Refeicao> findAll() {
        return dao.findAll();
    }
}
