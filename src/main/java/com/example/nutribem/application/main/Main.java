package com.example.nutribem.application.main;

import com.example.nutribem.application.repository.*;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.entities.paciente.CPF;
import com.example.nutribem.domain.entities.paciente.IntoleranciaLactose;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.paciente.Sexo;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.entities.refeicao.RefeicaoCategoria;
import com.example.nutribem.domain.usecases.alimento.*;
import com.example.nutribem.domain.usecases.cardapio.*;
import com.example.nutribem.domain.usecases.nutricionista.*;
import com.example.nutribem.domain.usecases.paciente.*;
import com.example.nutribem.domain.usecases.planoNutricional.*;
import com.example.nutribem.domain.usecases.refeicao.*;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioContatosUseCase;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioPlanoNutricionalUseCase;
import com.example.nutribem.domain.usecases.relatorios.EmitirRelatorioPlanosVencidosUseCase;
import com.example.nutribem.domain.usecases.utils.NotAuthenticatedException;
import com.example.nutribem.domain.usecases.valoresNutricionais.CalculateValoresNutricionaisUseCase;

import java.time.LocalDate;
import java.time.LocalTime;
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
        configureInjection();

        Nutricionista nutricionista = new Nutricionista("admin","admin");
        createNutricionistaUseCase.insert(nutricionista,"admin");

        ArrayList<String> dicasSenha = new ArrayList<>();
        dicasSenha.add("Cachorro");
        dicasSenha.add("Data de aniversario");
        dicasSenha.add("Padrão");
        createDicaSenhaUseCase.insert(nutricionista,dicasSenha);
        loginUseCase.login("admin","admin");
        System.out.println("\nDicas de senha:");
        recuperaSenhaUseCase.dicasSenha("admin").forEach(System.out::println);

        if (!loginUseCase.isLogged())
            throw new NotAuthenticatedException("Nutricionista nao esta logado.");

        CPF cpf  = CPF.valueOf("851.356.878-31");
        Paciente paciente = new Paciente(88,180,"José",cpf,"jose@email.com",
            "99384-7858","Sem historico",             IntoleranciaLactose.INTOLERANTE,
            false,false,"Nenhuma",
            "Saúdavel","Ganhar massa", LocalDate.of(1998,10,15),
            100.15, Sexo.MASCULINO,true);

        createPacienteUseCase.insert(paciente);

        CPF cpfPaciente2 = CPF.valueOf("224.067.048-74");
        Paciente paciente2 = new Paciente(96,170,"Maria",cpfPaciente2,"maria@email",
                "89657-7456","Cirurgia de apendice",IntoleranciaLactose.APTO,false,false,"Camarão","Cardiaca",
                "Perder peso",LocalDate.of(1996,10,02),89.2,Sexo.FEMININO);

        createPacienteUseCase.insert(paciente2);

        System.out.println("\nPacientes cadastrados:");
        findPacienteUseCase.findAll().forEach(paciente1 -> {
            System.out.println(paciente1.getNome());
        });
        paciente.setNome("José Carlos");
        updatePacienteUseCase.update(paciente);
        System.out.println("\nPaciente atualizado:");

        System.out.println((findPacienteUseCase.findByOne(paciente.getId()).get().getNome()));
        System.out.println("\nContatos de pacientes:");
        emitirRelatorioContatosUseCase.emitir().forEach(System.out::println);
        removePacienteUseCase.remove(paciente2.getId());
        System.out.println("\nPacientes cadastrados:");
        findPacienteUseCase.findAll().forEach(paciente1 -> {
            System.out.println(paciente1.getNome());
        });
        System.out.println();

        PlanoNutricional planoNutricional = new PlanoNutricional("Emagrecimento",LocalDate.now(),LocalDate.of(2022,12,10),paciente2);
        createPlanoNutricionalUseCase.insert(planoNutricional);

        PlanoNutricional planoNutricional1 = new PlanoNutricional("Ganho de massa",LocalDate.now(),LocalDate.of(2022,12,25),paciente);
        createPlanoNutricionalUseCase.insert(planoNutricional1);

        PlanoNutricional planoNutricional2 = new PlanoNutricional("Saúdavel", LocalDate.now(),LocalDate.of(2022,10,29),paciente2);
        createPlanoNutricionalUseCase.insert(planoNutricional2);

        System.out.println("\nPlanos nutricionais cadastrados: ");
        findPlanoNutricionalUseCase.findAll().forEach(planoNutricional3 -> {
            System.out.println(planoNutricional3.getNome());
        });
        System.out.println("\nPacientes com planos nutricionais vencidos:");
        System.out.println(emitirRelatorioPlanosVencidosUseCase.emitir());




        Cardapio cardapio = new Cardapio(15,planoNutricional);
        createCardapioUseCase.insert(cardapio);

        Cardapio cardapio1 = new Cardapio(15,planoNutricional1);
        createCardapioUseCase.insert(cardapio1);

        Refeicao refeicao = new Refeicao( LocalTime.now(), RefeicaoCategoria.ALMOCO,cardapio);
        createRefeicaoUseCase.insert(refeicao);

        Refeicao refeicao1 = new Refeicao(LocalTime.now(),RefeicaoCategoria.CEIA,cardapio1);
        createRefeicaoUseCase.insert(refeicao1);

        Alimento alimento = new Alimento("Cenoura",1,1,14,false,15F,2F,0F,0F);
        alimento.setRefeicao(refeicao);
        createAlimentoUseCase.insert(alimento);


        Alimento alimento1 = new Alimento("Feijoada",1,500,100,true,275F,100F,50F,41F);
        alimento1.setRefeicao(refeicao1);
        createAlimentoUseCase.insert(alimento1);


        System.out.println("\nRelatorio de plano nutricional:");
        System.out.println(emitirRelatorioPlanoNutricionalUseCase.emitir(planoNutricional,calculateValoresNutricionaisUseCase));


        System.out.println("\nCardapios cadastrados: ");
        findCardapioUseCase.findAll().forEach(cardapio2 ->
                System.out.println(cardapio2.getId()));
        removePlanoNutricionalUseCase.remove(planoNutricional);
        System.out.println("\nCardapios cadastrados após remoção:");
        findCardapioUseCase.findAll().forEach(cardapio2 ->
                System.out.println(cardapio2.getId()));
        System.out.println("\nRefeições cadastradas: ");
        findRefeicaoUseCase.findAll().forEach(refeicoes ->
                System.out.println(refeicoes.getId()));
        removeCardapioUseCase.delete(cardapio1);

        System.out.println("\nAlimentos cadastrados: ");
        findAlimentoUseCase.findAll().forEach(alimento2 ->
                System.out.println(alimento2.getNome()));
    }

    public static void configureInjection(){
        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        createAlimentoUseCase = new CreateAlimentoUseCase(alimentoDAO);
        findAlimentoUseCase = new FindAlimentoUseCase(alimentoDAO);
        removeAlimentoUseCase = new RemoveAlimentoUseCase(alimentoDAO);
        updateAlimentoUseCase = new UpdateAlimentoUseCase(alimentoDAO);
        activateAlimentoUseCase = new ActivateAlimentoUseCase(alimentoDAO);
        calculateValoresNutricionaisUseCase = new CalculateValoresNutricionaisUseCase(alimentoDAO);

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
        removePlanoNutricionalUseCase = new RemovePlanoNutricionalUseCase(planoNutricionalDAO);
        updatePlanoNutricionalUseCase = new UpdatePlanoNutricionalUseCase(planoNutricionalDAO);

        RefeicaoDAO refeicaoDAO = new InMemoryRefeicaoDAO();
        createRefeicaoUseCase = new CreateRefeicaoUseCase(refeicaoDAO);
        findRefeicaoUseCase = new FindRefeicaoUseCase(refeicaoDAO);
        removeRefeicaoUseCase = new RemoveRefeicaoUseCase(refeicaoDAO);
        updateRefeicaoUseCase = new UpdateRefeicaoUseCase(refeicaoDAO);


        emitirRelatorioContatosUseCase = new EmitirRelatorioContatosUseCase(pacienteDAO);
        emitirRelatorioPlanosVencidosUseCase = new EmitirRelatorioPlanosVencidosUseCase(planoNutricionalDAO);
        emitirRelatorioPlanoNutricionalUseCase = new EmitirRelatorioPlanoNutricionalUseCase(cardapioDAO,refeicaoDAO);


    }
}
