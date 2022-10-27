package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.alimento.AlimentoInputRequestValidator;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.PasswordsDoesntMatchException;
import com.example.nutribem.domain.usecases.utils.Validator;

import static com.example.nutribem.domain.usecases.utils.Validator.nullOrEmpty;

public class CreateNutricionistaUseCase {

    private NutricionistaDAO nutricionistaDAO;
    private static final Integer MINLENGTH=4 ;

    public CreateNutricionistaUseCase(NutricionistaDAO nutricionistaDAO) {
        this.nutricionistaDAO = nutricionistaDAO;
    }


    public String insert(String userName, String senha, String confirmacaoSenha){

        Notification notification = new Notification();

        if(!senha.equals(confirmacaoSenha)){
            throw new PasswordsDoesntMatchException("Senhas diferentes!");
        }

        if(nullOrEmpty(senha)){
            notification.addError("Senha is null or empty");
        }
        if(senha.length()<MINLENGTH){
            notification.addError("Senha does not have four digits");
        }
        if(nullOrEmpty(userName)){
            notification.addError("Nome de usuario is null or empty");
        }

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if(nutricionistaDAO.findByUserName(userName).isPresent())
            throw new EntityAlreadyExistsException("Nutricionista: '"+ userName +"' já está cadastrado.");

        Nutricionista nutricionista = new Nutricionista(senha,userName);

        return nutricionistaDAO.create(nutricionista);
    }


}
