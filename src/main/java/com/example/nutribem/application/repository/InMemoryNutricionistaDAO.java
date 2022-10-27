package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.nutricionista.NutricionistaDAO;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;

import java.util.*;

public class InMemoryNutricionistaDAO implements NutricionistaDAO {

    public static final Map<String, Nutricionista> db = new LinkedHashMap<>();


    @Override
    public String create(Nutricionista nutricionista) {
        db.put(nutricionista.getNomeUsuario(),nutricionista);
        return nutricionista.getNomeUsuario();
    }

    @Override
    public Optional<Nutricionista> findByUserName(String userName) {

            return db.values().stream()
                    .filter(nutricionista -> nutricionista.getNomeUsuario().equals(userName))
                    .findAny();
    }

    @Override
    public boolean update(Nutricionista nutricionista) {
        String userName= nutricionista.getNomeUsuario();
        if(db.containsKey(userName)){
            db.replace(userName,nutricionista);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getDicasSenhaFromUserName(String userName) {

        Nutricionista nutricionista = findByUserName(userName).orElseThrow(()->
             new EntityNotFoundException("Usuario não encontrado!")
        );

        return nutricionista.getDicas();


    }

    @Override
    public String getSenhaFromUserName(String userName) {
        Nutricionista nutricionista = findByUserName(userName).orElseThrow(()->
                new EntityNotFoundException("Usuario não encontrado!")
        );
        return nutricionista.getSenha();
    }


}
