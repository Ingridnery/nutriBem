package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface AlimentoDAO extends DAO<Alimento, Integer> {
    Optional<Alimento> findByName(String name);

    boolean isInAnyRefeicao(Alimento alimento);
    List<Alimento> findAlimentosFromRefeicao(Refeicao refeicao);
}
