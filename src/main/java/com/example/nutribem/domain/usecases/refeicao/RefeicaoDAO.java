package com.example.nutribem.domain.usecases.refeicao;

import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.List;

public interface RefeicaoDAO extends DAO<Refeicao,Integer> {
    List<Refeicao> findByCardapio(Integer cardapio);
}
