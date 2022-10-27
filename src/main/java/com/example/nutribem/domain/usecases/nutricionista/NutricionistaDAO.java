package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.entities.nutricionista.Nutricionista;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface NutricionistaDAO {


    String create(Nutricionista nutricionista);

    Optional<Nutricionista> findByUserName(String userName);

    boolean update(Nutricionista nutricionista);

    ArrayList<String> getDicasSenhaFromUserName(String userName);

    String getSenhaFromUserName(String senha);


}
