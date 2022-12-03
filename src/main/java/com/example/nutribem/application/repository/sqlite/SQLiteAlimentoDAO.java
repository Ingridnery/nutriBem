package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.alimento.AlimentoDAO;

import java.util.List;
import java.util.Optional;

public class SQLiteAlimentoDAO implements AlimentoDAO {
    @Override
    public Optional<Alimento> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Integer create(Alimento type) {
        return null;
    }

    @Override
    public Optional<Alimento> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Alimento> findAll() {
        return null;
    }

    @Override
    public boolean update(Alimento type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Alimento type) {
        return false;
    }
}
