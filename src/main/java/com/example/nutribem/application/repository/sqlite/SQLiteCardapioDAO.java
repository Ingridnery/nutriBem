package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.cardapio.CardapioDAO;

import java.util.List;
import java.util.Optional;

public class SQLiteCardapioDAO implements CardapioDAO {

    @Override
    public List<Cardapio> findByNumeroDia(Integer numeroDia) {
        return null;
    }

    @Override
    public List<Cardapio> findByPlanoNutricional(Integer idPlano) {
        return null;
    }

    @Override
    public Integer create(Cardapio type) {
        return null;
    }

    @Override
    public Optional<Cardapio> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Cardapio> findAll() {
        return null;
    }

    @Override
    public boolean update(Cardapio type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Cardapio type) {
        return false;
    }
}
