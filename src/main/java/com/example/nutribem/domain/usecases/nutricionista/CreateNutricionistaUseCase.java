package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.alimento.AlimentoInputRequestValidator;
import com.example.nutribem.domain.usecases.paciente.PacienteInputRequestValidator;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.PasswordsDoesntMatchException;
import com.example.nutribem.domain.usecases.utils.Validator;

import static com.example.nutribem.domain.usecases.utils.Validator.nullOrEmpty;

public class CreateNutricionistaUseCase {

    private NutricionistaDAO nutricionistaDAO;

    public CreateNutricionistaUseCase(NutricionistaDAO nutricionistaDAO) {
        this.nutricionistaDAO = nutricionistaDAO;
    }


    public String insert(Nutricionista nutricionista, String confirmacaoSenha){

        Validator<Nutricionista> validator = new NutricionistaInputRequestValidator();
        Notification notification = validator.validate(nutricionista);

        if(!nutricionista.getSenha().equals(confirmacaoSenha))
            notification.addError("Senhas diferentes!");

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if(nutricionistaDAO.findByUserName(nutricionista.getNomeUsuario()).isPresent())
            throw new EntityAlreadyExistsException("Nutricionista: '"+ nutricionista.getNomeUsuario() +"' já está cadastrado.");

        return nutricionistaDAO.create(nutricionista);
    }


}
