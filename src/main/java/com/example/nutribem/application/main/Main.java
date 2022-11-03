package com.example.nutribem.application.main;

import com.example.nutribem.application.repository.*;
import com.example.nutribem.domain.usecases.alimento.*;
import com.example.nutribem.domain.usecases.cardapio.*;
import com.example.nutribem.domain.usecases.nutricionista.*;
import com.example.nutribem.domain.usecases.paciente.*;
import com.example.nutribem.domain.usecases.planoNutricional.*;
import com.example.nutribem.domain.usecases.refeicao.*;

public class Main {

    public static CreateAlimentoUseCase createAlimentoUseCase;
    public static FindAlimentoUseCase findAlimentoUseCase;
    public static RemoveAlimentoUseCase removeAlimentoUseCase;
    public static UpdateAlimentoUseCase updateAlimentoUseCase;
    public static ActivateAlimentoUseCase activateAlimentoUseCase;

    public static CreateCardapioUseCase createCardapioUseCase;
    public static UpdateCardapioUseCase updateCardapioUseCase;
    public static FindCardapioUseCase findCardapioUseCase;
    public static FindCardapioByNumeroDiaUseCase findCardapioByNumeroDiaUseCase;
    public static RemoveCardapioUseCase removeCardapioUseCase;

    public static CreateDicaSenhaUseCase createDicaSenhaUseCase;
    public static CreateNutricionistaUseCase createNutricionistaUseCase;
    public static LoginUseCase loginUseCase;
    public static RecuperaSenhaUseCase recuperaSenhaUseCase;

    public static ActivatePacienteUseCase activatePacienteUseCase;
    public static CreatePacienteUseCase createPacienteUseCase;
    public static FindPacienteUseCase findPacienteUseCase;
    public static RemovePacienteUseCase removePacienteUseCase;
    public static UpdatePacienteUseCase updatePacienteUseCase;

    public static CreatePlanoNutricionalUseCase createPlanoNutricionalUseCase;
    public static FindPlanoNutricionalUseCase findPlanoNutricionalUseCase;
    public static RemovePlanoNutricionalUseCase removePlanoNutricionalUseCase;
    public static UpdatePlanoNutricionalUseCase updatePlanoNutricionalUseCase;

    public static CreateRefeicaoUseCase createRefeicaoUseCase;
    public static FindRefeicaoUseCase findRefeicaoUseCase;
    public static RemoveRefeicaoUseCase removeRefeicaoUseCase;
    public static UpdateRefeicaoUseCase updateRefeicaoUseCase;

    public static void main(String[] args) {
        configureInjection();
    }
    public static void configureInjection(){
        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        createAlimentoUseCase = new CreateAlimentoUseCase(alimentoDAO);
        findAlimentoUseCase = new FindAlimentoUseCase(alimentoDAO);
        removeAlimentoUseCase = new RemoveAlimentoUseCase(alimentoDAO);
        updateAlimentoUseCase = new UpdateAlimentoUseCase(alimentoDAO);
        activateAlimentoUseCase = new ActivateAlimentoUseCase(alimentoDAO);

        CardapioDAO cardapioDAO = new InMemoryCardapioDAO();
        createCardapioUseCase = new CreateCardapioUseCase(cardapioDAO);
        findCardapioUseCase = new FindCardapioUseCase(cardapioDAO);
        removeCardapioUseCase = new RemoveCardapioUseCase(cardapioDAO);
        findCardapioByNumeroDiaUseCase = new FindCardapioByNumeroDiaUseCase(cardapioDAO);
        updateCardapioUseCase = new UpdateCardapioUseCase(cardapioDAO);

        NutricionistaDAO nutricionistaDAO = new InMemoryNutricionistaDAO();
        createDicaSenhaUseCase = new CreateDicaSenhaUseCase(nutricionistaDAO);
        createNutricionistaUseCase = new CreateNutricionistaUseCase(nutricionistaDAO);
        loginUseCase = new LoginUseCase(nutricionistaDAO);
        recuperaSenhaUseCase = new RecuperaSenhaUseCase(nutricionistaDAO);

        PacienteDAO pacienteDAO = new InMemoryPacienteDAO();
        activatePacienteUseCase = new ActivatePacienteUseCase(pacienteDAO);
        createPacienteUseCase = new CreatePacienteUseCase(pacienteDAO);
        findPacienteUseCase = new FindPacienteUseCase(pacienteDAO);
        removePacienteUseCase = new RemovePacienteUseCase(pacienteDAO);
        updatePacienteUseCase = new UpdatePacienteUseCase(pacienteDAO);

        PlanoNutricionalDAO planoNutricionalDAO = new InMemoryPlanoNutricionalDAO();
        createPlanoNutricionalUseCase = new CreatePlanoNutricionalUseCase(planoNutricionalDAO);
        findPlanoNutricionalUseCase = new FindPlanoNutricionalUseCase(planoNutricionalDAO);
        removePlanoNutricionalUseCase = new RemovePlanoNutricionalUseCase();
        updatePlanoNutricionalUseCase = new UpdatePlanoNutricionalUseCase(planoNutricionalDAO);

        RefeicaoDAO refeicaoDAO = new InMemoryRefeicaoDAO();
        createRefeicaoUseCase = new CreateRefeicaoUseCase(refeicaoDAO);
        findRefeicaoUseCase = new FindRefeicaoUseCase(refeicaoDAO);
        removeRefeicaoUseCase = new RemoveRefeicaoUseCase(refeicaoDAO);
        updateRefeicaoUseCase = new UpdateRefeicaoUseCase(refeicaoDAO);


    }
}
