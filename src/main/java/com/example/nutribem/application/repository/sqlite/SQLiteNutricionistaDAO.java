package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.nutricionista.NutricionistaDAO;

import java.util.ArrayList;
import java.util.Optional;

public class SQLiteNutricionistaDAO implements NutricionistaDAO {
    @Override
    public String create(Nutricionista nutricionista) {
        return null;
    }

    @Override
    public Optional<Nutricionista> findByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public boolean update(Nutricionista nutricionista) {
        return false;
    }

    @Override
    public ArrayList<String> getDicasSenhaFromUserName(String userName) {
        return null;
    }

    @Override
    public String getSenhaFromUserName(String senha) {
        return null;
    }
}
