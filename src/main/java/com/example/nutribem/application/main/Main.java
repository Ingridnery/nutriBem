package com.example.nutribem.application.main;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.application.repository.inmemory.*;
import com.example.nutribem.application.repository.sqlite.*;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.alimento.*;
import com.example.nutribem.domain.usecases.cardapio.*;
import com.example.nutribem.domain.usecases.nutricionista.*;
import com.example.nutribem.domain.usecases.paciente.*;
import com.example.nutribem.domain.usecases.planoNutricional.*;
import com.example.nutribem.domain.usecases.refeicao.*;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioContatosUseCase;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioPlanoNutricionalUseCase;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioPlanosVencidosUseCase;
import com.example.nutribem.domain.usecases.valoresNutricionais.CalculateValoresNutricionaisUseCase;

import java.util.ArrayList;

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
    public static LogoutUseCase logoutUseCase;
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

    public static EmitirRelatorioContatosUseCase emitirRelatorioContatosUseCase;

    public static EmitirRelatorioPlanosVencidosUseCase emitirRelatorioPlanosVencidosUseCase;

    public static CalculateValoresNutricionaisUseCase calculateValoresNutricionaisUseCase;

    public static EmitirRelatorioPlanoNutricionalUseCase emitirRelatorioPlanoNutricionalUseCase;

    public static void main(String[] args) {
        createDatabase();
        //configureSQLiteInjection();
        configureInMemoryInjection();

        Nutricionista nutricionista = new Nutricionista("admin","admin");
        createNutricionistaUseCase.insert(nutricionista,"admin");

        ArrayList<String> dicasSenha = new ArrayList<>();
        dicasSenha.add("Cachorro");
        dicasSenha.add("Data de aniversario");
        dicasSenha.add("Padrão");
        createDicaSenhaUseCase.insert(nutricionista,dicasSenha);

        WindowLoader.main(args);
    }

    private static void createDatabase() {
        DatabaseBuilder db = new DatabaseBuilder();
        db.buildIfMissing();
    }

    public static void configureInMemoryInjection(){
        RefeicaoDAO refeicaoDAO = new InMemoryRefeicaoDAO();
        createRefeicaoUseCase = new CreateRefeicaoUseCase(refeicaoDAO);
        findRefeicaoUseCase = new FindRefeicaoUseCase(refeicaoDAO);
        removeRefeicaoUseCase = new RemoveRefeicaoUseCase(refeicaoDAO);
        updateRefeicaoUseCase = new UpdateRefeicaoUseCase(refeicaoDAO);

        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        createAlimentoUseCase = new CreateAlimentoUseCase(alimentoDAO);
        findAlimentoUseCase = new FindAlimentoUseCase(alimentoDAO);
        removeAlimentoUseCase = new RemoveAlimentoUseCase(alimentoDAO, refeicaoDAO);
        updateAlimentoUseCase = new UpdateAlimentoUseCase(alimentoDAO);
        activateAlimentoUseCase = new ActivateAlimentoUseCase(alimentoDAO, refeicaoDAO);
        calculateValoresNutricionaisUseCase = new CalculateValoresNutricionaisUseCase(alimentoDAO, refeicaoDAO);

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
        logoutUseCase =  new LogoutUseCase();
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
        removePlanoNutricionalUseCase = new RemovePlanoNutricionalUseCase(planoNutricionalDAO);
        updatePlanoNutricionalUseCase = new UpdatePlanoNutricionalUseCase(planoNutricionalDAO);

        emitirRelatorioContatosUseCase = new EmitirRelatorioContatosUseCase(pacienteDAO);
        emitirRelatorioPlanosVencidosUseCase = new EmitirRelatorioPlanosVencidosUseCase(planoNutricionalDAO);
        emitirRelatorioPlanoNutricionalUseCase = new EmitirRelatorioPlanoNutricionalUseCase(cardapioDAO,refeicaoDAO);


    }

    public static void configureSQLiteInjection() {
        NutricionistaDAO nutricionistaDAO = new SQLiteNutricionistaDAO();
        createDicaSenhaUseCase = new CreateDicaSenhaUseCase(nutricionistaDAO);
        createNutricionistaUseCase = new CreateNutricionistaUseCase(nutricionistaDAO);
        loginUseCase = new LoginUseCase(nutricionistaDAO);
        logoutUseCase =  new LogoutUseCase();
        recuperaSenhaUseCase = new RecuperaSenhaUseCase(nutricionistaDAO);

        PacienteDAO pacienteDAO = new SQLitePacienteDAO();
        activatePacienteUseCase = new ActivatePacienteUseCase(pacienteDAO);
        createPacienteUseCase = new CreatePacienteUseCase(pacienteDAO);
        findPacienteUseCase = new FindPacienteUseCase(pacienteDAO);
        removePacienteUseCase = new RemovePacienteUseCase(pacienteDAO);
        updatePacienteUseCase = new UpdatePacienteUseCase(pacienteDAO);

        PlanoNutricionalDAO planoNutricionalDAO = new SQLitePlanoNutricionalDAO();
        createPlanoNutricionalUseCase = new CreatePlanoNutricionalUseCase(planoNutricionalDAO);
        findPlanoNutricionalUseCase = new FindPlanoNutricionalUseCase(planoNutricionalDAO);
        removePlanoNutricionalUseCase = new RemovePlanoNutricionalUseCase(planoNutricionalDAO);
        updatePlanoNutricionalUseCase = new UpdatePlanoNutricionalUseCase(planoNutricionalDAO);

        CardapioDAO cardapioDAO = new SQLiteCardapioDAO();
        createCardapioUseCase = new CreateCardapioUseCase(cardapioDAO);
        findCardapioUseCase = new FindCardapioUseCase(cardapioDAO);
        removeCardapioUseCase = new RemoveCardapioUseCase(cardapioDAO);
        findCardapioByNumeroDiaUseCase = new FindCardapioByNumeroDiaUseCase(cardapioDAO);
        updateCardapioUseCase = new UpdateCardapioUseCase(cardapioDAO);

        RefeicaoDAO refeicaoDAO = new SQLiteRefeicaoDAO();
        createRefeicaoUseCase = new CreateRefeicaoUseCase(refeicaoDAO);
        findRefeicaoUseCase = new FindRefeicaoUseCase(refeicaoDAO);
        removeRefeicaoUseCase = new RemoveRefeicaoUseCase(refeicaoDAO);
        updateRefeicaoUseCase = new UpdateRefeicaoUseCase(refeicaoDAO);

        AlimentoDAO alimentoDAO = new SQLiteAlimentoDAO();
        createAlimentoUseCase = new CreateAlimentoUseCase(alimentoDAO);
        findAlimentoUseCase = new FindAlimentoUseCase(alimentoDAO);
        removeAlimentoUseCase = new RemoveAlimentoUseCase(alimentoDAO, refeicaoDAO);
        updateAlimentoUseCase = new UpdateAlimentoUseCase(alimentoDAO);
        activateAlimentoUseCase = new ActivateAlimentoUseCase(alimentoDAO, refeicaoDAO);
        calculateValoresNutricionaisUseCase = new CalculateValoresNutricionaisUseCase(alimentoDAO, refeicaoDAO);
    }
}
