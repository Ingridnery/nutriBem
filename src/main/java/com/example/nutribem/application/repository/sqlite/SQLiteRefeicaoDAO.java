package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;

import java.util.List;
import java.util.Optional;

public class SQLiteRefeicaoDAO implements RefeicaoDAO {
    @Override
    public List<Refeicao> findByCardapio(Integer cardapio) {
        return null;
    }

    @Override
    public boolean isInAnyRefeicao(Alimento alimento) {
        return false;
    }

    @Override
    public List<Alimento> findAlimentosFromRefeicao(Refeicao refeicao) {
        return null;
    }

    @Override
    public Integer create(Refeicao type) {
        return null;
    }

    @Override
    public Optional<Refeicao> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Refeicao> findAll() {
        return null;
    }

    @Override
    public boolean update(Refeicao type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Refeicao type) {
        return false;
    }
}
